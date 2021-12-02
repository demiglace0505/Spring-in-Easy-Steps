# Spring in Easy Steps

### Steps for DI

We first create a POJO Employee.java file which contains the getters and setters.

```java
public class Employee {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
```

Afterwards, we create the configuration config.xml file.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">

    <bean name="emp" class="com.demiglace.spring.springcore.Employee">
    	<property name="id">
    		<value>20</value>
    	</property>
    	<property name="name">
    		<value>Doge</value>
    	</property>
    </bean>

</beans>
```

And finally we test it using ClassPathXmlApplicationContext

```java
public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		Employee emp = (Employee) ctx.getBean("emp");
		System.out.println("Employee ID " + emp.getId());
		System.out.println("Employee Name: " + emp.getName());
	}
}
```

## Setter Injection

Setter Injection is the process of the Spring Container to using setter methods to do dependency injection. The steps are creating the Spring Beans (POJO), creating the Spring configuration file and using the Spring Bean from the container

### Collection Injection

We can also use Dependency Injection on Collections such as list, map, set and properties.

#### List

With the following Hospital class:

```java
public class Hospital {
	private String name;
	private List<String> departments;

	...
}
```

We define the following bean:

```xml
	<bean name="hospital"
		class="com.demiglace.spring.springcore.list.Hospital">
		<property name="name">
			<value>Global Hospital</value>
		</property>
		<property name="departments">
			<list>
				<value>Front Office</value>
				<value>In Patient</value>
				<value>Out Patient</value>
			</list>
		</property>
	</bean>
```

And we can test using a Test.java class

```java
public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("com/demiglace/spring/springcore/list/listconfig.xml");
		Hospital hospital = (Hospital) context.getBean("hospital");
		System.out.println(hospital.getName());
		System.out.println(hospital.getDepartments());
	}
}
```

#### Set

For sets, we can do the same. We start by creating the POJO

```java
public class CarDealer {
	private String name;
	private Set<String> models;

	...
}
```

Then the bean

```xml
	<bean name="cardealer"
		class="com.demiglace.spring.springcore.set.CarDealer">
		<property name="name">
			<value>Global Dealer</value>
		</property>
		<property name="models">
			<set>
				<value>Honda</value>
				<value>BMW</value>
				<value>Toyota</value>
			</set>
		</property>
	</bean>
```

And we finally write a test class

```java
public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("com/demiglace/spring/springcore/set/setconfig.xml");
		CarDealer cardealer = (CarDealer) context.getBean("cardealer");
		System.out.println(cardealer.getName());
		System.out.println(cardealer.getModels());
		System.out.println(cardealer.getModels().getClass());
	}
}
```

### Map

For Maps, we start with a POJO

```java

```

And for the bean, we can use of different configurations. We can use both key and value as attribute; key as attribute and value as element; key as element and value as attribute; both key and value as element

```xml
	<bean name="customer" p:id="20"
		class="com.demiglace.spring.springcore.map.Customer">
		<property name="products">
			<map>
				<!--both key and value as attribute -->
				<entry key="100" value="iPhone" />
				<!--key as attribute and value as element -->
				<entry key="200">
					<value>iPad</value>
				</entry>
				<!--key as element and value as attribute -->
				<entry value="Macbook Pro">
					<key>
						<value>300</value>
					</key>
				</entry>
				<!--both key and value as element -->
				<entry>
					<key>
						<value>400</value>
					</key>
					<value>Macbook Air</value>
				</entry>
			</map>
		</property>
	</bean>

```

#### Properties

For Proerty Injection, we can do the same by starting with a POJO

```java
public class CountriesAndLanguages {
	private Properties countryAndLangs;

	...
}

```

Then we configure the bean

```xml
	<bean name="countriesAndLangs"
		class="com.demiglace.spring.springcore.property.CountriesAndLanguages">
		<property name="countryAndLangs">
			<props>
				<prop key="USA">English</prop>
				<prop key="India">Hindi</prop>
			</props>
		</property>
	</bean>
