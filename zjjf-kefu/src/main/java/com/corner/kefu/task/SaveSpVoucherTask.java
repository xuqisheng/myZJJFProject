/**   
* @Title: SaveSpVoucherTask.java 
* @Package com.corner.pc.util 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月9日 下午3:58:32 
* @version V1.0   
*/

package com.corner.kefu.task;

import com.corner.core.beans.SpVoucher;
import com.corner.core.beans.SpVoucherTemp;
import com.corner.core.dao.SpVoucherMapper;
import com.corner.core.utils.msg.PhoneMsgService;
import com.corner.kefu.beans.vo.sp.StoreVo;
import com.corner.kefu.utils.SpVoucherConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @ClassName: SaveSpVoucherTask
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月9日 下午3:58:32
 * 
 */

public class SaveSpVoucherTask implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(SaveSpVoucherTask.class);
	private List<StoreVo> list;
	private SpVoucherTemp spVoucherTemp;
	private SpVoucherMapper spVoucherMapper;
	@Autowired
	PhoneMsgService phonemsgService;
	@Autowired
	SpVoucherConstants spVoucherConstants;


	public SaveSpVoucherTask(List<StoreVo> list, SpVoucherTemp spVoucherTemp, SpVoucherMapper spVoucherMapper) {
		super();
		this.list = list;
		this.spVoucherTemp = spVoucherTemp;
		this.spVoucherMapper = spVoucherMapper;
	}

	@Override
	public void run() {
		AtomicInteger atomicInteger = SpVoucherConstants.ConstantsMap.get("spVoucher"+spVoucherTemp.getId());
		atomicInteger.getAndIncrement();
		try {
			logger.info("手工发送优惠劵开始====================");
			for (StoreVo storeVo : list) {
				Calendar startTime = new GregorianCalendar();
			    startTime.set(Calendar.HOUR_OF_DAY,0);
			    startTime.set(Calendar.MINUTE,0);
			    startTime.set(Calendar.SECOND,0);
				SpVoucher spVoucher = new SpVoucher();
				spVoucher.setStoreId(Integer.parseInt(storeVo.getId()));
				spVoucher.setStoreNm(storeVo.getName());
				spVoucher.setRuleId(spVoucherTemp.getId());
				spVoucher.setName(spVoucherTemp.getRuleName());
				spVoucher.setRemark(spVoucherTemp.getRuleRemark());
				spVoucher.setMoney(spVoucherTemp.getUseMoney().intValue());
				spVoucher.setBillType(spVoucherTemp.getBillType());
				spVoucher.setStartTime(startTime.getTime());
				spVoucher.setCreateTime(new Date());
				startTime.add(Calendar.DATE, spVoucherTemp.getUseDay()-1);
				startTime.set(Calendar.HOUR_OF_DAY, 23);
				startTime.set(Calendar.MINUTE, 58);
				startTime.set(Calendar.SECOND, 0);
				spVoucher.setExpiredTime(startTime.getTime());
				spVoucher.setStartPrice(spVoucherTemp.getStartPrice());
				spVoucher.setPayType(spVoucherTemp.getPayType());
				spVoucher.setPayStr(spVoucherTemp.getPayStr());
				spVoucher.setGoodsType(spVoucherTemp.getUseItemFlag());
				spVoucher.setGoodsStr(spVoucherTemp.getUseItemIds());
				spVoucher.setPubStatus(new Byte("1"));
				spVoucher.setTransportTimeType(spVoucherTemp.getTransportTimeType());
				Map<String, Object> map = new HashMap<>();
				map.put("storeId", storeVo.getId());
				map.put("id", spVoucherTemp.getId());
				logger.info("店铺id:"+storeVo.getId()+"  "+"店铺name:"+storeVo.getName()+"  "+"优惠劵id:"+spVoucherTemp.getId());
				spVoucherMapper.insertSelective(spVoucher);
			}
		} catch (Exception e) {
			logger.error("{}",e);
		}finally {
			atomicInteger.getAndDecrement();
		}
	}

}
