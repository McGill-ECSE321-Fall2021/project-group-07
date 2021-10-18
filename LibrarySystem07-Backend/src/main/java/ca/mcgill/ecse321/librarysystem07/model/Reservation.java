/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

import javax.persistence.*;

import java.sql.Time;
import java.sql.Date;

// line 86 "model.ump"
// line 159 "model.ump"
@Entity
@Table(name = "Reservation")
public class Reservation
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Reservation> reservationsByReservationID = new HashMap<Integer, Reservation>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private int reservationID;

  //Reservation Associations
  private Visitor visitor;
  private List<TimeSlot> timeSlots;
  private ReservableItem reservableItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(int aReservationID, Visitor aVisitor, ReservableItem aReservableItem)
  {
    if (!setReservationID(aReservationID))
    {
      throw new RuntimeException("Cannot create due to duplicate reservationID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddVisitor = setVisitor(aVisitor);
    if (!didAddVisitor)
    {
      throw new RuntimeException("Unable to create reservation due to visitor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    timeSlots = new ArrayList<TimeSlot>();
    if (!setReservableItem(aReservableItem))
    {
      throw new RuntimeException("Unable to create Reservation due to aReservableItem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReservationID(int aReservationID)
  {
    boolean wasSet = false;
    Integer anOldReservationID = getReservationID();
    if (anOldReservationID != null && anOldReservationID.equals(aReservationID)) {
      return true;
    }
    if (hasWithReservationID(aReservationID)) {
      return wasSet;
    }
    reservationID = aReservationID;
    wasSet = true;
    if (anOldReservationID != null) {
      reservationsByReservationID.remove(anOldReservationID);
    }
    reservationsByReservationID.put(aReservationID, this);
    return wasSet;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getReservationID()
  {
    return reservationID;
  }
  /* Code from template attribute_GetUnique */
  public static Reservation getWithReservationID(int aReservationID)
  {
    return reservationsByReservationID.get(aReservationID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithReservationID(int aReservationID)
  {
    return getWithReservationID(aReservationID) != null;
  }
  /* Code from template association_GetOne */
  //@JoinColumn(name = "reservation_librarycardid")
  @ManyToOne
  public Visitor getVisitor()
  {
    return visitor;
  }
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
  
  public void setTimeSlots(List<TimeSlot> slots) {
	  for (TimeSlot t : slots) {
		  addTimeSlot(t);
	  }
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
  /* Code from template association_GetOne */
  @OneToOne(optional=false)
  public ReservableItem getReservableItem()
  {
    return reservableItem;
  }
  /* Code from template association_SetOneToMany */
  public boolean setVisitor(Visitor aVisitor)
  {
    boolean wasSet = false;
    if (aVisitor == null)
    {
      return wasSet;
    }

    Visitor existingVisitor = visitor;
    visitor = aVisitor;
    if (existingVisitor != null && !existingVisitor.equals(aVisitor))
    {
      existingVisitor.removeReservation(this);
    }
    visitor.addReservation(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addTimeSlot(Time aStartTime, Time aEndTime, Date aDate, TimeSlot.DayOfTheWeek aDayOfTheWeek, int aTimeSlotID, Librarian aLibrarian, HeadLibrarian aHeadLibrarian, Library aLibrary, Event aEvent)
  {
    return new TimeSlot(aStartTime, aEndTime, aDate, aDayOfTheWeek, aTimeSlotID, aLibrarian, aHeadLibrarian, aLibrary, aEvent, this);
  }

  public boolean addTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasAdded = false;
    if (timeSlots.contains(aTimeSlot)) { return false; }
    Reservation existingReservation = aTimeSlot.getReservation();
    boolean isNewReservation = existingReservation != null && !this.equals(existingReservation);
    if (isNewReservation)
    {
      aTimeSlot.setReservation(this);
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
    //Unable to remove aTimeSlot, as it must always have a reservation
    if (!this.equals(aTimeSlot.getReservation()))
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
  /* Code from template association_SetUnidirectionalOne */
  public boolean setReservableItem(ReservableItem aNewReservableItem)
  {
    boolean wasSet = false;
    if (aNewReservableItem != null)
    {
      reservableItem = aNewReservableItem;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    reservationsByReservationID.remove(getReservationID());
    Visitor placeholderVisitor = visitor;
    this.visitor = null;
    if(placeholderVisitor != null)
    {
      placeholderVisitor.removeReservation(this);
    }
    for(int i=timeSlots.size(); i > 0; i--)
    {
      TimeSlot aTimeSlot = timeSlots.get(i - 1);
      aTimeSlot.delete();
    }
    reservableItem = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "reservationID" + ":" + getReservationID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "visitor = "+(getVisitor()!=null?Integer.toHexString(System.identityHashCode(getVisitor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservableItem = "+(getReservableItem()!=null?Integer.toHexString(System.identityHashCode(getReservableItem())):"null");
  }
}