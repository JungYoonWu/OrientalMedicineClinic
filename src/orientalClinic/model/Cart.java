package orientalClinic.model;

import java.util.ArrayList;

public class Cart {
	
	//---------------------------------CartFieldSectionStart--------------------------------------
	
	private ArrayList<CartItem> itemList = new ArrayList<>();
	
	//---------------------------------CartFielSectiondEnd----------------------------------------
	
	
	
	//---------------------------------FunctionalMethodSectionStart-------------------------------
	
	public void addItem(Herbology herb) {
		CartItem item = getCartItem(herb);
		if(item == null) {
			itemList.add(new CartItem(herb));
		}else {
			item.addQuantity(1);
		}
	}
	
	//getCartItem Overloading
	private CartItem getCartItem(Herbology herb) {
		for(CartItem item : itemList) {
			if(item.getHerb() == herb) {
				return item;
			}
		}
		return null;
	}
	
	//getCartItem Overloading
	private CartItem getCartItem(int herbId) {
		for(CartItem item : itemList) {
			if(item.herbId == herbId) {
				return item;
			}
		}
		return null;
	}
	
	public String getItemInfo(int index) {
		return itemList.get(index).toString();
	}
	
	public boolean isValidHerb(int herbId) {
		for(CartItem item : itemList) {
			if(item.getHerb().getHerbId() == herbId) {
				return true;
			}
		}
		return false;
	}
	
	public void updateQuantity(int herbId, int quantity) {
		if(quantity == 0) {
			deleteItem(herbId);
		}else {
			CartItem item = getCartItem(herbId);
			item.setQuantity(quantity);
		}
	}
	
	public int getTotalPrice() {
		int totalPrice = 0;
		for(CartItem item : itemList) {
			totalPrice += item.getPrice();
		}
		return totalPrice;
	}
	
	//---------------------------------FunctionalMethodSectionEnd---------------------------------
	
	
	
	//---------------------------------NormalMethodSectionStart-----------------------------------
	
	public ArrayList<CartItem> getItemList(){
		return itemList;
	}
	
	public boolean isEmpty() {
		return itemList.isEmpty();
	}
	
	public int getNumItems() {
		return itemList.size();
	}
	
	public void resetCart() {
		itemList.clear();
	}
	
	public void deleteItem(int herbId) {
		CartItem item = getCartItem(herbId);
		itemList.remove(item);
	}

	//---------------------------------NormalMethodSectionEnd-------------------------------------
	
}