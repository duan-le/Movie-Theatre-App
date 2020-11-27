package Database;

import java.io.*;
import java.util.ArrayList;
import java.sql.*;
import Model.*;
import Model.Date;
public class DatabaseController {
    private Connection conn;
    PreparedStatement prepStmt;
    private ResultSet rs;
    
    public DatabaseController() {
    	try{  
    		Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
            String path = ""; 
            String user = "";
            String pass = "";
            conn = DriverManager.getConnection(path, user, pass);  
        } catch(Exception e){    
                System.out.println(e);
        }
    }
    
    // Query database to find movie matching movieName
    public Movie findMovie(String movieName) {
    	String n = "";
		String g = "";
		int d = 0;
		int m = 0;
		int y = 0;
		String rt = "";
    	try {
    		String query = "SELECT * FROM db.movie WHERE Name=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setString(1, movieName);
    		rs = prepStmt.executeQuery();
    		while(rs.next()) {
    			n = rs.getString("Name");
    			g = rs.getString("Genre");
    			d = rs.getInt("ReleaseDay");
    			m = rs.getInt("ReleaseMonth");
    			y = rs.getInt("ReleaseYear");
    			rt = rs.getString("RunningTime");
    		}
    		prepStmt.close();
    		rs.close();
    	} catch(Exception e) {
            System.out.println(e);
    	}
    	return new Movie(n, g, new Date(d, m, y), rt);
    }

    // Query database to find all showtimes for movie
    public ArrayList<Showtime> getAllShowtimes(String movieName){
		ArrayList<Showtime> showtimeList = new ArrayList<Showtime>();
    	int d = 0;
		int m = 0;
		int y = 0;
    	String st = "";
		String et = "";
    	try {
    		String query = "SELECT * FROM db.showtime WHERE MovieName=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setString(1, movieName);
    		rs = prepStmt.executeQuery();
    		while(rs.next()) {
    			d = rs.getInt("Day");
    			m = rs.getInt("Month");
    			y = rs.getInt("Year");
    			st = rs.getString("StartTime");
    			et = rs.getString("EndTime");
    			showtimeList.add(new Showtime(new Date(d, m, y), st, et));
    		}
    		prepStmt.close();
    		rs.close();
    	} catch(Exception e) {
            System.out.println(e);
    	}
        return showtimeList;
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
