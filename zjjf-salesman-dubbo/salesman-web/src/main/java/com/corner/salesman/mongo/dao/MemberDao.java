package com.corner.salesman.mongo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.corner.rpc.salesman.model.Member;
import com.corner.salesman.core.mongo.impl.BaseMongoDAOImpl;

@Repository("memberDao")
public class MemberDao  extends BaseMongoDAOImpl<Member>{

	@Autowired(required=false) 
    @Qualifier("mongoTemplate")  
	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate) {
		super.mongoTemplate = mongoTemplate;  
		
	}
	
}
