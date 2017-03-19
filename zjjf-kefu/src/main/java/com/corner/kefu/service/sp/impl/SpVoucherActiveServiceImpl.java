/**
 * @Title: SpVoucherActiveServiceImpl.java
 * @Package com.corner.kefu.service.sp.impl
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年3月29日 上午9:57:15
 * @version V1.0
 */

package com.corner.kefu.service.sp.impl;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.SpVoucherActiveGiftMapper;
import com.corner.core.dao.SpVoucherActiveMapper;
import com.corner.core.dao.SpVoucherTempMapper;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.sp.SpVoucherActiveMgRo;
import com.corner.kefu.beans.ro.sp.SpVoucherActiveRo;
import com.corner.kefu.beans.vo.sp.SpVoucherActiveVo;
import com.corner.kefu.beans.vo.sp.StoreMgVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;
import com.corner.kefu.dao.SpOrderInfoMgMapper;
import com.corner.kefu.dao.SpVoucherGradedMgMapper;
import com.corner.kefu.dao.sp.*;
import com.corner.kefu.enums.spVoucher.VoucherActiveType;
import com.corner.kefu.service.sp.SpVoucherActiveService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author 杨开泰  yangkaitai@izjjf.cn
 * @ClassName: SpVoucherActiveServiceImpl
 * @Description:
 * @date 2016年3月29日 上午9:57:15
 */
@Service
public class SpVoucherActiveServiceImpl implements SpVoucherActiveService {

    @Autowired
    SpVoucherActiveMapper spVoucherActiveMapper;

    @Autowired
    SpVoucherActiveMgMapper spVoucherActiveMgMapper;

    @Autowired
    SpVoucherTempMapper spVoucherTempMapper;

    @Autowired
    SpVoucherMgMapper spVoucherMgMapper;

    @Autowired
    SpGroupMgMapper spGroupMgMapper;

    @Autowired
    SpVoucherActiveGiftMapper spVoucherActiveGiftMapper;

    @Autowired
    SpVoucherActiveGiftMgMapper spVoucherActiveGiftMgMapper;

    @Autowired
    SpOrderActiveMapMgMapper spOrderActiveMapMgMapper;

    @Autowired
    SpVoucherGradedMgMapper spVoucherGradedMgMapper;
    @Autowired
    SpStoreMgMapper spStoreMgMapper;
    @Autowired
    SpVoucherActiveStoreLogMgMapper spVoucherActiveStoreLogMgMapper;
    @Autowired
    SpOrderInfoMgMapper spOrderInfoMgMapper;


