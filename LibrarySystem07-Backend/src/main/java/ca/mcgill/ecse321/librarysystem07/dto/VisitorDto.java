package ca.mcgill.ecse321.librarysystem07.dto;

public class VisitorDto {
	
	private float balance;
	private int demeritPoints;
	private String address;
	private	String name;
	private String username;
	private int libraryCardId;
	
	public VisitorDto() {}
	
	public VisitorDto(String aName, String aUsername, String aAddress, int aLibraryCardID) {
		name = aName;
		username = aUsername;
		address = aAddress;
		libraryCardId = aLibraryCardID;
		setDemeritPoints(0);
		setBalance(0);
	}
	
	public VisitorDto(String aName, String aUsername, String aAddress, int aLibraryCardID, int aDemeritPoints) {
		name = aName;
		username = aUsername;
		address = aAddress;
		libraryCardId = aLibraryCardID;
		setDemeritPoints(aDemeritPoints);
		setBalance(0);
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

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public int getLibraryCardId() {
		return libraryCardId;
	}	

}
