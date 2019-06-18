package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadFileDemo extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
//		System.out.println("����servlet");
		OutputStream out;//����Ӧ�����д����ļ�
		InputStream in;//��ȡ�������˵��ļ�
		String fileName = req.getParameter("filename");
		if(fileName.equals(null)) {
			out=resp.getOutputStream();
		}
		//��ö�ȡ�����ļ���������
		in = getServletContext().getResourceAsStream("com/file/Demo.txt");
		int length = in.available();
		//������Ӧ���ĵ�HIME
		resp.setContentType("application/force-download");
		resp.setHeader("Content-Length", String.valueOf(length));
		resp.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		//�ѷ������ļ����͸��ͻ�
		out=resp.getOutputStream();
		int bytesRead=0;
		byte[] buffer = new byte[512];
		while((bytesRead = in.read(buffer))!=-1) {
			out.write(buffer,0,bytesRead);
		}
		in.close();
		out.close();
		
	}	
}
