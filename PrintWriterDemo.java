package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class PrintWriterDemo  {
	public static void printWriter() throws  IOException {
		File file = new File("PrintWriterDemo.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		//���ļ�
		FileOutputStream fos = new FileOutputStream(file);
		//���ñ���
		OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
		/**
		 * ����Ĺ��췽��(true)���Զ�ˢ�¸��� ���Ϊfalse �򲻻��Զ�ˢ��
		 * Emm.... ��ʱ��û����������ɶ����
		 */
		PrintWriter pw = new PrintWriter(fos,true); 
		pw.write("������");
		pw.append("     �Ҽ��ڹ���������");//????
		pw.println("Emmm");
		pw.close();
		
	}
	public static void main(String[] args) {
		try {
			printWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
