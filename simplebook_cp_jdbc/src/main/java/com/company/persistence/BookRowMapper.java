package com.company.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.domain.BookVO;

public class BookRowMapper implements RowMapper<BookVO> {

	@Override
	public BookVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BookVO vo=new BookVO(rs.getInt("code"), rs.getString("title"),
			rs.getString("writer"),rs.getInt("price"));
		return vo;
	}

}
