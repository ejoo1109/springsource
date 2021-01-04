package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.MemberVO;

@Repository
public class MemberDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insert(MemberVO member) {
		System.out.println("====> insert");
		
		String sql = "insert into memberTBL values(?,?,?,?,?)";
		return jdbcTemplate.update(sql,member.getUserid(),member.getPassword(), member.getName(), member.getGender(), member.getEmail());
	}
	public int update(MemberVO member) {
		System.out.println("====> update");
		
		String sql="update memberTBL set password=? where userid=?";
		return jdbcTemplate.update(sql,member.getPassword(),member.getUserid());
	}
	
	public int delete(MemberVO member) {
		System.out.println("====> delete");
		
		String sql = "delete from memberTBL where userid=? and password=?";
		return jdbcTemplate.update(sql,member.getUserid(),member.getPassword());
	}
	
	public MemberVO getRow(MemberVO member) {
		System.out.println("====> getRow");

		String sql = "select * from memberTBL where userid=? and password=?";
		Object args[]= {member.getUserid(),member.getPassword()};
		return jdbcTemplate.queryForObject(sql,args, new MemberRowMapper());
	}
	
	public List<MemberVO> getList(){
		System.out.println("====> getList");
		
		String sql="select*from memberTBL";
		return jdbcTemplate.query(sql, new MemberRowMapper());
	}
}
