/**
 * @Title: PcSpVoucherActiveController.java
 * @Package com.corner.pc.web.controller
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月5日 下午9:33:30
 * @version V1.0
 */

package com.corner.kefu.controller.sp;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.enums.PayMethod;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.code.MatrixUtil;
import com.corner.kefu.beans.ro.sp.SpVoucherActiveMgRo;
import com.corner.kefu.beans.ro.sp.SpVoucherActiveRo;
import com.corner.kefu.beans.vo.sp.*;
import com.corner.kefu.config.CommonPageConfig;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.enums.spVoucher.CouponProductCondition;
import com.corner.kefu.enums.spVoucher.VoucherActiveType;
import com.corner.kefu.service.ConfigService;
import com.corner.kefu.service.ItemBaseService;
import com.corner.kefu.service.SpOrderInfoService;
import com.corner.kefu.service.sp.*;
import com.corner.kefu.utils.PromotionUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.DoubleRange;
import org.apache.commons.lang.time.DateUtils;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.jmx.snmp.EnumRowStatus.active;

/**
 * @author 杨开泰  yangkaitai@izjjf.cn
 * @ClassName: PcSpVoucherActiveController
 * @Description:活动管理控制器
 * @date 2016年3月29日 上午9:29:04
 */
@RequestMapping("/Customer/active")
@Controller
public class PcSpVoucherActiveController extends KefuBaseWebController {

    private static final Logger logger = LoggerFactory.getLogger(PcSpVoucherActiveController.class);

    private static final String PATH = "/promotion/";

    @Autowired
    SpVoucherActiveService spVoucherActiveService;

    @Autowired
    SpGroupService spGroupService;

    @Autowired
    SpVoucherTempService spVoucherTempService;

    @Autowired
    SpVoucherGradedService spVoucherGradedService;

    @Autowired
    SpOrderInfoService spOrderInfoService;

    @Autowired
    ItemBaseService itemBaseService;

    @Autowired
    ConfigService configService;
    @Autowired
    SpStoreService spStoreService;
    @Autowired
    SendVoucherService sendVoucherService;

	/*
     * @Autowired SpVoucherActiveMapService spVoucherActiveMapService;
	 * 
	 * @Autowired PcStoreService pcStoreService;
	 * 
	 * @Autowired SpActiveStoreMapService spActiveStoreMapService;
	 */





	@ResponseBody
    @RequestMapping("/canSendVoucher.do")
	public Object canSendVoucher(SpVoucherActive active){
        if(active.getId()==null){
            return ResponseUtils.sendMsg(false,"缺少id");
        }
        List<SpVoucherActiveStoreLog> list = spVoucherActiveService.getAccLog(active);
        if(list==null||list.size()==0){
            SpVoucherActive spVoucherActive = spVoucherActiveService.getSpVoucherActiveById(active.getId());
            Map<String,Object> map = sendVoucherService.sendVoucher(spVoucherActive.getId());
            return ResponseUtils.sendMsg(true);
        }
	    return ResponseUtils.sendMsg(false);
    }



	/**
	 * @Title:获取累积送券需要发送的用户
	 * @Description:
	 * @param 
	 * @return 
	 * @throws
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @date 2016/10/25 0025 14:01
	 */
	@RequestMapping("/toAccumulation.do")
	public String toAccumulation(SpVoucherActiveRo activeRo,HttpServletRequest request,Model model){
	    if(activeRo.getId()==null){
	        return error("缺少id!",model,request);
        }else{
            model.addAttribute("id",activeRo.getId());
        }
        String pageIndexStr = request.getParameter("pageIndex");
        Integer pageIndex = CommonPageConfig.commonPageIndex;
        if(StringUtils.isNotEmpty(pageIndexStr)){
            pageIndex = Integer.parseInt(pageIndexStr);
            activeRo.setPageIndex(pageIndex);
        }
        SpVoucherActive active = spVoucherActiveService.getSpVoucherActiveById(activeRo.getId());
        model.addAttribute("ruleStartStr",DateUtil.date2StandardString(active.getRuleStart()));
        model.addAttribute("ruleEndStr",DateUtil.date2StandardString(active.getRuleEnd()));
        if(StringUtils.isNotEmpty(activeRo.getKeyStr())){
            activeRo.setKeyStr(activeRo.getKeyStr().trim());
            model.addAttribute("keyStr",activeRo.getKeyStr().trim());
        }
        Pager<StoreMgVo> pager = spVoucherActiveService.getAccumulateStore(activeRo);
        List<StoreMgVo> list = pager.getList();
        model.addAttribute("list",list);
        this.pageUtil(activeRo.getPageIndex(),pager.getTotalSize(),activeRo.getPageSize(),request,model);
        return PATH+"accumulation";
    }






    /**
     * @param
     * @return
     * @throws
     * @Title: 获取店铺数据
     * @Description:
     * @author 杨开泰 yangkaitai@izjjf.cn
     * @date 2016/10/18 0018 14:12
     */
    @RequestMapping("/getStore.do")
    @ResponseBody
    public Object getStore(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<>();
        String keyStr = request.getParameter("keyStr");
        if (StringUtils.isNotEmpty(keyStr)) {
            paramMap.put("keyStr", keyStr);
        }
        Integer cityId = Integer.parseInt(request.getParameter("cityId"));
        Integer areaId = Integer.parseInt(request.getParameter("areaId"));
        Integer groupId = Integer.parseInt(request.getParameter("groupId"));
        if (cityId.intValue() != -1) {
            paramMap.put("cityId", cityId);
        }
        if (areaId.intValue() != -1) {
            paramMap.put("areaId", areaId);
        }
        if (groupId.intValue() != -1) {
            paramMap.put("groupId", groupId);
        }
        Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        if (pageIndex == null) {
            pageIndex = CommonPageConfig.asyncPageIndex;
        }
        Integer pageSize = CommonPageConfig.asyncPageSize;
        paramMap.put("pageIndex", pageIndex * pageSize);
        paramMap.put("pageSize", pageSize);
        Pager<StoreMgVo> pager = spStoreService.getStore(paramMap);
        pager.setFlag(true);
        return pager;
    }


    /**
     * @return java.lang.Object
     * @throws
     * @Title: getSendTimeConfig
     * @Description:获取配送时间配置方案
     * @author 杨开泰 yangkaitai@izjjf.cn
     * @date 2016/10/12 0012 11:17
     */
    @RequestMapping("/getSendTimeConfig.do")
    @ResponseBody
    public Object getSendTimeConfig() {
        List<SendTimeConfig> list = configService.getAllSendTimeConfig();
        return list;
    }


