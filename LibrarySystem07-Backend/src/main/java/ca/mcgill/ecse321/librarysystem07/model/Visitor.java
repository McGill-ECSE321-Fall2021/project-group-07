package ca.mcgill.ecse321.librarysystem07.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Visitor")
public class Visitor extends UserRole {
	
	private float balance;
	private int demeritPoints;
	private Library library;
	
	
	public Visitor(String aName, String aUsername, String aAddress, int aLibraryCardID, int aDemeritPoints, Library aLibrary) {

		super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
		this.balance = 0;
		this.setDemeritPoints(aDemeritPoints);
//		if (!aAddress.contains(library.getCity())) {
//			setBalance(this.balance + 10);
//		}
	}

	public float getBalance() {
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

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}
	
}
