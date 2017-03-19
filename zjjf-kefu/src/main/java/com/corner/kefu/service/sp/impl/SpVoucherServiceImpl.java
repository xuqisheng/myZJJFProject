/**
 *
 */
package com.corner.kefu.service.sp.impl;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.MsgBean;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.SpPushMsgMapMapper;
import com.corner.core.dao.SpPushMsgMapper;
import com.corner.core.dao.SpVoucherMapper;
import com.corner.core.utils.message.tools.UMengMessageVo;
import com.corner.core.utils.message.tools.UMengPushTools;
import com.corner.core.utils.safe.MD5;
import com.corner.kefu.beans.ro.sp.SpVoucherActiveRo;
import com.corner.kefu.beans.ro.sp.SpVoucherRo;
import com.corner.kefu.beans.ro.sp.SpVoucherTempRo;
import com.corner.kefu.beans.vo.ConfigShareVo;
import com.corner.kefu.beans.vo.sp.SpVhVo;
import com.corner.kefu.beans.vo.sp.StoreVo;
import com.corner.kefu.config.SpVoucherKey;
import com.corner.kefu.dao.sp.SpVoucherActiveMgMapper;
import com.corner.kefu.dao.sp.SpVoucherMgMapper;
import com.corner.kefu.dao.sp.SpVoucherTempMgMapper;
import com.corner.kefu.service.sp.SpVoucherService;
import com.corner.kefu.task.SaveSpVoucherTask;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName: SpVoucherService
 * @Description: 优惠劵业务类
 * @author: 杨开泰
 * @date: 2015年11月24日 下午12:39:58
 */
@Service
public class SpVoucherServiceImpl implements SpVoucherService {

    public static Logger logger = LoggerFactory.getLogger(SpVoucherServiceImpl.class);

