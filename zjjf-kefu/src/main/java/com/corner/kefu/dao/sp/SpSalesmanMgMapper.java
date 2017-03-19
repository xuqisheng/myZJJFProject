package com.corner.kefu.dao.sp;

import java.util.List;

import com.corner.core.beans.Salesman;
import com.corner.kefu.beans.ro.sp.SalesmanRo;
import com.corner.kefu.beans.vo.sp.SalesmanVo;


public interface SpSalesmanMgMapper {
	Salesman selectWithMobile(String mobile);

	List<SalesmanVo> getSalesmanList(SalesmanRo man);

	Integer getSalesmanListCount(SalesmanRo man);

	int getCountWithMObile(String mobile);

	Salesman getSalesman(String id);

	int deleteSalesman(String id);
}
