<?php
/**
 * Created by PhpStorm.
 * UserController: 丁兆顺
 * Date: 2018/12/1
 * Time: 13:56
 */
namespace app\index\controller;
use think\Controller;
use think\Db;
class ModifyTable extends Controller{
    public function index(){

    }
    //插入操作
    public function insert(){
        $data = ["name"=>"张萨安","pass"=>"123456"];
        $result = Db::table("user")->insert($data);
        //返回影响行数
        dump($result);
    }
    //多条数据插入
    public function insertMore(){
        $data = [
            ["name"=>"Monster","pass"=>"123"],
            ["name"=>"ultaner","pass"=>"321"],
        ];
        $result = Db::table("user")->insertAll($data);
        dump(Db::table("user")->getLastSql());
        dump($result);
    }
    //helper.php提供的方法执行插入操作
    public function helpFunInsert(){
        $data = [
            ["name"=>"丁兆顺","pass"=>"445"],
            ["name"=>"张富强","pass"=>"213"],
        ];
        $result = db("user")->insertAll($data);
        dump(db("user")->getLastSql());
        dump(db("user")->getLastInsID());
        dump($result);
    }

    /**
        更新数据
     */

    public function updataInfo(){
        //UPDATE `user`  SET `pass`='23333'  WHERE  (  id=1 )
        $data = Db::table("user")->where("id=1")->update(["pass"=>"23333"]);
        dump(Db::table("user")->getLastSql());
        dump($data);
    }
    //自增与自减操作
    public function incAndDec(){
        /*
         * 自增inc  指定某个元素进行自增自减操作
         * 自减dec  指定某个元素进行自减操作  step指定步数
         * */
        $data = Db::table("user")->where("id=1")->setInc("age",3);
        //通过helper.php提供的方法实现自增/自减操作
        \db("user")->setInc("age",3);
    }
    /*
     * 删除数据
     * 助手函数参考其他
     * */
    public function del(){
        $data = Db::table("user")->where("id=28")->delete();
        dump(Db::table("user")->getLastSql());
        dump($data);
        //删除多条数据
        $data = Db::table("user")->where("id in(21,22,23,24)")->delete();
        dump(Db::table("user")->getLastSql());
        dump($data);
    }


}














