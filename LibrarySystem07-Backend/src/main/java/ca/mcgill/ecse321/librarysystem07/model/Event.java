/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Event")
public class Event
{

 
  private String name;
  private int eventID;
  private Visitor visitor;

  public Event(String aName, int aEventID, Visitor aVisitor)
  {
    setName(aName);
    if (!setEventID(aEventID))
    {
      throw new RuntimeException("Cannot create due to duplicate eventID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (!setVisitor(aVisitor))
    {
      throw new RuntimeException("Unable to create Event due to aVisitor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
  }

  public void setEventID(int aEventID)
  {
    eventID = aEventID;
  }

  public String getName()
  {
    return name;
  }

  public int getEventID()
  {
    return eventID;
  }
  /* Code from template attribute_GetUnique */
  public static Event getWithEventID(int aEventID)
  {
    return eventsByEventID.get(aEventID);
  }

  /* Code from template association_GetOne */
  public Visitor getVisitor()
  {
    return visitor;
  }
  /* Code from template association_SetUnidirectionalOne */
  public void setVisitor(Visitor aNewVisitor)
  {
      visitor = aNewVisitor;
  }
}