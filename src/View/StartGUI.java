package View;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.MovieTheatreApp;

public class StartGUI extends JFrame{

	/**
	 * The serializable ID of the class. Can be used for later
	 */
//	private static final long serialVersionUID = 3468142464946467036L;
	
	/**
	 * The main panel of on the frame.
	 */
	private JPanel panel;
	
	/**
	 * The buttons on the panel.
	 */
	private JButton browse, login, cancel, create, logout;
	
	private MovieTheatreApp movieTheatreApp;
	
	/**
	 * Registered user start gui
	 */
	public StartGUI (String label, MovieTheatreApp map) {	
		super(label);
		
		this.movieTheatreApp = map;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(180, 150);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		browse = new JButton("Browse Movies");
		cancel = new JButton("Cancel Ticket");
		logout = new JButton("Logout");
		
		browse.addActionListener(new BrowseListener());
		logout.addActionListener(new LogoutListener());
		cancel.addActionListener(new CancelListener());
		
		panel.add(browse);
		panel.add(cancel);
		panel.add(logout);
		
		add("Center", panel);
		setVisible(true);
		
	}
	
	/*
	 * Ordinary user main GUI
	 */
	public StartGUI (MovieTheatreApp map) {	
		super("Welcome");
		
		this.movieTheatreApp = map;
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(180, 150);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		browse = new JButton("Browse Movies");
		create = new JButton("Create an Account");
		cancel = new JButton("Cancel Ticket");
		login = new JButton("Login");
		
		browse.addActionListener(new BrowseListener());
		login.addActionListener(new LoginListener());
		create.addActionListener(new CreateListener());
		cancel.addActionListener(new CancelListener());
		
		panel.add(browse);
		panel.add(cancel);
		panel.add(create);
		panel.add(login);
		
		add("Center", panel);
		setVisible(true);
		
	}
	
	/**
	 * Listens for when the cancel button is pressed.
 	 */
	class CancelListener implements ActionListener {

		/**
		 * Calls the cancel method.
		 * @param e The even listened for.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				dispose();
				movieTheatreApp.cancel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}
	
	/**
	 * Listens for when the admin button is pressed.
 	 */
	class BrowseListener implements ActionListener {

		/**
		 * Calls the newAdmin method.
		 * @param e The even listened for.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				dispose();
				movieTheatreApp.browse();


			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}
	
	/**
	 * Listens for when the student button is pressed.
 	 */
	class LoginListener implements ActionListener {

		/**
		 * Calls the newStudent method.
		 * @param e The even listened for.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				dispose();
				movieTheatreApp.login();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class CreateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				dispose();
				movieTheatreApp.register();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	

	class LogoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				dispose();
				movieTheatreApp.logout();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}