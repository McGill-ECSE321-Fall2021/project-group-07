package ca.mcgill.ecse321.librarysystem07.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "visitor")
public class Visitor extends UserRole {
	
	private float balance;
	private int demeritPoints;
	
	
	public Visitor(String aName, String aUsername, String aAddress, int aLibraryCardID, int aDemeritPoints) {

		super(aName, aUsername, aAddress, aLibraryCardID);
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

//	public Library getLibrary() {
//		return library;
//	}
//
//	public void setLibrary(Library library) {
//		this.library = library;
//	}
	
<<<<<<< HEAD
}
=======
}
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
