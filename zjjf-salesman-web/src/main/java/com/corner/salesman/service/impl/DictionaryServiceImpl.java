package com.corner.salesman.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.dao.DictionaryMapper;
import com.corner.salesman.model.Dictionary;
import com.corner.salesman.service.DictionaryService;
import com.corner.scms.config.Constants;
import com.corner.scms.utils.JedisUtils;
@Service
public class DictionaryServiceImpl implements DictionaryService {
	
	@Autowired
	private DictionaryMapper dictMapper;

	@Override
	public List<Dictionary> findDictList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<Dictionary> getDictPageList(Dictionary dictiVO) throws Exception {
		List<Dictionary> list = dictMapper.getDictPageList(dictiVO);
		int size = dictMapper.getDictPageSize(dictiVO);
		return new Pager<Dictionary>(size, list);
	}
	
	/**
	 * 设置抓取坐标位置的时间频率
	 * @param dictiVO
	 */
	public void setTimeHz2Redis(Dictionary dictiVO){
		//如果类型不为空且类型等于time_hz_type时，设置到redis中
		String type = dictiVO.getTimeHzType();
		if(StringUtils.isNotBlank(type) && Constants.TIME_HZ_TYPE_KEY.equals(type)&& Constants.TIME_HZ_VAL_KEY.equals(dictiVO.getTimeHzVal())){
			JedisUtils.set(Constants.TIME_HZ_VAL_KEY, dictiVO.getValue(), 0);
		}
	}

	@Override
	public int addDictionary(Dictionary dictiVO) throws Exception {
		//设置抓取坐标位置的时间频率
		setTimeHz2Redis(dictiVO);
		resetShopTypeCache(dictiVO);
		return dictMapper.insertSelective(dictiVO);
	}

	@Override
	public int updateDictionary(Dictionary dictiVO) throws Exception {
		//设置抓取坐标位置的时间频率
		setTimeHz2Redis(dictiVO);
		resetShopTypeCache(dictiVO);
		return dictMapper.updateByPrimaryKeySelective(dictiVO);
	}
	
	/**
	 * 如果添加或修改涉及到shop_type类型的数据，需要删除缓存数据
	 * @param dictiVO
	 */
	public void resetShopTypeCache(Dictionary dictiVO){
		String type =  dictiVO.getType();
		if("shop_type".equals(type)){
			JedisUtils.delObject(Constants.SHOP_TYPE_CONVERT_MAP);
		}
	}

	@Override
	public int deleteDictionary(Dictionary dictiVO) throws Exception {
		return dictMapper.updateByPrimaryKeySelective(dictiVO);
	}

	@Override
	public Dictionary findDictionaryById(String id) throws Exception {
		return dictMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据类型查询字典中的配置数据
	 * @param record
	 * @return
	 */
	@Override
	public List<Dictionary> findDictListByType(String type) throws Exception{
		return dictMapper.findDictListByType(type);
	}
	
	public boolean isExit_type_value_recode(Dictionary dictVo) throws Exception {
		try {
			List<Dictionary> exitList = dictMapper.findDictListByTypeAndValue(dictVo);
			if (exitList != null && exitList.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
