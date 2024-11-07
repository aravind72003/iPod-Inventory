package IPodInventory;
import java.util.*;
public class Main {
	
	private static final int SHIPPING_COST_PER_10 = 400;
	
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		
		while(flag) {
			Inventory brazilInventory = new Inventory(100,100);
			Inventory argentinaInventory = new Inventory(100,50);
			
			System.out.println("CHOOSE AN OPTION: ");
			System.out.println("1. PLACE AN ORDER \n2. EXIT");
			int choice = sc.nextInt();
			
			switch(choice) {				
				case 1:
					System.out.println("ENTER COUNTRY: ");
					String country = sc.next();
					
					System.out.println("ENTER IPOD REQUIRED: ");
					int ipodRequired = sc.nextInt();
					
					Order order = new Order(country,ipodRequired);
					int bestPrice = bestPrice(order,brazilInventory,argentinaInventory);
					
					System.out.println();
				
					System.out.println("Best Price: $"+bestPrice);
					System.out.println("Remaining iPods in Brazil: "+brazilInventory.get_stock());
					System.out.println("Remaining iPods in Argentina: "+argentinaInventory.get_stock());
						
					System.out.println();
					break;
				
				case 2:
					flag = false;
					break;
			} 
		} while(flag);
	}
	
	private static int bestPrice(Order order, Inventory brazilInventory, Inventory argentinaInventory) {
		int quantity = order.getQuantity();
		int bestPrice = 0;
		
		if(order.getCountry().equalsIgnoreCase("brazil")) {
			
			int ipodsFromBrazil = Math.min(quantity, brazilInventory.get_stock());
			int ipodsFromArgentina = quantity - ipodsFromBrazil;
			
			if(ipodsFromArgentina > argentinaInventory.get_stock()) {
				System.out.println("OUT OF STOCK");
				return 0;
			}
			
			if(ipodsFromArgentina % 10 != 0) {
				System.out.println("IPODS TO BE TRANSPORTED SHOULD BE MINIMUM OF 10");
				return 0;
			}
			
			bestPrice = ipodsFromArgentina * argentinaInventory.getPricePerUnit() +
					ipodsFromBrazil * brazilInventory.getPricePerUnit() +
					(ipodsFromArgentina / 10) * SHIPPING_COST_PER_10;
			
			argentinaInventory.reduceStock(ipodsFromArgentina);
			brazilInventory.reduceStock(ipodsFromBrazil);
		}
		
		else if(order.getCountry().equalsIgnoreCase("argentina")) {
			
			int ipodsFromArgentina = Math.min(quantity, argentinaInventory.get_stock());
			int ipodsFromBrazil = quantity - ipodsFromArgentina;
			
			if(ipodsFromBrazil > brazilInventory.get_stock()) {
				System.out.println("OUT OF STOCK");
				return 0;
			}
			
			if(ipodsFromBrazil % 10 != 0) {
				System.out.println("IPODS TO BE TRANSPORTED SHOULD BE MINIMUM OF 10");
				return 0;
			}
			
			bestPrice = ipodsFromArgentina * argentinaInventory.getPricePerUnit() +
					ipodsFromBrazil * brazilInventory.getPricePerUnit() + 
					(ipodsFromBrazil / 10) * SHIPPING_COST_PER_10;
			
			argentinaInventory.reduceStock(ipodsFromArgentina);
			brazilInventory.reduceStock(ipodsFromBrazil);
		}
		
		else {
			System.out.println("INVALID COUNTRY. PLEASE ENTER 'BRAZIL' OR 'ARGENTINA'.");
		}
		
		return bestPrice;
	}
}
