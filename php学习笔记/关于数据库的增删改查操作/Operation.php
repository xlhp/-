<?php
/**
 * Created by PhpStorm.
 * UserController: 丁兆顺
 * Date: 2018/11/29
 * Time: 19:59
 */

namespace app\index\controller;

use think\Controller;
use \app\index\Model\User;

class Operation extends Controller
{
    public function select()
    {
        $user = new User();
        $data = $user->query("select * from user");
        $data = $user -> query ("select * from user where pass = ?",[123]);
        //获取最近一次执行的sql语句
        dump($user->getLastSql());
//        $data1 = $user -> table("user");
//        $data2 = $data1->select();
        dump($data);
        echo "<hr />";
//        dump($data2);
    }
    public function insert(){
        $user = new User();
        //返回影响该表行数
        $result = $user->execute("insert into user values (null,'韦肖肖','321')");
        //? 占位符
        $result = $user -> execute("insert into user values (null,?,?)",["王冰","1234"]);
        //没啥区别 也是占位符
        $result = $user -> execute("insert into user values (null,:name,:pass)",["name"=>"王江","pass"=>"12345"]);
        dump($result);
    }
    //删除操作
    public function del(){
        $user = new User();
        //类似于上面
        $result = $user->execute("delete from user where id>?",[10]);
        $rusult = $user ->execute("delete from user where id>:id",[10]);
        dump($result);
    }
}