```

And finally a test class

```java
public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("com/demiglace/spring/springcore/property/propconfig.xml");
		CountriesAndLanguages countriesAndLangs = (CountriesAndLanguages) context.getBean("countriesAndLangs");
		System.out.println(countriesAndLangs);
	}
}
```

#### Reference Types

This is used when a class has a HAS A relationship with another class. In this example, the Student class depends on the Scores class. We start with creating the POJO classes.

```java
public class Student {
	private Scores scores;
  ...
}

public class Scores {
	private Double math;
	private Double physics;
	private Double chemistry;
  ...
}
```

We then create a bean of the dependency class scores. Afterwards we create a bean for the dependent class, in this case, student.

```xml
	<bean name="scores"
		class="com.demiglace.spring.springcore.reftypes.Scores" p:math="88"
		p:physics="90" p:chemistry="87">
	</bean>

	<bean name="student" class="com.demiglace.spring.springcore.reftypes.Student">
		<property name="scores">
			<ref bean="scores" />
		</property>
	</bean>
```

Another way of doing this is using p-tags. `p:scores-ref` is used because the variable name that we are interested in is `scores`.

```xml
<bean name="scores"
		class="com.demiglace.spring.springcore.reftypes.Scores" p:math="88"
		p:physics="90" p:chemistry="87" />

	<bean name="student" class="com.demiglace.spring.springcore.reftypes.Student" p:scores-ref="scores">
	</bean>
```

And finally, the test class

```java
public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("com/demiglace/spring/springcore/reftypes/reftypeconfig.xml");
		Student student = (Student) context.getBean("student");
		System.out.println(student);
	}
}
```

## Life Cycle Methods

Spring provides lifecycle methods to every bean it creates. These are `init()` and `destroy()`. The init method is invoked after a spring bean is instantiated and the setter methods are called. The destroy method is invoked before destruction of an object. We can configure the lifecycle methods using 3 ways: first is through XML configuration, second is by implementing Spring interfaces and finally using Annotations.

#### Life Cycle Methods using XML configuration

We create a POJO with a `hi()` and `bye()` methods that we will use as init and destroy.

```java
public class Patient {
	private int id;

	public void hi() {
		System.out.println("Inside Hi Method");
	}

	public void bye() {
		System.out.println("Inside Bye Method");
	}
}
```

We then write our configuration XML. We pass `hi` into init-method and `bye` into destroy-method

```xml
	<bean class="com.demiglace.spring.springcore.lc.xmlconfig.Patient"
		name="patient" p:id="123" init-method="hi" destroy-method="bye" />
```

In the test class, we use **AbstractApplicationContext** to invoke registerShutdownHook method.

```java
public class Test {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("com/demiglace/spring/springcore/lc/xmlconfig/lcconfig.xml");
		Patient patient = (Patient) context.getBean("patient");
		System.out.println(patient);
		context.registerShutdownHook();

	}
}
```

#### Life Cycle Methods using Spring Interface

We just need to implement InitializingBean and DisposableBean in our POJO

```java
public class Patient implements InitializingBean, DisposableBean {
	private int id;

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Inside afterPropertiesSet()");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Inside destroy()");
	}
}
```

#### Life Cycle Methods using Annotations

In using annotations, we need to import from javax.annotation.

```java
import javax.annotation.PreDestroy;
import javax.annotation.PostConstruct;

public class Patient  {
	private int id;

	@PostConstruct
	public void hi() {
		System.out.println("Inside Hi Method");
	}

	@PreDestroy
	public void bye() {
		System.out.println("Inside Bye Method");
	}
}
```

We then need to enable annotations using a bean in our config XML. This enables support for the PostConstruct and PreDestroy annotations.

```xml
<bean class="org.springframework.context.annotation.CommonAnnotationBeanPostProcessor"></bean>
```

To enable all annotations, we can add the following inside `<beans>` of our XML

```xml
<context:annotation-config />
```
