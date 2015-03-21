package Controllers;

import java.util.ArrayList;

import Helpers.StringHelper;
import Models.Manufacturer;
import Models.Product;

public class ManufacturerController {
	
	private ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();

	public ManufacturerController() {
		
	}
	
	public void placeProductInManufacturer(Product p) {
		String manufacturer = p.getManufacturer();
	}
}