    @Autowired
    SpVoucherActiveMgMapper spVoucherActiveMgMapper;
    @Autowired
    SpVoucherTempMgMapper spVoucherTempMgMapper;
    @Autowired
    SpVoucherMgMapper spVoucherMgMapper;
    @Autowired
    SpVoucherMapper spVoucherMapper;
    @Autowired
    SpPushMsgMapper spPushMsgMapper;
    @Autowired
    SpPushMsgMapMapper spPushMsgMapMapper;
    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    /**
     * @param @return 设定文件
     * @return MsgBean    返回类型
     * @throws
     * @Title: sendSpVoucherByActive
     * @Description: TODO(活动类型(0-默认活动, 1-注册送优惠券, 2-订单满送优惠券, 3-订单满减, 4-订单满折, 5-订单送实物, 6-送积分, 7-送兑奖码)活动类型(0-默认活动, 1-注册送优惠券, 2-订单满送优惠券, 3-订单满减, 4-订单满折, 5-订单送实物, 6-送积分, 7-送兑奖码))
     * @author 铁中棠 tiezhongtang@izjjf.cn
     * @date 2016年1月6日 下午7:57:32
     */
    public MsgBean sendSpVoucherByActive(
            Integer sendFlag,  //活动类型(0-默认活动,1-注册送优惠券,2-订单满送优惠券,3-订单满减,4-订单满折,5-订单送实物,6-送积分,7-送兑奖码)
            String orderId,  //满送订单id，因为那比订单送的现金券
            String orderNo,
            BigDecimal orderPrice, //满送订单价格
            Byte payType,   //满送订单id对应的支付方式
            Integer spGroupId,//满送订单id对应的定格
            String acGroupId,//活动对应的用户组id
            Integer storeId, //满送订单id对应的店铺id
            String storeName,  //满送订单id对应的店铺名称
            String storeMobile //店铺电话
    ) {
        if (sendFlag == null || spGroupId == null)
            {//错误
                return new MsgBean(false, "参数不能为空");
            } else if (SpVoucherKey.registerSendType == sendFlag)
            {//注册送
                List<SpVoucherActive> ruleList = null;
                boolean pre = true;
                //先获取用户组的满减活动规则
                if (!StringUtils.isEmpty(acGroupId))
                    {
                        SpVoucherActiveRo activeRo = new SpVoucherActiveRo();
                        activeRo.setAcGroupId(acGroupId);
                        activeRo.setRuleType(SpVoucherKey.registerSendType.byteValue());
                        activeRo.setStatus(Byte.parseByte("1"));
                        activeRo.setTodayNow(new Date());
                        activeRo.setSortName("a.updateTime");
                        activeRo.setSortOrder("DESC");
                        ruleList = spVoucherActiveMgMapper.getAcGroupSpVoucherActive(activeRo);
                        if (ruleList != null && !ruleList.isEmpty())
                            {
                                pre = false;
                            }
                    }
                //再获取区域的活动规则
                if (pre)
                    {
                        SpVoucherActiveRo activeRo = new SpVoucherActiveRo();
                        activeRo.setSpGropId(spGroupId);
                        activeRo.setRuleType(SpVoucherKey.registerSendType.byteValue());
                        activeRo.setStatus(Byte.parseByte("1"));
                        activeRo.setTodayNow(new Date());
                        activeRo.setSortName("a.updateTime");
                        activeRo.setSortOrder("DESC");
                        ruleList = spVoucherActiveMgMapper.getSpGroupSpVoucherActive(activeRo);
                    }
                if (ruleList == null || ruleList.isEmpty() || ruleList.get(0).getSendId() == null)
                    {
                        return new MsgBean(false, "未查到询注册送相应的活动");
                    } else
                    {
                        SpVoucher spVoucher = sendSpVoucherAuto(sendFlag, ruleList.get(0), orderId, orderNo, orderPrice, payType, spGroupId, storeId, storeName, storeMobile);
                        if (spVoucher == null)
                            {
                                return new MsgBean(false, "发送现金券失败");
                            } else
                            {
                                return new MsgBean(true, "发送现金券成功", spVoucher);
                            }
                    }
            } else if (SpVoucherKey.orderMangSendType == sendFlag)
            {//订单满送
                if (orderId == null || orderPrice == null || payType == null | storeId == null || storeName == null)
                    {
                        return new MsgBean(false, "参数不能为空");
                    } else
                    {
                        List<SpVoucherActive> listActive = null;
                        boolean pre = true;
                        //先获取用户组的满减活动规则
                        if (!StringUtils.isEmpty(acGroupId))
                            {
                                SpVoucherActiveRo activeRo = new SpVoucherActiveRo();
                                activeRo.setAcGroupId(acGroupId);
                                activeRo.setRuleType(SpVoucherKey.orderMangSendType.byteValue());
                                activeRo.setStatus(Byte.parseByte("1"));
                                activeRo.setTodayNow(new Date());
                                activeRo.setSortName("a.ruleStartPrice");
                                activeRo.setSortOrder("ASC");
                                listActive = spVoucherActiveMgMapper.getAcGroupSpVoucherActive(activeRo);
                                if (listActive != null && !listActive.isEmpty())
                                    {
                                        pre = false;
                                    }
                            }
                        //再获取区域的活动规则
                        if (pre)
                            {
                                SpVoucherActiveRo activeRo = new SpVoucherActiveRo();
                                activeRo.setSpGropId(spGroupId);
                                activeRo.setRuleType(SpVoucherKey.orderMangSendType.byteValue());
                                activeRo.setStatus(Byte.parseByte("1"));
                                activeRo.setTodayNow(new Date());
                                activeRo.setSortName("a.ruleStartPrice");
                                activeRo.setSortOrder("ASC");
                                listActive = spVoucherActiveMgMapper.getSpGroupSpVoucherActive(activeRo);
                            }
                        if (listActive == null || listActive.isEmpty())
                            {
                                return new MsgBean(false, "未查询到订单满送相应的活动");
                            } else
                            {
                                int j = listActive.size();
                                for (int i = 0; i < listActive.size(); i++)
                                    {
                                        SpVoucherActive spVoucherActive = listActive.get(i);
                                        if (spVoucherActive == null || spVoucherActive.getRuleStartPrice() == null)
                                            {
                                                return new MsgBean(false, "活动参数异常");
                                            }
                                        if (i == 0 && orderPrice.compareTo(spVoucherActive.getRuleStartPrice()) < 0)
                                            {
                                                return new MsgBean(false, "未达到指定金额");
                                            }
                                        if (i == (j - 1) && orderPrice.compareTo(spVoucherActive.getRuleStartPrice()) >= 0)
                                            {
                                                SpVoucher spvoucher = sendSpVoucherAuto(
                                                        sendFlag,//发送标识1-注册送，2-满减送等等在配置文件中配置
                                                        spVoucherActive,  //发送规则
                                                        orderId,  //满送订单id，因为那比订单送的现金券
                                                        orderNo,
                                                        orderPrice, //满送订单价格
                                                        payType,   //满送订单id对应的支付方式
                                                        spGroupId,//满送订单id对应的定格
                                                        storeId, //满送订单id对应的店铺id
                                                        storeName,  //满送订单id对应的店铺名称
                                                        storeMobile //店铺电话
                                                );
                                                if (spvoucher != null)
                                                    {
                                                        return new MsgBean(true, "发送现金券成功", spvoucher);
                                                    } else
                                                    {
                                                        return new MsgBean(false, "发送现金券失败");
                                                    }
                                            }
                                        if (orderPrice.compareTo(spVoucherActive.getRuleStartPrice()) == -1)
                                            {//小于当前值
                                                SpVoucherActive spVoucherActiveLast = listActive.get(i - 1);
                                                SpVoucher spvoucher = sendSpVoucherAuto(
                                                        sendFlag,//发送标识1-注册送，2-满减送等等在配置文件中配置
                                                        spVoucherActiveLast,  //发送规则
                                                        orderId,  //满送订单id，因为那比订单送的现金券
                                                        orderNo,
                                                        orderPrice, //满送订单价格
                                                        payType,   //满送订单id对应的支付方式
                                                        spGroupId,//满送订单id对应的定格
                                                        storeId, //满送订单id对应的店铺id
                                                        storeName,  //满送订单id对应的店铺名称
                                                        storeMobile //店铺电话
                                                );
                                                if (spvoucher != null)
                                                    {
                                                        return new MsgBean(true, "发送现金券成功", spvoucher);
                                                    } else
                                                    {
                                                        return new MsgBean(false, "发送现金券失败");
                                                    }
                                            }
                                    }
                            }
                    }
            } else
            {
                return new MsgBean(false, "参数不能为空");
            }
        return new MsgBean(false, "参数不能为空");
    }

