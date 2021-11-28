package ca.mcgill.ecse321.librarysystem07.dto;

import java.sql.Time;

public class HeadLibrarianTimeSlotDto {
	
	/*
	 * data transfer object for head librarian time slot
	 * 3 constructors, one null, one auto-generating schedule and one will all attributes set
	 * getters available for all non associative attributes, headLibrarian can be set and get
	 * 
	 */
	
	public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
	
	private Time startTime;
	private Time endTime;
	private DayOfTheWeek dayOfTheWeek;
	private HeadLibrarianDto headLibrarian;
<<<<<<< HEAD
	//private int headLibrarianTimeSlotId;
=======
	private Integer headLibrarianTimeSlotId;
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	
	public HeadLibrarianTimeSlotDto() {
		
	}
	
<<<<<<< HEAD
	public HeadLibrarianTimeSlotDto(HeadLibrarianDto headLibrarian) {
		
		this(Time.valueOf("09:00:00"), Time.valueOf("17:00:00"), DayOfTheWeek.Monday, headLibrarian);
	}
	
	public HeadLibrarianTimeSlotDto(Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek, HeadLibrarianDto headLibrarian) {
		
=======
	public HeadLibrarianTimeSlotDto(Integer headLibrarianTimeSlotId, HeadLibrarianDto headLibrarian) {
		
		this(headLibrarianTimeSlotId, Time.valueOf("09:00:00"), Time.valueOf("17:00:00"), DayOfTheWeek.Monday, headLibrarian);
	}
	
	public HeadLibrarianTimeSlotDto(Integer headLibrarianTimeSlotId, Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek, HeadLibrarianDto headLibrarian) {
		
		this.headLibrarianTimeSlotId = headLibrarianTimeSlotId;
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		this.startTime = startTime;
		this.endTime = endTime;
		this.dayOfTheWeek = dayOfTheWeek;
		this.headLibrarian = headLibrarian;
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

	public HeadLibrarianDto getHeadLibrarian() {
		return headLibrarian;
	}

	public void setHeadLibrarian(HeadLibrarianDto headLibrarian) {
		this.headLibrarian = headLibrarian;
	}

<<<<<<< HEAD
//	public int getHeadLibrarianTimeSlotId() {
//		return headLibrarianTimeSlotId;
//	}
=======
	public Integer getHeadLibrarianTimeSlotId() {
		return headLibrarianTimeSlotId;
	}
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3

}
