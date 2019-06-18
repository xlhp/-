package xmlDemo;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PersonHandler extends DefaultHandler{
	private List<Person> persons = null;
	private Person person;
	private String tag;//���ڼ�¼���ڽ����ı�ǩ��
	public List<Person> getPersons(){
		return persons;
	}
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		persons = new ArrayList<>();
		System.out.println("��ʼ����xml�ĵ�");
	}
	//��������ʱ����
	@Override
	public void endDocument()throws SAXException{
		super.endDocument();
		System.out.println("�ĵ���������");
	}
	/**
	 * 
	 * @param uri �����ռ�
	 * @param localName ��ǰ׺��ǩ��
	 * @param qName ��ǰ׺��ǩ��
	 * @param attributes ��ǰ��ǩ�����Լ���
	 * @throws SAXException
	 */
	@Override
	public void startElement(String uri,String localName,String qName,Attributes attributes) throws SAXException{
		super.startElement(uri, localName, qName, attributes);
		if("person".equals(qName)) {
			person=new Person();
			person.setId(attributes.getValue("person_id"));
		}
		tag=localName;
	}
	/**
	 * 
	 * @param uri �����ռ�
	 * @param loaclName ����ǰ׺�ı�ǩ��
	 * @param qName	��ǰ׺�ı�ǩ��
	 * @throws SAXException 
	 */
	@Override
	public void endElement(String uri ,String loaclName,String qName) throws SAXException {
		super.endElement(qName, qName, qName);
		tag=null;
		if("/person".equals(qName)) {
			persons.add(person);
		}
	}
	
	@Override
	public void characters(char[] ch,int start,int length) throws SAXException {
		super.characters(ch, start, length);
		if(tag!=null) {
			if(tag.equals("name")) {
				person.setName(new String(ch, start, length));
			}else if(tag.equals("addresss")) {
				person.setAddress(new String(ch, start, length));
			}else if(tag.equals("tel")) {
				person.setTel(new String(ch,start,length));
			}else if(tag.equals("fax")) {
				person.setFax(new String(ch, start, length));
			}else if(tag.equals("email")) {
				person.setEmail(new String(ch, start, length));
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
