<?php
/**
 * Created by PhpStorm.
 * UserController: 丁兆顺
 * Date: 2018/11/29
 * Time: 18:40
 */
namespace app\index\controller;
use think\db;
class ConnMysql{
    public function index(){
        $db = new db();
        $data = $db->table("user")->select();
        dump($data);
    }
}