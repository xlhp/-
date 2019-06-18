### Spring注解IOC

1. 引入包

   ```java
   基础包: 
   	1,spring-context
   	2,spring-beans
   	3,spring-core
   	4,spring-expression
   	5,commons-logging
   	6,apache.log4j
   注解新增包:	
   	1,spring-aop
   ```

2. 引入Sping配置文件

   ```java
   1,src下创建applicationContext.xml
   2,引入context约束 , beans 是基础配置约束
   ```

3. 注解使用

   ```java
   // 相当于   <bean id="Demo01" class=""></bean>
   @Component(value="Demo01")//@Component("Demo01")
   public class AnnotationDemo01 {
       //可以没有set方法 , 如果有set方法 , 就可以在set方法上设置
   	@Value("王东")
       private String name;
       @Autowired
       private Dog dog; 
   }
   class Dog {
       private String type;
   }
   
   ```

4. 注解详解

   1. @Compontent: 组件
      1. 作用:修饰一个类, 将这个类交给Spring管理
      2. 有三个衍生注解 , 功能类似
         1. @Controller:  web层
         2. @Repository: dao层 
         3. @Service: service层

   2. 属性注入注解
      1. 普通属性
         1. @Value : 设置普通属性
      2. 对象类型
         1. @Autowired : 设置对象类型的值, 按照对象类型来完成注入
         2. @Qualifier(“name | id”) ： 强制按名称完成属性注入, 通常与@Autowired搭配使用
         3. **@Resource: 完成对象类型的属性注入, 是按照名称完成属性注入**

   3. 其他注解

      1. 声明周期相关

         ```java
         @PostConstruct  : 初始化
         @PreDestroy  : 销毁
         ```

      2. Bean作用范围

         ```Annotation
         @Scope : 作用范围
         		singleton: 单例
         		prototype : 多例
         		request　　：请求
         		session   : session
         		globalsession : 
         ```

5. xml与注解对比

   ```
   适用场景
           :xml:可以适用所有场景
           	结构清晰,维护方便
           :注解:有些地方适用不了  , 比如 , 这个类不是自己提供的
   			开发方便
   ```

6. xml和注解整合开发

   ```
   1,xml管理bean
   2,使用注解完成属性注入
   ```

   例子:

   1. applicationContext.xml

      ```xml
      <!--这个配置就可以满足,仅扫描属性注解的需要-->
          <context:annotation-config />
          <bean id="userDaoDemo01" class="xyz.xlhp.AnnotationIOC.Dao.DaoImpl.UserDaoImpl"></bean>
          <bean id="serviceDemo01" class="xyz.xlhp.AnnotationIOC.Service.ServiceDemo01"></bean>
          <bean id="serviceDemo02" class="xyz.xlhp.AnnotationIOC.Service.ServiceDemo02"></bean>
      ```

   2. ServiceDemo02

      ```java
        @Resource(name="userDaoDemo01")
          private UserDao userDao;
      
          public void save(){
              System.out.println(userDao);
              System.out.println("sesrviceDemo02->savemethod");
          }
      ```

      

   3. userDaoDemo01

      ```java
       @Value("丁兆顺")
          private String name;
          @Resource(name = "serviceDemo01")
          private ServiceDemo01 serviceDemo01;
      
          @Override
          public void save() {
              this.toString();
              System.out.println("UserDaoImpl->save-method");
          }
      
          @Override
          public String toString() {
              return "UserDaoImpl{" +
                      "name='" + name + '\'' +
                      ", serviceDemo01=" + serviceDemo01 +
                      '}';
          }
      ```

      

   4. test

      ```java
       ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
              ServiceDemo02 serviceDemo02 = (ServiceDemo02) applicationContext.getBean("serviceDemo02");
              System.out.println(serviceDemo02);
              serviceDemo02.save();
      ```



### SpringAOP开发

1. 概念 Aspect oriented Prograamming aop (面向切面编程)

2. 用于解决OOP开发中遇得到的问题 , 在不修改源码的基础上, 完成工作

3. AOP的应用场景

   ```
   传统的采取纵向继承   即 : 抽取公共方法, 通过继承/组合 完成
   AOP采取的是横向抽取  即 : 使用动态代理机制增强需要增强的代码
   ```

4. SpringAOP实现原理:动态代理

   1. JDK动态代理 : 只能对实现了接口的类产生代理
   2. Cglib动态代理 ( 类似于Javassist 第三方代理技术 )  对没有实现接口的类产生代理对象,生成子类对象

