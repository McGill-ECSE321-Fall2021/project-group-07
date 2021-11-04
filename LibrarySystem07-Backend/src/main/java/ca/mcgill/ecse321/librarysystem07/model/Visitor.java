package ca.mcgill.ecse321.librarysystem07.model;

public class Visitor extends UserRole {
	
	private int balance;
	private int demeritPoints;

	public Visitor(String aName, String aUsername, String aAddress, int aLibraryCardID, int aDemeritPoints) {

		super(aName, aUsername, aAddress, aLibraryCardID);
		setBalance(0);
		this.setDemeritPoints(aDemeritPoints);
//		if (!aAddress.contains("montreal")) {
//			setDemeritPoints(this.balance + 10);
//		}
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getDemeritPoints() {
		return demeritPoints;
	}

	public void setDemeritPoints(int demeritPoints) {
		this.demeritPoints = demeritPoints;
	}
	
	
}
