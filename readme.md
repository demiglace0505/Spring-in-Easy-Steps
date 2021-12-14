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

## Dependency Check, Inner Beans and Scopes

#### Dependency Check

Using dependency check, we can ensure that an object has dependencies that are required. We can make the id field of a class mandatory using the **@Required** annotation. We attach this annotation to the setter method.

```java
	@Required
	public void setId(int id) {
		this.id = id;
	}
```

We then need to configure the config XML file to include the **@Required** anotation

```xml
	<bean class="com.demiglace.spring.springcore.dependencycheck.Prescription" name="prescription">
	</bean>
	<bean class="org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor"></bean>
```

Once done, when we try to run the program, we get the following BeanInitializationException error:

```
Property 'id' is required for bean 'prescription'
```

#### Inner Beans

We use inner beans to configure and define dependencies of a bean within another bean. In the example below, we have the following classes Employee and Address.

```java
public class Employee {
	private int id;
	private Address address;
}
```

```java
public class Address {
	private int hno;
	private String street;
	private String city;
}
```

We can set up Address as the inner bean for Employee using the property tag.

```xml
	<bean class="com.demiglace.spring.springcore.innerbeans.Employee"
		name="employee" p:id="123">
		<property name="address">
			<bean class="com.demiglace.spring.springcore.innerbeans.Address"
				name="address" p:hno="300" p:street="Doge St" p:city="Doge city">
			</bean>
		</property>
	</bean>
```

To test, we use the folowing Test class:

```java
public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/demiglace/spring/springcore/innerbeans/config.xml");
		Employee employee = (Employee) context.getBean("employee");
		System.out.println(employee);
	}
}
```

#### Bean Scopes

Scope refers to the availability of the object in the container. It can also be referred to as the number of objects created in the container of a particular bean type. There are five possible values. By default, Spring creates singleton.

> singleton
> prototype
> request
> session
> globalsession

The previous config.xml from inner beans will yield a singleton type. This is proved when calling the employee object twice, wherein both will have the same hashCode.

```java
public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/demiglace/spring/springcore/innerbeans/config.xml");
		Employee employee = (Employee) context.getBean("employee");
		System.out.println(employee.hashCode());

		Employee employee2 = (Employee) context.getBean("employee");
		System.out.println(employee2.hashCode());
	}
}
```

Configuring the bean's scope as prototype will cause the hashCodes to no longer match

```xml
	<bean class="com.demiglace.spring.springcore.innerbeans.Employee"
		name="employee" p:id="123" scope="prototype">
		<property name="address">
			<bean class="com.demiglace.spring.springcore.innerbeans.Address"
				name="address" p:hno="300" p:street="Doge St" p:city="Doge city">
			</bean>
		</property>
	</bean>
```

## Constructor Injection

Spring supports creating of objects using parameterized constructs. We use the `constructor-arg` tags for this. We first define a constructor in our Employee class

```java
public class Employee {
	private int id;
	private Address address;

	Employee(int id, Address address){
		this.address = address;
		this.id = id;
	}
```

We then configure our beans in the config.xml. We use `constructor-arg` to inject constructors and we can either use value tags or ref, in the case of a reference.

```xml
	<bean
		class="com.demiglace.spring.springcore.constructorinjection.Address"
		name="address" p:hno="123" p:street="Doge St" p:city="Doge city">
	</bean>

	<bean
		class="com.demiglace.spring.springcore.constructorinjection.Employee"
		name="employee">
		<constructor-arg>
			<value>123</value>
		</constructor-arg>
		<constructor-arg>
			<ref bean="address" />
		</constructor-arg>
	</bean>
```

We then write out the Test class

```java
public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/demiglace/spring/springcore/constructorinjection/config.xml");
		Employee employee = (Employee) context.getBean("employee");
		System.out.println(employee);
	}
}
```

Aside from using the `value` tags, we can also use them as an element, and the ref tag can be used as an attribute.

```xml
	<bean
		class="com.demiglace.spring.springcore.constructorinjection.Employee"
		name="employee">
		<constructor-arg value="123">
		</constructor-arg>
		<constructor-arg ref="address">
		</constructor-arg>
	</bean>
```

