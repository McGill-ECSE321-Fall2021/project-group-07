package ca.mcgill.ecse321.librarysystem07.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Time;
import java.sql.Date;
import javax.persistence.*;
// line 51 "model.ump"
// line 96 "model.ump"
@Entity
@Table(name = "Event")
public class Event
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Event> eventsByEventID = new HashMap<Integer, Event>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Event Attributes
  private String name;
  private int eventID;

  //Event Associations
  private Visitor visitor;
  private List<TimeSlot> timeSlots;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Event(String aName, int aEventID, Visitor aVisitor)
  {
    name = aName;
    if (!setEventID(aEventID))
    {
      throw new RuntimeException("Cannot create due to duplicate eventID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddVisitor = setVisitor(aVisitor);
    if (!didAddVisitor)
    {
      throw new RuntimeException("Unable to create event due to visitor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    timeSlots = new ArrayList<TimeSlot>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEventID(int aEventID)
  {
    boolean wasSet = false;
    Integer anOldEventID = getEventID();
    if (anOldEventID != null && anOldEventID.equals(aEventID)) {
      return true;
    }
    if (hasWithEventID(aEventID)) {
      return wasSet;
    }
    eventID = aEventID;
    wasSet = true;
    if (anOldEventID != null) {
      eventsByEventID.remove(anOldEventID);
    }
    eventsByEventID.put(aEventID, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  
  //@GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  public int getEventID()
  {
    return eventID;
  }
  /* Code from template attribute_GetUnique */
  public static Event getWithEventID(int aEventID)
  {
    return eventsByEventID.get(aEventID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEventID(int aEventID)
  {
    return getWithEventID(aEventID) != null;
  }
  /* Code from template association_GetOne */
//@JoinColumn(name="event_librarycardid")
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
	  this.timeSlots = slots;
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
      existingVisitor.removeEvent(this);
    }
    visitor.addEvent(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addTimeSlot(Time aStartTime, Time aEndTime, Date aDate, TimeSlot.DayOfTheWeek aDayOfTheWeek, int aTimeSlotID, Librarian aLibrarian, HeadLibrarian aHeadLibrarian, Library aLibrary, Reservation aReservation)
  {
    return new TimeSlot(aStartTime, aEndTime, aDate, aDayOfTheWeek, aTimeSlotID, aLibrarian, aHeadLibrarian, aLibrary, this, aReservation);
  }

  public boolean addTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasAdded = false;
    if (timeSlots.contains(aTimeSlot)) { return false; }
    Event existingEvent = aTimeSlot.getEvent();
    boolean isNewEvent = existingEvent != null && !this.equals(existingEvent);
    if (isNewEvent)
    {
      aTimeSlot.setEvent(this);
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
    //Unable to remove aTimeSlot, as it must always have a event
    if (!this.equals(aTimeSlot.getEvent()))
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
    eventsByEventID.remove(getEventID());
    Visitor placeholderVisitor = visitor;
    this.visitor = null;
    if(placeholderVisitor != null)
    {
      placeholderVisitor.removeEvent(this);
    }
    for(int i=timeSlots.size(); i > 0; i--)
    {
      TimeSlot aTimeSlot = timeSlots.get(i - 1);
      aTimeSlot.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "eventID" + ":" + getEventID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "visitor = "+(getVisitor()!=null?Integer.toHexString(System.identityHashCode(getVisitor())):"null");
  }
}