package IPodInventory;

public class Order {
	
	private String country;
	private int quantity;
	
	public Order(String country,int quantity) {
		this.country = country;
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getCountry() {
		return country;
	}
}
