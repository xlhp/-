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
	 * @return ����һ��information��������
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
	 * getTextContent()�õ��ڵ��ı����� ����������ӽڵ� �� ���ӽڵ�������ı����ݻ�ȡ
	 * getChildNodes()��ȡ�����ӽڵ�,�������ӽڵ�������ʱ��������jsDOM ���ӽڵ������� .parse(xml) ����һ��DOM ����
	 * item ������ iterator �����õ��ڼ����ڵ�
	 */
	public void getXml1() {
		// ���������ļ�����
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
	 * java DOM��������xml�ļ� ��������
	 */
	public void getXml() {
		long lasting = System.currentTimeMillis();
		System.out.println();
		File file = new File("src\\xmlDemo\\address.xml");
		// ����������������,
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// ͨ�����������������õ�����������
			DocumentBuilder builder = factory.newDocumentBuilder();
			// ͨ�������������xml�ļ�����������document����
			Document doc = builder.parse(file);
			// ͨ��DOM�������нڵ��ѯ
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
