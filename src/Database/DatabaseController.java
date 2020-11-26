package Database;

import java.io.*;
import java.util.ArrayList;
import java.sql.*;
import Model.*;
public class DatabaseController {
    private Connection db_con;
    private Statement stmt;
    
    public DatabaseController() {
    	try{  
    		Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
            String path = ""; // e.g. jdbc:mysql://localhost:3306/sonoo
            String user = ""; // e.g. root
            String pass = "";
            db_con = DriverManager.getConnection(path, user, pass);  
        } catch(Exception e){    
                System.out.println(e);
        }
    }
    
	private void insertMovie() {
		try {
			String query = "INSERT INTO mydb.student (id, firstname, lastname) VALUES (?, ?, ?)";
			PreparedStatement pStat = db_con.prepareStatement(query);
			
			pStat.setInt(1, id);
			pStat.setString(2, firstName);
			pStat.setString(3, lastName);
			pStat.executeUpdate();
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public Movie findMovie(String movieName){
    	try {
    		String query = "SELECT * FROM Movie WHERE "
    	} catch(Exception e) {
            System.out.println(e);
    	}
		return null;
    }

    public ArrayList<Showtime> getAllShowtimes(String movieName){
        //query database to find show times for movie
        return new ArrayList<Showtime>();
    }

    public ArrayList<Seat> getAllSeats(String movieName, Showtime showtime){
        //query to get all seats for a movie
        return new ArrayList<Seat>();
    }

    public void addTicket(Ticket ticket){
        // add ticket to database
    }

    public void updateSeat(String movieName, Showtime showtime, Seat seat){
        // update Seat e.g. boolean filled seat 
    }

    public void updateUserInfo(UserInfo userinfo, Account account) {
        // update user info for account
    }

    public void updateCardInfo(CardInfo cardinfo, Account account){
        // update card info for account
    }

    public void addAccount(Account account) {
        // add account
    }

    public void removeTicket(int ticketNumber){
        // remove ticket from database
    }

    public void addPurchasedTicket(TicketReceipt purchasedTicket){
        // add purchased ticket from receipt
    }

    public void removePurchasedTicket(int ticketNumber){
        // remove purchased ticket from database
    }
}
