/**   
* @Title: MaWalletMgServiceImpl.java 
* @Package com.corner.scms.service.fac.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月7日 下午6:08:42 
* @version V1.0   
*/

package com.corner.scms.service.fac.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.MaWallet;
import com.corner.core.dao.MaWalletMapper;
import com.corner.scms.dao.MaWalletMgMapper;
import com.corner.scms.service.fac.MaWalletMgService;

/** 
* @ClassName: MaWalletMgServiceImpl 
* @Description:经销商钱包业务类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月7日 下午6:08:42 
*  
*/
@Service
public class MaWalletMgServiceImpl implements MaWalletMgService {
    @Autowired
	MaWalletMgMapper maWalletMgMapper;
    
    @Autowired
    MaWalletMapper maWalletMapper;
	
	@Override
	public void addManagerWallet(Map<String, Object> map) throws Exception {
		maWalletMgMapper.addManagerWallet(map);
	}

	@Override
	public MaWallet selectById(String managerId) throws Exception {
		return maWalletMgMapper.selectById(managerId);
	}

	@Override
	public void insertSelective(MaWallet maWallet) throws Exception {
		maWalletMapper.insertSelective(maWallet);
	}

}
