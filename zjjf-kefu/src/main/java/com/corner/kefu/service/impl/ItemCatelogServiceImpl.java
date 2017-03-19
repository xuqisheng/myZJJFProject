package com.corner.kefu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemCatelog;
import com.corner.kefu.beans.ro.ItemCatelogRo;
import com.corner.kefu.beans.vo.sp.ItemCatelogVo;
import com.corner.kefu.dao.ItemBaseMgMapper;
import com.corner.kefu.dao.ItemCatelogMgMapper;
import com.corner.kefu.service.ItemCatelogService;
@Service
public class ItemCatelogServiceImpl implements ItemCatelogService{
	
	@Autowired
	ItemCatelogMgMapper itemCatelogMgMapper;
	
	@Autowired
	ItemBaseMgMapper itemBaseMgMapper;

	@Override
	public List<ItemCatelog> getAllItemCate(ItemCatelogRo item) throws Exception {
		item.setStatus(true);
		item.setIsdelete(false);
		item.setSortOrder("asc");
		item.setSortName("ordId");
		return itemCatelogMgMapper.getAllItemCateByBatch(item);
	}

	
	
	/**
	 * 
	* Title: getTreeItemCateLogAndItemBase 
	* Description: 获取商品分类树形图
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.ItemCatelogService#getTreeItemCateLogAndItemBase()
	 */
	@Override
	public List<ItemCatelogVo> getTreeItemCateLogAndItemBase() throws Exception {
		List<ItemCatelogVo> treeList = itemCatelogMgMapper.getItemCateLogTreeList();
		if(treeList!=null && treeList.size()!=0){
			for (ItemCatelogVo yiji : treeList) {
				yiji.setTreeLevel(1);
				List<ItemCatelogVo> erjiList = yiji.getCatelogVoList();
				if(erjiList!=null && erjiList.size()!=0){
					for (ItemCatelogVo erji : erjiList) {
						erji.setTreeLevel(2);
						List<ItemCatelogVo> sanjiList = erji.getCatelogVoList();
						if(sanjiList!=null&&sanjiList.size()!=0){
							for (ItemCatelogVo sanji : sanjiList) {
								sanji.setTreeLevel(3);
							}							
						}
					}
				}
				
			}
		}
		return treeList;
	}


     /**
      * 
     * Title: getItemCateLogList 
     * Description:获取商品分类 
     * @return
     * @throws Exception 
     * @see com.corner.kefu.service.ItemCatelogService#getItemCateLogList()
      */
	@Override
	public List<ItemCatelogVo> getItemCateLogList() throws Exception {
		return itemCatelogMgMapper.getItemCateLogList();
	}

}
