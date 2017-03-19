package com.zjjf.analysis.services.core;

import java.util.List;

@SuppressWarnings("unchecked")
public abstract class AbstractIndexService<T, V> extends AbstractIndexSursor<T, V>  {

	public abstract Integer getCurrentIndex(T t);
	
	public abstract void addBean(T t, V v);
	
	public abstract List<? extends T> getDataByIndex(Integer index) ;
	
	public abstract void _process(T t, V v) ;
	
	public void excuse(String jobkey, String type) {

		if ("1".equals(type)) {
			long beginTime = System.currentTimeMillis();
			Integer index = getIndex(jobkey);
			int i = 0;
			Class<V> _v_class = (Class<V>) getSuperClassGenricType(getClass(), 1);
			while (true) {
				long beginqueue = System.currentTimeMillis();
				List<? extends T> dataList = getDataByIndex(index);
				if (dataList.size() == 0) {
					break;
				}
				i++;
				System.out.println("批处理：" + jobkey + ", 第" + i + "次查询，spent time：" + (System.currentTimeMillis() - beginqueue) + "ms!");
				for (T t : dataList) {
					try {
						_process(t, _v_class.newInstance());
					} catch (InstantiationException | IllegalAccessException e) {
						System.out.println("批处理：" + jobkey + "初始化 V 异常 e :" + e.getCause());
						break;
					}
					index = getCurrentIndex(t);
				}
				saveIndex(jobkey, index);
			}
			System.out.println("批处理：" + jobkey + "总共耗时，spent time：" + (System.currentTimeMillis() - beginTime) + "ms!");
		} else {
			System.out.println("参数类型错误; 按照索引job, flag=1; 按照时间执行job, flag=2");
		}
	}

	public boolean check_unique(T t, V v) {
		// TODO Auto-generated method stub
		return false;
	}
}
