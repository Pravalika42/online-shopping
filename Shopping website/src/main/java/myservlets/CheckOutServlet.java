package myservlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.MySqlConnection;

import dao.*;
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public CheckOutServlet() {
        // TODO Auto-generated constructor stub
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getSession().getAttribute("email").toString();
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String mobileNumber = request.getParameter("mobilenumber");
		String paymentMethod = request.getParameter("paymentMethod");
		String status="bill";
		try {
			
			OrderDao oDao = new OrderDao(MySqlConnection.getConnection());
			StringBuilder random = new StringBuilder();// generating order id number randomly
			for (int iter = 0; iter < 4; iter++) {
				random.append((int) Math.floor(Math.random() * 9));
			}
			String transactId = random.toString();
			oDao.updateOrderDetails(email, address, city, state, country, mobileNumber, paymentMethod, transactId, status);
			
			UserDao uDao = new UserDao(MySqlConnection.getConnection());
			uDao.updateUserDetails(email, address, city, state, country, mobileNumber);
			
			response.sendRedirect("Bill.jsp?id="+transactId+"");
		}
		
		catch(Exception e)
	    {
	    	System.out.println(e);
	    }
	}

}
