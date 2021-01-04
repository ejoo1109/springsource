package com.company.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.domain.BoardVO;

//@Repository : 빈 생성 역할 (new BoardDAO())

@Repository
public class BoardDAO {
	
	@Autowired
	private DataSource ds;
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int insert(BoardVO vo) {
		int result=0;
		
		try {
			String sql ="insert into spring_board(bno,title,content,writer) values(seq_board.nextval,?,?,?)";
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			result = pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();		
		}		
		return result;
	}
	
	
	public int update(BoardVO vo) {
		int result=0;
		
		try {

			String sql ="update spring_board set title=?, content=?, updatedate=sysdate where bno=?";
			con =ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3,vo.getBno());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int delete(BoardVO vo) {
		int result=0;
		
		try {
			String sql="delete from spring_board where bno=?";
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getBno());
			result= pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public BoardVO getRow(int bno) {
		BoardVO vo=null;
		try {
			String sql="select * from spring_board where bno=?";
			
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setUpdatedate(rs.getDate("updatedate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	public List<BoardVO> getList() {
		List<BoardVO> list=new ArrayList();
		
		try {
			con=ds.getConnection();
			String sql="select * from spring_board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setUpdatedate(rs.getDate("updatedate"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
