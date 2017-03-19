package com.corner.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.corner.account.beans.ro.AccounterCondition;
import com.corner.account.dao.AccounterMgMapper;
import com.corner.account.dao.AuthorityMapper;
import com.corner.account.service.AccounterMgService;
import com.corner.core.beans.Accounter;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.AccounterMapper;
import com.corner.core.utils.safe.MD5;

@Service
public class AccounterMgServiceImpl extends BaseServiceImpl implements AccounterMgService {

	@Autowired
	AccounterMapper accounterMapper;

	@Autowired
	AccounterMgMapper accounterMgMapper;

	@Autowired
	AuthorityMapper authorityMapper;

	@Override
	public Pager<Accounter> getAccounterPageList(AccounterCondition command) {
		List<Accounter> list = accounterMgMapper.getPageList(command);
		int size = accounterMgMapper.getPageListSize(command);
		return new Pager<Accounter>(size, list);
	}

	@Override
	public ModelMsg updateByPrimaryKeySelective(Accounter accounter) {
		if (accounter != null && accounter.getPassword() != null && accounter.getPassword().length() < 32) {
			accounter.setPassword(MD5.StringToMd5(accounter.getPassword()));
		}
		int count = accounterMapper.updateByPrimaryKeySelective(accounter);
		if (count == 1) {
			return new ModelMsg(true, "修改成功");
		} else {
			return new ModelMsg(false, "修改失败");
		}
	}

	@Override
	public ModelMsg addObject(Accounter user) {
		if (user == null || user.getId() == null || user.getUserName() == null || user.getMobile() == null) {
			return new ModelMsg(false, "修改失败");
		} else {
			Accounter accounter1 = authorityMapper.getUserByUserCredential(user.getUserName());
			Accounter accounter2 = authorityMapper.getUserByUserCredential(user.getMobile());
			if (accounter1 ==null && accounter2==null) {
				int count = accounterMapper.insertSelective(user);
				if (count == 1) {
					return new ModelMsg(true, "修改成功");
				} else {
					return new ModelMsg(false, "修改异常");
				}
			}else{
				return new ModelMsg(false, "用户名重复");
			}
		}
	}

	@Override
	public List<String> getRolesById(String id) {
		if(id == null){
			return null;
		}else{
			return accounterMgMapper.getRolesById(id);
		}
	}

	@Override
	public ModelMsg saveUserRoles(String userId, String roleIds) {
		if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(roleIds)){
			return new ModelMsg(false, "修改失败");
		}else{
			String[] ids = roleIds.split(",");
			if(ids == null || ids.length < 1){
				return new ModelMsg(false, "修改失败");
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", userId);
			map.put("roleIds", ids);
			int count = accounterMgMapper.saveUserRoles(map);
			if (count >= 0) {
				return new ModelMsg(true, "修改成功");
			} else {
				return new ModelMsg(false, "修改异常");
			}
		}
	}

}
