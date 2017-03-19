package com.corner.task.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.corner.task.beans.*;
import com.corner.task.dao.*;
import com.corner.task.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.service.KaiDianBaoService;
import com.corner.task.util.DateUtil;

@Service
public class KaiDianBaoServiceImpl implements KaiDianBaoService {

	private static Logger logger = LoggerFactory.getLogger(KaiDianBaoServiceImpl.class);

	@Autowired
	SystemInfoMapper systemInfoMapper;
	@Autowired
	KaiDianBaoTaskMgMapper kaiDianBaoTaskMgMapper;
	@Autowired
	PlantItemLogMapper plantItemLogMapper;
	@Autowired
	PlantItemPreMapper plantItemPreMapper;
	@Autowired
	PlantItemMapper plantItemMapper;
	@Autowired
	PlantItemProductMapper plantItemProductMapper;
	@Autowired
	PlantItemProductMapMapper plantItemProductMapMapper;
	@Autowired
	ItemBaseMapper itemBaseMapper;



	@Override
	public ModelMsg updateKDBItemBaseScales() {

		return null;
	}

	/**
	 * 定时更新店宝价格
	 */
	@Override
	public ModelMsg updateKDBPlantItemPrice() {
		logger.info("====================更新店宝商品价格定时任务开始====================");
		try {
			SystemInfo systemInfo = systemInfoMapper.selectByPrimaryKey("KDB_Price_Task");
			if(systemInfo!=null && !StringUtils.isEmpty(systemInfo.getContent())){
				Date taskTime=DateUtil.StringToDateSimple(systemInfo.getContent());
				if(taskTime ==null){
//					logger.info("《更新店宝价格任务》定时任务执行失败，日期格式错误：{}",systemInfo.getContent());
//					return;
					return new ModelMsg(false, "《更新店宝价格任务》定时任务执行失败，日期格式错误：" + systemInfo.getContent());
				}
				Calendar taskCal = new GregorianCalendar();
				taskCal.setTime(taskTime);
				Calendar calendarNow = Calendar.getInstance();
				Long interval = calendarNow.getTimeInMillis()-taskCal.getTimeInMillis();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				logger.info("当前时间:  "+sdf.format(taskCal.getTime()));
				logger.info("录入系统设置时间:  "+sdf.format(taskTime));

				interval = 18L;
				if(interval>0 && interval<1800000){//时间节点在半小时以内
					List<PlantItemPre> list = kaiDianBaoTaskMgMapper.getAllPlantItemPre();
					logger.info("▇▇▇▇▇▇进入if()方法，时间差在半小时之内▇▇▇▇▇▇▇▇▇");
					if(list!=null&&list.size()!=0){
						logger.info("▇▇▇▇▇▇进入for()循环▇▇▇▇▇▇▇▇▇");
						for (PlantItemPre plantItemPre : list) {
							PlantItem plantItem = plantItemMapper.selectByPrimaryKey(plantItemPre.getId());
							if(plantItem==null){
								continue;
							}
							//库存
							String logicStockid = kaiDianBaoTaskMgMapper.checkItemIsHave(plantItemPre);
							if(logicStockid.contains("error")){
								logger.info("获取逻辑库存id出错:"+logicStockid);
								continue;
							}
							logger.info("▇▇▇▇▇▇逻辑库存id："+logicStockid);
							
							plantItemPre.setLogicStockId(logicStockid);
							plantItemMapper.updateByPrimaryKeySelective(BeanUtil.toObject(PlantItem.class , plantItemPre));

							plantItemPreMapper.deleteByPrimaryKey(plantItemPre.getId());

							//插入log表
							plantItemPre.setAddTime(new Date());
							plantItemPre.setUpdateTime(new Date());
							plantItemLogMapper.insertSelective(BeanUtil.toObject(PlantItemLog.class , plantItemPre));
							if(plantItemPre.getStatus() == 0){
								PlantItemProductMapExample example = new PlantItemProductMapExample();
								example.createCriteria().andPlantItemIdEqualTo(plantItem.getId());
								List<String> productIds = new ArrayList<>();
								plantItemProductMapMapper.selectByExample(example).forEach(map -> productIds.add(map.getProductId()));
								if(productIds != null && productIds.size() != 0){
									PlantItemProductExample example2 = new PlantItemProductExample();
									example2.createCriteria().andIdIn(productIds);
									PlantItemProduct product = new PlantItemProduct();
									product.setStatus(Byte.valueOf("0"));
									plantItemProductMapper.updateByExampleSelective(product , example2);
								}
							}else{
								PlantItemProductMapExample example = new PlantItemProductMapExample();
								example.createCriteria().andPlantItemIdEqualTo(plantItem.getId());
								example.createCriteria().andProductIdEqualTo(plantItem.getId());
								List<PlantItemProductMap> maps = plantItemProductMapMapper.selectByExample(example);

								PlantItemProduct product = new PlantItemProduct();
								product.setId(plantItemPre.getId());
								product.setAsDefault(true);
								product.setProductPrice(plantItemPre.getAreaPrice());
								product.setProMarketPrice(plantItemPre.getPlantMemPrice());

								product.setBuyStartTime(sdf.parse("2000-01-01 00:00:00"));
								product.setBuyEndTime(sdf.parse("9999-12-30 00:00:00"));
								product.setBuyLimitNum(plantItemPre.getRestrict());
								ItemBase itemBase = itemBaseMapper.selectByPrimaryKey(plantItemPre.getItemBaseId());
								product.setPkgName(itemBase.getName());
								product.setTotalLimitNum(plantItemPre.getSKUProTotalLimitNum());
								product.setRemark(plantItemPre.getRemark());
								product.setYouHui(plantItemPre.getYouHui());
								product.setUpTime(product.getBuyStartTime());
								product.setDownTime(product.getBuyEndTime());

								PlantItemProductMap map = new PlantItemProductMap();
								map.setProductId(product.getId());
								map.setPlantItemId(plantItemPre.getId());
								map.setPkgPrice(product.getProductPrice());

								if(maps == null || maps.size() == 0){
									product.setAddtime(new Date());
									plantItemProductMapper.insertSelective(product);
									plantItemProductMapMapper.insertSelective(map);
								}else{
									plantItemProductMapper.updateByPrimaryKeySelective(product);
									plantItemProductMapMapper.updateByPrimaryKeySelective(map);
								}
							}
						}
						logger.info("《更新店宝价格任务》结束");
						return new ModelMsg(true, "《更新店宝价格任务》定时任务执行结束");
					}
					//清空PlantItemPre表
					//kaiDianBaoTaskMgMapper.removeAllPlantItemPre();
					//logger.info("▇▇▇▇▇▇清空PlantItemPre表结束▇▇▇▇▇▇▇▇▇");
					
				   //更新价格
//				   if(list!=null&&list.size()!=0){
//					   kaiDianBaoTaskMgMapper.insertOrUpdatePlantItem(list);
//						//将数据插入PlantItemLog表中
//					   kaiDianBaoTaskMgMapper.batchAddPlantItemLog(list);
//					}
				}
				logger.info("《更新店宝价格任务》结束");
				return new ModelMsg(true, "未到任务设定时间，当前时间："+sdf.format(taskCal.getTime()) +" , 录入系统设置时间"+sdf.format(taskTime));
			}
			/*if (systemInfo != null && !StringUtils.isEmpty(systemInfo.getContent())) {
				Date taskTime = DateUtil.StringToDateSimple(systemInfo.getContent());
				if (taskTime == null) {
					return new ModelMsg(false, "《更新店宝价格任务》定时任务执行失败，日期格式错误：" + systemInfo.getContent());
				}
				Calendar taskCal = new GregorianCalendar();
				taskCal.setTime(taskTime);
				Calendar calendarNow = Calendar.getInstance();
				Long interval = calendarNow.getTimeInMillis() - taskCal.getTimeInMillis();
				if (interval > 0 && interval < 1800000) {// 时间节点在帮小时以内
					kaiDianBaoTaskMgMapper.updateKDBPlantItemPrice("");
					logger.info("更新店宝商品价格定时任务执行成功！消息:{}", "《更新店宝价格任务》定时任务执行成功");
					return new ModelMsg(true, "《更新店宝价格任务》定时任务执行成功");
				} else {
					logger.info("更新店宝商品价格定时任务执行失败！消息:{}", "《更新店宝价格任务》定时任务执行失败，不在执行时间内");
					return new ModelMsg(false, "《更新店宝价格任务》定时任务执行失败，不在执行时间内");
				}
			}*/
			return new ModelMsg(false, "《更新店宝价格任务》定时任务执行失败，未取到执行时间");
		} catch (Exception e) {
			logger.error("更新店宝商品价格定时任务异常：",e);
			return new ModelMsg(false, "店宝改价格定时任务异常");
		}
	}

}
