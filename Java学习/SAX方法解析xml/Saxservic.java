package sax_xml;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import xmlDemo.Address;

public class Saxservic {
	public static List<Map<String, String>> ReadXML(String uri,String NodeName){
		//创建一个saxparser工厂对象
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			//创建解析器对象
			SAXParser parser = factory.newSAXParser();
			//创建助手类实例   参数是根节点
			Handler handler = new Handler("VALUE");
			try {
				//开始解析
				parser.parse(uri,handler);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return handler.getList();
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static List<Address> ReadXML2(String uri,String NodeName){
		//创建一个saxparser工厂对象
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			//创建解析器对象
			SAXParser parser = factory.newSAXParser();
			//创建助手类实例   参数是根节点
			Handler handler = new Handler("VALUE");
			try {
				//开始解析
				parser.parse(uri,handler);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return handler.getAdd_list();
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