    /**
     * @param @param  storeId 关联店铺
     * @param @param  ruleId:优惠券规则id
     * @param @param  once：是否一个用户只能发放一次
     * @param @param  isPublish：0-不发布，1-发布
     * @param @return 设定文件
     * @return SpVoucher    返回类型
     * @throws
     * @Title: sendSpVoucherAuto
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 铁中棠 tiezhongtang@izjjf.cn
     */
    public synchronized SpVoucher sendSpVoucherAuto(
            Integer sendFlag,//发送标识1-注册送，2-满减送等等在配置文件中配置
            SpVoucherActive rule,  //发送规则
            String orderId,  //满送订单id，因为那比订单送的现金券
            String orderNo, //满送订单编号
            BigDecimal orderPrice, //满送订单价格
            Byte payType,   //满送订单id对应的支付方式
            Integer spGroupId,//满送订单id对应的定格
            Integer storeId, //满送订单id对应的店铺id
            String storeName,  //满送订单id对应的店铺名称
            String storeMobile //店铺电话
    ) {
        try
            {
//			if(ruleId==null || spGroupId==null){
//				return null;
//			}else if(SpVoucherKey.registerSendFlag==ruleId){
//				//暂无特殊条件审核
//			}else if(SpVoucherKey.orderMangSendFlag==ruleId){
//				if(orderId==null || orderPrice==null || payType==null| storeId==null || storeName==null){
//					return null;
//				}
//			}else{
//				return null;
//			}
                //获取活动规则（以后可能根据）
                //SpVoucherActive rule=spVoucherActiveDao.selectByPrimaryKey(ruleId);
                if (rule == null
                        || rule.getRuleName() == null
                        || rule.getStatus() == null
                        || rule.getRuleStart() == null
                        || rule.getRuleEnd() == null
                        || rule.getRuleScopFlag() == null
                        || rule.getRulePayType() == null
                        || rule.getRuleStartPrice() == null
                        || rule.getRuleSendLimit() == null
                        || rule.getSendType() == null
                        || rule.getSendId() == null)
                    {
                        return null;
                    }
                //检查现金券合法性
                SpVoucherTempRo ro = new SpVoucherTempRo();
                ro.setId(rule.getSendId());
                ro.setStatus((byte) 1);
                ro.setIsDelete(false);
                List<SpVoucherTemp> spList = spVoucherTempMgMapper.getSpVoucherTempByCondition(ro);
                if (spList == null || spList.isEmpty() || spList.get(0) == null)
                    {
                        return null;
                    }
                SpVoucherTemp spVoucherTemp = spList.get(0);
                if (spVoucherTemp == null
                        || spVoucherTemp.getBillType() == null
                        || spVoucherTemp.getUseMoney() == null
                        || spVoucherTemp.getUseDay() == null
                        || spVoucherTemp.getStartPrice() == null
                        || spVoucherTemp.getUseItemFlag() == null
                        || spVoucherTemp.getPayType() == null
                        || spVoucherTemp.getStartPrice() == null)
                    {
                        return null;
                    }
                //现金券规则未开启
                if (rule.getStatus() == 0)
                    {
                        return null;
                    }
                //现金券活动时间
                Date nowDate = new Date();
                if (nowDate.before(rule.getRuleStart())
                        || nowDate.after(rule.getRuleEnd())
                        || rule.getRuleEnd().before(rule.getRuleStart()))
                    {
                        return null;
                    }

                //注册送条件检查,定格id
            /*
			if(rule.getRuleScopFlag()==0){
				//doNothing
			}else if(rule.getRuleScopFlag()==1){
				String spGroupIdStr=","+spGroupId+",";
				if(StringUtils.isEmpty(rule.getRuleScop())
						||!rule.getRuleScop().startsWith(",") 
						||!rule.getRuleScop().endsWith(",")	){
					return null;
				}else if(!rule.getRuleScop().contains(spGroupIdStr)){
					return null;
				}
			}else{
				return null;
			}
			*/

                //是否满足指定支付方式
                if (rule.getRulePayType() == 0)
                    {
                        //doNothing
                    } else if (rule.getRulePayType() == 1)
                    {
                        String payStr = "," + payType + ",";
                        if (StringUtils.isEmpty(rule.getRulePayStr())
                                || !rule.getRulePayStr().startsWith(",")
                                || !rule.getRulePayStr().endsWith(","))
                            {
                                return null;
                            } else if (!rule.getRulePayStr().contains(payStr))
                            {
                                return null;
                            }
                    } else
                    {
                        return null;
                    }

                //是否满足指定发放次数至少一次
                if (rule.getRuleSendLimit() < 1)
                    {
                        return null;
                    }

                //是否满足指定起送金额
                int intStartPrice = rule.getRuleStartPrice().compareTo(new BigDecimal(0));
                if (intStartPrice < 0)
                    {
                        //startPrice为负数
                        return null;
                    } else if (intStartPrice == 0)
                    {
                        if (SpVoucherKey.orderMangSendType == sendFlag)
                            {
                                if (orderPrice.compareTo(new BigDecimal(0)) < 1)
                                    {
                                        return null;
                                    }
                            }
                    } else
                    {//startPrice为正数
                        if (SpVoucherKey.orderMangSendType == sendFlag)
                            {
                                if (orderPrice.compareTo(new BigDecimal(0)) < 1)
                                    {
                                        return null;
                                    }
                                if (rule.getRuleStartPrice().compareTo(orderPrice) > 0)
                                    {
                                        return null;
                                    }
                            }
                    }
                //=============================检查现金券字段是否合法======================================
                //首先检查现金券发布状态
                if (spVoucherTemp.getCreatPub() == null)
                    {
                        return null;
                    }

                //首先检查现金券状态
                if (spVoucherTemp.getCreatStatus() == null)
                    {
                        return null;
                    }

                //现金券有效期多少天
                if (spVoucherTemp.getUseDay() < 1)
                    {
                        return null;
                    }

                //现金券金额
                if (spVoucherTemp.getUseMoney().compareTo(new BigDecimal(0)) < 1)
                    {
                        return null;
                    }

                //检查支付方式
                if (spVoucherTemp.getPayType() != 0 && spVoucherTemp.getPayType() != 1)
                    {
                        return null;
                    } else if (spVoucherTemp.getPayType() == 1)
                    {
                        if (StringUtils.isEmpty(spVoucherTemp.getPayStr())
                                || !spVoucherTemp.getPayStr().startsWith(",")
                                || !spVoucherTemp.getPayStr().endsWith(","))
                            {
                                return null;
                            }
                    }

                //检查商品字段
                if (spVoucherTemp.getUseItemFlag() != 0 && spVoucherTemp.getUseItemFlag() != 1 && spVoucherTemp.getUseItemFlag() != 2)
                    {
                        return null;
                    } else if (spVoucherTemp.getUseItemFlag() == 1 || spVoucherTemp.getUseItemFlag() == 2)
                    {
                        if (StringUtils.isEmpty(spVoucherTemp.getUseItemIds())
                                || !spVoucherTemp.getUseItemIds().startsWith(",")
                                || !spVoucherTemp.getUseItemIds().endsWith(","))
                            {
                                return null;
                            }
                    }

                //查询该用户发放同一类型现金券个数
                SpVoucherRo spvRo = new SpVoucherRo();
                spvRo.setStoreId(storeId);
                spvRo.setRuleId(rule.getSendId());//temperId

                //订单金额满送次数
                if (sendFlag == SpVoucherKey.orderMangSendType)
                    {
                        //同一订单只送一次
                        spvRo.setPreOrderId(orderId);
                        List<SpVhVo> spVhVoList = spVoucherMgMapper.getStoreVoucherVoList(spvRo);
                        if (spVhVoList != null && spVhVoList.size() > 0 && spVhVoList.get(0) != null)
                            {
                                return null;
                            }
                    }

                //注册送(只发一次)
                if (sendFlag == SpVoucherKey.registerSendType)
                    {
                        spvRo.setPreOrderId(null);
                        List<SpVhVo> spVhVoList = spVoucherMgMapper.getStoreVoucherVoList(spvRo);
                        if (spVhVoList != null && spVhVoList.size() > 0)
                            {
                                return null;
                            }
                    }

                //单个商店一天限制多少次
                //spvRo.setRuleId(null);
                spvRo.setPreOrderId(null);
                spvRo.setOneDay(1);
                List<SpVhVo> spVhVoList = spVoucherMgMapper.getStoreVoucherVoList(spvRo);
                if (spVhVoList != null && spVhVoList.size() >= rule.getRuleSendLimit())
                    {
                        return null;//超过次数
                    }

                //插入现金券
                SpVoucher spVoucher = new SpVoucher();
                spVoucher.setStoreId(storeId);
                spVoucher.setStoreNm(storeName);
                spVoucher.setRuleId(spVoucherTemp.getId());
                spVoucher.setName(spVoucherTemp.getRuleName());
                spVoucher.setRemark(spVoucherTemp.getRuleRemark());//设置备注
                spVoucher.setPreOrderId(orderId);//******重要字段*****
                spVoucher.setMoney(spVoucherTemp.getUseMoney().intValue());//现金券金额
                spVoucher.setBillType(spVoucherTemp.getBillType());//现金券or优惠券等
                spVoucher.setStartPrice(spVoucherTemp.getStartPrice());//起送金额
                spVoucher.setPayType(spVoucherTemp.getPayType());//支付方式
                spVoucher.setPayStr(spVoucherTemp.getPayStr());//支付字符串
                spVoucher.setGoodsType(spVoucherTemp.getUseItemFlag());//商品规则
                spVoucher.setGoodsStr(spVoucherTemp.getUseItemIds());//商品ids
                Calendar startTime = new GregorianCalendar();
                startTime.set(Calendar.HOUR_OF_DAY, 0);
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.SECOND, 0);
                spVoucher.setStartTime(startTime.getTime());
                spVoucher.setCreateTime(startTime.getTime());
                spVoucher.setUpdateTime(startTime.getTime());
                startTime.add(Calendar.DATE, spVoucherTemp.getUseDay() - 1);
                startTime.set(Calendar.HOUR_OF_DAY, 23);
                startTime.set(Calendar.MINUTE, 58);
                startTime.set(Calendar.SECOND, 0);

                spVoucher.setExpiredTime(startTime.getTime());
                //spVoucher.setCreaterId(createrId);//默认
                //spVoucher.setCreaterName(createrName);//默认
                spVoucher.setCreateType((byte) 1);
                spVoucher.setSecKey(MD5.StringToMd5(storeId + spVoucherTemp.getUseMoney().toString() + SpVoucherKey.SPVOUCHERKEY));
                spVoucher.setPubStatus(spVoucherTemp.getCreatPub());
                spVoucher.setStatus(spVoucherTemp.getStatus());
                int result = spVoucherMapper.insertSelective(spVoucher);
                if (result == 1)
                    {
                        try
                            {
                                String content = spVoucherTemp.getRuleMsg();
                                if (StringUtils.isEmpty(content))
                                    {
                                        return spVoucher;
                                    }
                                //if(SpVoucherKey.enablePhonemsg != null && SpVoucherKey.enablePhonemsg==1){
                                //phonemsgService.sendMessage(storeMobile, content);
                                //}
                                if (SpVoucherKey.enableUMmsg != null && SpVoucherKey.enableUMmsg == 1)
                                    {
                                        UMengPushTools uMengPushTools = UMengPushTools.getInstance();
                                        UMengMessageVo uMengMessageVo = new UMengMessageVo();
                                        uMengMessageVo.setAlert("转角街坊优惠券消息");
                                        uMengMessageVo.setTicker("转角街坊优惠券消息");
                                        uMengMessageVo.setTitle("转角街坊优惠券消息");
                                        uMengMessageVo.setText(content);
                                        uMengMessageVo.setAlias(storeMobile);
                                        uMengMessageVo.setAlias_type("android");
                                        uMengPushTools.sendAndroidCustomizedcast(uMengMessageVo);//android
                                        uMengMessageVo.setAlias_type("iOS");
                                        uMengPushTools.sendIOSCustomizedcast(uMengMessageVo);//ios
                                        //uMengPushTools.sendIOSCustomizedcast2(uMengMessageVo);//iso appstore
                                        SpPushMsg spPushMsg = new SpPushMsg();
                                        spPushMsg.setMsgTitle("优惠券消息");
                                        spPushMsg.setTicker("优惠券消息");
                                        if (StringUtils.isEmpty(orderNo))
                                            {
                                                spPushMsg.setContent(content);
                                            } else
                                            {
                                                spPushMsg.setContent(content + "。订单编号:" + orderNo);
                                            }
                                        spPushMsg.setMsgType((byte) 2);//优惠消息
                                        spPushMsg.setPublishId("system");
                                        spPushMsg.setPublishName("system");
                                        spPushMsg.setPublishTime(new Date());
                                        SpPushMsgMap spPushMsgMap = new SpPushMsgMap();
                                        spPushMsgMap.setMsgId(spPushMsg.getId());
                                        spPushMsgMap.setStoreId(storeId);
                                        spPushMsgMapMapper.insertSelective(spPushMsgMap);
                                        spPushMsgMapper.insertSelective(spPushMsg);
                                    }
                            } catch (Exception e)
                            {
                                logger.error("【现金券通知】：发送短信或友盟消息异常", e);
                            }
                        return spVoucher;
                    } else
                    {
                        spVoucherMapper.deleteByPrimaryKey(spVoucher.getId());
                        return null;
                    }
            } catch (Exception e)
            {
                logger.error("现金券发放异常", e);
                return null;
            }
    }

    /**
     * Title: getSpVoucherIsUsedList
     * Description:根据优惠劵id判断优惠劵有没有被使用
     *
     * @param id
     * @return
     * @throws Exception
     * @see com.corner.kefu.service.sp.SpVoucherService#getSpVoucherIsUsedList(java.lang.Integer)
     */
    @Override
    public List<SpVoucher> getSpVoucherIsUsedList(Integer id) throws Exception {
        return spVoucherMgMapper.getSpVoucherIsUsedList(id);
    }

    /**
     * Title: asyAddSpVoucher
     * Description:发送优惠劵
     *
     * @param list
     * @param spVoucherTemp
     * @throws Exception
     * @see com.corner.kefu.service.sp.SpVoucherService#asyAddSpVoucher(java.util.List, com.corner.core.beans.SpVoucherTemp)
     */
    @Override
    public void asyAddSpVoucher(List<StoreVo> list, SpVoucherTemp spVoucherTemp) throws Exception {
        taskExecutor.execute(new SaveSpVoucherTask(list, spVoucherTemp, spVoucherMapper));

        /*int pageCount = list.size() % 250 > 0 ? list.size() / 250 + 1 : list.size() / 250;
        for (int i = 0; i < pageCount; i++)
            {
                List<StoreVo> listtemp = new ArrayList<>(list.subList(i * 250, (i + 1) * 250 > list.size() ? list.size() : (i + 1) * 250));
                taskExecutor.execute(new SaveSpVoucherTask(listtemp, spVoucherTemp, spVoucherMapper));
            }*/

        /*if (!StringUtil.stringIsNullOrEmpty(spVoucherTemp.getRuleMsg())
                && SpVoucherKey.phonemsgStart != null
                && list != null && list.size() > SpVoucherKey.phonemsgStart.intValue()
                && SpVoucherKey.enablePhonemsg != null && SpVoucherKey.enablePhonemsg == 1)
            {
                StringBuffer mobiles = new StringBuffer();
                for (Iterator<StoreVo> iterator = list.iterator(); iterator.hasNext(); )
                    {
                        StoreVo storeVo = iterator.next();
                        mobiles.append(storeVo.getMobile());
                        mobiles.append(",");
                    }
                mobiles.deleteCharAt(mobiles.length() - 1);//移除最后一个符号
                taskExecutor.execute(new SendPhoneMsgRunnable(mobiles.toString(), "【转角街坊】尊敬的会员:" + spVoucherTemp.getRuleMsg() + "回N退订", PhoneMsgConfig.apikeyyx, PhoneMsgConfig.usernameyx, PhoneMsgConfig.passwordyx));
            }
        if (!StringUtil.stringIsNullOrEmpty(spVoucherTemp.getRuleMsg()))
            {
                taskExecutor.execute(new PcSpVoucherPushRunnable(list, spVoucherTemp.getRuleMsg()));
            }*/
    }

    /**
     * Title: getSpVoucherByRuleIdAndStoreId
     * Description:根据优惠劵id和店铺id查询优惠劵使用情况
     *
     * @param map
     * @return
     * @throws Exception
     * @see com.corner.kefu.service.sp.SpVoucherService#getSpVoucherByRuleIdAndStoreId(java.util.Map)
     */
    @Override
    public List<SpVoucher> getSpVoucherByRuleIdAndStoreId(Map<String, Object> map) throws Exception {
        return spVoucherMgMapper.getSpVoucherByRuleIdAndStoreId(map);
    }

    /**
     * Title: deleteByRuleIdAndStoreId
     * Description:
     *
     * @param map
     * @throws Exception
     * @see com.corner.kefu.service.sp.SpVoucherService#deleteByRuleIdAndStoreId(java.util.Map)
     */
    @Override
    public void deleteByRuleIdAndStoreId(Map<String, Object> map) throws Exception {
        spVoucherMgMapper.deleteByRuleIdAndStoreId(map);
    }

    /**
     * Title: getSpVoucherUsedLog
     * Description:获取优惠劵使用记录
     *
     * @param map
     * @return
     * @throws Exception
     * @see com.corner.kefu.service.sp.SpVoucherService#getSpVoucherUsedLog(java.util.Map)
     */
    @Override
    public Pager<StoreVo> getSpVoucherUsedLog(Map<String, Object> map) throws Exception {
        List<StoreVo> list = spVoucherMgMapper.getSpVoucherUsedLog(map);
        Integer count = spVoucherMgMapper.getCountSpVoucherUsedLog(map);
        return new Pager<StoreVo>(count, list);
    }

    @Override
    public List<SpVoucher> getSpVoucerIsEdit(Integer id) throws Exception {
        return spVoucherMgMapper.getSpVoucerIsEdit(id);
    }

    /**
     * 邀请的小店审核通过后向邀请人发送优惠券
     */
    @Override
    public boolean saveSpVoucher(Store store1, ConfigShareVo configShare) {
        // TODO Auto-generated method stub
        SpVoucher spVoucher = new SpVoucher();
        spVoucher.setStoreId(store1.getId());
        spVoucher.setStoreNm(store1.getName());
        if (configShare.getRuleId() != null)
            {
                spVoucher.setRuleId(configShare.getRuleId());
                spVoucher.setName(configShare.getRuleName());
                spVoucher.setRemark(configShare.getRuleRemark());
                spVoucher.setMoney(configShare.getUseMoney().intValue());
                spVoucher.setPlantHalve(100.00);
                spVoucher.setPubStatus(Byte.parseByte("1"));
                spVoucher.setBillType(configShare.getBillType());
                spVoucher.setStartPrice(configShare.getStartPrice());
                spVoucher.setPayType(configShare.getPayType());
                spVoucher.setPayStr(configShare.getPayStr());
                spVoucher.setGoodsType(configShare.getUseItemFlag());
                spVoucher.setGoodsStr(configShare.getUseItemIds());

                Calendar startTime = new GregorianCalendar();
                startTime.set(Calendar.HOUR_OF_DAY, 0);
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.SECOND, 0);
                spVoucher.setStartTime(startTime.getTime());
                spVoucher.setCreateTime(startTime.getTime());
                spVoucher.setUpdateTime(startTime.getTime());
                startTime.add(Calendar.DATE, configShare.getUseDay() - 1);
                startTime.set(Calendar.HOUR_OF_DAY, 23);
                startTime.set(Calendar.MINUTE, 58);
                startTime.set(Calendar.SECOND, 0);

//			spVoucher.setStartTime(new Date());
//			spVoucher.setCreateTime(new Date());
//			spVoucher.setUpdateTime(new Date());

                spVoucher.setExpiredTime(DateUtils.addDays(new Date(), configShare.getUseDay()));
                spVoucherMapper.insertSelective(spVoucher);
                logger.info("优惠券发送后成功！");
                return true;

            } else
            {
                return false;
            }
    }

    @Override
    public SpVoucher selectByPrimaryKey(String id) throws Exception {
        return spVoucherMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByPrimaryKey(String spVoucherId) throws Exception {
        spVoucherMapper.deleteByPrimaryKey(spVoucherId);
    }

}
