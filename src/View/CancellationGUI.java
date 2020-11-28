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
			
			boolean succesful = cc.cancel(ticketNo);
			
			if(successful)
			{
				CancelCofirmationGUI();
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
	
	class CancelConfirmationGUI() extends JFrame
	{
		public CancelConfirmationGUI()
		{
			JLabel message = new JLabel
		}
	}
	
	
	
	
	
}
