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
	private int libraryCardID;
	
	public HeadLibrarianDto() {
		
	}
		
	public HeadLibrarianDto(int libraryCardID, String name, String username, String address) {
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

	public int getLibraryCardID() {
		return libraryCardID;
	}

}
