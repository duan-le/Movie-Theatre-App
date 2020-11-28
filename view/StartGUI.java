package model;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MovieTheaterApp;

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
	private JButton browse, login, cancel, create;
	
	/**
	 * Creates the frame and initializes all components on it.
	 */
	public StartGUI (String label) {	
		super(label);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(230, 150);
		
		browse = new JButton("Browse Movies as a Guest");
		create = new JButton("Create an Account");
		login = new JButton("Login");
		cancel = new JButton("Cancel");
		
		browse.addActionListener(new GuestListener());
		login.addActionListener(new LoginListener());
		create.addActionListener(new CreateListener());
		cancel.addActionListener(new CancelListener());
		
		panel.add(browse);
		panel.add(create);
		panel.add(login);
		panel.add(cancel);
		
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
			
			cancel();
		}	
	}
	
	/**
	 * Disposes of the frame and ends the program.
	 */
	public void cancel() {
		dispose();
		System.exit(1);
	}
	
	/**
	 * Listens for when the admin button is pressed.
 	 */
	class GuestListener implements ActionListener {

		/**
		 * Calls the newAdmin method.
		 * @param e The even listened for.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			newBrowse();
		}	
	}
	
	/**
	 * Disposes of this frame and starts a new browsing frame.
	 */
	public void newBrowse() {
		dispose();
		new BrowsingGUI("Browse Movies", 0);
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
			
			newLogin();
		}
	}
	
	/**
	 * Disposes of this frame and starts a new studentLogin frame.
	 */
	public void newLogin() {
		dispose();
		System.out.println("Properly disposed");
		new AccountGUI("Login");
	}
	
	class CreateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			newCreate();
		}
	}
	
	public void newCreate () {
		dispose();
		new AccountGUI ("Create Account");
	}
	
}
