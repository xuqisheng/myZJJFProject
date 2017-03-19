package com.corner.core.utils.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.MessageFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 
* @ClassName: XMLUtil
* @Description: TODO(使用java Jaxb 解析xml)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2015年4月15日 下午1:10:49
*
 */
public class JAXBXMLUtil {
	
    /**
     * 读取一个xmlString到javaBean,带Config配置
     */
    @SuppressWarnings("unchecked")
    public static <T> T readBeanFromXMLString(Class<T> clazz, String xmlString) throws JAXBException {
    	try {
    		JAXBContext jc = JAXBContext.newInstance(clazz);
    		Unmarshaller unmar  = jc.createUnmarshaller();
    		Reader reader = new StringReader(xmlString);
    		return (T) unmar .unmarshal(reader);
    	} catch (JAXBException e) {
    		// logger.trace(e);
    		throw e;
    	}
    }
    
    /**
     * 读取一个beanToXMLString
     */
    public static <T> String beanToXMLString(Class<T> clazz, T bean) throws JAXBException {
    	try {
    		JAXBContext jc = JAXBContext.newInstance(clazz);
    		Marshaller mar  = jc.createMarshaller();
    		mar.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
    		Writer writer = new StringWriter();
    		mar.marshal(bean, writer);
    		return writer.toString();
    	} catch (JAXBException e) {
    		// logger.trace(e);
    		throw e;
    	}
    }

	/**
	 * 读取一个xml文件到javaBean
	 */
    @SuppressWarnings("unchecked")
    public static <T> T readString(Class<T> clazz, String context) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(new File(context));
        } catch (JAXBException e) {
            throw e;
        }
    }

	/**
	 * 读取一个xml文件到javaBean,带Config配置
	 */
    @SuppressWarnings("unchecked")
    public static <T> T readConfig(Class<T> clazz, String config, Object... arguments) throws IOException, JAXBException {
        InputStream is = null;
        try {
            if (arguments.length > 0) {
                config = MessageFormat.format(config, arguments);
            }
            // logger.trace("read configFileName=" + config);
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            is = new FileInputStream(config);
            return (T) u.unmarshal(is);
        } catch (IOException e) {
            // logger.trace(config, e);
            throw e;
        } catch (JAXBException e) {
            // logger.trace(config, e);
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

	/**
	 * 读取一个xml流到javaBean,带Config配置
	 */
    @SuppressWarnings("unchecked")
    public static <T> T readConfigFromStream(Class<T> clazz, InputStream dataStream) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(dataStream);
        } catch (JAXBException e) {
            // logger.trace(e);
            throw e;
        }
    }
    
    
//    public static void main(String[] args) throws JAXBException {
//    	TestOrgs testOrgs  = new  TestOrgs();
//    	testOrgs.setSize(100);
//    	testOrgs.setErrmsg("dddd");
//        String xmlString = XMLUtil.beanToXMLString(TestOrgs.class, testOrgs);
//        System.out.println(xmlString);
//    }
}
