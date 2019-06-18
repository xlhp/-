package com;

public class InstanceDemo{
	private String str ;
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	//�����ʱ�ͽ���ʵ����
	private static InstanceDemo instance2 = new InstanceDemo();
	public static InstanceDemo getInstance2() {
		return instance2;
	}
	//��ʹ��ʱ����ʵ����
	private static InstanceDemo instance = null;
	public static  InstanceDemo getInstance() {
		if(instance==null)
			
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			synchronized(InstanceDemo.class) { //�˴���synchronized����ָ��ĳ������,�˶�������������Ի������ڵĴ�����п���(think in java page:685)
				if(instance==null) {
					instance = new InstanceDemo();
				}
			}
		return instance;
	}
	private InstanceDemo() {}
}
