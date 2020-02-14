package com.lixy.mapper.generator;

import com.lixy.model.domain.Link;
import com.lixy.model.domain.LinkExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkMapper {
	long countByExample(LinkExample example);

	int deleteByExample(LinkExample example);

	int deleteByPrimaryKey(Integer linkId);

	int insert(Link record);

	int insertSelective(Link record);

	List<Link> selectByExample(LinkExample example);

	Link selectByPrimaryKey(Integer linkId);

	int updateByExampleSelective(@Param("record") Link record, @Param("example") LinkExample example);

	int updateByExample(@Param("record") Link record, @Param("example") LinkExample example);

	int updateByPrimaryKeySelective(Link record);

	int updateByPrimaryKey(Link record);
}