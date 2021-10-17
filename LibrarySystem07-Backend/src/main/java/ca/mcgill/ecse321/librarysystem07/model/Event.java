/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.librarysystem07.model;

import java.util.List;

// line 52 "model.ump"
// line 123 "model.ump"
public class Event
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Event Attributes
  private List<TimeSlot> schedule;

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
    Visitor placeholderVisitor = visitor;
    this.visitor = null;
    if(placeholderVisitor != null)
    {
      placeholderVisitor.removeEvent(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "schedule" + "=" + (getSchedule() != null ? !getSchedule().equals(this)  ? getSchedule().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "visitor = "+(getVisitor()!=null?Integer.toHexString(System.identityHashCode(getVisitor())):"null");
  }
}