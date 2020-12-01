package Database;

import java.util.*;

import java.sql.*;
import java.text.*;
import Model.*;

public class DatabaseController {
    private Connection conn;
    private PreparedStatement prepStmt;
    private ResultSet rs;
    
    public DatabaseController() {
    	try{   
    		Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
            String path = "jdbc:mysql://127.0.0.1:3306/db"; 
            String user = "root";
            String pass = "password";
            conn = DriverManager.getConnection(path, user, pass);  
        } catch(Exception e) {    
                System.out.println(e);
        }
    }
    
    /*
     *  Query database to find all movies
     */
    public ArrayList<Movie> getAllMovies() {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		String n = "";
		String g = "";
		String rt = "";
		java.util.Date dt = null;
     	try {
    		String query = "SELECT * FROM db.movie";
    		prepStmt = conn.prepareStatement(query);
    		rs = prepStmt.executeQuery();
    		while(rs.next()) {
       			n = rs.getString("Name");
    			g = rs.getString("Genre");
    			dt = rs.getTimestamp("ReleaseDate");
    			rt = rs.getString("RunningTime");
    			movieList.add(new Movie (n, g, dt, rt));
    		}
    		prepStmt.close();
    		rs.close();
    	} catch(Exception e) {
            System.out.println(e);
    	}
        return movieList;
    }
    
    /*
     *  Query database to find movie matching movieName
     */
    public Movie findMovie(String movieName) {
    	String n = "";
		String g = "";
		String rt = "";
		java.util.Date dt = null;
    	try {
    		String query = "SELECT * FROM db.movie WHERE Name=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setString(1, movieName);
    		rs = prepStmt.executeQuery();
    		while(rs.next()) {
    			n = rs.getString("Name");
				g = rs.getString("Genre");
				dt = rs.getTimestamp("ReleaseDate");
				rt = rs.getString("RunningTime");
    		}
    		prepStmt.close();
    		rs.close();
    	} catch(Exception e) {
            System.out.println(e);
		}
		return new Movie(n, g, dt, rt);
	}
	

    /*
     *  Query database to find all showtimes for movie
     */
    public ArrayList<Showtime> getAllShowtimes(String movieName) {
		ArrayList<Showtime> showtimeList = new ArrayList<Showtime>();
     	try {
    		String query = "SELECT * FROM db.showtime WHERE MovieName=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setString(1, movieName);
    		rs = prepStmt.executeQuery();
    		while(rs.next()) {
    			showtimeList.add(new Showtime(rs.getTimestamp("ShowDate")));
    		}
    		prepStmt.close();
    		rs.close();
    	} catch(Exception e) {
            System.out.println(e);
    	}
        return showtimeList;
    }

    /*
     *  Query to get all seats for a movie at a specific showtime
     */
    public ArrayList<Seat> getAllSeats(String movieName, Showtime showtime) {
    	ArrayList<Seat> seatList = new ArrayList<Seat>();
    	int sn = 0;
    	boolean avail = true;
    	try {
    		String query = "SELECT * FROM db.seat WHERE MovieName=? and ShowDate=?";
    		prepStmt = conn.prepareStatement(query);
			prepStmt.setString(1, movieName);
    		prepStmt.setTimestamp(2, new Timestamp(showtime.getDate().getTime()));
    		rs = prepStmt.executeQuery();
    		while(rs.next()) {
    			sn = rs.getInt("Number");
    			avail = rs.getBoolean("Available");
    			seatList.add(new Seat(sn, avail));
    		}
    		prepStmt.close();
    		rs.close();
    	} catch(Exception e) {
            System.out.println(e);
    	}
        return seatList;
    }
    
    /*
     *  Update Seat e.g. boolean filled seat
     */
    public void updateSeat(String movieName, Showtime showtime, int seatNumber, boolean avail){
    	try {
    		String query = "UPDATE db.seat SET Available=? WHERE MovieName=? and ShowDate=? and Number=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setBoolean(1, avail);
			prepStmt.setString(2, movieName);
    		prepStmt.setTimestamp(3, new Timestamp(showtime.getDate().getTime()));
    		prepStmt.setInt(4, seatNumber);
    		prepStmt.executeUpdate();
			prepStmt.close();
		} catch(Exception e) {
	        System.out.println(e);
		}
    }
    
    /*
     * Get ticket from database by everything but ticket number
     */
    public Ticket getTicket(String movieName, Showtime showtime, Seat seat) {
    	int tn = 0;
    	int sn = 0;
		java.util.Date dt = null;
		String mn = "";
    	double p = 0;
    	try {
    		String query = "SELECT * FROM db.ticket WHERE SeatNumber=? and Date=? and MovieName=?";
    		prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, seat.getSeatNumber());
    		prepStmt.setTimestamp(2, new Timestamp(showtime.getDate().getTime()));
    		prepStmt.setString(3, movieName);
    		rs = prepStmt.executeQuery();
			while (rs.next()) {
				tn = rs.getInt("Number");
				sn = rs.getInt("SeatNumber");
				dt = rs.getTimestamp("Date");
				mn = rs.getString("MovieName");
				p = rs.getDouble("Price");
			}
    		prepStmt.close();
			rs.close();
		} catch(Exception e) {
	        System.out.println(e);
		}
    	
