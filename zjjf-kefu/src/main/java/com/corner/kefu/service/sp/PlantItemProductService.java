package com.corner.kefu.service.sp;

import com.corner.core.beans.PlantItemProduct;

import java.math.BigDecimal;

/**
 * Created by MXH on 2016/10/25.
 */
public interface PlantItemProductService {
    void insertProduct(PlantItemProduct product , String plantItemId , BigDecimal pkgPrice , Integer num) throws Exception;

    void updateProduct(PlantItemProduct product);

    void insertDefaultProduct(PlantItemProduct product) throws Exception;
}
