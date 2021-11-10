package ca.mcgill.ecse321.librarysystem07.dto;

import java.util.Collections;
import java.util.List;

public class LibrarianDto {
	
	private String name;
	private String username;
	private String address;
	private Integer libraryCardID;
	private List<LibrarianTimeSlotDto> librarianTimeSlots;
	
	public LibrarianDto() {
		
	}
	
	@SuppressWarnings("unchecked")
	public LibrarianDto(Integer libraryCardID) {
		this(libraryCardID, "lib_default", "lib_default", "lib_default", Collections.EMPTY_LIST);
	}
	
	@SuppressWarnings("unchecked")
	public LibrarianDto(Integer libraryCardID, String name, String username, String address) {
		this(libraryCardID, name, username, address, Collections.EMPTY_LIST);
	}
	
	public LibrarianDto(Integer libraryCardID, String name, String username, String address, List<LibrarianTimeSlotDto> librarianTimeSlots) {
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

	public Integer getLibraryCardID() {
		return libraryCardID;
	}

}
