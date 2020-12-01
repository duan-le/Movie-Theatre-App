package Controller;

import Database.DatabaseController;
import View.StartGUI;
import Model.*;
public class MovieTheatreApp {
	private StartGUI startGUI;
	private BrowsingController browsingController;
	private PaymentController paymentController;
	private CancellationController cancellationController;
	private AccountController accountController;
//	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private boolean loggedIn;
	private OrdinaryUser user;
	
	public MovieTheatreApp(DatabaseController db) {
		browsingController = new BrowsingController(db, this);
		paymentController = new PaymentController(db);
		cancellationController = new CancellationController(db);
		accountController = new AccountController(db, this);
		user = new OrdinaryUser();
//		startGUI = new StartGUI("Ordinary User", this);
	}
	
	public static void main(String[] args) {
		// print to console. switch to gui later
		DatabaseController databaseController = new DatabaseController();
		MovieTheatreApp app = new MovieTheatreApp(databaseController);

			try{
				if (app.user.getClass() == OrdinaryUser.class) {
					app.startGUI = new StartGUI (app);
//					app.ordinaryStartMenu();
				}
				else {
					app.startGUI = new StartGUI ("Signed in to your account", app);
//					app.registerUserMenu();
				}
			} catch(Exception e){
				System.out.println(e);
			}
		} 
	
	public void loginStatus(boolean l) {
		loggedIn = l;
	}
	
	public void startPayment() throws Exception {
		paymentController.pay(loggedIn, user);
	}
	
	public void login () throws Exception {
		user = new RegisteredUser();
		try {
			accountController.login(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		startGUI = new StartGUI ("Logged in", this);
	}
	
	public void userSelection () {
		startGUI = new StartGUI ("Logged in", this);
	}
	
	public void browse () throws Exception {
		user = new OrdinaryUser();
		browsingController.browse(user);
//		startPayment();
	}
	
	public void cancel () throws Exception {
		cancellationController.cancel(new OrdinaryUser());
	}
	
	public void register () throws Exception {
		accountController.createAccount();
	}
	
	public void logout () {
		user = new OrdinaryUser ();
	}
	
	public void restart () {
		startGUI = new StartGUI (this);
	}
	
	/*
	public void ordinaryOption() {
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
					registerUserMenu();
					break;
				case 2:
					browsingController.browse(user);
					startPayment();
					break;
				case 3:
					cancellationController.cancel(user);
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

	
	public void registeredUserOption() {
		try {
			String line = reader.readLine();
			int option = Integer.parseInt(line);
			switch(option) {
				case 1:
					browsingController.browse(user);
					startPayment();
					break;
				case 2:
					cancellationController.cancel(user);
					break;
				case 3:				
					user = new OrdinaryUser();
					break;
				case 4:
					System.out.println("Exiting System");
					System.exit(1);
				default:
					break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	

	public void ordinaryStartMenu() {
		String menu = "1. login" +
					"\n2. movie"+
					"\n3. cancel" +
					"\n4. register"+
					"\n5. exit";
		System.out.println(menu);
		ordinaryOption();

	}
	
	public void registerUserMenu(){
		String menu = "1. movie" +
					"\n2. cancel" +
					"\n3. log out" +
					"\n4. exit";
		System.out.println(menu);
		registeredUserOption();
	}
	*/

}
