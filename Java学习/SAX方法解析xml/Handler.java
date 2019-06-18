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
	// ����õ��Ľڵ����ֺ�ֵ
	private Map<String, String> map = null;
	// ����map���Ϸ������
	private List<Map<String, String>> list = null;
	// ���浱ǰ����Ľڵ���
	String currentTag = null;
	// ���浱ǰ����ڵ��ֵ
	String currentValue = null;
	// ������ڵ�����
	String nodeName = null;
	
	/**
	 * ʹ��javabean�������������õ��Ķ���
	 * @param nodeName
	 */
	private List<Address> add_list = null;
	//����һ��addressʵ��
	private Address add_obj = null;
	
	public  List<Address> getAdd_list(){
		return add_list;
	}
	// �ڳ�ʼ����������ʱ��
	public Handler(String nodeName) {
		this.nodeName = nodeName;
	}

	// ����һ��list
	public List<Map<String, String>> getList() {
		return list;
	}

	// ��ʼ����xmlʱ ����
	public void startDocument() throws SAXException {
		System.out.println("--startDocument()--");
		// ʵ����list����
		list = new ArrayList<>();
		//ʵ����
		add_list = new ArrayList<>();
	}

	// ��ʼ����һ��Ԫ��ʱ����
	/**
	 * @param uri:�����ռ��uri
	 * @param localName:��ǩ����
	 * @param qName:�������ռ�ı�ǩ����
	 * @param Attributes:
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		System.out.println("����startElement");
//		System.out.println("--startElement()--" + qName);
		// ��֤�Ƿ�Ϊ��Ԫ�� ��:ʵ����map����
		if (qName.equals(nodeName)) {
			System.out.println("׼��ʵ����map");
			map = new HashMap<>();
			//ʵ��������
			add_obj = new Address();
		}
		System.out.println("�Ѿ�ʵ����map");
		// ����Ԫ���Ƿ�������  ��������
		if (attributes != null && map != null) {
			System.out.println("���ڲ�������");
			for (int i = 0; i < attributes.getLength(); i++) {
//				System.out.println("��ʼ��������");
				map.put(attributes.getQName(i), attributes.getValue(i));
			}
		}
		currentTag = qName;
	}

	// �����κ�һ���ڵ㶼��Ҫ����characters()����
	/**
	 * @param ch:���ݵ��ַ�����
	 * @param start:��ʼλ��
	 * @param length:����
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("��ʼ��������");
//		System.out.println("--characters()--");
		if (currentTag != null && map != null) {
			// ���ַ���������ƴ�ӳ��ַ���
			currentValue = new String(ch, start, length);
//			System.out.println(currentValue);
			//�ж�����:���ݲ�Ϊnull,���ݲ�Ϊ��,���ݲ��ǻ��з�
			add_obj.setAll(currentTag, currentValue);
			if (currentValue != null && !currentValue.trim().equals("") && !currentValue.trim().equals("\n")) {
				System.out.println("�����ݷŽ�map");
				map.put(currentTag, currentValue);
				// System.out.println("ִ������");
//				System.out.println("---" + currentTag + " " + currentValue);
			}
			//��������,�����������ÿ�
			currentTag = null;
			currentValue = null;
		}
	}
	//������ÿһ��Ԫ�ض�����ø÷���
	public void endElement(String uri, String localName, String qName) throws SAXException {
//		System.out.println("--endElement()--" + qName);
		if (qName.equals(nodeName)) {
			//��map�Ž�list��
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
