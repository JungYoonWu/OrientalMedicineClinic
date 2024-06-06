package orientalClinic.model;

public class Reservation {
	//---------------------------------HerbologyFieldSectionStart------------------------------
	private String date;
	private String time;
	private String cusName;
	private String cusPhone;
	
	public Reservation(String date, String time, String cusName, String cusPhone) {
		this.date = date;
		this.time = time;
		this.cusName = cusName;
		this.cusPhone = cusPhone;
	}
	//---------------------------------HerbologyFieldSectionEnd---------------------------------

	
	
	
	//---------------------------------GetterSectionStart---------------------------------------
	
	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public String getCusName() {
		return cusName;
	}

	public String getCusPhone() {
		return cusPhone;
	}
	
	
	//---------------------------------GetterSectionEnd-----------------------------------------
	
	
	
	//---------------------------------OtherMethodSectionStart----------------------------------
	
	@Override
	public String toString() {
		return date + ", " + time + ", " + cusName + ", " + cusPhone;
	}
	
	//---------------------------------OtherMethodSectionEnd------------------------------------
}
