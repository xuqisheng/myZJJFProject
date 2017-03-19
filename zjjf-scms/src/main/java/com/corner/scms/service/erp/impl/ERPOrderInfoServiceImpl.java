package com.corner.scms.service.erp.impl;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.*;
import com.corner.core.enums.OrderPrefix;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.safe.MD5;
import com.corner.scms.beans.ro.erp.ERPOrderDetailRo;
import com.corner.scms.beans.vo.erp.ERPOrderInfoVo;
import com.corner.scms.beans.vo.erp.ERPWarehouseUserVo;
import com.corner.scms.dao.ScmsPlantItemMgMapper;
import com.corner.scms.dao.erp.ERPManagerOrderInfoMgMapper;
import com.corner.scms.service.callable.CallAbleService;
import com.corner.scms.service.erp.*;
import com.corner.scms.service.sp.ScmsStockLogMgService;
import com.corner.scms.utils.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ERPOrderInfoServiceImpl implements ERPOrderInfoService{
    @Autowired
    ERPManagerOrderInfoMapper eRPManagerOrderInfoMapper;
    @Autowired
    ERPManagerOrderInfoMgMapper eRPManagerOrderInfoMgMapper;
    @Autowired
    ERPManagerOrderDetailMapper eRPManagerOrderDetailMapper;
    @Autowired
    ERPWarehouseUserMapper erpWarehouseUserMapper;
    @Autowired
    ERPManagerService managerService;
    @Autowired
    ItemBaseMapper itemBaseMapper;
    @Autowired
    ERPWarehouseUserMapMapper erpWarehouseUserMapMapper;
    @Autowired
    ERPPlantItemLogMapper eRPPlantItemLogMapper;
    @Autowired
    ScmsPlantItemMgMapper scmsPlantItemMgMapper;
    @Autowired
    ScmsStockLogMgService scmsStockLogMgService;
    @Autowired
    CallAbleService callAbleService;
    @Autowired
    ERPWarehouseService warehouseService;
    @Autowired
    ERPPurchaseStockService purchaseStockService;
    @Autowired
    ERPManagerItemService erpManagerItemService;
    private static Logger logger = LoggerFactory.getLogger(ERPOrderInfoServiceImpl.class);
    @Override
    public ModelMsg deleteObjects(String tableName, String[] array) {
        return null;
    }

    @Override
    public ERPManagerOrderInfo selectByOrderId(String orderId) throws Exception{
        ERPManagerOrderInfoExample example = new ERPManagerOrderInfoExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<ERPManagerOrderInfo> list = eRPManagerOrderInfoMapper.selectByExample(example);
        if(list == null || list.size() == 0){
            throw new Exception("订单信息不存在");
        }
        return list.get(0);
    }
    @Override
    public List<ERPManagerOrderDetail> selectDetailByOrderId(String orderId) throws Exception{
        ERPManagerOrderDetailExample example = new ERPManagerOrderDetailExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andIsDeleteEqualTo(false);
        List<ERPManagerOrderDetail> list = eRPManagerOrderDetailMapper.selectByExample(example);
        if(list == null || list.size() == 0){
            throw new Exception("订单信息不存在");
        }
        return list;
    }
    @Override
    public ERPManagerOrderDetail selectDetailById(String id) throws Exception{
        return eRPManagerOrderDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public Pager<ERPManagerOrderInfo> list(ERPOrderDetailRo ro) {
        Page page = PageHelper.startPage(ro.getPageIndex() == 0 ? 1:ro.getPageIndex()/10 , ro.getPageSize());
        ERPManagerOrderInfoExample example = new ERPManagerOrderInfoExample();
        example.setOrderByClause(ro.getStatus() == 0 ? "addTime DESC" : "endTime DESC");
        ERPManagerOrderInfoExample.Criteria criteria = example.createCriteria();
        ERPManagerOrderInfoExample.Criteria criteria2 = example.or();

        criteria.andStatusEqualTo(ro.getStatus());
        criteria2.andStatusEqualTo(ro.getStatus());

        criteria.andIsDeleteEqualTo(false);
        criteria2.andIsDeleteEqualTo(false);

        criteria.andSupplierIdEqualTo(ro.getSupplierId());
        criteria2.andSupplierIdEqualTo(ro.getSupplierId());

        if(!StringUtil.stringIsNullOrEmpty(ro.getWhId())){
            criteria.andWhIdEqualTo(ro.getWhId());
            criteria2.andWhIdEqualTo(ro.getWhId());
        }

        if(!StringUtil.stringIsNullOrEmpty(ro.getManagerId())){
            criteria.andManagerIdEqualTo(ro.getWhId());
            criteria2.andManagerIdEqualTo(ro.getWhId());
        }
        if(!StringUtil.stringIsNullOrEmpty(ro.getOrderId())){
            criteria.andOrderIdLike("%"+ro.getOrderId()+"%");
            criteria2.andManagerNameLike("%"+ro.getOrderId()+"%");
        }
        List<ERPManagerOrderInfo> list = eRPManagerOrderInfoMapper.selectByExample(example);
        return new Pager<ERPManagerOrderInfo>((int) page.getTotal() , list);
    }
    @Override
    public ERPOrderInfoVo detail(String orderId) throws Exception{
        ERPOrderInfoVo vo = BeanUtil.toObject(ERPOrderInfoVo.class , selectByOrderId(orderId));
        vo.setDetails(selectDetailByOrderId(orderId));
        return vo;
    }


    @Override
    public ModelMsg addOrderInfo(ERPOrderDetailRo ro) throws Exception{
        ModelMsg msg = new ModelMsg(true , "新增成功");
        List<String> managerIds = new ArrayList<>();
        for(String str : ro.getManagerIds()) {
            if (!managerIds.contains(str)) {
                managerIds.add(str);
            }
        }
        Date addTime = new Date();
        ERPManagerOrderInfo erpManagerOrderInfo = new ERPManagerOrderInfo();
        erpManagerOrderInfo.setAddTime(addTime);

        erpManagerOrderInfo.setWhId(ro.getWhId());
        ERPWarehouse warehouse = warehouseService.getWarehouseById(erpManagerOrderInfo.getWhId());
        erpManagerOrderInfo.setWhName(warehouse.getName());

        erpManagerOrderInfo.setType(ro.getType());
        erpManagerOrderInfo.setSupplierId(ro.getSupplierId());
        erpManagerOrderInfo.setGaveTime(ro.getGaveTime());
        erpManagerOrderInfo.setRemark(ro.getRemark());
        erpManagerOrderInfo.setItemPrice(new BigDecimal(0));
        ERPManager manager = new ERPManager();
        for(String str : managerIds) {
            erpManagerOrderInfo.setOrderId(callAbleService.getStockOrderId(OrderPrefix.ManagerOrderInfo));// 订单号
            erpManagerOrderInfo.setId(StringUtil.newUUID());
            erpManagerOrderInfo.setManagerId(str);
            if(manager == null || !erpManagerOrderInfo.getManagerId().equals(manager.getId()))
                manager = managerService.getERPManagerById(erpManagerOrderInfo.getManagerId());
            erpManagerOrderInfo.setManagerName(manager.getManagerName());
            erpManagerOrderInfo.setStatus(Byte.valueOf("0"));
            for (int i = 0; i < ro.getManagerIds().length; i++) {
                if(str.equals(ro.getManagerIds()[i])){
                    ERPManagerOrderDetail detail = new ERPManagerOrderDetail();

                    ItemBase itemBase = itemBaseMapper.selectByPrimaryKey(ro.getItemBaseIds()[i]);

                    detail.setName(itemBase.getName());
                    detail.setId(ro.getIds() == null || ro.getIds().length == 0 ? null : ro.getIds()[i]);
                    detail.setAddTime(new Date());
                    detail.setBarCode(itemBase.getMdseId());
                    detail.setImg(itemBase.getImgB());
                    detail.setItemBaseId(ro.getItemBaseIds()[i]);
                    detail.setSpec(itemBase.getSpec());
                    detail.setPkg(itemBase.getPkg());
                    //获取箱码
                    ItemBaseExample example = new ItemBaseExample();
                    example.createCriteria().andTgIdEqualTo(itemBase.getTgId()).andUpIdEqualTo(0);
                    List<ItemBase> list = itemBaseMapper.selectByExample(example);
                    if(list.size() == 0){
                        detail.setMdseId(detail.getMdseId());
                    }else{
                        detail.setMdseId(list.get(0).getMdseId());
                    }
                    detail.setItemId(ro.getItemIds()[i]);
                    detail.setItemCode(ro.getItemCodes()[i]);

                    detail.setId(StringUtil.newUUID());
                    detail.setOrderId(erpManagerOrderInfo.getOrderId());
                    detail.setAddTime(addTime);

                    detail.setAreaPrice(ro.getAreaPrices()[i]);
                    detail.setItemBaseId(ro.getItemBaseIds()[i]);

                    detail.setQuantity(ro.getQuantitys()[i]);
                    detail.setOperateQuantity(Short.valueOf("0"));
                    detail.setManagerId(ro.getManagerIds()[i]);
                    if(eRPManagerOrderDetailMapper.insertSelective(detail) != 1){
                        throw new RuntimeException("新增详情失败");
                    }
                    erpManagerOrderInfo.setItemPrice(erpManagerOrderInfo.getItemPrice().add(detail.getAreaPrice().multiply(new BigDecimal(detail.getQuantity()))));
                }
            }
            if(eRPManagerOrderInfoMapper.insertSelective(erpManagerOrderInfo) != 1){
                throw new RuntimeException("新增采购单失败");
            }
        }
        return msg;
    }
    @Override
    public ModelMsg addERPPurchaseStockInInfo(ERPOrderDetailRo ro, String userId, String userName) throws Exception{
        Date da = new Date();
        String orderId = callAbleService.getStockOrderId(OrderPrefix.PurchaseStockIn);
        ERPManagerOrderInfo info = selectByOrderId(ro.getOrderId());
        if(info.getStatus() == 1){
            throw new Exception("订单已入库");
        }
        ERPPurchaseStockInfo inInfo= new ERPPurchaseStockInfo();

        ERPPurchaseStockDetail inDetail = new ERPPurchaseStockDetail();

        inDetail.setOrderId(orderId);
        inDetail.setRemark(ro.getRemark());
        inDetail.setAddTime(da);

        inInfo.setWhId(info.getWhId());
        inInfo.setWhName(info.getWhName());
        inInfo.setTaskTime(new Date());
        inInfo.setManagerId(info.getManagerId());
        ERPManager manager = managerService.getERPManagerById(inInfo.getManagerId());
        inInfo.setManagerName(manager.getManagerName());
        inInfo.setManagerCode(manager.getManagerCode());

        inInfo.setSupplierId(ro.getSupplierId());
        inInfo.setSupplierName(ro.getSupplierName());
        inInfo.setpId(info.getId());
        inInfo.setpOrderId(info.getOrderId());
        inInfo.setOrderId(orderId);
        inInfo.setAddTime(da);
        inInfo.setAddUser(userId);
        inInfo.setAddUserName(userName);
        inInfo.setId(StringUtil.newUUID());
        inInfo.setRemark(ro.getRemark());
        inInfo.setItemPrice(new BigDecimal(0));
        inInfo.setItemQuantity(Short.valueOf("0"));


        for (int i = 0; i < ro.getQuantitys().length; i++) {
            ERPManagerOrderDetail erp = eRPManagerOrderDetailMapper.selectByPrimaryKey(ro.getIds()[i]);
            if(ro.getQuantitys()[i] != null && ro.getQuantitys()[i]>0){
                Short defQuantity = 0;
                inDetail.setOperateQuantity(ro.getQuantitys()[i]);
                inDetail.setId(StringUtil.newUUID());
                inDetail.setWh3Id(ro.getWarehouseIdLevel3s()[i]);
                ERPWarehouse warehouse3 = warehouseService.getWarehouseById(inDetail.getWh3Id());
                inDetail.setWh3Name(warehouse3.getName());
                ERPWarehouse warehouse2 =warehouseService.getWarehouseById(warehouse3.getUpId());
                inDetail.setWh2Name(warehouse2.getName());
                ERPWarehouse warehouse1 =warehouseService.getWarehouseById(warehouse2.getUpId());
                inDetail.setWh1Name(warehouse1.getName());
                inDetail.setName(erp.getName());
                inDetail.setpId(erp.getId());
                inDetail.setAreaPrice(ro.getAreaPrices()[i]);
                inDetail.setBarCode(erp.getBarCode());
                inDetail.setMdseId(erp.getMdseId());
                inDetail.setItemCode(erp.getItemCode());
                inDetail.setImg(erp.getImg());
                inDetail.setQuantity(erp.getQuantity());
                inDetail.setOperateQuantity(defQuantity);
                inDetail.setPrice(erp.getAreaPrice());
                inDetail.setSpec(erp.getSpec());
                inDetail.setOperateStock(ro.getQuantitys()[i]);
                inDetail.setTotalPrice(inDetail.getAreaPrice().multiply(new BigDecimal(inDetail.getOperateStock())));
                inDetail.setProductionTime(ro.getProductionDates()[i]);
                inDetail.setItemBaseId(erp.getItemBaseId());

                purchaseStockService.insertDetail(inDetail);//  添加入库单详情

                inInfo.setItemPrice(inDetail.getTotalPrice().add(inInfo.getItemPrice()));   //计算入库单总金额
                inInfo.setItemQuantity(Short.valueOf(inDetail.getOperateStock() + inInfo.getItemQuantity() + ""));//计算入库单总数量
            }
        }

        //  添加入库单信息
        purchaseStockService.insertInfo(inInfo);
        return new ModelMsg(true , "入库完成");
    }

    @Override
    public boolean checkUserName(String userName) {
        return eRPManagerOrderInfoMgMapper.checkUserName(userName) == 0;
    }

    @Override
    @Transactional
    public ModelMsg addERPWarehouseUser(ERPWarehouseUser erpWarehouse, String id) throws RuntimeException{
        if(StringUtil.stringIsNullOrEmpty(erpWarehouse.getId())){
            erpWarehouse.setPassword(MD5.StringToMd5(erpWarehouse.getPassword()));
            erpWarehouse.setAddTime(new Date());
            erpWarehouse.setId(StringUtil.newUUID());
            Integer result = erpWarehouseUserMapper.insertSelective(erpWarehouse);
            if(result == 0)
                throw new RuntimeException("插入仓库用户失败");
            ERPWarehouseUserMapKey erpWarehouseUserMapKey = new ERPWarehouseUserMapKey();
            erpWarehouseUserMapKey.setUserId(erpWarehouse.getId());
            erpWarehouseUserMapKey.setWhId(id);
            result = erpWarehouseUserMapMapper.insertSelective(erpWarehouseUserMapKey);
            if(result == 0)
                throw new RuntimeException("插入用户中间表失败");
        }else{
            erpWarehouse.setPassword(StringUtil.stringIsNullOrEmpty(erpWarehouse.getPassword()) ? null : MD5.StringToMd5(erpWarehouse.getPassword()));
            erpWarehouseUserMapper.updateByPrimaryKeySelective(erpWarehouse);
        }
        return new ModelMsg(true , "添加成功");
    }

    @Override
    public Pager<ERPWarehouseUserVo> erpWarehouseUserListPage(ERPOrderDetailRo erpOrderDetailRo , String supplierId) {
        erpOrderDetailRo.setSupplierId(supplierId);
        erpOrderDetailRo.setSortName("a.addTime");
        erpOrderDetailRo.setSortOrder("DESC");
        List<ERPWarehouseUserVo> list = eRPManagerOrderInfoMgMapper.getERPWarehouseUserListPage(erpOrderDetailRo);
        Integer count = eRPManagerOrderInfoMgMapper.getERPWarehouseUserListPageCount(erpOrderDetailRo);
        return new Pager<>(count , list);
    }

    @Override
    public ERPWarehouseUserVo getERPWarehouseUserById(String id) {
        return eRPManagerOrderInfoMgMapper.getERPWarehouseUserById(id);
    }
    @Override
    public void updateDetail(ERPManagerOrderDetail detail) throws Exception {
        if(eRPManagerOrderDetailMapper.updateByPrimaryKeySelective(detail) != 1)
            throw new Exception("修改失败");
    }
    @Override
    public void updateInfo(ERPManagerOrderInfo info) throws Exception {
        if(eRPManagerOrderInfoMapper.updateByPrimaryKeySelective(info) != 1)
            throw new Exception("修改失败");
    }
}
