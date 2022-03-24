package model;

public class Order extends Cart {
	private String userName;
	private String address;
	private String city;
	private String state;
	private String country;
	private String mobile;
	private String orderDate;
	private String deliveryDate;
	private String paymentMethod;
	private String transactId;
	private String status;
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(String email, int productId, String productName, int quantity, int price, int total, String category) {
		super(email, productId, productName, quantity, price, total, category);
		// TODO Auto-generated constructor stub
	}
	public Order(String userName, String address, String city, String state, String country, String mobile,
			String orderDate, String deliveryDate, String paymentMethod, String transactId, String status) {
		super();
		this.userName = userName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.mobile = mobile;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.paymentMethod = paymentMethod;
		this.transactId = transactId;
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getTransactId() {
		return transactId;
	}
	public void setTransactId(String transactId) {
		this.transactId = transactId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
