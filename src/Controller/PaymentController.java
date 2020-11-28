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

	public PaymentController(DatabaseController db) {
		databaseController = db;
	}
	
	public void pay(OrdinaryUser user) throws Exception{
		// print to console. switch to gui later
		
		ArrayList<Ticket> ticketList = user.getTicketList();
		double price = 0;
		for (Ticket t : ticketList) {
			price += t.getTicketPrice();
		}
		
		
		if (user instanceof OrdinaryUser) {
			ordinaryPay(user);
		} else {
			registeredPay(user);
		}
			
		System.out.println("CardInfo, BillingInfo and UserInfo Payment Processed");
		
		for (Ticket t : ticketList) {
			TicketReceipt ticketReceipt = new TicketReceipt(t.getTicketNumber());
			databaseController.addTicketReceipt(ticketReceipt);
			user.addTicketReceipt(ticketReceipt);
			databaseController.updateSeat(t.getMovieName(), t.getShowtime(), t.getSeatNumber(), false);
		}
	}
	
	private void ordinaryPay(OrdinaryUser user) throws Exception {
		
		
		// enter info
		System.out.println("");
		System.out.println("Enter Card Number: ");
		String cardinfo = reader.readLine();
		System.out.println("Enter Billing Info: ");
		String billinginfo = reader.readLine();
		
		// confirm payment
		
		// process payment here
	}
	
	private void registeredPay(OrdinaryUser user) {

		// confirm payment
		
		// Payment is automatic because registered user should be logged in
		System.out.println("Payment Automatically Processed");

	}
}
