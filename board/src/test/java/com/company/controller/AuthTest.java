package com.company.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class) //Test 클래스 명시
//톰캣위에서 실행되는게 아니기때문에 xml 파일 읽기 설정
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})

@Slf4j
public class AuthTest {
	
	@Autowired
	private DataSource ds;
	
	@Test //테스트 메소드 (조건:public, void, 파라메터 없음)
	public void test() {
		log.info("테스트 호출");
		
		String sql = "insert into spring_member_auth(userid,auth) values(?,?)";
		
		for(int i = 0; i<100; i++) {
			//try(커넥션) : 자동으로 close됨
			try (Connection con = ds.getConnection();
				PreparedStatement pstmt=con.prepareStatement(sql)){
				
				if(i<80) {
					pstmt.setString(1, "user"+i);
					pstmt.setString(2,"ROLE_MEMBER");
				}else if(i<90) {
					pstmt.setString(1, "manager"+i);
					pstmt.setString(2,"ROLE_MANAGER");					
				}else {
					pstmt.setString(1, "admin"+i);
					pstmt.setString(2,"ROLE_ADMIN");
				}
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
