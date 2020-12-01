package Controller;

import Database.DatabaseController;
import View.PaymentGUI;
import Model.*;
import java.util.*;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import java.io.*; 

public class PaymentController {
	private PaymentGUI paymentGUI;
	private DatabaseController databaseController;
	private int cardNumber;
	private String email;
	private String billingInfo;
	private BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 

	public PaymentController(DatabaseController db) {
		databaseController = db;
	}
	
	public void pay(boolean loggedIn, OrdinaryUser user) throws Exception{
		// print to console. switch to gui later
		
		ArrayList<Ticket> ticketList = user.getTicketList();
		double price = 0;
		for (Ticket t : ticketList) {
			price += t.getTicketPrice();
		}
		
		
		if (loggedIn) {
			paymentGUI = new PaymentGUI(user, this);
			paymentGUI.RegPaymentGUI();
			registeredPay();
		} else {
			paymentGUI = new PaymentGUI(user, this);
			ordinaryPay("info");
		}
			
		System.out.println("CardInfo, BillingInfo and UserInfo Payment Processed");

	
		for (Ticket t : ticketList) {
			TicketReceipt ticketReceipt = new TicketReceipt(t.getTicketNumber(), cardNumber);
			databaseController.addTicketReceipt(ticketReceipt);
			user.addTicketReceipt(ticketReceipt);
			databaseController.updateSeat(t.getMovieName(), t.getShowtime(), t.getSeatNumber(), false);
		}
		
	}
	
	public void ordinaryPay(String info) throws Exception {
		// enter info
		/*System.out.println("");
		System.out.println("Enter Card Number: ");
		cardNumber = Integer.parseInt(reader.readLine());
		System.out.println("Enter Billing Info: ");
		String billinginfo = reader.readLine();*/
		
		// confirm payment
		paymentGUI.OrdPaymentGUI();
		
		
		// process payment here
		//System.out.println("Payment is now being Processed");

	}
	
	
	public void parseInfo(String e, String b, String c)
	{
		email = e;
		billingInfo = b;
		cardNumber = Integer.parseInt(c);
	}
	
	private void registeredPay() {

		// confirm payment
		
		// Payment is automatic because registered user should be logged in
		System.out.println("Payment Automatically Processed");

	}
}
