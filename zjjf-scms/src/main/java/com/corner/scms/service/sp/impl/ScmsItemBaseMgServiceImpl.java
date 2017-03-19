/**   
* @Title: ScmsItemBaseMgServiceImpl.java 
* @Package com.corner.scms.service.sp.impl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月15日 下午4:57:13 
* @version V1.0   
*/
package com.corner.scms.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemBase;
import com.corner.core.dao.ItemBaseMapper;
import com.corner.scms.dao.ScmsItemBaseMgMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sp.ScmsItemBaseMgService;

/** 
 * @ClassName: ScmsItemBaseMgServiceImpl 
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月15日 下午4:57:13  
 */
@Service
public class ScmsItemBaseMgServiceImpl extends BaseServiceImpl implements ScmsItemBaseMgService {
    @Autowired
	ScmsItemBaseMgMapper scmsItemBaseMgMapper;
    
    @Autowired
    ItemBaseMapper itemBaseMapper;
	@Override
	public List<ItemBase> getAllItemBase() {
		return scmsItemBaseMgMapper.getAllItemBase();
	}
	

	@Override
	public void Update(ItemBase itemBase) {
		itemBaseMapper.updateByPrimaryKeySelective(itemBase);
	}
   
	
}
