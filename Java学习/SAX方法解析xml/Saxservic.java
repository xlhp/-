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
		//����һ��saxparser��������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			//��������������
			SAXParser parser = factory.newSAXParser();
			//����������ʵ��   �����Ǹ��ڵ�
			Handler handler = new Handler("VALUE");
			try {
				//��ʼ����
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
		//����һ��saxparser��������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			//��������������
			SAXParser parser = factory.newSAXParser();
			//����������ʵ��   �����Ǹ��ڵ�
			Handler handler = new Handler("VALUE");
			try {
				//��ʼ����
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
