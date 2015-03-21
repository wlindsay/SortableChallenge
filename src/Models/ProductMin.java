package Models;

import java.util.ArrayList;

public class ProductMin {
	private String product_name;
	
	private ArrayList<Listing> listings = new ArrayList<Listing>();
	
	public ProductMin() {
		
	}
	
	public String getProductName() {
		return product_name;
	}
	
	public void addListing(Listing l) {
		listings.add(l);
	}
}
