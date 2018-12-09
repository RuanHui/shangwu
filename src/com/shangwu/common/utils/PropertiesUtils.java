package com.shangwu.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ruanhui
 * @date 2018/6/22
 */
public class PropertiesUtils {

    /**
     *@author ruanhui
     *@date 2018/6/22
     *@description 获取配置文件中的值
     */
    public static String getSchema(String schemaName) {
        String name = null;
        try {
            Properties properties = new Properties();
               // 使用ClassLoader加载properties配置文件生成对应的输入流
               InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("mybatis-sqlmap.properties");
               // 使用properties对象加载输入流
                properties.load(in);
            //获取key对应的value值
            name = properties.getProperty(schemaName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }


    /**
     *@author ruanhui
     *@date 2018/7/11
     *@description 获取对应配置文件中对应的值
     */
    public static String getSchema(String schema,String schemaName) {
        String name = null;
        try {
            Properties properties = new Properties();
               // 使用ClassLoader加载properties配置文件生成对应的输入流
               InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(schema);
               // 使用properties对象加载输入流
                properties.load(in);
            //获取key对应的value值
            name = properties.getProperty(schemaName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

}
