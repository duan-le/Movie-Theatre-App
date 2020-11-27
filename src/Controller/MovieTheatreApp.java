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
	private BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
	private OrdinaryUser user;
	
	public static void main(String[] args) {
		// print to console. switch to gui later
		try{
			MovieTheatreApp app = new MovieTheatreApp();
			app.startMenu();
			app.selectOption();
			app.startPayment();

		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void startPayment() throws Exception {
		paymentController.pay(user);
	}
	
	public void selectOption() throws Exception{
		// ordingary or register button 
		// create user object based on button pressed
		// two different start paths but same loop path e.g. everything goes back to browse
		String line = reader.readLine();
		int option = Integer.parseInt(line);
		switch(option){
			case 1:
				// create register user
				user = new RegisteredUser();
				while (accountController.login(user)) {
					break;
				}
				browsingController.browse(user);
				break;
			case 2:
				// create ordinary user
				user = new OrdinaryUser();
				browsingController.browse(user);
				break;
			case 3:
				cancellationController.cancel();
			default:
				break;
		}
	}
	public void startMenu(){
		String menu = "1. login" +
					"\n2. movie";
		System.out.println(menu);
	}
	
    // Main test
//    public static void main(String[] args) {
//    	DatabaseController dbc = new DatabaseController();
//    	Movie movie = dbc.findMovie("Movie 1");
//    	System.out.println(movie.getName());
//    	System.out.println(movie.getRunningTime());
//    	
//    }

}