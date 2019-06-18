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
		//打开文件
		FileOutputStream fos = new FileOutputStream(file);
		//设置编码
		OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
		/**
		 * 下面的构造方法(true)会自动刷新该流 如果为false 则不会自动刷新
		 * Emm.... 暂时是没看懂这仨有啥区别
		 */
		PrintWriter pw = new PrintWriter(fos,true); 
		pw.write("哈哈哈");
		pw.append("     我加在哈哈哈后面");//????
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
