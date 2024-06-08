package orientalClinic.model;

public class Reservation {
	//---------------------------------HerbologyFieldSectionStart------------------------------
	private String date;
	private String time;
	private String cusName;
	private String cusPhone;
	private int rezId;
	private static int counter = 0;
	
	public Reservation(String date, String time, String cusName, String cusPhone) {
		this.rezId = ++counter;
		this.date = date;
		this.time = time;
		this.cusName = cusName;
		this.cusPhone = cusPhone;
	}
	//---------------------------------HerbologyFieldSectionEnd---------------------------------

	
	
	
	//---------------------------------GetterSectionStart---------------------------------------
	
	public int getRezId() {
		return rezId;
	}
	
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
		return "예약정보 : " + date + ", " + time + ", " + cusName + ", " + cusPhone;
	}
	
	//---------------------------------OtherMethodSectionEnd------------------------------------
}
