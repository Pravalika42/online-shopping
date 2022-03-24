package myservlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.MySqlConnection;
import model.*;
import dao.*;

public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddToCartServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getSession().getAttribute("email").toString();
		int productId = Integer.parseInt(request.getParameter("id"));
		int quantity =1;
		try {
			ProductDao pDao = new ProductDao(MySqlConnection.getConnection());
			Product product = pDao.getSingleProduct(productId);
			CartDao cDao = new CartDao(MySqlConnection.getConnection());
			Cart cProduct = cDao.getSingleCartProduct(email, productId);
			if(cProduct!=null) {
				response.sendRedirect("UserHome.jsp?msg=exist");
			}
			else {
				Cart cartProduct = new Cart();
				cartProduct.setProductName(product.getName());
				cartProduct.setEmail(email);
				cartProduct.setCategory(product.getCategory());
				cartProduct.setPrice(product.getPrice());
				cartProduct.setQuantity(quantity);
				cartProduct.setTotal(product.getPrice());
				cartProduct.setProductId(productId);
				cDao.addToCart(cartProduct);
				response.sendRedirect("UserHome.jsp?msg=added");
			}
		}
		catch(Exception e)
	    {
	    	response.sendRedirect("UserHome.jsp?msg=invalid");
	    	System.out.println(e);
	    }
		
	}

	
}
