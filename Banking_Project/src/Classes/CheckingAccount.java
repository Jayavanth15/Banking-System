package Classes;

public class CheckingAccount extends BankAccount{

	private double dailyWithdrawalLimit;
	
	CheckingAccount(String customerName, String userId, String userPw) {
		super(customerName, userId, userPw);
		this.dailyWithdrawalLimit = 300.00;
	}
	
	public double getDailyWithdrawalLimit() {return dailyWithdrawalLimit;}
	public void setDailyWithdrawalLimit(double dailyWithdrawalLimit) {this.dailyWithdrawalLimit = dailyWithdrawalLimit;}
}
