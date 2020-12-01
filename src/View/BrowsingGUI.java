package View;

import java.awt.Color;
import java.awt.Dimension;
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
import Controller.BrowsingController;
import Model.OrdinaryUser;

public class BrowsingGUI extends JFrame {
	
	private JFrame frame;
	private JPanel bottom, seats, main;
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
		seats = new JPanel (new GridLayout(0, 10));
		seats.setPreferredSize(new Dimension(300,30));
	
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
		
		frame = new JFrame("Movies Showing");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		frame.add("Center", scroll);
		frame.add("South", bottom);
		textArea.setText(movies);
		frame.setVisible(true);
		
	}
	
	public void displaySeats (String movieName) {
				
		frame = new JFrame("Browse Seats for " + movieName);
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main = new JPanel (new GridLayout(2, 1));
		
//		JTextArea textArea = new JTextArea();
//		JScrollPane scroll = new JScrollPane(textArea);
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
		main.add(seats);
		main.add(bottom);
		frame.add(main);
//		frame.add("Center", scroll);
//		frame.add("South", bottom);
//		textArea.setText(s);
		frame.setVisible(true);
	}
	
	/*
	 * adds seats to the frame based on their availability
	 */
	public void addSeatsToFrame (int i, Color c) {
		JPanel f = new JPanel();
		f.setPreferredSize(new Dimension(30, 30));
		f.setBackground(c);
		f.add(new JLabel (String.valueOf(i), JLabel.CENTER));
		seats.add(f);
	}
	
	public void displayShowtimes (String showtimes, String movieName) {
		
		frame = new JFrame("Available Showtimes for " + movieName);
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		frame.add("Center", scroll);
		frame.add("South", bottom);
		textArea.setText(showtimes);
		frame.setVisible(true);
	}
		
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
	
	public void displayConfirmation (double price) {
		dispose();
		JFrame f = new JFrame ("Confirmed");
		JOptionPane.showMessageDialog(f, "Ticket selected. Total: $" + String.format("%.2f", price));
	}
	
	class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			bc.getMTA().restart();
		}
		
	}
	
	class SelectMovieListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				frame.dispose();
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
				frame.dispose();
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
				frame.dispose();
				bc.selectShowTime();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
}
