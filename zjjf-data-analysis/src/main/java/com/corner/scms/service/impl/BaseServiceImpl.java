package com.corner.scms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.msg.ModelMsg;
import com.corner.scms.dao.PublicMapper;
import com.corner.scms.service.BaseService;

@Service
public class BaseServiceImpl implements BaseService {

	@Autowired PublicMapper publicMapper;

	@Override
	public ModelMsg deleteObjects(String tableName, String[] array) {
		if(array == null || array.length < 1){
			return new ModelMsg(false,"修改失败");
		}else{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("tableName", tableName);
			params.put("ids", array);
			int count = publicMapper.deleteObjects(params);
			if(count == array.length){
				return new ModelMsg(true,"修改成功");	
			}else{
				return new ModelMsg(false,"修改异常");	
			}
		}
	}


}
