package ca.mcgill.ecse321.librarysystem07.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 17 "model.ump"
// line 107 "model.ump"
public class Librarian extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Attributes
  private List<TimeSlot> schedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Librarian(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary, List<TimeSlot> aSchedule)
  {
    super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
    schedule = aSchedule;
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

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "schedule" + "=" + (getSchedule() != null ? !getSchedule().equals(this)  ? getSchedule().toString().replaceAll("  ","    ") : "this" : "null");
  }
}