package com.zjjf.analysis.services.core;

import java.util.ArrayList;
import java.util.List;

import com.zjjf.analysis.common.utils.DateUtil;

public abstract class AbstractTimeService<T, V> extends AbstractCoreService<T, V>{

	public abstract List<? extends T> getDataByTime(String yyyyMMdd, Integer offset);
	
	public abstract void _process(T t, V v, String dayTime);

	public void excuse(String jobkey, String type) {

		this.excuse(jobkey, type, DateUtil.getByTime("yyyyMMdd", 1));
	}
	
	public void excuse(String jobkey, String type, String dayTime, String all) {

		for (int i = Integer.valueOf(all); i > 0; i--) {
			excuse(jobkey, type, DateUtil.getByTime("yyyyMMdd", i));
		}
	}
	
	public void excuse(String jobkey, String type, String dayTime) {

		System.out.println("dayTime=" + dayTime);
		long beginTime = System.currentTimeMillis();
		@SuppressWarnings("unchecked")
		Class<V> _v_class = (Class<V>) getSuperClassGenricType(getClass(), 1);
		int i = 0;
		while (true) {
			i++;
			try {
				List<? extends T> dataList = getDataByTime(dayTime, i);
				System.out.println("批处理：" + jobkey + ", 第" + i + "次查询，spent time：" + (System.currentTimeMillis() - beginTime) + "ms!");
				if (dataList.size() == 0) {
					break;
				}
				process(jobkey, dataList, _v_class, dayTime);
			} catch (Exception e) {
				System.out.println("批处理：" + jobkey + ", 第" + i + "次, 异常 e :" + e.getCause());
				break;
			}
		}
		System.out.println("批处理：" + jobkey + "总共耗时，spent time：" + (System.currentTimeMillis() - beginTime) + "ms!");
	}
	
	public void process(String jobkey, List<? extends T> list, Class<V> _v_class, String dayTime) throws InstantiationException, IllegalAccessException {
		long beginTime = System.currentTimeMillis();
		List<V> _vlist = new ArrayList<V>();
		for (T t : list) {
			V v = _v_class.newInstance();
			_process(t, v, dayTime);
			_vlist.add(v);
		}
		batchAddBean(_vlist, dayTime);
		System.out.println("批处理：" + jobkey + "获取今日已经导入的数据； 总计：" + list.size() + "条， 总共耗时，spent time：" + (System.currentTimeMillis() - beginTime) + "ms!");
	}
	
	public void batchAddBean(List<V> _vList, String dayTime) {}
	
}
