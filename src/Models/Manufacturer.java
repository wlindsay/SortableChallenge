package Models;

import java.util.ArrayList;

import Helpers.StringHelper;

public class Manufacturer {

	private ArrayList<Product> products = new ArrayList<Product>();
	private String name;
	
	public Manufacturer(String name) {
		this.name = name;
	}
	
	public void addProduct(Product p) {
		products.add(p);
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Will attempt to find the product which the listing talks about. If it is found the listing will be added to the product.
	 * 
	 * @param l - listing
	 * @return boolean - true if found, false otherwise
	 */
	public boolean assignListingToProduct(Listing l) {
		for (Product p : products) {
			if (StringHelper.contains(l.getTitle(), p.getModel())) {
				p.addListing(l);
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
}
