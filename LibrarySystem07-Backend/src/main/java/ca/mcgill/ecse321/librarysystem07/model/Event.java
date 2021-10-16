/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;

import ca.mcgill.ecse321.librarysystem07.model.Timeslot.DayoftheWeek;

import java.sql.Time;
import java.sql.Date;

// line 53 "model.ump"
// line 98 "model.ump"
public class Event
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Event Associations
  private Timeslot timeslot;
  private List<Visitor> visitors;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Event(Timeslot aTimeslot)
  {
    if (aTimeslot == null || aTimeslot.getEvent() != null)
    {
      throw new RuntimeException("Unable to create Event due to aTimeslot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    timeslot = aTimeslot;
    visitors = new ArrayList<Visitor>();
  }

  public Event(Time aStartTimeForTimeslot, Time aEndTimeForTimeslot, Date aStartDateForTimeslot, Date aEndDateForTimeslot, DayoftheWeek aDayOfTheWeekForTimeslot, HeadLibrarian aHeadLibrarianForTimeslot)
  {
    timeslot = new Timeslot(aStartTimeForTimeslot, aEndTimeForTimeslot, aStartDateForTimeslot, aEndDateForTimeslot, aDayOfTheWeekForTimeslot, aHeadLibrarianForTimeslot, this);
    visitors = new ArrayList<Visitor>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Timeslot getTimeslot()
  {
    return timeslot;
  }
  /* Code from template association_GetMany */
  public Visitor getVisitor(int index)
  {
    Visitor aVisitor = visitors.get(index);
    return aVisitor;
  }

  public List<Visitor> getVisitors()
  {
    List<Visitor> newVisitors = Collections.unmodifiableList(visitors);
    return newVisitors;
  }

  public int numberOfVisitors()
  {
    int number = visitors.size();
    return number;
  }

  public boolean hasVisitors()
  {
    boolean has = visitors.size() > 0;
    return has;
  }

  public int indexOfVisitor(Visitor aVisitor)
  {
    int index = visitors.indexOf(aVisitor);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfVisitors()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addVisitor(Visitor aVisitor)
  {
    boolean wasAdded = false;
    if (visitors.contains(aVisitor)) { return false; }
    visitors.add(aVisitor);
    if (aVisitor.indexOfEvent(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aVisitor.addEvent(this);
      if (!wasAdded)
      {
        visitors.remove(aVisitor);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeVisitor(Visitor aVisitor)
  {
    boolean wasRemoved = false;
    if (!visitors.contains(aVisitor))
    {
      return wasRemoved;
    }

    int oldIndex = visitors.indexOf(aVisitor);
    visitors.remove(oldIndex);
    if (aVisitor.indexOfEvent(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aVisitor.removeEvent(this);
      if (!wasRemoved)
      {
        visitors.add(oldIndex,aVisitor);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addVisitorAt(Visitor aVisitor, int index)
  {  
    boolean wasAdded = false;
    if(addVisitor(aVisitor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfVisitors()) { index = numberOfVisitors() - 1; }
      visitors.remove(aVisitor);
      visitors.add(index, aVisitor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveVisitorAt(Visitor aVisitor, int index)
  {
    boolean wasAdded = false;
    if(visitors.contains(aVisitor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfVisitors()) { index = numberOfVisitors() - 1; }
      visitors.remove(aVisitor);
      visitors.add(index, aVisitor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addVisitorAt(aVisitor, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Timeslot existingTimeslot = timeslot;
    timeslot = null;
    if (existingTimeslot != null)
    {
      existingTimeslot.delete();
    }
    ArrayList<Visitor> copyOfVisitors = new ArrayList<Visitor>(visitors);
    visitors.clear();
    for(Visitor aVisitor : copyOfVisitors)
    {
      aVisitor.removeEvent(this);
    }
  }

}