We can also use C schemas.

```xml
	<bean
		class="com.demiglace.spring.springcore.constructorinjection.Employee"
		name="employee" c:id="123" c:address-ref="address">
	</bean>
```

#### Ambiguity Problem

We can avoid type ambuiguity by using the type, index and name attributes.

```java
public class Addition {
	Addition(int a, double b) {
		System.out.println("Inside constructor");
		System.out.println(a);
		System.out.println(b);
	}
}
```

```xml
	<bean class="com.demiglace.spring.springcore.ambiguity.Addition" name="addition">
		<constructor-arg value="20.3" type="double" index="1">
		</constructor-arg>
		<constructor-arg value="10" type="int" index="0">
		</constructor-arg>
	</bean>
```

## Bean Externalization

We can set up properties from an external file using Bean Externalization. We first create the properties file `database.properties`

```
dbServer=dogeserver
dbPort=420
dbUser=test
dbPass=test
```

We then setup the beans in our config.xml. We import properties from the Spring context namespace

```xml
	<context:property-placeholder
		location="com/demiglace/spring/springcore/propertyplaceholder/database.properties" />

	<bean
		class="com.demiglace.spring.springcore.propertyplaceholder.MyDAO"
		name="myDAO">
		<constructor-arg>
			<value>${dbServer}</value>
		</constructor-arg>
	</bean>
```

## Auto-Wiring

The linking of the dependent and dependency bean can be done automatically by the Spring Container. This can be done in two ways: by XML or by Annotations.

#### XML Auto-Wiring

The following configuration makes use of the **byType** autowiring. When Spring creates the Employee bean, it will go to the Employee class and search for the object dependencies. In this case, it will find address as one of the dependencies. Spring will then determine the type, which is Address and searches for the bean with class Address and inject that bean into the Employee bean. **byName** on the other hand, works by searching for a bean with the same name of the reference variable defined, in this case _address_

```xml
	<bean
		class="com.demiglace.spring.springcoreadvanced.autowiring.Address"
		name="address" p:hno="123" p:street="Doge St" p:city="Doge city">
	</bean>

	<bean
		class="com.demiglace.spring.springcoreadvanced.autowiring.Employee"
		name="employee" autowire="byType">
	</bean>
```

To use auto wiring by constructor injection, we need to provide a parameterized constructor in our Java class.

```java
public class Employee {
	private Address address;

	Employee(Address address) {
		this.address = address;
	}
```

```xml
	<bean
		class="com.demiglace.spring.springcoreadvanced.autowiring.Address"
		name="address" p:hno="123" p:street="Doge St" p:city="Doge city">
	</bean>

	<bean
		class="com.demiglace.spring.springcoreadvanced.autowiring.Employee"
		name="employee" autowire="constructor">
	</bean>
```

#### Annotation Auto-Wiring

We can setup auto-wiring for the setters by using the **@Autowired** annotation. We need to make sure to include `<context:annotation-config/>` in our XML.

```java
	@Autowired
	public void setAddress(Address address) {
		this.address = address;
	}
```

Aside from using at the setter level, we can also use it on a field level

```java
	@Autowired
	private Address address;
```

And also at a constructor level, which will make use of constructor injection.

```java
	@Autowired
	Employee(Address address) {
		this.address = address;
	}
```

#### @Qualifier Annotation

The **@Qualifier** annotation tells the Spring Container that it should look for a bean with the given name and then inject that bean.

```java
public class Employee {
	@Autowired
	@Qualifier("address123")
	private Address address;

```

```xml
  <context:annotation-config/>

	<bean
		class="com.demiglace.spring.springcoreadvanced.autowiring.annotations.Address"
		name="address123" p:hno="123" p:street="Doge St" p:city="Doge city">
	</bean>

	<bean
		class="com.demiglace.spring.springcoreadvanced.autowiring.annotations.Address"
		name="address567" p:hno="567" p:street="Doge St" p:city="Doge city">
	</bean>

	<bean
		class="com.demiglace.spring.springcoreadvanced.autowiring.annotations.Employee"
		name="employee">
	</bean>
```

