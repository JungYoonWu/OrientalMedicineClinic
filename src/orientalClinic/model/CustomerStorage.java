package orientalClinic.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerStorage {

	//---------------------------------StorageFieldSectionStart------------------------------------
	
	private ArrayList<Customer> customers;
	private String customerFilename = "customers.txt";
	
	public CustomerStorage() throws IOException{
		customers = new ArrayList<>();
		loadCustomersFromFile();
	}
	
	//---------------------------------StorageFieldSectionEnd--------------------------------------
	
	
	
	//---------------------------------FunctionalMethodSectionStart--------------------------------
	
	private void loadCustomersFromFile() throws IOException{
		FileReader fr;
		try {
			fr = new FileReader(customerFilename);
			BufferedReader br = new BufferedReader(fr);
			String idStr;
			while((idStr = br.readLine()) != null && idStr.equals("")) {
				int id = Integer.parseInt(idStr);
				
			}
			
			
		}catch(FileNotFoundException | NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//---------------------------------FunctionalMethodSectionEnd----------------------------------
}
