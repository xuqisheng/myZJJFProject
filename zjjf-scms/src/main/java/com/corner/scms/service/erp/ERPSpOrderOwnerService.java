package com.corner.scms.service.erp;

import com.corner.core.beans.ERPSpOrderOwnerDetail;
import com.corner.core.enums.SpOrderOwnerType;
import com.corner.scms.beans.ro.erp.ERPSpOrderOwnerRo;
import com.corner.scms.beans.vo.SpOrderDetailVo;

import java.math.BigDecimal;
import java.util.List;


public interface ERPSpOrderOwnerService {
	void addERPSpOrderOwnerDetail(ERPSpOrderOwnerRo ro, String orderId) throws Exception;

	List<ERPSpOrderOwnerDetail> getOwnerDetail(String orderInfoId);
	List<ERPSpOrderOwnerDetail> getOwnerDetail(String orderInfoId , SpOrderOwnerType type);
	List<ERPSpOrderOwnerDetail> getOwnerDetailByPid(String orderInfoPId);
	ERPSpOrderOwnerDetail getDetailById(String id);
	void updateDetail(ERPSpOrderOwnerDetail detail) throws Exception;

	List<SpOrderDetailVo> getOwnerDetailToVo(String orderInfoId);
}
