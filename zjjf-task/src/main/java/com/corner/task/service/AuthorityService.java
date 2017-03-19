package com.corner.task.service;

import com.corner.task.beans.Admin;
import com.corner.task.beans.ro.LoginRo;

public interface AuthorityService {

	Admin getUserByAdminCredential(LoginRo loginRo);
}
