package myservlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.MySqlConnection;
import model.*;
import dao.*;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("usrname");
	    String email = request.getParameter("email");
	    String pass = request.getParameter("psw");
	    try{
	    UserDao uDao = new UserDao(MySqlConnection.getConnection());
	    User user = new User();
	    user.setName(name);
	    user.setEmail(email);
	    user.setPassword(pass);
	    if(uDao.userRegister(user)) {
	    	response.sendRedirect("UserRegister.jsp?msg=valid");
	    }
	    else {
	    	response.sendRedirect("UserRegister.jsp?msg=invalid");
	    }
	    }
	    catch(Exception e)
	    {
	    	response.sendRedirect("UserRegister.jsp?msg=invalid");
	    	System.out.println(e);
	    }

	}

}
