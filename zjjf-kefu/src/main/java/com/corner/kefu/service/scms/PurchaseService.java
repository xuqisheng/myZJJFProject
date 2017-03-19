package com.corner.kefu.service.scms;

import java.util.List;

import org.springframework.stereotype.Service;







import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.scms.ScmsPurchaseRo;
import com.corner.kefu.beans.vo.ScOrderDetailVo;
import com.corner.kefu.beans.vo.ScOrderInfoVo;
import com.corner.kefu.service.BaseService;
@Service

public interface PurchaseService extends BaseService{

	Pager<ScOrderInfoVo> findAllOrderInfo(ScmsPurchaseRo condition);

	ScOrderInfoVo findOrderByOid(String orderId);

	List<ScOrderDetailVo> findOrderDetail(String orderId);

}
