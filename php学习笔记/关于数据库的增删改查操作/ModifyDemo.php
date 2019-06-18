<?php
/**
 * Created by PhpStorm.
 * User: 丁兆顺
 * Date: 2018/12/1
 * Time: 23:35
 */
namespace app\index\controller;
use think\Controller;
use app\index\model\Demo;
class ModifyDemo extends Controller{
    public function index(){
        //删除操作
        $res = null;
        $demo = new Demo();
        /*
         * 创建一个查询集对象  通过get
         * 然后通过delete方法删除查出的信息
         *
         * */
//        dump($res = $demo::get(45));
//        dump($res->delete());
        $res = $demo::destroy(18);
                //$demo::destroy("2,3,4")
                //$demo::destroy([1,2,3]);

        //删除信息匹配
        $res = $demo::destroy(["name"=>"张富强","pass"=>"213"]);
        //当然也支持闭包操作
        $res = $demo::destroy(function($query){
            $query->where("name","丁兆顺")
                ->whereOr("pass","123");
        });
        //也支持通过where判断后调用delete方法进行删除
        $res = $demo->where(["name"=>"张富强"])->delete();
        dump($res);
        dump($res);
    }

    //更新操作
    public function modify(){
        //设置字段更新数据
        $demo = new Demo();
        //使用get获取首先要先获取
        $res = $demo::get(4);
        $res->name="dingzhaoshun";
        $res->save();
        //可以通过数组
        $demo->save(
            ["name"=>"丁兆顺","pass"=>"123"],
            ["id"=>"4"]
        );
        //更新多条数据
        $res = $demo->where("id>5")->update(["name"=>"丁兆顺","pass"=>"321"]);
        $res = $demo::where("id>4")->update(["name"=>"丁兆顺","pass"=>"123"]);
        //闭包跟新
        $demo->save(["name"=>"dingzhaoshun","pass"=>"123"],function ($query){
            $query->where("id>4");
        });
        dump($res);
    }
}