package orientalClinic.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HerbologyStorage {
	
	//---------------------------------StorageFieldSectionStart------------------------------------
	
	ArrayList<Herbology> herbList = new ArrayList<>();
	final int MAX_QUANTITY = 20;
	private String herbologyFilename = "herbList.txt";
	private int lastId;
	private boolean isSaved;
	
	public HerbologyStorage()throws IOException{
		loadHerbListFromFile();
		generateLastId();
		isSaved = true;
	}
	
	//---------------------------------StorageFieldSectionEnd--------------------------------------
	
	
	
	//---------------------------------FunctionalMethodSectionStart--------------------------------
	
	private void generateLastId(){
		lastId = 0;
		for(Herbology herb : herbList) {
			int id = herb.getHerbId();
			if(id > lastId) {
				lastId = id;
			}
		}
	}
	
	private void loadHerbListFromFile() throws IOException{
		FileReader fr;
		try {
			fr = new FileReader(herbologyFilename);
			BufferedReader br = new BufferedReader(fr);
			String idStr;
			while((idStr = br.readLine()) != null && !idStr.equals("")) {
				int id = Integer.parseInt(idStr);
				String name = br.readLine();
				String origin = br.readLine();
				int price = Integer.parseInt(br.readLine());
				herbList.add(new Herbology(id, name, origin, price));
			}
			fr.close();
			br.close();
		}catch(FileNotFoundException | NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean isValidHerb(int herbId) {
		for(Herbology herb : herbList) {
			if(herb.getHerbId() == herbId) {return true;}
		}
		return false;
	}
	
	
	public Herbology getHerbById(int herbId) {
		for (Herbology herb : herbList) {
			if (herb.getHerbId() == herbId)
				return herb;
		}
		return null;
	}
	
	public void addHerb(String name, String origin, int price) {
		
		Herbology herb = new Herbology(++lastId, name, origin, price);
		herbList.add(herb);
		isSaved = false;
	}
	
	public void deleteItem(int herbId) {
		herbList.remove(getHerbById(herbId));
		isSaved = false;
	}
	
	public void saveHerbListToFile() {
		try {
			FileWriter fw = new FileWriter(herbologyFilename);
			for (Herbology herb : herbList) {
				fw.write(herb.getHerbId() + "\n");
				fw.write(herb.getName() + "\n");
				fw.write(herb.getOrigin() + "\n");
				fw.write(herb.getPrice() + "\n");
			}
			fw.close();
			isSaved = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//---------------------------------FunctionalMethodSectionEnd----------------------------------
	
	
	
	//---------------------------------NormalMethodSectionStart------------------------------------
	
	public int getNumHerbs() {
		return herbList.size();
	}
	
	public String getHerbInfo(int i) {
		return herbList.get(i).toString();
	}

	public int getMaxQuantity() {
		return MAX_QUANTITY;
	}
	
	public boolean isEmpty() {
		return herbList.size() == 0;
	}
	
	public boolean isSaved() {
		return isSaved;
	}
	
	//---------------------------------NormalMethodSectionEnd--------------------------------------
	
}