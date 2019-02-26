package com.albert.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	@Test
	public void test(){
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("Beans.xml");
	      HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
          HelloWorld obj2 = (HelloWorld) context.getBean("helloWorld_2");
	      obj.getMessage();
          obj2.getMessage();
	}
}