    /**
     * Title: save
     * Description:保存活动
     */
    @Override
    public void save(Map<String, Object> map) throws Exception {
        SpVoucherActive spVoucherActive = (SpVoucherActive) map.get("spVoucherActive");
        if (spVoucherActive.getId() != null) {
            spVoucherActiveMapper.updateByPrimaryKeySelective(spVoucherActive);
            //删除SpVoucherActiveMap表中的关联数据  (定格)
            spVoucherActiveMgMapper.deleteSpVoucherActiceWithGroupId(spVoucherActive);
            //删除SpVoucherGraded表中相关数据(优惠劵层级)
            spVoucherGradedMgMapper.deleteSpVoucherGradeByActiveId(spVoucherActive);
            //删除赠品表中的数据
            spVoucherActiveGiftMgMapper.deleGiftByActiveId(spVoucherActive);
        } else {
            spVoucherActiveMgMapper.insertSelectiveAndGetIdBack(spVoucherActive);
        }
        if (spVoucherActive.getRuleScopFlag() == 1 || spVoucherActive.getRuleScopFlag() == 2) {
            String groupIds = spVoucherActive.getRuleScop();
            if (StringUtil.stringIsNullOrEmpty(groupIds)) {
                throw new Exception("活动没有关联定格！");
            }
            String[] groupIdArr = groupIds.substring(1, groupIds.lastIndexOf(",")).split(",");
            if (groupIdArr == null || groupIdArr.length == 0) {
                throw new Exception("活动没有关联定格！");
            }
            List<String> groupIdList = Arrays.asList(groupIdArr);
            map.put("groupIdList", groupIdList);
            spVoucherActiveMgMapper.saveSpVoucherActiceWithGroupId(map);
        }

        //处理商品
        Byte ruleType = spVoucherActive.getRuleType();
        if (ruleType == 12) {//满购商品送商品
            String buyGoods = spVoucherActive.getBuyGoods();
            if (StringUtil.stringIsNullOrEmpty(buyGoods)) {
                throw new Exception("没有购满商品！");
            }
            SpVoucherActiveGift spVoucherActiveGift = new SpVoucherActiveGift();
            spVoucherActiveGift.setType(1);//满购商品
            spVoucherActiveGift.setSpVoucherActiveId(spVoucherActive.getId());
            String[] buyGoodsArr = buyGoods.split(":");
            spVoucherActiveGift.setItemBaseId(Integer.parseInt(buyGoodsArr[0]));
            spVoucherActiveGift.setBuyCount(Integer.parseInt(buyGoodsArr[2]));
            spVoucherActiveGift.setItemBaseName(buyGoodsArr[1]);
            spVoucherActiveGiftMapper.insertSelective(spVoucherActiveGift);
        }
        if (ruleType == 9 || ruleType == 10 || ruleType == 11 || ruleType == 12) {
            List<SpVoucherActiveGift> list = new ArrayList<SpVoucherActiveGift>();
            String sendGoods = spVoucherActive.getSendGoods();
            if (!StringUtil.stringIsNullOrEmpty(sendGoods)) {
                String[] sendGoodsArr = sendGoods.split("&");
                for (int i = 0; i < sendGoodsArr.length; i++) {
                    String item = sendGoodsArr[i];
                    SpVoucherActiveGift spVoucherActiveGift = new SpVoucherActiveGift();
                    String[] itemArr = item.split(":");
                    spVoucherActiveGift.setType(2);//送商品
                    spVoucherActiveGift.setNumber(Integer.parseInt(itemArr[2]));//每次赠送的数量
                    spVoucherActiveGift.setCount(Integer.parseInt(itemArr[3]));//赠送总量
                    spVoucherActiveGift.setItemBaseId(Integer.parseInt(itemArr[0]));//itemBaseId
                    spVoucherActiveGift.setSpVoucherActiveId(spVoucherActive.getId());
                    spVoucherActiveGift.setItemBaseName(itemArr[1]);
                    list.add(spVoucherActiveGift);
                }
            }
            if (list.size() != 0) {
                spVoucherActiveGiftMgMapper.batchSave(list);
            }
        }

        //处理优惠劵层级关系
        //modify by 杨开泰 暗号项目  新增累积送劵活动  2016/10/20 0020 10:05
        if (ruleType == 2 || ruleType == 9 || ruleType.intValue() == VoucherActiveType.ACCUMULATE_SEND_COUPON.getIndex().intValue()) {
            String[] voucherTempIdArr = (String[]) map.get("voucherTempIdArr");
            String[] startPriceArr = (String[]) map.get("startPriceArr");
            String[] sendLimitArr = null;
            if (ruleType == 2 || ruleType == 9) {
                sendLimitArr = (String[]) map.get("sendLimitArr");
            }
            List<SpVoucherGraded> list = new ArrayList<SpVoucherGraded>();
            for (int i = 0; i < voucherTempIdArr.length; i++) {
                SpVoucherGraded spVoucherGraded = new SpVoucherGraded();
                spVoucherGraded.setActiveId(spVoucherActive.getId());
                if (ruleType == 2 || ruleType == 9) {
                    spVoucherGraded.setSendLimit(Byte.valueOf(sendLimitArr[i]));
                } else {
                    spVoucherGraded.setSendLimit(Byte.valueOf("0"));//累积送劵活动不需要设置限制张数 暗号项目  杨开泰 2016/10/20 0020 10:08
                }
                spVoucherGraded.setStartPrice(new BigDecimal(startPriceArr[i]));
                spVoucherGraded.setTempId(Integer.parseInt(voucherTempIdArr[i]));
                list.add(spVoucherGraded);
            }
            if (list.size() != 0) {
                spVoucherGradedMgMapper.batchSave(list);
            }
        }

    }


