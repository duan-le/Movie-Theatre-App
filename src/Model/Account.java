package Model;
import java.util.*;
public class Account {
	private UserInfo userInfo;
	private BillingInfo billingInfo;
	private CardInfo cardInfo;
	private String email;
	private String password;
	private Date creationDate;
	public Account(UserInfo ui, BillingInfo bi, CardInfo ci, String e, String p, Date cd) {
		userInfo = ui;
		billingInfo = bi;
		cardInfo = ci;
		email = e;
		password = p;
		creationDate = cd;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public BillingInfo getBillingInfo() {
		return billingInfo;
	}
	
	public CardInfo getCardInfo() {
		return cardInfo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	public Date getCreationDate(){
		return creationDate;
	}
}
