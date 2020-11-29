package View;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.AccountController;
import Model.OrdinaryUser;

import javax.swing.JPasswordField;


public class AccountGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private LoginGUI loginGUI;
//	private RegisterGUI registerGUI;
	private JLabel flname, address, phoneNum, billingName, billingAddress, billingNum, cardNumber, cardName, email, password;
	private JTextField name, addr, phono, bname, baddr, bphono, cardnum, chn, mail, pass;
	private JButton okay, cancel;
	private JPasswordField passw;
	private AccountController ac;
	private OrdinaryUser ou;
	
	private JPanel mainpanel, userInfo, billingInfo, cardInfo, loginInfo, buttons;

	public AccountGUI(String label, AccountController ac)
	{
		super (label);
		
		this.ac = ac;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 800);
		
		mainpanel = new JPanel (new GridLayout (10, 1));
		mainpanel.add(new JLabel("Account Information", SwingConstants.CENTER));
		userInfo = new JPanel(new GridLayout (3, 2));
		
		billingInfo = new JPanel(new GridLayout (3, 2));
		
		cardInfo = new JPanel(new GridLayout (2, 2));
		
		loginInfo = new JPanel(new GridLayout (2, 2));
		
		buttons = new JPanel(new FlowLayout());
		
		okay = new JButton ("Confirm Registration");
		cancel = new JButton ("Cancel");
		
		flname = new JLabel ("Name: ");
		name = new JTextField ();
		address = new JLabel ("Address: ");
		addr = new JTextField ();
		phoneNum = new JLabel ("Phone Number: ");
		phono = new JTextField ();
		billingName = new JLabel ("Name: ");
		bname = new JTextField ();
		billingAddress = new JLabel ("Billing Address: ");
		baddr = new JTextField ();
		billingNum = new JLabel ("Billing Phone Number: ");
		bphono = new JTextField ();
		cardNumber = new JLabel ("Card Number: ");
		cardnum = new JTextField ();
		cardName = new JLabel ("Card Holder Name: ");
		chn = new JTextField ();
		email = new JLabel("Email: ");
		mail = new JTextField ();
		password = new JLabel ("Password: ");
		pass = new JTextField ();
		
		userInfo.add(flname);
		userInfo.add(name);
		userInfo.add(address);
		userInfo.add(addr);
		userInfo.add(phoneNum);
		userInfo.add(phono);
		
		billingInfo.add(billingName);
		billingInfo.add(bname);
		billingInfo.add(billingAddress);
		billingInfo.add(baddr);
		billingInfo.add(billingNum);
		billingInfo.add(bphono);
		
		cardInfo.add(cardNumber);
		cardInfo.add(cardnum);
		cardInfo.add(cardName);
		cardInfo.add(chn);
		
		loginInfo.add(email);
		loginInfo.add(mail);
		loginInfo.add(password);
		loginInfo.add(pass);
		
		buttons.add(okay);
		buttons.add(cancel);
		
		okay.addActionListener(new CreateListener());
		cancel.addActionListener(new CancelListener());
		
		mainpanel.add(new JLabel ("User Information", SwingConstants.LEFT));
		mainpanel.add(userInfo);
		mainpanel.add(new JLabel ("Billing Information", SwingConstants.LEFT));
		mainpanel.add(billingInfo);
		mainpanel.add(new JLabel ("Card Information", SwingConstants.LEFT));
		mainpanel.add(cardInfo);
		mainpanel.add(new JLabel ("Login Information", SwingConstants.LEFT));
		mainpanel.add(loginInfo);
		mainpanel.add(buttons);
		
		add (mainpanel);
		
		setVisible(true);
		
	}
	
	public AccountGUI (String label, AccountController ac, OrdinaryUser ou) {
		super (label);
		
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ac = ac;
		this.ou = ou;
		
		mainpanel = new JPanel (new GridLayout (3, 1));
		mainpanel.add(new JLabel("Enter Login Information", SwingConstants.CENTER));
		loginInfo = new JPanel (new GridLayout (2, 2));
		buttons = new JPanel(new FlowLayout());
		
		email = new JLabel("Email: ");
		mail = new JTextField ();
		password = new JLabel ("Password: ");
		pass = new JTextField ();
		
		okay = new JButton ("Login");
		cancel = new JButton ("Cancel");
		okay.addActionListener(new LoginListener());
		cancel.addActionListener(new CancelListener());
		
		loginInfo.add(email);
		loginInfo.add(mail);
		loginInfo.add(password);
		loginInfo.add(pass);
		buttons.add(okay);
		buttons.add(cancel);
		
		mainpanel.add(loginInfo);
		mainpanel.add(buttons);
		
		add (mainpanel);
		
		setVisible(true);
		
	}
	
	public JButton getOkayButton () {
		return okay;
	}
	
	public JButton getCancelButton () {
		return cancel;
	}
	
	public String getName () {
		return name.getText();
	}
	
	public String getAddress () {
		return addr.getText();
	}
	
	public String getPhoneNum () {
		return phono.getText();
	}
	
	public String getBillingName () {
		return bname.getText();
	}
	
	public String getBillingAddress () {
		return baddr.getText();
	}
	
	public String getBillingPhoneNum () {
		return bphono.getText();
	}
	
	public int getCardNum () {
		return Integer.parseInt(cardnum.getText());
	}
	
	public String getCardName () {
		return chn.getText();
	}
	
	public String getEmail () {
		return mail.getText();
	}
	
	public String getPassword () {
		return pass.getText();
	}
	
//	public String getPass () {
//		return String.valueOf(passw.getPassword());
//	}
	
	public String getMail () {
		return email.getText();
	}

	public void displayInvalidRegistration() {
		JFrame f = new JFrame ("Error Message");
		JOptionPane.showMessageDialog(f, "Unable to process your registration.");
	}
	
	public void displayConfirmedRegistration(String date) {
		JFrame f = new JFrame ("Confirmation");
		JOptionPane.showMessageDialog(f, "Account successfully created on " + date + ". You were billed your $20 annual account fee and "
				+ "will automatically be billed annually billed. ");
	}
	
	public void displayLoginConfirmation () {
		JFrame f = new JFrame ("Logged in");
		JOptionPane.showMessageDialog(f, "Welcome back. Start browsing movies.");
	}
	
	public void displayInvalidLogin () {
		JFrame f = new JFrame ("Error Message");
		JOptionPane.showMessageDialog(f, "Invalid account credentials.");
	}
	
	class CreateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Aut
			dispose();
			try {
				ac.register();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
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
			dispose();
		}	
	}
	
	class LoginListener implements ActionListener {

		/**
		 * Calls the cancel method.
		 * @param e The even listened for.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			ac.checkLogin(ou);
		}	
	} 

}