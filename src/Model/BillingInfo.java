package Model;

public class BillingInfo {
	private String name;
	private String address;
	private String phoneNumber;
	
	public BillingInfo(String n, String a, String pn) {
		name = n;
		address = a;
		phoneNumber = pn;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
}
