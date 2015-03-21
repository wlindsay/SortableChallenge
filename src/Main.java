import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import Controllers.ManufacturerController;
import Models.Listing;
import Models.Manufacturer;
import Models.Product;
import Models.ProductMin;

import com.google.gson.Gson;


public class Main {
	
	private ManufacturerController manufacturerController = new ManufacturerController();
	
	public Main(String productsFilename, String listingsFileName) {
		try {
			parseProducts(productsFilename);
			parseListings(listingsFileName);
			outputResult();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Will parse the JSON products page and cluster the different manufacturers together
	 * 
	 * @throws IOException
	 */
	private void parseProducts(String filename) throws IOException {
		FileInputStream fis;
		
		try {
			fis = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			System.out.println("The input products file was not found.");
			return;
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		Gson gson = new Gson();
		
		String line = null;
		while ((line = br.readLine()) != null) {
			Product p = gson.fromJson(line, Product.class);
			manufacturerController.placeProductInManufacturer(p);
		}
		
		br.close();
		fis.close();
	}
	
	/**
	 * Will parse the JSON input listings file and determine to which product a listing belongs to
	 * 
	 * @param filename
	 * @throws IOException
	 */
	private void parseListings(String filename) throws IOException {
		FileInputStream fis;
		
		try {
			fis = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			System.out.println("The input listings file was not found.");
			return;
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		Gson gson = new Gson();
		
		String line = null;
		while ((line = br.readLine()) != null) {
			Listing l = gson.fromJson(line, Listing.class);
			Manufacturer man = manufacturerController.findManufacturerOfListing(l);
			
			if (man != null) {
				man.assignListingToProduct(l);
			}
		}
		
		br.close();
		fis.close();
	}
	
	private void outputResult() throws IOException {
		PrintWriter writer = new PrintWriter("result.json", "UTF-8");
		Gson gson = new Gson();
		int numProducts = 0;
		
		writer.print("[");
		
		for (Manufacturer man : manufacturerController.getManufacturers()) {
			numProducts = man.getProducts().size();
			int curProduct = 1;
			
			for (Product p : man.getProducts()) {
				writer.print(gson.toJson(p, ProductMin.class));
				
				if (curProduct != numProducts) {
					writer.println(",");
				}
				
				curProduct++;
			}
		}
		
		writer.print("]");
		writer.close();
	}

	public static void main(String[] args) {
		new Main("data/products.json", "data/listings.json");
	}
}
