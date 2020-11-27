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

	public void browse(OrdinaryUser user) throws Exception {
		// Print to console. Change to GUI later
		user.addTicket(selectMovie());
		
	}
	private Ticket selectMovie() throws Exception{
		// in the gui we will show all movie
		System.out.println("Enter movie: ");
		String movieName = reader.readLine();
		Movie movie = databaseController.findMovie(movieName);
		if (movie == null)
		{
			return;
		}
		Showtime showtime = selectShowTime(movieName);
		Seat seat = selectSeat(movieName, showtime);

		// create ticket
		Ticket ticket = new Ticket(movieName, showtime, seat.getSeatNumber());
		databaseController.addTicket(ticket);
		return ticket;
		
	}

    private Showtime selectShowTime(String movieName) throws Exception{
		ArrayList<Showtime> allShowTime = databaseController.getAllShowtimes(movieName);
		
		// display available show times
		for (int i = 0; i < allShowTime.size(); i++){
			if(i % 10 == 0)
				System.out.println();
			System.out.print(allShowTime.get(i));
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
		databaseController.updateSeat(movieName, showtime, seat);
		return seat;
	}


}
