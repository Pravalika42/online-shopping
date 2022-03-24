package myservlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.MySqlConnection;
import model.*;
import dao.*;
public class AddNewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public AddNewProductServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String category=request.getParameter("category");
		int price=Integer.parseInt(request.getParameter("price"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		String image = request.getParameter("image");
		try {
			ProductDao pDao = new ProductDao(MySqlConnection.getConnection());
		    Product product = new Product();
		    product.setId(id);
		    product.setName(name);
		    product.setCategory(category);
		    product.setPrice(price);
		    product.setQuantity(quantity);
		    product.setImage(image);
		    pDao.insertProduct(product);
		    response.sendRedirect("AddNewProduct.jsp?msg=done");
		}
		 catch(Exception e)
	    {
	    	response.sendRedirect("AddNewProduct.jsp?msg=wrong");
	    	System.out.println(e);
	    }

	}
	
	
	
	
}
