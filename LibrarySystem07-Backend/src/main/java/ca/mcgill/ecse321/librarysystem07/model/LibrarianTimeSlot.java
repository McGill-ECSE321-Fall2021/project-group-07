package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Time;
import java.sql.Date;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Librarian Time Slot")
public class LibrarianTimeSlot {

	private Time startTime;
	private Time endTime;
	private DayOfTheWeek dayOfTheWeek;
	private Librarian librarian;
	private Library library;
	private int timeSlotId;

	public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	public LibrarianTimeSlot(int librarianTimeSlotId, Librarian aLibrarian, Time aStartTime, Time aEndTime, DayOfTheWeek aDayOfTheWeek, Library aLibrary)
	{
		setStartTime(aStartTime);
		setEndTime(aEndTime);
		setDayOfTheWeek(aDayOfTheWeek);
		setLibrarian(aLibrarian);
		setLibrary(aLibrary);
		setTimeSlotId(librarianTimeSlotId);
	}

	@Id
	public LibrarianTimeSlot getLibrarianTimeSlot() {
		return this;
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
