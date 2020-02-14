package com.lixy.mapper.generator;

import com.lixy.model.domain.ArticleCategory;
import com.lixy.model.domain.ArticleCategoryExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCategoryMapper {
	long countByExample(ArticleCategoryExample example);

	int deleteByExample(ArticleCategoryExample example);

	int insert(ArticleCategory record);

	int insertSelective(ArticleCategory record);

	List<ArticleCategory> selectByExample(ArticleCategoryExample example);

	int updateByExampleSelective(@Param("record") ArticleCategory record,
			@Param("example") ArticleCategoryExample example);

	int updateByExample(@Param("record") ArticleCategory record, @Param("example") ArticleCategoryExample example);
}