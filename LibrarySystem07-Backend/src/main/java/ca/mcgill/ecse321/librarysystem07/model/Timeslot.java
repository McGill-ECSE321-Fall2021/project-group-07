/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.sql.Time;
import java.sql.Date;
import java.util.*;

// line 41 "model.ump"
// line 128 "model.ump"

@Entity
public class Timeslot
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	public enum DayoftheWeek { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }

	//Timeslot Attributes
	private Time startTime;
	private Time endTime;
	private Date startDate;
	private Date endDate;
	private DayoftheWeek dayOfTheWeek;
	private String timeslotId;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Timeslot(Time aStartTime, Time aEndTime, Date aStartDate, Date aEndDate, DayoftheWeek aDayOfTheWeek, String aTimeslotId)
	{
		startTime = aStartTime;
		endTime = aEndTime;
		startDate = aStartDate;
		endDate = aEndDate;
		dayOfTheWeek = aDayOfTheWeek;
		timeslotId = aTimeslotId;
	}
	
	public Timeslot(Time aStartTime, Time aEndTime, Date aStartDate, Date aEndDate, DayoftheWeek aDayOfTheWeek)
	{
		startTime = aStartTime;
		endTime = aEndTime;
		startDate = aStartDate;
		endDate = aEndDate;
		dayOfTheWeek = aDayOfTheWeek;
		timeslotId = "101010";
	}


	//------------------------
	// INTERFACE
	//------------------------

	public boolean setStartTime(Time aStartTime)
	{
		boolean wasSet = false;
		startTime = aStartTime;
		wasSet = true;
		return wasSet;
	}

	public boolean setEndTime(Time aEndTime)
	{
		boolean wasSet = false;
		endTime = aEndTime;
		wasSet = true;
		return wasSet;
	}

	public boolean setStartDate(Date aStartDate)
	{
		boolean wasSet = false;
		startDate = aStartDate;
		wasSet = true;
		return wasSet;
	}

	public boolean setEndDate(Date aEndDate)
	{
		boolean wasSet = false;
		endDate = aEndDate;
		wasSet = true;
		return wasSet;
	}

	public boolean setDayOfTheWeek(DayoftheWeek aDayOfTheWeek)
	{
		boolean wasSet = false;
		dayOfTheWeek = aDayOfTheWeek;
		wasSet = true;
		return wasSet;
	}

	public boolean setTimeslotId(String aTimeslotId) {
		boolean wasSet = false;
		timeslotId = aTimeslotId;
		wasSet = true;
		return wasSet;
	}

	@Id
	@OneToOne(optional=false)
	public String getTimeslotId() {
		return timeslotId;
	}

	@OneToOne(optional=false)
	public Time getStartTime()
	{
		return startTime;
	}

	@OneToOne(optional=false)
	public Time getEndTime()
	{
		return endTime;
	}

	@OneToOne(optional=false)
	public Date getStartDate()
	{
		return startDate;
	}

	@OneToOne(optional=false)
	public Date getEndDate()
	{
		return endDate;
	}

	@OneToOne(optional=false)
	public DayoftheWeek getDayOfTheWeek()
	{
		return dayOfTheWeek;
	}

	public String toString()
	{
		return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
				"  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
				"  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
				"  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
				"  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
				"  " + "dayOfTheWeek" + "=" + (getDayOfTheWeek() != null ? !getDayOfTheWeek().equals(this)  ? getDayOfTheWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator");
	}
}