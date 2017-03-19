package com.corner.kefu.controller.sp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corner.core.beans.*;
import com.corner.core.beans.vo.Pager;
import com.corner.core.config.UploadKeys;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.sp.SendVoucherConditionRo;
import com.corner.kefu.beans.ro.sp.SpVoucherTempMgRo;
import com.corner.kefu.beans.ro.sp.SpVoucherTempRo;
import com.corner.kefu.beans.vo.sp.SpVoucherTempVo;
import com.corner.kefu.beans.vo.sp.StoreVo;
import com.corner.kefu.config.SystemKeys;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.enums.spGroup.SpGroupCondition;
import com.corner.kefu.enums.spVoucher.SendVoucherCondition;
import com.corner.kefu.service.ConfigService;
import com.corner.kefu.service.ItemBaseService;
import com.corner.kefu.service.sp.SpStoreService;
import com.corner.kefu.service.sp.SpVoucherService;
import com.corner.kefu.service.sp.SpVoucherTempService;
import com.corner.kefu.service.sp.TempStoreService;
import com.corner.kefu.utils.FileUpload;
import com.corner.kefu.utils.PathUtil;
import com.corner.kefu.utils.SpVoucherConstants;
import com.corner.kefu.utils.excel.SendSpVoucherParseExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: PcSpVoucherController
 * @Description:优惠券管理
 * @author: 杨开泰
 * @date: 2015年11月24日 下午12:37:48
 */
@RequestMapping("/Customer/voucher")
@Controller
public class PcSpVoucherController extends KefuBaseWebController {

    private static final Logger logger = LoggerFactory.getLogger(PcSpVoucherController.class);

    private static final String PATH = "promotion/";

    @Autowired
    SpVoucherTempService spVoucherTempService;
    @Autowired
    SpVoucherConstants spVoucherConstants;
    @Autowired
    SpStoreService spStoreService;
    @Autowired
    ItemBaseService itemBaseService;
    @Autowired
    SpVoucherService spVoucherService;
    @Autowired
    TempStoreService tempStoreService;
    @Autowired
    ConfigService configService;


