package Controller;

import Database.DatabaseController;
import Model.*;
import View.BrowsingGUI;
import java.io.*; 
import java.util.*;

import com.mysql.cj.exceptions.DataConversionException;

public class BrowsingController {
	
	private BrowsingGUI browsingGUI;
	private DatabaseController databaseController; 

	public BrowsingController(DatabaseController db) {
		databaseController = db;
	}
	
	public void browse(OrdinaryUser user) throws Exception {
//		browsingGUI = new BrowsingGUI ("Browse Movies", databaseController.getAllMovies().toString(), this);
		user.addTicket(selectMovie(user));
		
	}
	
	public boolean ordinaryBrowse(String movieName){
		boolean check = true;
		Date date = new Date();
		Movie movie = databaseController.findMovie(movieName);
		if (movie.getReleaseDate().getTime() > date.getTime()){
			check = false;
	//		System.out.println("Movie does not exist");
			// gui exits the browse
		}
		return check;
	}

	private Ticket selectMovie(OrdinaryUser user) throws Exception{
		
		// in the database the movie table will contain all movies including movies that are not yet to be announced.
		// only the registered user can see this in the browser and reserve (select) that movie after it passes the logic.
		
		if (user.getClass() == OrdinaryUser.class) {
			browsingGUI = new BrowsingGUI ("Browse Movies", databaseController.getAllMovies().toString(), this);
			// only show movies that are released to date
			
		} 
		
		
		
		while (ordinaryBrowse(browsingGUI.getTextField()) == false) {
			browsingGUI.displayInvalidMovie();
			browsingGUI.displayMovies(databaseController.getAllMovies().toString());
		}
		Movie movie = databaseController.findMovie(browsingGUI.getTextField());
		Showtime showtime = selectShowTime(browsingGUI.getTextField());
		Seat seat = selectSeat(user, browsingGUI.getTextField(), showtime);
		ordinaryBrowse(browsingGUI.getTextField());
		// create ticket
		Ticket ticket = databaseController.getTicket(movie.getName(), showtime, seat);
		
		return ticket;
		
	}
	
	public void confirmMovie (String movieName) {
		
	}

    private Showtime selectShowTime(String movieName) throws Exception{
		ArrayList<Showtime> allShowTime = databaseController.getAllShowtimes(movieName);
		
		String s = "";
		// display available show times
		for (int i = 0; i < allShowTime.size(); i++){
			if(i % 10 == 0)
				s += "\n";
			s += allShowTime.get(i).toString();
			//System.out.println(allShowTime.get(i));
		}
		
		
		// select show time
		browsingGUI.displayShowtimes();
		int index = Integer.parseInt(browsingGUI.getTextField());
		Showtime showtime = allShowTime.get(index);
		return showtime;
    }
	
	private Seat selectSeat(OrdinaryUser user, String movieName, Showtime showtime) throws Exception {
		ArrayList<Seat> allSeats= databaseController.getAllSeats(movieName, showtime);
		Movie movie = databaseController.findMovie(movieName);
		Date date = new Date();
		if (movie.getReleaseDate().getTime() > date.getTime()){
			double seatAvail =0;
			for (Seat seat: allSeats){
				if (seat.getAvailability())
					seatAvail ++;
			}
			if (seatAvail / allSeats.size() < .9)
				System.out.println("over 10% is booked already");
				// gui exits the browse
		}
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
	
	@Override
	public String toString () {
		String s = "";
		
		
		
		return s;
	}
}
