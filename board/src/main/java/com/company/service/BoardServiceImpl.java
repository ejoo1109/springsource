package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.FileAttach;
import com.company.mapper.AttachMapper;
import com.company.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	@Autowired
	private AttachMapper attachmapper;

	@Transactional
	@Override
	public boolean regist(BoardVO board) {
		
		boolean result = mapper.insert(board)>0?true:false;
		
		//첨부파일이 null이거나 size()가 0이라면 글만 등록
		if(board.getAttachList()==null || board.getAttachList().size()<=0) {
			return result;
		}
		//첨부파일 삽입
		board.getAttachList().forEach(attach -> { 
			attach.setBno(board.getBno());
			attachmapper.insert(attach);
		});
		return result;
	}

	@Override
	public boolean modify(BoardVO board) {
		
		//첨부파일 전체 삭제
		attachmapper.delete(board.getBno());
		//게시물 수정
		boolean result= mapper.update(board)>0?true:false;
		
		//첨부파일이 null이거나 size()가 0이라면 글만 등록
		if(board.getAttachList()==null || board.getAttachList().size()<=0) {
			return result;
		}
		//첨부파일 삽입
		board.getAttachList().forEach(attach -> { 
			attach.setBno(board.getBno());
			attachmapper.insert(attach);
		});
		return result;
	}

	@Override
	public boolean remove(int bno) {
		return mapper.delete(bno)>0?true:false;
	}

	@Override
	public BoardVO getRow(int bno) {
		return mapper.read(bno);
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return mapper.list(cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.totalCnt(cri);
	}

	@Override
	public List<FileAttach> getAttachList(int bno) {
		return mapper.attachList(bno);
	}



}
