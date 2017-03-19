package com.corner.kefu.service.scms;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.scms.ScmsItemMgRo;
import com.corner.kefu.service.BaseService;
/**
 * 
 * @ClassName: ScmsOrderInfoMgServiceImpl 
 * @Description: 商品信息处理
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2015年12月28日 上午11:45:29 
 *
 */
@Service
public interface ScmsGroupItemService extends BaseService{
	Pager<Map<String, Object>> getGorupItemlistPage(Map<String, Object> map);
}
