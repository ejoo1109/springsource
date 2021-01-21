package com.company.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardVO {
	private int bno;
	private String title;
	private String content;	
	private String writer;
	private Date regdate;
	private Date updatedate;
	private int replycnt; //게시글 옆의 칼럼수 추가
	
	//파일 첨부 목록
	private List<FileAttach> attachList;
}
