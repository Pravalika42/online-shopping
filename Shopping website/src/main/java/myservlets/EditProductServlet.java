package myservlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.MySqlConnection;
import model.*;
import dao.*;

public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public EditProductServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String category=request.getParameter("category");
		int price=Integer.parseInt(request.getParameter("price"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		
		try {
			ProductDao pDao = new ProductDao(MySqlConnection.getConnection());
			CartDao cDao = new CartDao(MySqlConnection.getConnection());
			pDao.updateProductDetails(name, category, price, quantity, id);
			if(quantity==0)  
			{
				cDao.deleteCartProductAdmin(id);
			}
			response.sendRedirect("EditAllProduct.jsp?msg=done");
		}
		catch(Exception e)
	    {
	    	response.sendRedirect("EditAllProduct.jsp?msg=wrong");
	    	System.out.println(e);
	    }
	}

}
