package xmlDemo;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMXML {
	public static void main(String[] args) {
		// new DOMXML().getXml();
		// new DOMXML().getXml1();
		Information[] infor = new DOMXML().getInfor();
		for (Information information : infor) {
			System.out.println(information.toString());
		}
	}

	/**
	 * 
	 * @return 返回一个information对象数组
	 */
	public Information[] getInfor() {
		File file = new File("src//xmlDemo//address.xml");
		Information[] infor = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			NodeList list = document.getElementsByTagName("VALUE");
			infor = new Information[list.getLength()];
			for (int i = 0; i < list.getLength(); i++) {
				NodeList childList = list.item(i).getChildNodes();
				infor[i]=new Information();
				System.out.println(childList.getLength());
				for (int j = 0; j < childList.getLength(); j++) {
					if (j % 2 != 0) {
						// System.out.println(childList.item(j).getTextContent()+
						// " "+childList.item(j).getNodeName());
						Information.setAll(childList.item(j).getNodeName(), childList.item(j).getTextContent(),
								infor[i]);
					}
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infor;

	}

	/**
	 * getTextContent()得到节点文本内容 如果下面有子节点 则 将子节点的所有文本内容获取
	 * getChildNodes()获取所有子节点,其中数子节点数量的时候类似于jsDOM 中子节点数类似 .parse(xml) 返回一个DOM 对象
	 * item 类似于 iterator 用来得到第几个节点
	 */
	public void getXml1() {
		// 创建解析文件对象
		File file = new File("src//xmlDemo//address.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			NodeList list = document.getElementsByTagName("VALUE").item(0).getChildNodes();
			System.out.println(list.getLength());
			for (int i = 0; i < list.getLength(); i++) {
				System.out.println(list.item(i).getTextContent());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * java DOM方法解析xml文件 基本案例
	 */
	public void getXml() {
		long lasting = System.currentTimeMillis();
		System.out.println();
		File file = new File("src\\xmlDemo\\address.xml");
		// 创建解析工厂对象,
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 通过解析工厂对象来得到解析器对象
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 通过解析器对象和xml文件对象来创建document对象
			Document doc = builder.parse(file);
			// 通过DOM树来进行节点查询
			NodeList nl = doc.getElementsByTagName("VALUE");
			NodeList nl2 = doc.getElementsByTagName("NO");
			NodeList nl3 = doc.getElementsByTagName("NAME");
			for (int i = 0; i < nl.getLength(); i++) {
				System.out.println(nl.item(i).getFirstChild().getNodeValue());
			}
			for (int i = 0; i < nl2.getLength(); i++) {
				System.out.println(nl2.item(i));
			}
			for (int i = 0; i < nl3.getLength(); i++) {
				System.out.println(nl3.item(i).getLastChild().getNodeValue());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
