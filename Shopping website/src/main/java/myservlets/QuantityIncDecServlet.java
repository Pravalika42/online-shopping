package myservlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.MySqlConnection;
import model.*;
import dao.*;

public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public QuantityIncDecServlet() {
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String email = request.getSession().getAttribute("email").toString();
    	int id = Integer.parseInt(request.getParameter("id"));
    	String incdec = request.getParameter("quantity");
    	int productQuantity = 0;
    	int price=0;
    	int total=0;
    	int cartQuantity=0;
    	try {
    		ProductDao pDao = new ProductDao(MySqlConnection.getConnection());
			Product product = pDao.getSingleProduct(id);
			productQuantity = product.getQuantity();
			
			CartDao cDao = new CartDao(MySqlConnection.getConnection());
			Cart cProduct = cDao.getSingleCartProduct(email, id);
			price=cProduct.getPrice();
			total = cProduct.getTotal();
			cartQuantity = cProduct.getQuantity();
			if(cartQuantity<=productQuantity){
				if(cartQuantity==1 && incdec.equals("dec")){
					response.sendRedirect("MyCart.jsp?msg=notpossible");
				}
				else if(cartQuantity !=1 && incdec.equals("dec")){
					total = total-price;
					cartQuantity--;
					cProduct.setQuantity(cartQuantity);
					productQuantity++;
					product.setQuantity(productQuantity);
					cDao.updateCartProductQuantity(id, cartQuantity, total, email);
					pDao.updateQuantity(id, productQuantity);
					response.sendRedirect("MyCart.jsp?msg=dec");
				}
				else {
					total = total+price;
					cartQuantity++;
					cProduct.setQuantity(cartQuantity);
					productQuantity--;
					product.setQuantity(productQuantity);
					cDao.updateCartProductQuantity(id, cartQuantity, total, email);
					pDao.updateQuantity(id, productQuantity);
					response.sendRedirect("MyCart.jsp?msg=inc");
				}
			}
    	}
    	catch(Exception e)
	    {
	    	System.out.println(e);
	    }
    }
}
