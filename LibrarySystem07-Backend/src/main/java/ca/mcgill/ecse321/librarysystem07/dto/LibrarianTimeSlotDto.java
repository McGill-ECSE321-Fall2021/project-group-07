package ca.mcgill.ecse321.librarysystem07.dto;

import java.sql.Time;

public class LibrarianTimeSlotDto {
	
	/*
	 * data transfer object for librarian time slot
	 * 3 constructors, one null, one auto-generating schedule and one will all attributes set
	 * getters available for all non associative attributes, librarian can be set and get
	 * 
	 */

	public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
	
	private Time startTime;
	private Time endTime;
	private DayOfTheWeek dayOfTheWeek;
	private LibrarianDto librarian;
<<<<<<< HEAD
=======
	private Integer librarianTimeSlotId;
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	
	public LibrarianTimeSlotDto() {
		
	}
	
<<<<<<< HEAD
	public LibrarianTimeSlotDto(LibrarianDto librarian) {
		
		this(Time.valueOf("09:00:00"), Time.valueOf("17:00:00"), DayOfTheWeek.Monday, librarian);
	}
	
	public LibrarianTimeSlotDto(Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek, LibrarianDto librarian) {
		
		//this.librarianTimeSlotId = librarianTimeSlotId;
=======
	public LibrarianTimeSlotDto(Integer librarianTimeSlotId, LibrarianDto librarian) {
		
		this(librarianTimeSlotId, Time.valueOf("09:00:00"), Time.valueOf("17:00:00"), DayOfTheWeek.Monday, librarian);
	}
	
	public LibrarianTimeSlotDto(Integer librarianTimeSlotId, Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek, LibrarianDto librarian) {
		
		this.librarianTimeSlotId = librarianTimeSlotId;
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		this.startTime = startTime;
		this.endTime = endTime;
		this.dayOfTheWeek = dayOfTheWeek;
		this.librarian = librarian;
	}
	
	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public DayOfTheWeek getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public LibrarianDto getLibrarian() {
		return librarian;
	}

	public void setLibrarian(LibrarianDto librarian) {
		this.librarian = librarian;
	}

<<<<<<< HEAD
//	public int getLibrarianTimeSlotId() {
//		return librarianTimeSlotId;
//	}
=======
	public Integer getLibrarianTimeSlotId() {
		return librarianTimeSlotId;
	}
	
	public void setLibrarianTimeSlotId(Integer librarianTimeSlotId) {
		this.librarianTimeSlotId = librarianTimeSlotId;
	}
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3

}
