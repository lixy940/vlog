package com.lixy.mapper.generator;

import com.lixy.model.domain.Tag;
import com.lixy.model.domain.TagExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagMapper {
	long countByExample(TagExample example);

	int deleteByExample(TagExample example);

	int deleteByPrimaryKey(Integer tagId);

	int insert(Tag record);

	int insertSelective(Tag record);

	List<Tag> selectByExample(TagExample example);

	Tag selectByPrimaryKey(Integer tagId);

	int updateByExampleSelective(@Param("record") Tag record, @Param("example") TagExample example);

	int updateByExample(@Param("record") Tag record, @Param("example") TagExample example);

	int updateByPrimaryKeySelective(Tag record);

	int updateByPrimaryKey(Tag record);
}