    /**
     * @param @return
     * @param @throws Exception
     * @return String    返回类型
     * @throws
     * @Title: getSpVoucherTemp
     * @Description:获取优惠劵基本信息
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/getSpVoucherTemp.do")
    public String getSpVoucherTemp(SpVoucherTemp spVoucherTemp, Model model) throws Exception {
        SpVoucherTempVo spVoucherTempVo = spVoucherTempService.getSpVoucherTemp(spVoucherTemp);
        model.addAttribute("spVoucherTempVo", spVoucherTempVo);
        return PATH + "coupon-info";
    }

    /**
     * @param @param  spVoucherTempRo
     * @param @return
     * @return Object    返回类型
     * @throws
     * @Title: getPageSpVoucherTempList
     * @Description:搜索优惠劵模板列表
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/getPageSpVoucherTempList.do")
    @ResponseBody
    public Object getPageSpVoucherTempList(SpVoucherTempRo spVoucherTempRo) {
        try
            {
                Pager<SpVoucherTemp> pager = spVoucherTempService.getPageSpVoucherTempList(spVoucherTempRo);
                pager.setFlag(true);
                return pager;
            } catch (Exception e)
            {
                logger.error("{}", e);
                return null;
            }
    }

    /**
     * @param @param  id
     * @param @param  file
     * @param @param  model
     * @param @param  request
     * @param @return
     * @param @throws Exception
     * @return String    返回类型
     * @throws
     * @Title: readExcel
     * @Description:读取excel数据
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @SuppressWarnings({"static-access"})
    @RequestMapping("/readExcel.do")
    public String readExcel(@RequestParam(value = "id", required = true) Integer id,
                            @RequestParam(value = "excel", required = false) MultipartFile file, Model model,
                            HttpServletRequest request) throws Exception {
        if (id == null)
            {
                return error("参数有误", model, request);
            }
        SpVoucherTemp spVoucherTemp = spVoucherTempService.getIsElegalSpVoucherTemp(id);
        if (spVoucherTemp == null)
            {
                return error("参数有误！", model, request);
            }

        AtomicInteger atomicInteger = (AtomicInteger) spVoucherConstants.ConstantsMap.get("spVoucher" + id);
        if (atomicInteger == null)
            {
                spVoucherConstants.ConstantsMap.put("spVoucher" + id, new AtomicInteger(0));
            } else
            {
                if (spVoucherConstants.ConstantsMap.get("spVoucher" + id).get() != 0)
                    {
                        return error("优惠劵正在发送,请等发送完成后继续发送", model, request);
                    }
            }
        if (null != file && !file.isEmpty())
            {
                String filePath = PathUtil.getClasspath() + UploadKeys.FILEPATHFILE; // 文件上传路径
                String fileName = FileUpload.fileUp(file, filePath, "storeExcel");
                List<String> storeIdList = SendSpVoucherParseExcelUtil.readExcel(fileName);
                List<Store> storeList = null;
                if (storeIdList != null && storeIdList.size() != 0)
                    {
                        storeList = spStoreService.getAllLegalByIds(storeIdList);
                    }
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("ruleId", spVoucherTemp.getId());
                String flag = new Date().getTime() + "";
                map.put("flag", flag);
                map.put("condition", 3);
                map.put("id", spVoucherTemp.getId());// 优惠券id
                if (storeList != null && storeList.size() != 0)
                    {
                        map.put("storeList", storeList);
                        tempStoreService.batchSave(map);
                    }
                // model.addAttribute("flag", flag);// 时间标示符
                // model.addAttribute("condition", 3);// 表示为excel导入
                model.addAttribute("rule", spVoucherTemp);
                Integer useDay = Integer.parseInt(spVoucherTemp.getUseDay() + "");
                model.addAttribute("startDate", DateUtil.getFormateDate());// 开始时间
                Date endDate = DateUtils.addDays(new Date(), useDay);
                model.addAttribute("endDate", DateUtil.date2String(endDate));// 结束时间
                String startPrice = "";// 支付限制
                if (spVoucherTemp.getStartPrice().compareTo(new BigDecimal(0)) == 0)
                    {
                        startPrice = "没有限制";
                    } else
                    {
                        startPrice = "单笔订单满" + spVoucherTemp.getStartPrice() + "元";
                    }
                model.addAttribute("startPrice", startPrice);
                model.addAttribute("id", id);
                SendVoucherConditionRo sendVoucherConditionRo = new SendVoucherConditionRo();
                sendVoucherConditionRo.setSendVoucherCondition(SendVoucherCondition.EXCEL);
                sendVoucherConditionRo.setSpGroupCondition(SpGroupCondition.ALL_SPGROUP);
                sendVoucherConditionRo.setId(spVoucherTemp.getId());
                sendVoucherConditionRo.setFlag(flag);
                String jsonStr = JSON.toJSONString(sendVoucherConditionRo);
                model.addAttribute("jsonStr", jsonStr);
                // request.getSession().setAttribute(flag, map);
            }
        return PATH + "coupon-config";
    }


    /**
     * @param @param  flag
     * @param @param  id
     * @param @param  model
     * @param @param  request
     * @param @return
     * @return String    返回类型
     * @throws
     * @Title: delTempStore
     * @Description:
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/delTempStore.do")
    public String delTempStore(HttpServletRequest request)
            throws Exception {
        String jsonStr = request.getParameter("jsonStr");
        if (StringUtils.isEmpty(jsonStr))
            {
                return "redirect:/Customer/voucher/toIndex.do";
            }
        SendVoucherConditionRo sendVoucherConditionRo = JSON.parseObject(jsonStr, SendVoucherConditionRo.class);
        if (!StringUtils.isEmpty(sendVoucherConditionRo.getFlag()) && sendVoucherConditionRo.getId() != null)
            {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("flag", sendVoucherConditionRo.getFlag());
                map.put("id", sendVoucherConditionRo.getId());
                tempStoreService.delTempStore(map);
            }
        return "redirect:/Customer/voucher/toIndex.do";
    }


    /**
     * @param @param  id
     * @param @param  request
     * @param @param  model
     * @param @return
     * @return String    返回类型
     * @throws
     * @Title: toSpVoucherSendWho
     * @Description:跳转到优惠劵发送给谁页面
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/toSpVoucherSendWho.do")
    public String toSpVoucherSendWho(@RequestParam(value = "id", required = true) Integer id,
                                     HttpServletRequest request, Model model) throws Exception {
        SpVoucherTemp spVoucherTemp = spVoucherTempService.getIsElegalSpVoucherTemp(id);
        if (spVoucherTemp == null)
            {
                return error("参数有误!", model, request);
            }
        Integer pageIndex = SystemKeys.commonPageIndex;
        Integer pageSize = SystemKeys.commonPageSize;
        String pageIndexStr = request.getParameter("pageIndex");
        if (!StringUtil.stringIsNullOrEmpty(pageIndexStr))
            {
                pageIndex = Integer.parseInt(pageIndexStr);
            }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ruleId", id);
        map.put("pageIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        String keyStr = request.getParameter("keyStr");
        if (!StringUtil.stringIsNullOrEmpty(keyStr))
            {
                map.put("keyStr", keyStr.trim());
                model.addAttribute("keyStr", keyStr.trim());
            }
        List<StoreVo> list = spStoreService.getSpvoucherStoreList(map);
        Integer count = spStoreService.getCountSpvoucherStoreList(map);
        this.pageUtil((pageIndex - 1) * pageSize, count, pageSize, request, model);
        model.addAttribute("list", list);
        model.addAttribute("ruleId", id);
        return PATH + "coupon-detail-add";
    }


    /**
     * @param @param  flag//标示符
     * @param @param  id //优惠劵id
     * @param @param  request
     * @param @return
     * @return Object    返回类型
     * @throws
     * @Title: getTempStoreList
     * @Description:获取TempStore表中的数据s
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/getTempStoreList.do")
    @ResponseBody
    public Object getTempStoreList(HttpServletRequest request) {
        try
            {
                String jsonStr = request.getParameter("jsonStr");
                if (StringUtils.isEmpty(jsonStr))
                    {
                        return ResponseUtils.sendMsg(false, "缺少必要参数jsonStr!");
                    }
                SendVoucherConditionRo sendVoucherConditionRo = JSON.parseObject(jsonStr, SendVoucherConditionRo.class);

                Integer pageIndex = SystemKeys.asynchronousPageIndex;
                Integer pageSize = SystemKeys.asynchronousPageSize;
                String pageIndexStr = request.getParameter("pageIndex");
                if (!StringUtil.stringIsNullOrEmpty(pageIndexStr))
                    {
                        pageIndex = Integer.parseInt(pageIndexStr);
                    }
                Map<String, Object> map = new HashMap<String, Object>();
                if (sendVoucherConditionRo.getId() == null)
                    {
                        return null;
                    }
                if (StringUtil.stringIsNullOrEmpty(sendVoucherConditionRo.getFlag()))
                    {
                        return null;
                    }
                String keyStr = request.getParameter("keyStr");
                if (!StringUtil.stringIsNullOrEmpty(keyStr))
                    {
                        map.put("keyStr", keyStr.trim());
                    }
                map.put("pageIndex", pageIndex * pageSize);
                map.put("pageSize", pageSize);
                map.put("flag", sendVoucherConditionRo.getFlag());
                map.put("id", sendVoucherConditionRo.getId());
                List<StoreVo> list = tempStoreService.getTempStoreList(map);
                Integer count = tempStoreService.getCountTempStoreList(map);
                return new Pager<StoreVo>(true, count, list);
            } catch (Exception e)
            {
                logger.error("", e);
                return ResponseUtils.sendMsg(false, "程序出错!");
            }
    }


    /**
     * @return java.lang.String
     * @throws
     * @Title: toEditSpVoucher
     * @Description:编辑优惠劵
     * @author 杨开泰 yangkaitai@izjjf.cn
     * @date 2016/10/3 0003 17:11
     */
    @RequestMapping(value = "/toEditspVoucherRule.do", method = RequestMethod.GET)
    public String toEditSpVoucher(@RequestParam(required = true, value = "id") Integer id, Model model,
                                  HttpServletRequest request) throws Exception {
        SpVoucherTemp spVoucherTemp = spVoucherTempService.getIsElegalSpVoucherTemp(id);
        if (spVoucherTemp == null)
            {
                return error("程序出错!", model, request);
            }
        // 编辑优惠劵的时候,如果优惠劵已经发送,并且已经有人已经使用,则不能编辑,页面确定按钮会把隐藏
        List<SpVoucher> list = spVoucherService.getSpVoucherIsUsedList(id);
        if (list != null && list.size() != 0)
            {
                model.addAttribute("isEdit", 0);// 不可编辑
            } else
            {
                model.addAttribute("isEdit", 1);// 可以编辑
            }
        String pageIndexStr = request.getParameter("pageIndex");
        if (!StringUtil.stringIsNullOrEmpty(pageIndexStr))
            {
                model.addAttribute("pageIndex", pageIndexStr);
            } else
            {
                model.addAttribute("pageIndex", 1);
            }
        List<SendTimeConfig> sendTimeConfigs = configService.getAllSendTimeConfig();
        model.addAttribute("sendTimeConfigs",sendTimeConfigs);
        model.addAttribute("payType", spVoucherTemp.getPayType());// 支付方式
        model.addAttribute("payStr", spVoucherTemp.getPayStr());
        model.addAttribute("goodsType", spVoucherTemp.getUseItemFlag());// 商品类别选项
        model.addAttribute("mgItems", spVoucherTemp.getMgItems());// 商品选项
        String useMoney = spVoucherTemp.getUseMoney() + "";
        useMoney = useMoney.substring(0, useMoney.lastIndexOf("."));
        model.addAttribute("useMoney", useMoney);
        String startPrice = spVoucherTemp.getStartPrice() + "";
        startPrice = startPrice.substring(0, startPrice.lastIndexOf("."));
        model.addAttribute("startPrice", startPrice);
        String startPriceStr = "0";// 表示单笔金额限制，1表示不限制
        if (startPrice.equals("0"))
            {
                startPriceStr = "1";
            }
        model.addAttribute("startPriceStr", startPriceStr);
        model.addAttribute("spVoucherRule", spVoucherTemp);
        model.addAttribute("id", spVoucherTemp.getId());
        model.addAttribute("remark", spVoucherTemp.getRemark());
        return PATH + "coupon-add";
        // return PATH+"coupon-new-edit";
    }

