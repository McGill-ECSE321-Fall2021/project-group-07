package ca.mcgill.ecse321.librarysystem07.dto;

import java.util.Collections;
import java.util.List;

public class LibrarianDto {
	
	private String name;
	private String username;
	private String address;
<<<<<<< HEAD
	private int libraryCardID;
=======
	private Integer libraryCardID;
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	private List<LibrarianTimeSlotDto> librarianTimeSlots;
	
	public LibrarianDto() {
		
	}
	
	@SuppressWarnings("unchecked")
<<<<<<< HEAD
	public LibrarianDto(int libraryCardID, String name, String username, String address) {
		this(libraryCardID, name, username, address, Collections.EMPTY_LIST);
	}
	
	public LibrarianDto(int libraryCardID, String name, String username, String address, List<LibrarianTimeSlotDto> librarianTimeSlots) {
=======
	public LibrarianDto(Integer libraryCardID) {
		this(libraryCardID, "lib_default", "lib_default", "lib_default", Collections.EMPTY_LIST);
	}
	
	@SuppressWarnings("unchecked")
	public LibrarianDto(Integer libraryCardID, String name, String username, String address) {
		this(libraryCardID, name, username, address, Collections.EMPTY_LIST);
	}
	
	public LibrarianDto(Integer libraryCardID, String name, String username, String address, List<LibrarianTimeSlotDto> librarianTimeSlots) {
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		this.libraryCardID = libraryCardID;
		this.username = username;
		this.address = address;
		this.name = name;
		this.librarianTimeSlots = librarianTimeSlots;
	}

	public List<LibrarianTimeSlotDto> getLibrarianTimeSlots() {
		return librarianTimeSlots;
	}

	public void setLibrarianTimeSlots(List<LibrarianTimeSlotDto> librarianTimeSlots) {
		this.librarianTimeSlots = librarianTimeSlots;
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
