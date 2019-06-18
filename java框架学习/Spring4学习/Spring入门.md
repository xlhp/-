### Spring4 入门学习

#### 学习计划

1. 第一阶段: spring 概述  springIOC入门(XML)   , spring的Bean管理 , spring属性注入
2. 第二阶段: springIOC的注解方式, spring的AOP开发(XML)
3. 第三阶段:SpingAOP的注解开发, Spring的声明式事务, jdbcTemplate
4. 第四阶段:SSH整合 (暂时放弃)

#### 基本概述

1. Spring叫做se/EE开发的一站式框架
   1. 一站式: Spring可以解决所有层的问题
      1. WEB层:Spring-MVC
      2. Service层: Spring的Bean管理  , Spring声明式事务
      3. DAO层 : Spring的JDBC模板, Spring的ORM模块
2. Spring基本版本
   1. Spring3-x
   2. Sping4-x

#### SpringIOC入门

1. ##### 什么是IOC

   1. 概念: 控制反转 (Inversion of control)
   2. IOC : 将对象的创建权限, 交给Spring管理 

2. 下载Spring开发包

3. spring开发包

   1. docs  : Spring的开发规范和API文档
   2. libs : Sping的开发Jar文件和源码
   3. schema : Spring的配置文件的约束

4. 第一个例子:

   1. 基本包识别 : 四个基础包+日志包logging/log4j
      1. apache-common-logging.jar : apache 提供的一个日志记录接口
      2. log4j : 具体实现了logging的接口
      3. spring基础包  : 
         1. context 
         2. core
         3. beans
         4. expression

   2. 问题: 
      1. 如何在不修改程序源代码的情况下 , 对原有代码进行扩展
         1. 个人想法: 使用反射,  传入方法名, 获取方法 , 并执行
         2. 工厂设计模式 , 可以作为解耦的方式
         3. 真正SpringIOC实现: 工厂+反射+配置文件  , 已实现程序解耦合 

   3. SpringIOC如何管理的
      1. applicationContext.xml

   4. DI(依赖注入)

      1. 前提: 必须有IOC的环境 , Spring管理这个类的时候将类的依赖啊的属性注入进来
   5. Spring工厂类

      1. 老版本使用beanFactory
         1. 调用getBean的时候,才会创建配置文件中的管理的类
      2. **当前版本使用ApplicationContext继承了BeanFactory**
         1. 当初始化ApplicationContext后 , 就会初始化配置文件中所有的类
         2. 两个实现类
            1. ClassPathXMlApplicationContext
               1. 用于加载类路径下的配置文件
            2. FileSystemXmlApplicationContext
               1. 用于加载系统目录下的配置文件


#### Spring基本配置

1. ##### schema 可以配置提示(Eclipse)

2. bean标签

   1. id & name
      1. id   :   使用了约束中的唯一约束  (  不可出现特殊字符 )
      2. name   :  没有使用约束的唯一约束( 理论上可以出现重复 , 实际开发中不允许出现相同 , 可以出现特殊字符 )   
   2. class  :  类的全路径

3. bean声明周期配置

   1. init-method  :  当初始化类的时候 , 调用该方法
   2. destroy-method ： 当类被销毁的时候，　会被 调用,  销毁时间:  工厂被销毁的时候才会销毁单例创建的bean  , applicationContext无法销毁, 子类才有这种销毁方法 , 不需要重点记

4. ***Bean的作用范围***

   1. Scope   => bean的作用范围
      1. singleton   :  默认 单例
      2. prototype  :  多例模式
      3. request  :  应用到web项目中,  Spring创建这个类后, 将这个类存入到request范围中
      4. sesssion  : 应用在web项目中, Spring创建这个类后, 将这个类存入到session范围中
      5. globalsession  : 应用web项目中, 必须在porlet环境下使用 , 如果没有在porlet环境中, 相当于session

5. Bean创建的三种方式

   1. 无参构造 , 提供一个无参构造方法 , 配置文件与原始配置bean无任何更改

   2. 静态工厂实例化的方式

      1. 需提供一个静态创建方法 , 该方法返回一个bean对象

         ```java
         public class Bean2Factory{
             public static Bean2 createBean2(){
                 .....
                 return new Bean2();
             }
             
         }
         ```

         

      2. 配置文件中

         ```xml
         <bean id="bean2" class="com.xxx.xxx" factory-method="ceateBean2" ></bean>
         ```

   3. 实例工厂实例化的方式

      1. 提供一个工厂类及工厂方法

         ```java
         public class Bean3Factory{
             public Bean3 createBean3(){
                 .....
                 return new Bean3();
             }
         }
         ```

      2. 配置文件

         ```xml
         <bean id="bean3Factory" class="com.xxxxxxxx.xxx"></bean>
         <bean id="bean3" factory-bean="bean3Factory" factory-method="createBean3"></bean>
         ```

