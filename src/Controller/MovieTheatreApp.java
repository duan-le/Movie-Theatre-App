package Controller;

import Database.DatabaseController;
import View.StartGUI;
import java.io.*; 
import java.util.*;
import Model.*;
public class MovieTheatreApp {
	private StartGUI startGUI;
	private BrowsingController browsingController;
	private PaymentController paymentController;
	private CancellationController cancellationController;
	private AccountController accountController;
	private DatabaseController databaseController;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	private OrdinaryUser user;
	
	public static void main(String[] args) {
		// print to console. switch to gui later
		try{
			MovieTheatreApp app = new MovieTheatreApp();
			app.startMenu();
			app.selectOption();
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void startPayment() throws Exception {
		paymentController.pay(user);
	}
	
	public void selectOption() {
		// ordingary or register button 
		// create user object based on button pressed
		// two different start paths but same loop path e.g. everything goes back to browse
		try {
			String line = reader.readLine();
			int option = Integer.parseInt(line);
			System.out.println(option);
			switch(option) {
				case 1:
					user = new RegisteredUser();
					while (accountController.login(user)) {
						break;
					}
					browsingController.browse(user);
					startPayment();
					break;
				case 2:
					user = new OrdinaryUser();
					browsingController.browse(user);
					startPayment();
					break;
				case 3:
					cancellationController.cancel();
					break;
				case 4:
					accountController.register();
					break;
				default:
					break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void startMenu() {
		String menu = "1. login" +
					"\n2. movie";
		System.out.println(menu);
	}
}
