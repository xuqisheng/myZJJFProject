package com.corner.kefu.dao;

import com.corner.core.utils.callable.CheckItemIsHave;
import com.corner.core.utils.callable.SocktOperateLog;

public interface CallableMgMapper {
    String socktOperateLog(SocktOperateLog socktOperateLog);

    String checkItemIsHave(CheckItemIsHave checkItemIsHave);

    String getDateTypeUUID(String tableName);
}
