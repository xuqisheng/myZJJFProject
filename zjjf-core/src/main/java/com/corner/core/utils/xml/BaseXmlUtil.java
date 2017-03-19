package com.corner.core.utils.xml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @ClassName: BaseXmlUtil
 * @Description: TODO(JAXP API) ·org.w3c.dom ，W3C 推荐的用于 XML 标准规划文档对象模型的 Java 工具
 *               ·org.xml.sax，用于对 XML 进行语法分析的事件驱动的简单 API ·javax.xml.parsers
 *               ，工厂化工具，允许应用程序开发人员获得并配置特殊的语法分析器工具 JDOM 能够替换org.w3c.dom软件包来有计划地操作
 *               XML 文档 javax.xml.transform 定义了XSLT API，使用它，你可以将XML转化为一般的可视的页面。
 * @author luke
 * @email luke@mibodoctor.com
 * @date 2015年4月16日 下午2:22:59
 * 
 */
public class BaseXmlUtil {
	private static final Logger logger = LoggerFactory.getLogger(BaseXmlUtil.class);
    /** JAXP attribute used to configure the schema language for validation. */  
    private static final String SCHEMA_LANGUAGE_ATTRIBUTE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";  
  
    /** JAXP attribute value indicating the XSD schema language. */  
    private static final String XSD_SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";

	public BaseXmlUtil() {

	}

	public Document readXMLFile(String fileName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
	        // 设置Namespace有效  
            factory.setNamespaceAware(true);  
            // 打开验证  
            factory.setValidating(true);  
            //设置验证的SCHEMA方式为XSD  
            factory.setAttribute(SCHEMA_LANGUAGE_ATTRIBUTE, XSD_SCHEMA_LANGUAGE); 
            DocumentBuilder documentBuilder = factory.newDocumentBuilder(); 
            documentBuilder.setEntityResolver(new EntityResolver() {  
                @Override  
                public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {  
                    Map<String, String> schemaMappings = new HashMap<String, String>();  
                    try {  
                        Properties mappings = PropertiesLoaderUtils.loadAllProperties("META-INF/spring.schemas", null);  
                        CollectionUtils.mergePropertiesIntoMap(mappings, schemaMappings);  
                        ResourceLoader rl = new DefaultResourceLoader();  
                        Resource resource = rl.getResource(schemaMappings.get(systemId));  
                        InputSource inputSource = new InputSource(resource.getInputStream());  
                        inputSource.setPublicId(publicId);  
                        inputSource.setSystemId(systemId);  
                        return inputSource;  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                    return null;  
                }  
            });  
  
            // 解析错误处理  
            documentBuilder.setErrorHandler(new ErrorHandler() {

				@Override
				public void warning(SAXParseException exception) throws SAXException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void error(SAXParseException exception) throws SAXException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					// TODO Auto-generated method stub
					
				}
            	
            });  
            Document document = documentBuilder.parse(fileName);  
            Element root = document.getDocumentElement();  
            //printNode(root);  
			File file = new File(fileName);
			doc = builder.parse(file);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		}
		return doc;

	}

	public void readXMLElements(NodeList nodeList) {

		NamedNodeMap attributes = null;

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childNode = nodeList.item(i);
			String str = null;
			if (childNode instanceof Element) {
				Element child = (Element) childNode;
				if (child.getChildNodes().getLength() > 1) {
					str = "Element :" + child.getTagName() + ";";
				} else {
					str = "Element :" + child.getTagName();
					str += " : " + child.getFirstChild().getNodeValue() + "  ";
				}
				attributes = child.getAttributes();
				for (int j = 0; j < attributes.getLength(); j++) {
					Node attr = attributes.item(j);
					if (attr instanceof Attr) {
						String attrName = attr.getNodeName();
						String attrValue = attr.getNodeValue();
						str += "  Attribute " + attrName + " : " + attrValue + ";";
					}

				}
				System.out.println(str);
			}

		}
	}

    /** 
     * 递归打印document的主要节点 
     *  
     * @param e 
     */  
    private static void printNode(Element e) {  
        if (e.hasChildNodes()) {  
            NodeList subList = e.getChildNodes();  
            for (int i = 0; i < subList.getLength(); i++) {  
                Node n = subList.item(i);  
                if (n instanceof Element) {  
                    printNode((Element) n);  
                }  
            }  
        } else {  
            StringBuffer sb = new StringBuffer();  
            sb.append("<").append(e.getNodeName());  
            if (e.hasAttributes()) {  
                NamedNodeMap attr = e.getAttributes();  
                for (int i = 0; i < attr.getLength(); i++) {  
                    sb.append(" ").append(attr.item(i).getNodeName()).append("=\"").append(attr.item(i).getNodeValue()).append("\"");  
                }  
            }  
            sb.append(">");  
  
            String content = e.getNodeValue();  
            if (StringUtils.isEmpty(content)) {  
                sb.append(content);  
            }  
            sb.append("</" + e.getNodeName() + ">");  
            System.out.println(sb);  
  
        }  
    }  
  
    /** 
     * SAX解析测试 
     *  
     * @param file 
     */  
    public static void testSAXParser(File file) {  
  
        try {  
            SAXParserFactory factory = SAXParserFactory.newInstance();  
            factory.setNamespaceAware(true);  
            factory.setValidating(true);  
  
            SAXParser parser = factory.newSAXParser();  
            //parser.parse(file, new SAXParserHandler());  
        } catch (ParserConfigurationException e) {  
            e.printStackTrace();  
        } catch (SAXException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * SAP解析事件回调 
     *  
     * @author zhangpu 
     *  
     */  
    static class SAXParserHandler extends DefaultHandler {  
        /** 
         * uri: Namespace URI, localName: 没有前缀的节点名称, qName: 节点全名，包括NAMEPSACE前缀, 
         * Attributes： 属性 
         */  
        @Override  
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
            logger.info("startElement - localName:" + localName + "; qName:" + qName + "; uri:" + uri);  
            for (int i = 0; i < attributes.getLength(); i++) {  
                logger.info("attribute_" + i + " - " + attributes.getLocalName(i) + " : " + attributes.getValue(i));  
            }  
        }  
  
        @Override  
        public void endElement(String uri, String localName, String qName) throws SAXException {  
            logger.info("endElement - localName:" + localName + "; qName:" + qName + "; uri:" + uri);  
        }  
  
        @Override  
        public void characters(char[] ch, int start, int length) throws SAXException {  
            String content = new String(ch, start, length);  
            content = content.trim();  
            if (content.length() > 0) {  
                logger.info("characters: " + content);  
            }  
        }  
  
    }  
	
//	public static void main(String[] args) {
//		ReadXML readXML = new ReadXML();
//		Document doc = readXML.readXMLFile("E:/workspace/ReadXML/src/com/qu/xml/type.xml");
//		NodeList nodeList = doc.getElementsByTagName("*");
//		readXML.readXMLElements(nodeList);
//
//	}
}