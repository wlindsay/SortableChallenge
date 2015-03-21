import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import Models.Product;

import com.google.gson.Gson;


public class Main {
	
	public Main() {
		try {
			parseProducts();
		} catch (FileNotFoundException e) {
			// TODO Print file not found exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Will parse the JSON products page and cluster the different manufacturers together
	 * 
	 * @throws IOException
	 */
	private void parseProducts() throws IOException {
		FileInputStream fis = new FileInputStream("data/products.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		Gson gson = new Gson();
		
		String line = null;
		while ((line = br.readLine()) != null) {
			Product p = gson.fromJson(line, Product.class);
			int x = 0;
		}
		
		br.close();
		fis.close();
	}

	public static void main(String[] args) {
		new Main();
	}
}
