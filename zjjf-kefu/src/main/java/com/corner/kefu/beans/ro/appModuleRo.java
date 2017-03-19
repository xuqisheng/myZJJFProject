package com.corner.kefu.beans.ro;


import com.corner.core.beans.ro.ABaseRo;

import java.io.Serializable;


public class appModuleRo extends ABaseRo implements Serializable {

    private String  name;

    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
