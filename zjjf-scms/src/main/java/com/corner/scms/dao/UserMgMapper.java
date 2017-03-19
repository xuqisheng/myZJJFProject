package com.corner.scms.dao;

import com.corner.core.beans.User;
import com.corner.scms.beans.ro.auth.LoginRo;

import java.util.List;

public interface UserMgMapper {
    List<User> selectUser(LoginRo ro);
    User selectUserBySupplierId(String spId);
}