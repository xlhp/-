# Zookeeper

## 安装

1. 环境

   Linux：Centos6.5+

   jdk ：1.7+

2. 下载zookeeper ：<https://zookeeper.apache.org/>

3. 将安装包放到/usr/local/下面

   ```
   mv zookeeper-3.4.6.tar.gz /usr/local/
   ```

   

4. 解压

   ```
   tar -zxvf zookeeper3.4.6.tar.gz
   ```

5. 进入解压路径,创建一个data文件

6. 进入conf文件路径，将zoo_simple.cfg 复制一份交zoo.cfg

   ```
   cp zoo_simple.cfg zoo.cfg
   ```

7. 修改zoo.cfg 

   ```
   vim zoo.cfg
   dataDir = /usr/local/zookeeper3.4.6/data(即刚才新建的文件夹)
   ```

8. 进入zookeeper的bin路径启动zookeeper

   ```
   ./zkServer.sh start
   ```

9. 查看状态 

   ```
   ./zkServer.sh status
   ```

## Zookeeper注意事项

1. zookeeper使用的端口是2181， 所以需要打开端口 当然也可以直接关闭防火墙，当然我觉得还是开放端口稍微好点， 不然服务器很容易出现安全问题

   ```
   #查询端口是否开放
   firewall-cmd --query-port=2181/tcp
   firewall-cmd --zone=public --query-port=2181/tcp
   #开放端口
   firewall-cmd --zone=public --add-port=2181/tcp --permanent
   #重新载入firewall设置
   firewall-cmd --reload
   #查看是否开放成功
   firewall-cmd --zone=public --query-port=2181/tcp
   ```

   

