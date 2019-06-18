1. ## redis学习


### redis应用场景:

1. 缓存:数据查询,短链接, 新闻内容, 商品内容...(最多使用)
2. 聊天室的在线好友列表
3. 任务队列(秒杀, 抢购, 12306)
4. 应用排行榜
5. 网站访问统计



redis环境搭建

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

### redis 五种数据类型

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



### 杂项配置

1. 后台运行redis

   ```
   redis.conf : daemonize  yes : 可以后台运行
   ```

2. redis-cli -p  port ：链接数据库

   



### string

### list

1. lpush/rpush/lrange ：lpush / rpush 就相当于将值压栈, 压栈顺序不同, lrange 就是出栈规则, 一直都是先进后出

   1. lpush:  从左侧开始插入 

      ```
      lpush list01 1 2 3 4 5 
      使用lrange查询时 : lpush : 正着进 , 反着出
      ```

      

   2. rpush: 从右侧开始插入 

      ```
      rpush list01 1 2 3 4 5 
      使用lrange 时, 反着进,正着出
      ```

      

   3. lrange : 查询list

      ```redis
      lrange list01 0 -1 : 查询所有
      ```

2. lpop/rpop : 每次出一个 , 出栈顶/底部   相当于remove 删除并返回值

   ```
   lpop list01 
   ```

   

3. lindex : 按照索引查询链表内容  由上到下

   ```
   lindex list01 2
   ```

4. llen 获取长度

5. lrem key 删除n个值

   ```redis
   lrem list 几个 什么值
   lpush list01 1 2 3 3 3 4 4 4
   lrem list01 2 3 
   最后结果: 去掉两个3 即: list01 : 1 2 3 4 4 4
   ```

6. ltrim key 开始index 结束index , 截取指定范围的值后在赋值给key

   ```redis
   lpush list01 1 2 3 4 5 
   ltrim list01 2 4
   lrange list01 
   3 4 5
   ```

7. rpoplpush  源列表 目的列表

   ```
   rpoplpush list01 list02 
   将list01的底部 交给lsit02的头
   ```

   



### SET

#### 操作

1. sadd  : 添加
2. smembers : 遍历一个集合
3. sismember : 查询一个集合中是否有一个元素
4. scard : 获取集合中的元素个数
5. srem key value : 删除集合中的元素
6. srandmember key : 随机出数字
7. spop key 随机出栈 : 数字会删除
8. smove key1 key2 : 在key1里某个值, 迁移到key2中
9. 数学集合类
   1. 差集 : sdiff
   2. 交集 : sinter
   3. 并集 : sunion



### Hash

#### 概念：KV模式不变，但V是一个键值对

#### 操作

1. hset

2. hget

3. hmset

4. hmget

5. hgetall

6. hdel

7. hlen 

8. hexists key 

   ```
   检查该key里面是否有相对相应的内容键
   ```

   

9. hkeys/hvals key

   ```
   查看所有的key or 查看所有的value
   ```

   

10. hincrby/hincrbyfloat

    ```
    加上值
    ```

    

11. hsetnx



### Zset(sorted set)

#### 概念：

#### 操作：

1. #### zadd/zrange

2. zrangebyscore key 开始score 结束score

3. zrem key 某score下对应的value至，作用是删除元素

4. zcard/zcount key score区间/zrank key values至，作用是获取下标值/zscore key对应值，获取分数

5. zrevrank key values值，作用是逆序获得下标

6. zrevrange 逆序获取score值

7. zrevrangebyscore key



### 解析配置文件

1. include ： 设置多个config文件

2. general ： 通用配置

   1. daemonize ： 设置是否在后台运行

   2. port ： 端口

   3. pidfile ：运行管道路径

   4. tcp-backlog : backlog 是一个连接队列

      ```
      在高并发环境下，你需要一个高backlog值来避免慢客户端连接问题
      ```

   5. bind : 端口绑定

   6. timeout ： 无操作多长时间断开连接

   7. tcp-keepalive ： 设置通讯验证时间

   8. loglevel ： 设置日志级别（共四个）

   9. logfile ： 日志文件名字

   10. syslog-enabled

   11. syslog-ident

   12. syslog-facility

   13. databases

3. 





   

   

   

   