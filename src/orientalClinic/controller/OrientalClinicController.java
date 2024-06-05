package orientalClinic.controller;

import orientalClinic.model.Admin;
import orientalClinic.model.Calendar_Rez;
import orientalClinic.model.Cart;
import orientalClinic.model.Customer;
import orientalClinic.model.HerbologyStorage;
import orientalClinic.view.ConsoleView;

public class OrientalClinicController {
	
	ConsoleView view;
	HerbologyStorage mHerbologyStorage;
	Cart mCart;
	Calendar_Rez mCalendar;
	Customer mCustomer;
	Admin mAdmin;
	
	String[] menuList = {
		"0.  종료",
		"1.  진료일정 보기",
		"2.  전체예약 확인",
		"3.  진료 예약",
		"4.  진료 취소",
		"5.  한약목록 보기",
		"6.  장바구니 보기",
		"7.  장바구니에 한약 추가",
		"8.  장바구니 한약 수량 변경",
		"9.  예약금액 입금",
		"10. 관리자"
	};
	
	String[] adminMenuList = {
		"0. 종료",
		"한약 정보 추가",
		"한약 정보 삭제",
		"한약 정보 파일 저장"
	};
	
	public OrientalClinicController(HerbologyStorage herbologyStorage, Cart cart, Calendar_Rez calendar,ConsoleView view) {
		this.view = view;
		this.mHerbologyStorage = herbologyStorage;
		this.mCalendar = calendar;
		this.mCart = cart;
		mCustomer = new Customer();
		mAdmin = new Admin();
	}
	
	public void start() {
		welcome();
		registerCustomerInfo();
		
		int menu;
		do {
			menu = view.selectMenu(menuList);
			
			switch(menu) {
			case 1 -> viewCanlanderRezInfo();
			case 2 -> viewTotalCalender();
			case 3 -> reservationDiagnosis();
			case 4 -> cancelReservation();
			case 5 -> viewHerbInfo();
			case 6 -> viewCart();
			case 7 -> addHerbToCart();
			case 8 -> deleteHerbInCart();
			case 9 -> updateHerbInCart();
			case 10 -> resetCart();
			case 11 -> order();
			case 0 -> end();
			default -> view.showMessage("잘못된 메뉴 번호 입니다.");
			}
		}while(menu != 0);
		
	}
	
	private void viewHerbInfo() {
		view.displayHerbInfo(mHerbologyStorage);
	}
	
	private void viewCart() {
		view.displayCart(mCart);
	}
	
	private void addHerbToCart() {
		view.displayHerbInfo(mHerbologyStorage);
		int herbId = view.selectHerbId(mHerbologyStorage);
		mCart.addItem(mHerbologyStorage.getHerbId(herbId));
		view.showMessage(">>장바구니에 도서를 추가하였습니다.");	
	}
	
	private void deleteHerbInCart() {
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			
			int herbId = view.selectHerbId(mCart);
			if (view.askConfirm(">> 해당 도서를 삭제하려면 yes를 입력하세요 : ", "yes")) {
				
				mCart.deleteItem(herbId);
				view.showMessage(">> 해당 도서를 삭제했습니다.");
			}
		}
	}
	
	private void updateHerbInCart() {
		// 장바구니 보여주기
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			// 도서 ID 입력 받기
			int bookId = view.selectHerbId(mCart);
			// 수량 입력 받기
			int quantity = view.inputRangeNumber(0, mHerbologyStorage.getMaxQuantity());
			// 도서 ID에 해당하는 cartItem 가져와서 cartItem quantity set 수량
			mCart.updateQuantity(bookId, quantity);
		}
	}
	
	private void resetCart() {
		view.displayCart(mCart);
		
		if (!mCart.isEmpty()) {
			if (view.askConfirm(">> 장바구니를 비우려면 yes를 입력하세요 : ", "yes")) {
				mCart.resetCart();
				view.showMessage(">> 장바구니를 비웠습니다.");
			}
		}
		
	}
	
	private void order() {
		getDeliveryInfo();
		viewOrderInfo();
		if(view.askConfirm("진짜 주문하려면 yes를 입력하세요 : ", "yes"));{
			mCart.resetCart();
		}
	}

	private void getDeliveryInfo() {
		view.inputDeliveryInfo(mCustomer);	
	}

	private void viewOrderInfo() {
		view.displayOrder(mCart, mCustomer);
	}
	
	//종료인사
	private void end() {
		view.showMessage(">> 종료합니다.");
	}

	//환영인사
	private void welcome() {
		view.displayWelcome();
	}
	
	private void registerCustomerInfo() {
		mCustomer = new Customer();
		view.inputCustomerInfo(mCustomer);
	}
}
