package com.corner.core.config;

public class MybatisCommonKey {
	// 根据主键查询
	public static final String selectByPrimaryKey = "selectByPrimaryKey";
	// 根据主键删除
	public static final String deleteByPrimaryKey = "deleteByPrimaryKey";
	// 插入
	public static final String insert = "insert";
	// 插入
	public static final String insertSelective = "insertSelective";
	// 根据主键不为空更新
	public static final String updateByPrimaryKeySelective = "updateByPrimaryKeySelective";
	// 根据主键更新
	public static final String updateByPrimaryKey = "updateByPrimaryKey";
	// 根据行政级别查询
	public static final String selectByRegionLevel = "selectByRegionLevel";
	// 根据用户id查询
	public static final String selectByUserId = "selectByUserId";
	// 根据父级id查询
	public static final String selectByPID = "selectByPID";
	// 根据userid查找默认地址
	// 根据userid查找已选择的
	public static final String selectIsCheckOfUserId = "selectIsCheckOfUserId";
	public static final String findUserByMap = "findUserByMap";
	// 根据userid更新
	public static final String findUserByMobile = "findUserByMobile";
	public static final String updateByUserId = "updateByUserId";
	// 批量删除
	public static final String updateByIds = "updateByIds";
	// 批量查询
	public static final String selectByIds = "selectByIds";
	// 用户个人信息
	public static final String selectUserShowInfo = "selectUserShowInfo";
	// 统计购物车中商品的总单价
	public static final String selectTotalPrice = "selectTotalPrice";
	// 更新购物车数量与总单价
	public static final String updatNumAndTotalPriceById = "updatNumAndTotalPriceById";
	// 根据id更新
	public static final String updateById = "updateById";
	// 更新订单状态
	public static final String updateStatus = "updateStatus";
	// 根据用户id去查询订单（默认全部）
	public static final String selectAllByUserId = "selectAllByUserId";
	// 根据用户id去查询订单（某一状态）
	public static final String selectAllByUserIdOfStatus = "selectAllByUserIdOfStatus";
	// 根据商铺id去查询订单（默认全部）
	public static final String selectAllByStoreId = "selectAllByStoreId";
	// 根据商铺id去查询订单（某一状态）
	public static final String selectAllByStoreIdOfStatus = "selectAllByStoreIdOfStatus";
	// 根据条件查询
	public static final String selectOfQuery = "selectOfQuery";
	// 根据处理状态查询用户反馈信息
	public static final String selectUserFeedbackByStatus = "selectUserFeedbackByStatus";
	// 根据用户id查询默认地址
	public static final String selectDefaultAddressByUserId = "selectDefaultAddressByUserId";
	// 查询全部订单（包括逻辑删除的）
	public static final String selectOfUserId = "selectOfUserId";
	// 统计某一状态的订单数
	public static final String selectCountOfStatus = "selectCountOfStatus";
	// 更新评论状态
	public static final String updateIsStar = "updateIsStar";
	// 查询积分商品列表
	public static final String selectCreditItem = "selectCreditItem";
	// 查询全部
	public static final String selectAllOfQuery = "selectAllOfQuery";
	public static final String selectAllOfZB = "selectAllOfZB";
	// 查询此当前用户搜索的关键字的条数
	// 查询全部（不需要条件）
	public static final String selectAll = "selectAll";
	public static final String GetClassByStoreID = "GetClassByStoreID";
	// 根据区域查询商铺
	public static final String selectAllByArea = "selectAllByArea";
	public static final String selectByStoreId = "selectByStoreId";
	// 查询此当前用户搜索的关键字的条数
	public static final String selectSearchRecordByKeyword = "selectSearchRecordByKeyword";
	// 查询历时记录
	public static final String selectSearch = "selectSearch";
	// 根据商铺id查询
	public static final String selectByStoreIdWithPage = "selectByStoreIdWithPage";
	// 根据关键字查询
	public static final String selectByKeyWords = "selectByKeyWords";
	// 更新销量
	public static final String updateSales = "updateSales";
	// 更新商品
	public static final String updateItem = "updateItem";
	// 更新状态
	public static final String updateType = "updateType";
	// 通过拥有者和金额查询现金券
	public static final String selectVoucher = "selectVoucher";
	// 查询点击量最高的6件商品名
	public static final String selectIteamClickRate = "selectIteamClickRate";
	// 查询销量最高的6件商品名
	public static final String selectIteamSales = "selectIteamSales";
	// 查询商品详情
	public static final String selectProductDetails = "selectProductDetails";
	// 根据条件统计
	public static final String selectCountOfQuery = "selectCountOfQuery";
	// 获取随机推荐的6个商品
	public static final String selectRandomItem = "selectRandomItem";
	// 根据时间查询地址
	public static final String selectMyAddressByTime = "selectMyAddressByTime";
	// 批量插入现金券
	public static final String insertVoucher = "insertVoucher";
	// 批量添加
	public static final String addOrderDetailBatch = "addOrderDetailBatch";
	// 根据订单编号查询
	public static final String selectByOrderId = "selectByOrderId";
	// 根据id查询
	public static final String selectById = "selectById";
	// 根据订单号统计商品数量
	public static final String selectCountNumByOrderId = "selectCountNumByOrderId";
	// 根据现金券状态查询现金券
	public static final String selectByBillState = "selectByBillState";
	// 查询当前用户的月度积分

