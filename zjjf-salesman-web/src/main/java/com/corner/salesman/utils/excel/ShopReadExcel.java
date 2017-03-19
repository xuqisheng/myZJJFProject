package com.corner.salesman.utils.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.corner.core.utils.JSONUtil;
import com.corner.rpc.salesman.model.Shop;
import com.corner.salesman.commons.utils.ObjectUtils;
import com.corner.salesman.commons.utils.excel.ExcelProcessor;
import com.corner.salesman.commons.utils.excel.XRow;
import com.corner.scms.config.Constants;
import com.corner.scms.utils.JedisUtils;

/**
 * 解析Excel测试类
 * 
 * @author zhangchaofeng
 * @version 1.0
 * @date Oct 18, 2011
 */
public class ShopReadExcel extends ExcelProcessor {
	
//	private static Logger logger = LoggerFactory.getLogger(ShopReadExcel.class);
	
	private List<Shop> list = null;
	
	private Map<String, String> regionMap = null;
	
//	private Map<String, String> spGroupMap = null;
	
	public ShopReadExcel(String fileName,List<Shop> list) throws Exception {
		super(fileName);
		this.list = list;
	}
	
	public ShopReadExcel(String fileName,List<Shop> list,Map<String, String> regionMap) throws Exception {
		super(fileName);
		this.list = list;
		this.regionMap = regionMap;
	}
	
	/*public ShopReadExcel(String fileName,List<Shop> list,Map<String, String> regionMap,Map<String, String> spGroupMap) throws Exception {
		super(fileName);
		this.list = list;
		this.regionMap = regionMap;
		this.spGroupMap = spGroupMap;
	}*/
	
