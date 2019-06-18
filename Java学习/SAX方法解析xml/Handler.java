package sax_xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import xmlDemo.Address;

public class Handler extends DefaultHandler {
	// 储存得到的节点名字和值
	private Map<String, String> map = null;
	// 储存map集合方便遍历
	private List<Map<String, String>> list = null;
	// 储存当前处理的节点名
	String currentTag = null;
	// 储存当前处理节点的值
	String currentValue = null;
	// 储存根节点名称
	String nodeName = null;
	
	/**
	 * 使用javabean对象来储存所得到的对象
	 * @param nodeName
	 */
	private List<Address> add_list = null;
	//创建一个address实例
	private Address add_obj = null;
	
	public  List<Address> getAdd_list(){
		return add_list;
	}
	// 在初始化解析器的时候将
	public Handler(String nodeName) {
		this.nodeName = nodeName;
	}

	// 返回一个list
	public List<Map<String, String>> getList() {
		return list;
	}

	// 开始解析xml时 调用
	public void startDocument() throws SAXException {
		System.out.println("--startDocument()--");
		// 实例化list集合
		list = new ArrayList<>();
		//实例化
		add_list = new ArrayList<>();
	}

	// 开始解析一个元素时调用
	/**
	 * @param uri:命名空间的uri
	 * @param localName:标签名称
	 * @param qName:带命名空间的标签名称
	 * @param Attributes:
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		System.out.println("进入startElement");
//		System.out.println("--startElement()--" + qName);
		// 验证是否为根元素 是:实例化map集合
		if (qName.equals(nodeName)) {
			System.out.println("准备实例化map");
			map = new HashMap<>();
			//实例化对象
			add_obj = new Address();
		}
		System.out.println("已经实例化map");
		// 检测该元素是否有属性  处理属性
		if (attributes != null && map != null) {
			System.out.println("正在操作属性");
			for (int i = 0; i < attributes.getLength(); i++) {
//				System.out.println("开始储存属性");
				map.put(attributes.getQName(i), attributes.getValue(i));
			}
		}
		currentTag = qName;
	}

	// 解析任何一个节点都需要调用characters()方法
	/**
	 * @param ch:内容的字符数组
	 * @param start:开始位置
	 * @param length:长度
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("开始解析内容");
//		System.out.println("--characters()--");
		if (currentTag != null && map != null) {
			// 将字符数组重新拼接成字符串
			currentValue = new String(ch, start, length);
//			System.out.println(currentValue);
			//判断条件:内容不为null,内容不为空,内容不是换行符
			add_obj.setAll(currentTag, currentValue);
			if (currentValue != null && !currentValue.trim().equals("") && !currentValue.trim().equals("\n")) {
				System.out.println("将内容放进map");
				map.put(currentTag, currentValue);
				// System.out.println("执行内容");
//				System.out.println("---" + currentTag + " " + currentValue);
			}
			//解析结束,将两个变量置空
			currentTag = null;
			currentValue = null;
		}
	}
	//解析完每一个元素都会调用该方法
	public void endElement(String uri, String localName, String qName) throws SAXException {
//		System.out.println("--endElement()--" + qName);
		if (qName.equals(nodeName)) {
			//将map放进list中
			list.add(map);
			add_list.add(add_obj);
			map = null;
			add_obj=null;
			
		}
		System.out.println();
	}

	public void endDocument() throws SAXException {
		System.out.println("--endDocument()--");
		super.endDocument();
	}
}
