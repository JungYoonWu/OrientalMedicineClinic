package orientalClinic.model;

public class Herbology {
	private int herbId;
	private String name;
	private String origin;
	private int price;
	private int inStock;
	
	public Herbology(int herbId, String name, String origin, int price) {
		this.herbId = herbId;
		this.name = name;
		this.origin = origin;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return herbId + ", " + name + ", " + origin + ", " + price + "원";
	}
	
	
	public int getHerbId() {
		return herbId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public int getPrice() {
		return price;
	}

}
