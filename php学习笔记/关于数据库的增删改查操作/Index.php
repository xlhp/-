<?php
namespace app\index\controller;
use think\Request;
//use think\Controller;
class Index
{
    public function index()
    {
//        return '<style type="text/css">*{ padding: 0; margin: 0; } .think_default_text{ padding: 4px 48px;} a{color:#2E5CD5;cursor: pointer;text-decoration: none} a:hover{text-decoration:underline; } body{ background: #fff; font-family: "Century Gothic","Microsoft yahei"; color: #333;font-size:18px} h1{ font-size: 100px; font-weight: normal; margin-bottom: 12px; } p{ line-height: 1.6em; font-size: 42px }</style><div style="padding: 24px 48px;"> <h1>:)</h1><p> ThinkPHP V5<br/><span style="font-size:30px">十年磨一剑 - 为API开发设计的高性能框架</span></p><span style="font-size:22px;">[ V5.0 版本由 <a href="http://www.qiniu.com" target="qiniu">七牛云</a> 独家赞助发布 ]</span></div><script type="text/javascript" src="https://tajs.qq.com/stats?sId=9347272" charset="UTF-8"></script><script type="text/javascript" src="https://e.topthink.com/Public/static/client.js"></script><think id="ad_bd568ce7058a1091"></think>';
//        $request = request();
//        dump($request);
//        //使用系统类
//        /*
//         * 单例模式创建 无法通过new进行实例化
//         * */
//        $request2 = Request::instance();
        return view();

    }
    public function instanceReq(Request $request){
//        dump($request);
        //获取域名
        dump($request->domain());
        //获取url地址 : 除去域名以外的url  即 用户在地址栏能看到的所有url除去域名之外的信息
        dump($request->url());
        //获取入口文件
        dump($request->baseFile());
        //获取baseUrl
        dump($request->baseUrl());
        //获取pathinfo  即除去域名与参数的路径
        dump($request->pathinfo());
        //pathinfo 无后缀
        dump($request->path());
        //url地址伪静态后缀  即一般网页伪装成.html文件
        dump($request->ext());
    }
    public function getReqInfo(Request $req){
        //获取当前模块
        dump($req->module());
        //获取控制器
        dump($req->controller());
        //获取当前方法
        dump($req->action());
    }
    public function getReqType(Request $req){
        dump($req->method());
        //获取资源类型
        dump($req->type());
        //访问地址
        dump($req->ip());
        //判断是否ajax
        dump($req->isAjax());
        //获取请求参数
        dump($req->param());
        //只想获取请求的几个参数
        dump($req->only("id"));
        //获取特定字段
        dump($req->except("id"));
    }
    public function simAjax(){
        return view();
    }

    public function getData(Request $req){

        //判断get请求中是否存在id
        dump($req->has("id","get"));
        dump(input("?get.id"));
        //读取参数
        dump($req->get(""));
        dump(input("get.id"));

        //获取所有
        dump($req->get());
        dump(input("get."));
//        return view();
    }

    //对数据进行过滤 防止数据库被恶意破坏
    public function filter(Request $req){
        $req->filter("");
    }

    public function modType(Request $req){
        dump($req->get("id","md5"));
    }

    //修改变量
    public function modifyVar(Request $req){
        dump($req -> get("name"));
        $req->get(["name"=>"ding","id"=>2]);
        dump($req->get());
    }
    //查看请求类型
    public function ReqType(Request $req){
//        dump($req->isGet());
//        dump($req->isPost());
//        dump($req->isMobile());
//
//        dump(\request()->isMobile());
//        dump(\request()->session());
//        dump(\request()->server());
        dump(\request()->method());
        dump(\request()->isAjax());
    }
    //参数绑定
    public function bindingParam(Request $req,$id=1,$name="admin"){
        dump($id);
        dump($name);
    }


}
