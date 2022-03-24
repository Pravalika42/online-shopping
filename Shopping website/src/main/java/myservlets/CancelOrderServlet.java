package myservlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.MySqlConnection;
import model.*;
import dao.*;
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public CancelOrderServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String email=request.getParameter("email");
		int productId = Integer.parseInt(request.getParameter("productid"));
		String status="cancel";
		int productQuantity = 0;
		int cartQuantity = 0;
		
		try {
    		ProductDao pDao = new ProductDao(MySqlConnection.getConnection());
			Product product = pDao.getSingleProduct(productId);
			productQuantity = product.getQuantity();
			
			OrderDao oDao = new OrderDao(MySqlConnection.getConnection());
			Order oProduct = oDao.getSingleOrder(id, email);
			cartQuantity = oProduct.getQuantity();
			
			productQuantity = productQuantity + cartQuantity;
			product.setQuantity(productQuantity);
			pDao.updateQuantity(productId, productQuantity);
			
			oDao.updateStatus(email, id, status);
			response.sendRedirect("MyOrders.jsp?msg=cancel");
		}
    	catch(Exception e)
	    {
    		response.sendRedirect("MyOrders.jsp?msg=wrong");
	    	System.out.println(e);
	    }
	}
		

	
}
