package com.corner.kefu.service.sp.impl;

import com.corner.core.beans.PlantItemProduct;
import com.corner.core.beans.PlantItemProductMap;
import com.corner.core.dao.PlantItemProductMapMapper;
import com.corner.core.dao.PlantItemProductMapper;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.service.sp.PlantItemProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by MXH on 2016/10/25.
 */
@Service
public class PlantItemProductServiceImpl implements PlantItemProductService {
    @Autowired
    PlantItemProductMapMapper plantItemProductMapMapper;
    @Autowired
    PlantItemProductMapper plantItemProductMapper;
    @Override
    public void insertProduct(PlantItemProduct product , String plantItemId , BigDecimal pkgPrice , Integer num) throws Exception{
        if(StringUtil.stringIsNullOrEmpty(plantItemId))
            throw new Exception("参数有误");
        else if(pkgPrice == null || pkgPrice.compareTo(new BigDecimal("0")) < 1)
            throw new Exception("参数有误");
        product.setAddtime(new Date());
        plantItemProductMapper.insertSelective(product);

        PlantItemProductMap map = new PlantItemProductMap();
        map.setProductId(product.getId());
        map.setPlantItemId(plantItemId);
        map.setPkgPrice(pkgPrice);
        map.setNum(num);
        plantItemProductMapMapper.insertSelective(map);
    }

    @Override
    public void updateProduct(PlantItemProduct product) {

    }

    @Override
    public void insertDefaultProduct(PlantItemProduct product) throws Exception{
        product.setBuyStartTime(new Date());
        product.setBuyEndTime(DateUtil.getMaxDate());
        product.setUpTime(product.getBuyStartTime());
        product.setDownTime(product.getBuyEndTime());
        insertProduct(product , product.getId() , product.getProductPrice() , 0);
    }
}