    /**
     * Title: getSpVoucherActiveList
     * Description:查询活动列表
     *
     * @param spVoucherActiveRo
     * @return
     * @throws Exception
     * @see com.corner.kefu.service.sp.SpVoucherActiveService#getSpVoucherActiveList(com.corner.kefu.beans.ro.sp.SpVoucherActiveRo)
     */
    @Override
    public Pager<SpVoucherActiveVo> getSpVoucherActiveList(SpVoucherActiveRo spVoucherActiveRo) throws Exception {
        List<SpVoucherActiveVo> list = spVoucherActiveMgMapper.getSpVoucherActiveList(spVoucherActiveRo);
        for (SpVoucherActiveVo spVoucherActiveVo : list) {
            if (spVoucherActiveVo.getPlantHalve() != null) {
                BigDecimal plantHalve = new BigDecimal(Double.toString(spVoucherActiveVo.getPlantHalve().doubleValue()));
                BigDecimal spHalve = new BigDecimal("100").subtract(plantHalve);
                spVoucherActiveVo.setPlantHalveStr(plantHalve.toString() + "%");
                spVoucherActiveVo.setSpHalveStr(spHalve.toString() + "%");
            }
        }

        //如果是批发商资源参与的活动,查询参与该活动的批发商数量
        for (SpVoucherActiveVo spVoucherActiveVo2 : list) {
            if (spVoucherActiveVo2.getRuleScopFlag() == 2) {//批发商自愿参与
                List<SpVoucherActiveMiddle> list2 = spVoucherActiveMgMapper.getCountParticipationInActive(spVoucherActiveVo2);
                if (list2 != null) {
                    spVoucherActiveVo2.setTotalSupplier(list2.size());
                }
            }
        }
        Integer totalSize = spVoucherActiveMgMapper.getCountSpVoucherActiveList(spVoucherActiveRo);
        return new Pager<>(totalSize, list);
    }


    /**
     * Title: selectByPrimaryKey
     * Description:根据id查询SpVoucherActive
     *
     * @param id
     * @return
     * @throws Exception
     * @see com.corner.kefu.service.sp.SpVoucherActiveService#selectByPrimaryKey(java.lang.Integer)
     */
    @Override
    public SpVoucherActive selectByPrimaryKey(Integer id) throws Exception {
        return spVoucherActiveMapper.selectByPrimaryKey(id);
    }


    @Override
    public ModelMsg updateByPrimaryKeySelective(SpVoucherActive spVoucherActive) throws Exception {
        ModelMsg modelMsg = new ModelMsg();
        modelMsg.setSuccess(true);
        if (spVoucherActive.getStatus() == 0) {//如果停用活动，要把已经参与这个活动的组从映射表中删除
            spVoucherActiveMgMapper.deleteFromMiddle(spVoucherActive);
            modelMsg.setMessage("停用");
        } else if (spVoucherActive.getStatus() == 2) {//如果启用活动,要判断当前时间是否已经超过活动结束时间
            spVoucherActive = spVoucherActiveMapper.selectByPrimaryKey(spVoucherActive.getId());
            Date now = new Date();
            if (now.after(spVoucherActive.getRuleEnd())) {
                modelMsg.setSuccess(false);
                modelMsg.setMessage("活动时间到期,请重新编辑!");
                return modelMsg;
            }
            if (now.after(spVoucherActive.getRuleStart()) && now.before(spVoucherActive.getRuleEnd())) {
                spVoucherActive.setStatus((byte) 1);//进行中
                modelMsg.setMessage("进行中");
            } else {
                spVoucherActive.setStatus((byte) 2);//未开始
                modelMsg.setMessage("未开始");
            }
        }
        spVoucherActiveMapper.updateByPrimaryKeySelective(spVoucherActive);
        return modelMsg;
    }


    @Override
    public SpVoucherActiveVo getSpVoucherActiveVoById(int id) throws Exception {
        SpVoucherActiveVo spVoucherActiveVo = spVoucherActiveMgMapper.getSpVoucherActiveVoById(id);
        Byte ruleType = spVoucherActiveVo.getRuleType();
        if (ruleType == 1 || ruleType == 2 || ruleType == 9) {
            SpVoucherTemp spVoucherTemp = spVoucherTempMapper.selectByPrimaryKey(spVoucherActiveVo.getSendId());
            if (spVoucherTemp != null) {
                spVoucherActiveVo.setSpVoucherTemp(spVoucherTemp);
            } else {
                spVoucherTemp = new SpVoucherTemp();
                spVoucherActiveVo.setSpVoucherTemp(spVoucherTemp);
            }
        }
        switch (ruleType) {
            case 1:
                spVoucherActiveVo.setRuleTypeStr("注册送优惠劵");
                break;
            case 2:
                spVoucherActiveVo.setRuleTypeStr("满送优惠劵");
                break;
            case 3:
                spVoucherActiveVo.setRuleTypeStr("满减");
                break;
            case 9:
                spVoucherActiveVo.setRuleTypeStr("满送优惠劵+商品");
                break;
            case 10:
                spVoucherActiveVo.setRuleTypeStr("满减+商品");
                break;
            case 11:
                spVoucherActiveVo.setRuleTypeStr("满送商品");
                break;
            case 12:
                spVoucherActiveVo.setRuleTypeStr("满购商品送商品");
                break;
            default:
                spVoucherActiveVo.setRuleTypeStr("默认活动");
                break;
        }
        spVoucherActiveVo.setRuleStartStr(DateUtil.date2StandardString(spVoucherActiveVo.getRuleStart()));
        spVoucherActiveVo.setRuleEndStr(DateUtil.date2StandardString(spVoucherActiveVo.getRuleEnd()));
        return spVoucherActiveVo;
    }


