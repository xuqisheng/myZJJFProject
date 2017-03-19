/**   
* @Title: SpVoucherTempServiceImpl.java 
* @Package com.corner.kefu.service.sp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月30日 下午6:36:56 
* @version V1.0   
*/

package com.corner.kefu.service.sp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemBase;
import com.corner.core.beans.SpVoucherTemp;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ItemBaseMapper;
import com.corner.core.dao.SpVoucherTempMapper;
import com.corner.core.utils.JSONUtil;
import com.corner.kefu.beans.ro.sp.SpVoucherTempMgRo;
import com.corner.kefu.beans.ro.sp.SpVoucherTempRo;
import com.corner.kefu.beans.vo.sp.SpVoucherTempVo;
import com.corner.kefu.dao.sp.SpVoucherMgMapper;
import com.corner.kefu.dao.sp.SpVoucherTempMgMapper;
import com.corner.kefu.service.sp.SpVoucherTempService;

/** 
* @ClassName: SpVoucherTempServiceImpl 
* @Description:优惠劵模板业务实现类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月30日 下午6:36:56 
*  
*/
@Service
public class SpVoucherTempServiceImpl implements SpVoucherTempService {

	@Autowired
	SpVoucherTempMgMapper spVoucherTempMgMapper;
	
	@Autowired
	SpVoucherTempMapper spVoucherTempMapper;
	
	@Autowired
	ItemBaseMapper itemBaseMapper;
	
	@Autowired
	SpVoucherMgMapper spVoucherMgMapper;
	
	/**
	 * 
	* Title: getPageSpVoucherTempList 
	* Description:分页查询优惠劵模板列表 
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpVoucherTempService#getPageSpVoucherTempList()
	 */
	@Override
	public Pager<SpVoucherTemp> getPageSpVoucherTempList(SpVoucherTempRo spVoucherTempRo) throws Exception {
		List<SpVoucherTemp>list = spVoucherTempMgMapper.getPageSpVoucherTempList(spVoucherTempRo);
		Integer totalSize = spVoucherTempMgMapper.getCountPageSpVoucherTempList(spVoucherTempRo);
		return new Pager<SpVoucherTemp>(totalSize, list);
	}

	/**
	 * 
	* Title: selectByPrimaryKey 
	* Description:根据优惠劵id查询优惠劵 
	* @param sendId
	* @return 
	* @see com.corner.kefu.service.sp.SpVoucherTempService#selectByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public SpVoucherTemp selectByPrimaryKey(Integer sendId) {
		return spVoucherTempMapper.selectByPrimaryKey(sendId);
	}

	
    /**
     * 	
    * Title: getSpVoucherTemp 
    * Description:根据优惠劵id查询优惠劵 
    * @param spVoucherTemp
    * @return
    * @throws Exception 
    * @see com.corner.kefu.service.sp.SpVoucherTempService#getSpVoucherTemp(com.corner.core.beans.SpVoucherTemp)
     */
	@Override
	public SpVoucherTempVo getSpVoucherTemp(SpVoucherTemp spVoucherTemp) throws Exception {
		SpVoucherTempVo spVoucherTempVo = spVoucherTempMgMapper.getSpVoucherTemp(spVoucherTemp);
		String spVoucherTempStr = spVoucherTempVo.getPayStr();
		spVoucherTempStr = spVoucherTempStr.substring(1, spVoucherTempStr.lastIndexOf(","));
		String[] payStrArr = spVoucherTempStr.split(",");
		String temp = "";
		for (int i = 0; i < payStrArr.length; i++) {
			switch (Integer.parseInt(payStrArr[i])) {
			case 1:
				temp+="快钱支付、";
				break;
			case 2:
				temp+="货到付款、";
				break;
			case 3:
				temp+="支付宝支付、";
				break;
			case 4:
				temp+="微信支付、";
				break;
			default:
				break;
			}
		}
		temp = temp.substring(0, temp.lastIndexOf("、"));
		spVoucherTempVo.setSpVoucherTempPayStr(temp);
		
		if(spVoucherTempVo.getUseItemFlag()==1||spVoucherTempVo.getUseItemFlag()==2){
			//如果优惠劵是指定/排除商品，就给SpVoucherTempVo设置一个list
			//list中的每一个元素是一个map数据,map中存放3个key-value值 用于页面展示
			List<Map<String, Object>>list = new ArrayList<Map<String,Object>>();
			String mgItems = spVoucherTempVo.getMgItems();
			String[] maItemsArr = mgItems.split(";");
			for (int i = 0; i < maItemsArr.length; i++) {
				Map<String, Object>map = new HashMap<String,Object>();
				String[] itemArr = maItemsArr[i].split("@");
				if(itemArr[0].equals("cat")){
					map.put("no1", "cat");//用于表示是商品还是类别
					map.put("no2", itemArr[2]);//如果是类别就存类别名称 如果是商品存商品对象
					if(spVoucherTempVo.getUseItemFlag()==1){
						map.put("no3", itemArr[3]);//存参与还是不参与
					}else {
						map.put("no3", 1);//存参与还是不参与
				    }
				}else if (itemArr[0].equals("item")) {
					map.put("no1", "item");
					ItemBase itemBase = itemBaseMapper.selectByPrimaryKey(Integer.parseInt(itemArr[1]));
					map.put("no2", itemBase);
					if(spVoucherTempVo.getUseItemFlag()==1){
						map.put("no3", itemArr[3]);//存参与还是不参与
					}else {
						map.put("no3", 1);//存参与还是不参与
				    }
				}
				list.add(map);
			}
			String mgItemsStr = JSONUtil.objectToJSONString(list);
			spVoucherTempVo.setMgItemsStr(mgItemsStr);
		}
		return spVoucherTempVo;
	}

