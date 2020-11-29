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

	public BrowsingController(DatabaseController db) {
		databaseController = db;
	}
	
	public void browse(OrdinaryUser user) throws Exception {
		// Print to console. Change to GUI later\
		user.addTicket(selectMovie(user));
		
	}
	private void ordinaryBrowse(String movieName){
		Date date = new Date();
		Movie movie = databaseController.findMovie(movieName);
		if (movie.getReleaseDate().getTime() > date.getTime()){
			System.out.println("Movie does not exist");
			// gui exits the browse
		}
	}

	private Ticket selectMovie(OrdinaryUser user) throws Exception{
		// in the gui we will show all movie
		
		// in the database the movie table will contain all movies including movies that are not yet to be announced.
		// only the registered user can see this in the browser and reserve (select) that movie after it passes the logic.
		System.out.println("Enter movie: ");
		String movieName = reader.readLine();
		if (user.getClass() == OrdinaryUser.class) {
			// only show movies that are released to date
			ordinaryBrowse(movieName);
		} 

		Movie movie = databaseController.findMovie(movieName);
		Showtime showtime = selectShowTime(movieName);
		Seat seat = selectSeat(movieName, showtime);

		// create ticket
		Ticket ticket = databaseController.getTicket(movie.getName(), showtime, seat);
		
		return ticket;
		
	}

    private Showtime selectShowTime(String movieName) throws Exception{
		ArrayList<Showtime> allShowTime = databaseController.getAllShowtimes(movieName);
		
		// display available show times
		for (int i = 0; i < allShowTime.size(); i++){
			if(i % 10 == 0)
				System.out.println();
			System.out.println(allShowTime.get(i));
		}

		// select show time
		System.out.println("Select show time: ");
		int index = Integer.parseInt(reader.readLine());
		Showtime showtime = allShowTime.get(index);
		return showtime;
    }
	
	private Seat selectSeat(String movieName, Showtime showtime) throws Exception {
		ArrayList<Seat> allSeats= databaseController.getAllSeats(movieName, showtime);

		// display available seats
		for (int i = 0; i < allSeats.size(); i++){
			if (i % 10 == 0)
				System.out.println();
			System.out.print(allSeats.get(i).getSeatNumber()+ " ");
		}

		// select seats then update database
		System.out.println("Select seat number: ");
		int index = Integer.parseInt(reader.readLine()); 
		Seat seat = allSeats.get(index);
		
		return seat;
	}
}
