package Model;

public class CardInfo {
	private int cardNumber;
	private String cardHolderName;

	public CardInfo(int cn, String chn) {
		cardNumber = cn;
		cardHolderName = chn;
	}
	
	public int getCardNumber() {
		return cardNumber;
	}
	
	public String getCardHolderName() {
		return cardHolderName;
	}
}
