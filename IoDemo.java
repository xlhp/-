package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

public class IoDemo {
	/**
	 * 字节流:inputstream/outputstream
	 * 
	 */
	public static void main(String[] args)throws IOException {
//		bytesOstream();
//		bytesIstream();
		charOStream();
		charIStream();
	}
	public static void bytesOstream() throws IOException {
		String str = "Hello IO!";
		byte[] bytesArr = str.getBytes();
		File file = new File("wenjian.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		//因为使用字节流作为媒介,所以对应的写出是outputstream
		//媒介对象是文件,所以使用fileoutputstream
		OutputStream out = new FileOutputStream(file);
		out.write(bytesArr);
		out.close();
	}
	//字节流读入文件  一下代码仅供参考,一切。。。。  串词了，水平有限，还需要改
	public static void bytesIstream() throws IOException{
		File file = new File("wenjian.txt");
		byte[] bytesArr = new byte[4];
		int length;
		String str = null;
		StringBuffer strbuffer = new StringBuffer();
		//媒介:字节流   ->inputstream 对象:file  ->fileinputstream
		InputStream in = new FileInputStream(file);
		/*
		 * in.read()方法返回一个int值 该值表示取到的字节长度,如果没取到,就返回-1
		 * read(byte[])将信息读入到该数组中
		 */
		while((length=in.read(bytesArr)) !=-1) {
			str = new String(bytesArr,0,length);
			strbuffer.append(str);
//			System.out.println(strbuffer);
			for(int i=0;i<bytesArr.length;i++) {
				bytesArr[i]=0;
			}
		}
		System.out.println(strbuffer);
		in.close();
	}
	/*
	 * 字符流：Reader/Writer
	 * 
	 * */
	//字符流文件输出流
	public static void charOStream() throws IOException {
		String str = "Hello charIO!";
		File file = new File("charIODemo.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		//字符流Writer，对象为文件：filewriter
		Writer writer = new FileWriter(file);
		writer.write(str);
		writer.close();
	}
	//字符流文件读入流
	public static void charIStream() throws IOException{
		File file = new File("charIODemo.txt");
		int length;
		Reader reader = new FileReader(file);
		char[] charArr = new char[4];
		StringBuffer strbuf = new StringBuffer();
		while((length=reader.read(charArr))!=-1) {
			strbuf.append(charArr,0,length);
		}
		System.out.println(strbuf);
	}
}

