## Standalone Collections

To implement standalone collections, we use the util schema namespace. We import the namespace to the XML

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:util="http://www.springframework.org/schema/util"
	xsi:schemaLocation="
   	http://www.springframework.org/schema/util
    http://www.springframework.org/schema/util/spring-util.xsd
    ">

	<util:list list-class="java.util.LinkedList"
		id="productNames">
		<value>Mac Book</value>
		<value>iPhone</value>
	</util:list>

	<bean
		class="com.demiglace.spring.springcoreadvanced.standalone.collections.ProductsList"
		name="productslist">
		<property name="productNames">
			<ref bean="productNames" />
		</property>
	</bean>
</beans>
```

## Stereotype Annotations

We can use Stereotype Annotations to create and inject objects. When using the **@Component** annotation, Spring will automatically create an object with a bean name the same as the class, in camel-case. We can change this by providing a value in the Component annotation, in this case, inst.

We can also use the **@Scope** annotation to specificy the scope of an object. By default, the Spring Container singleton scope.

In the `base-package` attribute of the component-scan context, we provide the package which the Spring Container needs to scan for the classes marked with the **@Component** annotation. It is important to note that the component-scan context element already internally enables the annotation config.

```java
@Component("inst")
@Scope("prototype")
public class Instructor {
	@Value("10")
	private int id;
	@Value("Doge")
	private String name;
```

```xml
	<context:component-scan
		base-package="com.demiglace.spring.springcoreadvanced.stereotype.annotations">
	</context:component-scan>
```

To inject collection types as value, we use the util schema in the XML configuration. Then once we have assigned an id, we use the **@Value** annotation.

```java
@Component("inst")
@Scope("prototype")
public class Instructor {

	@Value("#{topics}")
	private List<String> topics;
```

```xml
	<context:component-scan
		base-package="com.demiglace.spring.springcoreadvanced.stereotype.annotations">
	</context:component-scan>

