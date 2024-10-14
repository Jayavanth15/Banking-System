package Classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import Exceptions.*;

public class Main {

	static Scanner sc = new Scanner(System.in);
	
	public void createAnAccount(ArrayList<BankAccount> bankAccount) throws InvalidPasswordFormatException {
		System.out.println("Enter Customer's name : ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.println("Enter User Id : ");
		String userId = sc.next();
		System.out.println("Enter User Password : ");
		String userPsw = sc.next();
		if(userPsw.length() < 8 || !userPsw.contains("*")) {
			throw new InvalidPasswordFormatException("Password format is invalid");
		}
		else {
			System.out.println("Accound created successfully");
			CheckingAccount ca = new CheckingAccount(name, userId, userPsw);
			bankAccount.add(ca);
		}
	}
	
	public BankAccount customerCheck(ArrayList<BankAccount> bankAccount, String userId, String userPsw) {
		Iterator<BankAccount> itr = bankAccount.iterator();
		while(itr.hasNext()) {
			BankAccount ba = itr.next();
			if(ba.getUserId().equals(userId) && ba.getUserPw().equals(userPsw)) {
				return ba;
			}
		}
		return null;
	}
	
	public void deleteAccount(ArrayList<BankAccount> bankAccount) throws CustomerAccountNotFoundException{
		System.out.println("Enter User Id : ");
		String userId = sc.next();
		System.out.println("Enter User Password : ");
		String userPsw = sc.next();
		BankAccount account = customerCheck(bankAccount, userId, userPsw);
		if(account != null) {
			System.out.println("Account deleted successfully");
			bankAccount.removeIf(acc -> acc.equals(account));
			
		}
		else {
			throw new CustomerAccountNotFoundException("Customer details are invalid");
		}
	}
	
	public void depositInAccount(ArrayList<BankAccount> bankAccount) throws NegativeDollarAmountException, CustomerAccountNotFoundException {
		System.out.println("Enter User Id : ");
		String userId = sc.next();
		System.out.println("Enter User Password : ");
		String userPsw = sc.next();
		BankAccount account = customerCheck(bankAccount, userId, userPsw);
		if(account != null) {
			System.out.println("Enter amount in dollar : ");
			double amount = sc.nextDouble();
			if(amount < 0) {
				throw new NegativeDollarAmountException("Amount value is invalid");
			}
			else {
				System.out.println("Amount deposited");
				account.setAccountBalance(account.getAccountBalance()+amount);
			}
		}
		else {
			throw new CustomerAccountNotFoundException("Customer details are invalid");
		}
	}
	
	public void withdrawFromAccount(ArrayList<BankAccount> bankAccount) throws NegativeDollarAmountException, CustomerAccountNotFoundException, InsufficientFundsException {
		System.out.println("Enter User Id : ");
		String userId = sc.next();
		System.out.println("Enter User Password : ");
		String userPsw = sc.next();
		BankAccount account = customerCheck(bankAccount, userId, userPsw);
		if(account != null) {
			System.out.println("Enter amount in dollar : ");
			double amount = sc.nextDouble();
			if(amount < 0) {
				throw new NegativeDollarAmountException("Amount value is invalid");
			}
			else {
				if(amount <= account.getAccountBalance()) {
					System.out.println("Amount withdrawn");
					account.setAccountBalance(account.getAccountBalance()-amount);
				}
				else {
					throw new InsufficientFundsException("Amount in account is less than entered amount");
				}
			}
		}
		else {
			throw new CustomerAccountNotFoundException("Customer details are invalid");
		}
	}
	
	public void checkBalance(ArrayList<BankAccount> bankAccount) throws CustomerAccountNotFoundException {
		System.out.println("Enter User Id : ");
		String userId = sc.next();
		System.out.println("Enter User Password : ");
		String userPsw = sc.next();
		BankAccount account = customerCheck(bankAccount, userId, userPsw);
		if(account != null) {
			System.out.println("Customer's name : " + account.getCustomerName());
			System.out.println("Account Number : " + account.getAccountNumber());
			System.out.println("Account balance : " + account.getAccountBalance());
			if(account instanceof CheckingAccount) {
				System.out.println("Account Type : Checking");
				System.out.println("Account Daily Withdrawal Limit : " + ((CheckingAccount) (account)).getDailyWithdrawalLimit());

			}
		}
		else {
			throw new CustomerAccountNotFoundException("Customer details are invalid");
		}
	}
	
	public static void main(String[] args) {
		
		Main main = new Main();
		ArrayList<BankAccount> bankAccount = new ArrayList<>();
		
		while(true) {
			System.out.println("1 – Create An Account\n2 – Delete An Account\n3 – Make An Account Deposit \n4 – Make An Account Withdrawal \n5 – Check An Account Balance \n6 – Exit");
			System.out.println("Enter choice : ");
			int choice = sc.nextInt();
			switch(choice){
				case 1:
					try {
						main.createAnAccount(bankAccount);
					}
					catch(InvalidPasswordFormatException ip) {
						System.err.println("Error: Must Enter a Valid Password");
						System.out.println(ip);
					}
					break;
				case 2:
					try{
						main.deleteAccount(bankAccount);
					}
					catch(CustomerAccountNotFoundException ca) {
						System.err.println("Error: Must Enter a Valid User ID and Password");
						System.out.println(ca);
					}
					break;
				case 3:
					try {
						main.depositInAccount(bankAccount);
					}
					catch(NegativeDollarAmountException nd) {
						System.err.println("Error: Must Enter a Positive Dollar Amount");
						System.out.println(nd);
					}
					catch(CustomerAccountNotFoundException ca) {
						System.err.println("Error: Must Enter a Valid User ID and Password");
						System.out.println(ca);
					}
					break;
				case 4:
					try {
						main.withdrawFromAccount(bankAccount);
					}
					catch(InsufficientFundsException is) {
						System.err.println("Error: Must Withdraw an Amount Less Than Your Balance");
						System.out.println(is);
					}
					catch(NegativeDollarAmountException nd) {
						System.err.println("Error: Must Enter a Positive Dollar Amount");
						System.out.println(nd);
					}
					catch(CustomerAccountNotFoundException ca) {
						System.err.println("Error: Must Enter a Valid User ID and Password");
						System.out.println(ca);
					}
					break;
				case 5:
					try {
						main.checkBalance(bankAccount);
					}
					catch(CustomerAccountNotFoundException ca) {
						System.err.println("Error: Must Enter a Valid User ID and Password");
						System.out.println(ca);
					}
					break;
				case 6:
					System.exit(0);
				default:
					System.err.println("Please enter a valid choice (1 thru 6)");
					break;
			}
		}
	}
}
