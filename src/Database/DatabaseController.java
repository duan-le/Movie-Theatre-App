package Database;

import java.io.*;
import java.util.ArrayList;
import java.sql.*;
import Model.*;
public class DatabaseController {
    private Connection db_con;
    private Statement stmt;
    public DatabaseController(){
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            String path = ""; // e.g. jdbc:mysql://localhost:3306/sonoo
            String user = ""; // e.g. root
            String pass = "";
            db_con = DriverManager.getConnection(path, user, pass);  
            stmt = db_con.createStatement();  

        } catch(Exception e){    
                System.out.println(e);
        }     
     }
    Movie findMovie(String movieName) throws Exception{

        // query database to find movie
        String query = "select * from Movie" +
                        "from Movie" +
                        "where Movie.name = " + movieName;
        ResultSet rs=stmt.executeQuery(query);  

        return new Movie();
    }

    ArrayList<Showtime> getAllShowtimes(String movieName){
        //query database to find show times for movie
        return new ArrayList<Showtime>();
    }

    ArrayList<Showtime> getAllSeats(String movieName, Showtime showtime){
        //query to get all seats for a movie
        return new ArrayList<Showtime>();
    }

    void addTicket(Ticket ticket){
        // add ticket to database
    }

    void updateSeat(String movieName, Showtime showtime){
        // update Seat e.g. boolean filled seat 
    }

    void updateUserInfo(UserInfo userinfo, Account account) {
        // update user info for account
    }

    void updateCardInfo(CardInfo cardinfo, Account account){
        // update card info for account
    }

    void addAccount(Account account) {
        // add account
    }

    void removeTicket(int ticketNumber){
        // remove ticket from database
    }

    void addPurchasedTicket(TicketReceipt purchasedTicket){
        // add purchased ticket from receipt
    }

    void removePurchasedTicket(int ticketNumber){
        // remove purchased ticket from database
    }
}
