## SpringAOP的基于AspectJ的注解开发

#### 1. 引入jar包

1. 基础包
2. AOP包
3. 整合Test包

#### 2. 引入配置文件

#### 3. 编写目标类

#### 4. 配置目标类

#### 5. 编写切面类

#### 6. 配置切面类

#### 7. 使用注解对AOP对象进行增强

1. 配置文件中开启注解的AOP的开发

   ```xml
   
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/aop
           http://www.springframework.org/schema/aop/spring-aop.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd
           ">
       <!--开启注释AOP开发-->
       <aop:aspectj-autoproxy ></aop:aspectj-autoproxy>
       <!--orderDao交由Spring处理-->
       <bean id="orderDao" class="xyz.xlhp.aop.annotation.OrderDao" ></bean>
       <!--切面类交由spring管理-->
       <bean id="myAspectJ" class="xyz.xlhp.aop.annotation.MyAspectJAnnotation"></bean>
   
   </beans>
   ```

2. 在切面类上添加注解

   ```java
   
   /**
    * 切面类
    * @author 丁兆顺
    * @date 2019/5/2 19:58
    * @desciption
    */
   @Aspect
   public class MyAspectJAnnotation {
       @Before(value="execution(* xyz.xlhp.aop.annotation.OrderDao.del(..))")
       public void beforeAspectJ(){
           System.out.println("---前置增强---");
       }
   }
   
   ```

3. Spring的注解的AOP的通知类型

   1. @Before 　：　前置通知

   2. @AfterReturning (value= “” , returning = “”)　： 后置通知

   3. @Around ：环绕通知

      ```java
      public Object aroundTest(ProceedingJoinPoint joinPoint){
          ....
      }
      ```

   4. @AfterThrowing : 异常抛出通知

   5. @After  : 最终通知

#### 8. 切入点注解

```java
@Pointcut(value="execution(* xxxx.xxxx.xxxx.xxx(..))")
private void pointcut1(){} // 该方法无意义, 唯一有用的只有methodname
```



## Spring事务(tranaction) ==> 

[接SpringJDBCTemplate.]: D:\31284\笔记\Spring4学习\SpringJDBCTemplate.md





