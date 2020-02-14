package com.lixy.mapper.generator;

import com.lixy.model.domain.ArticleTag;
import com.lixy.model.domain.ArticleTagExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTagMapper {
	long countByExample(ArticleTagExample example);

	int deleteByExample(ArticleTagExample example);

	int insert(ArticleTag record);

	int insertSelective(ArticleTag record);

	List<ArticleTag> selectByExample(ArticleTagExample example);

	int updateByExampleSelective(@Param("record") ArticleTag record, @Param("example") ArticleTagExample example);

	int updateByExample(@Param("record") ArticleTag record, @Param("example") ArticleTagExample example);
}