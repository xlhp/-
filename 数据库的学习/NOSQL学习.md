# NOSQL: 使用REDIS案例学习

#### noSQL

​	概念:not only sql: 用于应对高并发 , 海量读写 , 易扩展

1. 键值对:redis: key_value  易缓存
2. 列式存储:Hbase   易扩展
3. 文档型数据库: mongoDB
4. 图形数据库

#### nosql理论特点: 

1. 扩展性高
2. 大数据量高性能
3. 多样灵活的数据模型

#### redis概念 

​	由C语言实现的直接操作内存的开源的高性能的数据库软件

#### redis基本包含内容

1. kv : 键值对
2. Cache : 高速缓存
3. Persistence  持久化

#### NOsql3V3高

1. 大数据时代的3v : 问题描述
   1. 海量volume
   2. 多样variety
   3. 实时velocity
2. 互联网要求的三高 : 问题处理
   1. 高并发 
   2. 高可扩
   3. 高性能

#### NOsql入门概述

1. ##### nosql的四大类

   1. kv  => redis , memcache
   2. 文档数据库 =>MongoDB , CouchDb
      1. mongodb 基于分布式文件存储的数据库    大型文档
      2. 为web应用提供可扩展的高性能时速局存储解决方案
      3. bson
   3. 列存储数据库 => HBase , Cassandra 
   4. 图关系数据库 => Neo4j , infoGrid  
      1. 使用: 朋友圈社交网络, 广告推荐系统
   5. 对比:

2. ##### 在分布式数据库中CAP原理CAP+BASE

   1. 传统ACID
      1. 原子性
      2. 一致性
      3. 独立性
      4. 持久性

   2. CAP  :  不可能出现三种满足 , 只可能满足两种
      1. consistency  强一致性
         1. 在分布式大数据量下的级别下, 一般不要求强一致性, 因为多数数据只要求大概就行
         2. 一般通过后期
      2. availability    可用性
         1. 常与分区容错性结合
      3. Partition tolerance 分区容错性  必须满足
      4. 三选二
         1. CA
         2. CP
         3. AP

   3. 一般大型网站都是弱一致性&A&P

   4. 数据库写实时性&读实时性

   5. 忌讳多个大表的关联查询

   6. base ： 为了解决关系型数据库强一致性引起的问题而引起的可用性降低而提出的解决方案

      1. 基本可用(basically available)
      2. 软状态(Soft state)
      3. 最终一致(Eventually consistent)
      4. 他的思想是通过让系统放松对某一刻数据一致性的要求,来换取系统整体伸缩性和性能上的改观

   7. 分布式和集群

      1. 分布式

         ```
         有多台计算机和通信的软件组成通过计算机网络连接(本地网络|广域网)组成. 分布式系统是建立在网络之上的软件系统, 正是因为软件的特性, 所以分布式系统具有高度的内聚性和透明性,因此,网络和分布式系统之间的区别更多的在于高层软件(特别是操作系统), 而不是硬件,分布式系统可以应用在不同的平台上如:pc,局域网,广域网等等
         对比集群 : 多台不同的服务器上部署不同的服务模块
         ```

      2. 集群

         ```
         不同的多台服务器上部署相同的服务模块, 通过分布式调度软件进行统一的调度,对外提供服务和访问
         ```

         



#### 阿里巴巴中文网站架构发展历程

1. Perl+CGI+Oracle : 1999

2. java+servlet : 2000

3. EJB : 01-04

4. 去Spring重构 : Spring+ibatis+webx+antx  : 2005-2007

   底层架构:ISearch , MQ+ESB, 数据挖掘,CMS

5. Memcaached集群,mysql+数据切分=Cobar  : 08-09

   分布式存储,Hadoop,kv,CDN

6. 安全,镜像,应用服务器升级 , 秒杀, NOsql ,SSD  : 10年

7. 11年开始第五代网站架构 :  敏捷 , 开放 , 体验5



#### redis应用场景:

1. 缓存:数据查询,短链接, 新闻内容, 商品内容...(最多使用)
2. 聊天室的在线好友列表
3. 任务队列(秒杀, 抢购, 12306)
4. 应用排行榜
5. 网站访问统计



#### redis环境搭建

1. 安装包:源码形式提供, .c文件
2. 需要手动将.c编译为.o文件  -->需要安装gcc 
3. 编译后的文件安装到linux系统上
4. 下载redis压缩包, 
5. 在解压的路径下 make 编译
6. 然后安装 :  make PREFIX=路径  install
7. 复制解压redis文件夹中的 redis.conf 文件 至安装路径
8. 后端启动  vim redis.conf    修改daemonize 为yes
9. 到redis安装目录下 ,启动redis    : ./bin/redis-server   ./redis.conf
10. redis默认端口 6379  开放端口
11. 启动客户端 ,连接已启动redis服务
12. redis安装目录下  ./bin/redis-cli    连接服务

#### redis 五种数据类型

string list hash set sorted-set

功能: 添加 删除,获取,查看多少元素,判断元素是否在集合中,

key的通用操作

jedis:java操作redis

#### redis常识:

1. 一个redis支持16个仓库

#### redis常用命令:

1. keys : 展示当前所有的键
2. 默认在0号仓库
3. 切换仓库  select   0-15
4. move  key  1   :将数据移动 到几号仓库
5. echo  : 打印东西
6. quit  : 退出链接
7. dbsize: 显示当前数据库的key数量
8. info : 获取服务器的信息和统计
9. flushdb：删除当前选择数据库中所有的key
10. flushall : 删除所有数据库中所有的key

