package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BoardVO;

//@Repository : 빈 생성 역할 (new BoardDAO())

@Repository
public class BoardDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insert(BoardVO vo) {
		System.out.println("====>Spring JDBC insert");
		
		String sql ="insert into spring_board(bno,title,content,writer) values(seq_board.nextval,?,?,?)";
		int	result = jdbcTemplate.update(sql,vo.getTitle(),vo.getContent(),vo.getWriter());
		return result;
	}
	
	public int update(BoardVO vo) {
		System.out.println("====>Spring JDBC update");
		
		String sql ="update spring_board set title=?, content=?, updatedate=sysdate where bno=?";
		int result=jdbcTemplate.update(sql,vo.getTitle(),vo.getContent(),vo.getBno());
		return result;
	}
	public int delete(BoardVO vo) {
		System.out.println("====>Spring JDBC delete");
		
		String sql="delete from spring_board where bno=?";
		int	result= jdbcTemplate.update(sql,vo.getBno());
		return result;
	}
	public BoardVO getRow(int bno) {
		System.out.println("====>Spring JDBC getRow");
			
		String sql="select * from spring_board where bno=?";
		Object[] args= {bno};
		BoardVO vo=jdbcTemplate.queryForObject(sql, args, new BoardRowMapper());
		return vo;
	}
	public List<BoardVO> getList() {
		System.out.println("====>Spring JDBC getList");
		
		String sql="select * from spring_board";
		return jdbcTemplate.query(sql, new BoardRowMapper());
	}
}