6. Spring属性注入(有三种方法可以使用 , spring 支持两种)

   1. 使用有参构造

      ```xml
      <bean id="" class="">
      	<constructor-arg name="" value=""></constructor-arg>
      	<!-- 对象作为参数 -->    
          <constructor-arg name="" ref=""></constructor-arg>
      </bean>
      ```

      

   2. 使用set方法

      ```xml
      <bean id="" class="">
          <property name="" value=""></property>
          <!-- 对象作为参数 -->
          <property name="" ref=""></property>
      </bean>
      ```

   3. 接口注入 (spring不支持) 

   4. 例子

      1. xml文件

         ```xml
         <!-- 多种方式实现property-dependency-inject -->
             <bean id="car1" class="xyz.xlhp.Demo02.CarDemo01" init-method="createMethod" destroy-method="destroyMethod">
                 <constructor-arg name="name" value="夜宴"/>
                 <constructor-arg name="worth" value="100000"/>
             </bean>
             <bean id="car2" class="xyz.xlhp.Demo02.CartDemo02" init-method="initMethod" destroy-method="destroyMethod">
                 <property name="name" value="BYD王朝"></property>
                 <property name="worth" value="1000000"></property>
             </bean>
         	<!-- carTyre 是一个Tyre类型的Property -->
             <bean id="car3" class="xyz.xlhp.Demo02.CarDemo03">
                 <property name="name" value="东风"></property>
                 <property name="carTyre" ref="tyre"></property>
                 <property name="worth" value="1000000"></property>
             </bean>
             <bean id="tyre" class="xyz.xlhp.Demo02.Tyre">
                 <property name="name" value="米其林"></property>
             </bean>
         ```

      2. CarDemo01

         ```java
         
         /**
          * 有参构造方法实现property inject
          * @Author 丁兆顺
          * @Date 17:18 2019/4/23
          **/
         public class CarDemo01 {
             private String name;
             private double worth;
             /**
              * 有参构造
              * @Author 丁兆顺
              * @Date 17:20 2019/4/23
              * @param name
              * @param worth
              * @return 
              **/
             public CarDemo01(String name, double worth) {
                 this.name = name;
                 this.worth = worth;
             }
         
             /**
              * 对象创建激发
              * @Author 丁兆顺
              * @Date 17:33 2019/4/23
              * @param 
              * @return void
              **/
             public void createMethod(){
                 System.out.println("创建:"+this.getClass().getName());
             }
             /**
              * 对象销毁激发
              * @Author 丁兆顺
              * @Date 17:33 2019/4/23
              * @param
              * @return void
              **/
             public void destroyMethod(){
                 System.out.println("销毁:"+this.getClass().getName());
             }
         
             @Override
             public String toString() {
                 return "CarDemo01{" +
                         "name='" + name + '\'' +
                         ", worth=" + worth +
                         '}';
             }
         }
         ```

      3. 其他同理, 这里就不一一上传

   5. p名称空间的方式property-dependency-inject

      1. p名称空间注入在spring2.5加入spring框架中

      2. 引入p名称空间 

         ```xml
         xmlns:p = "http://www.springframework.org/schema/p
         ```

      3. 使用

         ```xml
         <!--以p名称空间注入写法-->
         <bean id="" class="" p:name="" p:price="" p:car2-ref=""></bean>
         ```

   6. SPEL实现property-dependency-inject (Sping Expression language)

      1. 从spring3.0加入spring

      2. #{SpEL}

      3. 例子

         ```java
         public class SPELDemo01 {
             private String position;
             private String name;
             private String worth;
             private Tyre tyre;
         
             public void setTyre(Tyre tyre) {
                 this.tyre = tyre;
             }
         
             public void setPosition(String position) {
                 this.position = position;
             }
         
             public void setName(String name) {
                 this.name = name;
             }
         
             public void setWorth(String worth) {
                 this.worth = worth;
             }
         
         
             @Override
             public String toString() {
                 return "SPELDemo01{" +
                         "position='" + position + '\'' +
                         ", name='" + name + '\'' +
                         ", worth='" + worth + '\'' +
                         ", tyre=" + tyre +
                         '}';
             }
         }
         
         ```

         ```xml
           <!--使用SpEL表达式来实现property-dependency-inject-->
             <bean id="garage" class="xyz.xlhp.Demo02.SPELDemo01" p:name="#{car3.name}" p:position="#{'黑科技管理学院门口'}"
                   p:worth="#{car3.worth*10}" p:tyre="#{car3.carTyre}"></bean>
         ```

   7. 集合属性注入

      1. 

7. 分模块开发配置

   1. 加载配置文件时, 一起加载多个

      ```java
      new ClassPathXmlApplicationContext("applicationConext.xml","applicationContext2.xml")
      ```

      

   2. 在一个配置文件中引入其他配置文件

      ```xml
      <import resource="applicationContext2.xml" />
      ```

      

​    

#### CRM的综合案例

1.   

​      

​      

​      

​      

​      

​      

​      