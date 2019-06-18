## SpringMVC增强

[TOC]



### 高级参数绑定

#### 绑定Array类型

```java
 @RequestMapping(value = "/del.action")
    public void delItem(Integer[] ids) {
        System.err.println("进入del.action控制器");
        for (int i : ids) {
            out.println(i);
        }
    }
//当然可以封装到一个对象中的属性中, 类似于集合的使用
```



#### 绑定List类型

```jsp
<!--jsp页面-->
<form action="${pageContext.request.contextPath}/delList.action" method="post">
        商品列表：
        <table width="100%" border=1>

            <tr>
                <td><input type="checkbox" value=""/></td>
                <td>商品名称</td>
                <td>商品价格</td>
                <td>生产日期</td>
                <td>商品描述</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${itemList }" var="item" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="" value="${item.id}"/></td>
                    <td><input type="text" name="list[${s.index}].name" value="${item.name }"/></td>
                    <td><input type="text" name="list[${s.index}].price" value="${item.price }"/></td>
                    <td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${item.detail }</td>
                    <td><a href="${pageContext.request.contextPath }/toEdit.action?id=${item.id}">修改</a></td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" value="删除" />
    </form>
```



```java
@RequestMapping(value = "delList.action")
    public void delItemList(QueryVo vo) {
        System.err.println("开始处理请求");
        for (Items items : vo.getList()) {
            out.println(items.getName() + " => " + items.getPrice());
        }
    }
```



### @RequestMapping注解使用

1. @RequestMapping(“value”) :省略value = 
2. @RequestMapping(value=“xxx” , method={RequestMethod.POST,RequestMethod.GET}) : 设置接收方法
3. 窄化路径: 限制子路径 , 将@RequestMapping加到类上
4. @RequestMapping(value={“xxx”,“xxxxx”,“xxxx.action”}) : 设置多个value值 映射多个路径

### Controller方法返回值

1. ModelAndView : 无敌的, 既可以携带数据, 也可以携带视图路径  : 不建议使用

2. String : 返回视图路径, 一般配合string 将在形参上添加Model/ModelMap 用来保存数据  , 官方推荐使用String

   为什么: 解耦 : 将时速局和视图分开

   ```java
   //设置重定向到xxx.action
   return "redirect:xxx.action";
   //请求转发到xxx.action
   return "forward:xxx.action";
   ```

3.  void : 配合ajax请求 : 异步请求时使用

### SpringMVC中异常处理

#### 异常处理思路

```
包含两类 : 预期异常, 和 运行时异常
将内部错误都抛给DispatcherServlet  然后由DispatcherServlet交由HandlerExcpetionResolver处理
所有的自定义异常处理类 都需要实现HandlerExceptionResolver接口 , 保证 配置进Springmvc后 可以被找到,并使用
```

``` java
public class NotNullExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mav  = new ModelAndView();
        mav.setViewName("error");
        System.err.println(e instanceof NotNullException);
        if(e instanceof NotNullException){
            NotNullException exception = (NotNullException) e;
            System.err.println(exception.getMsg());
            mav.addObject("error",exception.getMsg());
        }else{
            mav.addObject("error","未知异常");
        }
        return mav;
    }
}
```

```xml
<!-- springmvc.xml配置错误 -->
<bean class="xxxx.xxxx.xxx.xxx.className"></bean>
```

### 图片上传处理

```
注意:
1,上传格式一定为:enctype="multipart/form-data"
2,给tomcat添加虚拟目录, 可以在server.xml中配置, 也可以在idea/eclipse中配置
3,
```



```java
 @RequestMapping(value = "updateitem.action")
    public String updateitem(Items items, MultipartFile pictureFile) throws IOException {
        ModelAndView mav = new ModelAndView();
//        使用UUID生成一个随机字符串
        String name = UUID.randomUUID().toString().replace("-", "");
        // 从文件名中截取文件类型
        String extension = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
        // 将随机生成的文件名与文件类型拼接成新的文件名
        name = name + "." + extension;
        // 将文件存储到指定目录下
        pictureFile.transferTo(new File("E:\\workSpace_IDEA\\programFile\\" + name));
        // 将拼接成的文件名 赋值给实例
        items.setPic(name);
        System.err.println(items);
        // 更新数据库信息
        itemService.updateItemsInfo(items);
        // 重定向至某个文件
        return "redirect:toEdit.action?id=" + items.getId();
    }
```



### Json数据交互	

```java
 @RequestMapping(value = "json.action")
// @ResponseBody : 用来将对象封装成一个json字符串, 并返回
// @ReqesutBody : 用于将json字符串封装到对象中
public @ResponseBody
    Items json(@RequestBody Items items) {
        System.err.println(items);
        return items;
    }
```

```javascript
<script type="text/javascript">
        $(function () {
    		   // 模拟请求数据
                var param = '{"id": 1, "name": "测试商品","price": 99.9,"detail": "测试商品描述","pic": "123456.jpg"}';
    			// 发送json请求
                    $.ajax({
                        // 设置请求路径
                        url: "${pageContext.request.contextPath}/json2.action",
                        // 设置发送数据
                        data: param,
                        // 设置发送数据类型
                        contentType:"application/json;charset=UTF-8",
                        // 设置请求方式
                        type:"post",
                        // 设置返回值类型
                        dataType:"json",
                        // 请求成功返回值接受方法
                        success : function (data) {
                            alert(data.name);
                        }
                    });
            }
        );
</script>
```



### SpringMVC实现Restful

```java
 @RequestMapping(value = "toEdit/{id}")
// @PathVariable 用来表示 url上有参数 , 通过这个映射 可以获取参数
// /SpringMVCDemo02_war_exploded/toEdit/3
    public ModelAndView toEdit1(@PathVariable Integer id, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        Items items = itemService.selectItemsById(id);
        mav.addObject("item", items);
        mav.setViewName("editItem");
        return mav;
    }
```



### 拦截器

```xml

    <!--配置springmvc拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"></mvc:mapping>
            <bean class="xyz.xlhp.SpringMVC_Mybatis.interceptor.InterceptorDemo01"></bean>
        </mvc:interceptor>
        <mvc:interceptor>
            <mvc:mapping path="/**"></mvc:mapping>
            <bean class="xyz.xlhp.SpringMVC_Mybatis.interceptor.InterceptorDemo02"></bean>
        </mvc:interceptor>
    </mvc:interceptors>
```

```java
public class InterceptorDemo01 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("拦截器1=>执行前");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器1=>执行后");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("拦截器1=>页面渲染后");
    }
}

```

#### 注意

```
preHandler : 执行方法前 
postHandler : 执行方法后
afterCompletion : 页面渲染后

如果有多个拦截器
prehandler : 按照顺序执行 , 如果 有false 则 不继续向下执行
如果preHandler 执行了 但是是false 则 只执行 afterCompletion  postHandler 不执行
```

