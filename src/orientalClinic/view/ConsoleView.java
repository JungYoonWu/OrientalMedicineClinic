package orientalClinic.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import orientalClinic.model.Cart;
import orientalClinic.model.HerbologyStorage;
import orientalClinic.model.Reservation;
import orientalClinic.model.ReservationCalendar;
import orientalClinic.model.user.Customer;

public class ConsoleView {

	//----------------------------CommonMethodSectionStart---------------------------------------
	
	//매개변수로 받은 문자열을 출력하는 method
	public void showMessage(String message) {
		System.out.println(message);
	}
	
	//최종 확인으로 승인하는 method
	public boolean askConfirm(String message, String yes) {
		System.out.println(message);
		
		Scanner input = new Scanner(System.in);
		if(input.nextLine().equals(yes)) {
			return true;
		}else {
			return false;
		}
	}
	
	//숫자입력받는 method
	private int inputNumberWithValidation() {
		Scanner input = new Scanner(System.in);
		
		try {
			int number = input.nextInt();
			return number;
		} catch (Exception e) {
			System.out.print("숫자를 입력하세요 :");
			return inputNumberWithValidation();
		}
	}
	
	//파라미터로 전달받은 문자열을 출력후 거기에 맞는 문자열을 입력받는 method
	public String inputString(String message) {
		Scanner input = new Scanner(System.in);
		System.out.print(message);
		return input.nextLine();
	}
	
	public int readNumber(String message) {
		if (message != null || !message.equals(""))
			System.out.print(message);
		
		Scanner input = new Scanner(System.in);
		try {
			int number = input.nextInt();
			return number;
		} catch (Exception e) {
			System.out.print("숫자를 입력하세요 :");
			return readNumber(message);
		}
	}
	
	//----------------------------CommonMethodSectionEnd----------------------------------------

	
	
	//----------------------------JustConsoleMethodSectionStart---------------------------------
	
	//환영인사 method
	public void displayWelcome() {
		String welcome = ("*****************************************\n"
						+ "*    Welcome OrientalMedicineClinic     *\n"
						+ "*****************************************");
		System.out.println(welcome);
	}
	
	//menuList에 있는 menu를 고르는 method 
	//menu안에 있는 번호가 아니라면 계속 입력받게 함
	public int selectMenu(String[] menuList) {
		displayMenu(menuList);
		
		int menu;
		do {
			System.out.println(">> 메뉴 선택 : ");
			menu = inputNumberWithValidation();
			if(menu<0 || menu >= menuList.length) {
				System.out.println("잘못된 번호 입니다. 0부터 "+(menuList.length-1)+"까지의 숫자를 입력하세요.");
			}
		}while(menu<0 || menu >= menuList.length);
		
		return menu;
	}	
	
	
	//menuList에 있는 목록을 display하는 method
	public void displayMenu(String[] menuList) {
		System.out.println("=========================================");
	      for(int i = 1; i < menuList.length; i++) {
	         System.out.println(menuList[i]);
	      }
	      System.out.println(menuList[0]);
	      System.out.println("=========================================");
	}	
	//----------------------------JustConsoleMethodSectionEnd----------------------------------
	

	
	//----------------------------PartHerbologyMethodSectionStart------------------------------
	
	public void displayHerbInfo(HerbologyStorage mHerbStorage) {
		for(int i = 0; i < mHerbStorage.getNumHerbs(); i++) {
			String herbInfo = mHerbStorage.getHerbInfo(i);
			System.out.println(herbInfo);
		}
	}
	
	public int selectHerbId(HerbologyStorage mHerbStorage) {
		boolean result;
		int herbId;
		do {
			showMessage("추가할 한약의 ID를 입력하세요 : ");
			herbId = inputNumberWithValidation();
			result = mHerbStorage.isValidHerb(herbId);
			if(!result) {
				showMessage("일치하는 허브 ID가 없습니다.");
			}
		}while(!result);
		return herbId;
	}
	
	//----------------------------PartHerbologyMethodSectionEnd--------------------------------
	
	
	
