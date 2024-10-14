package Classes;

public class BankAccount {

	private String customerName;
	private int accountNumber = noOfAccounts;
	private String userId;
	private String userPw;
	private double accountBalance;
	private static int noOfAccounts = 0;
	
	BankAccount(String customerName, String userId, String userPw) {
		this.customerName = customerName;
		this.accountNumber = ++noOfAccounts;
		this.userId = userId;
		this.userPw = userPw;
	}

	public String getCustomerName() {return customerName;}
	public void setCustomerName(String customerName) {this.customerName = customerName;}
	public String getUserId() {return userId;}
	public void setUserId(String userId) {this.userId = userId;}
	public String getUserPw() {return userPw;}
	public void setUserPw(String userPw) {this.userPw = userPw;}
	public Double getAccountBalance() {return accountBalance;}
	public void setAccountBalance(Double accountBalance) {this.accountBalance = accountBalance;}
	public int getAccountNumber() {return accountNumber;}
	
	public static double getNoOfAccounts() {return noOfAccounts;}
	
	public static void decrAccounts() {noOfAccounts-=1;}
}
