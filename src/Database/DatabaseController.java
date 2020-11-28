package Database;

import java.util.*;
import java.sql.*;
import java.text.*;
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
            String path = "jdbc:mysql://127.0.0.1:3306/db"; 
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(path, user, pass);  
        } catch(Exception e) {    
                System.out.println(e);
        }
    }
    
    // Query database to find movie matching movieName
    public Movie findMovie(String movieName) {
    	String n = "";
		String g = "";
		String rt = "";
    	int d = 0;
		int m = 0;
		int y = 0;

    	try {
    		String query = "SELECT * FROM db.movie WHERE Name=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setString(1, movieName);
    		rs = prepStmt.executeQuery();
    		while(rs.next()) {
    			n = rs.getString("Name");
				g = rs.getString("Genre");
				SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd HH:mm");
				String date = df.format(rs.getTimestamp("ReleaseDate"));
				String [] ds = date.split(" ");
				y = Integer.parseInt(ds[0]);
				m = Integer.parseInt(ds[1]);
				d = Integer.parseInt(ds[2]);
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
    	try {
    		String query = "SELECT * FROM db.showtime WHERE MovieName=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setString(1, movieName);
    		rs = prepStmt.executeQuery();
    		while(rs.next()) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd HH:mm");
				String date = df.format(rs.getTimestamp("ShowDate"));
				String [] ds = date.split(" ");
				y = Integer.parseInt(ds[0]);
				m = Integer.parseInt(ds[1]);
				d = Integer.parseInt(ds[2]);
    			st = ds[3];
    			showtimeList.add(new Showtime(new Date(d, m, y), st));
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
    		String query = "SELECT * FROM db.seat WHERE MovieName=? and ShowDate=?";
    		prepStmt = conn.prepareStatement(query);
			prepStmt.setString(1, movieName);
			SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd HH:mm");
			String dt = showtime.getDate().getYear() + " "+ 
						showtime.getDate().getMonth() + " " +
						showtime.getDate().getDay() + " " + 
						showtime.getStartTime();
    		prepStmt.setTimestamp(2, new Timestamp(df.parse(dt).getTime()));
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
    		String query = "UPDATE db.seat SET Available=? WHERE MovieName=? and ShowDate=? and Number=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setBoolean(1, avail);
			prepStmt.setString(2, movieName);
			SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd HH:mm");
			String dt = showtime.getDate().getYear() + " "+ 
						showtime.getDate().getMonth() + " " +
						showtime.getDate().getDay() + " " + 
						showtime.getStartTime();
    		prepStmt.setTimestamp(3, new Timestamp(df.parse(dt).getTime()));
    		prepStmt.setInt(4, seatNumber);
    		prepStmt.executeUpdate();
			prepStmt.close();
		} catch(Exception e) {
	        System.out.println(e);
		}
    }
    
    // Get ticket from database by everything but ticket number
    public Ticket getTicket(String movieName, Showtime showtime, Seat seat) {
    	int tn = 0;
    	int sn = 0;
    	int d = 0;
    	int m = 0;
    	int y = 0;
    	String st = "";
    	String mn = "";
    	double p = 0;
    	try {
    		String query = "SELECT * FROM db.ticket WHERE SeatNumber=? and Date=? and MovieName=?";
    		prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, seat.getSeatNumber());
			SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd HH:mm");
			String dt = showtime.getDate().getYear() + " "+ 
						showtime.getDate().getMonth() + " " +
						showtime.getDate().getDay() + " " + 
						showtime.getStartTime();
    		prepStmt.setTimestamp(2, new Timestamp(df.parse(dt).getTime()));
    		prepStmt.setString(3, movieName);
    		rs = prepStmt.executeQuery();
			while (rs.next()) {
				tn = rs.getInt("Number");
				sn = rs.getInt("SeatNumber");
				df = new SimpleDateFormat("yyyy MM dd HH:mm");
				String date = df.format(rs.getTimestamp("Date"));
				String [] ds = date.split(" ");
				y = Integer.parseInt(ds[0]);
				m = Integer.parseInt(ds[1]);
				d = Integer.parseInt(ds[2]);
				st = ds[3];
				mn = rs.getString("MovieName");
				p = rs.getDouble("Price");
			}
    		prepStmt.close();
			rs.close();
		} catch(Exception e) {
	        System.out.println(e);
		}
    	
    	return new Ticket(mn, sn, tn, new Showtime(new Date(d, m, y), st), p);
    }
    
    // Get ticket by ticket number 
    public Ticket getTicket(int ticketNumber) {
    	int tn = 0;
    	int sn = 0;
    	int d = 0;
    	int m = 0;
    	int y = 0;
    	String st = "";
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
				SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd HH:mm");
				String date = df.format(rs.getTimestamp("Date"));
				String [] ds = date.split(" ");
				y = Integer.parseInt(ds[0]);
				m = Integer.parseInt(ds[1]);
				d = Integer.parseInt(ds[2]);
				st = ds[3];
				mn = rs.getString("MovieName");
				p = rs.getDouble("Price");
			}
    		prepStmt.close();
			rs.close();
		} catch(Exception e) {
	        System.out.println(e);
		}
    	
    	return new Ticket(mn, sn, tn, new Showtime(new Date(d, m, y), st), p);
    }
    
    // Add purchased ticket from receipt
    public void addTicketReceipt(TicketReceipt ticketReceipt) {
        try {
        	String query = "INSERT INTO db.ticketreceipt VALUES(?)";
    		prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, ticketReceipt.getTicketNumber());
    		prepStmt.setTimestamp(2, "default");
    		prepStmt.executeUpdate();
			prepStmt.close();
        } catch(Exception e) {
	        System.out.println(e);
		}
    }

    // Remove purchased ticket from database
    public void removeTicketReceipt(int ticketNumber) {
    	try {
        	String query = "DELETE FROM db.ticketreceipt WHERE TicketNumber=?";
    		prepStmt = conn.prepareStatement(query);
    		prepStmt.setInt(1, ticketNumber);
    		prepStmt.executeUpdate();
			prepStmt.close();
        } catch(Exception e) {
	        System.out.println(e);
		}
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
	// Testing DatabaseController
	public static void main(String [] args){
		DatabaseController dc = new DatabaseController();
		Ticket t = dc.getTicket(1);
		System.out.println(t.getMovieName());
	}
}
