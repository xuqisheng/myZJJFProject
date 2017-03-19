package com.corner.kefu.task;

import com.corner.core.beans.SpVoucher;
import com.corner.core.beans.SpVoucherActive;
import com.corner.core.beans.SpVoucherActiveStoreLog;
import com.corner.core.beans.SpVoucherTemp;
import com.corner.core.dao.SpVoucherActiveStoreLogMapper;
import com.corner.core.dao.SpVoucherMapper;
import com.corner.core.dao.SpVoucherTempMapper;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.vo.sp.SpVoucherGradedVo;
import com.corner.kefu.beans.vo.sp.StoreMgVo;
import com.corner.kefu.dao.sp.SpVoucherActiveStoreLogMgMapper;
import com.corner.kefu.dao.sp.SpVoucherMgMapper;
import com.corner.kefu.dao.sp.SpVoucherTempMgMapper;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName: SaveAccSpVoucherTask
 * @Description:保存累积送券活动需要发送的优惠劵
 * @author: 杨开泰
 * @date: 2016年 10月25 18:34
 */
public class SaveAccSpVoucherTask implements Runnable {
    private SpVoucherMgMapper spVoucherMgMapper;
    private SpVoucherMapper spVoucherMapper;
    private SpVoucherTempMgMapper spVoucherTempMgMapper;
    private SpVoucherTempMapper spVoucherTempMapper;
    private SpVoucherActiveStoreLogMgMapper spVoucherActiveStoreLogMgMapper;
    private SpVoucherActiveStoreLogMapper spVoucherActiveStoreLogMapper;
    private SpVoucherActive spVoucherActive;
    private List<StoreMgVo> list;
    private List<SpVoucherGradedVo> gradedVoList;

    public SaveAccSpVoucherTask(SpVoucherMgMapper spVoucherMgMapper, SpVoucherMapper spVoucherMapper, SpVoucherTempMgMapper spVoucherTempMgMapper, SpVoucherTempMapper spVoucherTempMapper, SpVoucherActiveStoreLogMgMapper spVoucherActiveStoreLogMgMapper, SpVoucherActiveStoreLogMapper spVoucherActiveStoreLogMapper, SpVoucherActive spVoucherActive, List<StoreMgVo> list, List<SpVoucherGradedVo> gradedVoList) {
        this.spVoucherMgMapper = spVoucherMgMapper;
        this.spVoucherMapper = spVoucherMapper;
        this.spVoucherTempMgMapper = spVoucherTempMgMapper;
        this.spVoucherTempMapper = spVoucherTempMapper;
        this.spVoucherActiveStoreLogMgMapper = spVoucherActiveStoreLogMgMapper;
        this.spVoucherActiveStoreLogMapper = spVoucherActiveStoreLogMapper;
        this.spVoucherActive = spVoucherActive;
        this.list = list;
        this.gradedVoList = gradedVoList;
    }

