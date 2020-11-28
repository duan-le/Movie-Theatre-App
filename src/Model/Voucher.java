package Model;
import java.util.*;

public class Voucher {
	private double amount;
	private Date expirationDate;
	
	public Voucher(double a, Date ed) {
		amount = a;
		expirationDate = ed;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}
}
