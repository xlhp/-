## SpringJDBCTemplate学习

### 1, 搭建环境 : 引入包

1. 引入基础包6个

2. 数据库驱动

3. SpringJDBCtemplate支持包

   ```java
   spring-jdbc
   spring-tx
   ```

4. 单元测试包

### 2, 入门开发

```java
 public void test1(){
//        创建连接池对象实例
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3360/mydb2");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        // 创建jdbcTemplate模板类实例
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        String sql = "insert into user(username,password) values(?,?)";
//        执行插入语句
        int result = jdbcTemplate.update(sql,"丁兆顺","123");
        System.out.println(result);
    }
```

### 3, 其他连接池配置

#### 1, DBCP

1. 引入包

   ```
   dbcp
   pool
   ```

#### C3P0

1. 引入包

2. 与其他没什么区别

#### 引入外部属性文件

1. 第一种方法

   ```xml
    <bean id="properties1" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
           <property name="location" value="classpath:jdbc.properties"></property>
       </bean>
   ```

   

2. 第二种方法   

   ```xml
    <context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
   ```

      

#### CRUD操作

1. CUD操作和DButils没什么区别

2. R操作

   ```java
      jdbcTemplate.queryForObject(slq,Object,args);
      // 封装到一个对象中
      jdbcTemplate.queryForObject(sql,RowMapper(interface),args)
      class MyRowMapper implements RowMapper<T>{
          public T mapRow(ResultSet res , int args){
              
          }
      }
   	// 查询多个对象
       jdbcTemplate.query(sql,RowMapper,args);
   
   
   // 例子
   @RunWith(SpringJUnit4ClassRunner.class)
   @ContextConfiguration("classpath:applicationContext.xml")
   public class RTemplate {
       @Resource(name="jdbcTemplate3")
       private JdbcTemplate jdbcTemplate;
       @Test
       public void rOperation(){
           String sql = "select * from user where id = ?";
           User user = (User) jdbcTemplate.queryForObject(sql, new ReadInfoUtils(),1);
           System.out.println(user);
       }
   
   //    插叙多个对象
       @Test
       public void readAll(){
           String sql = "select * from user";
           List<User> list = jdbcTemplate.query(sql , new ReadInfoUtils());
           System.out.println(list);
       }
   }
   
   // 查询类
   public class ReadInfoUtils implements RowMapper {
   
       @Override
       public Object mapRow(ResultSet resultSet, int i) throws SQLException {
           User user = new User();
           user.setId(resultSet.getInt(1));
           user.setUsername(resultSet.getString(2));
           user.setPassword(resultSet.getString(3));
           return user;
       }
   }
   
   ```

