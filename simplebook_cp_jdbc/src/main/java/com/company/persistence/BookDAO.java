package com.company.persistence;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BookVO;

@Repository
public class BookDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insert(BookVO vo) {
		System.out.println("====> insert");
		
		String sql = "insert into bookTBL values(?,?,?,?)";
		int result =jdbcTemplate.update(sql,vo.getCode(),vo.getTitle(),vo.getWriter(),vo.getPrice());
		return result;
	}
	public int update(BookVO vo) {
		System.out.println("====> update");
		
		String sql="update bookTBL set price=? where code=?";
		return jdbcTemplate.update(sql,vo.getPrice(),vo.getCode());
	}
	public int delete(int code) {
		System.out.println("===> delete");
		
		String sql="delete from bookTBL where code=?";
		return jdbcTemplate.update(sql,code);
	}
	public BookVO getRow(int code) {
		System.out.println("====> getRow");
		
		String sql="select * from bookTBL where code=?";
		Object args[]= {code};
		return jdbcTemplate.queryForObject(sql,args, new BookRowMapper());
	}
	public List<BookVO> getList(){
		System.out.println("====> getList");
		
		String sql = "select * from bookTBL";
		return jdbcTemplate.query(sql, new BookRowMapper());


	}
}
