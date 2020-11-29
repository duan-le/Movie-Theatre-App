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
		System.out.println("here");
		String movies = "";
		if (user.getClass() == OrdinaryUser.class) {
			if (movie == null || (movie != null && ordinaryBrowse(browsingGUI.getMovie()) == false )) {
				browsingGUI.dispose();
				browsingGUI.displayInvalidMovie();
				movies = ordinaryBrowse();
				browsingGUI.displayMovies(movies);
			}
		}
		else if (movie == null) {
			browsingGUI.dispose();
			browsingGUI.displayInvalidMovie();
			movies = getMovies();
			browsingGUI.displayMovies(movies);
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
			if (i%10 == 0)
				str += "\n";
			str += allShowTime.get(i).toString();
		}
		return str;
	}
	
    public void selectShowTime() throws Exception{
    	
    	String movieName = browsingGUI.getMovie();
		ArrayList<Showtime> allShowTime = databaseController.getAllShowtimes(movieName);
		
		int index = Integer.parseInt(browsingGUI.getShowtime());
		Showtime showtime = allShowTime.get(index);
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
	
	public Seat selectSeat(OrdinaryUser user, String movieName, Showtime showtime) throws Exception {
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
	
}
