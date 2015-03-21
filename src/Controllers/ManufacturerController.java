package Controllers;

import java.util.ArrayList;

import Helpers.StringHelper;
import Models.Listing;
import Models.Manufacturer;
import Models.Product;

public class ManufacturerController {
	
	private ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();

	public ManufacturerController() {
		
	}
	
	public void placeProductInManufacturer(Product p) {
		boolean productAdded = false;
		
		for (Manufacturer man : manufacturers) {
			if (StringHelper.areStringsSimilar(man.getName(), p.getManufacturer())) {
				man.addProduct(p);
				productAdded = true;
				break;
			}
		}
		
		if (!productAdded) {
			Manufacturer man = new Manufacturer(p.getManufacturer());
			man.addProduct(p);
			manufacturers.add(man);
		}
	}
	
	public Manufacturer findManufacturerOfListing(Listing l) {
		for (Manufacturer man : manufacturers) {
			if (StringHelper.areStringsSimilar(man.getName(), l.getManufacturer())) {
				return man;
			}
		}
		
		return null;
	}
	
	public ArrayList<Manufacturer> getManufacturers() {
		return manufacturers;
	}
}
