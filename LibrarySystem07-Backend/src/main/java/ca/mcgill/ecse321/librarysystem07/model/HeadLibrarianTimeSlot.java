package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Date;
import java.sql.Time;

public class HeadLibrarianTimeSlot {
	private Time startTime;
	private Time endTime;
	private DayOfTheWeek dayOfTheWeek;
	private Date date;
	private HeadLibrarian headLibrarian;

	 public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	 public HeadLibrarianTimeSlot(HeadLibrarian aLibrarian, Time aStartTime, Time aEndTime, Date aDate, DayOfTheWeek aDayOfTheWeek)
	  {
	    setStartTime(aStartTime);
	    setEndTime(aEndTime);
	    setDate(aDate);
	    setDayOfTheWeek(aDayOfTheWeek);
	    setHeadLibrarian(aLibrarian);
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

	public HeadLibrarian getHeadLibrarian() {
		return headLibrarian;
	}

	public void setHeadLibrarian(HeadLibrarian headLibrarian) {
		this.headLibrarian = headLibrarian;
	}
}
