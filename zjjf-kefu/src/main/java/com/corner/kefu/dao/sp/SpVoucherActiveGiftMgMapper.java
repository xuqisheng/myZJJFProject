package com.corner.kefu.dao.sp;

import java.util.List;

import com.corner.core.beans.SpVoucherActive;
import com.corner.core.beans.SpVoucherActiveGift;

public interface SpVoucherActiveGiftMgMapper {

	void batchSave(List<SpVoucherActiveGift> list) throws Exception;

	/**
	 * 
	* @Title: deleGiftByActiveId 
	* @Description:删除赠品表中的相关数据
	* @param @param spVoucherActive
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void deleGiftByActiveId(SpVoucherActive spVoucherActive) throws Exception;

}
