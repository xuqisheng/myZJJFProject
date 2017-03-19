package com.corner.task.config;

import java.util.Hashtable;

import com.corner.task.beans.SKUActive;

public class SessionConfig {
	/**
	 * 	用户session key
	 */
	public static String USER_SESSION_KEY = "USER_SESSION_KEY";
	
	public static Hashtable<String, SKUActive> skuActiveMap = new Hashtable<String,SKUActive>();
}
