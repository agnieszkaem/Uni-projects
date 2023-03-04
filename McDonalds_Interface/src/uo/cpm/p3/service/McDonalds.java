package uo.cpm.p3.service;

import uo.cpm.p3.model.Menu;
import uo.cpm.p3.model.Order;
import uo.cpm.p3.model.Product;

public class McDonalds {

	Menu menu = new Menu();
	Order order = new Order();
	
	public McDonalds() {
		
		
	}
	

	public Product[] getMenuProducts()
	{
		return menu.getProducts();
	}
	
	public Product[] getProductsByType(String type) {
	return menu.getProductsByType(type);
	}
	
	public String getOrderList() {
		return order.orderToString();
		
	}
	public void initOrder()
	{
		order.initialize();
	}
	
	public String getOrderCode()
	{
		return order.getCode();
	}
	
	public void addToOrder ( Product p, Integer units )
	{
		order.add(p, units);
	}
	
	public void removeFromOrder(Product p, Integer units ) {
		order.remove(p, units);
	}
	
	
	public void saveOrder()
	{
		order.saveOrder();
	}

	public Object getOrderTotal() {
		return order.getPrice();
	}
	
	public boolean discount() {
		return order.getIfDiscount();
	}
	
	public int getNumOfProduct(Product item) {
		return order.getProductUnits1(item);
	}

	public void setTypeToOrder(String type) {
		order.setTypeOfOrder(type);
	}
	

}

