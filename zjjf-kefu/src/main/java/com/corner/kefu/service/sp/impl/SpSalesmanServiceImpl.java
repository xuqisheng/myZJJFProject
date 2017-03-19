package com.corner.kefu.service.sp.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Salesman;
import com.corner.core.dao.SalesmanMapper;
import com.corner.kefu.beans.ro.sp.SalesmanRo;
import com.corner.kefu.beans.vo.sp.SalesmanVo;
import com.corner.kefu.dao.sp.SpSalesmanMgMapper;
import com.corner.kefu.service.sp.SpSalesmanService;
/**
 * 业务员service
 * @author aimee at 2015年6月9日上午11:34:54
 * @email 1297579898@qq.com
 */
@Service
public class SpSalesmanServiceImpl implements SpSalesmanService{
	@Autowired
	SpSalesmanMgMapper salesmanMgMapper;
	
	@Autowired
	SalesmanMapper salesmanMapper;
	/**
	 * 根据业务员手机号查询
	 * @author aimee at 2015年6月10日上午11:12:13
	 * @email 1297579898@qq.com
	 * @param mobile
	 * @return
	 */
	@Override
	public Salesman selectWithMobile(String mobile){
		return salesmanMgMapper.selectWithMobile(mobile);
	}
	@Override
	public List<SalesmanVo> getSalesmanList(SalesmanRo man) {
		// TODO Auto-generated method stub
		return salesmanMgMapper.getSalesmanList(man);
	}
	@Override
	public Integer getSalesmanListCount(SalesmanRo man) {
		// TODO Auto-generated method stub
		return salesmanMgMapper.getSalesmanListCount(man);
	}
	@Override
	public int getCountWithMObile(String mobile) {
		// TODO Auto-generated method stub
		return salesmanMgMapper.getCountWithMObile(mobile);
	}
	@Override
	public Salesman getSalesman(String id) {
		// TODO Auto-generated method stub
		return salesmanMapper.selectByPrimaryKey(id);
	}
	@Override
	public int save(Salesman man) {
		// TODO Auto-generated method stub
		man.setRegTime(new Date());
		man.setLastTime(new Date());
		return salesmanMapper.insertSelective(man);
	}
	@Override
	public int updateSalesman(Salesman man) {
		// TODO Auto-generated method stub
		return salesmanMapper.updateByPrimaryKeySelective(man);
	}
	@Override
	public int deleteSalesman(String id) {
		// TODO Auto-generated method stub
		return salesmanMgMapper.deleteSalesman(id);
	}
	
	
}
