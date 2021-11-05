package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Time;
import java.sql.Date;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "librarian time slot")
public class LibrarianTimeSlot {

	private Time startTime;
	private Time endTime;
	private DayOfTheWeek dayOfTheWeek;
	private Librarian librarian;
	//private Library library;
	private int librarianTimeSlotId;

	public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	public LibrarianTimeSlot(int librarianTimeSlotId, Librarian aLibrarian, Time aStartTime, Time aEndTime, DayOfTheWeek aDayOfTheWeek)
	{
		setStartTime(aStartTime);
		setEndTime(aEndTime);
		setDayOfTheWeek(aDayOfTheWeek);
		setLibrarian(aLibrarian);
		//setLibrary(aLibrary);
		setLibrarianTimeSlotId(librarianTimeSlotId);
	}

	public DayOfTheWeek getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
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

	@ManyToOne(optional=false)
	public Librarian getLibrarian() {
		return librarian;
	}

	public void setLibrarian(Librarian librarian) {
		this.librarian = librarian;
	}

//	public Library getLibrary() {
//		return library;
//	}
//
//	public void setLibrary(Library library) {
//		this.library = library;
//	}

	@Id
	public int getLibrarianTimeSlotId() {
		return librarianTimeSlotId;
	}

	public void setLibrarianTimeSlotId(int alibrarianTimeSlotId) {
		this.librarianTimeSlotId = alibrarianTimeSlotId;
	}
}