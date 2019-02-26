### Spring 注入 Bean 示例

#### 使用 Maven 加载依赖的 jar 包
用到的 pom.xml 如下:
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.albert.spring</groupId>
	<artifactId>HelloWorld</artifactId>
	<packaging>war</packaging>
	<version>1.0-SNAPSHOT</version>
	<name>Spring 入门 HelloWorld 项目</name>
    <description>Spring 注入 Bean 示例</description>
	<properties>
		<spring-version>4.0.6.RELEASE</spring-version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.8</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${spring-version}</version>
		</dependency>
	</dependencies>
</project>
```

这里使用 JUnit 测试，测试时会用到 ApplicationContext 对象，用它来加载 bean，所以依赖使用 spring-context, 它已经包含了 spring-beans 和 spring-core 等依赖。

#### 用到的 Java Bean 为 HelloWorld 类

```
package com.albert.spring;

public class HelloWorld {
	private String message;

	public void setMessage(String message){
	    this.message  = message;
	}

	public void getMessage(){
	    System.out.println("Your Message : " + message);
	}
}
```
它只有一个属性 message。

#### Bean 的配置文件

按照 Maven 约定，非 java 源文件的配置文件放在 src/main/resources 目录下，新建 Beans.xml，内容为:
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
   <bean id="helloWorld" class="com.albert.spring.HelloWorld">
       <property name="message" value="Hello World!"/>
   </bean>

    <bean id="helloWorld_2" class="com.albert.spring.HelloWorld"
            p:message="Hello World 2">
    </bean>
</beans>
```

第一个 Bean 使用 `<property name="" value="" />`的形式为 Bean 的属性赋值，
第二个 Bean 使用了 Spring 特有的命名空间 "http://www.springframework.org/schema/p"，直接使用 `p:property_name=""`的形式赋值。

#### 测试

测试程序放在 src/test/java 目录下:
```
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
```
直接使用 `ClassPathXmlApplicationContext`从classpath下的配置文件 Beans.xml 创建容器，然后就可以从容器中获取配置的 Bean