	// 查询当前用户的月度积分
	public static final String selectAddressById = "selectAddressById";

	// 检查用户是否已领过此活动的现金券
	public static final String selectVoucherByActiveId = "selectVoucherByActiveId";
	// 刷新
	public static final String refresh = "refresh";

	// 查询添加的商品是否已存在
	public static final String selectByItemIdAndStoreId = "selectByItemIdAndStoreId";
	// 根据基本商品编号与商铺编号查询商品
	public static final String selectOfItemIdAndStoreId = "selectOfItemIdAndStoreId";
	// 查询购物车中关于某个商品某个用户在某个商铺中是够已经存在
	public static final String selectIsExsit = "selectIsExsit";
	// 根据商铺id查询商铺主人id
	public static final String selectManagerIdByStoreId = "selectManagerIdByStoreId";
	// 根据用户id修改我的历时状态
	public static final String updateSearchByUserId = "updateSearchByUserId";
	// 查询订单状态对应的条数
	public static final String selectOrderCount = "selectOrderCount";
	// 查询区域省市区
	public static final String selectAllRegion = "selectAllRegion";
	// 查询区域的区ID
	public static final String selectAreaid = "selectAreaid";
	// 查询用户的全部订单
	public static final String selectAllOfUser = "selectAllOfUser";
	// 查询商铺的全部订单
	public static final String selectAllOfStore = "selectAllOfStore";
	// 查询快过期的现金券的数量
	public static final String selectCount = "selectCount";
	// 根据用户编号与商铺编号一起查询
	public static final String selectByUserIdWithStoreId = "selectByUserIdWithStoreId";
	// 根据用户id与商铺id统计购物车中商品价格
	public static final String selectTotalPriceOfUserIdWithStoreId = "selectTotalPriceOfUserIdWithStoreId";
	// 查询当前日期
	public static final String selectOfDate = "selectOfDate";
	// 按月查询
	public static final String selectOfMonth = "selectOfMonth";
	// 安当日统计
	public static final String selectCountOfDate = "selectCountOfDate";
	// 按月统计
	public static final String selectCountOfMonth = "selectCountOfMonth";
	// 按日统计总价格
	public static final String selectSumMoneyOfDate = "selectSumMoneyOfDate";
	// 按月统计总价格
	public static final String selectSumMoneyOfMonth = "selectSumMoneyOfMonth";

	// 根据友盟推送类型获取消息列表
	public static final String selectPushMessageByPushType = "selectPushMessageByPushType";
	// 查询所有帮助类型
	public static final String selectAllHelpType = "selectAllHelpType";
	// 查询当前帮助类型下的标题
	public static final String selectHelpByType = "selectHelpByType";
	// 根据问题标题找解决方案
	public static final String selectHelpInfoByTitle = "selectHelpInfoByTitle";
	// 根据关键字查询解决方案
	public static final String selectHelpInfoByKeyword = "selectHelpInfoByKeyword";
	//根据用户名查询用户
	public static final String findUserByUsername = "findUserByUsername";
	//更新月度积分为0
	public static final String updateMonthCredit = "updateMonthCredit";

	//根据Id 和当天时间查询积分日志对象
	public static final String getCreditLogById = "getCreditLogById";

	//查询日志表中已使用的现金券
	public static final String selectVoucherLogByUserId="selectVoucherLogByUserId";
	//根据条件查询商品
	public static final String selectItemSelective="selectItemSelective";
	
	// 根据商品id查询商品基本信息 
	public static final String selectItemById="selectItemById";
	//根据条件查询商品信息
	public static final String selectItem="selectItem";
	//批量上架
	public static final String batchPutaway="batchPutaway";
	//批量下架
	public static final String batchUnShelve="batchUnShelve";
	//PC端我的销售查询
	public static final String groupStoreGoodsSalecountT="groupStoreGoodsSalecountT";
}