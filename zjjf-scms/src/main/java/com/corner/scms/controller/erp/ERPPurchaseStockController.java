package com.corner.scms.controller.erp;

import com.corner.core.beans.*;
import com.corner.core.beans.vo.Pager;
import com.corner.core.enums.CheckStatus;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.auth.LoginRo;
import com.corner.scms.beans.ro.erp.ERPPurchaseStockRo;
import com.corner.scms.beans.vo.erp.ERPPurchaseStockVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.erp.ERPPurchaseStockService;
import com.corner.scms.service.erp.ERPWarehouseService;
import com.corner.scms.utils.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mxh on 2016/9/5.
 */
@Controller
@RequestMapping("/scms/ERPPurchaseStock")
public class ERPPurchaseStockController extends ScmsBaseWebController {
    private static Logger logger = LoggerFactory.getLogger(ERPPurchaseStockController.class);
    @Autowired
    ERPPurchaseStockService erpPurchaseStockService;
    @Autowired
    ERPWarehouseService erpWarehouseService;
    @RequestMapping(value = {"list/{level}"})
    public String stockInList(@PathVariable("level") Byte level , ERPPurchaseStockRo commod , HttpServletRequest request, Model model) throws Exception{
        Supplier supplier = getCurrentUser(Supplier.class , request);
        commod.setSupplierId(supplier.getId());
        commod.setLevel(level);
        if(commod.getEndTime() != null){
            commod.setEndTime(DateUtil.updateDate(commod.getEndTime() , 1));
        }
        Pager<ERPPurchaseStockInfo> list =  erpPurchaseStockService.getInfoListByRo(commod);
        if(commod.getEndTime() != null){
            commod.setEndTime(DateUtil.updateDate(commod.getEndTime() , -1));
        }
        pageUtil(commod.getPageIndex() , list.getTotalSize() , commod.getPageSize() , request , model);
        model.addAttribute("checkStatus" , CheckStatus.values());
        model.addAttribute("commod" , commod);
        model.addAttribute("list" , list.getList());
        if(level== 1)
            return "erp/product/stockIn-list";
        else
            return "erp/product/stockOut-list";
    }

    @RequestMapping(value = {"check/{id}/{checkStatus}"})
    public Object stockInList(@PathVariable("id") String id ,@PathVariable("checkStatus") Byte checkStatus , HttpServletRequest request, Model model){
        if(StringUtil.stringIsNullOrEmpty(id , CheckStatus.getName(checkStatus)))
            return ResponseUtils.sendMsg(false , "审核失败：缺少必要参数");
        try {
            User user = this.getUser(request);
            erpPurchaseStockService.bacthCheck(checkStatus , user.getId() , user.getUserName() , id);
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseUtils.sendMsg(false , "审核失败："+e.getMessage());
        }
        return ResponseUtils.sendMsg(true , "交易成功");
    }
    @RequestMapping(value = {"bacthCheck/{checkStatus}"})
    @ResponseBody
    public Object stockInList(ERPPurchaseStockRo ro ,@PathVariable("checkStatus") Byte checkStatus, HttpServletRequest request, Model model){
        if(StringUtil.stringIsNullOrEmpty(ro.getIds()))
            return ResponseUtils.sendMsg(false , "审核失败：缺少必要参数");
        else if(StringUtil.stringIsNullOrEmpty(CheckStatus.getName(checkStatus)))
            return ResponseUtils.sendMsg(false , "审核失败：缺少必要参数");
        try {
            User user = this.getUser(request);
            erpPurchaseStockService.bacthCheck(checkStatus , user.getId() , user.getUserName() , ro.getIds());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseUtils.sendMsg(false , "审核失败："+e.getMessage());
        }
        return ResponseUtils.sendMsg(true , "交易成功");
    }
    @RequestMapping(value = {"bacthDelete"})
    @ResponseBody
    public Object bacthDelete(ERPPurchaseStockRo ro ,HttpServletRequest request, Model model){
        if(StringUtil.stringIsNullOrEmpty(ro.getIds()))
            return ResponseUtils.sendMsg(false , "审核失败：缺少必要参数");
        try {
            User user = this.getUser(request);
            erpPurchaseStockService.bacthDelete(user.getId() , user.getUserName() , ro.getIds());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseUtils.sendMsg(false , "删除失败："+e.getMessage());
        }
        return ResponseUtils.sendMsg(true , "交易成功");
    }
    @RequestMapping(value = {"detail/{id}"})
    public Object stockInList(@PathVariable("id") String id, HttpServletRequest request, Model model) throws Exception{
        ERPPurchaseStockInfo erpPurchaseStockInfo = erpPurchaseStockService.getInfoById(id);
        ERPPurchaseStockVo vo = BeanUtil.toObject(ERPPurchaseStockVo.class , erpPurchaseStockInfo);
        vo.setDetails(erpPurchaseStockService.getDetailsByOrderId(vo.getOrderId()));
        model.addAttribute("detail" , vo);
        if(vo.getLevel() == 1)
            return "erp/product/stockIn-detail";    //采购入库
        else
            return "erp/product/stockOut-detail";    //采购退货
    }
    @RequestMapping(value = {"stockOut/toEdit/{id}"})
    public String stockOutAdd(@PathVariable("id") String id,HttpServletRequest request, Model model) throws Exception{
        ERPWarehouse warehouse = getCurrentUser(ERPWarehouse.class , request);
        List<ERPWarehouse> list = new ArrayList<>();
        if (warehouse != null) {
            list.add(warehouse);
            model.addAttribute("warehouses" , list);
        }else{
            Supplier supplier = getCurrentUser(Supplier.class , request);
            model.addAttribute("warehouses" , erpWarehouseService.getERPWarehouseLevel1All(supplier.getId()));
        }
        if(!StringUtil.stringIsNullOrEmpty(id)){
            ERPPurchaseStockInfo erpPurchaseStockInfo = erpPurchaseStockService.getInfoById(id);
            ERPPurchaseStockVo vo = BeanUtil.toObject(ERPPurchaseStockVo.class , erpPurchaseStockInfo);
            vo.setDetails(erpPurchaseStockService.getDetailsByOrderId(vo.getOrderId()));
            model.addAttribute("detail" , vo);
        }
        return "erp/product/stockOut-edit";
    }
    @RequestMapping(value = {"stockOut/save"})
    public String stockOutSave(ERPPurchaseStockRo ro, HttpServletRequest request, Model model) throws Exception{
        Supplier supplier = getCurrentUser(Supplier.class , request);
        ro.setSupplierId(supplier.getId());
        ro.setSupplierName(supplier.getSupplierName());
        try {
            User user = this.getUser(request);
            erpPurchaseStockService.addERPPurchaseStockOutInfo(ro , user.getId() , user.getUserName());
        }catch (Exception e){
            logger.error(e.toString());
            return error(e.getMessage(), model , request);
        }
        return "redirect:/scms/ERPPurchaseStock/list/2.do";
    }
    @RequestMapping(value = {"print/{id}"})
    public String stockOutSave(@PathVariable("id")String id, HttpServletRequest request, Model model) throws Exception{
        ERPPurchaseStockVo vo = BeanUtil.toObject(ERPPurchaseStockVo.class , erpPurchaseStockService.getInfoById(id));
        vo.setDetails(erpPurchaseStockService.getDetailsByOrderId(vo.getOrderId()));
        model.addAttribute("detail" , vo);
        model.addAttribute("warehouse" , erpWarehouseService.getWarehouseById(vo.getWhId()));
        return "erp/product/stockOut-print";
    }

}
