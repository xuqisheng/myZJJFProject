package com.corner.scms.controller.erp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.corner.scms.interceptor.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ERPMarketStockInfo;
import com.corner.core.beans.ERPWarehouse;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.User;
import com.corner.core.beans.vo.Pager;
import com.corner.core.enums.CheckStatus;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPMarketStockRo;
import com.corner.scms.beans.vo.OrderInfoVo;
import com.corner.scms.beans.vo.SpOrderDetailVo;
import com.corner.scms.beans.vo.erp.ERPMarketStockVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.erp.ERPMarketStockService;
import com.corner.scms.service.erp.ERPSpOrderOwnerService;
import com.corner.scms.service.erp.ERPWarehouseService;
import com.corner.scms.service.sp.ScmsOrderInfoMgService;
import com.corner.scms.utils.BeanUtil;

/**
 * Created by mxh on 2016/9/5.
 */
@Controller
@RequestMapping("/scms/ERPMarketStock")
public class ERPMarketStockController extends ScmsBaseWebController {
    private static Logger logger = LoggerFactory.getLogger(ERPMarketStockController.class);
    @Autowired
    ERPMarketStockService erpMarketStockService;
    @Autowired
    ERPWarehouseService erpWarehouseService;
    @Autowired
    ScmsOrderInfoMgService scmsOrderInfoMgService;
    @Autowired
    ERPSpOrderOwnerService erpSpOrderOwnerService;
    
    
    
