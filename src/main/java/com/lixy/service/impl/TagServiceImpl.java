package com.lixy.service.impl;

import java.util.List;

import com.lixy.model.domain.Tag;
import com.lixy.model.domain.TagExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lixy.mapper.generator.TagMapper;
import com.lixy.service.TagService;

/**
 * @author : 宋浩志
 * @createDate : 2018年9月25日
 * 
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class TagServiceImpl implements TagService {
	private static final String TAGS_CACHE_KEY = "'tag'";
	private static final String TAGS_CACHE_NAME = "tags";
	@Autowired
	private TagMapper tagMapper;

	@Override
//	@Cacheable(value = TAGS_CACHE_NAME, key = TAGS_CACHE_KEY)
	public List<Tag> findTags() {
		return tagMapper.selectByExample(null);
	}

	@Override
	public Tag findByTagId(int linkId) {
		return tagMapper.selectByPrimaryKey(linkId);
	}

	@Override
//	@CacheEvict(value = TAGS_CACHE_NAME, allEntries = true, beforeInvocation = true)
	public void save(Tag tag) throws Exception {
		tagMapper.insert(tag);
	}

	@Override
//	@CacheEvict(value = TAGS_CACHE_NAME, allEntries = true, beforeInvocation = true)
	public void update(Tag tag) throws Exception {
		tagMapper.updateByPrimaryKeySelective(tag);
	}

	@Override
//	@CacheEvict(value = TAGS_CACHE_NAME, allEntries = true, beforeInvocation = true)
	public void remove(int tagId) throws Exception {
		tagMapper.deleteByPrimaryKey(tagId);
	}

	@Override
	public Tag findByTagUrl(String tagUrl) {
		TagExample tagExample = new TagExample();
		TagExample.Criteria criteria = tagExample.createCriteria();
		criteria.andTagUrlEqualTo(tagUrl);
		return tagMapper.selectByExample(tagExample).get(0);
	}

}
