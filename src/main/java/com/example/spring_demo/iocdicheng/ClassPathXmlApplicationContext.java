package com.example.spring_demo.iocdicheng;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

/**
 * spring ioc的实现原理
 * 手写spring ioc 实现过程
 */
public class ClassPathXmlApplicationContext {

    /**
     * xml文件的名称
     */
    private String xmlPath;

    public ClassPathXmlApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    /**
     * @param beanId bean的id值
     * @return 反射取得对象
     */
    public Object getBean(String beanId) throws DocumentException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        //1、读取xml配置文件
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(this.getClass().getClassLoader().getResourceAsStream(xmlPath));
        //获取根节点对象
        Element rootElement = document.getRootElement();
        //获取根节点下的bean(对象)
        List<Element> sonElements = rootElement.elements();
        Object obj = null;
        for (Element sonElement : sonElements) {
            //2、获取到每个bean配置，获取class地址
            String sonBeanId = sonElement.attributeValue("id");
            if (!beanId.equals(sonBeanId)) {
                continue;
            }
            //获取class地址
            String beanClassPath = sonElement.attributeValue("class");
            //3、拿到class地址，进行反射实例化对象，使用反射api，为私有属性赋值
            Class<?> forName = Class.forName(beanClassPath);
            obj = forName.newInstance();
            //根据bean对象，拿到成员属性
            List<Element> sonSonElements = sonElement.elements();
            for (Element element : sonSonElements) {
                String name = element.attributeValue("name");
                String value = element.attributeValue("value");
                //使用反射api，为私有属性赋值
                Field declaredField = forName.getDeclaredField(name);
                //开启对私有熟悉赋值的权限
                declaredField.setAccessible(true);
                declaredField.set(obj, value);
            }
        }
        return obj;
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, DocumentException, NoSuchFieldException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("dataformatxml" + File.separator + "user.xml");
        Object user = applicationContext.getBean("user1");
        UserInfo userInfo = (UserInfo) user;
        System.out.println(userInfo.toString());
    }

}
