<<<<<<< HEAD
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Time;
import java.sql.Date;
import java.util.*;

// line 56 "model.ump"
// line 134 "model.ump"
public class HeadLibrarianTimeSlot
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, HeadLibrarianTimeSlot> headlibrariantimeslotsByHeadLibrarianimeSlotID = new HashMap<Integer, HeadLibrarianTimeSlot>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarianTimeSlot Attributes
  private Time startTime;
  private Time endTime;
  private Date date;
  private DayOfTheWeek dayOfTheWeek;
  private int headLibrarianimeSlotID;

  //HeadLibrarianTimeSlot Associations
  private HeadLibrarian headLibrarian;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HeadLibrarianTimeSlot(Time aStartTime, Time aEndTime, Date aDate, DayOfTheWeek aDayOfTheWeek, int aHeadLibrarianimeSlotID, HeadLibrarian aHeadLibrarian)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    date = aDate;
    dayOfTheWeek = aDayOfTheWeek;
    if (!setHeadLibrarianimeSlotID(aHeadLibrarianimeSlotID))
    {
      throw new RuntimeException("Cannot create due to duplicate headLibrarianimeSlotID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (!setHeadLibrarian(aHeadLibrarian))
    {
      throw new RuntimeException("Unable to create HeadLibrarianTimeSlot due to aHeadLibrarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setHeadLibrarianimeSlotID(int aHeadLibrarianimeSlotID)
  {
    boolean wasSet = false;
    Integer anOldHeadLibrarianimeSlotID = getHeadLibrarianimeSlotID();
    if (anOldHeadLibrarianimeSlotID != null && anOldHeadLibrarianimeSlotID.equals(aHeadLibrarianimeSlotID)) {
      return true;
    }
    if (hasWithHeadLibrarianimeSlotID(aHeadLibrarianimeSlotID)) {
      return wasSet;
    }
    headLibrarianimeSlotID = aHeadLibrarianimeSlotID;
    wasSet = true;
    if (anOldHeadLibrarianimeSlotID != null) {
      headlibrariantimeslotsByHeadLibrarianimeSlotID.remove(anOldHeadLibrarianimeSlotID);
    }
    headlibrariantimeslotsByHeadLibrarianimeSlotID.put(aHeadLibrarianimeSlotID, this);
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

  public int getHeadLibrarianimeSlotID()
  {
    return headLibrarianimeSlotID;
  }
  /* Code from template attribute_GetUnique */
  public static HeadLibrarianTimeSlot getWithHeadLibrarianimeSlotID(int aHeadLibrarianimeSlotID)
  {
    return headlibrariantimeslotsByHeadLibrarianimeSlotID.get(aHeadLibrarianimeSlotID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithHeadLibrarianimeSlotID(int aHeadLibrarianimeSlotID)
  {
    return getWithHeadLibrarianimeSlotID(aHeadLibrarianimeSlotID) != null;
  }
  /* Code from template association_GetOne */
  public HeadLibrarian getHeadLibrarian()
  {
    return headLibrarian;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setHeadLibrarian(HeadLibrarian aNewHeadLibrarian)
  {
    boolean wasSet = false;
    if (aNewHeadLibrarian != null)
    {
      headLibrarian = aNewHeadLibrarian;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    headlibrariantimeslotsByHeadLibrarianimeSlotID.remove(getHeadLibrarianimeSlotID());
    headLibrarian = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "headLibrarianimeSlotID" + ":" + getHeadLibrarianimeSlotID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "dayOfTheWeek" + "=" + (getDayOfTheWeek() != null ? !getDayOfTheWeek().equals(this)  ? getDayOfTheWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "headLibrarian = "+(getHeadLibrarian()!=null?Integer.toHexString(System.identityHashCode(getHeadLibrarian())):"null");
  }
}
=======
package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Head Librarian Time Slot")
public class HeadLibrarianTimeSlot {
	
	private Time startTime;
	private Time endTime;
	private DayOfTheWeek dayOfTheWeek;
	private Date date;
	private HeadLibrarian headLibrarian;
	private Library library;
	private int timeSlotId;

	public enum DayOfTheWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	public HeadLibrarianTimeSlot(int headLibrarianTimeSlotId, HeadLibrarian aLibrarian, Time aStartTime, Time aEndTime, Date aDate, DayOfTheWeek aDayOfTheWeek, Library aLibrary)
	{
		setStartTime(aStartTime);
		setEndTime(aEndTime);
		setDate(aDate);
		setDayOfTheWeek(aDayOfTheWeek);
		setHeadLibrarian(aLibrarian);
		setLibrary(aLibrary);
		setTimeSlotId(headLibrarianTimeSlotId);
	}

	public HeadLibrarianTimeSlot getHeadLibrarianTimeSlot() {
		return this;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public DayOfTheWeek getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public HeadLibrarian getHeadLibrarian() {
		return headLibrarian;
	}

	public void setHeadLibrarian(HeadLibrarian headLibrarian) {
		this.headLibrarian = headLibrarian;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	@Id
	public int getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(int timeSlotId) {
		this.timeSlotId = timeSlotId;
	}
}
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa
