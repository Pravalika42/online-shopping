package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import model.*;

public class CartDao {
	private Connection connect;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public CartDao(Connection connect) {
		super();
		this.connect = connect;
	}
	public boolean addToCart(Cart model) {
		boolean result = false;
		try {
			query="insert into cart(email,product_id,quantity,price,total) values(?,?,?,?,?)";
			pst = connect.prepareStatement(query);
			pst.setString(1, model.getEmail());
			pst.setInt(2, model.getProductId());
			pst.setInt(3, model.getQuantity());
			pst.setInt(4, model.getPrice());
			pst.setInt(5, model.getTotal());
			pst.executeUpdate();
		    result=true;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public List<Cart> getCartProducts(String email){
		List<Cart> shoe = new ArrayList<>();
		try {
			
			query="select * from product inner join cart on product.id=cart.product_id and cart.email=? and cart.address is NULL";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, email);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Cart row = new Cart();
				row.setProductId(rs.getInt("product_id"));
				row.setCategory(rs.getString("catagory"));
				row.setProductName(rs.getString("name"));
				row.setPrice(rs.getInt("price"));
				row.setQuantity(rs.getInt("cart.quantity"));
				row.setTotal(rs.getInt("total"));
				shoe.add(row);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return shoe;
	}
	
	public int getCartTotalPrice(String email) {
		int sum = 0;
		try {
			query="select sum(total) from cart where email=? and address is NULL";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, email);
			rs = pst.executeQuery();
			while(rs.next()) {
				sum = rs.getInt(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return sum;
	}
	public Cart getSingleCartProduct(String email,int id) {
		Cart row = null;
		try {
			query="select * from cart where email=? and product_id=? and address is NULL";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, email);
			pst.setInt(2, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				row = new Cart();
				row.setEmail(email);
				row.setProductId(rs.getInt("product_id"));
				row.setPrice(rs.getInt("price"));
				row.setQuantity(rs.getInt("quantity"));
				row.setTotal(rs.getInt("total"));
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return row;
	}
	
	public void updateCartProductQuantity(int id,int quantity,int total,String email) {
		try {
			query="update cart set total =?,quantity=? where email=? and product_id=? and address is NULL";
			pst = this.connect.prepareStatement(query);
			pst.setInt(1, total);
			pst.setInt(2, quantity);
			pst.setString(3, email);
			pst.setInt(4, id);
			pst.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public void removeFromCart(String email,int id) {
		try {
			query="delete from cart where email=? and product_id=? and address is NULL";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, email);
			pst.setInt(2, id);
			pst.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	public void deleteCartProductAdmin(int id) {
		try {
			query="delete from cart where product_id=? and address is NULL";
			pst = this.connect.prepareStatement(query);
			pst.setInt(2, id);
			pst.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}





