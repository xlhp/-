package com;

public class InstanceDemo{
	private String str ;
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	//类加载时就进行实例化
	private static InstanceDemo instance2 = new InstanceDemo();
	public static InstanceDemo getInstance2() {
		return instance2;
	}
	//在使用时进行实例化
	private static InstanceDemo instance = null;
	public static  InstanceDemo getInstance() {
		if(instance==null)
			
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			synchronized(InstanceDemo.class) { //此处的synchronized用来指定某个对象,此对象的锁被用来对花括号内的代码进行控制(think in java page:685)
				if(instance==null) {
					instance = new InstanceDemo();
				}
			}
		return instance;
	}
	private InstanceDemo() {}
}
