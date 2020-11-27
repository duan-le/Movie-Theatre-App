package Controller;

import Database.DatabaseController;
import View.AccountGUI;
import java.io.*;
public class AccountController {
	private AccountGUI accountGUI;
	private DatabaseController databaseController;
	private BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 

	public void register() {
		
	}
	
	public void login() throws Exception{
		System.out.println("Enter email: ");
		String email = reader.readLine();
		System.out.println("Enter password: ");
		String password = reader.readLine();
		account = databaseController.getAccount(email, password);
	}
	
	public void update() {
		
	}
}
