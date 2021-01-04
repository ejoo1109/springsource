package com.company.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.domain.BoardVO;
import static com.company.persistence.JDBCUtil.*;

//@Repository : 빈 생성 역할 (new BoardDAO())

@Repository
public class BoardDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int insert(BoardVO vo) {
		int result=0;
		
		try {
			con = getConnection();
			String sql ="insert into spring_board(bno,title,content,writer) values(seq_board.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			result = pstmt.executeUpdate();
			if(result>0) commit(con);
			
		} catch (Exception e) {
			e.printStackTrace();
			rollback(con);
		} finally {
			close(pstmt);
			close(con);			
		}		
		return result;
	}
	
	
	public int update(BoardVO vo) {
		int result=0;
		
		try {

			String sql ="update spring_board set title=?, content=?, updatedate=sysdate where bno=?";
			con =getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setDate(3, (Date) vo.getUpdatedate());
			pstmt.setInt(4,vo.getBno());
			result=pstmt.executeUpdate();
			if(result>0) {
				commit(con);
			}
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return result;
	}
	public int delete(BoardVO vo) {
		int result=0;
		
		try {
			String sql="delete from spring_board where bno=?";
			con=getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getBno());
			result= pstmt.executeUpdate();
			if(result>0) {
				commit(con);
			}
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(con);
		}
		return result;
	}
	public BoardVO getRow(int bno) {
		BoardVO vo=null;
		try {
			String sql="select * from spring_board where bno=?";
			
			con=getConnection();
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
		}finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return vo;
	}
	public List<BoardVO> getList() {
		List<BoardVO> list=new ArrayList();
		
		try {
			con=getConnection();
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
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return list;
	}
}