	@Override
	public void processRow(XRow row) {
		int rowIndex = row.getRowIndex();
		
		if(rowIndex>2){
			Shop shop = new Shop();
			
			for (int i = 0; i < row.getCellsSize(); i++) {
				//System.out.print("[" + row.getRowIndex() + ","+ (char) row.getCell(i).getColumnIndex() + ","+ row.getCell(i).getValue() + "]");
				String value = row.getCell(i).getValue();
				//如果单元格中的值为空则直接跳过
				if(StringUtils.isBlank(value)){
					continue;
				}
				
//				if(i==0){
//					shop.setShopNo(value);//商铺编号
//				}else 
				if(i==0){
					shop.setShopName(value);//商铺名称
				}else if(i==1){
					shop.setShopType(value);//商铺类型
				}else if(i==2){
					shop.setTelephone(value);//固定电话
				}else if(i==3){
					shop.setProvince(value);//省份
	    			//如果省份类型为非数字字符串，则需要映射取值填充
	    			if(!ObjectUtils.isInteger(value)){
	    				String provinceId = regionMap.get(value);
	    				if(StringUtils.isNotBlank(provinceId)){
	    					shop.setProvinceId(Integer.parseInt(provinceId));//省份
	    				}
	    			}
				}else if(i==4){
					shop.setCity(value);//城市
	    			//如果区域类型为非数字字符串，则需要映射取值填充
	    			if(!ObjectUtils.isInteger(value)){
	    				String cityId = regionMap.get(value);
	    				if(StringUtils.isNotBlank(cityId)){
	    					shop.setCityId(Integer.parseInt(cityId));//城市
	    				}
	    			}
				}else if(i==5){
					shop.setArea(value);//区域
	    			//如果区域类型为非数字字符串，则需要映射取值填充
	    			if(!ObjectUtils.isInteger(value)){
	    				String areaId = regionMap.get(value);
	    				if(StringUtils.isNotBlank(areaId)){
	    					shop.setAreaId(Integer.parseInt(areaId));//区域
	    				}
	    			}
				}
				
				/*else if(i==7){
					shop.setSpGroupId(value);//定格(运营确认，excel中的定格列填写数字信息，非名称)
	    			//如果区域类型为非数字字符串，则需要映射取值填充
	    			if(ObjectUtils.isInteger(value)){
	    				String spGroupName = spGroupMap.get(value);
	    				shop.setSpGroupName(spGroupName);//定格名称
	    			}
					
				}else if(i==8){
					shop.setLineName(value);//线路
				}*/
				
				else if(i==6){
					shop.setShopAddress(value);//详细地址
				}else if(i==7){
					shop.setBossName(value);//店铺负责人
				}else if(i==8){
					shop.setBossTel(value);//负责人联系方式
				}else if(i==9){
					shop.setRemarks(value);//店铺负责人备注
				}else if(i==10){
					shop.setShopArea(value);//营业面积
				}else if(i==11){
					//营业时间
					String[] workTime = value.split("-");
					if(workTime.length==1){
						shop.setStartShopHours(workTime[0]);
					}else if(workTime.length==2){
						shop.setStartShopHours(workTime[0]);
						shop.setEndShopHours(workTime[1]);
					}
				}else if(i==12){
					shop.setStaffNum(value);//人员数量
				}else if(i==13){
					shop.setDistributionNum(value);//配送员数量k
				}else if(i==14){
					shop.setDcShop(value);//配送合作商
				}else if(i==15){
					shop.setMainProduct(value);//主营商品
				}else if(i==16){
					shop.setSaleRatio(value);//主营商品占比
				}else if(i==17){
					value = this.setConvertChar2Num(value);
					shop.setIsMultipleShop(value);//是否连锁
				}else if(i==18){
					shop.setTurnover(value);//日均营业额
				}else if(i==19){
					value = this.setConvertChar2Num(value);
					shop.setBaccyLicence(value);//是否有烟草许可证
				}else if(i==20){
					shop.setSku(value);//SKU数
				}else if(i==21){
					value = this.setConvertChar2Num(value);
					shop.setIsPos(value);//有无POS机
				}else if(i==22){
					value = this.setConvertChar2Num(value);
					shop.setIsComputer(value);//有无电脑
				}else if(i==23){
					value = this.setConvertChar2Num(value);
					shop.setIsWifi(value);//有无WIFI
				}else if(i==24 || i==25 ||i==26){
					//支付宝	微信	银联
					String ipay = shop.getIpay();
					value = this.setConvertChar2Num(value);
					if(i==24 && "1".equals(value)){
						value = "支付宝";
					}else if(i==25 && "1".equals(value)){
						value = "微信";
					}else if(i==26 && "1".equals(value)){
						value = "银联";
					}
					
					if(StringUtils.isNotBlank(ipay)){
						ipay+=","+value;
					}else{
						ipay=value;
					}
				}else if(i==27){
					value = this.setConvertChar2Num(value);
					shop.setOtherPatform(value);//其他合作平台
				}
			}
			//将组装的对象存入list中
			list.add(shop);
		}
	}
	
	public String setConverTypeName2Num(String key){
		Map<String, Object> shopTypeMap = (Map<String, Object>)JedisUtils.getObject(Constants.SHOP_TYPE_CONVERT_MAP);
		return shopTypeMap.get(key).toString();
	}
	
	/**
	 * 将文字转换为数值（有、无、是及否转换为 0 和 1）
	 * @return
	 */
	public String setConvertChar2Num(String value){
		
		if(StringUtils.isNotBlank(value)){
				
			if(value.equals("有")||value.equals("是")){
				value = "1";
			}else if(value.equals("无")||value.equals("否")){
				value = "0";
			}else{
				value = "0";
			}
		}
		return value;
	}
	
	
	public static void main(String[] args) throws Exception {
		List<Shop> mylist = new ArrayList<Shop>();
		ShopReadExcel reader=new ShopReadExcel("d:/test.xlsx",mylist);
		//reader.processByRow();//处理所有的sheet
		//reader.stop();//运行一半需要停止调用此方法，释放文件流，正常运行完毕会自动释放
		reader.processByRow(1);//处理第一个sheet，sheet索引从1开始
		
		for(int i=0;i<mylist.size();i++){
			System.err.println(JSONUtil.objectToJSONString(mylist.get(i)));
		}
		
	}
}
