package org.zjjf.mogodb.test;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

/**
 * 把JavaBean字段名称转换为Mongodb里面的字段名称；
 * 把Mongodb的数据转换为对象；
 * 约束规范：
 *    mongodb 字段全部小写，字段名称不宜过长。
 * @author Administrator
 *
 */
public class SalesmanMgSwitch implements ISwitch{
	
	public Document switchBean2Mongo(Object o) {
		SalesmanMgMO obj = (SalesmanMgMO)o;
		Document d = new Document();
		d.append("oid", obj.getId());
		d.append("address", obj.getAddress());
		d.append("area", obj.getArea());
		d.append("birthday", obj.getBirthday());
		d.append("city", obj.getCity());
		d.append("createby", obj.getCreateBy());
		d.append("CreateTime", obj.getCreateTime());
		d.append("Credit", obj.getCredit());
		d.append("deptid", obj.getDeptId());
		d.append("deptname", obj.getDeptName());
		d.append("deviceuuid", obj.getDeviceUUID());
		d.append("email", obj.getEmail());
		d.append("gender", obj.getGender());
		d.append("identitycard", obj.getIdentitycard());
		d.append("intensity", obj.getIntensity());
		d.append("isdelete", obj.getIsDelete());
		d.append("ismanager", obj.getIsManager());
		d.append("lastip", obj.getLastIP());
		d.append("lasttime", obj.getLastTime());
		d.append("mobile", obj.getMobile());
		d.append("monthcredit", obj.getMonthCredit());
		d.append("negativeidentitycard", obj.getNegativeidentitycard());
		d.append("nickname", obj.getNickName());
		d.append("olddeptId", obj.getOldDeptId());
		d.append("password", obj.getPassword());
		d.append("positiveidentitycard", obj.getPositiveidentitycard());
		d.append("postname", obj.getPostName());
		d.append("posttype", obj.getPostType());
		d.append("province", obj.getProvince());
		d.append("regdays", obj.getRegDays());
		d.append("regip", obj.getRegIP());
		d.append("registration", obj.getRegistration());
		d.append("regtime", obj.getRegTime());
		d.append("token", obj.getToken());
		d.append("updateby", obj.getUpdateBy());
		d.append("updatetime", obj.getUpdateTime());
		d.append("url", obj.getUrl());
		d.append("username", obj.getUserName());
		d.append("usertype", obj.getUserType());
		return d;
	}

	@Override
	public List<Document> switchList(List<Object> list) {
		List<Document> listDoc = null;
		if(null != list && list.size() > 0){
			listDoc = new ArrayList<Document>();
		}
		for(Object one:list){
			listDoc.add(this.switchBean2Mongo(one));
		}
		return listDoc;
	}
	

}
