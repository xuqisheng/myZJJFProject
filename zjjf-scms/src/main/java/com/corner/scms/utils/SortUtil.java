/**   
* @Title: SortUtil.java 
* @Package com.corner.scms.utils 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月2日 下午6:09:09 
* @version V1.0   
*/

package com.corner.scms.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

/** 
* @ClassName: SortUtil 
* @Description:对list进行排序
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年2月2日 下午6:09:09 
*  
*/

public class SortUtil {
	
	
	/**
	 * 
	* @Title: sort 
	* @Description:对List进行排序
	* @param @param sortList 需要排序的List
	* @param @param param1 排序的参数名称
	* @param @param orderType 排序类型：正序-asc；倒序-desc 
	* @param @return
	* @return List<Object>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public static List sort(List sortList, String param1, String orderType) {
		Comparator mycmp1 = ComparableComparator.getInstance ();
		if("desc".equals(orderType)){
			mycmp1 = ComparatorUtils. reversedComparator(mycmp1); //逆序（默认为正序）
		}
		
		ArrayList<Object> sortFields = new ArrayList<Object>();
		sortFields.add( new BeanComparator(param1 , mycmp1)); //主排序（第一排序）

		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort (sortList , multiSort);
		
		return sortList;
	}

}
