package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import Database.DatabaseController;
import Model.*;
import View.CancellationGUI;

public class CancellationController {
	private CancellationGUI cancellationGUI;
	private DatabaseController databaseController;
	private int cardNumber;

	private BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
	
	public CancellationController(DatabaseController db) {
		databaseController = db;
	}
	
	public void cancel(OrdinaryUser user) throws Exception {
		System.out.println("Enter ticket number: ");
		String line = reader.readLine();
		int ticketNumber = Integer.parseInt(line);
		Ticket ticket = databaseController.getTicket(ticketNumber);
		if (check72hours(ticket)){
			System.out.println("At least 72 hours before movie starts for Cancellation. Cancellation Failed.");
			return;
		}

		if (user.getClass() == OrdinaryUser.class) {
			ordinaryCancel(user, ticket);
		} else {
			registeredCancel(user, ticket);
		}
		boolean deleted = databaseController.removeTicketReceipt(ticketNumber, cardNumber);
		if (deleted){
			databaseController.updateSeat(ticket.getMovieName(), ticket.getShowtime(), ticket.getSeatNumber(), true);
		}


	}

	private boolean check72hours(Ticket ticket){
		Date date = new Date();
		long dif = ticket.getShowtime().getDate().getTime() - date.getTime();
		if (dif < 0){
			return true;
		}
		long diffHours = dif / (60 * 60 * 1000 * 24);
		if (diffHours > 72)
			return true;
		return false;
	}

	private void ordinaryCancel(OrdinaryUser user, Ticket ticket) throws Exception {
		// enter info
		System.out.println("Enter Card Number: ");
		cardNumber = Integer.parseInt(reader.readLine());
		System.out.println("Enter Billing Info: ");
		String billinginfo = reader.readLine();
		
		// confirm cancelation

		// email for voucher
		System.out.println("Enter email for voucher: ");
		String email = reader.readLine();
		

		// process refund here
		double refundAmount = ticket.getTicketPrice() * .85;
		System.out.println("Cancellation is now being Processed");
		System.out.println("$"+refundAmount + " voucher sent to email.");
		// need to add this logic to database table
		//add 1 year to date
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1); // to get previous year add -1
		Date nextYear = cal.getTime();
		
		System.out.println("Expires: " + nextYear);
	}
	
	private void registeredCancel(OrdinaryUser user, Ticket ticket) {

		
		// Cancellation is automatic because registered user should be logged in
		double refundAmount = ticket.getTicketPrice();
		System.out.println("Cancellation Automatically Processed");
		System.out.println("Cancellation is now being Processed");
		System.out.println("$"+refundAmount + " voucher sent to email");
		// need to add this logic to database table
		//add 1 year to date
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1); // to get previous year add -1
		Date nextYear = cal.getTime();

		System.out.println("Expires: " + nextYear);

	}
}
