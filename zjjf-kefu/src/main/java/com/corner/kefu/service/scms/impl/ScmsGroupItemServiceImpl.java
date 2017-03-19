package com.corner.kefu.service.scms.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ScmsGroupMapper;
import com.corner.core.dao.ScmsItemMapper;
import com.corner.kefu.beans.ro.scms.ScmsItemMgRo;
import com.corner.kefu.dao.ItemBaseMgMapper;
import com.corner.kefu.dao.ScmsGroupMgMapper;
import com.corner.kefu.dao.ScmsItemMgMapper;
import com.corner.kefu.service.scms.ScmsGroupItemService;
/**
 * 
* @ClassName: ScmsManagerMgServiceImpl 
* @Description: 商品信息处理 
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2015年12月28日 上午11:45:29 
*
 */
@Service
public class ScmsGroupItemServiceImpl implements ScmsGroupItemService {
	private static Logger logger = LoggerFactory.getLogger(ScmsGroupItemServiceImpl.class);
	@Autowired
	ScmsItemMapper scmsItemMapper;
	@Autowired
	ScmsItemMgMapper scmsItemMgMapper;
	@Autowired
	ItemBaseMgMapper itemBaseMgMapper;
	@Autowired
	ScmsGroupMgMapper scmsGroupMgMapper;
	@Autowired
	ScmsGroupMapper scmsGroupMapper;
	
	@Override
	public Pager<Map<String, Object>> getGorupItemlistPage(Map<String, Object> map) {
		logger.info("查询联合采购商品信息内容");
		int size = scmsGroupMgMapper.selectGorupItemListPageSize(map);
		List<Map<String, Object>> list = scmsGroupMgMapper.selectGorupItemListPage(map);
		return new Pager<Map<String, Object>>(size,list);
	}
}
