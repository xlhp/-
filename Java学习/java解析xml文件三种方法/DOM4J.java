package xmlDemo;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;

public class DOM4J {
	public static void main(String[] args) {
		long last = System.currentTimeMillis();
		System.out.println(last);
		new DOM4J().getXml();
	}
	
//	public void getXml2() {
//		File file = new File("src//xmlDemo//address.xml");
//		SAXReader reader = new SAXReader();
//		try {
//			Document doc = reader.read(file);
//			List<Node> list = doc.selectNodes("RESULT/VALUE/NO");
//			System.out.println(list.size());
//			Iterator<Node> it = list.iterator();
//			while(it.hasNext()) {
//				System.out.println(it.next());
//			}
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
//	}
	
	/**
	 * ��ʵ��DOM4j�õ�����
	 */
	public void getXml() {
		File file = new File("src//xmlDemo//address.xml");
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(file);
			//��ȡ���ڵ�
			Element root = doc.getRootElement();
			List<Element> list =root.elements("VALUE");
			//��ȡ���ڵ��� valueԪ���б�
			Iterator<Element> ite = list.iterator();
			while(ite.hasNext()) {
				System.out.println(ite.next().elements().iterator().next().getText());
			}
			
			Element foo;
			Iterator<Element> it = root.elementIterator();
			while(it.hasNext()) {
				foo=it.next();
				System.out.println(foo);
				//���Ը�Ԫ�ص�xml
				System.out.println(foo.asXML());
				//��ȡ��Ԫ����ĳ����Ԫ�ص��ı�
				System.out.println(foo.elementText("NO"));
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