5. JDK动态代理

   ```
   具体不多做赘述, 最主要未解决问题 , 为什么必须要有接口 才可以实现Proxy, 没有接口 ,则会报$Proxy4的错误
   ```

   

6. Cglib动态代理

   ```
   Cglib:第三方的开源代码生成类库, 动态添加类的属性和方法
   1,配置环境:
   	引入6个基本包 : 基本包里面有cglib的包
   ```

   例子

   ```java
   public class CglibProxyDemo implements MethodInterceptor {
   
       private CustomerDao customerDao;
   
       public CglibProxyDemo(CustomerDao customerDao){
           this.customerDao = customerDao;
       }
   
       public CustomerDao createProxy(){
   //        创建cglib核心类对象
           Enhancer enhancer = new Enhancer();
   //        设置父类
           enhancer.setSuperclass(customerDao.getClass());
   //        设置回调
           enhancer.setCallback(this);
   //        创建代理对象
           CustomerDao proxy = (CustomerDao) enhancer.create();
           return proxy;
   
       }
   
   
       @Override
       public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
           if("save".equals(method.getName())){
               System.out.print(new Date()+" => ");
               return methodProxy.invoke(customerDao,objects);
           }
           return method.invoke(customerDao,objects);
       }
   }
   
   ```

   ​	

