package orientalClinic.model;

public class CartItem {
	private Herbology herb;
	private int quantity;
	
	public CartItem(Herbology herb) {
		this.herb = herb;
		this.quantity = 1;
	}

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
	
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	@Override
	public String toString() {
		return herb.getHerbId() + ", " + herb.getName() + ", " + herb.getOrigin() +
				", " + quantity + "개, " + herb.getPrice() + "원";
	}
	
	public int getPrice() {
		return this.quantity * herb.getPrice();
	}
}
