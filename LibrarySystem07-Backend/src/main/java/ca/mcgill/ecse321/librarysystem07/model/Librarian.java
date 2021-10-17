/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;

// line 17 "model.ump"
// line 138 "model.ump"
public class Librarian extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Attributes
  private List<Time> hours;

  //Librarian Associations
  private List<Timeslot> timeslots;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Librarian(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary, List<Time> aHours)
  {
    super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
    hours = aHours;
    timeslots = new ArrayList<Timeslot>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setHours(List<Time> aHours)
  {
    boolean wasSet = false;
    hours = aHours;
    wasSet = true;
    return wasSet;
  }

  public List<Time> getHours()
  {
    return hours;
  }
  /* Code from template association_GetMany */
  public Timeslot getTimeslot(int index)
  {
    Timeslot aTimeslot = timeslots.get(index);
    return aTimeslot;
  }

  public List<Timeslot> getTimeslots()
  {
    List<Timeslot> newTimeslots = Collections.unmodifiableList(timeslots);
    return newTimeslots;
  }

  public int numberOfTimeslots()
  {
    int number = timeslots.size();
    return number;
  }

  public boolean hasTimeslots()
  {
    boolean has = timeslots.size() > 0;
    return has;
  }

  public int indexOfTimeslot(Timeslot aTimeslot)
  {
    int index = timeslots.indexOf(aTimeslot);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeslots()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addTimeslot(Timeslot aTimeslot)
  {
    boolean wasAdded = false;
    if (timeslots.contains(aTimeslot)) { return false; }
    timeslots.add(aTimeslot);
    if (aTimeslot.indexOfLibrarian(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTimeslot.addLibrarian(this);
      if (!wasAdded)
      {
        timeslots.remove(aTimeslot);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeTimeslot(Timeslot aTimeslot)
  {
    boolean wasRemoved = false;
    if (!timeslots.contains(aTimeslot))
    {
      return wasRemoved;
    }

    int oldIndex = timeslots.indexOf(aTimeslot);
    timeslots.remove(oldIndex);
    if (aTimeslot.indexOfLibrarian(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTimeslot.removeLibrarian(this);
      if (!wasRemoved)
      {
        timeslots.add(oldIndex,aTimeslot);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTimeslotAt(Timeslot aTimeslot, int index)
  {  
    boolean wasAdded = false;
    if(addTimeslot(aTimeslot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeslots()) { index = numberOfTimeslots() - 1; }
      timeslots.remove(aTimeslot);
      timeslots.add(index, aTimeslot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTimeslotAt(Timeslot aTimeslot, int index)
  {
    boolean wasAdded = false;
    if(timeslots.contains(aTimeslot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeslots()) { index = numberOfTimeslots() - 1; }
      timeslots.remove(aTimeslot);
      timeslots.add(index, aTimeslot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTimeslotAt(aTimeslot, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Timeslot> copyOfTimeslots = new ArrayList<Timeslot>(timeslots);
    timeslots.clear();
    for(Timeslot aTimeslot : copyOfTimeslots)
    {
      aTimeslot.removeLibrarian(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hours" + "=" + (getHours() != null ? !getHours().equals(this)  ? getHours().toString().replaceAll("  ","    ") : "this" : "null");
  }
}