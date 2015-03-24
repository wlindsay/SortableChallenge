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
			System.out.println("Parsing products.");
			parseProducts(productsFilename);
			
			System.out.println("Associating listings to products.");
			parseListings(listingsFileName);
			
			outputResult();
			System.out.println("Done! Results are in a file called results.json in this project's directory.");
		} catch (IOException e) {
			System.out.println("Something went wrong!");
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
			System.out.println("Error: The input products file was not found.");
			throw(e);
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
			System.out.println("Error: The input listings file was not found.");
			throw(e);
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
		PrintWriter writer = new PrintWriter("result.txt", "UTF-8");
		Gson gson = new Gson();
		
		for (Manufacturer man : manufacturerController.getManufacturers()) {
			for (Product p : man.getProducts()) {
				writer.println(gson.toJson(p, ProductMin.class));
			}
		}

		writer.close();
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Error: Missing parameters. The command should look as follows: java -jar main.jar <products JSON file> <listings JSON file>");
		} else {
			new Main(args[0], args[1]);
		}
	}
}