7. SpringAOP开发(AspectJ的XML的方式)

   1. aop思想最早是由AOP联盟组织提出的

      1. spring有自己的实现方式, 但是非常繁琐
      2. AspectJ是一个AOP框架, 被Spring引入AspectJ作为自身AOP的开发
      3. Spring有两套AOP开发方式, 传统方式已被弃用, 现在用基于AspectJ

   2. AOP开发相关术语

      1. JoinPoint(连接点)  : 可以被拦截到的点 (特么 那些被增强的点 , 就是被拦截的方法 即连接点 , 有没有增强方法无所谓, 只要允许被增强就ok) 

      2. PointCut : 切入点 , 真正被拦截的点 (  真正被增强的方法 ) 即切入点

      3. Advice : 通知/增强   对增强方法抽取出来, 形成一个方法, 这玩意就是Advice

         方法层面的增强  对比 Introduction

         1. 前置通知: 在执行方法前 增强  
         2. 后置通知
         3. 环绕通知

      4. Introduction: 引介    类层面的增强    即 可以在类上添加方法

      5. Target : 被增强的对象  比如 对UserDao增强  UserDao就是目标

      6. Weaving :  织入  将Advice应用到Target的过程

      7. Proxy : 代理对象  一个类被织入后产生的对象

      8. Aspect : 切面, 多个通知和多个切入点的组合

   3. AOP实际开发入门

      1. 引入包

         1. 基本包6个
         2. AOP开发包
            1. springaop
            2. springn-aspects
            3. aopalliance-1.0.0
            4. aspectj-weaver

      2. 引入配置文件约束

         ```xml
         <?xml version="1.0" encoding="UTF-8"?>
         <beans xmlns="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:aop="http://www.springframework.org/schema/aop" xsi:schemaLocation="
                 http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                 http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd"> 
         
         </beans>
         ```

      3. 编写目标类  并配置

         ```java
         public class UserDao {
         
             public void save(){
                 System.out.println("--------save------");
             }
             public void insert(){
                 System.out.println("--------insert------");
             }
             public void update(){
                 System.out.println("--------update------");
             }
             public void close(){
                 System.out.println("--------close------");
             }
         }
         
         ```

      4. 编写切面类

         ```java
         public class MyAspectJ {
             /**
              * advice 配置增强
              * @Author 丁兆顺
              * @Date 16:18 2019/4/29
              * @param
              * @return void
              **/
             public void enhance(){
                 System.out.print(new Date()+"  =>  当前执行: ");
             }
         }
         ```

         

      5. 配置applicationContext.xml文件

         ``` xml
          <!--这个配置就可以满足,仅扫描属性注解的需要-->
             <context:annotation-config/>
             <!--配置切入点-->
             <bean id="userDao" class="xyz.xlhp.SpringAOPDemo.UserDao"></bean>
             <!--Advice托管-->
             <bean id="myAspectJ" class="xyz.xlhp.SpringAOPDemo.MyAspectJ"></bean>
             <!--通过对AOP的配置完成对目标类的代理-->
             <aop:config>
                 <!--表达式配置哪些类需要增强-->
                 <aop:pointcut id="userDaoProxy"
                               expression="execution(* xyz.xlhp.SpringAOPDemo.UserDao.save(..))"></aop:pointcut>
                 <!--配置切面-->
                 <aop:aspect ref="myAspectJ">
                     <aop:before method="enhance" pointcut-ref="userDaoProxy"></aop:before>
                 </aop:aspect>
         
             </aop:config>
         ```

         

      6. Spring整合JUnit单元测试

         1. 引入SpringTest包 : spring-test-4.2.4.releases.jar

         2. 引入单元测试JUnit依赖包

         3. 测试类Demo

            ```java
            @RunWith(SpringJUnit4ClassRunner.class)
            //使用该注解后,就不需要创建工厂了
            @ContextConfiguration("classpath:applicationContext.xml")
            public class Test1 {
            // 依赖注入    
                @Resource(name="userDao")
                private UserDao userDao;
            
                @Test
                public void test(){
                    userDao.insert();
                    userDao.update();
                    userDao.save();
                    userDao.close();
                }
            }
            ```

   4. Spring中通知类型

      1. 前置通知(前置增强)

         ```xml
         <aop:before method="" pointcut-ref=""></aop:before>
         一般用来获取切入点信息 , 其他位置也可以获取切入点信息
         ```

      2. 后置通知(后置增强)

         ```xml
         <aop:after-returning></aop:after-returning>
         可以获取方法返回值
         ```

      3. 环绕通知(在目标方法执行之前和之后进行增强)

         ```xml
         <aop:round method="" pointcut-ref=""></aop:round>
         可以目标方法的执行
         ```

      4. 异常抛出通知

         ```xml
         <after-throwing method="" pointcut-ref=""></after-throwing>
         在程序异常时进行操作(catch) 可以获取异常信息
         ```

      5. 最终通知

         ```xml
         <after method="" point-ref=""></after>
         无论代码是否异常,  该代码块最终会执行(finally)
         ```

      6. 引介通知

      7. 例子

         ```xml
         <!--配置切入点-->
             <bean id="userDao" class="xyz.xlhp.SpringAOPDemo.UserDao"></bean>
             <!--Advice托管-->
             <bean id="myAspectJ" class="xyz.xlhp.SpringAOPDemo.MyAspectJ"></bean>
             <!--通过对AOP的配置完成对目标类的代理-->
             <aop:config>
                 <!--表达式配置哪些类需要增强-->
                 <aop:pointcut id="userDaoProxy"
                               expression="execution(* xyz.xlhp.SpringAOPDemo.UserDao.save(..))"></aop:pointcut>
                 <aop:pointcut id="insert"
                               expression="execution(* xyz.xlhp.SpringAOPDemo.UserDao.insert(..))"></aop:pointcut>
                 <aop:pointcut id="update" expression="execution(* xyz.xlhp.SpringAOPDemo.UserDao.update(..))"></aop:pointcut>
                 <aop:pointcut id="close" expression="execution(* xyz.xlhp.SpringAOPDemo.UserDao.close())"></aop:pointcut>
                 <!--配置切面-->
                 <!--配置前置通知-->
                 <aop:aspect ref="myAspectJ">
                     <!-- 前置通知 -->
                     <aop:before method="enhance" pointcut-ref="userDaoProxy"></aop:before>
                 </aop:aspect>
                 <!--配置后置通知-->
                 <aop:aspect ref="myAspectJ">
                     <aop:after-returning method="returnEnhance" returning="result" pointcut-ref="insert"></aop:after-returning>
                 </aop:aspect>
                 <!--环绕通知-->
                 <aop:aspect ref="myAspectJ">
                     <aop:around method="aroudAdvice" pointcut-ref="update"></aop:around>
                 </aop:aspect>
                 <!--异常通知-->
                 <aop:aspect ref="myAspectJ">
                     <aop:after-throwing method="throwExceptionAdvice" pointcut-ref="close"></aop:after-throwing>
                 </aop:aspect>
                 <!--最终通知-->
                 <aop:aspect ref="myAspectJ">
                     <aop:after method="finallyAdvice" pointcut-ref="close"></aop:after>
                 </aop:aspect>
         ```

         

   5. Spring的切入点表达式写法

      ```
      [访问修饰符] 方法返回值 包名.类名.方法名(参数)
      [public] void/Object/* com.itheima.spring.CustomerDao.save(..)
      例如: 
      	* *.*.*.*Dao.save()
      	* com.itheima.spring.CustomerDao+.save(..)  // + : 指代本身及其子类
      	
      	
      ```

      

   

   

   

   

   

   

   

   

   

   

   


   ​		




















