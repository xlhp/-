package xmlDemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;

public class Demo1 {
	public static void main(String[] args) {
		new Demo1().saxParseXML();
	}
	public void saxParseXML() {
		/**1,����һ����������������
		 * 2,ͨ���������󴴽�SAX������
		 * 3,ͨ��һ�����ݴ�����(��Ҫ�Լ�д)
		 * 4,��ʼ����
		 */
		//1,
		SAXParserFactory sex = SAXParserFactory.newInstance();
		try {
			//2,
			SAXParser saxParser = sex.newSAXParser();
			//3,
			PersonHandler personhandler = new PersonHandler();
			InputStream is =  Thread.currentThread().
					getContextClassLoader().
					getResourceAsStream("xmlDemo//person.xml");
			try {
				saxParser.parse(is, personhandler);
				personhandler.getPersons().forEach((person)->{System.out.println(person.toString());});;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}	
