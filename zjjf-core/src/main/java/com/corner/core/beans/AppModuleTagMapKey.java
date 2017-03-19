package com.corner.core.beans;

public class AppModuleTagMapKey {
    private String itemTagId;

    private String moduleId;

    public String getItemTagId() {
        return itemTagId;
    }

    public void setItemTagId(String itemTagId) {
        this.itemTagId = itemTagId == null ? null : itemTagId.trim();
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }
}