package Controller;

import javax.swing.UIManager;

import Database.DatabaseController;
import View.StartGUI;
import Model.*;
public class MovieTheatreApp {
	private StartGUI startGUI;
	private BrowsingController browsingController;
	private PaymentController paymentController;
	private CancellationController cancellationController;
	private AccountController accountController;
	private boolean loggedIn;
	private OrdinaryUser user;
	
	public MovieTheatreApp(DatabaseController db) {
		browsingController = new BrowsingController(db, this);
		paymentController = new PaymentController(db);
		cancellationController = new CancellationController(db);
		accountController = new AccountController(db, this);
		user = new OrdinaryUser();
	}
	
	public static void main(String[] args) {
		DatabaseController databaseController = new DatabaseController();
		MovieTheatreApp app = new MovieTheatreApp(databaseController);
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				if (app.user.getClass() == OrdinaryUser.class) {
					app.startGUI = new StartGUI(app);
				}
				else {
					app.startGUI = new StartGUI ("Signed in to your account", app);
				}
			} catch (Exception e){
				System.out.println(e);
			}
		} 
	
	public void loginStatus(boolean l) {
		loggedIn = l;
	}
	
	public void startPayment() throws Exception {
		paymentController.pay(loggedIn, user);
		paymentController.setMta(this);
	}
	
	public void login() throws Exception {
		user = new RegisteredUser();
		try {
			accountController.login(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void userSelection () {
		startGUI = new StartGUI ("Logged in", this);
	}
	
	public void browse() throws Exception {
		browsingController.browse(user);
	}
	
	public void cancel() throws Exception {
		cancellationController.cancel(new OrdinaryUser());
		cancellationController.setMta(this);
	}
	
	public void register() throws Exception {
		accountController.createAccount();
	}
	
	public void logout() {
		user = new OrdinaryUser ();
		startGUI = new StartGUI (this);
	}
	
	public void restart() {
		if (user.getClass() == OrdinaryUser.class) {
			startGUI = new StartGUI (this);
		}
		else {
			startGUI = new StartGUI ("Signed in to your account", this);
		}
	}
	
	public void setUser(OrdinaryUser u) {
		user = u;
	}
	
	public OrdinaryUser getUser () {
		return user;
	}
}
