package Controller;

import Database.DatabaseController;
import Model.*;
import View.AccountGUI;
import java.io.*;

public class AccountController {
	private AccountGUI accountGUI;
	private DatabaseController databaseController;
	private BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
	
	public AccountController(DatabaseController db) {
		databaseController = db;
	}

	public void register() {
		// get user, billing, card info

		UserInfo userInfo = new UserInfo("name3", "addr3", "phone3");
		CardInfo cardInfo = new CardInfo(3333, "chn3");
		BillingInfo billingInfo = new BillingInfo("bn3", "ba3", "bp3");
		String email = "email3";
		String password = "pass3";
		Account account = new Account(userInfo, billingInfo, cardInfo, email, password);
		databaseController.addAccount(account);
	}
	
	public boolean login(OrdinaryUser user) throws Exception {
		System.out.println("Enter email: ");
		String email = reader.readLine();
		System.out.println("Enter password: ");
		String password = reader.readLine();
		Account account = databaseController.getAccount(email, password);
		if (account != null) {
			((RegisteredUser) user).setAccount(account);
			return true;
		}
		return false;
	}
	
	public void update() {
		
	}
}
