/**   
* @Title: ScmsItemCatelogMgServiceImpl.java 
* @Package com.corner.scms.service.sp.impl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月7日 下午1:59:45 
* @version V1.0   
*/
package com.corner.scms.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemCatelog;
import com.corner.scms.dao.ScmsItemCatelogMgMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sp.ScmsItemCatelogMgService;

/** 
 * @ClassName: ScmsItemCatelogMgServiceImpl 
 * @Description:商品类别接口实现类
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月7日 下午1:59:45  
 */
@Service
public class ScmsItemCatelogMgServiceImpl extends BaseServiceImpl implements ScmsItemCatelogMgService {
    @Autowired
	ScmsItemCatelogMgMapper scmsItemCatelogMgMapper;
	
	@Override
	public List<ItemCatelog> selectAll() {
		
		return scmsItemCatelogMgMapper.selectAll();
	}

}
