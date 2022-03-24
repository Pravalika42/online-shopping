package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import model.*;
public class OrderDao {
	private Connection connect;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public OrderDao(Connection connect) {
		super();
		this.connect = connect;
	}
	public List<Order> getOrders(String email){
		List<Order> orders = new ArrayList<>();
		try {
			
			query="select * from cart inner join product where cart.product_id=product.id and cart.email=? and cart.status!='cancel' and cart.orderDate is not NULL";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, email);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setProductId(rs.getInt("product_id"));
				order.setCategory(rs.getString("catagory"));
				order.setProductName(rs.getString("name"));
				order.setPrice(rs.getInt("price"));
				order.setQuantity(rs.getInt("quantity"));
				order.setTotal(rs.getInt("total"));
				order.setAddress(rs.getString("address"));
				order.setCity(rs.getString("city"));
				order.setState(rs.getString("state"));
				order.setCountry(rs.getString("country"));
				order.setMobile(rs.getString("mobilenumber"));
				order.setOrderDate(rs.getString("orderdate"));
				order.setDeliveryDate(rs.getString("deliverydate"));
				order.setPaymentMethod(rs.getString("paymentmethod"));
				order.setTransactId(rs.getString("transactionid"));
				orders.add(order);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return orders;
	}
	
	public void updateOrderDetails(String email,String address,String city,String state,String country,String mobile,String payMethod,String transactId,String status) {
		try {
			query="update cart set address=?,city=?,state=?,country=?,mobilenumber=?,orderdate=now(),deliverydate=DATE_ADD(orderdate,INTERVAL 7 DAY),paymentmethod=?,transactionid=?,status=? where email=? and address is NULL";
			pst = this.connect.prepareStatement(query);
			pst.setString(1,address);
			pst.setString(2,city);
			pst.setString(3,state);
			pst.setString(4,country);
			pst.setString(5,mobile);
			pst.setString(6,payMethod);
			pst.setString(7,transactId);
			pst.setString(8,status);
			pst.setString(9,email);
			pst.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public void updateStatus(String email,int id,String status) {
		try {
			query="update cart set status=? where transactionid=? and email=? and address is not NULL";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(3, email);
			pst.setInt(2, id);
			pst.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	public Order getSingleOrder(int id,String email) {
		Order row = null;
		try {
			query="select * from cart where transactionid=? and email = ? and address is not NULL";
			pst = this.connect.prepareStatement(query);
			pst.setInt(1, id);
			pst.setString(2, email);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				row = new Order();
				row.setProductId(rs.getInt("product_id"));
				row.setPrice(rs.getInt("price"));
				row.setQuantity(rs.getInt("quantity"));
				row.setTotal(rs.getInt("total"));
				row.setAddress(rs.getString("address"));
				row.setCity(rs.getString("city"));
				row.setState(rs.getString("state"));
				row.setCountry(rs.getString("country"));
				row.setMobile(rs.getString("mobilenumber"));
				row.setOrderDate(rs.getString("orderdate"));
				row.setDeliveryDate(rs.getString("deliverydate"));
				row.setPaymentMethod(rs.getString("paymentmethod"));
				row.setTransactId(rs.getString("transactionid"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return row;
	}
	
	public int getBillTotal(String email,String id) {//transact id
		int sum = 0;
		try {
			query="select sum(total) from cart where email=? and transactionid=? and status='bill'";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, id);
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
	
	public Order getOrderUserDetails(String email,String id) {//transact id
		Order row = null;
		try {
			query="select * from user inner join cart where cart.email=user.email and cart.email=? and transactionid=? and cart.status='bill'";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				row = new Order();
				row.setUserName(rs.getString("user.name"));
				row.setEmail(email);
				row.setProductId(rs.getInt("product_id"));
				row.setPrice(rs.getInt("price"));
				row.setQuantity(rs.getInt("cart.quantity"));
				row.setTotal(rs.getInt("total"));
				row.setAddress(rs.getString("address"));
				row.setCity(rs.getString("city"));
				row.setState(rs.getString("state"));
				row.setCountry(rs.getString("country"));
				row.setMobile(rs.getString("mobilenumber"));
				row.setOrderDate(rs.getString("orderdate"));
				row.setDeliveryDate(rs.getString("deliverydate"));
				row.setPaymentMethod(rs.getString("paymentmethod"));
				row.setTransactId(rs.getString("transactionid"));
				break;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return row;
	}
	
	public List<Order> getOrderProductDetails(String email,String id) {//transact id
		List<Order> orders = new ArrayList<>();
		try {
			query="select * from cart inner join product where cart.product_id = product.id and cart.email=? and transactionid=? and cart.status='bill'";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Order row = new Order();
				row.setProductId(rs.getInt("product_id"));
				row.setCategory(rs.getString("catagory"));
				row.setProductName(rs.getString("product.name"));
				row.setPrice(rs.getInt("price"));
				row.setQuantity(rs.getInt("cart.quantity"));
				row.setTotal(rs.getInt("total"));
				row.setAddress(rs.getString("address"));
				row.setCity(rs.getString("city"));
				row.setState(rs.getString("state"));
				row.setCountry(rs.getString("country"));
				row.setMobile(rs.getString("mobilenumber"));
				row.setOrderDate(rs.getString("orderdate"));
				row.setDeliveryDate(rs.getString("deliverydate"));
				row.setPaymentMethod(rs.getString("paymentmethod"));
				row.setTransactId(rs.getString("transactionid"));
				orders.add(row);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return orders;
	}
	
	
}
