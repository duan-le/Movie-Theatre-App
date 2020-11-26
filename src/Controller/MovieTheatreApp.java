package Controller;

import Database.DatabaseController;
import View.StartGUI;
import java.io.*; 
import java.util.*;

public class MovieTheatreApp {
	private StartGUI startGUI;
	private BrowsingController browsingController;
	private PaymentController paymentController;
	private CancellationController cancellationController;
	private AccountController accountController;
	private DatabaseController databaseController;
	private BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 

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
	public void selectOption() throws IOException{
		String line = reader.readLine();
		int option = Integer.parseInt(line);
		switch(option){
			case 1:
			accountController.login();
			break;
			case 2:
			browsingController.browse();
			break;
			default:
			break;
		}
	}
	public void startMenu(){
		String menu = "1. login" +
					"\n2. movie";
		System.out.println(menu);
	}

}