package com.corner.salesman.web.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.corner.rpc.salesman.model.Member;
import com.corner.salesman.service.api.MemberService;

/** 
 * 类名称：DemoController
 * 创建人：zs 
 * 创建时间：2016-02-22
 */
//@Controller
//@RequestMapping(value="/member")
public class MemberController {

	
	   @Autowired
	   private MemberService memberService;  
	    
		/**
		 * 新增
		 */
		@RequestMapping(value="/save")
		public ModelAndView save(ModelAndView mv) throws Exception{
			
			Member member = new Member();
			member.setAddress("深圳武汉大学城708");
			member.setAge(25);
			member.setEmail("longxian@qq.com");
			member.setMemberId("10001");
			member.setMemberName("元宝");
			member.setMobile("13510280849");
			memberService.save(member);
			
			mv.addObject("msg","success");
			mv.addObject("member", member);
			return mv;
		}
		
	  
		@RequestMapping(value="/findById")
	    public void findMember() {  
	    	Member a = memberService.findByid("574ce8f3858c195838cb5656");
	        System.out.println(JSON.toJSONString(a));  
	    }  
		
}
