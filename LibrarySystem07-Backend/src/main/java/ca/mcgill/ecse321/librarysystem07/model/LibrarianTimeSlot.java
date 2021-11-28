package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Time;
import java.sql.Date;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Librarian Time Slot")
=======
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
<<<<<<< HEAD
@Table(name = "librarian time slot")
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
=======
@Table(name = "librarian_timeslot")
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
public class LibrarianTimeSlot {

	private Time startTime;
	private Time endTime;
	private DayOfTheWeek dayOfTheWeek;
<<<<<<< HEAD
	private Date date;
	private Librarian librarian;
	private Library library;
	private int timeSlotId;

	public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	public LibrarianTimeSlot(int librarianTimeSlotId, Librarian aLibrarian, Time aStartTime, Time aEndTime, Date aDate, DayOfTheWeek aDayOfTheWeek, Library aLibrary)
	{
		setStartTime(aStartTime);
		setEndTime(aEndTime);
		setDate(aDate);
		setDayOfTheWeek(aDayOfTheWeek);
		setLibrarian(aLibrarian);
		setLibrary(aLibrary);
		setTimeSlotId(librarianTimeSlotId);
	}

	@Id
	public LibrarianTimeSlot getLibrarianTimeSlot() {
		return this;
=======
	private Librarian librarian;
	//private Library library;
	private int librarianTimeSlotId;

	public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	public LibrarianTimeSlot() {
		
	}
	
	public LibrarianTimeSlot(int librarianTimeSlotId, Librarian aLibrarian, Time aStartTime, Time aEndTime, DayOfTheWeek aDayOfTheWeek)
	{
		setStartTime(aStartTime);
		setEndTime(aEndTime);
		setDayOfTheWeek(aDayOfTheWeek);
		setLibrarian(aLibrarian);
		//setLibrary(aLibrary);
		setLibrarianTimeSlotId(librarianTimeSlotId);
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
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

<<<<<<< HEAD
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

=======
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
	@ManyToOne(optional=false)
	public Librarian getLibrarian() {
		return librarian;
	}

	public void setLibrarian(Librarian librarian) {
		this.librarian = librarian;
	}

<<<<<<< HEAD
	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public int getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(int timeSlotId) {
		this.timeSlotId = timeSlotId;
	}
}
=======
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
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
