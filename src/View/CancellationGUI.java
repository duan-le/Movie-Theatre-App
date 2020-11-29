package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.CancellationController;
import Database.DatabaseController;
import model.PaymentGUI.CancelListener;
import model.PaymentGUI.SubmitListener;

public class CancellationGUI extends JFrame {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton submit, cancel;
	private JTextField ticketNum;
	private CancellationController cc;
	private DatabaseController db;
	private JLabel ticketNumLabel;
	
	public CancellationGUI (String label, CancellationController c) {
		super(label);
		
		cc = c;
		
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
	
	class SubmitListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			String ticketNo = ticketNum.getText();
			
			boolean successful = cc.cancel(ticketNo);
			
			if(successful)
			{
				CancelConfirmationGUI();
			}
			else
			{
				CancellationFailedGUI();
			}
			
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
			setVisible(true);
		}
		
		
	
	
	
	
		public void CancellationFailedGUI()
		{
			JFrame tcf = new JFrame("Ticket Cancellation Failed");
			JPanel panel = new JPanel();
			JLabel message = new JLabel("Your ticket could not be cancelled, please try again");
			JButton ok = new JButton("OK");
			ok.addActionListener(new CancelListener());
			
			panel.add(message);
			panel.add(ok);
			
			
			tcf.add("Center", panel);
			setVisible(true);
		}
		
		
	
	
	
}