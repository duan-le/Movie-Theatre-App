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
    
    // Add ticket to database
    public Ticket getTicket(String movieName, Showtime showtime, Seat seat) {
        return new Ticket();
    }
    
    // Remove ticket from database
    public void removeTicket(int ticketNumber) {
        
    }
    
    // Get ticket price
    public double getTicketPrice() {
    	return 0;
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
        return new Account();
    }
    
    // Update user info for account
    public void updateUserInfo(UserInfo userinfo, Account account) {
        
    }

    // Update card info for account
    public void updateCardInfo(CardInfo cardinfo, Account account) {
        
    }
}