    /**
     * 
    * Title: getCountSpVoucherTempList 
    * Description:查询优惠劵列表总量 
    * @param map
    * @return
    * @throws Exception 
    * @see com.corner.kefu.service.sp.SpVoucherTempService#getCountSpVoucherTempList(java.util.Map)
     */
	@Override
	public Integer getCountSpVoucherTempList(Map<String, Object> map) throws Exception {
		return spVoucherTempMgMapper.getCountSpVoucherTempList(map);
	}

	
	/**
	 * 
	* Title: getSpVoucherTempList 
	* Description:查询优惠劵列表 
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpVoucherTempService#getSpVoucherTempList(java.util.Map)
	 */
	@Override
	public List<SpVoucherTempVo> getSpVoucherTempList(Map<String, Object> map) throws Exception {
		List<SpVoucherTempVo> list = spVoucherTempMgMapper.getSpVoucherTempList(map);
		// 循环查询使用数
		for (Iterator<SpVoucherTempVo> iterator = list.iterator(); iterator.hasNext();) {
			SpVoucherTempVo spVoucherTempVo = iterator.next();
			Integer usedCount = spVoucherTempMgMapper.getGrantCountAndUsedCount( spVoucherTempVo.getId());
			spVoucherTempVo.setUsedCount(usedCount);
		}
		return list;
	}

	
	/**
	 * 
	* Title: getIsElegalSpVoucherTemp 
	* Description:判断优惠劵模板是否合法 
	* @param id
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpVoucherTempService#getIsElegalSpVoucherTemp(java.lang.Integer)
	 */
	@Override
	public SpVoucherTemp getIsElegalSpVoucherTemp(Integer id) throws Exception {
		return spVoucherTempMgMapper.getIsElegalSpVoucherTemp(id);
	}

	
	/**
	 * 
	* Title: addSpVoucherTemp 
	* Description:新增优惠劵模板 
	* @param spVoucherTemp
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpVoucherTempService#addSpVoucherTemp(com.corner.core.beans.SpVoucherTemp)
	 */
	@Override
	public void addSpVoucherTemp(SpVoucherTemp spVoucherTemp) throws Exception {
		spVoucherTempMapper.insertSelective(spVoucherTemp);
	}

	
	
	/**
	 * 
	* Title: removeSpvoucherTempById 
	* Description:逻辑删除优惠劵模板 
	* @param id
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpVoucherTempService#removeSpvoucherTempById(java.lang.Integer)
	 */
	@Override
	public void removeSpvoucherTempById(Integer id) throws Exception {
		spVoucherTempMgMapper.removeSpvoucherTempById(id);
		spVoucherMgMapper.deleteBySpVoucherTempId(id);
	}

	
    /**
     * 
    * Title: updatSpVoucherTemp 
    * Description:编辑优惠劵
    * @param spVoucherTemp
    * @throws Exception 
    * @see com.corner.kefu.service.sp.SpVoucherTempService#updatSpVoucherTemp(com.corner.core.beans.SpVoucherTemp)
     */
	@Override
	public void updatSpVoucherTemp(SpVoucherTemp spVoucherTemp) throws Exception {
		spVoucherTempMapper.updateByPrimaryKeySelective(spVoucherTemp);
	}

	/**
	 * 
	* Title: getSpVoucherTempIndex 
	* Description:优惠劵管理页面首页查询 
	* @param spVoucherTempRo
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpVoucherTempService#getSpVoucherTempIndex(com.corner.kefu.beans.ro.sp.SpVoucherTempRo)
	 */
	@Override
	public Pager<SpVoucherTempVo> getSpVoucherTempIndex(SpVoucherTempMgRo spVoucherTempMgRo) throws Exception {
		List<SpVoucherTempVo> list = spVoucherMgMapper.getSpVoucherTempIndex(spVoucherTempMgRo);
		Integer count = spVoucherMgMapper.getCountSpVoucherTempIndex(spVoucherTempMgRo);
		return new Pager<SpVoucherTempVo>(count, list);
	}

}
