package Controller;

import Database.DatabaseController;
import Model.*;
import View.BrowsingGUI;

import java.awt.Color;
import java.io.*; 
import java.util.*;

public class BrowsingController {
	
	private BrowsingGUI browsingGUI;
	private DatabaseController databaseController; 
	private MovieTheatreApp movieTheatreApp;
	public BrowsingController(DatabaseController db, MovieTheatreApp mta) {
		databaseController = db;
		movieTheatreApp = mta;
	}
	
	public MovieTheatreApp getMTA() {
		return movieTheatreApp;
	}
	
	public String getMovies () {
		String movies = "";
		Date date = new Date ();
		ArrayList <Movie> allMovies = databaseController.getAllMovies();
		for (Movie m : allMovies) {
			if (m.getReleaseDate().getTime() <= date.getTime())
				movies += m.getName() + "\n";
		}
		return movies;
	}
	
	public String getUnreleased () {
		String movies = "";
		Date date = new Date ();
		ArrayList <Movie> allMovies = databaseController.getAllMovies();
		for (Movie m : allMovies) {
			if (m.getReleaseDate().getTime() > date.getTime())
				movies += m.getName() + "\n";
		}
		return movies;
	}
	
	public void browse(OrdinaryUser user) throws Exception {

		String movies = "";
		if (user.getClass() == OrdinaryUser.class) {
			movies = getMovies();
			browsingGUI = new BrowsingGUI ("Browse Movies", movies, this, user);
			// only show movies that are released to date
		} 
		else {
			System.out.println("here");
			movies = getMovies();
			String unreleased = getUnreleased();
			browsingGUI = new BrowsingGUI ("Browse Movies", movies, unreleased, this, user);
		}
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
		if (user.getClass() == OrdinaryUser.class) {
			if (movie == null || (movie != null && ordinaryBrowse(browsingGUI.getMovie()) == false )) {
				browsingGUI.dispose();
				browsingGUI.displayInvalidMovie();
				browse(user);
				return;
			}
		}
		else if (movie == null) {
			browsingGUI.dispose();
			browsingGUI.displayInvalidMovie();
			browse(user);
		}
			
		browsingGUI.displayShowtimes(getAllShowtimes(movie.getName()), movie.getName());
		
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
		Showtime showtime = allShowTime.get(index-1);
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
				browsingGUI.displayShowtimes(getAllShowtimes(movieName), movieName);
				check = false;
			}
		}
		// display available seats
		if (check == true) {
			getSeats (movieName, showtime);
			browsingGUI.displaySeats(movieName);
		}
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
    
    /*
     * Used to pass seats and their colour coding based on availability to gui
     */
    public void getSeats (String movieName, Showtime showtime) {
    	
    	ArrayList<Seat> allSeats= databaseController.getAllSeats(movieName, showtime);
    	
    	for (Seat s : allSeats) {
    		if (s.getAvailability() == false)
    			browsingGUI.addSeatsToFrame (s.getSeatNumber(), new Color (255, 0, 0));
    		else 
    			browsingGUI.addSeatsToFrame (s.getSeatNumber(), new Color (0, 255, 0));
    	}
    }
	
	public void selectSeat(OrdinaryUser user) throws Exception {
		
		String movieName = browsingGUI.getMovie();
		ArrayList<Showtime> allShowTime = databaseController.getAllShowtimes(movieName);
		Showtime showtime = allShowTime.get((Integer.parseInt(browsingGUI.getShowtime()) - 1));
		
		ArrayList<Seat> allSeats= databaseController.getAllSeats(movieName, showtime);
		Movie movie = databaseController.findMovie(movieName);
		
		int index = Integer.parseInt(browsingGUI.getSeat()); 
		Seat seat = allSeats.get(index-1);
		browsingGUI.dispose();
		if (seat != null) {
			Ticket ticket = databaseController.getTicket(movieName, showtime, seat);
			user.addTicket(ticket);
			browsingGUI.displayConfirmation(ticket.getTicketPrice());
			movieTheatreApp.startPayment();
		}		
	}
	
}