	<util:list list-class="java.util.LinkedList" id="topics">
		<value>Math</value>
		<value>Science</value>
		<value>English</value>
	</util:list>
```

For object types, we can inject using autowiring **@Autowired**.

```java
	@Autowired
	private Profile profile;
```

## Spring Expression Language

The Spring Expression Language supports parsing and executing expressions with the help of @Value annotation. The Spring Expression Language is denoted by `#{}`. To invoke a static method inside an expression we use the syntax `T(class).method(param)`

```java
	@Value("#{T(java.lang.Integer).MIN_VALUE}")
	private int id;
	@Value("#{'Doge'.toUpperCase()}")
	private String name;
```

We can also use the **new** operator.

```java
	@Value("#{new java.lang.String('DoGe')}")
	private String name;
```

## Injecting Interfaces

Consider the following structure: OrderBOImpl _is a_ OrderBO and OrderDAOImpl _is a_ OrderDAO. OrderBO _has a_ OrderDAO. We first create the POJI/POJO.

```java
public interface OrderDAO {
	void createOrder();
}
```

```java
public class OrderDAOImpl implements OrderDAO {
	@Override
	public void createOrder() {
		System.out.println("Inside OrderDAO createOrder()");
	}

}
```

```java
public interface OrderBO {
	void placeOrder();
}
```

```java
public class OrderBOImpl implements OrderBO {
	private OrderDAO dao;

	@Override
	public void placeOrder() {
		System.out.println("Inside OrderBO placeOrder()");
		dao.createOrder();
	}
}
```

We then proceed on configuring the XML. We first define the dependency bean, which is OrderDAOImpl. It is important to note that we always provide the implementation in the bean, and use the interface in the java class. Spring uses runtime polymorphism and injects any implementation class into the interface object.

```xml
	<bean class="com.demiglace.spring.springcoreadvanced.injecting.interfaces.OrderDAOImpl" name="dao">
	</bean>

	<bean class="com.demiglace.spring.springcoreadvanced.injecting.interfaces.OrderBOImpl" name="bo">
		<property name="dao" ref="dao"></property>
	</bean>
```

The test output will be

```
Inside OrderBO placeOrder()
Inside OrderDAOImpl createOrder()
```

Spring provides loose coupling in our application through runtime polymorphism, which means that we can inject different implementations of the dependencies at runtime without changing our dependent classes. For example, we can create a new class that implements OrderDAO

```java
public class OrderDAOImpl2 implements OrderDAO {
	@Override
	public void createOrder() {
		System.out.println("Inside OrderDAOImpl2 createOrder()");
	}
}

```

We just need to create a new bean in our config.xmland change the ref in the dependent bean

```xml
	<bean
		class="com.demiglace.spring.springcoreadvanced.injecting.interfaces.OrderDAOImpl"
		name="dao">
	</bean>

	<bean
		class="com.demiglace.spring.springcoreadvanced.injecting.interfaces.OrderDAOImpl2"
		name="dao2">
	</bean>

	<bean
		class="com.demiglace.spring.springcoreadvanced.injecting.interfaces.OrderBOImpl"
		name="bo">
		<property name="dao" ref="dao2"></property>
	</bean>
```

The test output will be

```
Inside OrderBO placeOrder()
Inside OrderDAOImpl2 createOrder()
```

#### Using Annotations

We can also use annotations. We tuse the **@Qualifier** annotation to tell Spring which class to inject in case there are multiple classes that implements an interface. In this case, Spring will inject the bean _dao_.

```java
@Component("bo")
public class OrderBOImpl implements OrderBO {
	@Autowired
  @Qualifier("dao")
	private OrderDAO dao;
```

```java
@Component("dao")
public class OrderDAOImpl implements OrderDAO {
```

```java
@Component("dao2")
public class OrderDAOImpl2 implements OrderDAO {
```

and in config.xml

```xml
<context:component-scan
		base-package="com.demiglace.spring.springcoreadvanced.injecting.interfaces"></context:component-scan>
```

the test output will be

```
Inside OrderBO placeOrder()
Inside OrderDAOImpl createOrder()
```

## Spring JDBC

When we use Java Database Connectivity to perform DB operations, we traditionally write a lot of boilerplate. Spring simplifies JDBC by providing the class **JDBCTemplate**, which is a combination of JDBC technology and the template design pattern. The template carries all the boilerplate. The JDBCTemplate in spring depends upon javax.sql.DataSource which is an interface that Spring provides an implementation in **DriverManagerDataSource**. The DriverManagerDataSource is responsible for creating the database connection and takes four parameters: driverClassName, url, username, password.

To setup the project, we need the following maven dependencies

> spring-context
> spring-jdbc
> mysql-connector-java

We configure the driver manager data source and the JDBC template beans in the config XML.

```xml
	<bean
		class="org.springframework.jdbc.datasource.DriverManagerDataSource"
		name="dataSource" p:driverClassName="com.mysql.jdbc.Driver"
		p:url="jdbc:mysql://localhost/mydb" p:username="root"
		p:password="1234">
	</bean>

	<bean class="org.springframework.jdbc.core.JdbcTemplate"
		name="jdbcTemplate" p:dataSource-ref="dataSource">
	</bean>
```

We can test that the JdbcTemplate is connecting to our MySQL database by the following test method

```java
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/demiglace/spring/springjdbc/config.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "INSERT into employee values(?,?,?)";
		int result = jdbcTemplate.update(sql, new Integer(1), "Doge", "Mr");
		System.out.println("Number of records inserted are:" + result);
	}
```

#### DTO and DAO

We can then proceed on creating the DTO and DAO objects. We first create the DTO and once we have this entity, we'll create a DAO interface that will be implemented by a DAO class. The implementation of the DAO class will contain all the database code, and then will use the jdbcTemplate to perform the database operations.

The DTO is the Employee class, Then the EMployeeDAO and its implementation

```java
public class Employee {
	private int id;
	private String firstName;
	private String lastName;
```

```java
public interface EmployeeDAO {
	int create(Employee employee);
}

```

```java
public class EmployeeDAOImpl implements EmployeeDAO {
	@Override
	public int create(Employee employee) {
		String sql = "INSERT into employee values(?,?,?)";
		int result = jdbcTemplate.update(sql, employee.getId(), employee.getFirstName(), employee.getLastName());
		return result;
	}
}
```

We then need to wire the configuration for EmployeeDAOImpl to use the JDBCTemplate.

```xml
	<bean
		class="org.springframework.jdbc.datasource.DriverManagerDataSource"
		name="dataSource" p:driverClassName="com.mysql.jdbc.Driver"
		p:url="jdbc:mysql://localhost/mydb" p:username="root"
		p:password="1234">
	</bean>

	<bean class="org.springframework.jdbc.core.JdbcTemplate"
		name="jdbcTemplate" p:dataSource-ref="dataSource">
	</bean>

	<bean
		class="com.demiglace.spring.springjdbc.employee.dao.impl.EmployeeDAOImpl"
		name="employeeDAO">
		<property name="jdbcTemplate">
			<ref bean="jdbcTemplate"></ref>
		</property>
	</bean>
```

And finally the test class:

```java
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/demiglace/spring/springjdbc/employee/test/config.xml");
		EmployeeDAO dao = (EmployeeDAO) context.getBean("employeeDAO");
		Employee employee = new Employee();
		employee.setId(2);
		employee.setFirstName("Cate");
		employee.setLastName("Mrs");
		int result = dao.create(employee);
		System.out.println("Number of records inserted are:" + result);
	}
```

To summarize, the Spring container reads from the configuration file 3 beans: first is the EmployeeDAOImpl which needs the JdbcTemplate bean which then needs the dataSource bean. The Spring container will then create an instance of the Data Source using the information provided such as the driverClassName, username, password, and url. Spring then injects the dataSource bean into the JdbcTemplate bean which uses the JDBCTemplate object to inject into the employeeDAOImpl while creating an object of the employeeDAOImpl. We then create an Employee DTO and invoke the create method. In the create method, we are using the JDBCTemplate to execute the sql statement.

#### Reading records

There are two methods for doing a select query in JDBCTemplate: **queryForObject** and **query**. RowMapper is an interface from the Spring Framework that we need to implement when using these queries. It maps the JDBC Result Set that comes back into an object of a class that we create. We start by creating a RowMapper class. The integers passed into the getInt, getString method are the column number.

```java
public class EmployeeRowMapper implements RowMapper<Employee> {
	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setId(rs.getInt(1));
		emp.setFirstName(rs.getString(2));
		emp.setLastName(rs.getString(3));
		return emp;
	}
}
```

We can then use this into our read method.

```java
	@Override
	public Employee read(int id) {
		String sql = "SELECT * from employee WHERE id=?";
		EmployeeRowMapper rowMapper = new EmployeeRowMapper();
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return employee;
	}
```

To read multiple records, we make use of the **query** method.

```java
	@Override
	public List<Employee> read() {
		String sql = "SELECT * from employee";
		EmployeeRowMapper rowMapper = new EmployeeRowMapper();
		List<Employee> result = jdbcTemplate.query(sql, rowMapper);
		return result;
	}
```

#### Auto-Wiring

We can make use of the **@Component** and **@Autowired** annotations to auto-wire the jdbcTemplate. Instead of

```xml
	<bean
		class="com.demiglace.spring.springjdbc.employee.dao.impl.EmployeeDAOImpl"
		name="employeeDAO">
		<property name="jdbcTemplate">
			<ref bean="jdbcTemplate"></ref>
		</property>
	</bean>
```

We can use annotations in the POJO

```java
@Component("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
```

## Spring ORM

In Spring ORM, we map every field in the class into a database column. The ORM will then automatically convert objects into database rows and vice versa. The ORM will provide the SQL on the fly so we don't need to write any SQL queries anymore. The Java EE standard for doing ORM is JPA, Java Persistence API. Hibernate is an example of an implementation of the JPA standard. Spring provides the **HibernateTemplate** to make it easy to use Hibernate.

The design for an application that uses the HibernateTemplate will be similar to the following. An entity class which will be marked with all the annotations, the entityDAO interface which will have all the database code, and its corresponding entityDAOImpl which depends upon HibernateTemplate. The HiberNateTemplate uses the SessionFactory bean interface, which takes care of establishing the connection.

#### Mapping an Entity to a Database table

We can either use XML or Annotations to do ORM Mapping. There are four JPA annotations commonly used: **@Entity**, **@Table**, **@Id**, **@Column**. The @Entity annotation is used on the class itself. The @Id annotation is used on the field that will serve as a primary key. The @Table is optional and is only used if the database table name is different from the class name. The same goes for the @Column annotation.

We start by creating the entity:

```java
@Entity
@Table(name="product")
public class Product {
	@Id
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String desc;
	@Column(name="price")
	private double price;
```

We then create a DAO interface and its implementation, which we autowire with the hibernateTemplate bean.

```java
public interface ProductDAO {
	int create(Product product);
}

```

```java
@Component
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public int create(Product product) {
		return 0;
	}
}
```

We then configure the LocalSessionFactoryBean. This requires the following dependencies: the DataSource, HibernateProperties and list of entity classes. The configuration file looks like the following:

```xml
  <context:component-scan base-package="com.demiglace.spring.springorm.product.dao.impl"></context:component-scan>

	<bean
		class="org.springframework.jdbc.datasource.DriverManagerDataSource"
		name="dataSource" p:driverClassName="com.mysql.jdbc.Driver"
		p:url="jdbc:mysql://localhost/mydb" p:username="root"
		p:password="1234">
	</bean>

	<bean
		class="org.springframework.orm.hibernate5.LocalSessionFactoryBean"
		name="sessionFactory" p:dataSource-ref="dataSource">
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
				<prop key="hibernate.show_sql">true</prop>
			</props>
		</property>
		<property name="annotatedClasses">
			<list>
				<value>com.demiglace.spring.springorm.product.entity.Product</value>
			</list>
		</property>
	</bean>

	<bean class="org.springframework.orm.hibernate5.HibernateTemplate"
		name="hibernateTemplate" p:sessionFactory-ref="sessionFactory">
	</bean>
```

#### Transaction Manager

When we execute multiple update statements, there are scenarios wherein we want either all of the transactions to succeed or none should succeed. This is done by the Transaction Manager using commit and rollback.

Spring makes it easy to do transaction management by providing a TransactionManager. We first need to configure the HibernateTransactionManager bean and then add annotation support using the XML configuration. Finally, we will use the @Transactional annotation to our java methods.

```xml
<beans
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:util="http://www.springframework.org/schema/util"
	xsi:schemaLocation="
    http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx.xsd
    ">

    <tx:annotation-driven/>

	<bean class="org.springframework.orm.hibernate5.HibernateTemplate"
		name="hibernateTemplate" p:sessionFactory-ref="sessionFactory">
	</bean>

	<bean
		class="org.springframework.orm.hibernate5.HibernateTransactionManager"
		name="transactionManager" p:sessionFactory-ref="sessionFactory">
	</bean>
</beans>
```

At this point, we can now implement the create, update and delete methods in our DAOImpl class.

```java
	@Override
	@Transactional
	public int create(Product product) {
		Integer result = (Integer) hibernateTemplate.save(product);
		return result;
	}

	@Override
	@Transactional
	public void update(Product product) {
		hibernateTemplate.update(product);
	}

	@Override
	@Transactional
	public void delete(Product product) {
		hibernateTemplate.delete(product);
	}
```

#### Fetching Records

To fetch a single record from the database, we can make use of the Hibernate Template **get** method. This method takes the return type as the first parameter, and for the second parameter, we pass into it the id.

```java
	@Override
	public Product find(int id) {
		Product product = hibernateTemplate.get(Product.class, id);
		return product;
	}
```

To fetch multiple records, we use the **loadAll** method of Hibernate Template.

```java
	@Override
	public List<Product> findAll() {
		List<Product> products = hibernateTemplate.loadAll(Product.class);
		return products;
	}
```
