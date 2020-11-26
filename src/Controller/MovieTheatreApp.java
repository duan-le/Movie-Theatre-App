package Controller;

import Database.DatabaseController;
import View.StartGUI;

public class MovieTheatreApp {
	private StartGUI startGUI;
	private BrowsingController browsingController;
	private PaymentController paymentController;
	private CancellationController cancellationController;
	private AccountController accountController;
	private DatabaseController databaseController;
	
	public static void main(String[] args) {

		System.out.println("This is the driver class!");
	}

}