package com.corner.scms.controller.erp;

import com.corner.core.beans.*;
import com.corner.core.beans.vo.Pager;
import com.corner.core.enums.CheckStatus;
import com.corner.core.enums.ProfitType;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPProfitRo;
import com.corner.scms.beans.vo.erp.ERPProfitVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.erp.ERPProfitService;
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
@RequestMapping("/scms/ERPProfit")
public class ERPProfitController extends ScmsBaseWebController {
    private static Logger logger = LoggerFactory.getLogger(ERPProfitController.class);
    @Autowired
    ERPProfitService ERPProfitService;
    @Autowired
    ERPWarehouseService erpWarehouseService;
    @RequestMapping(value = {"list" , ""})
    public String stockInList(ERPProfitRo commod , HttpServletRequest request, Model model) throws Exception{
        Supplier supplier = getCurrentUser(Supplier.class , request);
        commod.setSupplierId(supplier.getId());
        commod.setSortName("addTime");
        commod.setSortOrder("DESC");
        if(commod.getEndTime() != null){
            commod.setEndTime(DateUtil.updateDate(commod.getEndTime() , 1));
        }
        Pager<ERPProfitInfo> list =  ERPProfitService.getInfoListByRo(commod);
        if(commod.getEndTime() != null){
            commod.setEndTime(DateUtil.updateDate(commod.getEndTime() , -1));
        }
        model.addAttribute("commod" , commod);
        pageUtil(commod.getPageIndex() , list.getTotalSize() , commod.getPageSize() , request , model);
        model.addAttribute("checkStatus" , CheckStatus.values());
        model.addAttribute("profitTypes" , ProfitType.values());

        model.addAttribute("list" , list.getList());
        return "erp/profit/list";

    }
    @RequestMapping(value = {"check/{id}/{checkStatus}"})
    public Object stockInList(@PathVariable("id") String id ,@PathVariable("checkStatus") Byte checkStatus , HttpServletRequest request, Model model){
        if(StringUtil.stringIsNullOrEmpty(id , CheckStatus.getName(checkStatus)))
            return ResponseUtils.sendMsg(false , "审核失败：缺少必要参数");
        try {
            User user = this.getUser(request);
            ERPProfitService.bacthCheck(checkStatus , user.getId() , user.getUserName() , id);
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseUtils.sendMsg(false , "审核失败："+e.getMessage());
        }
        return ResponseUtils.sendMsg(true , "交易成功");
    }
    @RequestMapping(value = {"bacthCheck/{checkStatus}"})
    @ResponseBody
    public Object stockInList(ERPProfitRo ro ,@PathVariable("checkStatus") Byte checkStatus, HttpServletRequest request, Model model){
        if(StringUtil.stringIsNullOrEmpty(ro.getIds()))
            return ResponseUtils.sendMsg(false , "审核失败：缺少必要参数");
        else if(StringUtil.stringIsNullOrEmpty(CheckStatus.getName(checkStatus)))
            return ResponseUtils.sendMsg(false , "审核失败：缺少必要参数");
        try {
            User user = this.getUser(request);
            ERPProfitService.bacthCheck(checkStatus , user.getId() , user.getUserName() , ro.getIds());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseUtils.sendMsg(false , "审核失败："+e.getMessage());
        }
        return ResponseUtils.sendMsg(true , "交易成功");
    }
    @RequestMapping(value = {"bacthDelete"})
    @ResponseBody
    public Object bacthDelete(ERPProfitRo ro ,HttpServletRequest request, Model model){
        if(StringUtil.stringIsNullOrEmpty(ro.getIds()))
            return ResponseUtils.sendMsg(false , "审核失败：缺少必要参数");
        try {
            User user = this.getUser(request);
            ERPProfitService.bacthDelete(user.getId() , user.getUserName() , ro.getIds());
        }catch (Exception e){
            logger.error(e.toString());
            return ResponseUtils.sendMsg(false , "删除失败："+e.getMessage());
        }
        return ResponseUtils.sendMsg(true , "交易成功");
    }
    @RequestMapping(value = {"detail/{id}"})
    public Object stockInList(@PathVariable("id") String id, HttpServletRequest request, Model model) throws Exception{
        ERPProfitInfo ERPProfitInfo = ERPProfitService.getInfoById(id);
        ERPProfitVo vo = BeanUtil.toObject(ERPProfitVo.class , ERPProfitInfo);
        vo.setDetails(ERPProfitService.getDetailsByOrderId(vo.getOrderId()));
        model.addAttribute("detail" , vo);
        return "erp/profit/detail";
    }
    @RequestMapping(value = {"toEdit/{id}"})
    public String stockOutAdd(@PathVariable("id") String id,HttpServletRequest request, Model model) throws Exception{
        model.addAttribute("profitTypes" , ProfitType.values());

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
            ERPProfitInfo ERPProfitInfo = ERPProfitService.getInfoById(id);
            ERPProfitVo vo = BeanUtil.toObject(ERPProfitVo.class , ERPProfitInfo);
            vo.setDetails(ERPProfitService.getDetailsByOrderId(vo.getOrderId()));
            model.addAttribute("detail" , vo);
        }
        return "erp/profit/edit";
    }

    @RequestMapping(value = {"save"})
    public String stockOutSave(ERPProfitRo ro, HttpServletRequest request, Model model) throws Exception{
        Supplier supplier = getCurrentUser(Supplier.class , request);
        ro.setSupplierId(supplier.getId());
        ro.setSupplierName(supplier.getSupplierName());
        try {
            User user = this.getUser(request);
            ERPProfitService.addERPProfitInfo(ro , user.getId() , user.getUserName());
        }catch (Exception e){
            logger.error(e.toString());
            return error(e.getMessage(), model , request);
        }
        return "redirect:list.do";
    }

}
