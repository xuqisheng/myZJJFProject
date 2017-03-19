package test;


import com.corner.core.beans.ERPPurchaseStockInfo;
import com.corner.core.beans.ERPPurchaseStockInfoExample;
import com.corner.core.dao.ERPPurchaseStockInfoMapper;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPPurchaseStockRo;
import com.corner.scms.service.WalletService;
import com.corner.scms.service.callable.CallAbleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-config.xml" })
public class TransactionTest {

    private static final Logger logger = LoggerFactory.getLogger(TransactionTest.class);

    @Autowired
    private WalletService walletService;
    @Autowired
    private CallAbleService callAbleService;

    @Autowired
    private ERPPurchaseStockInfoMapper purchaseStockInfoMapper;

    @Test
    public void getOrderId() throws Exception{
        String orderId = callAbleService.getOrderId("test");
        logger.info("----------orderId : " + orderId);
        System.out.printf("----------orderId : " + orderId);
    }

    @Test
    public void pageTest(){
        ERPPurchaseStockRo ro = new ERPPurchaseStockRo();
        //ro.setSupplierId("2222");
        PageHelper.startPage(1,6);
        ERPPurchaseStockInfoExample example = new ERPPurchaseStockInfoExample();
        ERPPurchaseStockInfoExample.Criteria criteria =example.createCriteria();
//        criteria.andSupplierIdEqualTo(ro.getSupplierId());
        criteria.andIsDeleteEqualTo(false);
        if(!StringUtil.stringIsNullOrEmpty(ro.getManagerName()))
            criteria.andManagerNameLike("%"+ro.getManagerName()+"%");
        if(ro.getLevel() != null && ro.getLevel() != 0)
            criteria.andLevelEqualTo(ro.getLevel());
        if(ro.getCheckStatus() != null && ro.getCheckStatus() != 0)
            criteria.andCheckStatusEqualTo(ro.getCheckStatus());
        if(ro.getStatus() != null && ro.getStatus() != 0)
            criteria.andStatusEqualTo(ro.getStatus());
        if(ro.getBeginTime() != null)
            criteria.andAddTimeGreaterThan(ro.getBeginTime());
        if(ro.getEndTime() != null)
            criteria.andAddTimeLessThan(ro.getEndTime());
//        Page page = PageHelper.startPage(ro.getPageSize() , ro.getPageIndex());
        List<ERPPurchaseStockInfo> list = purchaseStockInfoMapper.selectByExample(example);
        System.out.println(String.valueOf(list.size()));
    }

/**
     * 正常插入记录测试
     *//*

    @Test
    public void inserNormalTest(){
        String orderId = "AAAAAAAAAA";
        SpOrderInfo order = new SpOrderInfo();
        order.setId(orderId);
        order.setOrderId(orderId);
        order.setAddTime(new Date());
        order.setGoodsPrice(BigDecimal.TEN);
        order.setOrderPrice(BigDecimal.TEN);
        order.setUserId(orderId);
        order.setStoreId(111111);
        order.setConsignee(orderId);
        order.setMobile(orderId);
        order.setAddress(orderId);
        order.setStatus((byte)1);
        order.setSupportmetho((byte)1);
        order.setIsDelete(false);

        // 插入订单，无异常抛出
        try {
            transactionTestService.insertOrder(order, false);
            SpOrderInfo result  = orderMapper.selectByPrimaryKey(orderId);
            if(result!=null)
                logger.error("找到订单ID="+order.getId());
            else
                logger.error("找不到ID="+orderId+"的订单");
            // 断言订单不为空
            assertNotNull(result);
            // 断言订单id相等
            assertEquals(orderId,result.getId());
        }finally {
            // 清除资源
            orderMapper.deleteByPrimaryKey(orderId);
        }


        // 插入订单，有异常抛出
        try {
            transactionTestService.insertOrder(order, true);
        }catch (Exception e){
            SpOrderInfo result  = orderMapper.selectByPrimaryKey(orderId);
            if(result!=null)
                logger.error("找到订单ID="+order.getId());
            else
                logger.error("找不到ID="+orderId+"的订单");

            // 断言订单为空，即有异常时不会插入订单
            assertNull(result);
            logger.debug(e.getMessage());
        }finally {
            // 清除资源
            orderMapper.deleteByPrimaryKey(orderId);
        }
    }

    */
/**
     * 提现事务测试
     */

    /*@Test
    public void withdraw(){

        // 插入提现单
        Long widthdrawId = null;
        try {
           widthdrawId = walletService.doWithDraw("4889237AFE434EF99A66BD3E58B23FB0",BigDecimal.TEN,"4889237AFE434EF99A66BD3E58B23FB0");
            SpWithDraw result  = withDrawMapper.selectByPrimaryKey(widthdrawId);
            if(result!=null)
                logger.error("找到提现单ID="+result.getId());
            else
                logger.error("提现单没有生成");
            // 断言不为空，即无异常时会插入
            assertNotNull(result);
        }catch (Exception e){
            SpWithDraw result  = withDrawMapper.selectByPrimaryKey(widthdrawId);
            if(result!=null)
                logger.error("找到提现单ID="+result.getId());
            else
                logger.error("提现单没有生成");
            // 断言为空，即有异常时不会插入
            assertNull(result);
            logger.debug(e.getMessage());
        }finally {
            // 清除资源
            withDrawMapper.deleteByPrimaryKey(widthdrawId);
        }
    }*/
}