    /**
     * @param @param  id
     * @param @param  model
     * @param @param  request
     * @param @return
     * @return String    返回类型
     * @throws
     * @Title: getSpVoucherUsedLog
     * @Description:获得优惠劵的使用记录
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping(value = "/getSpVoucherUsedLog.do")
    public String getSpVoucherUsedLog(@RequestParam(required = true, value = "id") Integer id, Model model,
                                      HttpServletRequest request) throws Exception {
        SpVoucherTemp spVoucherTemp = spVoucherTempService.getIsElegalSpVoucherTemp(id);
        if (spVoucherTemp == null)
            {
                return error("参数有误!", model, request);
            }
        Integer pageIndex = SystemKeys.commonPageIndex;
        Integer pageSize = SystemKeys.commonPageSize;
        String pageIndexStr = request.getParameter("pageIndex");
        if (!StringUtil.stringIsNullOrEmpty(pageIndexStr))
            {
                pageIndex = Integer.parseInt(pageIndexStr);
            }
        Map<String, Object> map = new HashMap<String, Object>();
        String keyStr = request.getParameter("keyStr");
        if (!StringUtil.stringIsNullOrEmpty(keyStr))
            {
                map.put("keyStr", keyStr);
                model.addAttribute("keyStr", keyStr);
            }
        map.put("pageIndex", (pageIndex - 1) * pageSize);
        map.put("pageSize", pageSize);
        map.put("id", id);
        Pager<StoreVo> pager = spVoucherService.getSpVoucherUsedLog(map);
        model.addAttribute("list", pager.getList());
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("id", id);
        this.pageUtil((pageIndex - 1) * pageSize, pager.getTotalSize(), pageSize, request, model);
        return PATH + "coupon-used";
    }



    /**
     * @param @param  ruleId
     * @param @param  id
     * @param @param  model
     * @param @param  request
     * @param @return
     * @return Object    返回类型
     * @throws
     * @Title: removeStore
     * @Description:移除店铺(单个)
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping(method = RequestMethod.POST, value = "/removeStore.do")
    @ResponseBody
    public Object removeStore(@RequestParam(required = true, value = "ruleId") Integer ruleId,
                              @RequestParam(required = true, value = "id") Integer id, Model model, HttpServletRequest request) {
        try
            {
                if (StringUtil.stringIsNullOrEmpty(ruleId + "") || StringUtil.stringIsNullOrEmpty(id + ""))
                    {
                        return ResponseUtils.sendMsg(false, "参数有误!");
                    }
                String spVoucherId = request.getParameter("spVoucherId");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("ruleId", ruleId);
                map.put("storeId", id);
                SpVoucher spVoucher = spVoucherService.selectByPrimaryKey(spVoucherId);
                String statusStr = spVoucher.getStatus() + "";
                if (statusStr.equals("2") || statusStr.equals("3"))
                    {
                        return ResponseUtils.sendMsg(false, "优惠劵已经被使用,无法删除");
                    }
                spVoucherService.deleteByPrimaryKey(spVoucherId);
                return ResponseUtils.sendMsg(true, "删除成功!");
            } catch (Exception e)
            {
                logger.error("", e);
                return error("参数有误!", model, request);
            }
    }

    /**
     * @param @param  id
     * @param @param  request
     * @param @param  model
     * @param @return
     * @return String    返回类型
     * @throws
     * @Title: toSpVoucherObject
     * @Description:跳转到发送优惠劵
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @SuppressWarnings("static-access")
    @RequestMapping(value = "/toSpVoucherObject.do", method = RequestMethod.GET)
    public String toSpVoucherObject(@RequestParam(required = true, value = "id") Integer id, HttpServletRequest request,
                                    Model model) throws Exception {
        AtomicInteger atomicInteger = spVoucherConstants.ConstantsMap.get("spVoucher" + id);
        if (atomicInteger == null)
            {
                spVoucherConstants.ConstantsMap.put("spVoucher" + id, new AtomicInteger(0));
            } else
            {
                if (spVoucherConstants.ConstantsMap.get("spVoucher" + id).get() != 0)
                    {
                        return error("优惠劵正在发送,请等发送完成后继续发送", model, request);
                    }
            } // 校验id是否存在
        SpVoucherTemp spVoucherTemp = spVoucherTempService.getIsElegalSpVoucherTemp(id);
        if (spVoucherTemp == null)
            {
                return error("参数有误!", model, request);
            }
        String pageIndexStr = request.getParameter("pageIndex");
        if (!StringUtil.stringIsNullOrEmpty(pageIndexStr))
            {
                model.addAttribute("pageIndex", pageIndexStr);
            } else
            {
                model.addAttribute("pageIndex", 1);
            }
        model.addAttribute("id", spVoucherTemp.getId());
        return PATH + "coupon-send";
    }

    /**
     * @param @param  id
     * @param @param  request
     * @param @param  model
     * @param @return
     * @return String    返回类型
     * @throws Exception
     * @throws
     * @Title: deletSpVoucher
     * @Description:逻辑删除优惠劵模板
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping(value = "/deletSpVoucher.do", method = RequestMethod.GET)
    public String deletSpVoucher(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request,
                                 Model model) throws Exception {
        if (id == null)
            {
                return error("参数有误!", model, request);
            }
        String pageIndexStr = request.getParameter("pageIndex");
        SpVoucherTemp spVoucherTemp = spVoucherTempService.getIsElegalSpVoucherTemp(id);
        if (spVoucherTemp == null)
            {
                return error("参数有误!", model, request);
            }
        List<SpVoucher> list = spVoucherService.getSpVoucherIsUsedList(id);
        if (list != null && list.size() != 0)
            {
                return error("优惠券已经有被使用，不能删除!", model, request);
            }
        spVoucherTempService.removeSpvoucherTempById(id);
        return "redirect:/Customer/voucher/toIndex.do?pageIndex=" + pageIndexStr;
    }


    /**
     * @return java.lang.Object
     * @throws
     * @Title: AddSpVoucher
     * @Description:添加/编辑优惠劵
     * @author 杨开泰 yangkaitai@izjjf.cn
     * @date 2016/10/3 0003 16:32
     */
    @RequestMapping("/addSpVoucher.do")
    @ResponseBody
    public Object AddSpVoucher(Model model, HttpServletRequest request, SpVoucherTemp spVoucherTemp) {
        try
            {
                // TODO 参数校验
                if (StringUtil.stringIsNullOrEmpty(spVoucherTemp.getRuleName().trim()))
                    {
                        return ResponseUtils.sendMsg(false, "名字不能为空!");
                    }
                // 支付方式
                String[] payTypeArr = request.getParameterValues("payTypeMethod");
                String payTypeStr = ",";
                if (payTypeArr == null || payTypeArr.length == 0)
                    {
                        return ResponseUtils.sendMsg(false, "请选择支付方式");
                    } else if (payTypeArr.length == 1 && payTypeArr[0].equals("0"))
                    {
                        spVoucherTemp.setPayType(new Byte("0"));
                    } else
                    {
                        spVoucherTemp.setPayType(new Byte("1"));
                        for (int i = 0; i < payTypeArr.length; i++)
                            {
                                payTypeStr += payTypeArr[i] + ",";
                            }
                        spVoucherTemp.setPayStr(payTypeStr);
                    }

                // 金额限制
                Integer startPriceRadio = Integer.parseInt(request.getParameter("startPriceRadio"));
                if (startPriceRadio == 1)
                    {// 没有金额限制,startPrice为数据库默认值
                        spVoucherTemp.setStartPrice(new BigDecimal("0"));
                    } else
                    {
                        // TODO 校验是否是正整数
                        String startPrice = request.getParameter("startPrice");
                        if (StringUtil.stringIsNullOrEmpty(startPrice))
                            {
                                return ResponseUtils.sendMsg(false, "请输入订单金额!");
                            }
                        spVoucherTemp.setStartPrice(new BigDecimal(startPrice));
                    }
                // 页面商品/类别选中项
                Integer useItemFlag = Integer.parseInt(request.getParameter("useItemFlag"));
                if (useItemFlag.toString().equals("2"))
                    {// 排除类别/商品
                        String[] itemArr = request.getParameterValues("itemArr");
                        String mgItems = "";
                        if (itemArr == null || itemArr.length == 0)
                            {
                                return ResponseUtils.sendMsg(false, "选中排除商品的时候,必须添加不参与的商品");
                            }
                        List<String> goodsStrTemp = new ArrayList<String>();
                        for (int i = 0; i < itemArr.length; i++)
                            {
                                String var = itemArr[i];
                                String[] varArr = var.split("@");
                                mgItems += var + ";";
                                if (varArr[0].equals("item"))
                                    goodsStrTemp.add(varArr[1]);
                                else
                                    {
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
                        spVoucherTemp.setUseItemIds(goodsStr);
                        mgItems = mgItems.substring(0, mgItems.lastIndexOf(";"));
                        spVoucherTemp.setMgItems(mgItems);
                    } else
                    {
                        if (useItemFlag.toString().equals("1"))
                            {// 指定商品
                                String[] productArr = request.getParameterValues("productArr");
                                String mgItems = "";
                                if (productArr == null || productArr.length == 0)
                                    {
                                        return ResponseUtils.sendMsg(false, "选中指定商品的时候,必须添加参与的商品");
                                    }
                                List<String> inGoodsTypeTemp = new ArrayList<String>();// 参与
                                List<String> outGoodsTypeTemp = new ArrayList<String>();// 参与
                                for (int i = 0; i < productArr.length; i++)
                                    {
                                        String var = productArr[i];
                                        String[] varArr = var.split("@");
                                        mgItems += var + ";";
                                        if (varArr[0].equals("item"))
                                            if (varArr[3].equals("1"))
                                                {// 不参与
                                                    outGoodsTypeTemp.add(varArr[1]);
                                                } else
                                                {
                                                    inGoodsTypeTemp.add(varArr[1]);
                                                }
                                        else
                                            {
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
                                spVoucherTemp.setUseItemIds(goodsStr);
                                mgItems = mgItems.substring(0, mgItems.lastIndexOf(";"));
                                spVoucherTemp.setMgItems(mgItems);
                            }
                    }
                spVoucherTemp.setCreatPub(new Byte("1"));
                spVoucherTemp.setUpdateTime(new Date());
                if (spVoucherTemp.getId() != null)
                    spVoucherTempService.updatSpVoucherTemp(spVoucherTemp);
                else
                    {
                        spVoucherTemp.setAddTime(new Date());
                        spVoucherTempService.addSpVoucherTemp(spVoucherTemp);
                    }
                return ResponseUtils.sendMsg(true, "新增成功!");
            } catch (Exception e)
            {
                logger.error("", e);
                return ResponseUtils.sendMsg(false, "程序出错!");
            }
    }

    /**
     * @param @param  id
     * @param @param  request
     * @param @return
     * @return Object    返回类型
     * @throws
     * @Title: getStoreList
     * @Description:查询所有未被删除,审核通过的店铺列表
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/getStoreList.do")
    @ResponseBody
    public Object getStoreList(@RequestParam(value = "id", required = true) Integer id, HttpServletRequest request) {
        try
            {
                Integer pageIndex = SystemKeys.asynchronousPageIndex;
                Integer pageSize = SystemKeys.asynchronousPageSize;
                String pageIndexStr = request.getParameter("pageIndex");
                if (!StringUtil.stringIsNullOrEmpty(pageIndexStr))
                    {
                        pageIndex = Integer.parseInt(pageIndexStr);
                    }
                Map<String, Object> map = new HashMap<String, Object>();
                if (id == null)
                    {
                        return null;
                    }
                String keyStr = request.getParameter("keyStr");
                if (!StringUtil.stringIsNullOrEmpty(keyStr))
                    {
                        map.put("keyStr", keyStr.trim());
                    }
                map.put("pageIndex", pageIndex * pageSize);
                map.put("pageSize", pageSize);
                List<StoreVo> list = spStoreService.getStoreListWithSpGroup(map);
                Integer count = spStoreService.getCountStoreListWithSpGroup(map);
                return new Pager<>(true, count, list);
            } catch (Exception e)
            {
                logger.error("", e);
                return ResponseUtils.sendMsg(false, "程序出错");
            }
    }

    /**
     * @param @param  request
     * @param @param  model
     * @param @return
     * @return String    返回类型
     * @throws
     * @Title: toConfig
     * @Description:跳转到确认订单页面
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/toConfig.do")
    public String toConfig(HttpServletRequest request, Model model, SendVoucherConditionRo sendVoucherConditionRo)
            throws Exception {
        String jsonStr = JSONObject.toJSONString(sendVoucherConditionRo);
        if (StringUtils.isEmpty(jsonStr))
            {
                return error("数据异常,没有和偶去jsonStr!", model, request);
            }
        model.addAttribute("jsonStr", jsonStr);

        // 数据校验
        if (sendVoucherConditionRo.getSendVoucherCondition() == null
                || sendVoucherConditionRo.getSpGroupCondition() == null || sendVoucherConditionRo.getId() == null)
            {
                return error("缺少必要数据!", model, request);
            }

        AtomicInteger atomicInteger = spVoucherConstants.ConstantsMap.get("spVoucher" + sendVoucherConditionRo.getId());
        if (atomicInteger == null)
            {
                spVoucherConstants.ConstantsMap.put("spVoucher" + sendVoucherConditionRo.getId(), new AtomicInteger(0));
            } else
            {
                if (spVoucherConstants.ConstantsMap.get("spVoucher" + sendVoucherConditionRo.getId()).get() != 0)
                    {
                        return error("优惠劵正在发送,请等发送完成后继续发送", model, request);
                    }
            }

        switch (sendVoucherConditionRo.getSendVoucherCondition())
            {
                case NO_ORDER_MONTH:
                    if (sendVoucherConditionRo.getCustomerNum() == null)
                        {
                            return error("缺少必要数据customerNum!", model, request);
                        }
                    break;
                case DESIGNATED_CUSTOMERS:
                    if (sendVoucherConditionRo.getStoreArr() == null || sendVoucherConditionRo.getStoreArr().length == 0)
                        {
                            return error("没有指定用户!", model, request);
                        }
                    break;
                default:
                    break;
            }

        switch (sendVoucherConditionRo.getSpGroupCondition())
            {
                case DESIGNATED_SPGROUP:
                    if (sendVoucherConditionRo.getSpGroupArr() == null || sendVoucherConditionRo.getSpGroupArr().length == 0)
                        {
                            return error("没有指定定格!", model, request);
                        }
                    break;
                default:
                    break;
            }
        // 用于区分是否是excel导入
        SpVoucherTemp spVoucherTemp = spVoucherTempService.getIsElegalSpVoucherTemp(sendVoucherConditionRo.getId());
        if (spVoucherTemp == null)
            {
                return error("优惠劵id有误!", model, request);
            }
        model.addAttribute("rule", spVoucherTemp);
        Integer useDay = Integer.parseInt(spVoucherTemp.getUseDay() + "");
        model.addAttribute("startDate", DateUtil.getFormateDate());// 开始时间
        Date endDate = DateUtils.addDays(new Date(), useDay);
        model.addAttribute("endDate", DateUtil.date2String(endDate));// 结束时间
        String startPrice = "";// 支付限制
        if (spVoucherTemp.getStartPrice().compareTo(new BigDecimal(0)) == 0)
            {
                startPrice = "没有限制";
            } else
            {
                startPrice = "单笔订单满" + spVoucherTemp.getStartPrice() + "元";
            }
        model.addAttribute("startPrice", startPrice);
        model.addAttribute("id", sendVoucherConditionRo.getId());
        return PATH + "coupon-config";
		
    }

    /**
     * @param @param  request
     * @param @param  model
     * @param @return
     * @return Object    返回类型
     * @throws
     * @Title: searchConfig
     * @Description:
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/searchConfig.do")
    @ResponseBody
    public Object searchConfig(HttpServletRequest request, Model model) {
        try
            {
                String jsonStr = request.getParameter("jsonStr");
                if (StringUtils.isEmpty(jsonStr))
                    {
                        return ResponseUtils.sendMsg(false, "缺少必要参数jsonStr!");
                    }
                SendVoucherConditionRo sendVoucherConditionRo = JSON.parseObject(jsonStr, SendVoucherConditionRo.class);

                //数据校验
                if (sendVoucherConditionRo.getSendVoucherCondition() == null
                        || sendVoucherConditionRo.getSpGroupCondition() == null
                        || sendVoucherConditionRo.getId() == null)
                    {
                        return ResponseUtils.sendMsg(false, "缺少必要数据!");
                    }

                AtomicInteger atomicInteger = spVoucherConstants.ConstantsMap.get("spVoucher" + sendVoucherConditionRo.getId());
                if (atomicInteger == null)
                    {
                        spVoucherConstants.ConstantsMap.put("spVoucher" + sendVoucherConditionRo.getId(), new AtomicInteger(0));
                    } else
                    {
                        if (spVoucherConstants.ConstantsMap.get("spVoucher" + sendVoucherConditionRo.getId()).get() != 0)
                            {
                                return error("优惠劵正在发送,请等发送完成后继续发送", model, request);
                            }
                    }

                switch (sendVoucherConditionRo.getSendVoucherCondition())
                    {
                        case NO_ORDER_MONTH:
                            if (sendVoucherConditionRo.getCustomerNum() == null)
                                {
                                    return ResponseUtils.sendMsg(false, "缺少必要数据customerNum!");
                                }
                            break;
                        case DESIGNATED_CUSTOMERS:
                            if (sendVoucherConditionRo.getStoreArr() == null || sendVoucherConditionRo.getStoreArr().length == 0)
                                {
                                    return ResponseUtils.sendMsg(false, "没有指定用户!");
                                }
                            break;
                        case EXCEL:
                            if (StringUtils.isEmpty(sendVoucherConditionRo.getFlag()))
                                {
                                    return ResponseUtils.sendMsg(false, "缺少必要参数flag!");
                                }
                            break;
                        default:
                            break;
                    }
                switch (sendVoucherConditionRo.getSpGroupCondition())
                    {
                        case DESIGNATED_SPGROUP:
                            if (sendVoucherConditionRo.getSpGroupArr() == null || sendVoucherConditionRo.getSpGroupArr().length == 0)
                                {
                                    return ResponseUtils.sendMsg(false, "没有指定定格!");
                                }
                            break;
                        default:
                            break;
                    }


                Integer pageIndex = SystemKeys.asynchronousPageIndex;
                Integer pageSize = SystemKeys.asynchronousPageSize;
                String pageIndexStr = request.getParameter("pageIndex");
                if (!StringUtil.stringIsNullOrEmpty(pageIndexStr))
                    {
                        pageIndex = Integer.parseInt(pageIndexStr);
                    }

                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("pageIndex", pageIndex * pageSize);
                paramMap.put("pageSize", pageSize);

                SpVoucherTemp spVoucherTemp = spVoucherTempService.getIsElegalSpVoucherTemp(sendVoucherConditionRo.getId());
                if (spVoucherTemp == null)
                    {
                        return ResponseUtils.sendMsg(false, "优惠劵id有误!");
                    }
                List<StoreVo> list = new ArrayList<StoreVo>();
                Integer count = 0;
                String keyStr = request.getParameter("keyStr");
                if (!StringUtil.stringIsNullOrEmpty(keyStr))
                    {
                        paramMap.put("keyStr", keyStr);
                    } else
                    {
                        paramMap.remove("keyStr");
                    }
                paramMap.put("spGroupIdArr", sendVoucherConditionRo.getSpGroupArr());
                paramMap.put("storeArr", sendVoucherConditionRo.getStoreArr());

                switch (sendVoucherConditionRo.getSendVoucherCondition())
                    {
                        case NO_LIMIT:
                            list = spStoreService.getAllLegalStoreList(paramMap);
                            count = spStoreService.getCountAllLegalStoreList(paramMap);
                            model.addAttribute("count", count);// 实际选中用户数
                            break;
                        case NO_ORDER:
                            list = spStoreService.getNoOrderStoreList(paramMap);
                            count = spStoreService.getCountNoOrderStoreList(paramMap);
                            break;
                        case NO_ORDER_MONTH:
                            Integer customerNum = sendVoucherConditionRo.getCustomerNum();
                            customerNum = Integer.parseInt("-" + customerNum);
                            //Date now = new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(new Date());
                            calendar.set(Calendar.HOUR_OF_DAY, 0);
                            calendar.set(Calendar.SECOND, 0);
                            calendar.set(Calendar.MINUTE, 0);
                            Date someMonth = DateUtils.addMonths(calendar.getTime(), customerNum);
                            paramMap.put("someMonth", someMonth);

                            list = spStoreService.getNoOrderStoreList(paramMap);
                            count = spStoreService.getCountNoOrderStoreList(paramMap);
                            break;
                        case DESIGNATED_CUSTOMERS:
                            String[] storeArr = (String[]) paramMap.get("storeArr");
                            if (storeArr == null || storeArr.length == 0)
                                {
                                    return error("请选择用户!", model, request);
                                }
                            List<String> searchList = Arrays.asList(storeArr);
                            paramMap.put("searchList", searchList);
                            list = spStoreService.isHasSpVoucher(paramMap);
                            count = spStoreService.isCountHasSpVoucher(paramMap);
                            break;
                        case EXCEL:
                            paramMap.put("flag", sendVoucherConditionRo.getFlag());
                            list = tempStoreService.getTempStoreList(paramMap);
                            count = tempStoreService.getCountTempStoreList(paramMap);
                            break;
                        default:
                            break;
                    }
                return new Pager<StoreVo>(count, list);
			
            } catch (Exception e)
            {
                logger.error("", e);
                return ResponseUtils.sendMsg(false, "程序错误");
            }
    }

    /**
     * @param @param  request
     * @param @param  model
     * @param @return
     * @return Object    返回类型
     * @throws
     * @Title: asynSendSpVoucher
     * @Description:发送优惠劵
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @SuppressWarnings("static-access")
    @RequestMapping("/asynSendSpVoucher.do")
    @ResponseBody
    public Object asynSendSpVoucher(HttpServletRequest request, Model model) {
        try
            {
                String jsonStr = request.getParameter("jsonStr");
                if (StringUtils.isEmpty(jsonStr))
                    {
                        return ResponseUtils.sendMsg(false, "缺少数据jsonStr!");
                    }
                SendVoucherConditionRo sendVoucherConditionRo = JSON.parseObject(jsonStr, SendVoucherConditionRo.class);

                // 数据校验
                if (sendVoucherConditionRo.getSendVoucherCondition() == null
                        || sendVoucherConditionRo.getSpGroupCondition() == null || sendVoucherConditionRo.getId() == null)
                    {
                        return ResponseUtils.sendMsg(false, "缺少必要数据!");
                    }

                switch (sendVoucherConditionRo.getSendVoucherCondition())
                    {
                        case NO_ORDER_MONTH:
                            if (sendVoucherConditionRo.getCustomerNum() == null)
                                return ResponseUtils.sendMsg(false, "缺少必要数据customerNum!");
                            break;
                        case DESIGNATED_CUSTOMERS:
                            if (sendVoucherConditionRo.getStoreArr() == null || sendVoucherConditionRo.getStoreArr().length == 0)
                                return ResponseUtils.sendMsg(false, "没有指定用户!");
                            break;
                        case EXCEL:
                            if (StringUtils.isEmpty(sendVoucherConditionRo.getFlag()))
                                return ResponseUtils.sendMsg(false, "缺少必要参数flag!");
                            break;
                        default:
                            break;
                    }

                switch (sendVoucherConditionRo.getSpGroupCondition())
                    {
                        case DESIGNATED_SPGROUP:
                            if (sendVoucherConditionRo.getSpGroupArr() == null
                                    || sendVoucherConditionRo.getSpGroupArr().length == 0)
                                return ResponseUtils.sendMsg(false, "没有指定定格!");
                            break;
                        default:
                            break;
                    }

                AtomicInteger atomicInteger = spVoucherConstants.ConstantsMap
                        .get("spVoucher" + sendVoucherConditionRo.getId());
                if (atomicInteger == null)
                    spVoucherConstants.ConstantsMap.put("spVoucher" + sendVoucherConditionRo.getId(), new AtomicInteger(0));
                else if (spVoucherConstants.ConstantsMap.get("spVoucher" + sendVoucherConditionRo.getId()).get() != 0)
                    return ResponseUtils.sendMsg(false, "优惠劵正在发送,请等发送完成后继续发送");

                // 要删除的店铺
                String[] delStoreArr = request.getParameterValues("delStoreArr");
                List<String> delStoreList = null;
                if (null != delStoreArr && delStoreArr.length > 0)
                    delStoreList = Arrays.asList(delStoreArr);

                SpVoucherTemp spVoucherTemp = spVoucherTempService.getIsElegalSpVoucherTemp(sendVoucherConditionRo.getId());
                if (spVoucherTemp == null)
                    return ResponseUtils.sendMsg(false, "优惠劵id错误!");

                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("pageSize", 0);
                paramMap.put("spGroupIdArr", sendVoucherConditionRo.getSpGroupArr());
                paramMap.put("storeArr", sendVoucherConditionRo.getStoreArr());
                String keyStr = request.getParameter("keyStr");
                if (!StringUtils.isEmpty(keyStr))
                    {
                        paramMap.put("keyStr", keyStr);
                    }
                List<StoreVo> list = null;
                switch (sendVoucherConditionRo.getSendVoucherCondition())
                    {
                        case NO_LIMIT:
                            list = spStoreService.getAllLegalStoreList(paramMap);
                            break;
                        case NO_ORDER:
                            list = spStoreService.getNoOrderStoreList(paramMap);
                            break;
                        case NO_ORDER_MONTH:
                            Integer customerNum = sendVoucherConditionRo.getCustomerNum();
                            customerNum = Integer.parseInt("-" + customerNum);
                            Date someMonth = DateUtils.addDays(new Date(), customerNum);
                            paramMap.put("someMonth", someMonth);
                            list = spStoreService.getNoOrderStoreList(paramMap);
                            break;
                        case DESIGNATED_CUSTOMERS:
                            String[] storeArr = (String[]) paramMap.get("storeArr");
                            if (storeArr == null || storeArr.length == 0)
                                {
                                    return error("请选择用户!", model, request);
                                }
                            List<String> searchList = Arrays.asList(storeArr);
                            paramMap.put("searchList", searchList);
                            list = spStoreService.isHasSpVoucher(paramMap);
                            break;
                        case EXCEL:
                            paramMap.put("flag", sendVoucherConditionRo.getFlag());
                            list = tempStoreService.getTempStoreList(paramMap);
                            break;
                        default:
                            break;
                    }
                if (delStoreList != null && delStoreList.size() != 0)
                    list.removeAll(delStoreList);
                spVoucherService.asyAddSpVoucher(list, spVoucherTemp);
                return ResponseUtils.sendMsg(true, "发送成功");
            } catch (Exception e)
            {
                logger.error("", e);
                return ResponseUtils.sendMsg(false, "程序出错");
            }
    }

    /**
     * @param @param  request
     * @param @param  model
     * @param @return
     * @return String    返回类型
     * @throws
     * @Title: toAddSpVoucher
     * @Description:跳转到新增优惠劵页面
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/toCouponAdd.do")
    public String toAddSpVoucher(HttpServletRequest request, Model model) throws Exception {
        String pageIndexStr = request.getParameter("pageIndex");
        List<SendTimeConfig> configs = configService.getAllSendTimeConfig();
        model.addAttribute("sendTimeConfigs",configs);
        model.addAttribute("pageIndex", pageIndexStr);
        return PATH + "coupon-add";
    }

    /**
     * @param @param  request
     * @param @param  model
     * @param @param  spVoucherTemp
     * @param @return
     * @param @throws Exception
     * @return String    返回类型
     * @throws
     * @Title: toIndex
     * @Description:跳转到优惠劵列表页面
     * @author 杨开泰 yangkaitai@izjjf.cn
     */
    @RequestMapping("/toIndex.do")
    public String toIndex(HttpServletRequest request, Model model, SpVoucherTempMgRo spVoucherTempMgRo)
            throws Exception {
        String pageIndexStr = request.getParameter("pageIndex");
        if (!StringUtil.stringIsNullOrEmpty(pageIndexStr))
            {
                model.addAttribute("pageIndex", pageIndexStr);
                spVoucherTempMgRo.setPageIndex(Integer.parseInt(pageIndexStr));
            } else
            {
                model.addAttribute("pageIndex", 1);
            }
        if (!StringUtil.stringIsNullOrEmpty(spVoucherTempMgRo.getRuleName()))
            {
                spVoucherTempMgRo.setRuleName(spVoucherTempMgRo.getRuleName().trim());
                model.addAttribute("keyStr", spVoucherTempMgRo.getRuleName().trim());
            }
        if (spVoucherTempMgRo.getStartDate() != null)
            {
                String startDateStr = DateUtil.date2String(spVoucherTempMgRo.getStartDate());
                model.addAttribute("startDateStr", startDateStr);
            }
        if (spVoucherTempMgRo.getEndDate() != null)
            {
                Date endDate = spVoucherTempMgRo.getEndDate();
                Date newEndDate = DateUtils.addDays(endDate, 1);
                spVoucherTempMgRo.setEndDate(newEndDate);
                String endDateStr = DateUtil.date2String(endDate);
                model.addAttribute("endDateStr", endDateStr);
            }
        if (!StringUtil.stringIsNullOrEmpty(spVoucherTempMgRo.getUseMoneyStr()))
            {
                spVoucherTempMgRo.setUseMoneyStr(spVoucherTempMgRo.getUseMoneyStr().trim());
                model.addAttribute("useMoneyStr", spVoucherTempMgRo.getUseMoneyStr().trim());
            }
        Pager<SpVoucherTempVo> pager = spVoucherTempService.getSpVoucherTempIndex(spVoucherTempMgRo);
        model.addAttribute("list", pager.getList());
        this.pageUtil(spVoucherTempMgRo.getPageIndex(), pager.getTotalSize(), spVoucherTempMgRo.getPageSize(), request,
                model);
        return PATH + "coupon";
    }

}
