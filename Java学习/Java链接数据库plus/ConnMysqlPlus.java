package Dmeo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnMysqlPlus {
	
	private void connMysql() {
		Connection conn;
		PreparedStatement preSta;
		PreparedStatement preStaSelect;
		ResultSet res ;
		List<ConnObj> list = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3360/bookdb";
		String user = "root";
		String password = "ding4732";
		//这样的插入语句好像体现不出来preparedStatement的用处啊
//		String insert = "INSERT INTO books(id,name,title,price,yr,description,sale_amount) \r\n" + 
//				"VALUES(\"006\",\"丁兆顺\",\"谁知道啥玩意2\",130,2014,\"真有钱,这么垃圾都出二了\",12);";
//		String delete = "DELETE from books WHERE ID=\"006\";";
		String update = "UPDATE books SET name=\"丁兆顺\" WHERE id=\"005\";";
		String select = "select * from books where name=?";
		try {
			conn = DriverManager.getConnection(url, user, password);
			//创建插入操作的statement(声明)
			preSta = conn.prepareStatement(update);
			preSta.executeUpdate();

			//提前编译该查询语句
			preStaSelect = conn.prepareStatement(select);
			//创建完善的查询语句
			preStaSelect.setString(1, "丁兆顺");
			
			preStaSelect.execute();
			res = preStaSelect.getResultSet();
			while(res.next()) {
				new ConnObj(res).toString();
			}
			//循环输出很烦啊
//			while(res.next()) {
//				System.out.print(res.getString("id")+"	");
//				System.out.print(res.getString("name")+"	");
//				System.out.print(res.getString("title")+"	");
//				System.out.print(res.getFloat("price")+"	");
//				System.out.print(res.getInt("yr")+"	");
//				System.out.print(res.getString("description")+"	");
//				System.out.print(res.getInt("sale_amount")+"	");
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	public static void main(String[] args) {
		new ConnMysqlPlus().connMysql();
	}
}
