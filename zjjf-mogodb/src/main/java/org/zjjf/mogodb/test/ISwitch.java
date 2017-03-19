package org.zjjf.mogodb.test;

import java.util.List;

import org.bson.Document;

public interface ISwitch {

	public Document switchBean2Mongo(Object o);

	public List<Document> switchList(List<Object> list);
}
