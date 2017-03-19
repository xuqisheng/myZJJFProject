package com.zjjf.analysis.mapper.analysis;

import org.apache.ibatis.annotations.Param;

import com.zjjf.analysis.beans.analysis.supplier.SaleDailyReport;

public interface SaleDailyReportMapper {

    int insert(SaleDailyReport record);

    void update(SaleDailyReport record);
    
    SaleDailyReport getExitRecord(@Param("supplierId") String supplierId, @Param("spGroupId") Integer spGroupId, @Param("dayTime") String dayTime);
}