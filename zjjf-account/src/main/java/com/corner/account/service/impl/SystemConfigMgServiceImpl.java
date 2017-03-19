package com.corner.account.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.account.beans.ro.SystemConfigCondition;
import com.corner.account.config.SessionConfig;
import com.corner.account.dao.SettlementConfMgMapper;
import com.corner.account.service.SystemConfigMgService;
import com.corner.core.beans.Accounter;
import com.corner.core.beans.SettlementConf;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.SettlementConfMapper;

@Service
public class SystemConfigMgServiceImpl extends BaseServiceImpl implements SystemConfigMgService {

	@Autowired SettlementConfMapper settlementConfMapper;
	
	@Autowired SettlementConfMgMapper settlementConfMgMapper;

	@Override
	public ModelMsg updateByPrimaryKeySelective(SettlementConf settlementConf) {
		int count = settlementConfMapper.updateByPrimaryKeySelective(settlementConf);
		if(count == 1){
			return new ModelMsg(true,"修改成功");	
		}else{
			return new ModelMsg(false,"修改失败");	
		}
	}

	@Override
	public Pager<SettlementConf> getSettlementConfPageList(SystemConfigCondition command) {
		List<SettlementConf> list=settlementConfMgMapper.getPageList(command);
		int size = settlementConfMgMapper.getPageListSize(command);
		return new Pager<SettlementConf>(size,list);
	}

	@Override
	public ModelMsg addObject(SettlementConf settlementConf) {
		if(settlementConf == null || settlementConf.getId() == null){
			return new ModelMsg(false,"修改失败");
		}else{
			Accounter current = (Accounter)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(current==null){
				return new ModelMsg(false,"请登录后进行操作");
			}
			int count = settlementConfMapper.insertSelective(settlementConf);
			if(count == 1){
				return new ModelMsg(true,"修改成功");
			}else{
				return new ModelMsg(false,"修改异常");	
			}
		}
	}

}
