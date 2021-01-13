package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.damain.PersonVO;

//DAO역할
public interface PersonMapper {
	//annotation 설정 방식=sql 구문을 인라인 방식
//	@Insert("insert into person(id,name) values(#{id},#{name})")
//	public int insert(@Param("id")String id, @Param("name")String name);
//	
//	@Update("update person set name=#{name} where id=#{id}")
//	public int update(@Param("id")String id, @Param("name")String name);
//	
//	@Delete("delete from person where id=#{id}")
//	public int delete(String id);
	
	//sql 구문을 xml방식
	public int insert(@Param("id")String id, @Param("name")String name);
	public int update(@Param("id")String id, @Param("name")String name);
	public int delete(String id);
	public String getPerson(String id);
	public List<PersonVO> selectAll();
}
