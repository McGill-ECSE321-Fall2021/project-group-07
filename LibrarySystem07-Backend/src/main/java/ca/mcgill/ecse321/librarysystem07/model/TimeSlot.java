/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Time;
import java.sql.Date;
import java.util.*;
import java.util.Map.Entry;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class TimeSlot
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, TimeSlot> timeslotsByTimeSlotID = new HashMap<Integer, TimeSlot>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeSlot Attributes
  private Time startTime;
  private Time endTime;
  private Date date;
  private DayOfTheWeek dayOfTheWeek;
  private int timeSlotID;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimeSlot(Time aStartTime, Time aEndTime, Date aDate, DayOfTheWeek aDayOfTheWeek, int aTimeSlotID)
  {
	//add check to make sure id is not already used
    startTime = aStartTime;
    endTime = aEndTime;
    date = aDate;
    dayOfTheWeek = aDayOfTheWeek;
    if (!setTimeSlotID(aTimeSlotID))
    {
      throw new RuntimeException("Cannot create due to duplicate timeSlotID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }
  
  public TimeSlot(Time aStartTime, Time aEndTime, Date aDate, DayOfTheWeek aDayOfTheWeek)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    date = aDate;
    dayOfTheWeek = aDayOfTheWeek;
    int anId = 0;
    for (Entry<Integer, TimeSlot> e : timeslotsByTimeSlotID.entrySet()) {
    	if (e.getKey() > anId) anId = e.getKey();
    }
    if (anId == 0) timeSlotID = anId;
    else timeSlotID = anId + 1;
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

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDayOfTheWeek(DayOfTheWeek aDayOfTheWeek)
  {
    boolean wasSet = false;
    dayOfTheWeek = aDayOfTheWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setTimeSlotID(int aTimeSlotID)
  {
    boolean wasSet = false;
    Integer anOldTimeSlotID = getTimeSlotID();
    if (anOldTimeSlotID != null && anOldTimeSlotID.equals(aTimeSlotID)) {
      return true;
    }
    if (hasWithTimeSlotID(aTimeSlotID)) {
      return wasSet;
    }
    timeSlotID = aTimeSlotID;
    wasSet = true;
    if (anOldTimeSlotID != null) {
      timeslotsByTimeSlotID.remove(anOldTimeSlotID);
    }
    timeslotsByTimeSlotID.put(aTimeSlotID, this);
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public Date getDate()
  {
    return date;
  }

  public DayOfTheWeek getDayOfTheWeek()
  {
    return dayOfTheWeek;
  }

  @Id
  public int getTimeSlotID()
  {
    return timeSlotID;
  }
  /* Code from template attribute_GetUnique */
  public static TimeSlot getWithTimeSlotID(int aTimeSlotID)
  {
    return timeslotsByTimeSlotID.get(aTimeSlotID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithTimeSlotID(int aTimeSlotID)
  {
    return getWithTimeSlotID(aTimeSlotID) != null;
  }

  public void delete()
  {
    timeslotsByTimeSlotID.remove(getTimeSlotID());
  }

}
