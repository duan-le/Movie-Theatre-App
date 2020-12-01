package Controller;

import Database.DatabaseController;
import View.PaymentGUI;
import Model.*;
import java.util.*;

public class PaymentController {
	private PaymentGUI paymentGUI;
	private DatabaseController databaseController;
	private int cardNumber;
	public MovieTheatreApp mta;
	private ArrayList<Ticket> ticketList;
	private OrdinaryUser ou;
	
	public void setMta(MovieTheatreApp mt) {
		mta = mt;
	}
	
	public PaymentController(DatabaseController db) {
		databaseController = db;
	}
	
	public void pay(boolean loggedIn, OrdinaryUser user) throws Exception {
		ou = user;
		ticketList = user.getTicketList();	
		if (loggedIn) {
			paymentGUI = new PaymentGUI(user, this);
			paymentGUI.RegPaymentGUI();
			// registeredPay();
		} else {
			paymentGUI = new PaymentGUI(user, this);
			ordinaryPay("info");
		}	
		for (Ticket t : ticketList) {
			TicketReceipt ticketReceipt = new TicketReceipt(t.getTicketNumber(), cardNumber);
			databaseController.addTicketReceipt(ticketReceipt);
			user.addTicketReceipt(ticketReceipt);
			databaseController.updateSeat(t.getMovieName(), t.getShowtime(), t.getSeatNumber(), false);
		}	
	}
	
	public void ordinaryPay(String info) throws Exception {
		paymentGUI.OrdPaymentGUI();
	}
	
	
	public void parseInfo(String e, String b, String c)
	{
		String email = e;
		String billingInfo = b;
		cardNumber = Integer.parseInt(c);
		
		for (Ticket t : ticketList) {
			TicketReceipt ticketReceipt = new TicketReceipt(t.getTicketNumber(), cardNumber);
			databaseController.addTicketReceipt(ticketReceipt);
			ou.addTicketReceipt(ticketReceipt);
			databaseController.updateSeat(t.getMovieName(), t.getShowtime(), t.getSeatNumber(), false);
		}
	}
}
