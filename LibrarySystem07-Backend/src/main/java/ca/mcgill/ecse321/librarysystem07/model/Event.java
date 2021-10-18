/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.librarysystem07.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Id;
import javax.persistence.OneToOne;

// line 52 "model.ump"
// line 123 "model.ump"
public class Event
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

	 private static Map<Integer, Event> eventsByEventID = new HashMap<Integer, Event>();

  //Event Attributes
  private List<TimeSlot> schedule;
  private int id;

  //Event Associations
  private Visitor visitor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Event(List<TimeSlot> aSchedule, Visitor aVisitor)
  {
    schedule = aSchedule;
    boolean didAddVisitor = setVisitor(aVisitor);
    if (!didAddVisitor)
    {
      throw new RuntimeException("Unable to create event due to visitor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    int anId = 0;
    for (Entry<Integer, Event> e : eventsByEventID.entrySet()) {
    	if (e.getKey() > anId) anId = e.getKey();
    }
    if (anId == 0) id = anId;
    else id = anId + 1;
    
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSchedule(List<TimeSlot> aSchedule)
  {
    boolean wasSet = false;
    schedule = aSchedule;
    wasSet = true;
    return wasSet;
  }

  public List<TimeSlot> getSchedule()
  {
    return schedule;
  }
  /* Code from template association_GetOne */
  public Visitor getVisitor()
  {
    return visitor;
  }
  
  @Id
  @OneToOne(optional=false)
  public int getEventID()
  {
    return id;
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

  public void delete()
  {
	 eventsByEventID.remove(getEventID());
    Visitor placeholderVisitor = visitor;
    this.visitor = null;
    if(placeholderVisitor != null)
    {
      placeholderVisitor.removeEvent(this);
    }
  }
  
  /* Code from template attribute_GetUnique */
  public static Event getWithTimeSlotID(int id)
  {
    return eventsByEventID.get(id);
  }
  
  /* Code from template attribute_HasUnique */
  public static boolean hasWithTimeSlotID(int id)
  {
    return getWithTimeSlotID(id) != null;
  }



  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "schedule" + "=" + (getSchedule() != null ? !getSchedule().equals(this)  ? getSchedule().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "visitor = "+(getVisitor()!=null?Integer.toHexString(System.identityHashCode(getVisitor())):"null");
  }
}