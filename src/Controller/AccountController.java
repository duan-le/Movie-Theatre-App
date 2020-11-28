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
		
		accountGUI = new AccountGUI ("Create an Account", 0);
		
		accountGUI.RegisterGUI.getOkayButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				UserInfo userInfo = new UserInfo(accountGUI.registerGUI.getName(), accountGUI.registerGUI.getAddress(),
					accountGUI.registerGUI.getPhoneNum());
				CardInfo cardInfo = new CardInfo(accountGUI.registerGUI.getCardNum(), accountGUI.registerGUI.getCardName());
				BillingInfo billingInfo = new BillingInfo(accountGUI.registerGUI.getBillingName(), 
					accountGUI.registerGUI.getBillingAddress(), accountGUI.registerGUI.getBillingPhoneNum());
				String email = accountGUI.registerGUI.getEmail();
				String password = accountGUI.registerGUI.getPassword();
				Account account = new Account(userInfo, billingInfo, cardInfo, email, password);
				databaseController.addAccount(account);
		}});
	}
	
	public boolean login(OrdinaryUser user) throws Exception {
		
		accountGUI = new AccountGUI ("Login", 1);
		
		accountGUI.RegisterGUI.getOkayButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				Account account = databaseController.getAccount(accountGUI.loginGUI.getMail(), accountGUI.loginGUI.getPass());
				if (account != null) {
					((RegisteredUser) user).setAccount(account);
					return true;
				}
				return false;
		}});
	}
	
	public void update() {
		
	}
}
