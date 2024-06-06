package orientalClinic.controller;

import orientalClinic.model.Admin;
import orientalClinic.model.Calendar_Rez;
import orientalClinic.model.Cart;
import orientalClinic.model.Customer;
import orientalClinic.model.HerbologyStorage;
import orientalClinic.view.ConsoleView;

public class OrientalClinicController {
	
	//---------------------------------ControllerFieldSectionStart------------------------------------
	
	ConsoleView view;
	HerbologyStorage mHerbologyStorage;
	Cart mCart;
	Calendar_Rez mCalendar;
	Customer mCustomer;
	Admin mAdmin;
	
	String[] menuList = {
		"0.  종료",
		"1.  진료일정 보기",
		"2.  전체예약 보기",
		"3.  진료 예약",
		"4.  진료 취소",
		"5.  한약목록 보기",
		"6.  장바구니 보기",
		"7.  장바구니에 한약 추가",
		"8.  장바구니 한약 수량 변경",
		"9.  장바구니 한약 삭제",
		"10. 장바구니 비우기",
		"11. 주문하기",
		"12. 관리자"
	};
	
	String[] adminMenuList = {
		"0. 종료",
		"1. 한약 정보 추가",
		"2. 한약 정보 삭제",
		"3. 한약 정보 보기",
		"4. 한약 정보 파일 저장"
	};
	
	public OrientalClinicController(HerbologyStorage herbologyStorage, Cart cart, Calendar_Rez calendar,ConsoleView view) {
		this.view = view;
		this.mHerbologyStorage = herbologyStorage;
		this.mCalendar = calendar;
		this.mCart = cart;
		mCustomer = new Customer();
		mAdmin = new Admin();
	}
	
	//---------------------------------ControllerFieldSectionEnd--------------------------------------
	
	
	
	//---------------------------------CustomerMenuMethodSectionStart---------------------------------
	
	public void start() {
		welcome();
		registerCustomerInfo();
		
		int menu;
		do {
			menu = view.selectMenu(menuList);
			
			switch(menu) {
//			case 1  -> viewCanlanderRezInfo();
//			case 2  -> viewTotalCalenderInfo();
//			case 3  -> reservationDiagnosis();
//			case 4  -> cancelReservation();
			case 5  -> viewHerbInfo();
			case 6  -> viewCart();
			case 7  -> addHerbToCart();
			case 8  -> updateHerbInCart();
			case 9  -> deleteHerbInCart();
			case 10 -> resetCart();
			case 11 -> order();
			case 12 -> adminMode();
			case 0  -> end();
			default -> view.showMessage("잘못된 메뉴 번호 입니다.");
			}
		}while(menu != 0);
		
	}

	//환영인사
	private void welcome() {
		view.displayWelcome();
	}
	
	//종료인사
	private void end() {
		view.showMessage(">> 종료합니다.");
	}
	
	//고객정보 설정
	private void registerCustomerInfo() {
		mCustomer = new Customer();
		view.inputCustomerInfo(mCustomer);
	}
	
	//한약목록 5번
	private void viewHerbInfo() {
		view.displayHerbInfo(mHerbologyStorage);
	}
	
	//장바구니 6번
	private void viewCart() {
		view.displayCart(mCart);
	}
	
	//장바구니 한약 추가 7번
	private void addHerbToCart() {
		view.displayHerbInfo(mHerbologyStorage);
		int herbId = view.selectHerbId(mHerbologyStorage);
		mCart.addItem(mHerbologyStorage.getHerbById(herbId));
		view.showMessage(">>장바구니에 도서를 추가하였습니다.");	
	}
	
	//장바구니 한약 수량 변경 8번
	private void updateHerbInCart() {
		
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			int bookId = view.selectHerbId(mCart);
			int quantity = view.inputRangeNumber(0, mHerbologyStorage.getMaxQuantity());
			mCart.updateQuantity(bookId, quantity);
		}
	}
	
	//장바구니 한약 삭제 9번
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

	//장바구니 비우기 10번
	private void resetCart() {
		view.displayCart(mCart);
		
		if (!mCart.isEmpty()) {
			if (view.askConfirm(">> 장바구니를 비우려면 yes를 입력하세요 : ", "yes")) {
				mCart.resetCart();
				view.showMessage(">> 장바구니를 비웠습니다.");
			}
		}
		
	}
	
	//주문 11번
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
	
	private void adminMode() {
		if(!authenticateAdmin()) {
			view.showMessage("관리자 모드로 전환할 수 없습니다.");
			return;
		}
		
		int menu;
		do {
			menu = view.selectMenu(adminMenuList);
			
			switch(menu) {
			case 1 -> addHerbToStorage();
			case 2 -> deleteHerbInStorage();
			case 3 -> viewHerbInfo();
			case 4 -> saveHerbListToFile();
			case 0 -> adminEnd();
			default -> view.showMessage("잘못된 메뉴 번호입니다.");
			}
		}while(menu != 0);
	}
	
	//---------------------------------CustomerMenuMethodSectionEnd------------------------------------
	
	
	
	//---------------------------------AdminMenuMethodSectionStart-------------------------------------
	
	//한약을 창고에 저장 1번
	private void addHerbToStorage() {
		view.showMessage("새로운 한약을 추가합니다.");
		
		mHerbologyStorage.addHerb(view.inputString("한약 이름 : "),
								  view.inputString("원산지 : "),
								  view.readNumber("가격 : "));

	}
	
	//한약을 창고에서 삭제 2번
	private void deleteHerbInStorage() {
		if (mHerbologyStorage.isEmpty()) {
			view.showMessage("한약 창고에 한약이 없습니다.");
			return;
		}
		viewHerbInfo();
		int herbId = view.selectHerbId(mHerbologyStorage);
		if (view.askConfirm(">> 해당 한약을 삭제하려면 yes를 입력하세요 : ", "yes")) {
			mHerbologyStorage.deleteItem(herbId);
			view.showMessage(">> 해당 한약을 삭제했습니다.");
		}
	}
	
	//한약 정보를 파일에 저장 4번
	private void saveHerbListToFile() {
		if(mHerbologyStorage.isSaved()) {
			view.showMessage("한약 정보가 파일과 동일합니다.");
		}else {
			mHerbologyStorage.saveHerbListToFile();
			view.showMessage("한약 정보를 저장했습니다.");
		}
	}
	
	private void adminEnd() {
		if(!mHerbologyStorage.isSaved()) {
			view.showMessage("수정한 한약정보가 파일에 저장되지 않았습니다.");
			if(view.askConfirm("한약 정보를 저장하려면 yes를 입력하세요", "yes")) {
				mHerbologyStorage.saveHerbListToFile();
			}
		}
		view.showMessage("관리자 모드를 종료합니다. \n");
	}
	
	//---------------------------------AdminMenuMethodSectionEnd---------------------------------------
	
	
	//---------------------------------ControllerMethodSectionStart------------------------------------
	
	private boolean authenticateAdmin() {
		view.showMessage("관리자 모드 접속을 위한 관리자 인증");
		String id = view.inputString("관리자 ID : ");
		String password = view.inputString("관리자 password : ");
		return mAdmin.login(id, password);
	}
	
	//---------------------------------ControllerMtehodSectionEnd--------------------------------------
	
}
