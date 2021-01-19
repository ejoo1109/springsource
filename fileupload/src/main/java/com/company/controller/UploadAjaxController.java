package com.company.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.FileAttach;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UploadAjaxController {

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("ajax 업로드 폼 요청");
	}
	
	@PostMapping(value="/uploadAjax", produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileAttach>> uploadPost(MultipartFile[] uploadFile) {
		log.info("업로드 요청");
		
		String uploadFolder="c:\\upload";
		String uploadFileName=null;
		
		//폴더 생성
		String uploadFolderPath = getFolder();// 2021\\01\\19
		File uploadPath= new File(uploadFolder, uploadFolderPath); //d:\\upload\\2021\\01\\19
		
		if(!uploadPath.exists()) {
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
			uploadFileName = uuid.toString()+"_"+f.getOriginalFilename();
			
			FileAttach attach = new FileAttach();
			attach.setFileName(f.getOriginalFilename());
			attach.setUploadPath(uploadFolderPath);
			attach.setUuid(uuid.toString());
			
			File saveFile = new File(uploadPath,uploadFileName);
			
			try {
				//transferTo - 서버에 저장하는 역할 
				f.transferTo(saveFile);
				//이미지인지 일반 파일인지 확인
				if(checkImageType(saveFile)) {
					attach.setImage(true);
				}
				attachList.add(attach);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<FileAttach>>(attachList,HttpStatus.OK);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator); 
		//폴더 구분시 사용하는 문자 - windows \, 리눅스 /
	}
	
} 