    @RequestMapping(value = {"list/{level}"})
    @Token(save = true)
    public String stockInList(@PathVariable("level") Byte level , ERPMarketStockRo commod , HttpServletRequest request, Model model) throws Exception{
        Supplier supplier = getCurrentUser(Supplier.class , request);
        commod.setSupplierId(supplier.getId());
        commod.setSortName("addTime");
        commod.setSortOrder("DESC");
        commod.setLevel(level);
        if(commod.getEndTime() != null){
            commod.setEndTime(DateUtil.updateDate(commod.getEndTime() , 1));
        }
        Pager<ERPMarketStockInfo> list =  erpMarketStockService.getInfoListByRo(commod);
        if(commod.getEndTime() != null){
            commod.setEndTime(DateUtil.updateDate(commod.getEndTime() , -1));
        }
        pageUtil(commod.getPageIndex() , list.getTotalSize() , commod.getPageSize() , request , model);
        model.addAttribute("checkStatus" , CheckStatus.values());
        model.addAttribute("commod" , commod);
        model.addAttribute("list" , list.getList());
        return "erp/market/list";
    }
    @RequestMapping(value = {"bacthCheck/{checkStatus}"})
    @Token(remove = true)
    @ResponseBody
    public Object bacthCheck(ERPMarketStockRo ro ,@PathVariable("checkStatus") Byte checkStatus, HttpServletRequest request, Model model){
        if(StringUtil.stringIsNullOrEmpty(ro.getIds()))
            return ResponseUtils.sendMsg(false , "审核失败：缺少必要参数");
        else if(StringUtil.stringIsNullOrEmpty(CheckStatus.getName(checkStatus)))
            return ResponseUtils.sendMsg(false , "审核失败：缺少必要参数");
        try {
            User user = this.getUser(request);
            erpMarketStockService.bacthCheck(checkStatus , user.getId() , user.getUserName() , ro.getIds());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseUtils.sendMsg(false , "审核失败："+e.getMessage());
        }
        return ResponseUtils.sendMsg(true , "交易成功");
    }
    @RequestMapping(value = {"bacthOutStock"})
    @Token(remove = true)
    @ResponseBody
    public Object bacthOutStock(ERPMarketStockRo ro ,HttpServletRequest request, Model model){
        if(StringUtil.stringIsNullOrEmpty(ro.getIds()))
            return ResponseUtils.sendMsg(false , "操作失败：缺少必要参数");
        try {
            erpMarketStockService.bacthOutStock(ro.getIds());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseUtils.sendMsg(false , "操作失败："+e.getMessage());
        }
        return ResponseUtils.sendMsg(true , "操作成功");
    }
    @RequestMapping(value = {"bacthSend"})
    @Token(remove = true)
    @ResponseBody
    public Object bacthSend(ERPMarketStockRo ro, HttpServletRequest request, Model model){
        if(StringUtil.stringIsNullOrEmpty(ro.getIds()))
            return ResponseUtils.sendMsg(false , "操作失败：缺少必要参数");
        try {
            erpMarketStockService.bacthSend(ro.getIds());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseUtils.sendMsg(false , "操作失败："+e.getMessage());
        }
        return ResponseUtils.sendMsg(true , "操作成功");
    }
    @RequestMapping(value = {"bacthDelete"})
    @Token(remove = true)
    @ResponseBody
    public Object bacthDelete(ERPMarketStockRo ro ,HttpServletRequest request, Model model){
        if(StringUtil.stringIsNullOrEmpty(ro.getIds()))
            return ResponseUtils.sendMsg(false , "审核失败：缺少必要参数");
        try {
            User user = this.getUser(request);
            erpMarketStockService.bacthDelete(user.getId() , user.getUserName() , ro.getIds());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseUtils.sendMsg(false , "删除失败："+e.getMessage());
        }
        return ResponseUtils.sendMsg(true , "交易成功");
    }
    @RequestMapping(value = {"detail/{id}"})
    public Object stockInList(@PathVariable("id") String id, HttpServletRequest request, Model model) throws Exception{
        ERPMarketStockInfo erpMarketStockInfo = erpMarketStockService.getInfoById(id);
        ERPMarketStockVo vo = BeanUtil.toObject(ERPMarketStockVo.class , erpMarketStockInfo);
        vo.setDetails(erpMarketStockService.getDetailsByOrderId(vo.getOrderId()));
        model.addAttribute("detail" , vo);
        return "erp/market/detail";
    }
    @RequestMapping(value = {"edit/{pId}/{id}"})
    public String edit(@PathVariable("pId") String pId , @PathVariable("id") String id , HttpServletRequest request, Model model) throws Exception{
        ERPWarehouse warehouse = getCurrentUser(ERPWarehouse.class , request);
        List<ERPWarehouse> list = new ArrayList<>();
        if (warehouse != null) {
            list.add(warehouse);
            model.addAttribute("warehouses" , list);
        }else{
            Supplier supplier = getCurrentUser(Supplier.class , request);
            model.addAttribute("warehouses" , erpWarehouseService.getERPWarehouseLevel1All(supplier.getId()));
        }

        if(!StringUtil.stringIsNullOrEmpty(pId) && !"1".equals(pId)) {
            ERPMarketStockInfo info = erpMarketStockService.getInfoById(pId);
            model.addAttribute("info", erpMarketStockService.getInfoById(pId));
            model.addAttribute("detail", erpMarketStockService.getDetailsByOrderId(info.getOrderId()));
        }
        if(!StringUtil.stringIsNullOrEmpty(id) && !"1".equals(id)){
            ERPMarketStockInfo info = erpMarketStockService.getInfoById(id);
            model.addAttribute("info2" , info);
            model.addAttribute("detail2" , erpMarketStockService.getDetailsByOrderId(info.getOrderId()));
        }
        return "erp/market/edit";
    }
    @RequestMapping(value = {"save"})
    public String save(ERPMarketStockRo ro, HttpServletRequest request, Model model){
        try {
            User user = getUser(request);
            Supplier supplier = getCurrentUser(Supplier.class , request);
            ro.setSupplierId(supplier.getId());
            ro.setSupplierName(supplier.getSupplierName());
            erpMarketStockService.addMarketStockIn(ro , user.getId() , user.getUserName());
            return "redirect:/scms/ERPMarketStock/list/2.do";
        }catch (Exception e){
            return error(e.getMessage() , model , request);
        }
    }
    @RequestMapping(value = {"print/{id}"})
    public String print(@PathVariable("id") String id , HttpServletRequest request, Model model) throws Exception{
        ERPMarketStockVo info = BeanUtil.toObject(ERPMarketStockVo.class ,erpMarketStockService.getInfoById(id));
        info.setDetails(erpMarketStockService.getDetailsByOrderId(info.getOrderId()));
        OrderInfoVo spOrderInfo=this.scmsOrderInfoMgService.findByOrId(info.getpOrderId());
        /*if(spOrderInfo.getLevel().intValue()==2){
        	SpOrderInfo fuOrderInfoVo = this.scmsOrderInfoMgService.getFuOrDerById(spOrderInfo.getpId());
        	model.addAttribute("fuOrder", fuOrderInfoVo);
        }*/
        //查询业务员手机号
//		String yewuyuanmobile = scmsOrderInfoMgService.getyewuyuanmobile(spOrderInfo.getStoreId());
        // 订单详细
        List<SpOrderDetailVo> orderDetails = erpMarketStockService.getOwnerDetailToVo(info.getOrderId());

        Map<String, Object> map = new HashMap<>();
        map.put("orderInfo", spOrderInfo);
        map.put("orderDetail", orderDetails);
        orderDetails.stream().filter(vo -> !"兑换".equals(vo.getYouHui())).forEach(vo -> map.put("linkOrderNo", vo.getOrderId()));
        model.addAttribute("map", map);
        model.addAttribute("info" , info);
        model.addAttribute("ownerDetail" , erpSpOrderOwnerService.getOwnerDetailToVo(spOrderInfo.getId()));
        return "erp/market/print";
    }
}
