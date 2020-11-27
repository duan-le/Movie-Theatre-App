package Model;

import java.util.ArrayList;

public class RegisteredUser extends OrdinaryUser {
	private Account account;
	private ArrayList<MovieNews> movieNewsList;
	
	public RegisteredUser() {
		account = null;
	}
	
	public void setAccount(Account a) {
		account = a;
	}
}
