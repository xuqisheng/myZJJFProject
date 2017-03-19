package com.zjjf.scheduled;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings({ "resource", "rawtypes" })
public class MainClassByTimeJob {

	/**
	 * args[0] 为Service的全路劲, args[1]=1为索引job, args[1]=2为时间job
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (args != null) {
			
			if (!"2".equals(args[1])) {
				System.out.println("参数类型错误; 按照索引job, flag=1; 按照时间执行job, flag=2");
				return;
			}
			try {
				String classPathName = args[0];
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				// 加载要反射的类
				Object instance = Class.forName(classPathName).newInstance();
				String beanName = classPathName.substring(classPathName.lastIndexOf(".") + 1, classPathName.length());
				instance = context.getBean(beanName.substring(0, 1).toLowerCase() + beanName.substring(1));
				if (args.length >= 2 && args.length <= 4) {
					methodInvoke(instance, args);
				} else {
					System.out.println("传入参数个数错误！ ");
				}
			} catch (Exception e) {
				System.out.println("传入参数，获取类实例异常，e: " + e);
				e.printStackTrace();
			}
		} else {
			System.out.println("传入参数个数错误！ ");
		}
	}

	private static void methodInvoke(Object instance, String args[]) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Method method = instance.getClass().getMethod("excuse", getParamClassArray(args));
		method.invoke(instance, getParamObjectArray(args));
	}

	private static Class[] getParamClassArray(String args[]) {

		Class[] a = new Class[args.length];
		for (int i = 0; i < a.length; i++) {
			a[i] = String.class;
		}
		return a;
	}

	private static Object[] getParamObjectArray(String args[]) {

		Object[] a = new Object[args.length];
		for (int i = 0; i < a.length; i++) {
			a[i] = args[i];
		}
		return a;
	}
}
