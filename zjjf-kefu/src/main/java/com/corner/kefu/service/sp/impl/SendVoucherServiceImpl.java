package com.corner.kefu.service.sp.impl;

import com.corner.core.beans.SpVoucherActive;
import com.corner.core.dao.SpVoucherActiveMapper;
import com.corner.core.dao.SpVoucherActiveStoreLogMapper;
import com.corner.core.dao.SpVoucherMapper;
import com.corner.core.dao.SpVoucherTempMapper;
import com.corner.kefu.beans.vo.sp.SpVoucherGradedVo;
import com.corner.kefu.beans.vo.sp.StoreMgVo;
import com.corner.kefu.dao.sp.*;
import com.corner.kefu.enums.spVoucher.VoucherActiveType;
import com.corner.kefu.service.sp.SendVoucherService;
import com.corner.kefu.task.SaveAccSpVoucherTask;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SendVoucherServiceImpl
 * @Description:
 * @author: 杨开泰
 * @date: 2016年 10月25 17:36
 */
@Service
public class SendVoucherServiceImpl implements SendVoucherService {

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    SpStoreMgMapper spStoreMgMapper;
    @Autowired
    SpVoucherActiveMgMapper spVoucherActiveMgMapper;
    @Autowired
    SpVoucherActiveMapper spVoucherActiveMapper;
    @Autowired
    SpVoucherMgMapper spVoucherMgMapper;
    @Autowired
    SpVoucherTempMgMapper spVoucherTempMgMapper;
    @Autowired
    SpVoucherMapper spVoucherMapper;
    @Autowired
    SpVoucherTempMapper spVoucherTempMapper;
    @Autowired
    SpVoucherActiveStoreLogMgMapper spVoucherActiveStoreLogMgMapper;
    @Autowired
    SpVoucherActiveStoreLogMapper spVoucherActiveStoreLogMapper;


    @Override
    public Map<String, Object> sendVoucher(int id) {
        SpVoucherActive spVoucherActive = spVoucherActiveMapper.selectByPrimaryKey(id);
        if(spVoucherActive.getRuleType().intValue()== VoucherActiveType.ACCUMULATE_SEND_COUPON.getIndex().intValue()){//累积送券活动   杨开泰 2016/10/25 0025 18:46
            List<SpVoucherGradedVo> gradeds = spVoucherActiveMgMapper.getSpVoucherGradeListByActiveId(spVoucherActive.getId());
            gradeds.sort((a1,a2)->a1.getStartPrice().compareTo(a2.getStartPrice()));//可以不用,sql已经排序   杨开泰 2016/10/25 0025 20:35
            Map<String,Object> map = new HashMap<>();
            map.put("startPrice",gradeds.get(0).getStartPrice());
            map.put("active",spVoucherActive);
            String rulePayStr = spVoucherActive.getRulePayStr();
            rulePayStr = StringUtils.substring(rulePayStr,1,rulePayStr.lastIndexOf(","));
            String[] rulePayArr = rulePayStr.split(",");
            map.put("rulePayArr",rulePayArr);
            if(spVoucherActive.getRuleScopFlag().intValue()==0){//全部定格
                List<StoreMgVo> storeMgVoList = spStoreMgMapper.getAccAllStore(map);
                if(storeMgVoList!=null&&storeMgVoList.size()!=0){//
                   taskExecutor.execute(new SaveAccSpVoucherTask(spVoucherMgMapper,spVoucherMapper,spVoucherTempMgMapper,spVoucherTempMapper,spVoucherActiveStoreLogMgMapper,spVoucherActiveStoreLogMapper,spVoucherActive,storeMgVoList,gradeds));
                }
            }else if(spVoucherActive.getRuleScopFlag().intValue()==1){//指定定格
                  String spGroupIds = spVoucherActive.getRuleScop();
                  spGroupIds = StringUtils.substring(spGroupIds,1,spGroupIds.lastIndexOf(","));
                  String[] spGroupIdArr = spGroupIds.split(",");
                  map.put("spGroupIdArr",spGroupIdArr);
                  List<StoreMgVo> storeMgVoList = spStoreMgMapper.getAccAllStore(map);
                  if(storeMgVoList!=null&&storeMgVoList.size()!=0){//
                    taskExecutor.execute(new SaveAccSpVoucherTask(spVoucherMgMapper,spVoucherMapper,spVoucherTempMgMapper,spVoucherTempMapper,spVoucherActiveStoreLogMgMapper,spVoucherActiveStoreLogMapper,spVoucherActive,storeMgVoList,gradeds));
                }
            }else if(spVoucherActive.getRuleScopFlag().intValue()==3){//指定用户
                  String storeIds = spVoucherActive.getStoreIds();
                  storeIds = StringUtils.substring(storeIds,1,storeIds.lastIndexOf(","));
                  String[] storeIdArr = storeIds.split(",");
                  map.put("storeIdArr",storeIdArr);
                  List<StoreMgVo> storeMgVoList = spStoreMgMapper.getAccAllStore(map);
                  if(storeMgVoList!=null&&storeMgVoList.size()!=0){//
                    taskExecutor.execute(new SaveAccSpVoucherTask(spVoucherMgMapper,spVoucherMapper,spVoucherTempMgMapper,spVoucherTempMapper,spVoucherActiveStoreLogMgMapper,spVoucherActiveStoreLogMapper,spVoucherActive,storeMgVoList,gradeds));
                }
            }
        }
        return null;
    }
}
