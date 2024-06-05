package orientalClinic.model;

import java.util.ArrayList;

public class HerbologyStorage {
	
	ArrayList<Herbology> herbList = new ArrayList<>();
	final int MAX_QUANTITY = 20;
	
	public int getNumHerbs() {
		return herbList.size();
	}
	
	public String getHerbInfo(int i) {
		return herbList.get(i).toString();
	}
	
	public boolean isValidHerb(int herbId) {
		for(Herbology herb : herbList) {
			if(herb.getHerbId() == herbId) {return true;}
		}
		return false;
	}

	public int getMaxQuantity() {
		return MAX_QUANTITY;
	}

	public Herbology getHerbId(int herbId) {
		for (Herbology herb : herbList) {
			if (herb.getHerbId() == herbId)
				return herb;
		}
		return null;
	}
}
