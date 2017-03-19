package com.corner.pc.service.impl;

import com.corner.pc.beans.vo.ModelMsg;
import com.corner.pc.dao.PublicMapper;
import com.corner.pc.service.ABaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ABaseServiceImpl implements ABaseService {

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
