package com.company.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.FileAttach;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Slf4j
public class UploadAjaxController {

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("ajax 업로드 폼 요청");
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="/uploadAjax", produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileAttach>> uploadPost(MultipartFile[] uploadFile) {
		log.info("업로드 요청");
		
		String uploadFolder="c:\\upload";
		String uploadFileName=null;
		
		//폴더 생성 - 날짜별로 저장할 폴더
		String uploadFolderPath = getFolder();// 2021\\01\\19
		File uploadPath= new File(uploadFolder, uploadFolderPath); //c:\\upload\\2021\\01\\19
		
		if(!uploadPath.exists()) { //폴더가 존재하지 않으면 생성요청
			uploadPath.mkdirs();
		}
		
		//첨부 파일에 대한 정보를 담을 객체 생성
		List<FileAttach> attachList = new ArrayList<>();
		
		for(MultipartFile f:uploadFile) {
			log.info("----------------------");
			log.info("upload file name : "+ f.getOriginalFilename());
			log.info("upload file size : "+ f.getSize());
			
			//파일명 중복 해결
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString()+"_"+f.getOriginalFilename(); //16진수를 이용하여 파일명 생성
			
			FileAttach attach = new FileAttach();//서버에서 받은 파일 받아서 서버로 리턴
			attach.setFileName(f.getOriginalFilename());
			attach.setUploadPath(uploadFolderPath);
			attach.setUuid(uuid.toString());
			
			File saveFile = new File(uploadPath,uploadFileName);
			
			try {
				//이미지인지 일반 파일인지 확인
				if(checkImageType(saveFile)) {
					attach.setFileType(true);
					//이미지라면 썸네일로 한번 더 저장(원본파일과 + 이름변경된파일)
					//파일 저장시 c:\\upload\\2021\\01\\20\\s_1224dfdfdf_원본파일명.jpg
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					InputStream in = f.getInputStream();
					Thumbnailator.createThumbnail(in,thumbnail,100,100);
					in.close();
					thumbnail.close();
				}
				//transferTo - 서버에 저장하는 역할 
				f.transferTo(saveFile);
				attachList.add(attach);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		//받은 리스트를 http 상태코드와함께 리턴 jsp에서 success에서 받을예정
		return new ResponseEntity<List<FileAttach>>(attachList,HttpStatus.OK);
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("썸네일 요청 " + fileName);
		
		File f = new File("c:\\upload\\"+fileName);
		
		ResponseEntity<byte[]> entity=null;
		
		HttpHeaders headers=new HttpHeaders();
		try {
			headers.add("Content-Type", Files.probeContentType(f.toPath())); // image/jpg
			//FileCopyUtils.copyToByteArray(f)-서버에 있는 파일을 복사하여 보내줌
			entity= new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(f),headers,HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	@GetMapping(value="/download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download(String fileName) {
		log.info("다운로드 요청"+fileName);
		
		Resource resource = new FileSystemResource("c:\\upload\\"+fileName);
		
		//"_" 이후 잘라내기해서 uuid없이 원본 파일명으로 다운로드
		String resourceUidName = resource.getFilename();
		String resourceName = resourceUidName.substring(resourceUidName.indexOf("_")+1); 
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachment;filename="
					+ new String(resourceName.getBytes("utf-8"),"ISO-8859-1"));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
	
	//서버에서 파일 삭제
	@PostMapping("/deleteFile")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteFile(String fileName, String type){

		log.info("파일삭제"+fileName+" 타입 : "+type);
		
		try {
			File file = new File("c:\\upload\\"+URLDecoder.decode(fileName,"utf-8"));
			
			//파일 (썸네일, 일반파일) 삭제
			file.delete();
			
			if(type.equals("image")) { //이미지였다면 원본 이미지 삭제
				String oriName =  file.getAbsolutePath().replace("s_", "");
				file = new File(oriName);
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	//서버에 저장한 파일이 이미지인지 일반 파일인지 확인
	// 이미지 파일 인지만 걸러주기 때문에 .jsp .sql 파일은 null 오류난다.
	private boolean checkImageType(File file) {//~.txt -> text/plain, text/html, image/jpeg, image/png

//			String contentType = Files.probeContentType(file.toPath());
//			return contentType.startsWith("image");
			MimetypesFileTypeMap m = new MimetypesFileTypeMap();
			m.addMimeTypes("image png jpg jpeg gif");
			return m.getContentType(file).contains("image");
	}
	
	//날짜에 따라 폴더 생성하기
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //원하는 형식으로 데이터 표현
		Date date = new Date(); //기본값은 시간,날짜가 길게나옴
		String str = sdf.format(date); //원하는 형식으로 시간,날짜 변경요청
		return str.replace("-", File.separator); //운영체제에 맞는 형식으로 요청 2021\01\19
		//폴더 구분시 사용하는 문자 - windows \, 리눅스 /
	}
	
} 
