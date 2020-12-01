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
	
	public String getMovies () {
		String movies = "";
		ArrayList <Movie> allMovies = databaseController.getAllMovies();
		for (Movie m : allMovies) {
			movies += m.getName() + "\n";
		}
		return movies;
	}
	
	public void browse(OrdinaryUser user) throws Exception {

		String movies = "";
		if (user.getClass() == OrdinaryUser.class) {
			movies = ordinaryBrowse();
			// only show movies that are released to date
		} 
		else {
			movies = getMovies();
		}
		browsingGUI = new BrowsingGUI ("Browse Movies", movies, this, user);
//		user.addTicket(selectMovie(user));
	}
	
	public boolean ordinaryBrowse(String movieName){
		boolean check = true;
		Date date = new Date();
		Movie movie = databaseController.findMovie(movieName);
		if (movie.getReleaseDate().getTime() > date.getTime()){
			check = false;
		}
		return check;
	}
	
	public String ordinaryBrowse () {
		String str = "";
		ArrayList <Movie> movies = databaseController.getAllMovies();
		Date date = new Date();
		for (Movie m : movies) {
			if (m.getReleaseDate().getTime() <= date.getTime());
			str += m.getName() + "\n";
		}
		return str;
	}

	public void selectMovie(OrdinaryUser user) throws Exception{
		
		// in the database the movie table will contain all movies including movies that are not yet to be announced.
		// only the registered user can see this in the browser and reserve (select) that movie after it passes the logic.
		Movie movie = databaseController.findMovie(browsingGUI.getMovie());
//		String movies = "";
		if (user.getClass() == OrdinaryUser.class) {
			if (movie == null || (movie != null && ordinaryBrowse(browsingGUI.getMovie()) == false )) {
				browsingGUI.dispose();
				browsingGUI.displayInvalidMovie();
				browse(user);
			}
		}
		else if (movie == null) {
			browsingGUI.dispose();
			browsingGUI.displayInvalidMovie();
			browse(user);
		}
			
		browsingGUI.displayShowtimes(getAllShowtimes(movie.getName()), movie.getName());
		
//		Showtime showtime = selectShowTime(browsingGUI.getShowtime());
//		Seat seat = selectSeat(user, browsingGUI.getSeat(), showtime);
//		ordinaryBrowse(browsingGUI.getTheatre());
		// create ticket
//		Ticket ticket = databaseController.getTicket(movie.getName(), showtime, seat);
		
//		return ticket;
		
	}

	public String getAllShowtimes (String movieName) {
		ArrayList<Showtime> allShowTime = databaseController.getAllShowtimes(movieName);
		String str = "";
		for (int i = 0; i < allShowTime.size(); i++) {
			str += (i+1) + ": " + allShowTime.get(i).toString() + "\n";
		}
		return str;
	}
	
    public void selectShowTime() throws Exception{
    	
    	boolean check = true;
    	String movieName = browsingGUI.getMovie();
		ArrayList<Showtime> allShowTime = databaseController.getAllShowtimes(movieName);
		int index = Integer.parseInt(browsingGUI.getShowtime());
		Showtime showtime = allShowTime.get(index - 1);
		ArrayList<Seat> allSeats= databaseController.getAllSeats(movieName, showtime);
		Movie movie = databaseController.findMovie(movieName);
		Date date = new Date();
		if (movie.getReleaseDate().getTime() > date.getTime()){
			double seatAvail = 0;
			for (Seat seat: allSeats){
				if (seat.getAvailability())
					seatAvail ++;
			}
			if (seatAvail / allSeats.size() < .9) {
				browsingGUI.displayInvalidSeat();
//				browsingGUI.displaySeats(getAllSeats(movieName, showtime), movieName);
				browsingGUI.displayShowtimes(getAllShowtimes(movieName), movieName);
				check = false;
//				System.out.println("over 10% is booked already");
				//gui exits the browse
			}
		}
		// display available seats
		if (check == true)
			browsingGUI.displaySeats(getAllSeats(movieName, showtime), movieName);
    }
    
    public String getAllSeats (String movieName, Showtime showtime) {
    	String str = "";
    	ArrayList<Seat> allSeats= databaseController.getAllSeats(movieName, showtime);
    	
    	for (Seat s : allSeats) {
    		if (s.getSeatNumber()%10 == 0)
    			str += "\n";
    		str += s.getSeatNumber() + "\t";
    	}
    	
    	return str;
    }
	
	public void selectSeat(OrdinaryUser user) throws Exception {
		
		
		String movieName = browsingGUI.getMovie();
		ArrayList<Showtime> allShowTime = databaseController.getAllShowtimes(movieName);
		Showtime showtime = allShowTime.get((Integer.parseInt(browsingGUI.getShowtime()) - 1));
		
		ArrayList<Seat> allSeats= databaseController.getAllSeats(movieName, showtime);
		Movie movie = databaseController.findMovie(movieName);
		/*
		Date date = new Date();
		if (movie.getReleaseDate().getTime() > date.getTime()){
			double seatAvail = 0;
			for (Seat seat: allSeats){
				if (seat.getAvailability())
					seatAvail ++;
			}
			if (seatAvail / allSeats.size() < .9) {
				browsingGUI.displayInvalidSeat();
				browsingGUI.displaySeats(getAllSeats(movieName, showtime), movieName);
//				System.out.println("over 10% is booked already");
				// gui exits the browse
			}
		}
		// display available seats
		for (int i = 0; i < allSeats.size(); i++){
			if (i % 10 == 0)
				System.out.println();
			System.out.print(allSeats.get(i).getSeatNumber()+ " ");
		}

		// select seats then update database
		System.out.println("Select seat number: ");
		*/
		
		int index = Integer.parseInt(browsingGUI.getSeat()); 
		Seat seat = allSeats.get(index-1);
		browsingGUI.dispose();
		if (seat != null) {
			user.addTicket(databaseController.getTicket(movieName, showtime, seat));
			browsingGUI.displayConfirmation ();
		}		
//		return seat;
	}
	
}
