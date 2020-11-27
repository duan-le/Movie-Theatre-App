package Model;

public class Account {
	private UserInfo userInfo;
	private BillingInfo billingInfo;
	private CardInfo cardInfo;
	private String email;
	private String password;
	
	public Account(UserInfo ui, BillingInfo bi, CardInfo ci, String e, String p) {
		userInfo = ui;
		billingInfo = bi;
		cardInfo = ci;
		email = e;
		password = p;
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
}
