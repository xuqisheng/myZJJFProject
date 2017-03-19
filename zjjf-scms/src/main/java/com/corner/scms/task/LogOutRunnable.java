package com.corner.scms.task;

import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogOutRunnable implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(LogOutRunnable.class);

	private Subject subject ;
	public LogOutRunnable(Subject subject ) {
		this.subject = subject;
	}
	
	@Override
	public void run() {
		try {
			subject.logout();
		} catch (Exception ex) {
			logger.error("保存开店宝搜索记录异常", ex);
		}
	}

}
