package xmlDemo;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class JDOM {
	public static void main(String[] args) {
		new JDOM().getXml();
	}
	public void getXml() {
		File file =new File("src//xmlDemo//address.xml");
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(file);
			Element root = doc.getRootElement();
			List<Element> list = root.getChildren();
			Iterator<Element> it = list.iterator();
			while(it.hasNext()) {
				Element ele = it.next();
				System.out.println(ele.getChildText("NO"));
				System.out.println(ele.getChildText("ADDR"));
				System.out.println(ele.getChildText("NAME"));
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
