package model;

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


public class AccountGUI extends JFrame {
	
	private JPanel panel;

	
	private JButton login, cancel, createAccount;
	
	private JTextField emailField, passwordField, newEmailField, newPassField;
	
	public String email;
	public String password;

	public AccountGUI(String label)
	{
		super(label);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(230, 150);
	
		createAccount = new JButton("Create Account");
		login = new JButton("Login");
		emailField = new JTextField();
		passwordField = new JTextField();
		newEmailField = new JTextField();
		newPassField = new JTextField();
		cancel = new JButton("Cancel");
		
		
		login.addActionListener(new LoginListener());
		createAccount.addActionListener(new CreateAccountListener());
		cancel.addActionListener(new CancelListener());
		
		panel.add(emailField);
		panel.add(passwordField);
		panel.add(newEmailField);
		panel.add(newPassField);
		panel.add(createAccount);
		panel.add(login);
		panel.add(cancel);
		
		add("Center", panel);
		setVisible(true);
		
		
	}
	
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
	
	class AccountMenuGUI
	{
		
	}
}
