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
		
	}
}
