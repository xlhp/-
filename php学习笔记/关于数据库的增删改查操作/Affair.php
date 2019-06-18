<?php
/**
 * Created by PhpStorm.
 * UserController: 丁兆顺
 * Date: 2018/12/1
 * Time: 15:10
 */
namespace app\index\controller;
use think\Db;
use think\Exception;

class Affair{
    public function index(){
        //自动控制事务
        Db::transaction(
            function(){
                $data = Db::table("user")->where("id=1")->update(["pass"=>"123456778"]);
                if($data>1){
                    echo "修改成功";
                }else{
                    return false;
                }
            }
        );
    }
    //手动控制事务
    /*
     * 事务就是 当存在事务中存在问题  就会将事务进行处理并回滚,避免造成错误
     * */

    public function hand(){
        //开启事务
        db::startTrans();
        //事务
        try{
            //删除
            Db::table("user")->delete("id=12");
            $result = Db::table("user")->delete("id=40");
            //提交操作  证明事务全部已经完成
            Db::commit();
        }catch(Exception $e){
            //如果出现异常,则回滚事务
            Db::rollback();
        }
    }
    /*
     * 视图查询类似于多表查询  具体使用参考手册
     * */
    public function viewSel(){
        //SELECT `goods`.`id`,`goods`.`name`,`goods`.`price`,fenlei.name tname FROM `goods` INNER JOIN `fenlei` ON `fenlei`.`id`=`goods`.`cid`
        $data = Db::view("goods","id,name,price")->view("fenlei","name tname","fenlei.id=goods.cid")->select();
        dump(Db::table("user")->getLastSql());
        dump($data);
    }
}