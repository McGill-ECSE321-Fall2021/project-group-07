/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Time;
import java.sql.Date;
import javax.persistence.*;
// line 23 "model.ump"
// line 108 "model.ump"
// line 128 "model.ump"
@Entity
@Table(name = "Head Librarian")
public class HeadLibrarian extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarian Associations
  private List<TimeSlot> timeSlots;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HeadLibrarian(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary)
  {
    super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
    timeSlots = new ArrayList<TimeSlot>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public TimeSlot getTimeSlot(int index)
  {
    TimeSlot aTimeSlot = timeSlots.get(index);
    return aTimeSlot;
  }

  @OneToMany
  public List<TimeSlot> getTimeSlots()
  {
    List<TimeSlot> newTimeSlots = Collections.unmodifiableList(timeSlots);
    return newTimeSlots;
  }

  public int numberOfTimeSlots()
  {
    int number = timeSlots.size();
    return number;
  }

  public boolean hasTimeSlots()
  {
    boolean has = timeSlots.size() > 0;
    return has;
  }

  public int indexOfTimeSlot(TimeSlot aTimeSlot)
  {
    int index = timeSlots.indexOf(aTimeSlot);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addTimeSlot(Time aStartTime, Time aEndTime, Date aDate, TimeSlot.DayOfTheWeek aDayOfTheWeek, int aTimeSlotID, Librarian aLibrarian, Library aLibrary, Event aEvent, Reservation aReservation)
  {
    return new TimeSlot(aStartTime, aEndTime, aDate, aDayOfTheWeek, aTimeSlotID, aLibrarian, this, aLibrary, aEvent, aReservation);
  }

  public boolean addTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasAdded = false;
    if (timeSlots.contains(aTimeSlot)) { return false; }
    HeadLibrarian existingHeadLibrarian = aTimeSlot.getHeadLibrarian();
    boolean isNewHeadLibrarian = existingHeadLibrarian != null && !this.equals(existingHeadLibrarian);
    if (isNewHeadLibrarian)
    {
      aTimeSlot.setHeadLibrarian(this);
    }
    else
    {
      timeSlots.add(aTimeSlot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasRemoved = false;
    //Unable to remove aTimeSlot, as it must always have a headLibrarian
    if (!this.equals(aTimeSlot.getHeadLibrarian()))
    {
      timeSlots.remove(aTimeSlot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTimeSlotAt(TimeSlot aTimeSlot, int index)
  {  
    boolean wasAdded = false;
    if(addTimeSlot(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTimeSlotAt(TimeSlot aTimeSlot, int index)
  {
    boolean wasAdded = false;
    if(timeSlots.contains(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTimeSlotAt(aTimeSlot, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=timeSlots.size(); i > 0; i--)
    {
      TimeSlot aTimeSlot = timeSlots.get(i - 1);
      aTimeSlot.delete();
    }
    super.delete();
  }

}