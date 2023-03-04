package uo.cpm.p3.model;

import java.util.*;

import uo.cpm.p3.util.FileUtil;

public class Order {
	
	private List<Product> orderList = null;
	private String typeOfOrder= "";
	private String code="  ";
	private boolean ifDiscount;
	
	public Order(){
		orderList = new ArrayList<Product>();
		generateCode();
	}

	public void add(Product item, int units){
		Product itemInOrder = null;
	
		for (Product a : orderList){
			if (a.getCode().equals(item.getCode()))
			{
				itemInOrder = a;
				itemInOrder.setUnits(itemInOrder.getUnits()+units);
				break;
			}
		}
		
		if (itemInOrder == null){
			Product itemToOrder = new Product(item);
			itemToOrder.setUnits(units);
			orderList.add(itemToOrder);
		}
	}
	
	public void setTypeOfOrder(String typeOrder) {
		this.typeOfOrder=typeOrder;
	}
	public String getTypeOfOrder() {
		return typeOfOrder;
	}

	public String getCode() {
		return code;
	}

	public float getPrice(){
		float total = 0.0f;
		for (Product a : orderList){
			
			total += a.getPrice()* a.getUnits();
			ifDiscount=false;
		}
		if(total>=60) {
			ifDiscount=true;
			total*=0.9f;
		}
		
		return total;
	}
	
	public boolean getIfDiscount() {
		return ifDiscount;
	}
	
	public void saveOrder(){
		FileUtil.saveToFile(code, orderList,typeOfOrder);
	  }

	public void initialize(){
		orderList.clear();
	}
	
	private void generateCode() {
		String base = "0123456789abcdefghijklmnopqrstuvwxyz";
		int longitudCodigo = 8;
		for(int i=0; i<longitudCodigo;i++){ 
			int numero = (int)(Math.random()*(base.length())); 
			code += base.charAt(numero);
		}
	}
	
	public int getProductUnits1(Product p) {
		int units=0;
		for (Product a : orderList){
			if(a.getName().equals(p.getName())) {
				units=a.getUnits();
			}
		}return units;
	}



	public void remove(Product item, int units){
		Product itemInOrder = null;
	
		for (Product a : orderList){
			if (a.getCode().equals(item.getCode()))
			{
				if(a.getUnits()>=units) {
				itemInOrder = a;
				itemInOrder.setUnits(itemInOrder.getUnits()-units);
				}
				else {
					itemInOrder.setUnits(0);
				}
				
				if (itemInOrder.getUnits()==0){
					orderList.remove(itemInOrder);
					
				}
				break;
			}
			

			
		}
	}
	
	public String orderToString() {
		StringBuffer buffer = new StringBuffer();
		
		for (Product a : orderList) {
			//buffer.append(a.toString());
			buffer.append(a.getName());
			buffer.append(" - ");
			buffer.append(a.getUnits());
			buffer.append(" uds ");
			buffer.append("\n");
		   }
		   
		buffer.append("Total: ");
		buffer.append(String.format("%.2f", getPrice()));
		buffer.append(" €");
		return buffer.toString();
		}
	

}
