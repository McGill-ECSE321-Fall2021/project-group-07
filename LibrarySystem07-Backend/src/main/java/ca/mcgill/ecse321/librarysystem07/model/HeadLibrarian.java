/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import ca.mcgill.ecse321.librarysystem07.dao.TimeslotRepository;
import java.sql.Time;
import java.util.List;

import javax.persistence.Entity;

import java.sql.Date;
import ca.mcgill.ecse321.librarysystem07.model.Timeslot.DayoftheWeek;
// line 24 "model.ump"
// line 144 "model.ump"
@Entity
public class HeadLibrarian extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarian Attributes
  private List<Time> hours;

  //HeadLibrarian Associations
  private Timeslot timeslot;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HeadLibrarian(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary, List<Time> aHours, Timeslot aTimeslot)
  {
    super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
    hours = aHours;
    if (aTimeslot == null)
    {
      throw new RuntimeException("Unable to create HeadLibrarian due to aTimeslot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    timeslot = aTimeslot;
  }

  public HeadLibrarian(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary, List<Time> aHours, Time aStartTimeForTimeslot, Time aEndTimeForTimeslot, Date aStartDateForTimeslot, Date aEndDateForTimeslot, DayoftheWeek aDayOfTheWeekForTimeslot, Event aEventForTimeslot)
  {
    super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
    hours = aHours;
    timeslot = new Timeslot(aStartTimeForTimeslot, aEndTimeForTimeslot, aStartDateForTimeslot, aEndDateForTimeslot, aDayOfTheWeekForTimeslot);
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
  /* Code from template association_GetOne */
  public Timeslot getTimeslot()
  {
    return timeslot;
  }

  //how
  public void delete()
  {
    Timeslot existingTimeslot = timeslot;
    timeslot = null;
    if (existingTimeslot != null)
    {
   //   TimeslotRepository.delete(existingTimeslot);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hours" + "=" + (getHours() != null ? !getHours().equals(this)  ? getHours().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "timeslot = "+(getTimeslot()!=null?Integer.toHexString(System.identityHashCode(getTimeslot())):"null");
  }
}