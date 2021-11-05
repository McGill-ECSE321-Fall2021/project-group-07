/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
<<<<<<< HEAD
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;

// line 37 "model.ump"
// line 123 "model.ump"
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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Event(String aName, int aEventID, Visitor aVisitor)
  {
    name = aName;
=======


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
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa
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

<<<<<<< HEAD
  public boolean setName(String aName)
=======
  public void setName(String aName)
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
  }

<<<<<<< HEAD
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

  public int getEventID()
  {
=======
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
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa
    return eventID;
  }
  /* Code from template attribute_GetUnique */
  public static Event getWithEventID(int aEventID)
  {
    return eventsByEventID.get(aEventID);
  }
<<<<<<< HEAD
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEventID(int aEventID)
  {
    return getWithEventID(aEventID) != null;
  }
  /* Code from template association_GetOne */
  public Visitor getVisitor()
  {
    return visitor;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setVisitor(Visitor aNewVisitor)
  {
    boolean wasSet = false;
    if (aNewVisitor != null)
    {
      visitor = aNewVisitor;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    eventsByEventID.remove(getEventID());
    visitor = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "eventID" + ":" + getEventID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "visitor = "+(getVisitor()!=null?Integer.toHexString(System.identityHashCode(getVisitor())):"null");
=======

  /* Code from template association_GetOne */
  public Visitor getVisitor()
  {
    return visitor;
  }
  /* Code from template association_SetUnidirectionalOne */
  public void setVisitor(Visitor aNewVisitor)
  {
      visitor = aNewVisitor;
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa
  }
}