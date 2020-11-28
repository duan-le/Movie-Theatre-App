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
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	private OrdinaryUser user;
	
	public MovieTheatreApp(DatabaseController db) {
		browsingController = new BrowsingController(db);
		paymentController = new PaymentController(db);
		cancellationController = new CancellationController(db);
		accountController = new AccountController(db);
	}
	
	public static void main(String[] args) {
		// print to console. switch to gui later
		DatabaseController databaseController = new DatabaseController();
		while(true){
			try{
				MovieTheatreApp app = new MovieTheatreApp(databaseController);
				app.startMenu();
				app.selectOption();
			} catch(Exception e){
				System.out.println(e);
			}
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
			switch(option) {
				case 1:
					user = new RegisteredUser();
					while (!accountController.login(user)) {
						System.out.println("Email/Password is not found try again!");
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
				case 5:
					System.out.println("Exiting System");
					System.exit(1);
				default:
					break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void startMenu() {
		String menu = "1. login" +
					"\n2. movie"+
					"\n3. cancel" +
					"\n4. register"+
					"\n5. exit";
		System.out.println(menu);
	}
}
