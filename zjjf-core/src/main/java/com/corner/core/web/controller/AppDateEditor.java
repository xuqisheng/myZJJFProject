package com.corner.core.web.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class AppDateEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.isEmpty(text)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = format.parse(text);
			} catch (ParseException e) {
				format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date = format.parse(text);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			setValue(date);
		}
	}
}