	//----------------------------PartCartMethodSectionStart-----------------------------------
	
	public void displayCart(Cart mCart) {
		if(mCart.isEmpty()) {
			showMessage(">>장바구니가 비어있습니다.");
			return;
		}
		for(int i = 0; i < mCart.getNumItems(); i++) {
			showMessage(mCart.getItemInfo(i));
		}
	}
	
	public int selectHerbId(Cart mCart) {
		boolean result;
		int herbId;
		do {
			showMessage("한약의 ID를 입력하세요 : ");
			herbId = inputNumberWithValidation();
			result = mCart.isValidHerb(herbId);
			if(!result) {
				showMessage("잘못된 한약 ID 입니다. ");
			}
		}while(!result);
		return herbId;
	}
	
	public int inputRangeNumber(int min, int max) {
		int number;
		do {
			System.out.println(">> 수량 입력 (" + min + " ~ " + max + "): ");
			number = inputNumberWithValidation();
			if(number < min || number > max) {
				showMessage("잘못된 수량 입니다.");
			}
		}while(number < min || number > max);
		return number;
	}
	
	//----------------------------PartCartMethodSectionEnd---------------------------------------
	
	
	
	
	//----------------------------PartRezCalendarMethodSectionStart------------------------------
	
	public void displayRez(ArrayList<Reservation> reservations) {
		showMessage("******************예약목록******************");
		for(Reservation rez : reservations) {
			showMessage(rez.toString());
		}
	}
	
	public void displayTotalRez(ArrayList<Reservation> reservations) {
		showMessage("******************전체예약목록****************");
		for(Reservation rez : reservations) {
			showMessage(rez.toString());
		}
	}
	
	public String selectRezDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		showMessage(">> 에약가능 날자 : ");
		for(int i = 0; i < 7; i++) {
			showMessage((i+1) + ". " + sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
		}
		showMessage(">> 예약 날자 번호 선택 : ");
		int choice = inputNumberWithValidation();
		cal.add(Calendar.DATE, choice-7);
		return sdf.format(cal.getTime());
	}
	
	public String selectRezTime() {
		String[] times = {"오전", "오후", "저녁"};
        showMessage(">>예약 가능한 시간 : ");
        for (int i = 0; i < times.length; i++) {
            showMessage((i + 1) + ". " + times[i]);
        }
        showMessage(">> 예약 시간 번호 선택 : ");
        int choice = inputNumberWithValidation();
        return times[choice - 1];
    
	}
	
	//----------------------------PartRezCalendarMethodSectionEnd--------------------------------
	
	
	
	//----------------------------PartUserMethodSectionStart-------------------------------------
	
	public void inputCustomerInfo(Customer customer) {
		Scanner input = new Scanner(System.in);
		showMessage("한의원 이용을 위해 이름과 전화번호를 입력하세요. ");
		showMessage(">> 이름 : ");
		customer.setName(input.nextLine());
		showMessage(">> 전화번호 : ");
		customer.setPhone(input.nextLine());
	}
	
	public void displayOrder(Cart mCart, Customer customer) {
		showMessage("***** 주문할 한약 *****");
		displayCart(mCart);
		
		//배송정보
		showMessage("***** 배송 정보 ******");
		showMessage(">> 이름 : " + customer.getName());
		showMessage(">> 전화번호 : " + customer.getPhone());
		showMessage(">> 이메일 : " + customer.getEmail());
		showMessage(">> 주소 : " + customer.getAddress());
		System.out.println();
	}
	
	public void inputDeliveryInfo(Customer customer) {//
		if (customer.getEmail() == null) {
			Scanner input = new Scanner(System.in);
			System.out.println("배송을 위하여 이메일 주소와 배송받을 곳의 주소를 입력받습니다.");
			System.out.print(">> 이메일 : ");
			customer.setEmail(input.nextLine());
			System.out.print(">> 주소 : ");
			customer.setAddress(input.nextLine());
		}
	}
	
	public void displayRez(ReservationCalendar reservations) {
		
	}
	
	//----------------------------PartUserMethodSectionEnd---------------------------------------
}
