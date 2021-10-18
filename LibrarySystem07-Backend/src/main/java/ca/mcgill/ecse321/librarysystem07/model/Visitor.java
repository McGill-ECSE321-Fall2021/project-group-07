/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;
import javax.persistence.*;

// line 11 "model.ump"
// line 102 "model.ump"
@Entity
public class Visitor extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Visitor Attributes
  private int demeritPoints;

  //Visitor Associations
  private List<Event> events;
  private List<Reservation> reservations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Visitor(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary, int aDemeritPoints)
  {
    super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
    demeritPoints = aDemeritPoints;
    events = new ArrayList<Event>();
    reservations = new ArrayList<Reservation>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDemeritPoints(int aDemeritPoints)
  {
    boolean wasSet = false;
    demeritPoints = aDemeritPoints;
    wasSet = true;
    return wasSet;
  }

  public int getDemeritPoints()
  {
    return demeritPoints;
  }
  
//  @Id
//  public Integer getLibrarianCardID() {
//	  return getLibraryCardID();
//  }
  
  /* Code from template association_GetMany */
  public Event getEvent(int index)
  {
    Event aEvent = events.get(index);
    return aEvent;
  }

  public List<Event> getEvents()
  {
    List<Event> newEvents = Collections.unmodifiableList(events);
    return newEvents;
  }

  public int numberOfEvents()
  {
    int number = events.size();
    return number;
  }

  public boolean hasEvents()
  {
    boolean has = events.size() > 0;
    return has;
  }

  public int indexOfEvent(Event aEvent)
  {
    int index = events.indexOf(aEvent);
    return index;
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
  public static int minimumNumberOfEvents()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Event addEvent(List<TimeSlot> aSchedule)
  {
    return new Event(aSchedule, this);
  }

  public boolean addEvent(Event aEvent)
  {
    boolean wasAdded = false;
    if (events.contains(aEvent)) { return false; }
    Visitor existingVisitor = aEvent.getVisitor();
    boolean isNewVisitor = existingVisitor != null && !this.equals(existingVisitor);
    if (isNewVisitor)
    {
      aEvent.setVisitor(this);
    }
    else
    {
      events.add(aEvent);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEvent(Event aEvent)
  {
    boolean wasRemoved = false;
    //Unable to remove aEvent, as it must always have a visitor
    if (!this.equals(aEvent.getVisitor()))
    {
      events.remove(aEvent);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEventAt(Event aEvent, int index)
  {  
    boolean wasAdded = false;
    if(addEvent(aEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvents()) { index = numberOfEvents() - 1; }
      events.remove(aEvent);
      events.add(index, aEvent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEventAt(Event aEvent, int index)
  {
    boolean wasAdded = false;
    if(events.contains(aEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvents()) { index = numberOfEvents() - 1; }
      events.remove(aEvent);
      events.add(index, aEvent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEventAt(aEvent, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReservations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reservation addReservation(int aReservationID, TimeSlot aReservationTimeSlot)
  {
    return new Reservation(aReservationID, aReservationTimeSlot, this);
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    Visitor existingVisitor = aReservation.getVisitor();
    boolean isNewVisitor = existingVisitor != null && !this.equals(existingVisitor);
    if (isNewVisitor)
    {
      aReservation.setVisitor(this);
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
    //Unable to remove aReservation, as it must always have a visitor
    if (!this.equals(aReservation.getVisitor()))
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
    while (events.size() > 0)
    {
      Event aEvent = events.get(events.size() - 1);
      aEvent.delete();
      events.remove(aEvent);
    }
    
    while (reservations.size() > 0)
    {
      Reservation aReservation = reservations.get(reservations.size() - 1);
      aReservation.delete();
      reservations.remove(aReservation);
    }
    
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "demeritPoints" + ":" + getDemeritPoints()+ "]";
  }
}