    	return new Ticket(mn, sn, tn, new Showtime(dt), p);
    }
    
    /*
     * Get ticket by ticket number 
     */
    public Ticket getTicket(int ticketNumber) {
    	int tn = 0;
    	int sn = 0;
		java.util.Date dt = null;
		String mn = "";
    	double p = 0;
    	try {
    		String query = "SELECT * FROM db.ticket WHERE Number=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setInt(1, ticketNumber);
    		rs = prepStmt.executeQuery();
			while (rs.next()) {
				tn = rs.getInt("Number");
				sn = rs.getInt("SeatNumber");
				dt = rs.getTimestamp("Date");
				mn = rs.getString("MovieName");
				p = rs.getDouble("Price");
			}
    		prepStmt.close();
			rs.close();
		} catch(Exception e) {
	        System.out.println(e);
		}
    	
    	return new Ticket(mn, sn, tn, new Showtime(dt), p);
    }
    
    /*
     * Add purchased ticket from receipt
     */
    public void addTicketReceipt(TicketReceipt ticketReceipt) {
        try {
        	String query = "INSERT INTO db.ticketreceipt VALUES(?)";
    		prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, ticketReceipt.getTicketNumber());
    		prepStmt.executeUpdate();
			prepStmt.close();
        } catch(Exception e) {
	        System.out.println(e);
		}
    }

    /*
     * Removes a purchased ticket from the database upon cancellation
     */
    public boolean removeTicketReceipt(int ticketNumber) {
		boolean deleted = false;
		try {
        	String query = "DELETE FROM db.ticketreceipt WHERE TicketNumber=?";
    		prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, ticketNumber);
    		deleted = prepStmt.executeUpdate() >= 1;
			prepStmt.close();
        } catch(Exception e) {
	        System.out.println(e);
		}
		return deleted;
    }

    /*
     * Adds an account to the database
     */
    public void addAccount(Account account) {
    	try {
    		String query = "INSERT INTO db.account VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, default)";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setString(1, account.getEmail());
    		prepStmt.setString(2, account.getPassword());
    		prepStmt.setInt(3, account.getCardInfo().getCardNumber());
    		prepStmt.setString(4, account.getCardInfo().getCardHolderName());
    		prepStmt.setString(5, account.getUserInfo().getName());
    		prepStmt.setString(6, account.getUserInfo().getAddress());
    		prepStmt.setString(7, account.getUserInfo().getPhoneNumber());
    		prepStmt.setString(8, account.getBillingInfo().getName());
    		prepStmt.setString(9, account.getBillingInfo().getAddress());
    		prepStmt.setString(10, account.getBillingInfo().getPhoneNumber());
    		prepStmt.executeUpdate();
			prepStmt.close();
		} catch(Exception e) {
	        System.out.println(e);
		}
    }

    /*
     * Gets an account from the database
     */
    public Account getAccount(String email, String password) {
    	Account account = null;
    	int cn = 0;
    	String chn = "";
    	String n = "";
    	String a = "";
    	String p = "";
    	String bn = "";
    	String ba = "";
		String bp = "";
		java.util.Date d = null;
    	try {
    		String query = "SELECT * FROM db.account WHERE Email=? and Password=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setString(1, email);
    		prepStmt.setString(2, password);
    		rs = prepStmt.executeQuery();
			if (rs.next()) {
				cn = rs.getInt("CardNumber");
				chn = rs.getString("CardHolderName");
				n = rs.getString("Name");
				a = rs.getString("Address");
				p = rs.getString("Phone");
				bn = rs.getString("BillingName");
				ba = rs.getString("BillingAddress");
				bp = rs.getString("BillingPhone");
				d = rs.getTimestamp("CreationDate");
				account = new Account(new UserInfo(n, a, p), new BillingInfo(bn, ba, bp), new CardInfo(cn, chn), email, password, d);
			}
    		prepStmt.close();
			rs.close();
		} catch(Exception e) {
	        System.out.println(e);
		}
    	return account;
    }
    
    /*
     * Updates user information for an account
     */
    public void updateUserInfo(UserInfo userInfo, Account account) {
        try {
        	String query = "UPDATE db.account SET Name=?, Address=?, Phone=? WHERE Email=? and Password=?";
        	prepStmt = conn.prepareStatement(query);
    		prepStmt.setString(1, userInfo.getName());
    		prepStmt.setString(2, userInfo.getAddress());
    		prepStmt.setString(3, userInfo.getPhoneNumber());
    		prepStmt.setString(4, account.getEmail());
    		prepStmt.setString(5, account.getPassword());
    		prepStmt.executeUpdate();
			prepStmt.close();
        } catch(Exception e) {
	        System.out.println(e);
		}
    }

    /*
     * Updates card information for an account
     */
    public void updateCardInfo(CardInfo cardInfo, Account account) {
    	try {
        	String query = "UPDATE db.account SET CardNumber=?, CardHolderName=? WHERE Email=? and Password=?";
        	prepStmt = conn.prepareStatement(query);
    		prepStmt.setInt(1, cardInfo.getCardNumber());
    		prepStmt.setString(2, cardInfo.getCardHolderName());
    		prepStmt.setString(3, account.getEmail());
    		prepStmt.setString(4, account.getPassword());
    		prepStmt.executeUpdate();
			prepStmt.close();
        } catch(Exception e) {
	        System.out.println(e);
		}
	}
}
