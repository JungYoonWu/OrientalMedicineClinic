package orientalClinic.model;

import java.util.ArrayList;



public class Cart {

	private ArrayList<CartItem> itemList = new ArrayList<>();
	
	public ArrayList<CartItem> getItemList(){
		return itemList;
	}
	
	public boolean isEmpty() {
		return itemList.isEmpty();
	}
	
	public int getNumItems() {
		return itemList.size();
	}
	
	public String getItemInfo(int index) {
		return itemList.get(index).toString();
	}
	
	public void addItem(Herbology herb) {
		CartItem item = getCartItem(herb);
		if(item == null) {
			itemList.add(new CartItem(herb));
		}else {
			item.addQuantity(1);
		}
	}
	
	private CartItem getCartItem(Herbology herb) {
		for(CartItem item : itemList) {
			if(item.getHerb() == herb) {
				return item;
			}
		}
		return null;
	}
	
	public void resetCart() {
		itemList.clear();
	}
	
	public boolean isValidHerb(int herbId) {
		for(CartItem item : itemList) {
			if(item.getHerb().getHerbId() == herbId) {
				return true;
			}
		}
		return false;
	}


	public void deleteItem(int herbId) {
		CartItem item = getCartItem(herbId);
		itemList.remove(item);
	}

	private CartItem getCartItem(int herbId) {
		for(CartItem item : itemList) {
			if(item.getHerb().getHerbId() == herbId) {
				return item;
			}
		}
		return null;
	}

	public void updateQuantity(int bookId, int quantity) {
		if(quantity == 0) {
			deleteItem(bookId);
		}else {
			CartItem item = getCartItem(bookId);
			item.setQuantity(quantity);
		}
	}
}
