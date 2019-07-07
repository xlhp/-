# MAVEN操作命令

1. ### 创建Maven的普通java项目： 

      mvn archetype:create 
         -DgroupId=packageName 
         -DartifactId=projectName  

2. ### 创建Maven的Web项目：   

       mvn archetype:create 
       -DgroupId=packageName    
       -DartifactId=webappName 
       -DarchetypeArtifactId=maven-archetype-webapp    

3. ### 编译源代码： mvn compile 

4. ### 编译测试代码：mvn test-compile    

5. ### 运行测试：mvn test   

6. ### 产生site：mvn site   

7. ### 打包：mvn package   

8. ### 在本地Repository中安装jar：mvn install 

9. ### 清除产生的项目：mvn clean   

10. ### 生成eclipse项目：mvn eclipse:eclipse  

11. ### 生成idea项目：mvn idea:idea  

12. ### 组合使用goal命令，如只打包不测试：mvn -Dtest package   

13. ### 编译测试的内容：mvn test-compile  

14. ### 只打jar包: mvn jar:jar  

15. ### 只测试而不编译，也不测试编译：

    ​	mvn test -skipping compile -skipping test-compile 

          ( -skipping 的灵活运用，当然也可以用于其他组合命令)  

16. ### 清除eclipse的一些系统设置:mvn eclipse:clean

17. ### 清理下载失败的包 : 不好使。。

    ```bat
    set REPOSITORY_PATH=D:\maven\repository
    rem 正在搜索...
    for /f "delims=" %%i in ('dir /b /s "%REPOSITORY_PATH%\*lastUpdated*"') do (
        del /s /q %%i
    )
    rem 搜索完毕
    pause
    ```

    

    ```java
    package xyz.xlhp;
    
    import java.io.File;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;
    
    /**
     * @author 丁兆顺
     * @date 2019/5/10 18:37
     * @desciption
     */
    public class Demo03 {
        private static Pattern pattern = Pattern.compile(".lastUpdated");
    
        public static void main(String[] args) {
    //         获取所要删除的目录
    //        Scanner scanner = new Scanner(System.in);
    //        maven仓库位置
            String cataLog = "D:\\Maven\\apache-maven-3.6.1-bin\\MavenRepository";
            File cataLogObject = new File(cataLog);
            if (!cataLogObject.exists()) {
                System.out.println("未发现目录");
                System.exit(1);
            }
            System.out.println("存在该路径");
            isDirectory(cataLogObject);
        }
    
        /**
         * @param file
         * @return void
         * @Author 丁兆顺
         * @Date 20:04 2019/5/10
         **/
        private static void isDirectory(File file) {
    
            if (file.isDirectory()) {
                File[] ne = file.listFiles();
                for (int i = 0; i < ne.length; i++) {
                    isDirectory(ne[i]);
                }
                File[] nextNe = file.listFiles();
                if (nextNe.length < 1) {
                    file.delete();
                }
            } else {
                Matcher matcher = pattern.matcher(file.getName());
                if (matcher.find()) {
                    System.out.println("这是一个File==>" + file.getName());
                    file.delete();
                }
            }
        }
    
    }
    
    ```

    

    