## Maven学习  :  <https://mvnrepository.com/>



[TOC]



### 入门 

1. 作用: 
   1. 管理jar包
      1. 增加第三方jar包
      2. 管理jar包之间的依赖关系, 保证不会冲突
   2. 可以将一个项目拆成若该模块
2. 概念: 基于java平台的, 自动化构建工具
   1. 历史: make - maven - gradle
3. 可以做的
   1. 清理: 删除编译结果
   2. 编译 : java=>class
   3. 测试 : 测试与开发
   4. 报告 : 测试结果进行显示
   5. 打包 : 将多个文件, 压缩成一个文件  , 用于安装和部署
   6. 安装 : 将包放到本地仓库  供其他项目使用
   7. 部署 : 发送到服务器
4. 本地仓库
5. 远程仓库(中央仓库)
6. 中央仓库镜像

### 配置

1. 下载MAVEN

2. 配置JAVA_HOME

3. 配置MAVEN_HOME/M2_HOME

4. 配置path

5. 验证

   1. mvn -v

6. 配置本地仓库  conf/settings.xml : 文件

   ```
   ${user.home}/.m2/repository  : 默认本地仓库位置
   
   配置:
   <localRepository></localRepository>
   ```

### 使用Maven

#### 1,约定 优于  配置

```
1,硬编码方式
2,配置方式
3,使用默认值(约定)
```

#### maven的目录结构

```
项目:
	-src
		--main
			---java : 源代码
			---resource : 资源文件
		--test
			---java : 源代码
			---source : 资源文件
	-pom.xml : 项目对象模型
```

 

```
如果是web应用， 目录结构如下
program : 
		--src 
			--main
				--webapp
					--WEB-INF
						--web.xml
					--index.jsp
		--pom.xml
```

pom.xml 

```
<groupId>域名翻转.大项目名</groupId>
<artifactId>子模块名</artifactId>
<version>版本号</version>

<name>等同于artifactid</name>
```



注: 在maven项目中, 如果需要使用一个当前未引入的jar时,  需要去中央仓库或者本地仓库寻找

#### Maven 常用命令

```
1, mvn compile  :  编译
2, mvn  test    :  测试
3, mvn package  : 打包
4, mvn install : 将开发模块放入本地仓库, 供其他项目使用
5, mvn clean  : 清理target目录=>删除编译后文件
```

### Maven依赖/依赖有效性

#### Maven私服

```
公司使用jar仓库
中央仓库 <= 私服 <= 本地仓库
通常用nexus进行搭建私服
```

#### 依赖的范围

|          | 编译 | 测试 | 运行 |
| -------- | ---- | ---- | ---- |
| compile  | Y    | Y    | Y    |
| test     | N    | Y    | N    |
| provided | Y    | Y    | N    |

#### 依赖排除

```
当a.jar引入时, 若a依赖于b, 则自动引入b.jar  : 但是有时候不需要引入b.jar  : 这就是依赖排除
```

解决: 排除依赖关系

```
dependency
	>exclusions
		>exclusions
            >groupId
            >artifactId
```

#### 依赖的传递性

```
条件: 要使a.jar->c.jar  ,当且仅当b.jar依赖c.jar的范围时compile
```

#### 多个Maven项目如何依赖

1. 先将依赖项目放入仓库中
2. 在pom.xml中配置依赖

### Maven : idea 中创建Maven项目

1. 创建Maven基本项目
2. 配置本地Maven仓库
3. 更新本地服务

### Maven的生命周期

1. 生命周期
   1. resources
   2. compile
   3. test
   4. package
2. 生命周期与构建的关系
3. 生命周期包含的三个阶段
   1. clean liffecycle : 清理
      1. pre-clean  clean post-clean
   2. default lifecycle : 默认  =>常见的
      1. 命令巨多
   3. site lifecycle : 站点
      1. pre-site 
      2. site 
      3. post-site 
      4. siste-deploy



 