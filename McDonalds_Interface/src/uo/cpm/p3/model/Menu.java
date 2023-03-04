package uo.cpm.p3.model;

import java.util.*;

import uo.cpm.p3.util.FileUtil;

public class Menu {
	
	private static final String PRODUCTS_FILE = "files/products.dat";
	private List<Product> productsList = null;
	private List<Product> productsByType= null;
	
	public Menu(){
		productsList = new ArrayList<Product>();
		loadProducts();
	}

	private void loadProducts(){
		FileUtil.loadFile (PRODUCTS_FILE, productsList);
	  }

	public Product[] getProducts(){
		Product[] products = productsList.toArray(new Product[productsList.size()]);
		return products;	
	}
	
	
	public Product[] getProductsByType(String type) {
		productsByType= new ArrayList<Product>();
		for(Product p:productsList) {
			if(p.getType().equals(type)) {
				productsByType.add(p);
			}
		}
		Product[] productsType=productsByType.toArray(new Product[productsByType.size()]);
		return productsType;
	}
	
}