3. 注意:RowMapper & RowCallbackHandler

   ```
   从网上查到的资料:
   1, JDBC查询返回一个结果集对象时, jdbc并不会一次性将所有匹配的数据都加载到JVM中, 而是只返回一批次的数据(由JDBC驱动程序决定), 当通过ResultSet#next()移动游标时滚动结果超过数据范围时,jdbc再获取一批数据,这样一种"批量化+串行化"的处理方式,避免大结果集处理时JVM内存的过大的开销
   2, RowMapper : 虽然你获取数据过程是串行化的, 但是数据最终会汇集成一个List<T> 一样会占用大量JVM内存 导致OutOfMemoryException异常
   3, 当RowMapper无法处理问题时, 就需要使用RowCountCallbackHandler接口
   ```

   

   

   

   

   ## Transaction

   ### PlatformTransactionManager(interface) : 平台事务管理器

   DataSourceTransactionManager : 底层使用JDBC管理事务

   HibernateTransactionManager : 底层使用Hibernate管理事务

   ### TranactionDefinition : 事务定义信息

   ```
   用于定义事务的相关信息, 隔离级别,超时信息,传播行为,是否只读
   ```

   ### TransactionStatus:事务的状态

   ```
   用于记录事务管理过程中,事务的状态的对象
   ```

   ### 事务管理的API的关系

   ```
   spring进行事务管理的时候, 首先平台事务管理器根据事务定义信息进行事务管理
   在事务管理过程中,产生各种状态,将这些状态的信息记录到事务状态的对象中
   ```

   ### 事务的传播行为(七种)

   #### 用途

   ```
   事务的传播行为是为了解决业务层方法相互调用的的问题
   ```

   #### 分类:

   1. 保证多个操作在同一个事务中

      ```
      1,propagation required : 默认值
      	如果a中有事务,b调用a时, 就会使用a中的事务, 将b中其他操作包含起来
      	如果a中没有事务, 则b创建一个事务,将所有操作包含起来
      2,propagation supports : 支持事务
      	如果a中有事务,就是用a中的事务, 如果a没有事务, 则不使用事务 
      3,propagation mandatory : 包含必须支持事务
      	如果a中有事务, 则使用a中的事务 , 如果a中没事务, b操作调用a操作,就		会报错
      ```

   2. 保证多个操作不再同一个事务中

      ```
      1,propagation requires new : 如果a中有事务, 将 a的事务挂起, 创建一个	新的事务,只包含自身操作; 如果a中没有事务, 创建一个新的事务,包含自身	 操作
      2,propagation not supported
      	:如果a中有事务,将a的事务挂起,如果没有事务, 则不使用事务管理
      3,propagation never : 如果a中有事务, 就抛异常
      ```

   3. 嵌套式事务

      ```
      propagation nested : 嵌套事务
      	如果a中有事务, 则按照a中的事务执行,执行完成,设置保存点,执行b的操作,	  如果没有异常,执行通过,如果有异常, 可以选择回滚到最初始的位置,也可以	  回滚到保存点
      ```

      

   ### Spring事务管理

   #### 创建Service接口与实现类

   #### 创建DAO的接口和实现类

   #### 配置DAO及service

   #### 配置连接池&jdbc模板

   #### 配置平台事务管理器

   #### 具体事务操作

   ##### 1,编程式事务管理

   ```xml
   xml文件配置, 不包含上面配置各种实现类和模板
   <!--配置事务管理的模板类-->
       <bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
           <!--平台事务管理器交由模板类处理-->
           <property name="transactionManager" ref="transactionManager"></property>
       </bean>
   ```

   ```java
   // java事务实现类 
   @Override
       public void transfer(int fromId, int aimId, double money) {
           transactionTemplate.execute(new TransactionCallbackWithoutResult() {
               @Override
               protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                   dao.delMoney(money,fromId);
                   int i = 1/0;
                   dao.addMoney(money,aimId);
               }
           });
       }	
   ```

   ##### 2, 配置式事务管理

   1. xml方式配置

      ```xml
      <!--配置事务增强 打算怎么增强-->
          <tx:advice id="txTransfer">
              <tx:attributes>
                  <tx:method name="update*" propagation="REQUIRED" isolation="DEFAULT"></tx:method>
                  <tx:method name="find*" read-only="true"></tx:method>
                  <tx:method name="save*" propagation="REQUIRED"></tx:method>
                  <tx:method name="transfer" propagation="REQUIRED"></tx:method>
              </tx:attributes>
          </tx:advice>
      
          <!--
              将事务增强配置到AOP中
              1,创建aop
              2,配置
          -->
          <aop:config>
              <!--创建切入点-->
              <aop:pointcut id="transfer" expression=
                          "execution(* xyz.xlhp.TransactionDemo01.service.Impl.TxServiceImpl.transfer(..))"></aop:pointcut>
              <!--将事务增强加入到切入点中-->
              <aop:advisor advice-ref="txTransfer" pointcut-ref="transfer"></aop:advisor>
          </aop:config>
      ```

   2. 注解方式配置

      ``` xml
       <!--开启事务注释管理-->
          <tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>
      ```

      开启事务注解管理后, 只需要在事务类上 使用@Transactional  注解就一切ok了 , 不需要对源码进行任何修改

   

   ##### 	

   

   

   

   

   

   

   

   
