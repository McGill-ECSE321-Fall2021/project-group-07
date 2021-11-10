package ca.mcgill.ecse321.librarysystem07.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Table(name = "\"user role\"")
public abstract class UserRole {

	private String name;
	private String username;
	private String address;
	private int libraryCardID;
	//private Library library;
	//private int balance?

	public UserRole(String aName, String aUsername, String aAddress, int aLibraryCardID) {
		this.name = aName;
		this.username = aUsername;
		this.address = aAddress;
		this.libraryCardID = aLibraryCardID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Id
	public int getLibraryCardID() {
		return libraryCardID;
	}

	public void setLibraryCardID(int libraryCardID) {
		this.libraryCardID = libraryCardID;
	}

//	public Library getLibrary() {
//		return library;
//	}
//
//	public void setLibrary(Library library) {
//		this.library = library;
//	}
	
	//if (!aAddress.contains("thisCity")) user.setBalance(user.balance + 10));
<<<<<<< HEAD
}
=======
}
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
