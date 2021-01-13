package com.company.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.domain.MemberVO;

@Repository
public class MemberDAO {
	@Autowired
	private DataSource ds;
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int insert(MemberVO member) {
		int result=0;
		try {
			String sql = "insert into memberTBL values(?,?,?,?,?)";
			
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getEmail());
			result=pstmt.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	public int update(MemberVO member) {
		int result=0;
		try {
			String sql="update memberTBL set password=? where userid=?";
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getUserid());
			result=pstmt.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	
	public int delete(MemberVO member) {
		int result=0;
		try {
			String sql = "delete from memberTBL where userid=? and password=?";
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPassword());
			result=pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}return result;
	}
	
	public MemberVO getRow(MemberVO member) {
		MemberVO vo=null;
		try {
			String sql = "select * from memberTBL where userid=? and password=?";
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,member.getUserid());
			pstmt.setString(2,member.getPassword());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				vo=new MemberVO(rs.getString(1),
						rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public List<MemberVO> getList(){
		List<MemberVO> list= new ArrayList<>();
		
		try {
			String sql="select*from memberTBL";
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setUserid(rs.getString("userid"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setGender(rs.getString("gender"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
