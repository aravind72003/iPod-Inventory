package IPodInventory;

public class Inventory {
	
	private int stock;
	private int pricePerUnit;
	
	public Inventory(int stock,int pricePerUnit) {
		this.stock = stock;
		this.pricePerUnit = pricePerUnit;
	}
	
	public int get_stock() {
		return stock;
	}
	
	public int getPricePerUnit() {
		return pricePerUnit;
	}
	
	public void reduceStock(int quantity) {
		stock -= quantity;
	}
	
}
