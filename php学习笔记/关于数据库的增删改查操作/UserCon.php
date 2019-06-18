<?php
/**
 * Created by PhpStorm.
 * User: 丁兆顺
 * Date: 2018/12/1
 * Time: 17:40
 */
namespace app\index\controller;
use think\Controller;
use app\index\model\Demo;
use think\Loader;
class UserCon extends Controller{
    public function index(){
        $demo = new Demo();
        dump($demo::get(1)->toArray());
        //调用静态方法
        $dat  = Demo::get(1)->toArray();
        //实例化模型
        $demo = new Demo();
        $dat2 = $demo::get(1)->toArray();
        //load类进行加载
        $demo1 = Loader::model("Demo");
        $dat3 = $demo1::get(1)->toArray();
        dump($dat3);
        //使用helper.php
        $demo2 = model("Demo");
        dump($demo2::get(1)->toArray());
    }
    //获取单条数据
    public function single(){
        $demo = new Demo();
        dump($demo::get(["name"=>"万源"])->toArray());
        dump($demo->getLastSql());

        echo "<hr />";
        //使用闭包函数
        dump(
            $demo::get(function ($query){
                $query->where("id=7");
            })->toArray()
        );
        echo "<hr />";
        //find方法
        dump($demo->where("id=8")->find()->toArray());
    }
    //查询多条数据
    public function selMore(){
        //all
        $demo = new Demo();
       $res = $demo::all();
       $res = $demo::all([1,2,3]);
       $res = $demo::all(["name"=>"丁兆顺"]);
       $res = $demo::all(
           function($query){
               $query->where(["name"=>"丁兆顺"])
                   ->whereOr(["pass"=>"123"]);
           }
       );
        foreach($res as $key => $value){
            dump($value->toArray());
        }
    }
    //获取值
    public function getValue(){
        //获取某个值
        $res = Demo::where("id",2)->value("name");
        dump($res);

        //获取某列值  获取了一个关联数组 第二个参数指定那个是key
        $res = Demo::column("name","id");
        dump($res);
    }
    //动态查询  可以查询到一条数据
    //感觉没啥用
    public function dynamic(){
        $res = Demo::getByPass("123");
        dump($res->toArray());
    }

    //增加数据 通过save方法添加或者是create方法
    public function addinfo(){
        $demo = new Demo();
//        $demo->name="丁兆顺";
//        $demo->pass = "456543";
        $data = [
            "id"=>"45",
            "name"=>"张富强",
            "pass"=>"ddddd",
        ];
        //除了传递数组 也可以使用data方法传值
        $demo->data(
            [
                "name"=>"张富强",
                "pass"=>"qer"
            ]
        );
//        dump($demo->save($data));
        dump($demo->save());
        //实例化模型时  传值
        $demo1 = new Demo(
            [
                "name"=>"张富强",
                "pass"=>"afdafdsaf",
                "sex"=>"1"
            ]
        );
//        dump($demo1->save());
        //屏蔽不存在的字段 将存在的字段插入进数据库中  该方法也可以用来指定字段  指定直插入某几个字段
        $demo1->allowField(true)->save();
        dump($demo1->getLastInsID());
    }
    //增加多条数据
    public function addMoreInfo(){
        $demo = new Demo();
        $list = [
            ["name"=>"丁兆顺","pass"=>"543"],
            ["name"=>"dingzhaoshun","pass"=>"54433"],
        ];
        $result = $demo->saveAll($list);
        dump($result);
    }

}