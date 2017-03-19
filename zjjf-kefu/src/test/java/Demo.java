import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.corner.core.beans.Store;
import com.corner.core.dao.StoreMapper;
import com.corner.core.utils.JSONUtil;
import com.corner.kefu.service.sp.SpRegionService;
import com.corner.kefu.service.sp.SpStoreService;

/**   
 * @Title: Demo.java 
 * @Package  
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn   
 * @date 2015年12月15日 下午4:48:20 
 * @version V1.0   
 */

/**
 * @ClassName: Demo
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2015年12月15日 下午4:48:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-config.xml" })
public class Demo {

	@Autowired
	SpRegionService spRegionService;

	@Autowired
	SpStoreService storeService;
	@Autowired
	StoreMapper StoreMapper;

	@Test
	public void init() throws Exception {
		String url = "http://api.map.baidu.com/geocoder/v2/";
		String ak = "GmeC8Vox1AaY65IzhYb0H6TGg1nTEXQZ";
		Map<String, Object> map1 = new HashMap<>();
		map1.put("regionId", 76);
		List<Store> storeList = storeService.getStoreByRegion(map1);
		if(storeList !=null && storeList.size()>0){
			for ( int i = 0;i < storeList.size(); i++) {
				if(storeList.get(i).getAddress() != null && !"".equals(storeList.get(i).getAddress())){
					HttpClient httpClient = new HttpClient();
					PostMethod method = new PostMethod(url);
					method.addParameter("address", storeList.get(i).getAddress());
					method.addParameter("city", "广州市");
					method.addParameter("ak", ak);
					method.addParameter("output", "json");
					method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
					try {
						int status = httpClient.executeMethod(method);
						if(status == 200){
							String resultData = method.getResponseBodyAsString();
							if(resultData != null && !"".equals(resultData)){
								Map<String, Object> map = JSONUtil.JSONToObject(resultData, Map.class);
								if(map != null && (int)map.get("status")==0){
									Map<String, Object> result = (Map<String, Object>)map.get("result");
									if(result != null){
										Map<String, Object> location = (Map<String, Object>)result.get("location");
										if(location != null){
											Store store = new Store();
											store.setId(storeList.get(i).getId());
											store.setLng(location.get("lng").toString());
											store.setLat(location.get("lat").toString());
											StoreMapper.updateByPrimaryKeySelective(store);
										}
										System.out.println("执行成功");
//										if(i==500){
//											ak="N5Wyb85nHkLWs0FHz9yoIkmbzs55NGva";
//										}else if(i==1000){
//											ak="GmeC8Vox1AaY65IzhYb0H6TGg1nTEXQZ";
//										}else if(i==1500){
//											ak="Qxstg6Lo1cE3LRtgBVPPKIYUOjUzmE83";
//										}else if(i==2000){
//											ak="";
//										}else{
//											ak="";
//										}
									}
								}else{
									System.out.println("店铺地址不准确");
								}
							}else{
								System.out.println("远程请求服务失败");
							}
						}else{
							System.out.println("远程请求服务失败");
						}
					} catch (HttpException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						method.releaseConnection();
					}
				}
				
			}
		}
	}
}
