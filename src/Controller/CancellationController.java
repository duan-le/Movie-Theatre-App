package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import Database.DatabaseController;
import Model.Ticket;
import View.CancellationGUI;

public class CancellationController {
	private CancellationGUI cancellationGUI;
	private DatabaseController databaseController;
	private BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
	
	public CancellationController(DatabaseController db) {
		databaseController = db;
	}
	
	public void cancel() throws Exception {
		System.out.println("Enter ticket number: ");
        String line = reader.readLine();
        int ticketNumber = Integer.parseInt(line);
        databaseController.removeTicketReceipt(ticketNumber);
        Ticket ticket = databaseController.getTicket(ticketNumber);
        databaseController.updateSeat(ticket.getMovieName(), ticket.getShowtime(), ticket.getSeatNumber(), true);
	}
}
