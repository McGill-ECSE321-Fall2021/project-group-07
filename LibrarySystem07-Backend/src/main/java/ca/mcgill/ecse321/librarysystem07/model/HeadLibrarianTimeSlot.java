package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Head Librarian Time Slot")
public class HeadLibrarianTimeSlot {
	
	private Time startTime;
	private Time endTime;
	private DayOfTheWeek dayOfTheWeek;
	private Date date;
	private HeadLibrarian headLibrarian;
	private Library library;
	private int timeSlotId;

	public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	public HeadLibrarianTimeSlot(int headLibrarianTimeSlotId, HeadLibrarian aLibrarian, Time aStartTime, Time aEndTime, Date aDate, DayOfTheWeek aDayOfTheWeek, Library aLibrary)
	{
		setStartTime(aStartTime);
		setEndTime(aEndTime);
		setDate(aDate);
		setDayOfTheWeek(aDayOfTheWeek);
		setHeadLibrarian(aLibrarian);
		setLibrary(aLibrary);
		setTimeSlotId(headLibrarianTimeSlotId);
	}

	public HeadLibrarianTimeSlot getHeadLibrarianTimeSlot() {
		return this;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne(optional=false)
	public HeadLibrarian getHeadLibrarian() {
		return headLibrarian;
	}

	public void setHeadLibrarian(HeadLibrarian headLibrarian) {
		this.headLibrarian = headLibrarian;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	@Id
	public int getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(int timeSlotId) {
		this.timeSlotId = timeSlotId;
	}
}
