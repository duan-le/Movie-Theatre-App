package Controller;

import Database.DatabaseController;
import Model.*;
import View.BrowsingGUI;
import java.io.*; 
import java.util.*;

public class BrowsingController {
	private BrowsingGUI browsingGUI;
	private DatabaseController databaseController;
	private BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 

	public void browse() {
		// Print to console. Change to GUI later
		
	}
	public Movie getMovie() throws Exception{
		System.out.println("Enter movie: ");
		String movieName = reader.readLine();
		return databaseController.findMovie(movieName);
	}
	public ArrayList<Seat> getAllSeats() throws Exception {
		System.out.println("Enter movie: ");
		String movieName = reader.readLine();
		System.out.println("Enter showtime: ");
		String showTime = reader.readLine();
		Showtime showtime = new Showtime();
		return databaseController.getAllSeats(movieName, showtime);
	}
	public void getShowTime() throws Exception {
		System.out.println("Enter show time: ");
		reader.readLine();
	}
}
