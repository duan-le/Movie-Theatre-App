package Controller;

import Database.DatabaseController;
import Model.*;
import View.AccountGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountController {
	private AccountGUI accountGUI;
	private DatabaseController databaseController;
	private MovieTheatreApp movieTheatreApp;
	
	public AccountController(DatabaseController db, MovieTheatreApp map) {
		databaseController = db;
		movieTheatreApp = map;
	}
	
	public void createAccount() {
		this.accountGUI = new AccountGUI ("Create an Account", this);
	}
	
	public MovieTheatreApp getMTA () {
		return movieTheatreApp;
	}

	public void register() throws Exception{
		
		UserInfo userInfo = new UserInfo(accountGUI.getName(), accountGUI.getAddress(), accountGUI.getPhoneNum());
		CardInfo cardInfo = new CardInfo(accountGUI.getCardNum(), accountGUI.getCardName());
		BillingInfo billingInfo = new BillingInfo(accountGUI.getBillingName(),accountGUI.getBillingAddress(), accountGUI.getBillingPhoneNum());
		String email = accountGUI.getEmail();
		String password = accountGUI.getPassword();
		Account account = new Account(userInfo, billingInfo, cardInfo, email, password, null);
		databaseController.addAccount(account);
		account = databaseController.getAccount(email, password);
		if (account == null) {
			accountGUI.displayInvalidRegistration();
		}
		else {
			movieTheatreApp.loginStatus(true);
			accountGUI.displayConfirmedRegistration(account.getCreationDate().toString());
			movieTheatreApp.setUser(new RegisteredUser());
			((RegisteredUser) movieTheatreApp.getUser()).setAccount(account);
			movieTheatreApp.restart();
		}
	}
	
	public void login(OrdinaryUser user) throws Exception {
		
		accountGUI = new AccountGUI ("Login", this, user);
	
	}
	
	public void checkLogin (OrdinaryUser user) {
		
		Account account = databaseController.getAccount(accountGUI.getEmail(), accountGUI.getPassword());
		if (account == null) {
			accountGUI.dispose();
			accountGUI.displayInvalidLogin();
			try {
				login(user);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		else {
			((RegisteredUser) user).setAccount(account);
			movieTheatreApp.setUser(user);
			accountGUI.setUser(user);
			accountGUI.dispose();
			accountGUI.displayLoginConfirmation();	
			movieTheatreApp.userSelection();
			movieTheatreApp.loginStatus(true);
		}
	}
}

