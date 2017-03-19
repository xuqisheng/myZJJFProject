package com.zjjf.analysis.services;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.common.constants.Constants;
import com.zjjf.analysis.services.view.IView;

@Service(version = "1.0.0")
public abstract class AbstractViewService<T> implements IView {

	@Override
	public Object[] getColumn(Object[][] tableView, Integer key) {

		Object[] idColumn = new Object[tableView.length];
		for (int i = 0; i < tableView.length; i++) {
			Object[] str = tableView[i];
			idColumn[i] = str[key];
		}
		return idColumn;
	}

	public Object[] getOrderTitleEn(Object[][] authorityArray) {

		return getColumn(authorityArray, 0);
	}

	public Object[] getOrderTitleCn(Object[][] authorityArray) {

		return getColumn(authorityArray, 1);
	}

	public Object[] sort_by_viewTitle(T t, Object[] idColumn) {

		Object[] row = new Object[idColumn.length];
		try {
			for (int i = 0; i < idColumn.length; i++) {
				String key = (idColumn[i] + "").replace(Constants.authority_query, "");
				BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor property : propertyDescriptors) {
					String tempKey = property.getName();
					if (key.equals(tempKey)) {
						Method getter = property.getReadMethod();
						Object value = getter.invoke(t);
						row[i] = value;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	public List<Object[]> stand_by_title(List<T> _list, Object[] idColumn) {

		List<Object[]> idColumnList = new ArrayList<Object[]>();
		for (T t : _list) {
			Object[] temp = sort_by_viewTitle(t, idColumn);
			idColumnList.add(temp);
		}
		return idColumnList;
	}
}
