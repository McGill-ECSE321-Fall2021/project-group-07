package ca.mcgill.ecse321.librarysystem07.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import ca.mcgill.ecse321.librarysystem07.model.*;

public class HeadLibrarianDto {
	
	private String name;
	private String username;
	private String address;
	private Integer libraryCardID;
	private List<TimeSlot> schedule;
	
	public HeadLibrarianDto() {
	}
	
	public HeadLibrarianDto(String name) {
		//this(name);
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

	public List<TimeSlot> getSchedule() {
		return schedule;
	}
	
	

}
