package orientalClinic.model;

public class CartItem {
	
	//---------------------------------CartItemFieldSectionStart---------------------------------
	
	private Herbology herb;
	int herbId;
	private int quantity;
	
	// CartItem Constructor
	public CartItem(Herbology herb) {
		this.herb = herb;
		this.herbId = herb.getHerbId();
		this.quantity = 1;
	}

	//---------------------------------CartItemFieldSectionEnd-----------------------------------
	
	
	
	//---------------------------------GetterSectionStart----------------------------------------
	
	public Herbology getHerb() {
		return herb;
	}

	public void setHerb(Herbology herb) {
		this.herb = herb;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	
	public int getPrice() {
		return this.quantity * herb.getPrice();
	}
	
	//---------------------------------GetterSectionEnd------------------------------------------
	
	
	//---------------------------------OtherMethodSectionStart-----------------------------------
	
	@Override
	public String toString() {
		return herb.getHerbId() + ", " + herb.getName() + ", " + herb.getOrigin() +
				", " + quantity + "개, " + herb.getPrice() + "원";
	}
	
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	//---------------------------------OtherMethodSectionEnd-------------------------------------
}