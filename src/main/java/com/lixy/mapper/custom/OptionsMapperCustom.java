package com.lixy.mapper.custom;

import java.util.List;
import java.util.Map;

import com.lixy.model.domain.Options;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

/**
 * @author : 宋浩志
 * @createDate : 2018年10月14日
 * 
 */
@Repository
public interface OptionsMapperCustom {
	/**
	 * 保存
	 * 
	 * @param map
	 */
	void saveMap(@Param("map") Map<String, Object> map);

	/**
	 * 所有设置选项
	 * 
	 * @return map
	 */
	List<Options> selectMap();

}
