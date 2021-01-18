package com.company.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//댓글 페이지에 대한 vo
@Setter
@Getter
@AllArgsConstructor
public class ReplyPageVO {
	private int replyCnt; //전체 게시물 댓글 수
	private List<ReplyVO> list; //게시물 리스트
}
