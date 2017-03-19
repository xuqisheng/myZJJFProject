package com.corner.kefu.service.sp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corner.core.beans.Salesman;
import com.corner.kefu.beans.ro.sp.SalesmanRo;
import com.corner.kefu.beans.vo.sp.SalesmanVo;
/**
 * 业务员service
 * @author aimee at 2015年6月9日上午11:34:54
 * @email 1297579898@qq.com
 */
@Service
public interface SpSalesmanService{

	/**
	 * 根据业务员手机号查询
	 * @author aimee at 2015年6月10日上午11:12:13
	 * @email 1297579898@qq.com
	 * @param mobile
	 * @return
	 */
	public Salesman selectWithMobile(String mobile);

	public List<SalesmanVo> getSalesmanList(SalesmanRo man);

	public Integer getSalesmanListCount(SalesmanRo man);

	public int getCountWithMObile(String mobile);

	public Salesman getSalesman(String id);

	public int save(Salesman man);

	public int updateSalesman(Salesman man);

	public int deleteSalesman(String id);
}
