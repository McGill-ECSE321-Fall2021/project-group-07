/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Time;
import java.sql.Date;
import java.util.*;

// line 44 "model.ump"
// line 129 "model.ump"
public class LibrarianTimeSlot
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, LibrarianTimeSlot> librariantimeslotsByLibrarianTimeSlotID = new HashMap<Integer, LibrarianTimeSlot>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LibrarianTimeSlot Attributes
  private Time startTime;
  private Time endTime;
  private Date date;
  private DayOfTheWeek dayOfTheWeek;
  private int librarianTimeSlotID;

  //LibrarianTimeSlot Associations
  private Librarian librarian;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LibrarianTimeSlot(Time aStartTime, Time aEndTime, Date aDate, DayOfTheWeek aDayOfTheWeek, int aLibrarianTimeSlotID, Librarian aLibrarian)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    date = aDate;
    dayOfTheWeek = aDayOfTheWeek;
    if (!setLibrarianTimeSlotID(aLibrarianTimeSlotID))
    {
      throw new RuntimeException("Cannot create due to duplicate librarianTimeSlotID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (!setLibrarian(aLibrarian))
    {
      throw new RuntimeException("Unable to create LibrarianTimeSlot due to aLibrarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDayOfTheWeek(DayOfTheWeek aDayOfTheWeek)
  {
    boolean wasSet = false;
    dayOfTheWeek = aDayOfTheWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setLibrarianTimeSlotID(int aLibrarianTimeSlotID)
  {
    boolean wasSet = false;
    Integer anOldLibrarianTimeSlotID = getLibrarianTimeSlotID();
    if (anOldLibrarianTimeSlotID != null && anOldLibrarianTimeSlotID.equals(aLibrarianTimeSlotID)) {
      return true;
    }
    if (hasWithLibrarianTimeSlotID(aLibrarianTimeSlotID)) {
      return wasSet;
    }
    librarianTimeSlotID = aLibrarianTimeSlotID;
    wasSet = true;
    if (anOldLibrarianTimeSlotID != null) {
      librariantimeslotsByLibrarianTimeSlotID.remove(anOldLibrarianTimeSlotID);
    }
    librariantimeslotsByLibrarianTimeSlotID.put(aLibrarianTimeSlotID, this);
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public Date getDate()
  {
    return date;
  }

  public DayOfTheWeek getDayOfTheWeek()
  {
    return dayOfTheWeek;
  }

  public int getLibrarianTimeSlotID()
  {
    return librarianTimeSlotID;
  }
  /* Code from template attribute_GetUnique */
  public static LibrarianTimeSlot getWithLibrarianTimeSlotID(int aLibrarianTimeSlotID)
  {
    return librariantimeslotsByLibrarianTimeSlotID.get(aLibrarianTimeSlotID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithLibrarianTimeSlotID(int aLibrarianTimeSlotID)
  {
    return getWithLibrarianTimeSlotID(aLibrarianTimeSlotID) != null;
  }
  /* Code from template association_GetOne */
  public Librarian getLibrarian()
  {
    return librarian;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setLibrarian(Librarian aNewLibrarian)
  {
    boolean wasSet = false;
    if (aNewLibrarian != null)
    {
      librarian = aNewLibrarian;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    librariantimeslotsByLibrarianTimeSlotID.remove(getLibrarianTimeSlotID());
    librarian = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "librarianTimeSlotID" + ":" + getLibrarianTimeSlotID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "dayOfTheWeek" + "=" + (getDayOfTheWeek() != null ? !getDayOfTheWeek().equals(this)  ? getDayOfTheWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "librarian = "+(getLibrarian()!=null?Integer.toHexString(System.identityHashCode(getLibrarian())):"null");
  }
}