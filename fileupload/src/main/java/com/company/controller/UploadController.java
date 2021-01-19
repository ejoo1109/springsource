package com.company.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UploadController {
	
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("업로드 폼 요청");
	}
	
	@PostMapping("/uploadForm")
	public void uploadPost(MultipartFile[] uploadFile) {
		log.info("업로드 요청");
		
		String uploadPath="c:\\upload";
		
		for(MultipartFile f:uploadFile) {
			log.info("----------------------");
			log.info("upload file name : "+ f.getOriginalFilename());
			log.info("upload file size : "+ f.getSize());
			
			File saveFile = new File(uploadPath, f.getOriginalFilename());
			
			try {
				//transferTo - 서버에 저장하는 역할 
				f.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@GetMapping(value="/download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download(String fileName) {
		log.info("다운로드 요청"+fileName);
		
		Resource resource = new FileSystemResource("c:\\upload\\"+fileName);
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachment;filename="
					+ new String(resourceName.getBytes("utf-8"),"ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
}
