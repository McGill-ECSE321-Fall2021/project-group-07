package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "head_librarian_timeslot")
public class HeadLibrarianTimeSlot {
	
	private Time startTime;
	private Time endTime;
	private DayOfTheWeek dayOfTheWeek;
	private HeadLibrarian headLibrarian;
	//private Library library;
	private int headLibrarianTimeSlotId;

	public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	public HeadLibrarianTimeSlot() {
		
	}
	
	public HeadLibrarianTimeSlot(int headLibrarianTimeSlotId, HeadLibrarian aLibrarian, Time aStartTime, Time aEndTime, DayOfTheWeek aDayOfTheWeek)
	{
		setStartTime(aStartTime);
		setEndTime(aEndTime);
		setDayOfTheWeek(aDayOfTheWeek);
		setHeadLibrarian(aLibrarian);
		//setLibrary(aLibrary);
		setHeadLibrarianTimeSlotId(headLibrarianTimeSlotId);
	}


	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public DayOfTheWeek getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	@ManyToOne(optional=false)
	public HeadLibrarian getHeadLibrarian() {
		return headLibrarian;
	}

	public void setHeadLibrarian(HeadLibrarian headLibrarian) {
		this.headLibrarian = headLibrarian;
	}

//	public Library getLibrary() {
//		return library;
//	}
//
//	public void setLibrary(Library library) {
//		this.library = library;
//	}

	@Id
	public int getHeadLibrarianTimeSlotId() {
		return headLibrarianTimeSlotId;
	}

	public void setHeadLibrarianTimeSlotId(int aheadLibrarianTimeSlotId) {
		this.headLibrarianTimeSlotId = aheadLibrarianTimeSlotId;
	}
}