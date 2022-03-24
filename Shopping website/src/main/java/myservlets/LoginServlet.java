package myservlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.MySqlConnection;
import model.*;
import dao.*;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
   		response.setContentType("text/html");
   		String email = request.getParameter("email");
   		String pass = request.getParameter("psw");
   		if("admin@gmail.com".equals(email) && "admin@123".equals(pass)){
   			request.getSession().setAttribute("email", email);
   			response.sendRedirect("admin/adminHome.jsp");
   		}
   		else{
   			int flag=0;
   			try{
   				UserDao uDao = new UserDao(MySqlConnection.getConnection());
   				User user = uDao.userLogin(email,pass);
   				if(user!= null) {
   					flag=1;
   					request.getSession().setAttribute("email", email);
   					response.sendRedirect("UserHome.jsp");
   				}
   				if(flag==0){
   					response.sendRedirect("UserLogin.jsp?msg=notexist");
   				}
   			}
   			catch(Exception e){
   				response.sendRedirect("UserLogin.jsp?msg=invalid");
   			}
   		}
	}

}
