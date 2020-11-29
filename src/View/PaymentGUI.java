package model;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.PaymentController;
import Model.Account;
import controller.MovieTheaterApp;


public class PaymentGUI extends JFrame{
	
	private JPanel panel;
	
	private PaymentController pc;
	
	private JButton submit, cancel;
	
	private JTextField emailField, cardField, expiryField, cvvField;
	
	public PaymentGUI(String label, PaymentController p)
	{
		super(label);
		
		pc = p;
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(230, 150);
		
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");
		
		emailField = new JTextField();
		cardField = new JTextField();
		expiryField = new JTextField();
		cvvField = new JTextField();		
		
		submit.addActionListener(new SubmitListener());
		cancel.addActionListener(new CancelListener());
		
		panel.add(submit);
		panel.add(cancel);
		
		add("Center", panel);
		setVisible(true);
		
	}
	
	public PaymentGUI(String label, Account a, PaymentController p)
	{
		pc = p;
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(230, 150);
		
		JLabel message = new JLabel("Your account has been billed automatically");
		
		JButton okBut = new JButton("OK");
		
		okBut.addActionListener(new CancelListener());
		
		panel.add(okBut);
		panel.add(message);
		
		add("Center", panel);
		setVisible(true);
	}
	
	class SubmitListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			String email = emailField.getText();
			String cardNum = cardField.getText();
			String cvv = cvvField.getText();
			String expiry = expiryField.getText();
			
			String billingInfo = email + cardNum + cvv + expiry;
			
			//Database checks to see if card info is valid
			pc.ordinaryPay();
			
			confirmationGUI("Ticket Purchase Confirmation");
			
			dispose();
			
		}
		
	}
	
	class CancelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
		
	}
	
	public void confirmationGUI(String label)
	{
		JPanel confPanel = new JPanel();
		confPanel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(230, 150);
		
		JLabel message = new JLabel("Your account has been billed and your receipt has been sent to your email");
		
		JButton okBut = new JButton("OK");
		
		okBut.addActionListener(new CancelListener());
		
		panel.add(okBut);
		panel.add(message);
		
		add("Center", panel);
		setVisible(true);
	}
	
	
}

