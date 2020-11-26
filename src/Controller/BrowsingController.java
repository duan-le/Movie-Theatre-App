package Controller;

import Database.DatabaseController;
import Model.*;
import View.BrowsingGUI;
import java.io.*; 

public class BrowsingController {
	private BrowsingGUI browsingGUI;
	private DatabaseController databaseController;
	private BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 

	public void browse() {
		// Print to console. Change to GUI later
		
	}
	public Movie getMovie() throws IOException{
		System.out.println("Enter movie: ");
		String movieName = reader.readLine();
		return databaseController.getMovie(movieName);
	}
	public void getSeat() throws IOException {
		System.out.println("Enter seat number: ");
		reader.readLine();
	}
	public void getShowTime() throws IOException {
		System.out.println("Enter show time: ");
		reader.readLine();
	}
}
