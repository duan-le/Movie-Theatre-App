package View;

import java.awt.Dimension;
import java.awt.GridLayout;
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
	
	static JFrame f;
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton submit, cancel, ok;
	private JTextField ticketNum;
	private static JTextField cardField;
	private static JTextField emailField;
	private static JTextField billingField;
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
			
			f.dispose();
		}
		
	}
	
	class CancelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//f.setVisible(false);
			f.dispose();
		}
		
	}
	
	
	class InfoProcessListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String em = emailField.getText();
			String c = cardField.getText();
			String b = billingField.getText();
			cc.billingInfoParse(em, c, b);
			dispose();
		}
	}
	
	public void getTicketNo()
	{
		f = new JFrame("Ticket Information");
		f.setSize(300, 300);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		
		submit = new JButton("Submit Cancellation");
		cancel = new JButton("Cancel");
		
		ticketNum = new JTextField(1);
		
		submit.addActionListener(new SubmitListener());
		cancel.addActionListener(new CancelListener());
		
		ticketNumLabel = new JLabel("Ticket number: ");
		
		JPanel buttons = new JPanel();
		
		panel.add(ticketNumLabel);
		panel.add(ticketNum);
		buttons.add(submit);
		buttons.add(cancel);
		
		f.add("Center", panel);
		f.add("South", buttons);
		f.setVisible(true);
	}
	
	
		public void CancelConfirmationGUI()
		{
			f = new JFrame("Ticket Cancellation Confirmed");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize(350, 100);
			JPanel panel = new JPanel();
			JPanel buttons = new JPanel();
			JLabel message = new JLabel("Your ticket has been cancelled");
			ok = new JButton("OK");
			ok.addActionListener(new CancelListener());
			
			panel.add(message);
			buttons.add(ok);
			
			f.add("Center", panel);
			f.add("South", buttons);
			f.setVisible(true);
		}
		
		
	
	
	
	
		public void CancellationFailedGUI(String label)
		{
			f = new JFrame("Ticket Cancellation Failed");
			f.setSize(300, 100);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel panel = new JPanel();
			JLabel message = new JLabel(label);
			JButton ok = new JButton("OK");
			ok.addActionListener(new CancelListener());
			
			panel.add(message);
			
			f.add("Center", panel);
			f.add("South", ok);
			f.setVisible(true);
		}
		
		public static void OrdinaryCancelGUI()
		{
			f = new JFrame("Cancel Ticket");
			f.setSize(500, 170);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel center = new JPanel();
			JPanel panel = new JPanel();
			
			center= new JPanel (new GridLayout (3, 2));
			
			JLabel cardLabel = new JLabel("Card Number: ");
			JLabel billingLabel = new JLabel("Billing Information: ");
			JLabel emailLabel = new JLabel("Email: ");
			
			cardField = new JTextField();
			billingField = new JTextField();
			emailField = new JTextField();
			
			cardField.setPreferredSize(new Dimension(30, 10));
			emailField.setPreferredSize(new Dimension(30, 10));
			billingField.setPreferredSize(new Dimension(30, 10));
			
			JButton smit = new JButton("Submit");
			smit.addActionListener(new SubmitListener());
			
			center.add(cardLabel);
			center.add(cardField);
			center.add(emailLabel);
			center.add(emailField);
			center.add(billingLabel);
			center.add(billingField);
			panel.add(smit);
			
			f.add("Center", center);
			f.add("South", panel);
			f.setVisible(true);
		}
		
		
		public static void RegCancelGUI(double refund)
		{
			f = new JFrame("Ticket Cancellation Processed");
			f.setSize(300, 200);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel panel = new JPanel();
			JLabel message = new JLabel("$" + refund + " voucher sent to email");
			JButton ok = new JButton("OK");
			ok.addActionListener(new CancelListener());
			
			panel.add(message);			
			
			f.add("Center", panel);
			f.add("South", ok);
			f.setVisible(true);
		}
		
		/*public static void main(String[] args)
		{
			DatabaseController db = new DatabaseController();
			CancellationController c = new CancellationController(db);
			CancellationGUI cd = new CancellationGUI("Cancellation", c);
			
			//cd.CancelConfirmationGUI();
			//cd.CancellationFailedGUI("Failed");
			//cd.getTicketNo();
			//cd.RegCancelGUI(45.00);
			cd.OrdinaryCancelGUI();
		}*/
	
	
	
}

