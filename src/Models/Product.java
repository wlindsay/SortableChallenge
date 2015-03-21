package Models;

import java.util.ArrayList;

public class Product {
	private String product_name;
	private String manufacturer;
	private String model;
	
	private ArrayList<Listing> listings = new ArrayList<Listing>();
	
	public Product() {
		
	}
	
	public String getProductName() {
		return product_name;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public String getModel() {
		return model;
	}
}
