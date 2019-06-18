package com;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ConnMysql extends HttpServlet{
	public  List<ConnectionObject> connMysql() throws ClassNotFoundException ,SQLException {
		Connection conn;
		Statement statement;
		ResultSet resultSet;
		List<ConnectionObject> objList = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3360/bookdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT";
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		conn = DriverManager.getConnection(url, "root", "ding4732");
		
		statement = conn.createStatement();
		resultSet = statement.executeQuery("SELECT * FROM books");
		while(resultSet.next()) {
			objList.add( new ConnectionObject(resultSet.getString("ID"), resultSet.getString("NAME"), 
					resultSet.getString("TITLE"), resultSet.getFloat("PRICE"), resultSet.getInt("YR"),
					resultSet.getString("DESCRIPTION"), resultSet.getInt("SALE_AMOUNT")));
			
		}
		resultSet.close();
		statement.close();
		conn.close();
		return objList;
	}
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse resp) {
		System.out.println("进入servlet");
		String id = req.getParameter("id");
		if(id.equals("1")) {
			 try {
				List<ConnectionObject> list = connMysql();
				if(!list.isEmpty()) {
					list.forEach((ConnectionObject object)->{
						System.out.println(object.getID()+" "+object.getNAME()+" "+object.getTITLE()+" "+object.getPRICE()+
								" "+object.getYR()+" "+object.getDESCRIPTION()+" "+object.getSALA_AMOUNT());
					});
				}else {
					System.out.println("您访问的数据库为空");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("属性不对");
		}
	}
}
