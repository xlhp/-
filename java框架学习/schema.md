### schema作用

```java
sping启动时需要验证xml文档, 约束的作用,就是验证配置文件的xml文档语法的正确性
schema 约束定义了文档的结构, 内容和语法,元素和属性
1,xml配置文件中, 所有的标签和属性东都需要schema来定义
2,所有的schema都需要一个id, 在xml配置文件中, 我们称之为namespace , 其值为一个	url,通常是这个xml的xsd文件的地址
3,引入一个schema约束使用xmlns, 属性值为对应schema文件的命名空间
4,若要引入的schema非w3c组织定义的, 必须指定schema文件的位置, schema文件的位置由	schemaLocation指定.
5,引入多个schema需要使用别名: xmlns:alias
```

### xmlns:

```
xmlns:XMLNamespaces缩写,XML(标准通用标记语言)命名空间
```

### schemaLocation属性

```scheme
用来指定名称空间和模式位置  
xmlSchema推荐标准中指出, xsi:schemaLocation属性可以在实例中的任何元素上使用, 而不一定是根元素, 不过xsi:schemaLocation必须出现在他要验证的任何元素和属性之前
```

### SpringSchemaDemo

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    <!-- 基础IOC约束 -->
    xmlns:context="http://www.springframework.org/schema/context"
	<!-- 开启注解管理Bean约束 -->
    xmlns:aop="http://www.springframework.org/schema/aop"
	<!--AOP注解约束-->
    xmlns:tx="http://www.springframework.org/schema/tx"
	<!-- 事务约束 -->
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd
	<!-- 基础IOC约束 -->
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd
	<!-- 注解管理Bean约束 -->
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd
	<!-- AOP注解约束 -->
    http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx.xsd">
	<!-- 事务约束 -->
</beans>

```



### mybatis配置文件书写

``` xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
```

mapper文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
```