    @Override
    public void run() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("active", spVoucherActive);
        String rulePayStr = spVoucherActive.getRulePayStr();
        rulePayStr = StringUtils.substring(rulePayStr, 1, rulePayStr.lastIndexOf(","));
        String[] rulePayArr = rulePayStr.split(",");
        paramMap.put("rulePayArr", rulePayArr);
        for (StoreMgVo storeMgVo :
                list) {
            BigDecimal totalOrderPrice = storeMgVo.getTotalOrderPrice();//modify by 杨开泰 这里的价格还没有判断有没有退货单    2016/10/26 0026 14:30
            paramMap.put("store", storeMgVo);
            BigDecimal totalReturn = spVoucherMgMapper.getTotalReturn(paramMap);
            if (totalReturn != null) {
                totalOrderPrice = totalOrderPrice.subtract(totalReturn);
                storeMgVo.setTotalOrderPrice(totalOrderPrice);
            }

            Map<BigDecimal, List<SpVoucherTemp>> gradeMap = new HashMap<>();
            for (int i = gradedVoList.size() - 1; i >= 0; i--) {//gradeVoList 按价格从小到大排序;转换成map 后 从大到小   杨开泰 2016/10/27 0027 13:39
                SpVoucherGradedVo gradedVo = gradedVoList.get(i);
                if (gradeMap.containsKey(gradedVo.getStartPrice())) {
                    gradeMap.get(gradedVo.getStartPrice()).add(gradedVo.getSpVoucherTemp());
                } else {
                    List<SpVoucherTemp> list = new ArrayList<>();
                    list.add(gradedVo.getSpVoucherTemp());
                    gradeMap.put(gradedVo.getStartPrice(), list);
                }
            }

            for (BigDecimal key :
                    gradeMap.keySet()) {
                if (totalOrderPrice.compareTo(key) >= 0) {
                    List<SpVoucherTemp> tempList = gradeMap.get(key);
                    for (SpVoucherTemp spVoucherTemp :
                            tempList) {
                        Map<String, Object> map = createSpVoucherAndLog(spVoucherActive, spVoucherTemp, storeMgVo);
                        SpVoucher spVoucher = (SpVoucher) map.get("voucher");
                        SpVoucherActiveStoreLog spVoucherActiveStoreLog = (SpVoucherActiveStoreLog) map.get("spVoucherActiveStoreLog");
                        spVoucherMapper.insertSelective(spVoucher);
                        spVoucherActiveStoreLogMapper.insertSelective(spVoucherActiveStoreLog);
                    }
                }
                break;
            }

            /*for (int i = 0; i < gradedVoList.size(); i++) {
                if (totalOrderPrice.compareTo(gradedVoList.get(i).getStartPrice()) >= 0) {
                    SpVoucherTemp spVoucherTemp = gradedVoList.get(i).getSpVoucherTemp();
                    Map<String, Object> map = createSpVoucherAndLog(spVoucherActive, spVoucherTemp, storeMgVo);
                    SpVoucher spVoucher = (SpVoucher) map.get("voucher");
                    SpVoucherActiveStoreLog spVoucherActiveStoreLog = (SpVoucherActiveStoreLog) map.get("spVoucherActiveStoreLog");
                    spVoucherMapper.insertSelective(spVoucher);
                    spVoucherActiveStoreLogMapper.insertSelective(spVoucherActiveStoreLog);
                }
            }*/
        }
    }

    private Map<String, Object> createSpVoucherAndLog(SpVoucherActive spVoucherActive, SpVoucherTemp spVoucherTemp, StoreMgVo storeMgVo) {
        Map<String, Object> resultMap = new HashMap<>();

        Calendar startTime = new GregorianCalendar();
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);

        SpVoucher spVoucher = new SpVoucher();
        spVoucher.setStoreId(storeMgVo.getId());
        spVoucher.setStoreNm(storeMgVo.getName());
        spVoucher.setRuleId(spVoucherTemp.getId());
        spVoucher.setName(spVoucherTemp.getRuleName());
        spVoucher.setRemark(spVoucherTemp.getRuleRemark());
        spVoucher.setMoney(spVoucherTemp.getUseMoney().intValue());
        spVoucher.setBillType(spVoucherTemp.getBillType());
        spVoucher.setStartTime(startTime.getTime());
        spVoucher.setCreateTime(new Date());
        startTime.add(Calendar.DATE, spVoucherTemp.getUseDay() - 1);
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

        SpVoucherActiveStoreLog spVoucherActiveStoreLog = new SpVoucherActiveStoreLog();
        spVoucherActiveStoreLog.setId(StringUtil.getUUID());
        spVoucherActiveStoreLog.setActiveId(spVoucherActive.getId());
        spVoucherActiveStoreLog.setStatus(Byte.valueOf("1"));
        spVoucherActiveStoreLog.setStoreId(storeMgVo.getId());
        spVoucherActiveStoreLog.setTotalOrDerPrice(storeMgVo.getTotalOrderPrice());
        spVoucherActiveStoreLog.setVoucherTempId(spVoucherTemp.getId());
        spVoucherActiveStoreLog.setUseMoney(spVoucherTemp.getUseMoney());

        resultMap.put("voucher", spVoucher);
        resultMap.put("spVoucherActiveStoreLog", spVoucherActiveStoreLog);
        return resultMap;
    }
}
