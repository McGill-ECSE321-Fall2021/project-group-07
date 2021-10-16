/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Time;
import java.sql.Date;
import java.util.*;

// line 41 "model.ump"
// line 128 "model.ump"
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

  //Timeslot Associations
  private List<Librarian> librarians;
  private HeadLibrarian headLibrarian;
  private Event event;
  private List<Reservation> reservations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Timeslot(Time aStartTime, Time aEndTime, Date aStartDate, Date aEndDate, DayoftheWeek aDayOfTheWeek, HeadLibrarian aHeadLibrarian, Event aEvent)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    startDate = aStartDate;
    endDate = aEndDate;
    dayOfTheWeek = aDayOfTheWeek;
    librarians = new ArrayList<Librarian>();
    if (aHeadLibrarian == null || aHeadLibrarian.getTimeslot() != null)
    {
      throw new RuntimeException("Unable to create Timeslot due to aHeadLibrarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    headLibrarian = aHeadLibrarian;
    if (aEvent == null || aEvent.getTimeslot() != null)
    {
      throw new RuntimeException("Unable to create Timeslot due to aEvent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    event = aEvent;
    reservations = new ArrayList<Reservation>();
  }

  public Timeslot(Time aStartTime, Time aEndTime, Date aStartDate, Date aEndDate, DayoftheWeek aDayOfTheWeek, String aNameForHeadLibrarian, String aUsernameForHeadLibrarian, String aAddressForHeadLibrarian, int aLibraryCardIDForHeadLibrarian, Library aLibraryForHeadLibrarian, List<Time> aHoursForHeadLibrarian)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    startDate = aStartDate;
    endDate = aEndDate;
    dayOfTheWeek = aDayOfTheWeek;
    librarians = new ArrayList<Librarian>();
    headLibrarian = new HeadLibrarian(aNameForHeadLibrarian, aUsernameForHeadLibrarian, aAddressForHeadLibrarian, aLibraryCardIDForHeadLibrarian, aLibraryForHeadLibrarian, aHoursForHeadLibrarian, this);
    event = new Event(this);
    reservations = new ArrayList<Reservation>();
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

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public DayoftheWeek getDayOfTheWeek()
  {
    return dayOfTheWeek;
  }
  /* Code from template association_GetMany */
  public Librarian getLibrarian(int index)
  {
    Librarian aLibrarian = librarians.get(index);
    return aLibrarian;
  }

  public List<Librarian> getLibrarians()
  {
    List<Librarian> newLibrarians = Collections.unmodifiableList(librarians);
    return newLibrarians;
  }

  public int numberOfLibrarians()
  {
    int number = librarians.size();
    return number;
  }

  public boolean hasLibrarians()
  {
    boolean has = librarians.size() > 0;
    return has;
  }

  public int indexOfLibrarian(Librarian aLibrarian)
  {
    int index = librarians.indexOf(aLibrarian);
    return index;
  }
  /* Code from template association_GetOne */
  public HeadLibrarian getHeadLibrarian()
  {
    return headLibrarian;
  }
  /* Code from template association_GetOne */
  public Event getEvent()
  {
    return event;
  }
  /* Code from template association_GetMany */
  public Reservation getReservation(int index)
  {
    Reservation aReservation = reservations.get(index);
    return aReservation;
  }

  public List<Reservation> getReservations()
  {
    List<Reservation> newReservations = Collections.unmodifiableList(reservations);
    return newReservations;
  }

  public int numberOfReservations()
  {
    int number = reservations.size();
    return number;
  }

  public boolean hasReservations()
  {
    boolean has = reservations.size() > 0;
    return has;
  }

  public int indexOfReservation(Reservation aReservation)
  {
    int index = reservations.indexOf(aReservation);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibrarians()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addLibrarian(Librarian aLibrarian)
  {
    boolean wasAdded = false;
    if (librarians.contains(aLibrarian)) { return false; }
    librarians.add(aLibrarian);
    if (aLibrarian.indexOfTimeslot(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLibrarian.addTimeslot(this);
      if (!wasAdded)
      {
        librarians.remove(aLibrarian);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeLibrarian(Librarian aLibrarian)
  {
    boolean wasRemoved = false;
    if (!librarians.contains(aLibrarian))
    {
      return wasRemoved;
    }

    int oldIndex = librarians.indexOf(aLibrarian);
    librarians.remove(oldIndex);
    if (aLibrarian.indexOfTimeslot(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLibrarian.removeTimeslot(this);
      if (!wasRemoved)
      {
        librarians.add(oldIndex,aLibrarian);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLibrarianAt(Librarian aLibrarian, int index)
  {  
    boolean wasAdded = false;
    if(addLibrarian(aLibrarian))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibrarians()) { index = numberOfLibrarians() - 1; }
      librarians.remove(aLibrarian);
      librarians.add(index, aLibrarian);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLibrarianAt(Librarian aLibrarian, int index)
  {
    boolean wasAdded = false;
    if(librarians.contains(aLibrarian))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibrarians()) { index = numberOfLibrarians() - 1; }
      librarians.remove(aLibrarian);
      librarians.add(index, aLibrarian);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLibrarianAt(aLibrarian, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReservations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reservation addReservation(int aReservationID, Visitor aVisitor)
  {
    return new Reservation(aReservationID, this, aVisitor);
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    Timeslot existingTimeslot = aReservation.getTimeslot();
    boolean isNewTimeslot = existingTimeslot != null && !this.equals(existingTimeslot);
    if (isNewTimeslot)
    {
      aReservation.setTimeslot(this);
    }
    else
    {
      reservations.add(aReservation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReservation(Reservation aReservation)
  {
    boolean wasRemoved = false;
    //Unable to remove aReservation, as it must always have a timeslot
    if (!this.equals(aReservation.getTimeslot()))
    {
      reservations.remove(aReservation);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReservationAt(Reservation aReservation, int index)
  {  
    boolean wasAdded = false;
    if(addReservation(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservationAt(Reservation aReservation, int index)
  {
    boolean wasAdded = false;
    if(reservations.contains(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservationAt(aReservation, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Librarian> copyOfLibrarians = new ArrayList<Librarian>(librarians);
    librarians.clear();
    for(Librarian aLibrarian : copyOfLibrarians)
    {
      aLibrarian.removeTimeslot(this);
    }
    HeadLibrarian existingHeadLibrarian = headLibrarian;
    headLibrarian = null;
    if (existingHeadLibrarian != null)
    {
      existingHeadLibrarian.delete();
    }
    Event existingEvent = event;
    event = null;
    if (existingEvent != null)
    {
      existingEvent.delete();
    }
    for(int i=reservations.size(); i > 0; i--)
    {
      Reservation aReservation = reservations.get(i - 1);
      aReservation.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "dayOfTheWeek" + "=" + (getDayOfTheWeek() != null ? !getDayOfTheWeek().equals(this)  ? getDayOfTheWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "headLibrarian = "+(getHeadLibrarian()!=null?Integer.toHexString(System.identityHashCode(getHeadLibrarian())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "event = "+(getEvent()!=null?Integer.toHexString(System.identityHashCode(getEvent())):"null");
  }
}