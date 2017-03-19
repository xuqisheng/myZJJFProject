package com.corner.scms.service.sp.impl;

import com.corner.core.beans.*;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.*;
import com.corner.core.utils.JSONUtil;
import com.corner.scms.beans.ro.PromotionRo;
import com.corner.scms.beans.vo.ScmsItemBaseVo;
import com.corner.scms.beans.vo.SpVoucherActiveVo;
import com.corner.scms.beans.vo.SpVoucherTempVo;
import com.corner.scms.beans.vo.SpvoucherVo;
import com.corner.scms.dao.PromotionMgMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sp.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: ScmsManagerMgServiceImpl 
* @Description: 商品信息处理 
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2015年12月28日 上午11:45:29 
*
*/
@Service
public class PromotionServiceImpl extends BaseServiceImpl implements PromotionService {

	private static Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

	@Autowired
	private SpVoucherActiveMiddleMapper spVoucherActiveMiddleMapper;
	
	@Autowired
	private PromotionMgMapper mgMapper;
	
	@Autowired
	ItemBaseMapper itemBaseMapper;
	
	@Autowired
	private SpVoucherActiveGiftMapper spVoucherActiveGiftMapper;
	
	@Autowired
	private SpVoucherActiveMapper spVoucherActiveMapper;
	
	@Autowired
	private SpVoucherTempMapper spVoucherTempMapper;
	@Autowired
	private SendTimeConfigMapper sendTimeConfigMapper;

	@Override
	public List<SpVoucherActiveVo> findAllVo(PromotionRo condition) {
		//自愿参与
				List<SpVoucherActiveVo> vo2=this.mgMapper.findZiYuanActive(condition);
				Map<Integer, Object> v1=new HashMap<Integer, Object>();
				for(SpVoucherActiveVo v:vo2){
					int number=this.mgMapper.findCount(v.getId());
					v.setNumber(number);
					v.setZiyuan(1);
					v1.put(v.getId(), v);
					if(v.getRuleType()==1||v.getRuleType()==2||v.getRuleType()==9){
							condition.setId(v.getId().toString());
							Double num=this.mgMapper.findMoneyByCondition(condition);
							
							if(num==null){
								v.setMoney(0.0);
							}else{
								BigDecimal   b   =   new   BigDecimal(num); 
								num   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
								v.setMoney(num);
							}
							
					}else if(v.getRuleType()==3||v.getRuleType()==10){
						condition.setId(v.getId().toString());
						Double num=this.mgMapper.findMoneyJByCondition(condition);
						
						if(num==null){
							v.setMoney(0.0);
						}else{
							BigDecimal   b   =   new   BigDecimal(num); 
							num   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
							v.setMoney(num);
						}
					}
				}
				vo2.clear();
				for (Map.Entry<Integer, Object> entry : v1.entrySet()) {  
					  vo2.add((SpVoucherActiveVo) entry.getValue());
				} 
			
		//平台活动
		List<SpVoucherActiveVo> vo=this.mgMapper.findPingTaiActive(condition);
		
		//平台活动  所有定格
		List<SpVoucherActiveVo> vo3=this.mgMapper.findAllGroupId(condition);
		vo2.addAll(vo);
		vo2.addAll(vo3);
		for(SpVoucherActiveVo v:vo2){
			
			condition.setId(v.getId().toString());
			v.setNumber(this.mgMapper.findCustomerNumber(condition));
		}
		return vo2;
	}

	
	@Override
	public List<SpVoucherActiveVo> findmorelist(PromotionRo condition) {
		List<SpVoucherActiveVo> all=this.mgMapper.findmorelist(condition);
		
		
		return all;
	}


	@Override
	public List<ScmsItemBaseVo> findproduct(PromotionRo condition) {
		return this.mgMapper.findproduct(condition);
	}


	@Override
	public int findCountProduct(PromotionRo condition) {
		return this.mgMapper.findCountProduct(condition);
	}


	@Override
	public void addPromotion(PromotionRo condition,SpVoucherActive active) {

		
		for(String id:condition.getIds()){
			condition.setId(id);
			int  count=this.mgMapper.findCustomerNumber(condition);
			SpVoucherActiveMiddle vocher=new SpVoucherActiveMiddle();
			vocher.setNumber(count);
			vocher.setScmsItemId(condition.getScmsItemId());
			vocher.setStoreGroupID(id);
			vocher.setSupplierId(condition.getSupplierId());
			vocher.setSpVoucherActiveId(condition.getSpVoucherActiveId());
			this.spVoucherActiveMiddleMapper.insertSelective(vocher);
		}
		
		this.insertSpVoucherActiveGift(condition,active);
	}
	
