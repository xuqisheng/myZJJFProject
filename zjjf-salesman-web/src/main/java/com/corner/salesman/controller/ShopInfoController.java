package com.corner.salesman.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.rpc.salesman.api.service.DictService;
import com.corner.rpc.salesman.api.service.ShopService;
import com.corner.rpc.salesman.model.Shop;
import com.corner.rpc.shop.api.service.RegionService;
import com.corner.rpc.shop.api.service.SpGroupService;
import com.corner.rpc.shop.api.service.StoreService;
import com.corner.rpc.shop.model.Region;
import com.corner.rpc.shop.model.Store;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.commons.utils.DateUtils;
import com.corner.salesman.commons.utils.FileUtil;
import com.corner.salesman.commons.utils.GenerateSequence;
import com.corner.salesman.commons.utils.JedisUtils;
import com.corner.salesman.model.Department;
import com.corner.salesman.model.ShopInfo;
import com.corner.salesman.service.DepartmentService;
import com.corner.salesman.service.ShopInfoService;
import com.corner.salesman.utils.excel.ShopReadExcel;
import com.corner.scms.beans.CustomerService;
import com.corner.scms.controller.ScmsBaseWebController;
/**
 * 店铺管理控制层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/shop")
public class ShopInfoController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(ShopInfoController.class);
	@Autowired
	private ShopInfoService shopInfoService;
	@Autowired
	private DepartmentService deptService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private DictService dictService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private SpGroupService spgService;
	@Autowired
	private StoreService storeService;
	
	
	//转角业务员  首页 
	@RequestMapping(value = "/index.do")
	public String toListPage(ShopInfo shopVO, HttpServletRequest request, Model model) {
		List<Region> provinceList = null;
		List<Map<String, String>> shopTypeList = null;
		try {
			//加载店宝省份数据列表
			Region region = new Region();
			region.setpId(1);
			provinceList = regionService.queryRegionList(region);
			shopTypeList = dictService.getDictListByType("shop_type");
			model.addAttribute("provinceList", provinceList);
			model.addAttribute("shopTypeList", shopTypeList);
			
		} catch (Exception e) {
			logger.info("获取区域信息列表异常：{}",e.getMessage());
		}
		return "salesman/shopInfo-list";
	}
	
	@RequestMapping(value = "/toUnregisterList.do")
	public String toUnregisterList(ShopInfo shopVO, HttpServletRequest request, Model model) {
		List<Region> provinceList = null;
		List<Map<String, String>> shopTypeList = null;
		try {
			//加载店宝省份数据列表
			Region region = new Region();
			region.setpId(1);
			provinceList = regionService.queryRegionList(region);
			shopTypeList = dictService.getDictListByType("shop_type");
			model.addAttribute("provinceList", provinceList);
			model.addAttribute("shopTypeList", shopTypeList);
			model.addAttribute("shopVO", shopVO);
			
		} catch (Exception e) {
			logger.info("获取区域信息列表异常：{}",e.getMessage());
		}
		return "salesman/unregisterList";
	}
	
	//转角业务员  首页 
	@RequestMapping(value = "/toAddPage.do")
	public String addShopInfoPage(ShopInfo shopVO, HttpServletRequest request, Model model) {
		List<Department> deptList = null;
		List<Region> provinceList = null;
		List<Map<String, String>> shopTypeList = null;
		try {
			deptList = deptService.findDeptList();
			//加载店宝省份数据列表
			Region region = new Region();
			region.setpId(1);
			provinceList = regionService.queryRegionList(region);
			shopTypeList = dictService.getDictListByType("shop_type");

		} catch (Exception e) {
			logger.info("获取部门信息列表异常：{}",e.getMessage());
		}
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("shopTypeList", shopTypeList);
		model.addAttribute("deptList", deptList);
		return "salesman/shopInfo-add";
	}
	
	//转角业务员  首页 
	@RequestMapping(value = "/toShopListPage.do")
	public String toShopListPage(String divId,ShopInfo shopVO, Model model) {
		model.addAttribute("divId", divId);
		model.addAttribute("shopVO", shopVO);
		return "salesman/shopChoiceList";
	}
	
	/**
	 * 跳转到用户编辑页面
	 * @param shopVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/toEditPage.do")
	public String toEditPage(Shop shopVO, HttpServletRequest request, Model model) throws Exception {
		
		List<Department> deptList = null;
		List<Region> provinceList = null;
		List<Map<String, String>> shopTypeList = null;
		
		deptList = deptService.findDeptList();
		//加载店宝省份数据列表
		Region region = new Region();
		region.setpId(1);
		provinceList = regionService.queryRegionList(region);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("shopId", shopVO.getShopId());
		Shop shopInfo = shopService.findShopById(paramMap);
		if(null != shopInfo){
			//此处sql为了满足app端业务不为空时默认为0处理，而web不需要所有额外特殊处理
			String shopArea = shopInfo.getShopArea();
			String distributionNum = shopInfo.getDistributionNum();
			String staffNum = shopInfo.getStaffNum();
			String sku = shopInfo.getSku();
			String turnover = shopInfo.getTurnover();
			
			shopInfo.setShopArea(shopArea.equals("0")?"":shopArea);
			shopInfo.setDistributionNum(distributionNum.equals("0")?"":distributionNum);
			shopInfo.setStaffNum(staffNum.equals("0")?"":staffNum);
			shopInfo.setSku(sku.equals("0")?"":sku);
			shopInfo.setTurnover(turnover.equals("0")?"":turnover);
		}
		shopTypeList = dictService.getDictListByType("shop_type");
		
		model.addAttribute("shopInfo", shopInfo);
		model.addAttribute("deptList", deptList);
		model.addAttribute("shopTypeList", shopTypeList);
		model.addAttribute("provinceList", provinceList);
		return "salesman/shopInfo-edit";
	}
	
	/**
	 * 
	* @Title: addSalesman 
	* @Description:   添加业务员信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/addShopInfo.do")
	@ResponseBody
	public Object addShopInfo(Shop shopVO, HttpServletRequest request) {
		try {
			//logger.info("添加部门信息为："+JSONUtil.objectToJSONString(shopVO));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
			shopVO.setCreateBy(supplier.getId());
			shopVO.setCreateTime(createTime);
			shopVO.setUpdateBy(supplier.getId());
			shopVO.setUpdateTime(createTime);
			shopVO.setShopId(StringUtil.getUUID());
			shopVO.setIsRegister("0");
			
			/*int spgNum = shopInfoService.checkShopIsExist(shopVO.getShopNo());
			if(spgNum>0){
				return ResponseUtils.sendMsg(false, "当前定格已经存在！");
			}*/
			
			//this.generateShopNo(shopVO);
			int size = shopService.addShopInfo(shopVO);
			if(size>0){
				return ResponseUtils.sendMsg(true, "添加成功");
			}else{
				return ResponseUtils.sendMsg(false, "添加失败");
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "添加异常："+e);
		}
	}
	
	public void generateShopNo(Shop shopInfo) throws Exception{
		//区域编号
		String regionStr = regionService.getRegionStr(shopInfo.getProvinceId(),shopInfo.getCityId(),shopInfo.getAreaId());
		regionStr = regionStr==null || "".equals(regionStr)?"":regionStr;
		//根据区域查出生成的最大值
		Store store = new Store();
		store.setAreaId(shopInfo.getAreaId());
		Integer num = storeService.getMaxSequenceNum(store);
		if(num==null){
			num=0;
		}
		//获取生成的数字
		String sequenceNum = "";
		do {
			sequenceNum = GenerateSequence.getSequence(num, 4);
			if(sequenceNum.contains("4")){
				num++;
				//不包含4的
				continue;
			}else{
				break;
			}
		} while (true);
		//拼接的店铺编号
		String supplierCode = (regionStr+sequenceNum).toString();
		// 查询该店铺编号是否存在
		// 如果是已经审批过的店铺,不进行判断
//		int total =storeService.checkShopNoIsExist(supplierCode);
//		if (total>0) {
//			this.generateShopNo(shopInfo);
//		}
		shopInfo.setShopNo(supplierCode);
	}
	
	/**
	 * @Title: listPage 
	 * @Description: 部门分页查询列表
	 * @param request
	 * @param salesman
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/listPage.do")
	@ResponseBody
	public Object listPage(HttpServletRequest request, ShopInfo shopVO) {
		try {
			logger.info("列表查询开始："+JSONUtil.objectToJSONString(shopVO));
			Pager<ShopInfo> pager = shopInfoService.getShopInfoPageList(shopVO);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "查询定格路线信息异常："+e);
		}
	}
	
	/**
	 * @Title: listPage 
	 * @Description: 部门分页查询列表
	 * @param request
	 * @param salesman
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/getChoiceList.do")
	@ResponseBody
	public Object getChoiceList(HttpServletRequest request, ShopInfo shopVO) {
		try {
			logger.info("列表查询开始："+JSONUtil.objectToJSONString(shopVO));
			Pager<ShopInfo> pager = shopInfoService.getChoicePageList(shopVO);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "查询定格路线信息异常："+e);
		}
	}
	
	/**
	 * 
	* @Title: delShopInfo 
	* @Description:   删除定格路线信息
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/delShopInfo.do")
	@ResponseBody
	public Object delShopInfo(HttpServletRequest request, ShopInfo shopVO) {
		try {
			String id = shopVO.getShopId();
			if(id==null){
				return ResponseUtils.sendMsg(false, "删除数据的ShopId不能为空！");
			}
	
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
			shopVO.setUpdateBy(supplier.getId());
			shopVO.setUpdateTime(createTime);
			int size = shopInfoService.deleteShopInfo(shopVO);
			if(size>0){
				return ResponseUtils.sendMsg(true, "删除成功");
			}else{
				return ResponseUtils.sendMsg(false, "删除失败");
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "删除异常："+e);
		}
	}
	
	/**
	 * 
	* @Title: delShopInfo 
	* @Description:   修改定格路线信息
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws555
	 */
	@RequestMapping(value = "/editShopInfo.do")
	@ResponseBody
	public Object editShopInfo(HttpServletRequest request, Shop shopVO) {
		try {
			logger.info("修改部门信息参数："+JSONUtil.objectToJSONString(shopVO));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
			shopVO.setUpdateBy(supplier.getId());
			shopVO.setUpdateTime(createTime);
			int size = shopService.updateShopInfo(shopVO);
			if(size>0){
				return ResponseUtils.sendMsg(true, "修改成功");
			}else{
				return ResponseUtils.sendMsg(false, "修改失败");
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "修改异常："+e);
		}
	}
	
	/**
	 * 
	* @Title: updateBindShop 
	* @Description:   绑定定格与店铺关系
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws555
	 */
	@RequestMapping(value = "/updateBindShop.do")
	@ResponseBody
	public Object updateBindShop(HttpServletRequest request, ShopInfo shopVO) {
		try {
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
			shopVO.setUpdateBy(supplier.getId());
			shopVO.setUpdateTime(createTime);
			int size = shopInfoService.updateBindSpgroupShop(shopVO);
			if(size>0){
				return ResponseUtils.sendMsg(true, "关联成功");
			}else{
				return ResponseUtils.sendMsg(false, "关联失败");
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "关联异常："+e);
		}
	}
	
	 /**
	  * @author jiangzeyun
	  * @startTime  2014-09-06
	  * @endTime    2014-09-08
	  * @param request
	  * @param response
	  */
	@RequestMapping(value = "/importExcel.do")
	@ResponseBody
	public Object importExcel(HttpServletRequest request, HttpServletResponse response) {
		
	  String userId = null;
	  CustomerService supplier = getCurrentUser(CustomerService.class, request);
	  if(null != supplier){
		  userId = supplier.getId();
	  }
	  
	  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	  MultipartFile multipartFile = multipartRequest.getFile("file");
	  String sourceName = multipartFile.getOriginalFilename(); // 原始文件名
	  String fileType = sourceName.substring(sourceName.lastIndexOf("."));
	  
	  System.out.println("上传的文件名为:"+sourceName+",   类型为:"+fileType);
	  String base = request.getSession().getServletContext().getRealPath("/") + "attachments" + File.separator + "uploadedExcel";
	  File file = new File(base);
	  if(!file.exists()){
	   file.mkdirs();
	  }
	  try{
	   String path=base + File.separator + sourceName;
	   
	   multipartFile.transferTo(new File(path));
	   //service.insert("insertAttachment", attach);
	   
	   
	   Map<String, String> regionMap = this.getEnableRegionCache();
	   //Map<String,String> spGroupMap = this.getEnableSpGroupCache();
	   //上传成功后读取Excel表格里面的数据
	   System.out.println("路径是"+path);
	   /*List<Shop> mylist = new ArrayList<Shop>();
	   ShopReadExcel reader=new ShopReadExcel(path,mylist,regionMap);
		reader.processByRow(1);//处理第一个sheet，sheet索引从1开始
		FileUtil.delFile(path);
		
		shopService.batchSaveShop(userId, mylist);*/
	   
		new Thread(new ImportRunnable(userId,path,regionMap)).start(); 
		
		System.err.println("=============导入成功===========");
		return ResponseUtils.sendMsg(true, "客户数据导入成功!");
	  }catch (Exception e) {
		  e.printStackTrace();
		  return ResponseUtils.sendMsg(false, "客户数据导入失败!");
	  }
	 }
	
	@SuppressWarnings("unchecked")
	public Map<String,String> getEnableRegionCache(){
		Map<String, String> regionMap = null;
		try{
			regionMap = (Map<String, String>)JedisUtils.getObject(Constants.ENABLE_REGION_CACHE_MAP);
			if(null == regionMap){
				regionMap = new HashMap<String,String>(); 
				
				List<HashMap<String,String>> regionList = regionService.getEnableRegionCache();
				for(Map<String, String> map: regionList){
					String key = map.get("key");
					String val = map.get("value");
					
					System.err.println("======================类型key:"+key+", value="+val);
					regionMap.put(key, val);
				}
				//将有效的省市区缓存2个小时，过期需要重新查询
				JedisUtils.setObject(Constants.ENABLE_REGION_CACHE_MAP,regionMap,7200);
			}
		}catch (Exception e) {
			logger.error("获取区域基础数据异常：{}",e.getMessage());
		}
		return regionMap;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,String> getEnableSpGroupCache(){
		Map<String, String> spGroupMap = null;
		try{
			spGroupMap = (Map<String, String>)JedisUtils.getObject(Constants.ENABLE_SP_GROUP_CACHE_MAP);
			if(null == spGroupMap){
				spGroupMap = new HashMap<String,String>(); 
				
				List<HashMap<String,String>> spgList = spgService.getEnableSpGroupCache();
				for(Map<String, String> map: spgList){
					String key = map.get("key");
					String val = map.get("value");
					
					System.err.println("======================类型key:"+key+", value="+val);
					spGroupMap.put(val, key);
				}
				//将有效的省市区缓存8个小时，过期需要重新查询
				JedisUtils.setObject(Constants.ENABLE_SP_GROUP_CACHE_MAP,spGroupMap,7200);
			}
		}catch (Exception e) {
			logger.error("获取定格基础数据异常：{}",e.getMessage());
		}
		return spGroupMap;
	}
	

	//转角业务员  首页 
	@RequestMapping(value = "/toFileImportPage.do")
	public String toFileImportPage(ShopInfo shopVO, HttpServletRequest request, Model model) {
		model.addAttribute("shopVO", shopVO);
		return "salesman/toFileImportPage";
	}
	
	@RequestMapping("/download")
    public String download(String fileName, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+ fileName);
        try {
        	String path = request.getSession().getServletContext().getRealPath("template");
            InputStream inputStream = new FileInputStream(new File(path + File.separator + fileName));

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

             // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            //  返回值要注意，要不然就出现下面这句错误！
            //java+getOutputStream() has already been called for this response
        return null;
    }
	
	//转角业务员  首页 
	@RequestMapping(value = "/syncShopData.do")
	@ResponseBody
	public Object syncShopData(String divId,ShopInfo shopVO, HttpServletRequest request) {
	   try{
		   
		   CustomerService supplier = getCurrentUser(CustomerService.class, request);
		   if(null != supplier){
			    //String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
				shopVO.setUpdateBy(supplier.getId());
				//shopVO.setUpdateTime(createTime);
		   }
			
		   new Thread(new SyncShop(shopVO)).start(); 
		   return ResponseUtils.sendMsg(true, "正在同步客户数据，请五分钟后操作客户相关数据!");
	   }catch (Exception e) {
		  e.printStackTrace();
		  return ResponseUtils.sendMsg(false, "同步客户数据失败!");
	   }
	}
	
	class SyncShop implements Runnable { // 实现了Runnable接口，jdk就知道这个类是一个线程  
		
		private ShopInfo shopVO;
		public SyncShop(){
			
		}
		public SyncShop(ShopInfo shopVO){
			this.shopVO =  shopVO;
		}
		
	    public void run() {  
	    	try {
				shopInfoService.batchSyncShop(shopVO);
			} catch (Exception e) {
				logger.error("多线程同步店铺过程中出现异常：{}",e);
				e.printStackTrace();
			}
	    }  
	}
	
	class ImportRunnable implements Runnable { // 实现了Runnable接口，jdk就知道这个类是一个线程  
		
		private String userId;
		
		private String path;
		
		private Map<String, String> regionMap;
		
		public ImportRunnable(){
			
		}
		public Runnable start() {
			// TODO Auto-generated method stub
			return null;
		}
		public ImportRunnable(String userId,String path,Map<String, String> regionMap){
			this.userId =  userId;
			this.path =  path;
			this.regionMap = regionMap;
		}
		
	    public void run() {  
	    	try {
	    		 List<Shop> mylist = new ArrayList<Shop>();
    		     ShopReadExcel reader=new ShopReadExcel(path,mylist,regionMap);
    			 reader.processByRow(1);//处理第一个sheet，sheet索引从1开始
    			 FileUtil.delFile(path);
    			
    			 shopService.batchSaveShop(userId, mylist);
			} catch (Exception e) {
				logger.error("导入数据异常：{}",e);
				e.printStackTrace();
			}
	    }  
	}
}
