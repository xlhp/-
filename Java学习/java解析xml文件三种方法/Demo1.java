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
		/**1,创建一个解析器工厂对象
		 * 2,通过工厂对象创建SAX解析器
		 * 3,通过一个数据处理器(需要自己写)
		 * 4,开始解析
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
