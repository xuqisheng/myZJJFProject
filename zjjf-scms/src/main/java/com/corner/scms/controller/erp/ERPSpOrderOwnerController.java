package com.corner.scms.controller.erp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.corner.core.beans.ERPMarketStockInfo;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.service.erp.ERPMarketStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corner.core.beans.ERPWarehouse;
import com.corner.core.beans.Supplier;
import com.corner.core.enums.SpOrderOwnerCash;
import com.corner.core.enums.SpOrderOwnerType;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPSpOrderOwnerRo;
import com.corner.scms.beans.vo.OrderInfoVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.erp.ERPSpOrderOwnerService;
import com.corner.scms.service.erp.ERPWarehouseService;
import com.corner.scms.service.sp.ScmsOrderInfoMgService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mxh on 2016/9/5.
 */
@Controller
@RequestMapping("/scms/ERPSpOrderOwner")
public class ERPSpOrderOwnerController extends ScmsBaseWebController {
    private static Logger logger = LoggerFactory.getLogger(ERPSpOrderOwnerController.class);
    @Autowired
    ScmsOrderInfoMgService scmsOrderInfoMgService;
    @Autowired
    ERPWarehouseService erpWarehouseService;
    @Autowired
    ERPMarketStockService erpMarketStockService;
    @Autowired
    ERPSpOrderOwnerService erpSpOrderOwnerService;
    @RequestMapping("toAddERPSpOrderOwner/{orderId}")
    public String toAddERPSpOrderOwner(@PathVariable("orderId") String orderId , HttpServletRequest request, Model model) throws Exception{
        if(StringUtil.stringIsNullOrEmpty(orderId))
            return error("请传入正确的订单号" ,  model ,request);
        OrderInfoVo orderInfoVo = scmsOrderInfoMgService.findByOrId(orderId);
        if(orderInfoVo == null)
            return error("订单信息不存在" ,  model ,request);

        ERPWarehouse warehouse = getCurrentUser(ERPWarehouse.class , request);
        List<ERPWarehouse> list = new ArrayList<>();
        if (warehouse != null) {
            list.add(warehouse);
            model.addAttribute("warehouses" , list);
        }else{
            Supplier supplier = getCurrentUser(Supplier.class , request);
            model.addAttribute("warehouses" , erpWarehouseService.getERPWarehouseLevel1All(supplier.getId()));
        }

        model.addAttribute("ownerDetail" , erpSpOrderOwnerService.getOwnerDetail(orderInfoVo.getId()));
        model.addAttribute("detail" , orderInfoVo);
        model.addAttribute("ownerTypes" , SpOrderOwnerType.values());
        model.addAttribute("ownerCashs" , SpOrderOwnerCash.values());
        return "order/order-owner-edit";
    }
    @RequestMapping("addERPSpOrderOwner/{orderId}")
    public String addERPSpOrderOwner(@PathVariable("orderId") String orderId , ERPSpOrderOwnerRo ro, HttpServletRequest request, Model model){
        try {
            List<ERPMarketStockInfo> infos = erpMarketStockService.getInfoByPId(null , orderId);
            if(infos != null && infos.size() != 0){
                return error("已经生成出库单:"+infos.get(0).getOrderId()+"，请删除出库单再修改附属订单" , model , request);
            }
            erpSpOrderOwnerService.addERPSpOrderOwnerDetail(ro , orderId);
            return "redirect:/scms/orderctl/GetSpOrderInfo.do?storeid=" + orderId;
        }catch (Exception e){
            return error(e.getMessage() , model , request);
        }
    }
    @RequestMapping("editERPSpOrderOwner/{orderId}")
    @ResponseBody
    public Object editERPSpOrderOwner(@PathVariable("orderId") String orderId , ERPSpOrderOwnerRo ro, HttpServletRequest request, Model model){
        try {
            erpSpOrderOwnerService.addERPSpOrderOwnerDetail(ro , orderId);

            erpMarketStockService.updateMarketByOwner(orderId);
            return ResponseUtils.sendMsg(true);
        }catch (Exception e){
            return ResponseUtils.sendMsg(false , e.getMessage());
        }
    }
}
