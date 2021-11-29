package ca.mcgill.ecse321.librarysystem07.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Visitor extends UserRole {
	
	private float balance;
	private int demeritPoints;

	
	public Visitor(String aName, String aUsername, String aAddress, int aLibraryCardID) {
		this(aName, aUsername, aAddress, aLibraryCardID, 0);

	}
	
	public Visitor(String aName, String aUsername, String aAddress, int aLibraryCardID, int aDemeritPoints) {

		super(aName, aUsername, aAddress, aLibraryCardID);
		this.balance = 0;
		this.setDemeritPoints(aDemeritPoints);
		if (aAddress == null) {
			setBalance(this.balance + 10);
		}
		else if (!(aAddress.contains("Montreal") || aAddress.contains("montreal"))) {
			setBalance(this.balance + 10);
		}
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getDemeritPoints() {
		return demeritPoints;
	}

	public void setDemeritPoints(int demeritPoints) {
		this.demeritPoints = demeritPoints;
	}

}
