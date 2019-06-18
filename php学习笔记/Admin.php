<?php
/**
 * Created by PhpStorm.
 * User: 丁兆顺
 * Date: 2018/11/30
 * Time: 12:38
 */
namespace  app\index\controller;
use think\Controller;
use app\index\model\User;
use think\Db;
class Admin extends Controller{
    public function index(){
        $user = new User();
        //查询所有数据
        $data = $user->table("user")->select();
//        dump($data);
        dump($user->getLastSql());
        //查询一条
        $data1 = $user->table("user")->find();
        dump($user->getLastSql());
        $data2 = Db::table("user")->select();
//        dump($data2);
        $data3 = $user->name("user")->select();
//        dump("查询name指定的表");
//        dump($data3);
        $data4 = \db("user")->select();
        dump($data4);
        dump(\db("user")->getLastSql());
        return view();
    }

    //and条件查询
    public function whereSel(){
        //双链接where方法就是进行and查询
        $data = Db::table("user")->where("id",">","3")->where("id","<","7")->select();
        dump(Db::table("user")->getLastSql());
        dump($data);
        echo "<hr />";
        $data1 = Db::table("user")->where("name","like","%丁%")->select();
        dump(Db::table("user")->getLastSql());
        dump($data1);

    }
    //or条件查询
    public function whereOrSel(){
        $data = Db::table("user")->whereOr("name","like","%丁%")->whereOr("id",">","4")->select();
        dump(Db::table("user")->getLastSql());
        dump($data);
    }
    //截取操作
    public function limiteSel(){
        $data = Db::table("user")->limit(2,5)->select();
        dump(Db::table("user")->getLastSql());
        dump($data);
    }
    //排序查询操作
    public function orderSel(){
        $data = Db::table("user")->order("id","desc")->select();
        dump(Db::table("user")->getLastSql());
        dump($data);
    }

    //查询某些字段 及 别名
    public function fieldSel(){
        $data = Db::table("user")->field("name 姓名,pass 密码")->select();
        $data = Db::table("user")->field(["name"=>"姓名","pass"=>"密码"])->select();
        dump(Db::table("user")->getLastSql());
        dump($data);
    }

    //调用系统函数
    public function funSel(){
        $data = Db::table("user")->field("count(*) as tot")->select();
        dump(Db::table("user")->getLastSql());
        dump($data);
    }
    //通过page方法进行截取分页
    public function usePage(){
        $data = Db::table("user")->page("2,2")->select();
        dump($data);
    }
    //分组聚合
    public function groupBy(){
        $data = Db::table("user")->field("pass,count(*) tot")->group("pass")->select();
        dump($data);
    }
    //having  对分组后的数据进行过滤
    public function havingUse(){
        $data = Db::table("user")->field("pass,count(*) tot")->group("pass")->having("tot>=4")->select();
        dump($data);
    }
    //多表查询
    public function moreTable(){
        $data = Db::query("select goods.* ,fenlei.name tname from goods, fenlei where goods.cid = fenlei.id");
        dump($data);
    }

    //tp的多表查询
    public function tpMoreTable(){
        //内联查询
        //SELECT `goods`.*,fenlei.name fname FROM `goods` INNER JOIN `fenlei` ON `goods`.`cid`=`fenlei`.`id`
        //左链接和右链接只要在join方法添加第三个参数即可
        //表名起别名  alias()方法
        $data = Db::table("goods")->field("goods.*,fenlei.name fname")
            ->join("fenlei","goods.cid=fenlei.id")->select();
        dump(Db::table("goods")->getLastSql());
        dump($data);
    }
    //合并多个插叙集的结果
    public function unionFun(){
        $data = Db::field("name uname")->table("user")->union("select * from goods")->select();
        dump(Db::table("user")->getLastSql());
        dump($data);
    }
    //如果使用tp进行统计数据 可以使用自带方法来使用
}

























