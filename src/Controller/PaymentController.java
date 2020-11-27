package Controller;

import Database.DatabaseController;
import View.PaymentGUI;
import Model.*;
import java.util.*;
import java.io.*; 

public class PaymentController {
	private PaymentGUI paymentGUI;
	private DatabaseController databaseController;
	
	private BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 

	public void pay() throws Exception{
		// print to console. switch to gui later
		System.out.println("retrieving ticket numbers from previous gui: ");
		/*
		priceList = ArrayList<Double>();
		for (int i: i < ticketList.size(); i++){
			priceList.append(databaseController.getTicketPrice(ticketList.get(i)))
		}
		*/

		// for now assume one ticket per user. later add logic similar to above loop for multiple tickets
		int ticketNumber = Integer.parseInt(reader.readLine());
		double price = databaseController.getTicketPrice(ticketNumber);
	}
}
