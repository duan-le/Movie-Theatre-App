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
	public MovieTheatreApp mta;
	private int cardNumber;
	private int ticketNumber;
	private OrdinaryUser u;
	private Ticket ticket;
	
	public CancellationController(DatabaseController db) {
		databaseController = db;
	}
	
	public void setMta(MovieTheatreApp mt) {
		mta = mt;
	}
	
	public void cancel(OrdinaryUser user) throws Exception {
		u = user;
		cancellationGUI = new CancellationGUI("Ticket Cancellation", this);
		cancellationGUI.getTicketNo();
	}
	
	public void ticketParse(String ticketNo) {
		ticketNumber = Integer.parseInt(ticketNo); 
		ticket = databaseController.getTicket(ticketNumber);
		if (!check72hours(ticket))
		{
			cancellationGUI.CancellationFailedGUI("This movie starts within 72 hours. Ticket Cancellation Failed");
			return;
		}
		if (u.getClass() == OrdinaryUser.class) {
			try {
				ordinaryCancel(u, ticket);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			registeredCancel(u, ticket);
		}
		boolean deleted = databaseController.removeTicketReceipt(ticketNumber);
		if (deleted){
			databaseController.updateSeat(ticket.getMovieName(), ticket.getShowtime(), ticket.getSeatNumber(), true);
		}
	}
	
	public void billingInfoParse(String e, String c, String b) {
		String email = e;
		String billingInfo = b;
		cardNumber = Integer.parseInt(c);
		double refundAmount = ticket.getTicketPrice() * .85;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		Date nextYear = cal.getTime();
		cancellationGUI.RegCancelGUI(refundAmount, nextYear);
	}

	private boolean check72hours(Ticket ticket) {
		Date date = new Date();
		long dif = ticket.getShowtime().getDate().getTime() - date.getTime();
		if (dif < 0){
			return true;
		}
		long diffHours = dif / (60 * 60 * 1000 * 24);
		if (diffHours > 72)
		{
			return true;
		}
		return false;
	}

	private void ordinaryCancel(OrdinaryUser user, Ticket ticket) throws Exception {
		cancellationGUI.OrdinaryCancelGUI();
	}
	
	private void registeredCancel(OrdinaryUser user, Ticket ticket) {
		double refundAmount = ticket.getTicketPrice();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		Date nextYear = cal.getTime();
		cancellationGUI.RegCancelGUI(refundAmount, nextYear);
	}
}
