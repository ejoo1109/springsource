package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.BookVO;

public interface BookMapper {
	public int insert(BookVO book); 
	public int delete(int code); 
	//개별적으로 넣으려면 @Param 이용
	public int update(@Param("code")int code,@Param("price") int price); 
	public List<BookVO> selectAll();
	public List<BookVO> search(@Param("criteria")String criteria,@Param("keyword") String keyword);
	public int update(BookVO vo);
	
}
