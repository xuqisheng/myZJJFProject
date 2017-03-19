package com.corner.core.pay.returnview;


/**
* @ClassName: XSFZState
* @Description: TODO(线上随访 成功页面)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2014年12月25日 上午11:55:22
*
*/ 
public class DEFAULTState implements State {

	@Override
	public void handle(PaySuccessModelAndView pmav) {
		// TODO Auto-generated method stub
		pmav.getModel().addAttribute("params",pmav.getMap());
		pmav.setView("BuySuccess");
	}

}
