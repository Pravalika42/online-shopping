package myservlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.MySqlConnection;
import model.*;
import dao.*;
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public RemoveFromCartServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getSession().getAttribute("email").toString();
    	int id = Integer.parseInt(request.getParameter("id"));
    	int productQuantity = 0;
    	int cartQuantity=0;
    	try {
    		ProductDao pDao = new ProductDao(MySqlConnection.getConnection());
			Product product = pDao.getSingleProduct(id);
			productQuantity = product.getQuantity();
			
			CartDao cDao = new CartDao(MySqlConnection.getConnection());
			Cart cProduct = cDao.getSingleCartProduct(email, id);
			cartQuantity = cProduct.getQuantity();
			
			productQuantity = productQuantity + cartQuantity;
			product.setQuantity(productQuantity);
			pDao.updateQuantity(id, productQuantity);
			cDao.removeFromCart(email, id);
			response.sendRedirect("MyCart.jsp?msg=removed");
    	}
    	catch(Exception e)
	    {
	    	System.out.println(e);
	    }
	}

	
}
