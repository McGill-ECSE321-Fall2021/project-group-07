package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Time;
import java.sql.Date;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import ca.mcgill.ecse321.librarysystem07.model.OldTimeSlot.DayOfTheWeek;

@Entity
@Table(name = "Librarian Time Slot")
public class LibrarianTimeSlot {

	private Time startTime;
	private Time endTime;
	private DayOfTheWeek dayOfTheWeek;
	private Date date;
	private Librarian librarian;
	private Library library;

	public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	public LibrarianTimeSlot(Librarian aLibrarian, Time aStartTime, Time aEndTime, Date aDate, DayOfTheWeek aDayOfTheWeek, Library aLibrary)
	{
		setStartTime(aStartTime);
		setEndTime(aEndTime);
		setDate(aDate);
		setDayOfTheWeek(aDayOfTheWeek);
		setLibrarian(aLibrarian);
		setLibrary(aLibrary);
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

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
}
