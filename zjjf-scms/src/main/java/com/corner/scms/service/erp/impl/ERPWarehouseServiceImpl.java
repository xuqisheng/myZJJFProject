package com.corner.scms.service.erp.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.scms.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ERPPhysicsStock;
import com.corner.core.beans.ERPWarehouse;
import com.corner.core.beans.ERPWarehouseExample;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ERPWarehouseMapper;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPWarehouseRo;
import com.corner.scms.beans.vo.erp.ERPWarehouseVo;
import com.corner.scms.dao.erp.ERPWarehouseMgMapper;
import com.corner.scms.service.erp.ERPWarehouseService;
@Service
public class ERPWarehouseServiceImpl implements ERPWarehouseService {

  @Autowired
  ERPWarehouseMgMapper erpWarehouseMgMapper;
  @Autowired
  ERPWarehouseMapper erpWarehouseMapper;

  //仓库&库区&库位list
  @Override
  public Pager<ERPWarehouseVo> getWarehouseBySupplierId(ERPWarehouseRo warehouseRo) {
    List<ERPWarehouseVo> list = null;
    int num = 0;
    if(!StringUtil.stringIsNullOrEmpty(warehouseRo.getNumAndName())){
      warehouseRo.setNumAndName(warehouseRo.getNumAndName().trim());
    }
    if(warehouseRo.getWhLevel() != null){
      if(warehouseRo.getWhLevel() == 1){
        //仓库列表
        list = erpWarehouseMgMapper.getWarehouseBySupplierId(warehouseRo);
        num = erpWarehouseMgMapper.getWarehouseBySupplierIdCount(warehouseRo);
      }else if(warehouseRo.getWhLevel() == 2){
        //库区列表
        list = erpWarehouseMgMapper.getWhareaBySupplierId(warehouseRo);
        num = erpWarehouseMgMapper.getWhareaBySupplierIdCount(warehouseRo);
      }else if(warehouseRo.getWhLevel() == 3){
        //库位列表
        list = erpWarehouseMgMapper.getWhpositionBySupplierId(warehouseRo);
        num = erpWarehouseMgMapper.getWhpositionBySupplierIdCount(warehouseRo);
      }

    }
    return new Pager<>(num, list);
  }

  //仓库元素级联
  @Override
  public List<ERPWarehouseVo> getWarehouseByUpId(String upId,String supplierId) {
    Map<String, Object> map = new HashMap<>();
    map.put("upId", upId);
    map.put("supplierId", supplierId);
    List<ERPWarehouseVo> whList = erpWarehouseMgMapper.getWarehouseByUpId(map);
    return whList;
  }

  //根据库位查询商品库存
  private List<ERPPhysicsStock> getPhysicsStockByUpId(String id, String supplierId) {
    Map<String, Object> map = new HashMap<>();
    map.put("id", id);
    map.put("supplierId", supplierId);
    return erpWarehouseMgMapper.getPhysicsStockByUpId(map);
  }

  //组装树
  @Override
  public List<ERPWarehouseVo> createTree(String upId, String supplierId) {
    //获取仓库
    List<ERPWarehouseVo> whList = this.getWarehouseByUpId(upId,supplierId);
    if(whList != null && whList.size()>0){
      for (ERPWarehouseVo wh : whList) {
        wh.setTreeList(this.getWarehouseByUpId(wh.getId(),supplierId));
      }
    }
    return whList;
  }

  //生成仓库编号
  private String createCode(String code, Byte whLevel) {
    String codeStr = "";
    if(code == null){
      String str = "";
      if(whLevel == 1){
        str="CK";
      }else if(whLevel == 2){
        str="KQ";
      }else if(whLevel == 3){
        str="KW";
      }
      //获取最大值
      String whCode = erpWarehouseMgMapper.getMaxCode(whLevel);
      if(!StringUtil.stringIsNullOrEmpty(whCode)){
        //生成序列值（待完成）

        codeStr = "";
      }else{
        codeStr = str+00001;
      }
      return codeStr;
    }else{
      return null;
    }
  }

  //添加
  @Override
  public boolean addWarehouse(ERPWarehouseRo warehouseRo) {
    warehouseRo.setAddTime(new Date());
    warehouseRo.setUpdateTime(new Date());
    Byte whLevel = warehouseRo.getWhLevel();
    String str = "";
    if(whLevel == 1){
      warehouseRo.setUpId("0");
    }
    //生成仓库编号
    //		String code = createCode(warehouseRo.getCode(),whLevel);
    //		if(StringUtil.stringIsNullOrEmpty(code)){
    //			warehouseRo.setCode(code);
    //		}
    int num = erpWarehouseMapper.insertSelective(warehouseRo);
    if(num>0){
      return true;
    }else{
      return false;
    }
  }

  //编辑
  @Override
  public boolean editWarehouse(ERPWarehouseRo warehouseRo) {
    warehouseRo.setUpdateTime(new Date());
    Byte whLevel = warehouseRo.getWhLevel();
    //生成仓库编号
    //		String code = createCode(warehouseRo.getCode(),whLevel);
    //		if(StringUtil.stringIsNullOrEmpty(code)){
    //			warehouseRo.setCode(code);
    //		}
    int num = erpWarehouseMapper.updateByPrimaryKeySelective(warehouseRo);
    if(num>0){
      return true;
    }else{
      return false;
    }
  }

  @Override
  public ERPWarehouseVo getWarehouseById(Map<String, Object> map) {
    return erpWarehouseMgMapper.getWarehouseById(map);
  }

