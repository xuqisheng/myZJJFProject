package com.corner.core.beans;

public class AppModuleCfgMapKey {
    private String moduleId;

    private Integer appCfgId;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    public Integer getAppCfgId() {
        return appCfgId;
    }

    public void setAppCfgId(Integer appCfgId) {
        this.appCfgId = appCfgId;
    }
}