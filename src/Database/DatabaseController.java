package Database;

import java.io.*;
import java.util.ArrayList;

import Model.*;
public class DatabaseController {
    Movie findMovie(String movieName){

        // query database to find movie
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
