package com.corner.salesman.service.api;

import java.util.Map;

import com.corner.rpc.salesman.model.Member;


public interface MemberService{ 
	
	 public Member findByid(String id);
	  
	 public Member findOne(Map params);
	  
	 public Member save(Member bean);
	  
     /** 
      * 暂时只是固定去修改,会有下一篇博客,写高级修改... 
      */  
     public void update(String id, Object params);
	
}
