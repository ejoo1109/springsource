package com.company.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
// 서버에 저장되는 파일 관리
@Getter
@Setter
@ToString
public class FileAttach {
 private String uuid;
 private String uploadPath;
 private String fileName;
 private boolean image;
}
