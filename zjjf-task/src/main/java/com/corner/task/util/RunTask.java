package com.corner.task.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
@Component
public class RunTask {
	public final Map<String, GTask> TASKS = new HashMap<String, GTask>(12);
	
	public final Map<String, ScheduledFuture<?>> SCHEDULED_FUTURE = new HashMap<String, ScheduledFuture<?>>(16);
}
