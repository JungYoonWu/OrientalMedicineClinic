package orientalClinic;

import java.io.IOException;

import orientalClinic.controller.OrientalClinicController;
import orientalClinic.model.Calendar_Rez;
import orientalClinic.model.Cart;
import orientalClinic.model.HerbologyStorage;
import orientalClinic.view.ConsoleView;

public class OrientalClinic {

	public static void main(String[]args) throws IOException {
		HerbologyStorage herbologyStorage = new HerbologyStorage();
		Cart cart = new Cart();
		Calendar_Rez calendar = new Calendar_Rez();
		ConsoleView view = new ConsoleView();
		
		OrientalClinicController controller = new OrientalClinicController(herbologyStorage,cart,calendar,view);
		
		controller.start();
	}
}
