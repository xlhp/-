<?php
/**
 * Created by PhpStorm.
 * UserController: 丁兆顺
 * Date: 2018/11/29
 * Time: 18:48
 */
namespace app\index\controller;
use app\index\model\User;
use app\index\model\Usernext;
use think\Db;
class Conn{
    public function index(){
        $db = new Db();
        $data = $db::table("UserController");
        dump($data);
        $data = $data->select();
        dump($data);
    }
    //使用sql语句
    public function sqlSelect(){
        $data = Db::query("select * from user ");
        dump($data);
    }
    //使用方法配置数据库链接


    /*
     * 使用方法链接数据库 需要调用Db::connect 方法创建一个Connection 对象
     * 然后调用Connection类的方法
     * thinkphp/think/db/Connection.php
     *
     */
    public function methodConn(){
        $conn = Db::connect(
            [
                "type" => "mysql",
                "hostname" => "127.0.0.1",
                "database" => "tpStudy",
                "username" => "root",
                "password" => "root",
                "hostport" => "3306"
            ]
        );
        //数据库类型://用户名:密码@数据库地址:数据库端口/数据库名#字符集
        $conn = Db::connect("mysql://root:root@127.0.0.1:3306/tpStudy#utf8");
//        dump($conn);
        dump($conn->query("select * from user"));

        dump($conn->table("user")->select());
    }

    //模型链接数据库
    public function modelConn(){
        $user = new User();
        dump($user);
        dump($user->table("user")->select());
        dump($user->all());
//        $user = new Usernext();
//        dump($db = $user->getConnection());
//        $data = $db ->table("user")->select();
//        dump($data);
    }

}