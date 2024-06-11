package orientalClinic.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReservationCalendar {

	//---------------------------------RezCalendarFieldSectionStart------------------------------
	
	private ArrayList<Reservation> reservations;
	private String rezFileName = "reservation.txt";
	private int lastId;
	private boolean isSaved;
	
	public ReservationCalendar()throws IOException{
		reservations = new ArrayList<>();
		loadRezListFromFile();
		generateLastId();
		isSaved = true;
	}
	
	//---------------------------------RezCalendarFieldSectionEnd--------------------------------
	
	
	
	
	//---------------------------------FunctionalMethodSectionStart------------------------------
	
	private void generateLastId() {
		lastId = 0;
		for(Reservation rez : reservations) {
			int id = rez.getRezId();
			if(id > lastId) {
				lastId = id;
			}
		}
	}
	
	private void loadRezListFromFile() throws IOException{
		FileReader fr;
		try {
			fr = new FileReader(rezFileName);
			BufferedReader br = new BufferedReader(fr);
			String idStr;
			while((idStr = br.readLine()) != null&&!idStr.equals("")) {
				String date = br.readLine();
				String time = br.readLine();
				String cusName = br.readLine();
				String cusPhone = br.readLine();
				reservations.add(new Reservation(date, time, cusName, cusPhone));
			}
			fr.close();
			br.close();
		}catch(FileNotFoundException | NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void saveRezListToFile() {
		try {
			FileWriter fw = new FileWriter(rezFileName);
			for(Reservation rez : reservations) {
				fw.write(rez.getRezId() + "\n");
				fw.write(rez.getDate() + "\n");
				fw.write(rez.getTime() + "\n");
				fw.write(rez.getCusName() + "\n");
				fw.write(rez.getCusPhone() + "\n");
			}
			fw.close();
			isSaved = true;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	 public void addReservation(Reservation reservation) throws IOException {
	        reservations.add(reservation);
	        saveRezListToFile();
	  }

	 public void removeReservation(String date, String time, String cusName, String cusPhone) throws IOException {
	     reservations.removeIf(r ->r.getDate().equals(date) && r.getTime().equals(time) &&
	                                 r.getCusName().equals(cusName) && r.getCusPhone().equals(cusPhone));
	     saveRezListToFile();
	 }
	
	
	//---------------------------------FunctionalMethodSectionEnd--------------------------------



	//---------------------------------NormalMethodSectionStart------------------------------------
	
	public ArrayList<Reservation> getReservations(){
		return reservations;
	}
	
	public boolean isEmpty() {
		return reservations.size() == 0;
	}
	
	public boolean isSaved() {
		return isSaved;
	}
	
	//---------------------------------NormalMethodSectionEnd--------------------------------------

}