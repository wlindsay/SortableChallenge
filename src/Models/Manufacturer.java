package Models;

import java.util.ArrayList;

public class Manufacturer {

	private ArrayList<Product> products = new ArrayList<Product>();
	private String name;
	
	public Manufacturer(String name) {
		this.name = name;
	}
	
	public void addProduct(Product p) {
		products.add(p);
	}
}
