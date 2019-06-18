package Dmeo1;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnObj {
	public ConnObj(ResultSet res) throws SQLException {
		this.id = res.getString("id");
		this.name = res.getString("name");
		this.title = res.getString("title");
		this.price = res.getFloat("price");
		this.year = res.getInt("yr");
		this.description = res.getString("description");
		this.sale_amount = res.getInt("sale_amount");
	}
	
	public ConnObj () {
		
	}
	
	private String id;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getTitle() {
		return title;
	}
	public float getPrice() {
		return price;
	}
	public int getYear() {
		return year;
	}
	public String getDescription() {
		return description;
	}
	public int getSale_amount() {
		return sale_amount;
	}
	private String name;
	private String title;
	private float price;
	private int year;
	private String description;
	private int sale_amount;
	@Override
	public String toString() {
		System.out.println(this.id+"	"+this.name+"	"+this.title+"	"+this.price+"	"+this.year+"	"+this.description+"	"+this.sale_amount);
		return null;
	}
	
	
}
