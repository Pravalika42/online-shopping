package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import model.*;
public class ProductDao {
	private Connection connect;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDao(Connection connect) {
		super();
		this.connect = connect;
	}
	
	public boolean insertProduct(Product model) {
		boolean result = false;
		try {
			query="insert into product value (?,?,?,?,?,?)";
			pst = connect.prepareStatement(query);
			pst.setInt(1,model.getId());
			pst.setString(2,model.getName());
			pst.setString(3,model.getCategory());
			pst.setInt(4,model.getPrice());
			pst.setInt(6,model.getQuantity());
			pst.setString(5,model.getImage());
			pst.executeUpdate();
		    result=true;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	public List<Product> getAllProducts(){
		List<Product> shoe = new ArrayList<>();
		try {
			query="select * from onlineshopping.product where quantity!=0";
			pst = this.connect.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setCategory(rs.getString("catagory"));
				row.setName(rs.getString("name"));
				row.setPrice(rs.getInt("price"));
				row.setQuantity(rs.getInt("quantity"));
				row.setImage(rs.getString("image"));
				shoe.add(row);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return shoe;
	}
	
	public List<String> getAllCategories(){
		List<String> categories = new ArrayList<>();
		String category;
		try {
			query="select distinct catagory from onlineshopping.product";
			pst = this.connect.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				category = rs.getString(1);
				categories.add(category);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return categories;
	}
	public List<Product> getAllSingleCategoryProducts(String category) {
		List<Product> shoe = new ArrayList<>();
		try {
			query="select * from onlineshopping.product where catagory =?";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, category);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Product row = new Product();
				row = new Product();
				row.setId(rs.getInt("id"));
				row.setCategory(rs.getString("catagory"));
				row.setName(rs.getString("name"));
				row.setPrice(rs.getInt("price"));
				row.setQuantity(rs.getInt("quantity"));
				row.setImage(rs.getString("image"));
				shoe.add(row);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return shoe;
	}
	
	public Product getSingleProduct(int id) {
		Product row = null;
		try {
			query="select * from onlineshopping.product where id =?";
			pst = this.connect.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				row = new Product();
				row.setId(rs.getInt("id"));
				row.setCategory(rs.getString("catagory"));
				row.setName(rs.getString("name"));
				row.setPrice(rs.getInt("price"));
				row.setQuantity(rs.getInt("quantity"));
				row.setImage(rs.getString("image"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return row;
	}
	public void updateQuantity(int id,int quantity) {
		try {
			query="update product set quantity=? where id=?";
			pst = this.connect.prepareStatement(query);
			pst.setInt(1, quantity);
			pst.setInt(2, id);
			pst.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public int getMaxId() {
		int id = 1;
		try {
			query="select max(id) from product";
			pst = this.connect.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				id=rs.getInt(1);
				id=id+1;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	public List<Product> getAllProductsAdmin(){
		List<Product> shoe = new ArrayList<>();
		try {
			query="select * from onlineshopping.product ";
			pst = this.connect.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setCategory(rs.getString("catagory"));
				row.setName(rs.getString("name"));
				row.setPrice(rs.getInt("price"));
				row.setQuantity(rs.getInt("quantity"));
				row.setImage(rs.getString("image"));
				shoe.add(row);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return shoe;
	}
	public void updateProductDetails(String name,String category,int price,int quantity,int id) {
		try {
			query="update product set name=?,catagory=?,price=?,quantity=? where id=?";
			pst = this.connect.prepareStatement(query);
			pst.setString(1,name);
			pst.setString(2,category);
			pst.setInt(3,price);
			pst.setInt(4,quantity);
			pst.setInt(5,id);
			pst.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}


