package com.lixy.service.impl;

import java.util.List;

import com.lixy.model.domain.Menu;
import com.lixy.model.domain.MenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lixy.mapper.generator.MenuMapper;
import com.lixy.service.MenuService;

/**
 * @author : 宋浩志
 * @createDate : 2018年12月18日
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class MenuServiceImpl implements MenuService {

	private static final String MENUS_CACHE_KEY = "'menu'";
	private static final String MENUS_CACHE_NAME = "menus";

	@Autowired
	private MenuMapper menuMapper;

	@Override
//	@Cacheable(value = MENUS_CACHE_NAME, key = MENUS_CACHE_KEY)
	public List<Menu> findMenus() {
		MenuExample example = new MenuExample();
		example.setOrderByClause("menu_sort");
		return menuMapper.selectByExample(example);
	}

	@Override
	public Menu findByMenuId(Integer menuId) {
		return menuMapper.selectByPrimaryKey(menuId);
	}

	@Override
//	@CacheEvict(value = MENUS_CACHE_NAME, allEntries = true, beforeInvocation = true)
	public void save(Menu menu) {
		menuMapper.insertSelective(menu);
	}

	@Override
//	@CacheEvict(value = MENUS_CACHE_NAME, allEntries = true, beforeInvocation = true)
	public void edit(Menu menu) {
		menuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
//	@CacheEvict(value = MENUS_CACHE_NAME, allEntries = true, beforeInvocation = true)
	public void remove(Integer menuId) {
		menuMapper.deleteByPrimaryKey(menuId);
	}

}
