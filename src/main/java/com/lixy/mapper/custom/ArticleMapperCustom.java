package com.lixy.mapper.custom;

import java.util.List;

import com.lixy.model.domain.ArticleCustom;
import com.lixy.model.domain.Category;
import com.lixy.model.domain.Tag;
import com.lixy.model.dto.ArchiveBo;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

/**
 * @author : 宋浩志
 * @createDate : 2018年11月1日
 */
@Repository
public interface ArticleMapperCustom {

	List<ArticleCustom> articleMapperCustom(@Param(value = "status") int status);

	List<ArticleCustom> findAllArticle(@Param(value = "status") int status, @Param(value = "post") String post);

	Integer countByStatus(@Param(value = "status") Integer status, @Param(value = "post") String post,@Param(value = "userId") Integer userId);

	void updateStatus(@Param(value = "id") int id, @Param(value = "status") int status);

	ArticleCustom findByArticleId(@Param(value = "id") Integer article_id);

	List<ArchiveBo> findDateAndCount();

	List<ArticleCustom> findPageArticle(ArticleCustom articleCustom);

	int findRepeatByUrl(@Param(value = "articleUrl") String articleUrl);

	ArticleCustom findByArticleUrl(@Param(value = "articleUrl") String articleUrl);

	List<ArticleCustom> findArtileByCategory(@Param("category") Category category, @Param(value="status") int status);

	List<ArticleCustom> findArtileByTag(@Param("tag") Tag tag, @Param(value="status") int status);
}
