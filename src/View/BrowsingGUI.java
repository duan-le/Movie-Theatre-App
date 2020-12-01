package View;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.BrowsingController;
import Controller.MovieTheatreApp;
import Model.OrdinaryUser;

public class BrowsingGUI extends JFrame {
	
	private JFrame movieFrame, seatFrame, showtimeFrame;
	private JPanel bottom;
	private JButton okay, cancel;
	private JTextField movie, theatre, seat, showtime;
	private JTextArea textArea;
	private BrowsingController bc;
	private OrdinaryUser ou;
	
	public BrowsingGUI(String label, String movies, BrowsingController browsingController, OrdinaryUser ou) {
		super (label);
		
		this.bc = browsingController;
		this.ou = ou;
		
		textArea = new JTextArea ();
	
		displayMovies(movies);
	}

	public String getMovie () {
		return movie.getText();
	}
	
	public String getSeat () {
		return seat.getText();
	}
	
	public String getTheatre () {
		return theatre.getText();
	}
	
	public String getShowtime () {
		return showtime.getText();
	}
 	
	public void displayMovies (String movies) {
		
		movieFrame = new JFrame("Movies Showing");
		movieFrame.setSize(300, 300);
		movieFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		frame.add("North", new JLabel("Movies Showing", SwingConstants.CENTER));
		
		JScrollPane scroll = new JScrollPane(textArea);
		movie = new JTextField (10);
		bottom = new JPanel (new GridLayout (2, 2));
		okay = new JButton ("Okay");
		okay.addActionListener(new SelectMovieListener());
		cancel = new JButton ("Cancel");
		cancel.addActionListener(new CancelListener());
		bottom.add(new JLabel ("Select a Movie: "));
		bottom.add(movie);
		bottom.add(okay);
		bottom.add(cancel);
		movieFrame.add("Center", scroll);
		movieFrame.add("South", bottom);
		textArea.setText(movies);
		movieFrame.setVisible(true);
		
	}
	
	public void displaySeats (String seats, String movieName) {
				
		seatFrame = new JFrame("Browse Seats for " + movieName);
		seatFrame.setSize(500, 300);
		seatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.add("North", new JLabel("Available Seats", SwingConstants.CENTER));
		
		JTextArea textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		seat = new JTextField (10);
		bottom = new JPanel (new FlowLayout ());
		okay = new JButton ("Okay");
		okay.addActionListener(new SelectSeatListener());
		cancel = new JButton ("Cancel");
		cancel.addActionListener(new CancelListener());
		bottom.add(new JLabel ("Select a Seat: "));
		bottom.add(seat);
		bottom.add(okay);
		bottom.add(cancel);
		seatFrame.add("Center", scroll);
		seatFrame.add("South", bottom);
		textArea.setText(seats);
		seatFrame.setVisible(true);
	}
	
	public void displayShowtimes (String showtimes, String movieName) {
		
		showtimeFrame = new JFrame("Available Showtimes for " + movieName);
		showtimeFrame.setSize(500, 300);
		showtimeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scroll = new JScrollPane(textArea);
		showtime = new JTextField (10);
		bottom = new JPanel (new FlowLayout ());
		okay = new JButton ("Okay");
		okay.addActionListener(new SelectShowtimeListener());
		cancel = new JButton ("Cancel");
		cancel.addActionListener(new CancelListener());
		bottom.add(new JLabel ("Select a Showtime: "));
		bottom.add(showtime);
		bottom.add(okay);
		bottom.add(cancel);
		showtimeFrame.add("Center", scroll);
		showtimeFrame.add("South", bottom);
		textArea.setText(showtimes);
		showtimeFrame.setVisible(true);
		
	}
	
	/*
		public void displayTheatres (String theatres) {
		
		frame = new JFrame("Browse Theatres");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.add("North", new JLabel("Available Theatres", SwingConstants.CENTER));
		
		JScrollPane scroll = new JScrollPane(textArea);
		theatre = new JTextField (10);
		bottom = new JPanel (new GridLayout (2, 2));
		okay = new JButton ("Okay");
		cancel = new JButton ("Cancel");
		bottom.add(new JLabel ("Select a Theatre: "));
		bottom.add(theatre);
		bottom.add(okay);
		bottom.add(cancel);
		frame.add("Center", scroll);
		frame.add("South", bottom);
		textArea.setText(theatres);
		frame.setVisible(true);
	}
	*/
		
	public void displayInvalidMovie () {
		JFrame f = new JFrame ("Error");
		JOptionPane.showMessageDialog(f, "Unable to find movie you searched for.");
	}
	
	public void displayInvalidShowtime () {
		JFrame f = new JFrame ("Error");
		JOptionPane.showMessageDialog(f, "Unable to find movie you searched for.");
	}
	
	public void displayInvalidSeat () {
		JFrame f = new JFrame ("Error");
		JOptionPane.showMessageDialog(f, "Over 10% of seats are already booked for this movie.\nPlease select another showtime");
	}
	
	public void displayConfirmation () {
		dispose();
		JFrame f = new JFrame ("Confirmed");
		JOptionPane.showMessageDialog(f, "Ticket selected");
	}
	
	class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
	
	class SelectMovieListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				movieFrame.dispose();
				bc.selectMovie (ou);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	class SelectSeatListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			try {
				seatFrame.dispose();
				bc.selectSeat(ou);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	class SelectShowtimeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			try {
				showtimeFrame.dispose();
				bc.selectShowTime();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	class SelectTheatreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
