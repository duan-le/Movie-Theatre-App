package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.PaymentGUI.CancelListener;
import model.PaymentGUI.SubmitListener;

public class CancellationGUI extends JFrame {

	private JPanel panel;
	private JButton submit, cancel;
	private JTextField ticketNum;
	
	public CancellationGUI (String label) {
		super(label);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(230, 150);
		
		submit = new JButton("Submit Cancellation");
		cancel = new JButton("Cancel");
		
		ticketNum = new JTextField();
		
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
			
			String ticketNo = ticketNum.getText();
			
			//Database checks to see if ticketNo is valid
			
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
