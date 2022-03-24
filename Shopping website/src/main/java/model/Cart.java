package model;

public class Cart {
	private String email;
	private int productId;
	private String productName;
	private int quantity;
	private int price;
	private int total;
	private String category;

	public Cart() {
		// TODO Auto-generated constructor stub
	}
	
	public Cart(String email, int productId, String productName, int quantity, int price, int total, String category) {
		super();
		this.email = email;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.category = category;
	}

	public Cart(int quantity) {
		super();
		this.quantity = quantity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
