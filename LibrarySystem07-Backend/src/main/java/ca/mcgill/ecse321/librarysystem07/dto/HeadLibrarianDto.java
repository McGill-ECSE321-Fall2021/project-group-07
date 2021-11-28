package ca.mcgill.ecse321.librarysystem07.dto;

import java.util.Collections;
import java.util.List;

public class HeadLibrarianDto {
	
	/*
	 * HeadLibrarian Data Transfer Objects, do not need list of
	 * time slots as there is only one schedule for the head librarian
	 * 
	 * 2 constructors, one null one setting all attributes
	 * can only get attributes
	 */
	
	private String name;
	private String username;
	private String address;
<<<<<<< HEAD
	private int libraryCardID;
=======
	private Integer libraryCardID;
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	
	public HeadLibrarianDto() {
		
	}
<<<<<<< HEAD
		
	public HeadLibrarianDto(int libraryCardID, String name, String username, String address) {
=======
	
	public HeadLibrarianDto(Integer libraryCardID) {
		this(libraryCardID, "default", "default", "default");
	}
		
	public HeadLibrarianDto(Integer libraryCardID, String name, String username, String address) {
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		this.libraryCardID = libraryCardID;
		this.username = username;
		this.address = address;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getAddress() {
		return address;
	}

<<<<<<< HEAD
	public int getLibraryCardID() {
		return libraryCardID;
	}
=======
	public Integer getLibraryCardID() {
		return libraryCardID;
	}
	
	public void setLibraryCardID(Integer libraryCardID) {
		this.libraryCardID = libraryCardID;
	}
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3

}
