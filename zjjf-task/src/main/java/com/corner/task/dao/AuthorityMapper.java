package com.corner.task.dao;

import com.corner.task.beans.Admin;
import com.corner.task.beans.ro.LoginRo;


public interface AuthorityMapper {
	Admin getUserByAdminCredential(LoginRo loginRo);
}