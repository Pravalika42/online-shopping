package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
public class UserDao {
	private Connection connect;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public UserDao(Connection con) {
		super();
		this.connect = con;
	}
	public User userLogin(String email,String password) {
		User user = null;
		try {
			query="select * from user where email=? and pass=?";
			pst=this.connect.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setAddress(rs.getString("address"));
				user.setCity(rs.getString("city"));
				user.setState(rs.getString("state"));
				user.setCountry(rs.getString("country"));
				user.setMobile(rs.getString("mobilenumber"));
				
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	public boolean userRegister(User model) {
		boolean result = false;
		try {
			query="insert into user(name,pass,email) values(?,?,?)";
			pst = connect.prepareStatement(query);
		    pst.setString(1, model.getName());
		    pst.setString(2, model.getPassword());
		    pst.setString(3, model.getEmail());
		    pst.executeUpdate();
		    result=true;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	public User getUserDetails(String email) {
	    User row = null;
		try {
			query="select * from user where email=?";
			pst = this.connect.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				row = new User();
				row.setName(rs.getString("name"));
				row.setPassword(rs.getString("pass"));
				row.setAddress(rs.getString("address"));
				row.setCity(rs.getString("city"));
				row.setState(rs.getString("state"));
				row.setCountry(rs.getString("country"));
				row.setMobile(rs.getString("mobilenumber"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return row;
	}
	public void updateUserDetails(String email,String address,String city,String state,String country,String mobile) {
		try {
			query="update user set address=?,city=?,state=?,country=?,mobileNumber=? where email =?";
			pst = this.connect.prepareStatement(query);
			pst.setString(1,address);
			pst.setString(2,city);
			pst.setString(3,state);
			pst.setString(4,country);
			pst.setString(5,mobile);
			pst.setString(6,email);
			pst.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
}