  @Override
  public ModelMsg delWarehouse(Byte level, String id,String supplierId) {
    //先确定有没有下级
    List<ERPWarehouseVo> warehouseList = null;
    List<ERPPhysicsStock> physicsList = null;
    if(level == 1){
      warehouseList = this.getWarehouseByUpId(id, supplierId);
      if(warehouseList != null && warehouseList.size() > 0){
        return new ModelMsg(false, "仓库下还存在库区,请删除库区后再操作");
      }
    }else if(level == 2){
      warehouseList = this.getWarehouseByUpId(id, supplierId);
      if(warehouseList != null && warehouseList.size() > 0){
        return new ModelMsg(false, "库区下还存在库位,请删除库位后再操作");
      }
    }else if(level == 3){
      physicsList = this.getPhysicsStockByUpId(id, supplierId);
      if(physicsList != null && physicsList.size() > 0){
        return new ModelMsg(false, "仓位下还存在商品库存,请删除商品库存后后再操作");
      }
    }else{
      return new ModelMsg(false, "数据有误");
    }
    Map<String, Object> map = new HashMap<>();
    map.put("id", id);
    map.put("supplierId", supplierId);
    int num = erpWarehouseMgMapper.delWarehouse(map);
    if(num > 0){
      return new ModelMsg(true,null);
    }else{
      return new ModelMsg(false, "删除失败");
    }

  }


  @Override
  public List<ERPWarehouse> getWarehouseLevel3(String whId) throws Exception{
    ERPWarehouseExample example = new ERPWarehouseExample();
    example.createCriteria().andWhLevelEqualTo((byte)2).andUpIdEqualTo(whId);
    List<ERPWarehouse> list2 = erpWarehouseMapper.selectByExample(example);
    if(list2 == null || list2.size() == 0)
      throw new Exception("无库区信息");
    List<String> level2Ids = new ArrayList<>();
    for (ERPWarehouse warehouse : list2) {
      level2Ids.add(warehouse.getId());
    }
    example = new ERPWarehouseExample();
    example.createCriteria().andWhLevelEqualTo((byte)3).andUpIdIn(level2Ids);
    List<ERPWarehouse> list3 = erpWarehouseMapper.selectByExample(example);
    if(list3 == null || list3.size() == 0)
      throw new Exception("无库位信息");
    return list3;
  }

  @Override
  public ERPWarehouse getWarehouseById(String whId) throws Exception {
    if (StringUtil.stringIsNullOrEmpty(whId))
      throw new Exception("传入参数错误");
    ERPWarehouse warehouse = erpWarehouseMapper.selectByPrimaryKey(whId);
    if (warehouse == null)
      throw new Exception("未找到对应信息");
    return warehouse;
  }

  @Override
  public ERPWarehouse getTopWarehouseByLevel3Id(String whId) throws Exception{
    if (StringUtil.stringIsNullOrEmpty(whId)){
      throw new Exception("传入参数错误");
    }
    return getWarehouseById(getWarehouseById(getWarehouseById(whId).getUpId()).getUpId());
  }

  @Override
  public List<ERPWarehouse> getERPWarehouseLevel1All(String supplierId) throws Exception{
    Byte whLevel = 1;
    return getERPWarehouseList(supplierId , whLevel , null , null);
  }
  private List<ERPWarehouse> getERPWarehouseList(String supplierId , Byte level , String upId , String name) throws Exception{
    ERPWarehouseExample example = new ERPWarehouseExample();
    ERPWarehouseExample.Criteria criteria = example.createCriteria();
    criteria.andIsDeleteEqualTo(false);
    if(level != null && level != 0)
      criteria.andWhLevelEqualTo(level);
    if(!StringUtil.stringIsNullOrEmpty(supplierId))
      criteria.andSupplierIdEqualTo(supplierId);
    if(!StringUtil.stringIsNullOrEmpty(upId))
      criteria.andUpIdEqualTo(upId);
    if(!StringUtil.stringIsNullOrEmpty(name ==null ? null : name.trim()))
      criteria.andNameLike("%"+name.trim()+"%");
    return selectByExample(example);
  }
  private List<ERPWarehouse> selectByExample(ERPWarehouseExample example) throws Exception{
    List<ERPWarehouse> list = erpWarehouseMapper.selectByExample(example);
//    if (list == null || list.size() == 0){
//      throw new Exception("无对应数据");
//    }
    return list;
  }
  /**
   *
   * @Title: getWarehouseLevel3ByLevel1Id
   * @Description:更具level1的仓库id 获取旗下所有level=3 的仓库集合
   * @param @param wareHouseId
   * @param @return
   * @return List<ERPWarehouse>    返回类型
   *@author 杨开泰 yangkaitai@izjjf.cn
   * @throws
   */
  @Override
  public List<ERPWarehouseVo> getWarehouseLevel3ByLevel1Id(String wareHouseId) {
    return erpWarehouseMgMapper.getWarehouseLevel3ByLevel1Id(wareHouseId);
  }

  @Override
  public List<ERPWarehouseVo> getWarehouseLevel3ByLevel1Id(Map<String, Object> map) throws Exception{
    String wareHouseId = map.get("wareHouseId").toString();
    String whName = map.get("whName").toString();
    ERPWarehouse warehouse1 = getWarehouseById(wareHouseId);
    List<ERPWarehouseVo> list = new ArrayList<>();
    for (ERPWarehouse warehouse2 : getERPWarehouseList(null, Byte.valueOf("2"), wareHouseId , null)) {
      for (ERPWarehouse warehouse3 : getERPWarehouseList(null, Byte.valueOf("3"), warehouse2.getId() , whName)) {
        ERPWarehouseVo vo = BeanUtil.toObject(ERPWarehouseVo.class , warehouse3);
        vo.setWhName(warehouse1.getName());
        vo.setWhAreaName(warehouse2.getName());
        list.add(vo);
      }
    }
    return list;
  }
}
