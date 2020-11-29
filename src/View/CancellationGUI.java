package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import Controller.CancellationController;
import Database.DatabaseController;

public class CancellationGUI extends JFrame {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton submit, cancel;
	private JTextField ticketNum, cardField, emailField, billingField;
	private CancellationController cc;
	private DatabaseController db;
	private JLabel ticketNumLabel;
	
	public CancellationGUI (String label, CancellationController c)  {
		super(label);
		
		cc = c;
		
	}
	
	class SubmitListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			String ticketNo = ticketNum.getText();
			
			cc.ticketParse(ticketNo);
			
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
	
	class InfoProcessListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String e = emailField.getText();
			String c = cardField.getText();
			String b = billingField.getText();
			cc.billingInfoParse(e, c, b);
			dispose();
		}
	}
	
	public int getTicketNo()
	{
		//JFrame f = new JFrame("Enter Ticket Number");
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(230, 150);
		
		submit = new JButton("Submit Cancellation");
		cancel = new JButton("Cancel");
		
		ticketNum = new JTextField();
		
		submit.addActionListener(new SubmitListener());
		cancel.addActionListener(new CancelListener());
		
		ticketNumLabel = new JLabel("Ticket number: ");
		
		panel.add(ticketNumLabel);
		panel.add(ticketNum);
		panel.add(submit);
		panel.add(cancel);
		
		add("Center", panel);
		setVisible(true);
	}
	
	
		public void CancelConfirmationGUI()
		{
			JFrame ccf = new JFrame("Ticket Cancellation Confirmed");
			JPanel panel = new JPanel();
			JLabel message = new JLabel("Your ticket has been cancelled");
			JButton ok = new JButton("OK");
			ok.addActionListener(new CancelListener());
			
			panel.add(message);
			panel.add(ok);
			
			ccf.add("Center", panel);
			ccf.setVisible(true);
		}
		
		
	
	
	
	
		public void CancellationFailedGUI(String label)
		{
			JFrame tcf = new JFrame("Ticket Cancellation Failed");
			JPanel panel = new JPanel();
			JLabel message = new JLabel(label);
			JButton ok = new JButton("OK");
			ok.addActionListener(new CancelListener());
			
			panel.add(message);
			panel.add(ok);
			
			
			tcf.add("Center", panel);
			tcf.setVisible(true);
		}
		
		public static void OrdinaryCancelGUI()
		{
			JFrame ocg = new JFrame("Cancel Ticket");
			JPanel panel = new JPanel();
			
			JLabel cardLabel = new JLabel("Card Number: ");
			JLabel billingLabel = new JLabel("Billing Information: ");
			JLabel emailLabel = new JLabel("Email: ");
			
			cardField = new JTextField("Card Number");
			billingField = new JTextField("Billing Info");
			emailField = new JTextField("Email");
			
			JButton ok = new JButton("Submit");
			ok.addActionListener(new CancelListener());
			
			panel.add(cardLabel);
			panel.add(cardField);
			panel.add(emailLabel);
			panel.add(emailField);
			panel.add(billingLabel);
			panel.add(billingField);
			panel.add(ok);
			
			
			ocg.add("Center", panel);
			ocg.setVisible(true);
		}
		
		
		public static void RegCancelGUI(double refund)
		{
			JFrame rcg = new JFrame("Ticket Cancellation Processed");
			JPanel panel = new JPanel();
			JLabel message = new JLabel("$" + refund + " voucher sent to email");
			JButton ok = new JButton("OK");
			ok.addActionListener(new CancelListener());
			
			panel.add(message);
			panel.add(ok);
			
			
			rcg.add("Center", panel);
			rcg.setVisible(true);
		}
		
		
	
	
	
}