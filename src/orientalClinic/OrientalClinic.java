package orientalClinic;

import java.io.IOException;

import orientalClinic.controller.OrientalClinicController;
import orientalClinic.model.Cart;
import orientalClinic.model.HerbologyStorage;
import orientalClinic.model.ReservationCalendar;
import orientalClinic.view.ConsoleView;

public class OrientalClinic {

	public static void main(String[]args) throws IOException {
		HerbologyStorage herbologyStorage = new HerbologyStorage();
		Cart cart = new Cart();
		ReservationCalendar reservations = new ReservationCalendar();
		ConsoleView view = new ConsoleView();
		
		OrientalClinicController controller = new OrientalClinicController(herbologyStorage,cart,reservations,view);
		
		controller.start();
	}
}