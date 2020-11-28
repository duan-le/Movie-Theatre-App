package View;

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
import javax.swing.JPasswordField;


public class AccountGUI extends JFrame {
	
	private AccountController accountController;
	private LoginGUI loginGUI;
	private RegisterGUI registerGUI;
	
	private JPanel userInfo, billingInfo, cardInfo, loginInfo, buttons;

	public AccountGUI(String label, int n)
	{
		super (label);
		
		determineAction(n);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 800);
		setVisible(true);
	}
	
	public void determineAction (String label, int n) {
		if (n == 0)
			registerGUI = new RegisterGUI(this);
		else
			loginGUI = new LoginGUI (label, n);
	}
	
	
	class RegisterGUI {
		
		AccountGUI accountGUI;
		JLabel flname, address, phoneNum, billingName, billingAddress, billingNum, cardNumber, cardName, email, password;
		JTextField name, addr, phono, bname, baddr, bphono, cardnum, chn, mail, pass;
		
		JButton okay, cancel;
		
		RegisterGUI (AccountGUI ag) {
			
			this.accountGUI = ag;
			
			ag.userInfo = new JPanel(new GridLayout (0, 2));
			ag.billingInfo = new JPanel(new GridLayout (0, 2));
			ag.cardInfo = new JPanel(new GridLayout (0, 2));
			ag.loginInfo = new JPanel(new GridLayout (0, 2));
			ag.buttons = new JPanel(new FlowLayout());
			
			configureText();
			configureUserInfo();
			configureBillingInfo();
			configureCardInfo();
			configureLoginInfo();
			configureButtons();
		
			ag.add(ag.userInfo);
			ag.add(ag.billingInfo);
			ag.add(ag.cardInfo);
			ag.add(ag.loginInfo);
			ag.add(ag.buttons);
			
		}
		
		public void configureText () {
			flname = new JLabel ("Last Name: ");
			name = new JTextField ();
			address = new JLabel ("Address: ");
			addr = new JTextField ();
			phoneNum = new JLabel ("Phone Number: ");
			phono = new JTextField ();
			billingName = new JLabel ("Name: ");
			bname = new JTextField ();
			billingAddress = new JLabel ("Billing Address: ");
			baddr = new JTextField ();
			billingNum = new JLabel ("Billing Phone Number: ");
			bphono = new JTextField ();
			cardNumber = new JLabel ("Card Number: ");
			cardnum = new JTextField ();
			cardName = new JLabel ("Card Holder Name: ");
			chn = new JTextField ();
			email = new JLabel("Email: ");
			mail = new JTextField ();
			password = new JLabel ("Password: ");
			pass = new JTextField ();
		}
		
		public void configureUserInfo (AccountGUI ag) {
			ag.userInfo.add(flname);
			ag.userInfo.add(name);
			ag.userInfo.add(address);
			ag.userInfo.add(addr);
			ag.userInfo.add(phoneNum);
			ag.userInfo.add(phono);
		}
		
		public void configureBillingInfo (AccountGUI ag) {
			ag.billingInfo.add(billingName);
			ag.billingInfo.add(bname);
			ag.billingInfo.add(billingAddress);
			ag.billingInfo.add(baddr);
			ag.billingInfo.add(billingNum);
			ag.billingInfo.add(bphono);
		}
		
		public void configureCardInfo (AccountGUI ag) {
			ag.cardInfo.add(cardNumber);
			ag.cardInfo.add(cardnum);
			ag.cardInfo.add(cardName);
			ag.cardInfo.add(chn);
		}
		
		public void configureLoginInfo (AccountGUI ag) {
			ag.loginInfo.add(email);
			ag.loginInfo.add(mail);
			ag.loginInfo.add(password);
			ag.loginInfo.add(pass);
		}
		
		public void configureButtons () {
			okay = new JButton ("Create Account");
			cancel = new JButton ("Cancel");
			ag.buttons.add(okay);
			ag.buttons.add(cancel);
		}
		
		public JButton getOkayButton () {
			return okay;
		}
		
		public JButton getCancelButton () {
			return cancel;
		}
		
		public String getName () {
			return name.getText();
		}
		
		public String getAddress () {
			return addr.getText();
		}
		
		public String getPhoneNum () {
			return phono.getText();
		}
		
		public String getBillingName () {
			return bname.getText();
		}
		
		public String getBillingAddress () {
			return baddr.getText();
		}
		
		public String getBillingPhoneNum () {
			return bphono.getText();
		}
		
		public String getCardNum () {
			return cardnum.getText();
		}
		
		public String getCardName () {
			return chn.getText();
		}
		
		public String getEmail () {
			return mail.getText();
		}
		
		public String getPassword () {
			return pass.getText();
		}
	}
	
	
	class LoginGUI {
		
		JPasswordField password;
		JTextField email;
		JLabel pass, mail;
		JButton okay, cancel;
		
		LoginGUI (AccountGUI ag) {
			
			email = new JTextField();
			password = new JPasswordField();
			mail = new JLabel ("Email: ");
			pass = new JLabel ("Password: ")
			
			okay = new JButton ("Login");
			cancel = new JButton ("Cancel");
			
			ag.loginInfo = new JPanel(new GridLayout (0, 2));
			ag.buttons = new JPanel(new FlowLayout());
			
			ag.add(ag.loginInfo);
			ag.add(ag.buttons);
		}
		
		public String getPass () {
			return password.getText();
		}
		
		public String getMail () {
			return email.getText();
		}
		
		public JButton getOkayButton () {
			return okay;
		}
	}
	
	
}
