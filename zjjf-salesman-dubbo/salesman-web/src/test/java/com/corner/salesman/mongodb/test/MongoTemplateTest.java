package com.corner.salesman.mongodb.test;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.corner.rpc.salesman.model.Member;

/**
 * 单元测试
 * @author marker
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/config/spring/web-mongodb.xml")
public class MongoTemplateTest {

	 private static Log log = LogFactory.getLog(MongoTemplateTest.class.getName());  

	 @Autowired
     private MongoTemplate mongoTemplate;


    @Test
    public void testInsert(){
    	Member member = new Member();
		member.setAddress("深圳武汉大学城808");
		member.setAge(26);
		member.setEmail("longxian@qq.com");
		member.setMemberId("10003");
		member.setMemberName("龙哥");
		member.setMobile("13510280846");
        mongoTemplate.insert(member); 
    }
    
    
    /** 
     * 批量新增  
     * <br>------------------------------<br> 
     */  
    @Test  
    public void testInsertAll(){  
        List<Member> list = new ArrayList<Member>();  
        for (int i = 0; i < 10; i++) {  
        	
        	Member member = new Member();
    		member.setAddress("深圳武汉大学城80"+i);
    		member.setAge(i);
    		member.setEmail("longxian"+i+"@qq.com");
    		member.setMemberId(i+"");
    		member.setMemberName("龙哥");
    		member.setMobile("1351028086"+i);
        	
            list.add(member);  
        }  
        mongoTemplate.insertAll(list);  
    }  
    
    /**
     * 根据Id删除用户
     *
     * @author <a href='mailto:dennisit@163.com'>Cn.苏若年(En.dennisit)</a> Copy Right since 2013-10-13 下午04:09:20
     *                
     * @param id
     */
    @Test
    public void deleteById() {
    	 String id="9";  
        Criteria criteria = Criteria.where("memberId").is(id);
        if(null!=criteria){
            Query query = new Query(criteria);
            Member member = mongoTemplate.findOne(query, Member.class);
            log.info("[Mongo Dao ]deleteById:" + JSON.toJSONString(member));
            mongoTemplate.remove(member);
        }
    }
    
    /** 
     * 删除,按主键id, 如果主键的值为null,删除会失败 
     * <br>------------------------------<br> 
     * @param id 
     */  
    @Test
    public void deleteById2() {  
    	Member member = new Member();
    	member.set_id("574d526e858cb69a1063a475");
        mongoTemplate.remove(member);  
    }  
    
    /** 
     *  
     *<b>function:</b>根据输入的ID查找对象 
     * @author cuiran 
     * @createDate 2012-12-12 16:24:10 
     */  
    @Test
    public void findOne(){  
    	
    	//findById的使用说明：
        String id="574d526e858cb69a1063a474";  
        Member p= mongoTemplate.findById(id, Member.class);
        log.debug(JSON.toJSONString(p));  
        
        
        /*
        String id="10003";  
        Query query = new Query();
        Criteria criteria = Criteria.where("memberId").is(id);
        query.addCriteria(criteria);
        Member p2= mongoTemplate.findOne(query, Member.class);
        JSON.toJSONString(p2);*/
        
    }  
    
    
    @Test
    public void findAll() {
    	 String id="龙哥";  
    	 Query query = new Query();
         Criteria criteria = Criteria.where("memberName").is(id);
         query.addCriteria(criteria);
         List<Member> list =  mongoTemplate.find(query, Member.class);
        
        //List<Member> list =  mongoTemplate.find(new Query(), Member.class);
        System.err.println(list.size());
        for (Member m : list) {
        	 String jsonObject = JSON.toJSONString(m);  
        	 log.debug(jsonObject);  
        	 
        	 /** 将JSON字符串转换为JSON对象 **/  
        	 //JSONObject json = JSON.parseObject(text)  
		}
    }
    
    
    /** 
     * 按主键修改, 
     * 如果文档中没有相关key 会新增 使用$set修改器 
     * <br>------------------------------<br> 
     * @param user 
     */  
    public void updateById(Member member) {  
        Criteria criteria = Criteria.where("memberId").is(member.getMemberId());  
        Query query = new Query(criteria);  
        
        Update update = Update.update("age", member.getAge()).set("memberName", member.getMemberName()).set("address", member.getAddress());   
        mongoTemplate.updateFirst(query, update, Member.class);  
    }
    
    @Test
    public void updateById() {  
    	Member member = new Member();
    	member.setMemberId("7");
    	member.setAge(28);
    	member.setMemberName("龙贤风001");
    	member.setAddress("欢迎使用MongoDB的手册!MongoDB是一个开放源代码的面向文档的数据库,易于开发和缩放。");
        updateById(member);
    }
    
    /** 
     * 修改多条 
     * <br>------------------------------<br> 
     * @param criteriaUser 
     * @param user 
     */  
    public void update(Member criteriaMember, Member member) {  
        Criteria criteria = Criteria.where("mobile").is(criteriaMember.getMobile());
        Query query = new Query(criteria);  
        
        
        /*List<Member> list =  mongoTemplate.find(query, Member.class);
        System.err.println(list.size());
        for (Member m : list) {
        	 String jsonObject = JSON.toJSONString(m);  
        	 log.debug(jsonObject);  
		}*/
        
        
        Update update = Update.update("memberName", member.getMemberName()).set("age", member.getAge()).set("address", member.getAddress());  
        mongoTemplate.updateMulti(query, update, Member.class);  
    }  
    
    @Test
    public void updateMulti() {  
    	Member member = new Member();
    	member.setAge(29);
    	member.setMemberName("龙贤风88");
    	member.setAddress("湖南怀化通道牙屯堡镇火车站。");
    	
    	Member criteriaMember = new Member();
    	criteriaMember.setAge(5);
    	criteriaMember.setMobile("13510280849");
    	update(criteriaMember,member);
    }

    
    
}