    /**
     * Title: getActiveSupplierList
     * Description:获取批发城参与活动详情
     *
     * @param spVoucherActiveMgRo
     * @return
     * @throws Exception
     * @see com.corner.kefu.service.sp.SpVoucherActiveService#getActiveSupplierList(com.corner.kefu.beans.ro.sp.SpVoucherActiveMgRo)
     */
    @Override
    public Pager<SupplierVo> getActiveSupplierList(SpVoucherActiveMgRo spVoucherActiveMgRo) throws Exception {
        List<SupplierVo> list = spVoucherActiveMgMapper.getActiveSupplierList(spVoucherActiveMgRo);
        Integer count = spVoucherActiveMgMapper.getCountActiveSupplierList(spVoucherActiveMgRo);
        //计算成本,参与客户数（参与客户数不是批发商客户分组下的客户数量）
        SpVoucherActive spVoucherActive = spVoucherActiveMapper.selectByPrimaryKey(spVoucherActiveMgRo.getId());
        Byte ruleType = spVoucherActive.getRuleType();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("spVoucherActiveId", spVoucherActive.getId());
        if (ruleType == 1 || ruleType == 2 || ruleType == 9) {
            if (spVoucherActive.getRuleScopFlag() == 2) {//送优惠劵类型的活动且是批发商自愿参与
                //批发商自愿参与
                for (SupplierVo supplierVo : list) {
                    map.put("supplierId", supplierVo.getId());

                    //计算客户数,成本
                    Map<String, Object> resultMap = spVoucherActiveMgMapper.getTotalConsumerAndFee(map);
                    if (resultMap != null) {
                        Integer totalConsumer = Integer.parseInt(resultMap.get("totalConsumer") + "");
                        if (totalConsumer != 0) {
                            Double cost = Double.parseDouble(resultMap.get("cost") + "");
                            supplierVo.setTotalConsumer(totalConsumer);
                            supplierVo.setCost(cost);
                        } else {
                            supplierVo.setCost(0d);
                            supplierVo.setTotalConsumer(0);
                        }
                    } else {
                        supplierVo.setCost(0d);
                        supplierVo.setTotalConsumer(0);
                    }
                }
            }
        } else if (ruleType == 3 || ruleType == 10) {
            //计算满减
            for (SupplierVo supplierVo : list) {
                map.put("supplierId", supplierVo.getId());
                //计算客户数和成本
                Map<String, Object> resultMap = spOrderActiveMapMgMapper.getSpVoucherActivePlantCost(map);
                if (resultMap != null) {
                    Integer totalConsumer = Integer.parseInt(resultMap.get("totalConsumer") + "");
                    if (totalConsumer != 0) {
                        Double cost = Double.parseDouble(resultMap.get("cost") + "");
                        supplierVo.setTotalConsumer(totalConsumer);
                        supplierVo.setCost(cost);
                    } else {

                    }
                } else {
                    supplierVo.setCost(0d);
                    supplierVo.setTotalConsumer(0);
                }
            }
        } else {
            for (SupplierVo supplierVo : list) {
                supplierVo.setCost(0d);
            }
        }
        return new Pager<SupplierVo>(count, list);
    }


    /**
     * Title: getSpVoucherTempBySendId
     * Description:查询优惠劵
     *
     * @param sendId
     * @return
     * @throws Exception
     * @see com.corner.kefu.service.sp.SpVoucherActiveService#getSpVoucherTempBySendId(java.lang.Integer)
     */
    @Override
    public SpVoucherTemp getSpVoucherTempBySendId(Integer sendId) throws Exception {
        return spVoucherTempMapper.selectByPrimaryKey(sendId);
    }


    /**
     * Title: updateBatchSpVoucherActive
     * Description: 关闭所有已经结束的活动
     *
     * @throws Exception
     * @see com.corner.kefu.service.sp.SpVoucherActiveService#updateBatchSpVoucherActive()
     */
    @Override
    public void updateBatchSpVoucherActive() throws Exception {
        //结束所有到期活动
        List<SpVoucherActive> list = spVoucherActiveMgMapper.getAllFinishActive();
        if (list != null && list.size() != 0) {
            //处理SpVoucherActiveMiddle相关表
            spVoucherActiveMgMapper.updateFinishActive(list);
            spVoucherActiveMgMapper.updateAllFinishActive();
        }
        //开启所有要开启的活动
        spVoucherActiveMgMapper.updateStartAllActive();
    }


