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
	 * �ֽ���:inputstream/outputstream
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
		//��Ϊʹ���ֽ�����Ϊý��,���Զ�Ӧ��д����outputstream
		//ý��������ļ�,����ʹ��fileoutputstream
		OutputStream out = new FileOutputStream(file);
		out.write(bytesArr);
		out.close();
	}
	//�ֽ��������ļ�  һ�´�������ο�,һ�С�������  �����ˣ�ˮƽ���ޣ�����Ҫ��
	public static void bytesIstream() throws IOException{
		File file = new File("wenjian.txt");
		byte[] bytesArr = new byte[4];
		int length;
		String str = null;
		StringBuffer strbuffer = new StringBuffer();
		//ý��:�ֽ���   ->inputstream ����:file  ->fileinputstream
		InputStream in = new FileInputStream(file);
		/*
		 * in.read()��������һ��intֵ ��ֵ��ʾȡ�����ֽڳ���,���ûȡ��,�ͷ���-1
		 * read(byte[])����Ϣ���뵽��������
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
	 * �ַ�����Reader/Writer
	 * 
	 * */
	//�ַ����ļ������
	public static void charOStream() throws IOException {
		String str = "Hello charIO!";
		File file = new File("charIODemo.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		//�ַ���Writer������Ϊ�ļ���filewriter
		Writer writer = new FileWriter(file);
		writer.write(str);
		writer.close();
	}
	//�ַ����ļ�������
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

















