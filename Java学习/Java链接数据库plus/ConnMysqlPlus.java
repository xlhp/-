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
		//�����Ĳ������������ֲ�����preparedStatement���ô���
//		String insert = "INSERT INTO books(id,name,title,price,yr,description,sale_amount) \r\n" + 
//				"VALUES(\"006\",\"����˳\",\"˭֪��ɶ����2\",130,2014,\"����Ǯ,��ô������������\",12);";
//		String delete = "DELETE from books WHERE ID=\"006\";";
		String update = "UPDATE books SET name=\"����˳\" WHERE id=\"005\";";
		String select = "select * from books where name=?";
		try {
			conn = DriverManager.getConnection(url, user, password);
			//�������������statement(����)
			preSta = conn.prepareStatement(update);
			preSta.executeUpdate();

			//��ǰ����ò�ѯ���
			preStaSelect = conn.prepareStatement(select);
			//�������ƵĲ�ѯ���
			preStaSelect.setString(1, "����˳");
			
			preStaSelect.execute();
			res = preStaSelect.getResultSet();
			while(res.next()) {
				new ConnObj(res).toString();
			}
			//ѭ������ܷ���
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
