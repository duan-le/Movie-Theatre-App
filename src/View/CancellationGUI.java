package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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
	
	 JFrame f, ocg, gtg, rcg, cfg, ccg;
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton submit, cancel, ok;
	private static JTextField ticketNum;
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
			
			gtg.dispose();
		}
		
	}
	
	class CancelListenerRCG implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			rcg.dispose();
			cc.mta.restart();
		}
		
	}
	
	class CancelListenerGTG implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gtg.dispose();
			cc.mta.restart();
		}
		
	}
	
	class CancelListenerCCG implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ccg.dispose();
			cc.mta.restart();
		}
		
	}
	
	class CancelListenerCFG implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			cfg.dispose();
			cc.mta.restart();
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
			ocg.dispose();
		}
	}
	
	public void getTicketNo()
	{
		gtg = new JFrame("Ticket Information");
		gtg.setSize(300, 120);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		gtg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		JButton submitCanc = new JButton("Submit Cancellation");
		cancel = new JButton("Cancel");
		
		ticketNum = new JTextField();
		
		submitCanc.addActionListener(new SubmitListener());
		cancel.addActionListener(new CancelListenerGTG());
		
		ticketNumLabel = new JLabel("Ticket number: ");
		
		JPanel buttons = new JPanel();
		
		panel.add(ticketNumLabel);
		panel.add(ticketNum);
		buttons.add(submitCanc);
		buttons.add(cancel);
		
		gtg.add("Center", panel);
		gtg.add("South", buttons);
		gtg.setVisible(true);
	}
	
	
		public void CancelConfirmationGUI()
		{
			ccg = new JFrame("Ticket Cancellation Confirmed");
			ccg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ccg.setSize(350, 100);
			JPanel panel = new JPanel();
			JPanel buttons = new JPanel();
			JLabel message = new JLabel("Your ticket has been cancelled");
			JButton okay = new JButton("OK");
			okay.addActionListener(new CancelListenerCCG());
			
			panel.add(message);
			buttons.add(okay);
			
			ccg.add("Center", panel);
			ccg.add("South", buttons);
			ccg.setVisible(true);
		}
		
		
	
	
	
	
		public void CancellationFailedGUI(String label)
		{
			cfg = new JFrame("Ticket Cancellation Failed");
			cfg.setSize(400, 100);
			cfg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel panel = new JPanel();
			JPanel buttons = new JPanel();
			JLabel message = new JLabel(label);
			JButton okay = new JButton("OK");
			okay.addActionListener(new CancelListenerCFG());
			
			panel.add(message);
			buttons.add(okay);
			
			cfg.add("Center", panel);
			cfg.add("South", buttons);
			cfg.setVisible(true);
		}
		
		public void OrdinaryCancelGUI()
		{
			ocg = new JFrame("Cancel Ticket");
			ocg.setSize(500, 170);
			ocg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			
			submit = new JButton("Submit");
			submit.addActionListener(new InfoProcessListener());
			
			center.add(cardLabel);
			center.add(cardField);
			center.add(emailLabel);
			center.add(emailField);
			center.add(billingLabel);
			center.add(billingField);
			panel.add(submit);
			
			ocg.add("Center", center);
			ocg.add("South", panel);
			ocg.setVisible(true);
		}
		
		
		public void RegCancelGUI(double refund, Date exp)
		{
			rcg = new JFrame("Ticket Cancellation Processed");
			rcg.setSize(400, 100);
			rcg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel panel = new JPanel();
			JPanel buttons = new JPanel();
			JLabel message = new JLabel("$" + String.format("%.2f", refund) + " voucher sent to email. Expires: " + exp);
			JButton okay = new JButton("OK");
			okay.addActionListener(new CancelListenerRCG());
			
			panel.add(message);			
			buttons.add(okay);
			
			rcg.add("Center", panel);
			rcg.add("South", buttons);
			rcg.setVisible(true);
		}
		
		/*public static void main(String[] args)
		{
			DatabaseController db = new DatabaseController();
			CancellationController c = new CancellationController(db);
			CancellationGUI cd = new CancellationGUI("Cancellation", c);
			
			cd.CancelConfirmationGUI();
			cd.CancellationFailedGUI("Your ticket cancellation could not be processed");
			cd.getTicketNo();
			cd.RegCancelGUI(45.00);
			cd.OrdinaryCancelGUI();
		}*/
	
	
	
}

