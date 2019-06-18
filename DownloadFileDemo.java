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
//		System.out.println("进入servlet");
		OutputStream out;//向相应正文中传输文件
		InputStream in;//读取服务器端的文件
		String fileName = req.getParameter("filename");
		if(fileName.equals(null)) {
			out=resp.getOutputStream();
		}
		//获得读取本地文件的输入流
		in = getServletContext().getResourceAsStream("com/file/Demo.txt");
		int length = in.available();
		//设置相应正文的HIME
		resp.setContentType("application/force-download");
		resp.setHeader("Content-Length", String.valueOf(length));
		resp.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		//把服务器文件发送给客户
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
