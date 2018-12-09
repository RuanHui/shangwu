package com.shangwu.common.utils;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class ParseXml {
	private final static Logger log = Logger.getLogger(ParseXml.class);
	/**
	 * @author luokun fanlei
	 * @time 2016/07/26
	 * @description 解析xml
	 * @param Xmldocument
	 * @param classs
	 * @return
	 */
	@SuppressWarnings("unchecked")  
    public static <T> T converyToJavaBean(String Xmldocument, Class<T> classs) {  
        T t = null;  
        try {    
            Unmarshaller unmarshaller = JAXBContext.newInstance(classs).createUnmarshaller();  
            t = (T) unmarshaller.unmarshal(new StringReader(Xmldocument));  
        } catch (Exception e) { 
        	log.error("发生错误，因为：" + e.getMessage());
            e.printStackTrace();  
        }  
        return t;  
    }  
	
	/**
	 * @author luokun fanlei
	 * @time 2016/07/26
	 * @description 生成xml
	 * @param Xmldocument
	 * @return
	 */
	public static String convertToXml(Object Xmldocument) {   
        StringWriter writer = null;
        try {   
            Marshaller marshaller = JAXBContext.newInstance(Xmldocument.getClass()).createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  
            writer = new StringWriter();  
            marshaller.marshal(Xmldocument, writer);  
        } catch (Exception e) { 
        	log.error("发生错误，因为：" + e.getMessage());
            e.printStackTrace();  
        }  
  
        return writer.toString();  
    }  

}
