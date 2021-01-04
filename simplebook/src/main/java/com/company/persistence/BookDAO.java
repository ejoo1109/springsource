package com.company.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.domain.BookVO;
import static com.company.persistence.JDBCUtil.*;

@Repository
public class BookDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	public int insert(BookVO vo) {
		int result=0;
		try {
			String sql = "insert into bookTBL values(?,?,?,?)";
			
			con=getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getCode());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getPrice());
			result=pstmt.executeUpdate();
			if(result>0) {
				commit(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(con);
		}
		return result;
	}
	public int update(BookVO vo) {
		int result=0;
		try {
			String sql="update bookTBL set price=? where code=?";
			con=getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPrice());
			pstmt.setInt(2, vo.getCode());
			result=pstmt.executeUpdate();
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
	public int delete(int code) {
		int result=0;
		
		try {
			String sql="delete from bookTBL where code=?";
			con=getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs=pstmt.executeQuery();
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
	public BookVO getRow(int code) {
		BookVO vo=null;
		
		try {
			String sql="select * from bookTBL where code=?";
			con=getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new BookVO();
				vo.setCode(rs.getInt("code"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setPrice(rs.getInt("price"));
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
	public List<BookVO> getList(){
		List<BookVO> list = new ArrayList<>();
		
		try {
			String sql = "select * from bookTBL";
			con=getConnection();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(new BookVO(rs.getInt("code"), rs.getString("title"),
						rs.getString("writer"),rs.getInt("price")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return list;
	}
}
