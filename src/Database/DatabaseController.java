package Database;

import java.util.ArrayList;
import java.sql.*;
import Model.*;
import Model.Date;

public class DatabaseController {
    private Connection conn;
    private PreparedStatement prepStmt;
    private ResultSet rs;
    
    public DatabaseController() {
    	try{  
    		Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
            String path = "jdbc:mysql://localhost/db"; 
            String user = "root";
            String pass = "password";
            conn = DriverManager.getConnection(path, user, pass);  
        } catch(Exception e) {    
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
    public ArrayList<Showtime> getAllShowtimes(String movieName) {
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

    // Query to get all seats for a movie at a specific showtime
    public ArrayList<Seat> getAllSeats(String movieName, Showtime showtime) {
    	ArrayList<Seat> seatList = new ArrayList<Seat>();
    	int sn = 0;
    	boolean avail = true;
    	try {
    		String query = "SELECT * FROM db.seat WHERE MovieName=? and ShowDay=? and ShowMonth=? and ShowYear=? and StartTime=? and EndTime=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setString(1, movieName);
    		prepStmt.setInt(2, showtime.getDate().getDay());
    		prepStmt.setInt(3, showtime.getDate().getMonth());
    		prepStmt.setInt(4, showtime.getDate().getYear());
    		prepStmt.setString(5, showtime.getStartTime());
    		prepStmt.setString(6, showtime.getEndTime());    		
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
    
    // Update Seat e.g. boolean filled seat
    public void updateSeat(String movieName, Showtime showtime, int seatNumber, boolean avail){
    	try {
    		String query = "UPDATE db.seat SET Available=? WHERE MovieName=? and ShowDay=? and ShowMonth=? and ShowYear=? and StartTime=? and EndTime=? and Number=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setBoolean(1, avail);
    		prepStmt.setString(2, movieName);
    		prepStmt.setInt(3, showtime.getDate().getDay());
    		prepStmt.setInt(4, showtime.getDate().getMonth());
    		prepStmt.setInt(5, showtime.getDate().getYear());
    		prepStmt.setString(6, showtime.getStartTime());
    		prepStmt.setString(7, showtime.getEndTime());
    		prepStmt.setInt(8, seatNumber);
    		prepStmt.executeUpdate();
			prepStmt.close();
		} catch(Exception e) {
	        System.out.println(e);
		}
    }
    
    // Get ticket to database
    public Ticket getTicket(String movieName, Showtime showtime, Seat seat) {
    	int tn = 0;
    	int sn = 0;
    	int d = 0;
    	int m = 0;
    	int y = 0;
    	String st = "";
    	String et = "";
    	String mn = "";
    	double p = 0;
    	try {
    		String query = "SELECT * FROM db.ticket WHERE SeatNumber=? and Day=? and Month=? and Year=? and StartTime=? and EndTime=? and MovieName=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setInt(1, seat.getSeatNumber());
    		prepStmt.setInt(2, showtime.getDate().getDay());
    		prepStmt.setInt(3, showtime.getDate().getMonth());
    		prepStmt.setInt(4, showtime.getDate().getYear());
    		prepStmt.setString(5, showtime.getStartTime());
    		prepStmt.setString(6, showtime.getEndTime());
    		prepStmt.setString(7, movieName);
    		rs = prepStmt.executeQuery();
			while (rs.next()) {
				tn = rs.getInt("Number");
				sn = rs.getInt("SeatNumber");
				d = rs.getInt("Day");
				m = rs.getInt("Month");
				y = rs.getInt("Year");
				st = rs.getString("StartTime");
				et = rs.getString("EndTime");
				mn = rs.getString("MovieName");
				p = rs.getDouble("Price");
			}
    		prepStmt.close();
			rs.close();
		} catch(Exception e) {
	        System.out.println(e);
		}
    	
    	return new Ticket(mn, sn, tn, new Showtime(new Date(d, m, y), st, et), p);
    }
    
    // Add purchased ticket from receipt
    public void addTicketReceipt(TicketReceipt ticketReceipt) {
        
    }

    // Remove purchased ticket from database
    public void removeTicketReceipt(int ticketNumber) {
        
    }
    
    // Add account
    public void addAccount(Account account) {
    	try {
    		String query = "INSERT INTO db.account VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
				account = new Account(new UserInfo(n, a, p), new BillingInfo(bn, ba, bp), new CardInfo(cn, chn), email, password);
			}
    		prepStmt.close();
			rs.close();
		} catch(Exception e) {
	        System.out.println(e);
		}
    	
    	return account;
    }
    
    // Update user info for account
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

    // Update card info for account
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
