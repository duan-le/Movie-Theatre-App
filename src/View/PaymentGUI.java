package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.PaymentController;
import Database.DatabaseController;
import Model.Account;
import Model.OrdinaryUser;
import Controller.MovieTheatreApp;


public class PaymentGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	
	private JFrame rpg, tcf, opg;
	
	private PaymentController pc;
	
	private OrdinaryUser ou;
	
	private JButton submit, cancel;
	
	private JTextField emailField, cardField, billingField;
	
	public PaymentGUI(OrdinaryUser u, PaymentController p)
	{
		ou = u;
		pc = p;
	}
	
	public void OrdPaymentGUI()
	{
		opg = new JFrame("Payment Information");
		JPanel center = new JPanel (new GridLayout (3, 2));
		JPanel buttons = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
		opg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		opg.setSize(500, 190);
		
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");
		
		JLabel cardLabel = new JLabel("Card Number: ");
		JLabel billingLabel = new JLabel("Billing Information: ");
		JLabel emailLabel = new JLabel("Email: ");
		
		emailField = new JTextField();
		cardField = new JTextField();
		billingField = new JTextField();		
		
		cardField.setPreferredSize(new Dimension(30, 10));
		emailField.setPreferredSize(new Dimension(30, 10));
		billingField.setPreferredSize(new Dimension(30, 10));
		
		center.add(cardLabel);
		center.add(cardField);
		center.add(emailLabel);
		center.add(emailField);
		center.add(billingLabel);
		center.add(billingField);
		
		submit.addActionListener(new SubmitListener());
		cancel.addActionListener(new CancelListenerOPG());
		
		buttons.add(submit);
		buttons.add(cancel);
		
		opg.add("Center", center);
		opg.add("South", buttons);
		opg.setVisible(true);
		
	}
	
	public void RegPaymentGUI()
	{
		rpg = new JFrame("Payment Successful");
		panel = new JPanel();
		JPanel buttons = new JPanel();
		panel.setLayout(new GridBagLayout());
		rpg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rpg.setSize(400, 100);
		
		JLabel message = new JLabel("Your account has been billed automatically");
		
		JButton okBut = new JButton("OK");
		
		okBut.addActionListener(new CancelListenerRPG());
		
		panel.add(message);
		buttons.add(okBut);
		
		rpg.add("Center", panel);
		rpg.add("South", buttons);
		
		
		rpg.setVisible(true);
	}
	
	class SubmitListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			String email = emailField.getText();
			String cardNum = cardField.getText();
			String bInfo = billingField.getText();
			
			
			String billingInfo = email + " " + cardNum + "  "+ bInfo;
			
			//Database checks to see if card info is valid
			try {
				pc.ordinaryPay(billingInfo);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			confirmationGUI("Ticket Purchase Confirmation");
			
			opg.dispose();
			
		}
		
	}
	
	class CancelListenerOPG implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			opg.dispose();
		}
		
	}
	
	class CancelListenerTCF implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			tcf.dispose();
		}
		
	}
	
	class CancelListenerRPG implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			rpg.dispose();
		}
		
	}
	
	public void confirmationGUI(String label)
	{
		tcf = new JFrame("Ticket Purchase Confirmation");
		JPanel confPanel = new JPanel();
		confPanel.setLayout(new GridBagLayout());
		tcf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tcf.setSize(450, 100);
		
		JLabel message = new JLabel("Your account has been billed and your receipt has been sent to your email");
		
		JButton okBut = new JButton("OK");
		JPanel button = new JPanel();
		
		okBut.addActionListener(new CancelListenerTCF());
		
		confPanel.add(message);
		button.add(okBut);
		
		tcf.add("Center", confPanel);
		tcf.add("South", button);
		tcf.setVisible(true);
	}
	
	/*public static void main(String [] args)
	{
		OrdinaryUser u = new OrdinaryUser();
		DatabaseController db = new DatabaseController();
		PaymentController p = new PaymentController(db);
		PaymentGUI pg = new PaymentGUI(u, p);
		pg.RegPaymentGUI();
		pg.OrdPaymentGUI();
		pg.confirmationGUI("hello");
	}*/
}
