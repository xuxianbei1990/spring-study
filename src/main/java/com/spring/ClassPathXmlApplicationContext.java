package com.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Java��XML API
//http://www.cnblogs.com/liuling/archive/2013/04/14/BeanFactory.html
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class ClassPathXmlApplicationContext implements BeanFactory{
    private Map<String, Object> beans = new HashMap<String, Object>();
    
    public ClassPathXmlApplicationContext(String fileName) throws Exception{
    	SAXReader reader = new SAXReader();
    	//this.getClass().getClassLoader().getResourceAsStream
    	Document document = reader.read((fileName));
    	List<Element> elements = document.getRootElement().elements("bean");
    	for (Element e: elements){
    		String id = e.attributeValue("id");
    		String value = e.attributeValue("class");
    		Object o = Class.forName(value).newInstance();
    		beans.put(id, o);
    	}
    }
	public Object getBean(String id) {
		return beans.get(id);
	}

}
