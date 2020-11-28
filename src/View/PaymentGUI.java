package model;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.MovieTheaterApp;


public class PaymentGUI extends JFrame{
	
	private JPanel panel;
	
	private JButton submit, cancel;
	
	private JTextField emailField, cardField, expiryField, cvvField;
	
	public PaymentGUI(String label)
	{
		super(label);
		
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
	
	class SubmitListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			String email = emailField.getText();
			String cardNum = cardField.getText();
			String cvv = cvvField.getText();
			String expiry = expiryField.getText();
			
			//Database checks to see if card info is valid
			
			ConfirmationField();
			
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
	
}

