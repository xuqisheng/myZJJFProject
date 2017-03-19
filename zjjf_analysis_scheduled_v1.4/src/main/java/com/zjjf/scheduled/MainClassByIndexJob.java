package com.zjjf.scheduled;

import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("resource")
public class MainClassByIndexJob {

	/**
	 * args[0] 为Service的全路劲
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(args != null){
			try {
				String classPathName = args[0];
				ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
				//ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:config/applicationContext.xml");
				// 加载要反射的类
				Object instance = Class.forName(classPathName).newInstance();
				String beanName = classPathName.substring(classPathName.lastIndexOf(".") + 1, classPathName.length());
				instance = context.getBean(beanName.substring(0,1).toLowerCase() + beanName.substring(1));
				if (args.length == 2) {
					Method method = instance.getClass().getMethod("excuse", new Class[] { String.class, String.class });
					try {
						method.invoke(instance, classPathName, args[1]);
					} catch (Exception e) {
						System.out.println("excuse执行异常, e: " + e.getMessage() + "; " + e.getCause() + "");
					}
				} else if (args.length == 3) {
					Method method = instance.getClass().getMethod("excuse",
							new Class[] { String.class, String.class, String.class });
					try {
						method.invoke(instance, classPathName, args[1], args[2]);
					} catch (Exception e) {
						System.out.println("excuse执行异常, e: " + e.getMessage() + "; " + e.getCause() + "");
					}
				} else {
					System.out.println("传入参数个数错误！ ");
				}
			} catch (Exception e) {
				System.out.println("传入参数，获取类实例异常，e: " + e);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("传入参数个数错误！ ");
		}
	}
}