	//赠品表插数据
	private void insertSpVoucherActiveGift(PromotionRo condition,SpVoucherActive active){
		//
		
		if(active!=null&&active.getSendGoods()!=null&&!active.getSendGoods().trim().equals("")){
			SpVoucherActiveGift gif=new SpVoucherActiveGift();
			gif.setCount(condition.getCountInt());
			gif.setNumber(condition.getNumberInt());
			gif.setItemBaseId(Integer.parseInt(condition.getScmsItemId()));
			gif.setSpVoucherActiveId(condition.getSpVoucherActiveId());
			gif.setType(2);
			gif.setItemBaseName(condition.getItemBaseName());
			gif.setSupplierId(condition.getSupplierId());
			this.spVoucherActiveGiftMapper.insertSelective(gif);
			
		}else{
			if(condition.getScmsItemId()!=null){
				synchronized (mgMapper) {
					int isAlreadyExists=this.mgMapper.findAlreadyExists(condition);
					if(isAlreadyExists==1){
						return ;
					}
					int result=this.mgMapper.updateGoodsStock(condition);
					if (result==1){
						SpVoucherActiveGift gif=new SpVoucherActiveGift();
						gif.setCount(condition.getCountInt());
						gif.setNumber(condition.getNumberInt());
						gif.setType(2);
						gif.setSupplierId(condition.getSupplierId());
						gif.setPlantItemId(condition.getScmsItemId());
						gif.setItemBaseName(condition.getItemBaseName());
						gif.setSpVoucherActiveId(condition.getSpVoucherActiveId());
						this.spVoucherActiveGiftMapper.insertSelective(gif);
					}
				}
				
			}
		}
		
		
	}


	@Override
	public void updateActive_supplier(PromotionRo condition) {
		this.mgMapper.updateActive_supplier(condition);
		if(condition.getIds()==null||condition.getIds().length==0){
		return ;
	}
		this.addPromotion(condition,null);
	}


	@Override
	public SpVoucherActive findActiveById(PromotionRo condition) {
		return this.spVoucherActiveMapper.selectByPrimaryKey(Integer.parseInt(condition.getId()));
	}


	@Override
	public SpVoucherTempVo getSpVoucherTemp(SpVoucherTemp spVoucherTemp) {
		SpVoucherTempVo spVoucherTempVo = mgMapper.getSpVoucherTemp(spVoucherTemp);
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


	@Override
	public SpVoucherTemp findTempById(Integer sendId) {
		return this.spVoucherTempMapper.selectByPrimaryKey(sendId);
	}


	@Override
	public Double findMoneyByCondition(PromotionRo condition) {
		return this.mgMapper.findMoneyByCondition(condition);
	}


	@Override
	public Double findMoneyJByCondition(PromotionRo condition) {
		return this.mgMapper.findMoneyJByCondition(condition);
	}


	@Override
	public int findCountPeople(PromotionRo condition) {
		return this.mgMapper.findCountPeople(condition);
	}


	@Override
	public int findCountJPeople(PromotionRo condition) {
		return this.mgMapper.findCountJPeople(condition);
	}


	@Override
	public Pager<SpvoucherVo> findByCondition(PromotionRo condition) {
		List<SpvoucherVo> list=this.mgMapper.getPageList(condition);
		for(SpvoucherVo v:list){
			condition.setId(v.getOrderId());
			v.setOrderId(this.mgMapper.findOrderId(condition));
		}
		int size =this.mgMapper.getPageListSize(condition);
		return new Pager<SpvoucherVo>(size, list);
	}


	@Override
	public List<PlantItem> findPlantItem(PromotionRo condition) {
		return this.mgMapper.findPlantItem(condition);
	}


	@Override
	public ScmsItemBaseVo findVoById(String id) {
		return this.mgMapper.findVoById(id);
	}


	@Override
	public Pager<SpvoucherVo> findByConditionJ(PromotionRo condition) {
		List<SpvoucherVo> list=this.mgMapper.getPageList2(condition);
		int size =this.mgMapper.getPageListSize2(condition);
		return new Pager<SpvoucherVo>(size, list);
	}


	@Override
	public List<SpVoucherActive> findActiveByGroup(StoreGroup grop) {
		// TODO Auto-generated method stub
		return this.mgMapper.findActiveByGroup(grop);
	}


	@Override
	public SpVoucherActiveGift findGift(PromotionRo condition) {
		// TODO Auto-generated method stub
		return this.mgMapper.findGift(condition);
	}


	@Override
	public int findmorelistSize(PromotionRo condition) {
		// TODO Auto-generated method stub
		return this.mgMapper.findmorelistSize(condition);
	}


	@Override
	public SpVoucherActiveGift findApVoucherActiveGift(PromotionRo condition) {
		// TODO Auto-generated method stub
		return this.mgMapper.findApVoucherActiveGift(condition);
	}

	@Override
	public SendTimeConfig getSendTimeConfigById(Byte id) {
		return sendTimeConfigMapper.selectByPrimaryKey(id);
	}

}
