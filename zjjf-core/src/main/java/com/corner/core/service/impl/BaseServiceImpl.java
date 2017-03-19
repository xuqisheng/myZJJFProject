package com.corner.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.service.IBaseService;


public class BaseServiceImpl<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseService<M, PK> {

	protected static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);


}