    /**
     * @param @return
     * @param @throws Exception
     * @return String    返回类型
     * @throws
     * @Title: toSeeOrders
     * @Description:查看订单
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/toSeeOrders.do")
    public String toSeeOrders(HttpServletRequest request, Model model) throws Exception {
        String activeIdStr = request.getParameter("activeId");
        String spId = request.getParameter("spId");
        String storeId = request.getParameter("storeId");
        String pageIndexStr = request.getParameter("pageIndex");
        int pageIndex = 1;
        int pageSize = 10;
        if (!StringUtil.stringIsNullOrEmpty(pageIndexStr)) {
            pageIndex = Integer.parseInt(pageIndexStr);
        }
        if (StringUtil.stringIsNullOrEmpty(activeIdStr) || StringUtil.stringIsNullOrEmpty(spId) || StringUtil.stringIsNullOrEmpty(storeId)) {
            return error("缺少必要参数!", model, request);
        }
        SpVoucherActive spVoucherActive = spVoucherActiveService.selectByPrimaryKey(Integer.parseInt(activeIdStr));
        if (spVoucherActive == null) {
            return error("活动id错误!", model, request);
        }
        model.addAttribute("activeId", activeIdStr);
        model.addAttribute("spId", spId);
        model.addAttribute("storeId", storeId);
        Map<String, Object> map = new HashMap<>();
        map.put("activeId", activeIdStr);
        map.put("spId", spId);
        map.put("storeId", storeId);
        map.put("pageIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        String orderId = request.getParameter("orderId");
        if (!StringUtil.stringIsNullOrEmpty(orderId)) {
            map.put("orderId", orderId.trim());
            model.addAttribute("orderId", orderId.trim());
        }
        Pager<SpOrderInfo> pager = null;
        int ruleType = spVoucherActive.getRuleType().intValue();
        if (ruleType == 2 || ruleType == 9) {
            pager = spVoucherActiveService.getOrders(map);
        } else if (ruleType == 3 || ruleType == 10) {
            pager = spOrderInfoService.getOrdersFromSpOrderActiveMap(map);
        }
        if (pager != null && pager.getList() != null && pager.getList().size() != 0) {
            model.addAttribute("list", pager.getList());
            this.pageUtil(pageIndex, pager.getTotalSize(), pageSize, request, model);
        } else {
            model.addAttribute("list", new ArrayList<SpOrderInfo>());
            this.pageUtil(0, 0, pageSize, request, model);
        }

        return PATH + "promotion-order";
    }


    /**
     * @param @param  request
     * @param @param  model
     * @param @param  condition
     * @param @return
     * @return String    返回类型
     * @throws
     * @Title: watchActive
     * @Description:查看活动详情
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping(value = "/watchActive.do")
    public String watchActive(HttpServletRequest request, Model model, SpVoucherActiveMgRo spVoucherActiveMgRo) throws Exception {
        String type = request.getParameter("type");
        SpVoucherActive active = spVoucherActiveService.selectByPrimaryKey(spVoucherActiveMgRo.getId());
        if (StringUtil.stringIsNullOrEmpty(type) || spVoucherActiveMgRo.getId() == null) {
            return "redirect:/Customer/active/toIndex.do";
        }
        VoucherActiveType enumType = VoucherActiveType.map.get(active.getRuleType());

        //注册送优惠劵 或 提前下单送优惠劵
        if (enumType.getIndex().intValue() == VoucherActiveType.REGISTER_SEND_COUPON.getIndex().intValue()
                || enumType.getIndex().intValue() == VoucherActiveType.ADVANCE_ORDER.getIndex().intValue()) {
            SpVoucherTemp temp = spVoucherActiveService.getSpVoucherTempBySendId(active.getSendId());
            model.addAttribute("temp", temp);
        }

        //满送优惠劵和满送优惠劵+商品
        List<SpVoucherGradedVo> gradeVoList;
        List<StoreMgVo> storeMgVos;
        switch (enumType) {
            case ORDER_SEND__COUPON://满送优惠劵
                gradeVoList = spVoucherGradedService.getListByActiveId(active);
                model.addAttribute("gradeVoList", gradeVoList);
                break;
            case ORDER_SEND_COUPON_PRODUCT://满送优惠劵和商品
                gradeVoList = spVoucherGradedService.getListByActiveId(active);
                model.addAttribute("gradeVoList", gradeVoList);
                break;
            case ACCUMULATE_SEND_COUPON://累积送券
                gradeVoList = spVoucherGradedService.getListByActiveId(active);
                model.addAttribute("gradeVoList", gradeVoList);
                if(active.getRuleScopFlag().intValue()==3){//指定用户
                    storeMgVos = spStoreService.getAcculateStoreList(active);
                    model.addAttribute("storeMgVos",storeMgVos);
                }
                String imgUrl  = active.getImgUrl();
                imgUrl = MatrixUtil.fastfdspreurl+imgUrl;
                model.addAttribute("imgUrl",imgUrl);
            default:
                break;
        }

        //定格
        if (active.getRuleScopFlag() != 0) {
            String[] spGroupIdArr = active.getRuleScop().split(",");
            List<SpGroupVo> spGroupVoList = spGroupService.getSpGroupListByIds(spGroupIdArr);
            model.addAttribute("spGroupVoList", spGroupVoList);
        }

        //赠送商品
        List<String[]> result = new ArrayList<>();
        String sendGoods = active.getSendGoods();
        if (enumType.getIndex().intValue() == VoucherActiveType.ORDER_SEND_COUPON_PRODUCT.getIndex().intValue()//满送优惠劵+商品
                || enumType.getIndex().intValue() == VoucherActiveType.ORDER_REBATE_PRODUCT.getIndex().intValue()//满减+商品
                || enumType.getIndex().intValue() == VoucherActiveType.ORDER_PRODUCT.getIndex().intValue()//满送商品
                || enumType.getIndex().intValue() == VoucherActiveType.BUY_SEND_PRODUCT.getIndex().intValue()//满购商品送商品
                || enumType.getIndex().intValue() == VoucherActiveType.ADVANCE_ORDER.getIndex().intValue()//提前下单送优惠劵
                ) {
            if (!StringUtils.isEmpty(sendGoods)) {
                String[] r = sendGoods.split("&");
                for (String res : r) {
                    result.add(res.split(":"));
                }
                model.addAttribute("result", result);
            }
        }
        String rulePayStrArr[] = StringUtils.split(active.getRulePayStr(), ",");
        String RulePayStr = "";
        for (String rulePayStr : rulePayStrArr) {
            if (!StringUtils.isEmpty(rulePayStr)) {
                RulePayStr += PayMethod.getName(Byte.parseByte(rulePayStr)) + "  ";
            }
        }

        SpVoucherActiveVo spVoucherActiveVo = new SpVoucherActiveVo();
        BeanUtils.copyProperties(active, spVoucherActiveVo);
        //用于处理前台页面计算平台分担比例的问题
        BigDecimal plantHalve = new BigDecimal(Double.toString(active.getPlantHalve()));
        BigDecimal spHalve = new BigDecimal(100).subtract(plantHalve);
        spVoucherActiveVo.setPlantHalveStr(plantHalve.toString());
        spVoucherActiveVo.setSpHalveStr(spHalve.toString());


        if (enumType.getIndex().intValue() == VoucherActiveType.ADVANCE_ORDER.getIndex().intValue()) {
            SendTimeConfig sendTimeConfig = configService.getSendTimeConfigById(spVoucherActiveVo.getTransportTimeType());
            model.addAttribute("sendTimeConfig", sendTimeConfig);
        }

        model.addAttribute("RulePayStr", RulePayStr);
        model.addAttribute("active", spVoucherActiveVo);



        if (type.equals("watch")) {
            return PATH + "promotion-detail";
        } else if (type.equals("toInfo")) {
            return PATH + "promotion-join-detail";
        }
        return null;
    }


    /**
     * @param @param  request
     * @param @param  model
     * @param @return
     * @return String    返回类型
     * @throws
     * @Title: toEdit
     * @Description:跳转到编辑活动页面
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/toEdit.do")
    public String toEdit(HttpServletRequest request, Model model, SpVoucherActiveRo spVoucherActiveRo)
            throws Exception {
        String pageIndexStr = request.getParameter("pageIndex");
        if (StringUtil.stringIsNullOrEmpty(pageIndexStr)) {
            model.addAttribute("pageIndex", 1);
        } else {
            model.addAttribute("pageIndex", pageIndexStr);
        }
        model.addAttribute("edit", true);
        SpVoucherActive spVoucherActive = spVoucherActiveService.selectByPrimaryKey(spVoucherActiveRo.getId());
        if(spVoucherActive.getRuleType().intValue()==14){
            model.addAttribute("showImgUrl",MatrixUtil.fastfdspreurl+spVoucherActive.getImgUrl());
            model.addAttribute("imgUrl",spVoucherActive.getImgUrl());
            spVoucherActive.setImgUrl("");
        }else{
            spVoucherActive.setImgUrl("");
        }
        String ruleStartStr = DateUtil.getSimpleDate(spVoucherActive.getRuleStart());
        model.addAttribute("ruleStartStr", ruleStartStr);
        String ruleEndStr = DateUtil.getSimpleDate(spVoucherActive.getRuleEnd());
        model.addAttribute("ruleEndStr", ruleEndStr);

        // 获取优惠劵
        Byte ruleType = spVoucherActive.getRuleType();
        if (ruleType == 1 || ruleType == 13) {//注册送优惠劵 或 提前下单送优惠劵
            SpVoucherTemp spVoucherTemp = getSpVoucherTemp(spVoucherActive);
            model.addAttribute("spVoucherTemp", JSONUtil.objectToJSONString(spVoucherTemp));
        }

        if (ruleType == 2 || ruleType == 9 || ruleType.intValue()==14) {//满送优惠劵和满送优惠劵+商品,累积送劵活动
            List<SpVoucherGradedVo> spVoucherGradedVos = spVoucherGradedService.getListByActiveId(spVoucherActive);
            String gradeVoObjectStr = JSONUtil.objectToJSONString(spVoucherGradedVos);
            model.addAttribute("gradeVoObjectStr", gradeVoObjectStr);
            if(ruleType.intValue()==14&&spVoucherActive.getRuleScopFlag().intValue()==3){//
                List<StoreMgVo> storeMgVos = spStoreService.getAcculateStoreList(spVoucherActive);
                model.addAttribute("storeMgVos",storeMgVos);
            }
        }

        // 获取定格信息
        if (spVoucherActive.getRuleScopFlag() ==1 || spVoucherActive.getRuleScopFlag() ==2) {//指定定格或指定批发商参与   杨开泰 2016/10/26 0026 11:40
            String[] spGroupIdArr = spVoucherActive.getRuleScop().split(",");
            List<SpGroupVo> spGroupVos = spGroupService.getSpGroupListByIds(spGroupIdArr);
            model.addAttribute("spGroupVos", JSONUtil.objectToJSONString(spGroupVos));
        }

        // 解析活动满减字符串
        if (ruleType == 3 || ruleType == 10) {
            // 如果是满减活动就要把ruleContent数据清掉，防止页面js报错
            model.addAttribute("ruleContent", spVoucherActive.getRuleContent());
            spVoucherActive.setRuleContent("");
        } else {
            spVoucherActive.setRuleContent("");
        }
        spVoucherActive.setUseItemIds("");

        SpVoucherActiveVo spVoucherActiveVo = new SpVoucherActiveVo();
        BeanUtils.copyProperties(spVoucherActive, spVoucherActiveVo);
        //用于处理前台页面计算平台分担比例的问题
        BigDecimal plantHalve = new BigDecimal(Double.toString(spVoucherActive.getPlantHalve()));
        BigDecimal spHalve = new BigDecimal(100).subtract(plantHalve);
        spVoucherActiveVo.setPlantHalveStr(plantHalve.toString());
        spVoucherActiveVo.setSpHalveStr(spHalve.toString());
        String jsonStr = JSONUtil.objectToJSONString(spVoucherActiveVo);
        jsonStr = StringUtils.replace(jsonStr, "\\r\\n", "  ");
        model.addAttribute("spVoucherActive", jsonStr);
        return PATH + "promotion-add";
    }


    /**
     * @param @param  spVoucherActiveRo
     * @param @return 设定文件
     * @return Object    返回类型
     * @throws
     * @Title: canEdit
     * @Description:判断活动是否可以被编辑
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    @RequestMapping("/canEdit.do")
    @ResponseBody
    public Object canEdit(SpVoucherActive spVoucherActive) {
        try {
            if (spVoucherActive.getId() == null) {
                return ResponseUtils.sendMsg(false, "缺少参数id!");
            }
            spVoucherActive = spVoucherActiveService.selectByPrimaryKey(spVoucherActive.getId());
            if (spVoucherActive.getStatus() == 1) {
                return ResponseUtils.sendMsg(false, "活动正在进行中,不能编辑!");
            } else if (spVoucherActive.getStatus() == 0) {
                return ResponseUtils.sendMsg(false, "活动已经结束,不能编辑!");
            }
            return ResponseUtils.sendMsg(true);
        } catch (Exception e) {
            logger.error("", e);
            return ResponseUtils.sendMsg(false, e.toString());
        }
    }

    /**
     * @param @param  request
     * @param @return 设定文件
     * @return Object    返回类型
     * @throws
     * @Title: getActiveStoreMember
     * @Description:查询参与活动的某个批发商下店铺组详情
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    @RequestMapping("/getActiveStoreMember.do")
    @ResponseBody
    public Object getActiveStoreMember(HttpServletRequest request) {
        try {
            String status = request.getParameter("status");//status=1 统计送优惠卷   status=2统计满减
            if (StringUtil.stringIsNullOrEmpty(status)) {
                return ResponseUtils.sendMsg(false, "缺少必要参数status");
            }
            String supplierId = request.getParameter("supplierId");
            if (StringUtil.stringIsNullOrEmpty(supplierId)) {
                return ResponseUtils.sendMsg(false, "缺少必要参数supplierId!");
            }
            String spVoucherActiveId = request.getParameter("spVoucherActiveId");
            if (StringUtil.stringIsNullOrEmpty(spVoucherActiveId)) {
                return ResponseUtils.sendMsg(false, "缺少必要参数spVoucherActiveId!");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("supplierId", supplierId);
            map.put("spVoucherActiveId", spVoucherActiveId);
            List<StoreMgVo> list = new ArrayList<>();
            if (status.equals("1")) {//送优惠劵
                list = spGroupService.getActiveStoreMember(map);
            } else if (status.equals("2")) {
                list = spGroupService.getManjianActiveStore(map);//满减
            } else {
                return ResponseUtils.sendMsg(false, "程序出错,status参数值有误!");
            }
            return ResponseUtils.sendMsg(true, list);
        } catch (Exception e) {
            logger.error("", e);
            return ResponseUtils.sendMsg(false, "程序出错!");
        }
    }


    /**
     * @param @param  spVoucherActiveMgRo
     * @param @return
     * @return Object    返回类型
     * @throws
     * @Title: getActiveSupplierList
     * @Description:查询参与活动的批发商列表,并计算活动成本
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/getActiveSupplierList.do")
    @ResponseBody
    public Object getActiveSupplierList(SpVoucherActiveMgRo spVoucherActiveMgRo) {
        try {
            Pager<SupplierVo> pager = spVoucherActiveService.getActiveSupplierList(spVoucherActiveMgRo);
            pager.setFlag(true);
            return pager;
        } catch (Exception e) {
            logger.error("", e);
            return ResponseUtils.sendMsg(false, "程序出错!");
        }
    }

    /**
     * @param @param  spVoucherActive
     * @param @return 设定文件
     * @return Object    返回类型
     * @throws
     * @Title: updateStatus
     * @Description: 停用
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    @RequestMapping("/updateStatus.do")
    @ResponseBody
    public Object updateStatus(SpVoucherActive spVoucherActive) {
        try {
            if (spVoucherActive.getId() == null) {
                return ResponseUtils.sendMsg(false, "缺少参数id!");
            }
            ModelMsg modelMsg = spVoucherActiveService.updateByPrimaryKeySelective(spVoucherActive);
            if (!modelMsg.isSuccess()) {
                return ResponseUtils.sendMsg(false, modelMsg.getMessage());
            }
            return ResponseUtils.sendMsg(true, modelMsg.getMessage());
        } catch (Exception e) {
            return ResponseUtils.sendMsg(false, "程序出错!");
        }
    }

    /**
     * @param [request, spVoucherActive]
     * @return java.lang.Object
     * @throws
     * @Title: saveActive
     * @Description:
     * @author 杨开泰 yangkaitai@izjjf.cn
     * @date 2016/10/4 0004 10:13
     */
    @SuppressWarnings("deprecation")
    @RequestMapping("/saveActive.do")
    @ResponseBody
    public Object saveActive(HttpServletRequest request, SpVoucherActive spVoucherActive) {
        try {
            // 参数校验
            // 活动名称校验
            if (StringUtil.stringIsNullOrEmpty(spVoucherActive.getRuleName())) {
                return ResponseUtils.sendMsg(false, "没有活动名称!");
            }
            // 活动时间设置
            if (spVoucherActive.getRuleStart() == null || spVoucherActive.getRuleEnd() == null) {
                return ResponseUtils.sendMsg(false, "请设置活动时间!");
            }
                /*if(spVoucherActive.getRuleStart().compareTo(new Date())<=0){
                    return ResponseUtils.sendMsg(false, "活动开始时间最早只能从明天开始!");
                }*/
            if (spVoucherActive.getRuleStart().compareTo(spVoucherActive.getRuleEnd()) == 1) {
                return ResponseUtils.sendMsg(false, "活动开始时间不能大于结束时间!");
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(spVoucherActive.getRuleEnd());
            calendar.set(Calendar.HOUR, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 30);
            Date endDate = calendar.getTime();
            spVoucherActive.setRuleEnd(endDate);
            //判断当前时间是否比活动结束时间大
            Date today = new Date();
            if (today.compareTo(endDate) == 1) {
                return ResponseUtils.sendMsg(false, "活动结束时间不能小于当前时间！");
            }
            // 活动类型校验
            Byte ruleType = spVoucherActive.getRuleType();
            if (ruleType == null || ruleType > 14) {
                return ResponseUtils.sendMsg(false, "活动类型不正确!");
            }
            Map<String, Object> map = new HashMap<>();
            // 根据每一种活动类型,判断是否有相关字段,同时生成相应的字段,并且清空其它不相关的字段
            if (ruleType == VoucherActiveType.REGISTER_SEND_COUPON.getIndex()) {// 注册送优惠劵
                // 必须要有优惠劵id和每天限制优惠劵张数,参与区域不能选择批发商自愿参与
                // 设置优惠劵id
                map = setSendId(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                // 设置定格
                map = setRuleScopFlag(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                spVoucherActive.setRuleContent("");// 满减Json字符串
                spVoucherActive.setRulePayType((byte) 0);// 支付方式
                spVoucherActive.setRulePayStr(",1,2,3,4,5,");
                spVoucherActive.setRuleStartPrice(new BigDecimal("0"));
                spVoucherActive.setRuleSendLimit((byte) 1);
                spVoucherActive.setBuyGoods("");// 购买商品字符串
                spVoucherActive.setSendGoods("");// 赠送商品
                spVoucherActive.setUseItemFlag(CouponProductCondition.ALL_PRODUCT.getIndex());
                spVoucherActive.setUseItemIds("");
                spVoucherActive.setMgItems("");
                spVoucherActive.setTransportTimeType((byte) -1);
                spVoucherActive.setImgUrl("");
                spVoucherActive.setStoreIds("");
            }
            if (ruleType == VoucherActiveType.ORDER_SEND__COUPON.getIndex()) {// 满送优惠劵
                // 必须要有优惠劵id和每天限制优惠劵张数,如果活动是批发商资源参与,必须填写分摊比例,必须要选则定格

                // 设置支付方式
                map = setRulePayStr(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                // 设置定格
                map = setRuleScopFlag(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }

                // 设置优惠劵 必须放在最后面,因为会返回后续需要用到的参数
                map = setSendId(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }

                spVoucherActive.setRuleContent("");// 满减Json字符串
                spVoucherActive.setBuyGoods("");// 购买商品字符串
                spVoucherActive.setSendGoods("");// 赠送商品
                spVoucherActive.setRuleStartPrice(new BigDecimal("0"));
                spVoucherActive.setRuleSendLimit((byte) 1);
                spVoucherActive.setUseItemFlag(CouponProductCondition.ALL_PRODUCT.getIndex());
                spVoucherActive.setUseItemIds("");
                spVoucherActive.setMgItems("");
                spVoucherActive.setTransportTimeType((byte) -1);
                spVoucherActive.setImgUrl("");
                spVoucherActive.setStoreIds("");
            }

            if (ruleType == VoucherActiveType.ORDER_REBATE.getIndex()) {// 满减金额
                // 设置满减字符串
                map = setRuleConten(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                // 定格选择
                map = setRuleScopFlag(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                //指定商品/排除商品设置
                map = setUseItemFlag(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                spVoucherActive.setRuleSendLimit((byte) 0);
                spVoucherActive.setBuyGoods("");// 购买商品字符串
                spVoucherActive.setSendGoods("");// 赠送商品
                spVoucherActive.setSendId(0);// 优惠劵id
                spVoucherActive.setTransportTimeType((byte) -1);
                spVoucherActive.setImgUrl("");
                spVoucherActive.setStoreIds("");
            }

            if (ruleType == VoucherActiveType.ORDER_SEND_COUPON_PRODUCT.getIndex()) {// 满送优惠劵+商品

                // 设置优惠劵张数
                        /*map = setRuleSendLimit(spVoucherActive, request);
                        if (!(boolean) map.get("flag")) {
                            return ResponseUtils.sendMsg(false, map.get("message"));
                        }*/
                // 设置支付方式
                map = setRulePayStr(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                // 设置单笔订单金额
                        /*map = setRuleStartPrice(spVoucherActive, request);
                        if (!(boolean) map.get("flag")) {
                            return ResponseUtils.sendMsg(false, map.get("message"));
                        }*/
                // 赠送商品
                map = setSendGoods(spVoucherActive, request);
                if (map != null) {
                    if (!(boolean) map.get("flag")) {
                        return ResponseUtils.sendMsg(false, map.get("message"));
                    }
                }

                // 设置定格
                map = setRuleScopFlag(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }

                // 设置优惠劵 必须放在最后面,因为会返回后续需要用到的参数
                map = setSendId(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                spVoucherActive.setRuleContent("");// 满减Json字符串
                spVoucherActive.setBuyGoods("");// 购买商品字符串
                //spVoucherActive.setRuleStartPrice(new BigDecimal("0"));
                spVoucherActive.setRuleSendLimit((byte) 1);
                spVoucherActive.setUseItemFlag(CouponProductCondition.ALL_PRODUCT.getIndex());
                spVoucherActive.setUseItemIds("");
                spVoucherActive.setMgItems("");
                spVoucherActive.setTransportTimeType((byte) -1);
                spVoucherActive.setImgUrl("");
                spVoucherActive.setStoreIds("");
            }

            if (ruleType == VoucherActiveType.ORDER_REBATE_PRODUCT.getIndex()) {// 满减金额+商品
                // 设置满减字符串
                map = setRuleConten(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                // 赠送商品
                map = setSendGoods(spVoucherActive, request);
                if (map != null) {
                    if (!(boolean) map.get("flag")) {
                        return ResponseUtils.sendMsg(false, map.get("message"));
                    }
                }
                // 设置定格
                map = setRuleScopFlag(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }

                //指定商品/排除商品设置
                map = setUseItemFlag(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }

                //spVoucherActive.setRulePayType((byte) 0);// 支付方式
                //spVoucherActive.setRulePayStr("ALL");
                spVoucherActive.setRuleSendLimit((byte) 0);
                spVoucherActive.setBuyGoods("");// 购买商品字符串
                spVoucherActive.setSendId(0);// 优惠劵id
                spVoucherActive.setTransportTimeType((byte) -1);
                spVoucherActive.setImgUrl("");
                spVoucherActive.setStoreIds("");
            }

            if (ruleType == VoucherActiveType.ORDER_PRODUCT.getIndex()) {// 满送商品
                // 设置支付方式
                map = setRulePayStr(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                // 设置单笔订单金额
                map = setRuleStartPrice(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                // 赠送商品
                map = setSendGoods(spVoucherActive, request);
                if (map != null) {
                    if (!(boolean) map.get("flag")) {
                        return ResponseUtils.sendMsg(false, map.get("message"));
                    }
                }
                // 设置定格
                map = setRuleScopFlag(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                spVoucherActive.setRuleContent("");// 满减Json字符串
                spVoucherActive.setRuleSendLimit((byte) 0);
                spVoucherActive.setSendId(0);// 优惠劵id
                spVoucherActive.setUseItemFlag(CouponProductCondition.ALL_PRODUCT.getIndex());
                spVoucherActive.setUseItemIds("");
                spVoucherActive.setMgItems("");
                spVoucherActive.setTransportTimeType((byte) -1);
                spVoucherActive.setImgUrl("");
                spVoucherActive.setStoreIds("");
            }

            if (ruleType == VoucherActiveType.BUY_SEND_PRODUCT.getIndex()) {// 满购商品送商品
                // 设置购买商品
                map = setBuyGoodsStr(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                // 设置支付方式
                map = setRulePayStr(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                // 设置单笔订单金额
                        /*map = setRuleStartPrice(spVoucherActive, request);
                        if (!(boolean) map.get("flag")) {
                            return ResponseUtils.sendMsg(false, map.get("message"));
                        }*/
                // 赠送商品
                map = setSendGoods(spVoucherActive, request);
                if (map != null) {
                    if (!(boolean) map.get("flag")) {
                        return ResponseUtils.sendMsg(false, map.get("message"));
                    }
                }
                // 设置定格
                map = setRuleScopFlag(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                spVoucherActive.setRuleContent("");// 满减Json字符串
                spVoucherActive.setRuleSendLimit((byte) 0);
                spVoucherActive.setSendId(0);// 优惠劵id
                spVoucherActive.setUseItemFlag(CouponProductCondition.ALL_PRODUCT.getIndex());
                spVoucherActive.setUseItemIds("");
                spVoucherActive.setMgItems("");
                spVoucherActive.setTransportTimeType((byte) -1);
                spVoucherActive.setImgUrl("");
                spVoucherActive.setStoreIds("");
            }

            if (ruleType == VoucherActiveType.ADVANCE_ORDER.getIndex()) {//提前下单送优惠劵活动
                // 必须要有优惠劵id和每天限制优惠劵张数,参与区域不能选择批发商自愿参与
                // 设置优惠劵id
                map = setSendId(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                map = setRulePayStr(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                // 设置定格
                map = setRuleScopFlag(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                spVoucherActive.setRuleContent("");// 满减Json字符串
                spVoucherActive.setRulePayType((byte) 0);// 支付方式
                //spVoucherActive.setRulePayStr(",1,2,3,4,5,");
                spVoucherActive.setRuleStartPrice(new BigDecimal("0"));
                spVoucherActive.setBuyGoods("");// 购买商品字符串
                spVoucherActive.setSendGoods("");// 赠送商品
                spVoucherActive.setUseItemFlag(CouponProductCondition.ALL_PRODUCT.getIndex());
                spVoucherActive.setUseItemIds("");
                spVoucherActive.setMgItems("");
                spVoucherActive.setImgUrl("");
                spVoucherActive.setStoreIds("");
            }
            if (ruleType == VoucherActiveType.ACCUMULATE_SEND_COUPON.getIndex()) {//累积送劵
                //类级送劵活动用自己的设置定格方法
                map = setAccumulateRuleScopFlag(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                //设置支付方式
                map = setRulePayStr(spVoucherActive, request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }
                //设置优惠劵必须放在最后面
                map = setSendId(spVoucherActive,request);
                if (!(boolean) map.get("flag")) {
                    return ResponseUtils.sendMsg(false, map.get("message"));
                }

                spVoucherActive.setRuleContent("");// 满减Json字符串
                //spVoucherActive.setRulePayType((byte) 0);// 支付方式
                //spVoucherActive.setRulePayStr(",1,2,3,4,5,");
                spVoucherActive.setRuleStartPrice(new BigDecimal("0"));
                spVoucherActive.setRuleSendLimit((byte) 1);
                spVoucherActive.setBuyGoods("");// 购买商品字符串
                spVoucherActive.setSendGoods("");// 赠送商品
                spVoucherActive.setUseItemFlag(CouponProductCondition.ALL_PRODUCT.getIndex());
                spVoucherActive.setUseItemIds("");
                spVoucherActive.setMgItems("");
                spVoucherActive.setTransportTimeType((byte) -1);
            }
            if (spVoucherActive.getId() == null) {
                spVoucherActive.setAddTime(new Date());
            }
            spVoucherActive.setUpdateTime(new Date());

            map.put("spVoucherActive", spVoucherActive);
            spVoucherActiveService.save(map);
            return ResponseUtils.sendMsg(true, "保存成功!");
        } catch (Exception e) {
            logger.error("", e);
            return ResponseUtils.sendMsg(false, e.toString());
        }
    }


    /**
     * @param @param  spVoucherActive
     * @param @param  request
     * @param @return 设定文件
     * @return Map<String,Object>    返回类型
     * @throws Exception
     * @throws
     * @Title: setUseItemFlag
     * @Description:排除商品/指定商品设置
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    private Map<String, Object> setUseItemFlag(SpVoucherActive spVoucherActive, HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", false);
        if (spVoucherActive.getUseItemFlag() == null) {
            map.put("message", "缺少指定商品类别信息");
            return map;
        }
        if (spVoucherActive.getUseItemFlag().intValue() == CouponProductCondition.ASSIGN_PRODUCT.getIndex().intValue()) {
            //指定商品
            String[] productArr = request.getParameterValues("productArr");
            String mgItems = "";
            if (productArr == null || productArr.length == 0) {
                map.put("message", "选中指定商品的时候,必须添加参与的商品");
                return map;
            }
            List<String> inGoodsTypeTemp = new ArrayList<>();// 参与
            List<String> outGoodsTypeTemp = new ArrayList<>();// 参与
            for (int i = 0; i < productArr.length; i++) {
                String var = productArr[i];
                String[] varArr = var.split("@");
                mgItems += var + ";";
                if (varArr[0].equals("item")) {
                    if (varArr[3].equals("1")) {// 不参与
                        outGoodsTypeTemp.add(varArr[1]);
                    } else {
                        inGoodsTypeTemp.add(varArr[1]);
                    }
                } else {
                    ItemBase itemBase = new ItemBase();
                    itemBase.setCateId(Integer.parseInt(varArr[1]));
                    List<String> resultList = itemBaseService.getSpVoucherItemBaseList(itemBase);
                    inGoodsTypeTemp.addAll(resultList);
                }
            }
            outGoodsTypeTemp.forEach(inGoodsTypeTemp::remove);
            String goodsStr = inGoodsTypeTemp.toString();
            goodsStr = goodsStr.replace(" ", "");
            goodsStr = goodsStr.replace("[", "");
            goodsStr = goodsStr.replace("]", "");
            goodsStr = "," + goodsStr + ",";
            spVoucherActive.setUseItemIds(goodsStr);
            mgItems = mgItems.substring(0, mgItems.lastIndexOf(";"));
            spVoucherActive.setMgItems(mgItems);
        } else {
            if (spVoucherActive.getUseItemFlag().intValue() == CouponProductCondition.EXCLUDE_PRODUCT.getIndex().intValue()) {
                //排除商品
                String[] itemArr = request.getParameterValues("itemArr");
                String mgItems = "";
                if (itemArr == null || itemArr.length == 0) {
                    map.put("message", "选中排除商品的时候,必须添加不参与的商品");
                    return map;
                }
                List<String> goodsStrTemp = new ArrayList<>();
                for (int i = 0; i < itemArr.length; i++) {
                    String var = itemArr[i];
                    String[] varArr = var.split("@");
                    mgItems += var + ";";
                    if (varArr[0].equals("item")) {
                        goodsStrTemp.add(varArr[1]);
                    } else {
                        ItemBase itemBase = new ItemBase();
                        itemBase.setCateId(Integer.parseInt(varArr[1]));
                        List<String> resultList = itemBaseService.getSpVoucherItemBaseList(itemBase);
                        goodsStrTemp.addAll(resultList);
                    }
                }
                String goodsStr = goodsStrTemp.toString();
                goodsStr = goodsStr.replace(" ", "");
                goodsStr = goodsStr.replace("[", "");
                goodsStr = goodsStr.replace("]", "");
                goodsStr = "," + goodsStr + ",";
                spVoucherActive.setUseItemIds(goodsStr);
                mgItems = mgItems.substring(0, mgItems.lastIndexOf(";"));
                spVoucherActive.setMgItems(mgItems);
            }
        }
        map.put("flag", true);
        return map;
    }


    /**
     * @param @param  spVoucherActive
     * @param @param  request
     * @param @return 设定文件
     * @return Map<String,Object>    返回类型
     * @throws
     * @Title: setBuyGoodsStr
     * @Description:设置购买商品信息
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    private Map<String, Object> setBuyGoodsStr(SpVoucherActive spVoucherActive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", false);
        String buyGoodsItemBaseId = request.getParameter("buyGoodsItemBaseId");
        if (StringUtil.stringIsNullOrEmpty(buyGoodsItemBaseId)) {
            map.put("message", "购买商品id为空!");
            return map;
        }
        String buyGoodName = request.getParameter("buyGoodName");
        if (StringUtil.stringIsNullOrEmpty(buyGoodName)) {
            map.put("message", "购买商品名不能为空!");
            return map;
        }
        String buyGoodsNum = request.getParameter("buyGoodsNum");
        if (StringUtil.stringIsNullOrEmpty(buyGoodsNum)) {
            map.put("message", "购买商品数量不能为空!");
            return map;
        }
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        Matcher matcher = pattern.matcher(buyGoodsNum);
        if (!matcher.matches()) {
            map.put("message", "购买商品数量必须为大于0的正整数!");
            return map;
        }
        if (Integer.parseInt(buyGoodsNum) > 10000) {
            map.put("message", "购买商品数量不能大于10000的正整数!");
            return map;
        }
        String buyGoodStr = buyGoodsItemBaseId + ":" + buyGoodName + ":" + buyGoodsNum;
        spVoucherActive.setBuyGoods(buyGoodStr);
        map.put("flag", true);
        return map;
    }

    /**
     * @param @param  spVoucherActive
     * @param @param  request
     * @param @return 设定文件
     * @return Map<String,Object>    返回类型
     * @throws
     * @Title: setSendGoods
     * @Description:设置活动赠送商品
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    private Map<String, Object> setSendGoods(SpVoucherActive spVoucherActive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", false);
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        String[] sendGoodsIdArr = request.getParameterValues("sendGoodsIdArr");
        String[] sendGoodsNameArr = request.getParameterValues("sendGoodsNameArr");
        String[] sendGoodsNumArr = request.getParameterValues("sendGoodsNumArr");
        String[] sendGoodsTotalNumArr = request.getParameterValues("sendGoodsTotalNumArr");
        if (sendGoodsIdArr == null || sendGoodsIdArr.length == 0 || sendGoodsNameArr == null
                || sendGoodsNameArr.length == 0 || sendGoodsNumArr == null || sendGoodsNumArr.length == 0
                || sendGoodsTotalNumArr == null || sendGoodsTotalNumArr.length == 0) {
            // map.put("message", "赠送商品参数有误!");
            return null;// 认为没有填赠送商品
        }
        if (sendGoodsIdArr.length != sendGoodsNameArr.length || sendGoodsIdArr.length != sendGoodsNumArr.length
                || sendGoodsIdArr.length != sendGoodsTotalNumArr.length) {
            // map.put("message", "赠送商品参数有误!");
            return null;
        }
        // 设置赠送商品字符串
        String sendGoodStr = "";
        for (int i = 0; i < sendGoodsIdArr.length; i++) {
            String sendId = sendGoodsIdArr[i];
            String sendGoodsName = sendGoodsNameArr[i];
            if (StringUtil.stringIsNullOrEmpty(sendId) || StringUtil.stringIsNullOrEmpty(sendGoodsName)) {
                map.put("message", "赠送商品参数有误!");
                return map;
            }
            String sendGoodsNum = sendGoodsNumArr[i];
            String sendGoodsTotalNum = sendGoodsTotalNumArr[i];
            Matcher matcher = pattern.matcher(sendGoodsNum);
            if (!matcher.matches()) {
                map.put("message", "赠送商品错误&必须是大于0的正整数!&sendGoodsNumArr&" + i);
                return map;
            }
            if (Integer.parseInt(sendGoodsNum) > 10000) {
                map.put("message", "赠送商品错误&不能大于10000!&sendGoodsNumArr&" + i);
                return map;
            }
            matcher = pattern.matcher(sendGoodsTotalNum);
            if (!matcher.matches()) {
                map.put("message", "赠送商品错误&必须是大于0的正整数!&sendGoodsTotalNumArr&" + i);
                return map;
            }
            if (Integer.parseInt(sendGoodsTotalNum) > 10000) {
                map.put("message", "赠送商品错误&不能大于10000!&sendGoodsNumArr&" + i);
                return map;
            }
            if (Integer.parseInt(sendGoodsTotalNum) % Integer.parseInt(sendGoodsNum) != 0) {
                map.put("message", "赠送商品错误&赠送总量应该是数量的整数倍!&sendGoodsTotalNumArr&" + i);
                return map;
            }
            sendGoodStr += sendId + ":" + sendGoodsName + ":" + sendGoodsNum + ":" + sendGoodsTotalNum + "&";
        }
        sendGoodStr = sendGoodStr.substring(0, sendGoodStr.lastIndexOf("&"));
        spVoucherActive.setSendGoods(sendGoodStr);
        map.put("flag", true);
        return map;
    }

    /**
     * @param @param  spVoucherActive
     * @param @param  request
     * @param @return 设定文件
     * @return Map<String,Object>    返回类型
     * @throws
     * @Title: setRuleConten
     * @Description:设置满减字符串
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    private Map<String, Object> setRuleConten(SpVoucherActive spVoucherActive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", false);
        String weixingMethod = request.getParameter("weixingMethod");
        String huodaoMethod = request.getParameter("huodaoMethod");
        String zhifubaoMethod = request.getParameter("zhifubaoMethod");
        String kuaiqianMethod = request.getParameter("kuaiqianMethod");
        String qianbaoMethod = request.getParameter("qianbaoMethod");
        if (StringUtil.stringIsNullOrEmpty(weixingMethod) && StringUtil.stringIsNullOrEmpty(huodaoMethod)
                && StringUtil.stringIsNullOrEmpty(zhifubaoMethod) && StringUtil.stringIsNullOrEmpty(kuaiqianMethod)
                && StringUtil.stringIsNullOrEmpty(qianbaoMethod)) {
            map.put("message", "请填写满减规则!");
            return map;
        }
        // 判断是否按要求填写规则,并组装数据
        String ruleContent = "";
        String rulePayStr = ",";
        Integer count = 0;
        Integer minRuleStarPrice = 0;
        if (!StringUtil.stringIsNullOrEmpty(weixingMethod)) {
            map = PromotionUtil.ruleContenToJsonStr(ruleContent, weixingMethod, 4);// 微信付款
            if (!(boolean) map.get("flag")) {
                map.put("message", "微信支付格式不正确,请按操作提示填写!");
                return map;
            }
            ruleContent = (String) map.get("ruleContent");
            rulePayStr += "4,";
            Integer tempMin = Integer.parseInt((String) map.get("minStr"));
            if (minRuleStarPrice == 0) {
                minRuleStarPrice = tempMin;
            } else if (tempMin < minRuleStarPrice) {
                minRuleStarPrice = tempMin;
            }
            count++;
        }
        if (!StringUtil.stringIsNullOrEmpty(huodaoMethod)) {// 货到付款
            map = PromotionUtil.ruleContenToJsonStr(ruleContent, huodaoMethod, 2);
            if (!(boolean) map.get("flag")) {
                map.put("message", "货到付款格式不正确,请按操作提示填写!");
                return map;
            }
            ruleContent = (String) map.get("ruleContent");
            rulePayStr += "2,";
            Integer tempMin = Integer.parseInt((String) map.get("minStr"));
            if (minRuleStarPrice == 0) {
                minRuleStarPrice = tempMin;
            } else if (tempMin < minRuleStarPrice) {
                minRuleStarPrice = tempMin;
            }
            count++;
        }
        if (!StringUtil.stringIsNullOrEmpty(zhifubaoMethod)) {
            map = PromotionUtil.ruleContenToJsonStr(ruleContent, zhifubaoMethod, 3);// 支付宝
            if (!(boolean) map.get("flag")) {
                map.put("message", "支付宝支付格式不正确,请按操作提示填写!");
                return map;
            }
            ruleContent = (String) map.get("ruleContent");
            rulePayStr += "3,";
            Integer tempMin = Integer.parseInt((String) map.get("minStr"));
            if (minRuleStarPrice == 0) {
                minRuleStarPrice = tempMin;
            } else if (tempMin < minRuleStarPrice) {
                minRuleStarPrice = tempMin;
            }
            count++;
        }
        if (!StringUtil.stringIsNullOrEmpty(kuaiqianMethod)) {
            map = PromotionUtil.ruleContenToJsonStr(ruleContent, kuaiqianMethod, 1);// 快钱支付
            if (!(boolean) map.get("flag")) {
                map.put("message", "快钱支付格式不正确,请按操作提示填写!");
                return map;
            }
            ruleContent = (String) map.get("ruleContent");
            rulePayStr += "1,";
            Integer tempMin = Integer.parseInt((String) map.get("minStr"));
            if (minRuleStarPrice == 0) {
                minRuleStarPrice = tempMin;
            } else if (tempMin < minRuleStarPrice) {
                minRuleStarPrice = tempMin;
            }
            count++;
        }
        if (!StringUtil.stringIsNullOrEmpty(qianbaoMethod)) {
            map = PromotionUtil.ruleContenToJsonStr(ruleContent, qianbaoMethod, 5);// 钱包支付
            if (!(boolean) map.get("flag")) {
                map.put("message", "钱包支付格式不正确,请按操作提示填写!");
                return map;
            }
            ruleContent = (String) map.get("ruleContent");
            rulePayStr += "5,";
            Integer tempMin = Integer.parseInt((String) map.get("minStr"));
            if (minRuleStarPrice == 0) {
                minRuleStarPrice = tempMin;
            } else if (tempMin < minRuleStarPrice) {
                minRuleStarPrice = tempMin;
            }
            count++;
        }
        if (count == 5) {
            spVoucherActive.setRulePayType((byte) 0);
        } else {
            spVoucherActive.setRulePayType((byte) 1);
        }
        spVoucherActive.setRuleStartPrice(new BigDecimal(minRuleStarPrice));
        spVoucherActive.setRulePayStr(rulePayStr);
        ruleContent = ruleContent.substring(0, ruleContent.lastIndexOf("&"));
        spVoucherActive.setRuleContent(ruleContent);
        map.put("flag", true);
        return map;
    }

    /**
     * @param @param  spVoucherActive
     * @param @param  request
     * @param @return 设定文件
     * @return Map<String,Object>    返回类型
     * @throws
     * @Title: setRulePayStr
     * @Description:设置支付方式
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    private Map<String, Object> setRulePayStr(SpVoucherActive spVoucherActive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", false);
        String[] rulePayStrArr = request.getParameterValues("rulePayStrArr");
        if (rulePayStrArr == null || rulePayStrArr.length == 0) {
            map.put("message", "请选择支付方式!");
            return map;
        } else {
            String rulePayStr = Arrays.toString(rulePayStrArr);
            rulePayStr = rulePayStr.substring(1, rulePayStr.lastIndexOf("]")).replaceAll(" ", "");
            rulePayStr = "," + rulePayStr;
            rulePayStr += ",";
            spVoucherActive.setRulePayStr(rulePayStr);
        }
        // 根据rulePayStrArr长度设置rulePayType的值(目前有4种支付方式,如果rulePayStrArr长度为4就认为rulePayType=0)
        if (rulePayStrArr.length == 4) {
            spVoucherActive.setRulePayType((byte) 0);// 所有方式
        } else {
            spVoucherActive.setRulePayType((byte) 1);// 部分支持
        }
        map.put("flag", true);
        return map;
    }

    /**
     * @param @param  spVoucherActive
     * @param @param  request
     * @param @return 设定文件
     * @return Map<String,Object>    返回类型
     * @throws
     * @Title: setRuleStartPrice
     * @Description:设置单笔订单金额
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    private Map<String, Object> setRuleStartPrice(SpVoucherActive spVoucherActive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", false);
        String ruleStartPriceStr = request.getParameter("ruleStartPriceStr");
        if (StringUtil.stringIsNullOrEmpty(ruleStartPriceStr)) {
            map.put("message", "请填写单笔订单金额!");
            return map;
        }
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$");
        Matcher matcher = pattern.matcher(ruleStartPriceStr);
        if (!matcher.matches()) {
            map.put("message", "单笔订单金额格式不对,必须为大于0的正数且最多保留2位小数!");
            return map;
        }
        // 对订单金额数字大小做控制 不能大于100000
        if (Integer.parseInt(ruleStartPriceStr) > 100000) {
            map.put("message", "单笔订单金额不能大于100000!");
            return map;
        }
        spVoucherActive.setRuleStartPrice(new BigDecimal(ruleStartPriceStr));
        map.put("flag", true);
        return map;
    }

    /**
     * @param @param  spVoucherActive
     * @param @param  request
     * @param @return 设定文件
     * @return Map<String,Object>    返回类型
     * @throws
     * @Title: setRuleScopFlag
     * @Description:设置定格
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    private Map<String, Object> setRuleScopFlag(SpVoucherActive spVoucherActive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", false);
        if (spVoucherActive.getRuleScopFlag() == null) {
            map.put("message", "请选择参与区域!");
            return map;
        }
        if (spVoucherActive.getRuleScopFlag() == 2) {// 批发商参与
            Byte ruleType = spVoucherActive.getRuleType();
            if (ruleType != 11 && ruleType != 12) {
                String zjHalveStr = request.getParameter("zjHalveStr");
                String pfHalveStr = request.getParameter("pfHalveStr");
                if (StringUtil.stringIsNullOrEmpty(zjHalveStr) || StringUtil.stringIsNullOrEmpty(pfHalveStr)) {
                    map.put("message", "请填写费用分摊比例!");
                    return map;
                }
                Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$");
                Matcher matcher = pattern.matcher(zjHalveStr);
                if (!matcher.matches()) {
                    map.put("message", "转角承担比例格式不对,必须为正数,且最多保留2位小数!");
                    return map;
                }
                matcher = pattern.matcher(pfHalveStr);
                if (!matcher.matches()) {
                    map.put("message", "转角承担比例格式不对,必须为正数,且最多保留2位小数!");
                    return map;
                }
                Double zjHalve = Double.parseDouble(zjHalveStr);
                Double pfHalve = Double.parseDouble(pfHalveStr);
                if (zjHalve > 100 || pfHalve > 100) {
                    map.put("message", "费用比例设置不能超过100!");
                    return map;
                }
                if (zjHalve + pfHalve != 100) {
                    map.put("message", "转角和批发商承担费用比例之和应该等于100!");
                    return map;
                }
                spVoucherActive.setPlantHalve(zjHalve);
            } else {
                spVoucherActive.setPlantHalve(100d);
            }
            // }
        } else {// 默认平台比例100%
            spVoucherActive.setPlantHalve(100d);
        }

        if (spVoucherActive.getRuleScopFlag() != 0 && spVoucherActive.getRuleScopFlag()!=3) {// 设置定格
            String[] groupIdArr = request.getParameterValues("spGroupIdArr");
            if (groupIdArr == null || groupIdArr.length == 0) {
                map.put("message", "请选择要参与活动的定格!");
                return map;
            }
            String groupIdArrStr = Arrays.toString(groupIdArr);
            groupIdArrStr = groupIdArrStr.substring(1, groupIdArrStr.lastIndexOf("]"));
            groupIdArrStr = groupIdArrStr.replaceAll(" ", "");
            spVoucherActive.setRuleScop(","+groupIdArrStr+",");
        } else {
            spVoucherActive.setRuleScop("ALL");
        }
        map.put("flag", true);
        return map;
    }

    /**
     * @param
     * @return
     * @throws
     * @Title: 设置累积送劵活动类型的定格
     * @Description:
     * @author 杨开泰 yangkaitai@izjjf.cn
     * @date 2016/10/18 0018 18:21
     */
    private Map<String, Object> setAccumulateRuleScopFlag(SpVoucherActive spVoucherActive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", false);
        if (spVoucherActive.getRuleScopFlag() == null) {
            map.put("message", "请选择参与区域!");
            return map;
        }
        SpVoucherActive oldActive = null;
        if(spVoucherActive.getId()!=null){
            try {
                oldActive = spVoucherActiveService.getSpVoucherActiveVoById(spVoucherActive.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //查询在时间段内有没有累积送的活动
        List<SpVoucherActive> list = spVoucherActiveService.getAccumuLateActive(spVoucherActive);
        if(oldActive!=null){//如果是编辑,则list中要去掉该对象   杨开泰 2016/10/24 0024 16:59
            SpVoucherActive finalOldActive = oldActive;
            list.removeIf(s->s.getId().intValue()== finalOldActive.getId().intValue());
        }
        if (spVoucherActive.getRuleScopFlag() == 0) {//全部定格
            if (list != null && list.size() != 0) {
                String activeIds = "";
                if(list.size()!=0){
                    for (SpVoucherActive active :
                            list) {
                        activeIds += active.getId() + "  ";
                    }
                    map.put("message", "已经有累积送活动存在,活动编号为:" + activeIds);
                    return map;
                }
            } else {
                spVoucherActive.setRuleScop("ALL");
            }
        }
        if (spVoucherActive.getRuleScopFlag() == 1) {//指定定格
            String[] groupIdArr = request.getParameterValues("spGroupIdArr");
            if (groupIdArr == null || groupIdArr.length == 0) {
                map.put("message", "请选择要参与活动的定格!");
                return map;
            }
            String groupIdArrStr = Arrays.toString(groupIdArr);
            groupIdArrStr = groupIdArrStr.substring(1, groupIdArrStr.lastIndexOf("]"));
            groupIdArrStr = groupIdArrStr.replaceAll(" ", "");
            groupIdArrStr = "," + groupIdArrStr + ",";
            if (list != null && list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    SpVoucherActive active = list.get(i);
                    if (active.getRuleScopFlag().intValue() == 0) {//全部定格累积送活动
                        map.put("message", "已经存在全部定格累积送活动,活动编号为:" + list.get(i).getId());
                        return map;
                    }
                    if (active.getRuleScopFlag().intValue() == 1) {//指定定格累积送活动
                        String ruleScop = active.getRuleScop();
                        for (String groupId :
                                groupIdArr) {
                            if (ruleScop.contains("," + groupId + ",")) {
                                map.put("message", "已经存在指定定累积送活动,活动编号为:" + list.get(i).getId() + "  冲突定格id为:" + groupId);
                                return map;
                            }
                        }
                    }
                    if (active.getRuleScopFlag().intValue() == 3) {//指定用户
                        String storeIds = active.getStoreIds();
                        storeIds = StringUtils.substring(storeIds, 1, storeIds.length() - 1);
                        String[] storeIdArr = storeIds.split(",");
                        List<String> spGroupIds = spVoucherActiveService.getAssignStoreAccumuLateGroupIds(storeIdArr);
                        for (String groupId :
                                spGroupIds) {
                            if (groupIdArrStr.contains("," + groupId + ",")) {
                                map.put("message", "已经存在冲突的指定用户累积送活动(暂时提示这些!)");
                                return map;
                            }
                        }
                    }
                }
            }
            spVoucherActive.setRuleScop(groupIdArrStr);
        }
        if (spVoucherActive.getRuleScopFlag() == 3) {//指定用户
            String[] storeIdArr = request.getParameterValues("storeIdArr");
            if (storeIdArr == null || storeIdArr.length == 0) {
                map.put("message", "请指定用户!");
                return map;
            }
            if (list != null && list.size() != 0) {
                for (SpVoucherActive active :
                        list) {
                    if (active.getRuleScopFlag().intValue() == 0) {//全部定格累积送活动
                        map.put("message", "已经存在全部定格累积送活动,活动编号为:" + active.getId());
                        return map;
                    }
                    if (active.getRuleScopFlag().intValue() == 1) {//指定定格累积送活动
                        List<String> spGroupIds = spVoucherActiveService.getAssignStoreAccumuLateGroupIds(storeIdArr);
                        for (String groupId :
                                spGroupIds) {
                            if (active.getRuleScop().contains("," + groupId + ",")) {
                                map.put("message", "已经存在指定定累积送活动,活动编号为:" + active.getId() + " (暂时这么提示)");
                                return map;
                            }
                        }
                    }
                    if (active.getRuleScopFlag().intValue() == 3) {//指定用户累积送活动
                        for (String storeId :
                                storeIdArr) {
                            if (active.getStoreIds().contains(","+storeId+",")) {
                                map.put("message", "已经存在指定用户累积送活动,活动编号为:" + active.getId() + " 和store id 为:" + storeId + "冲突!");
                                return map;
                            }
                        }
                    }
                }
            }
            String storeIdArrStr = Arrays.toString(storeIdArr);
            storeIdArrStr = storeIdArrStr.substring(1,storeIdArrStr.lastIndexOf("]"));
            storeIdArrStr = storeIdArrStr.replaceAll(" ","");
            spVoucherActive.setStoreIds(","+storeIdArrStr+",");
        }
        spVoucherActive.setPlantHalve(Double.parseDouble("100"));
        map.put("flag",true);
        return map;
    }

    /**
     * @param @param  spVoucherActive
     * @param @param  request
     * @param @return 设定文件
     * @return Map<String,Object>    返回类型
     * @throws
     * @Title: setRuleSendLimit
     * @Description:设置每天发送优惠劵张数的限制
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    private Map<String, Object> setRuleSendLimit(SpVoucherActive spVoucherActive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", false);
        if (spVoucherActive.getRuleSendLimit() == null) {
            map.put("message", "必须填写每天限制张数!");
            return map;
        }
        map.put("flag", true);
        return map;
    }

    /**
     * @param @param  spVoucherActive
     * @param @param  request
     * @param @return 设定文件
     * @return Map<String,Object>    返回类型
     * @throws
     * @Title: setSendId
     * @Description:用于设置要赠送的优惠劵id
     * @author 杨开泰   yangkaitai@izjjf.cn
     */
    private Map<String, Object> setSendId(SpVoucherActive spVoucherActive, HttpServletRequest request) {
        /**
         * 如果是注册送优惠劵,只设置一张优惠劵
         * 满送优惠劵和满送优惠劵+商品可以设置多张优惠劵
         */
        Map<String, Object> map = new HashMap<>();
        map.put("flag", false);
        if (spVoucherActive.getRuleType() == 1) {//注册送优惠劵
            String voucherTempIdArr = request.getParameter("voucherTempIdArr");
            if (StringUtil.stringIsNullOrEmpty(voucherTempIdArr)) {
                map.put("message", "请选择优惠劵!");
                return map;
            }
            spVoucherActive.setSendId(Integer.parseInt(voucherTempIdArr));
        } else if (spVoucherActive.getRuleType().intValue() == 13)//提前下单送优惠劵
        {
            String voucherTempIdArr = request.getParameter("voucherTempIdArr");
            if (StringUtil.stringIsNullOrEmpty(voucherTempIdArr)) {
                map.put("message", "请选择优惠劵!");
                return map;
            }
            spVoucherActive.setSendId(Integer.parseInt(voucherTempIdArr));
            if (spVoucherActive.getRuleSendLimit() == null || spVoucherActive.getRuleSendLimit().intValue() == 0) {
                map.put("message", "请填写每天限制张数!");
                return map;
            }
        }else if(spVoucherActive.getRuleType().intValue()==14){//累积送劵活动
            String[] voucherTempIdArr = request.getParameterValues("voucherTempIdArr");//优惠劵id数组
            String[] startPriceArr = request.getParameterValues("startPriceArr");//满多少送优惠劵数组
            if(voucherTempIdArr==null||voucherTempIdArr.length==0||startPriceArr==null||startPriceArr.length==0){
                map.put("message", "优惠劵相关数据有误!");
                return map;
            }
            if(voucherTempIdArr.length!=startPriceArr.length){
                map.put("message", "优惠劵相关数据有误!");
                return map;
            }
            map.put("voucherTempIdArr", voucherTempIdArr);
            map.put("startPriceArr", startPriceArr);
        }else {
            String[] voucherTempIdArr = request.getParameterValues("voucherTempIdArr");//优惠劵id数组
            String[] startPriceArr = request.getParameterValues("startPriceArr");//满多少送优惠劵数组
            String[] sendLimitArr = request.getParameterValues("sendLimitArr");//每天限制张数数组
            if (voucherTempIdArr == null || voucherTempIdArr.length == 0
                    || startPriceArr == null || startPriceArr.length == 0
                    || sendLimitArr == null || sendLimitArr.length == 0) {
                map.put("message", "优惠劵相关数据有误!");
                return map;
            }
            if (voucherTempIdArr.length != startPriceArr.length && voucherTempIdArr.length != sendLimitArr.length) {
                map.put("message", "优惠劵相关数据有误!");
                return map;
            }
            //TODO 校验格式
            map.put("voucherTempIdArr", voucherTempIdArr);
            map.put("startPriceArr", startPriceArr);
            map.put("sendLimitArr", sendLimitArr);

            if (spVoucherActive.getRuleType().intValue() == 9) {//满送优惠劵+商品
                //如果是满送优惠劵+商品，startPrice，应该是优惠劵梯度的最小值
                int minPrice = 0;
                for (int i = 0; i < startPriceArr.length; i++) {
                    if (i == 0) {
                        minPrice = Integer.parseInt(startPriceArr[i]);
                        continue;
                    }
                    if (Integer.parseInt(startPriceArr[i]) < minPrice) {
                        minPrice = Integer.parseInt(startPriceArr[i]);
                    }
                }
                //map.put("minPrice", minPrice);
                spVoucherActive.setRuleStartPrice(new BigDecimal(minPrice));
            } else {
                spVoucherActive.setRuleStartPrice(new BigDecimal("0"));
            }
        }
        map.put("flag", true);
        return map;
    }

    /**
     * @param @param  request
     * @param @param  model
     * @param @return
     * @return String    返回类型
     * @throws
     * @Title: toIndex
     * @Description:跳转到活动管理页面
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/toIndex.do")
    private String toIndex(HttpServletRequest request, Model model, SpVoucherActiveRo spVoucherActiveRo)
            throws Exception {
        String pageIndexStr = request.getParameter("pageIndex");
        if (StringUtil.stringIsNullOrEmpty(pageIndexStr)) {
            model.addAttribute("pageIndex", 1);
        } else {
            model.addAttribute("pageIndex", pageIndexStr);
        }
        String searchKey = request.getParameter("searchKey");
        if (!StringUtil.stringIsNullOrEmpty(searchKey)) {
            spVoucherActiveRo.setSearchKey(searchKey.trim());
            model.addAttribute("searchKey", searchKey.trim());
        }
        if (spVoucherActiveRo.getRuleStart() != null) {
            model.addAttribute("ruleStart", DateUtil.getSimpleDate(spVoucherActiveRo.getRuleStart()));
        }
        if (spVoucherActiveRo.getRuleEnd() != null) {
            Date endDate = spVoucherActiveRo.getRuleEnd();
            String ruleEndStr = request.getParameter("ruleEnd");
            endDate = DateUtils.addDays(endDate, 1);
            spVoucherActiveRo.setRuleEnd(endDate);
            model.addAttribute("ruleEnd", ruleEndStr);
        }
        if (spVoucherActiveRo.getRuleType() != null) {
            model.addAttribute("ruleType", spVoucherActiveRo.getRuleType());
        } else {
            model.addAttribute("ruleType", "-1");
        }
        if (spVoucherActiveRo.getIsVoluntary()) {
            model.addAttribute("isVoluntary", 1);
        }
        Pager<SpVoucherActiveVo> pager = spVoucherActiveService.getSpVoucherActiveList(spVoucherActiveRo);
        List<SpVoucherActiveVo> list = pager.getList();
        model.addAttribute("spVoucherActiveList", list);
        this.pageUtil(spVoucherActiveRo.getPageIndex(), pager.getTotalSize(), CommonPageConfig.commonPageSize, request,
                model);
        return PATH + "promotion-index";
    }

    /**
     * @param @param  request
     * @param @param  model
     * @param @return
     * @return String    返回类型
     * @throws
     * @Title: toAdd
     * @Description:跳转到新增活动页面
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/toAdd.do")
    public String toAdd(HttpServletRequest request, Model model) throws Exception {
        String pageIndexStr = request.getParameter("pageIndex");
        if (StringUtil.stringIsNullOrEmpty(pageIndexStr)) {
            model.addAttribute("pageIndex", 1);
        } else {
            model.addAttribute("pageIndex", pageIndexStr);
        }
        model.addAttribute("fileServiceName", MatrixUtil.fastfdspreurl);
        return PATH + "promotion-add";
    }




    /**
     * @param @param  spVoucherActive
     * @param @return
     * @return SpVoucherTemp    返回类型
     * @throws
     * @Title: getSpVoucherTemp
     * @Description:获取
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    private SpVoucherTemp getSpVoucherTemp(SpVoucherActive spVoucherActive) {
        return spVoucherTempService.selectByPrimaryKey(spVoucherActive.getSendId());
    }

}
