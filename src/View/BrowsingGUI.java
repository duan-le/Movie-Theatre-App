package model;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.BrowsingController;
import controller.MovieTheaterApp;

public class BrowsingGUI extends JFrame {
	
	JFrame frame;
	private JPanel bottom;
	private JButton okay, cancel;
	private JTextField textField;
	private JTextArea textArea;
	private BrowsingController bc;
	
	public BrowsingGUI (String label, int n) {
		super (label);
		
		this.bc = new BrowsingController (this);
		
		textArea = new JTextArea ();
		textField = new JTextField (10);
		
		displayMovies();
		
	}
	
	public JTextField getTextField () {
		return textField;
	}
	
	public JButton getOkayButton () {
		return okay;
	}
	
	public JButton getCancelButton () {
		return cancel;
	}
	
	public JFrame getFrame () {
		return frame;
	}
 	
	public void displayMovies () {
		String str = "Need\nTo\nFigure\nOut\nHow\nTo\nConnect\nTo\nController\nand\nDatabase";
		
		frame = new JFrame("Browse Movies");
		frame.setSize(500, 300);
		
		frame.add("North", new JLabel("Movies Showing", SwingConstants.CENTER));
		
		JScrollPane scroll = new JScrollPane(textArea);
		textField = new JTextField (10);
		bottom = new JPanel (new FlowLayout ());
		okay = new JButton ("Okay");
		cancel = new JButton ("Cancel");
		bottom.add(new JLabel ("Select a Movie: "));
		bottom.add(textField);
		bottom.add(okay);
		bottom.add(cancel);
		frame.add("Center", scroll);
		frame.add("South", bottom);
		textArea.setText(str);
		frame.setVisible(true);
		
//		BrowsingController bc = new BrowsingController (this);
		bc.browseMovies();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void displaySeats () {
		String str = "Need\nTo\nFigure\nOut\nHow\nTo\nConnect\nTo\nController\nand\nDatabase";
		
		frame = new JFrame("Browse Seats");
		frame.setSize(500, 300);
		
		frame.add("North", new JLabel("Available Seats", SwingConstants.CENTER));
		
		JTextArea textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		textField = new JTextField (10);
		bottom = new JPanel (new FlowLayout ());
		okay = new JButton ("Okay");
		cancel = new JButton ("Cancel");
		bottom.add(new JLabel ("Select a Seat: "));
		bottom.add(textField);
		bottom.add(okay);
		bottom.add(cancel);
		frame.add("Center", scroll);
		frame.add("South", bottom);
		textArea.setText(str);
		frame.setVisible(true);
		
//		BrowsingController bc = new BrowsingController (this);
		bc.browseSeats();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void displayShowtimes () {
		String str = "Need\nTo\nFigure\nOut\nHow\nTo\nConnect\nTo\nController\nand\nDatabase";
		
		frame = new JFrame("Browse Showtimes");
		frame.setSize(500, 300);
		
		frame.add("North", new JLabel("Available Showtimes", SwingConstants.CENTER));

		JScrollPane scroll = new JScrollPane(textArea);
		textField = new JTextField (10);
		bottom = new JPanel (new FlowLayout ());
		okay = new JButton ("Okay");
		cancel = new JButton ("Cancel");
		bottom.add(new JLabel ("Select a Showtime: "));
		bottom.add(textField);
		bottom.add(okay);
		bottom.add(cancel);
		frame.add("Center", scroll);
		frame.add("South", bottom);
		textArea.setText(str);
		frame.setVisible(true);
		
//		BrowsingController bc = new BrowsingController (this);
		bc.browseShowtimes();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
		public void displayTheatres () {
		String str = "Need\nTo\nFigure\nOut\nHow\nTo\nConnect\nTo\nController\nand\nDatabase";
		
		frame = new JFrame("Browse Theatres");
		frame.setSize(500, 300);
		
		frame.add("North", new JLabel("Available Theatres", SwingConstants.CENTER));
		
		JScrollPane scroll = new JScrollPane(textArea);
		textField = new JTextField (10);
		bottom = new JPanel (new FlowLayout ());
		okay = new JButton ("Okay");
		cancel = new JButton ("Cancel");
		bottom.add(new JLabel ("Select a Theatre: "));
		bottom.add(textField);
		bottom.add(okay);
		bottom.add(cancel);
		frame.add("Center", scroll);
		frame.add("South", bottom);
		textArea.setText(str);
		frame.setVisible(true);
		
//		BrowsingController bc = new BrowsingController (this);
		bc.browseTheatres();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
