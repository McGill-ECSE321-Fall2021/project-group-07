/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Time;
import java.sql.Date;
import java.util.*;

import javax.persistence.*;


@Entity
@Table(name = "Time Slot")
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

  //TimeSlot Associations
  private Librarian librarian;
  private HeadLibrarian headLibrarian;
  private Library library;
  private Event event;
  private Reservation reservation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimeSlot(Time aStartTime, Time aEndTime, Date aDate, DayOfTheWeek aDayOfTheWeek, int aTimeSlotID, Library aLibrary)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    date = aDate;
    dayOfTheWeek = aDayOfTheWeek;
    librarian = null;
    headLibrarian = null;
    library = null;
    event = null;
    reservation = null;
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
  /* Code from template association_GetOne */
  @ManyToOne
  public Librarian getLibrarian()
  {
    return librarian;
  }
  /* Code from template association_GetOne */
  @ManyToOne
  public HeadLibrarian getHeadLibrarian()
  {
    return headLibrarian;
  }
  /* Code from template association_GetOne */
  @ManyToOne
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_GetOne */
  @ManyToOne
  public Event getEvent()
  {
    return event;
  }
  /* Code from template association_GetOne */
  @ManyToOne
  public Reservation getReservation()
  {
    return reservation;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibrarian(Librarian aLibrarian)
  {
    boolean wasSet = false;
    if (aLibrarian == null)
    {
      return wasSet;
    }

    Librarian existingLibrarian = librarian;
    librarian = aLibrarian;
    if (existingLibrarian != null && !existingLibrarian.equals(aLibrarian))
    {
      existingLibrarian.removeTimeSlot(this);
    }
    librarian.addTimeSlot(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setHeadLibrarian(HeadLibrarian aHeadLibrarian)
  {
    boolean wasSet = false;
    if (aHeadLibrarian == null)
    {
      return wasSet;
    }

    HeadLibrarian existingHeadLibrarian = headLibrarian;
    headLibrarian = aHeadLibrarian;
    if (existingHeadLibrarian != null && !existingHeadLibrarian.equals(aHeadLibrarian))
    {
      existingHeadLibrarian.removeTimeSlot(this);
    }
    headLibrarian.addTimeSlot(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibrary(Library aLibrary)
  {
    boolean wasSet = false;
    if (aLibrary == null)
    {
      return wasSet;
    }

    Library existingLibrary = library;
    library = aLibrary;
    if (existingLibrary != null && !existingLibrary.equals(aLibrary))
    {
      existingLibrary.removeTimeSlot(this);
    }
    library.addTimeSlot(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setEvent(Event aEvent)
  {
    boolean wasSet = false;
    if (aEvent == null)
    {
      return wasSet;
    }

    Event existingEvent = event;
    event = aEvent;
    if (existingEvent != null && !existingEvent.equals(aEvent))
    {
      existingEvent.removeTimeSlot(this);
    }
    event.addTimeSlot(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setReservation(Reservation aReservation)
  {
    boolean wasSet = false;
    if (aReservation == null)
    {
      return wasSet;
    }

    Reservation existingReservation = reservation;
    reservation = aReservation;
    if (existingReservation != null && !existingReservation.equals(aReservation))
    {
      existingReservation.removeTimeSlot(this);
    }
    reservation.addTimeSlot(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    timeslotsByTimeSlotID.remove(getTimeSlotID());
    Librarian placeholderLibrarian = librarian;
    this.librarian = null;
    if(placeholderLibrarian != null)
    {
      placeholderLibrarian.removeTimeSlot(this);
    }
    HeadLibrarian placeholderHeadLibrarian = headLibrarian;
    this.headLibrarian = null;
    if(placeholderHeadLibrarian != null)
    {
      placeholderHeadLibrarian.removeTimeSlot(this);
    }
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeTimeSlot(this);
    }
    Event placeholderEvent = event;
    this.event = null;
    if(placeholderEvent != null)
    {
      placeholderEvent.removeTimeSlot(this);
    }
    Reservation placeholderReservation = reservation;
    this.reservation = null;
    if(placeholderReservation != null)
    {
      placeholderReservation.removeTimeSlot(this);
    }
  }



  public String toString()
  {
    return super.toString() + "["+
            "timeSlotID" + ":" + getTimeSlotID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "dayOfTheWeek" + "=" + (getDayOfTheWeek() != null ? !getDayOfTheWeek().equals(this)  ? getDayOfTheWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "librarian = "+(getLibrarian()!=null?Integer.toHexString(System.identityHashCode(getLibrarian())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "headLibrarian = "+(getHeadLibrarian()!=null?Integer.toHexString(System.identityHashCode(getHeadLibrarian())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "event = "+(getEvent()!=null?Integer.toHexString(System.identityHashCode(getEvent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservation = "+(getReservation()!=null?Integer.toHexString(System.identityHashCode(getReservation())):"null");
  }
}