    /**
     * Title: getOrders
     * Description:查看订单
     *
     * @param map
     * @return
     * @throws Exception
     * @see com.corner.kefu.service.sp.SpVoucherActiveService#getOrders(java.util.Map)
     */
    @Override
    public Pager<SpOrderInfo> getOrders(Map<String, Object> map) throws Exception {
        List<SpOrderInfo> list = spVoucherActiveMgMapper.getOrders(map);
        Integer count = spVoucherActiveMgMapper.getCountOrders(map);
        return new Pager<SpOrderInfo>(count, list);
    }

    @Override
    public List<SpVoucherActive> getAccumuLateActive(SpVoucherActive spVoucherActive) {
        return spVoucherActiveMgMapper.getAccumuLateActive(spVoucherActive);
    }

    @Override
    public List<String> getAssignStoreAccumuLateGroupIds(String[] storeIdArr) {
        return spVoucherActiveMgMapper.getAssignStoreAccumuLateGroupIds(storeIdArr);
    }

    @Override
    public SpVoucherActive getSpVoucherActiveById(Integer id) {
        return spVoucherActiveMapper.selectByPrimaryKey(id);
    }


    @Override
    public Pager<StoreMgVo> getAccumulateStore(SpVoucherActiveRo activeRo) {
        SpVoucherActive active = spVoucherActiveMapper.selectByPrimaryKey(activeRo.getId());
        Integer count = 0;
        List<StoreMgVo> list = null;
        Map<String,Object> map = new HashMap<>();
        activeRo.setRuleScopFlag(active.getRuleScopFlag());
        if(active.getRuleScopFlag().intValue()==0){//全部定格
           map.put("active",activeRo);
           list = spStoreMgMapper.getAccStore(map);
        }else if(active.getRuleScopFlag().intValue()==1){//指定定格   杨开泰 2016/10/25 0025 14:36
            String ruleScop = active.getRuleScop();
            ruleScop = StringUtils.substring(ruleScop,1,ruleScop.lastIndexOf(","));
            String[] ruleScopArr = ruleScop.split(",");
            map.put("active",activeRo);
            map.put("ruleScopArr",ruleScopArr);
            list = spStoreMgMapper.getAccStore(map);
        }else{//指定用户   杨开泰 2016/10/25 0025 14:36
            String storeIds = active.getStoreIds();
            storeIds = StringUtils.substring(storeIds,1,storeIds.lastIndexOf(","));
            String[] storeIdArr = storeIds.split(",");
            map.put("active",activeRo);
            map.put("storeIdArr",storeIdArr);
            list = spStoreMgMapper.getAccStore(map);
        }

        if(list!=null&&list.size()!=0){//
            count = spStoreMgMapper.getCountAccStore(map);
            for (StoreMgVo storeMgVo :
                    list) {
                //计算活动期间下单总额
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("storeMgVo",storeMgVo);
                paramMap.put("active",active);
                String rulePayStr = active.getRulePayStr();
                rulePayStr = StringUtils.substring(rulePayStr,1,rulePayStr.lastIndexOf(","));
                String[] rulePayStrArr = rulePayStr.split(",");
                paramMap.put("rulePayStrArr",rulePayStrArr);
                BigDecimal totalOrderPrice = spOrderInfoMgMapper.getAccTotalOrderPrice(paramMap);
                if(totalOrderPrice!=null){
                    //计算活动期间退货总额
                    BigDecimal returnTotal = spOrderInfoMgMapper.getAccTotalReturnOrderPrice(paramMap);
                    if(returnTotal!=null){
                        totalOrderPrice = totalOrderPrice.subtract(returnTotal);
                    }
                }else{
                    totalOrderPrice = BigDecimal.ZERO;
                }
                storeMgVo.setTotalOrderPrice(totalOrderPrice);

                //计算发送优惠劵总额
                BigDecimal sendTotal = spVoucherActiveStoreLogMgMapper.getTotalSend(paramMap);
                if(sendTotal==null){
                    sendTotal = BigDecimal.ZERO;
                }
                storeMgVo.setTotalSendPrice(sendTotal);
            }
        }
        return new Pager<>(count,list);
    }

    @Override
    public List<SpVoucherActiveStoreLog> getAccLog(SpVoucherActive active) {
        return spVoucherActiveStoreLogMgMapper.getAccLog(active);
    }

}
