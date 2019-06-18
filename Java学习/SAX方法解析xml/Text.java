package sax_xml;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import xmlDemo.Address;

public class Text {
	public static void main(String[] args) {
		ArrayList<Map<String, String>> list = 
				(ArrayList<Map<String, String>>) 
				Saxservic.ReadXML("src\\sax_xml\\address.xml", "RESULT");
		Iterator<Map<String, String>> iter = list.iterator();
		while(iter.hasNext()) {
			Set<Entry<String, String>> set = iter.next().entrySet();
			for (Entry<String, String> entry : set) {
				System.out.println(entry.getKey()+"   "+entry.getValue());
			}
		}
		System.out.println("------------------------------------------------");
		ArrayList<Address> add_list = (ArrayList<Address>) Saxservic.ReadXML2("src\\sax_xml\\address.xml", "RESULT");
		Iterator<Address> iter2 = add_list.iterator();
		while(iter2.hasNext()) {
			System.out.println(iter2.next().toString());
		}
	}
}
