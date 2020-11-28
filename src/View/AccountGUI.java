package View;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;


public class AccountGUI {
	
	private AccountController accountController;
	
	private JPanel panel;
	
	private JTextField emailField, newPassField;
	
	private JPasswordField password;
	
	public String email;
	public String password;

	public AccountGUI(String label, AccountController ac)
	{
		super(label);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(230, 150);
	
		emailField = new JTextField();
		passwordField = new JTextField();
		cancel = new JButton("Cancel");
		
		login.addActionListener(new LoginListener());
		createAccount.addActionListener(new CreateAccountListener());
		cancel.addActionListener(new CancelListener());
		
//		panel.add(emailField);
//		panel.add(passwordField);
//		panel.add(newEmailField);
//		panel.add(newPassField);
//		panel.add(createAccount);
//		panel.add(login);
//		panel.add(cancel);
		
		add("Center", panel);
		setVisible(true);
	}
	
	class LoginGUI extends JFrame {
		
		JLabel firstName, lastName, address, phoneNum, billingName, billingAddress, billingNum, cardNumber, cardName;
		JTextField fname, lname, addr, phono, bname, baddr, bphono, cardnum, chn;
		JButton okay, cancel;
		
		LoginGUI (String label AccountGUI ag, AccountController ac) {
			super (label);
			
			firstName = new JLabel ("First Name: ");
			fname = new JTextField ();
			lastName = new JLabel ("Last Name: ");
			lname = new JTextField ();
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
			JTextField chn = new JTextField ();
			
		}
		
		public JButton getOkayButton () {
			return cancel;
		}
	}
	
	
	public void determineAction (int n) {
		if (n == 0)
			createAccount();
		else
			login();
	}
	
	
	public JTextField getEmail () {
		return this.emailField;
	}
	
	public JPasswordField getLoginPass () {
		return this.password;
	}
	
	public JTextField getNewPass () {
		return this.newPassField;
	}
	
	public void createAccount() {
		
		JLabel firstName = new JLabel ("First Name: ");
		JTextField fname = new JTextField ();
		JLabel lastName = new JLabel ("Last Name: ");
		JTextField lname = new JTextField ();
		JLabel address = new JLabel ("Address: ");
		JTextField addr = new JTextField ();
		JLabel phoneNum = new JLabel ("Phone Number: ");
		JTextField phono = new JTextField ();
		JLabel billingName = new JLabel ("Name: ");
		JTextField bname = new JTextField ();
		JLabel billingAddress = new JLabel ("Billing Address: ");
		JTextField baddr = new JTextField ();
		JLabel billingNum = new JLabel ("Billing Phone Number: ");
		JTextField bphono = new JTextField ();
		JLabel cardNumber = new JLabel ("Card Number: ");
		JTextField cardnum = new JTextField ();
		JLabel cardName = new JLabel ("Card Holder Name: ");
		JTextField chn = new JTextField ();
		JLabel  = new JLabel ();
		JTextField  = new JTextField ();
		
		
		
	}
	
	public void login () {
		emailField
		password = new JPasswordField ();
	}
	
	/*
	class CancelListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
	}
	
	class CreateAccountListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			String newEmail = newEmailField.getText();
			String newPass = newPassField.getText();
			// add account to database and perform checks
		}

	}
	
	class LoginListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			String email = emailField.getText();
			String password = passwordField.getText();
			// perform DB checks to make sure that login info is correct
			new AccountMenuGUI();
			dispose();
		}
		
	}
	*/
	
}
