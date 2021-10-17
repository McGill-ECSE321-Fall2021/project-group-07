/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;

import ca.mcgill.ecse321.librarysystem07.model.ReservableItem.TypeOfReservableItem;

// line 87 "model.ump"
// line 146 "model.ump"
public class Reservation
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Reservation> reservationsByReservationID = new HashMap<Integer, Reservation>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private int reservationID;
  private TimeSlot reservationTimeSlot;

  //Reservation Associations
  private Visitor visitor;
  private List<ReservableItem> reservableItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(int aReservationID, TimeSlot aReservationTimeSlot, Visitor aVisitor)
  {
    reservationTimeSlot = aReservationTimeSlot;
    if (!setReservationID(aReservationID))
    {
      throw new RuntimeException("Cannot create due to duplicate reservationID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddVisitor = setVisitor(aVisitor);
    if (!didAddVisitor)
    {
      throw new RuntimeException("Unable to create reservation due to visitor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    reservableItems = new ArrayList<ReservableItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReservationID(int aReservationID)
  {
    boolean wasSet = false;
    Integer anOldReservationID = getReservationID();
    if (anOldReservationID != null && anOldReservationID.equals(aReservationID)) {
      return true;
    }
    if (hasWithReservationID(aReservationID)) {
      return wasSet;
    }
    reservationID = aReservationID;
    wasSet = true;
    if (anOldReservationID != null) {
      reservationsByReservationID.remove(anOldReservationID);
    }
    reservationsByReservationID.put(aReservationID, this);
    return wasSet;
  }

  public boolean setReservationTimeSlot(TimeSlot aReservationTimeSlot)
  {
    boolean wasSet = false;
    reservationTimeSlot = aReservationTimeSlot;
    wasSet = true;
    return wasSet;
  }

  public int getReservationID()
  {
    return reservationID;
  }
  /* Code from template attribute_GetUnique */
  public static Reservation getWithReservationID(int aReservationID)
  {
    return reservationsByReservationID.get(aReservationID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithReservationID(int aReservationID)
  {
    return getWithReservationID(aReservationID) != null;
  }

  public TimeSlot getReservationTimeSlot()
  {
    return reservationTimeSlot;
  }
  /* Code from template association_GetOne */
  public Visitor getVisitor()
  {
    return visitor;
  }
  /* Code from template association_GetMany */
  public ReservableItem getReservableItem(int index)
  {
    ReservableItem aReservableItem = reservableItems.get(index);
    return aReservableItem;
  }

  public List<ReservableItem> getReservableItems()
  {
    List<ReservableItem> newReservableItems = Collections.unmodifiableList(reservableItems);
    return newReservableItems;
  }

  public int numberOfReservableItems()
  {
    int number = reservableItems.size();
    return number;
  }

  public boolean hasReservableItems()
  {
    boolean has = reservableItems.size() > 0;
    return has;
  }

  public int indexOfReservableItem(ReservableItem aReservableItem)
  {
    int index = reservableItems.indexOf(aReservableItem);
    return index;
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
      existingVisitor.removeReservation(this);
    }
    visitor.addReservation(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReservableItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */

  public ReservableItem addReservableItem(int aId, Library aLibrary, int aDuplicates, String aName, String aAuthor, ReservableItem.Status aStatus, ReservableItem.TypeOfReservableItem aReservableItem)
  {
    return new ReservableItem(aId, aLibrary, aDuplicates, aName, aAuthor, aStatus, aReservableItem);
  }

  public boolean addReservableItem(ReservableItem aReservableItem)
  {
    boolean wasAdded = false;
    if (reservableItems.contains(aReservableItem)) { return false; }
    Reservation existingReservation = aReservableItem.getReservation();
    boolean isNewReservation = existingReservation != null && !this.equals(existingReservation);
    if (isNewReservation)
    {
      aReservableItem.setReservation(this);
    }
    else
    {
      reservableItems.add(aReservableItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReservableItem(ReservableItem aReservableItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aReservableItem, as it must always have a reservation
    if (!this.equals(aReservableItem.getReservation()))
    {
      reservableItems.remove(aReservableItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReservableItemAt(ReservableItem aReservableItem, int index)
  {  
    boolean wasAdded = false;
    if(addReservableItem(aReservableItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservableItems()) { index = numberOfReservableItems() - 1; }
      reservableItems.remove(aReservableItem);
      reservableItems.add(index, aReservableItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservableItemAt(ReservableItem aReservableItem, int index)
  {
    boolean wasAdded = false;
    if(reservableItems.contains(aReservableItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservableItems()) { index = numberOfReservableItems() - 1; }
      reservableItems.remove(aReservableItem);
      reservableItems.add(index, aReservableItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservableItemAt(aReservableItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    reservationsByReservationID.remove(getReservationID());
    Visitor placeholderVisitor = visitor;
    this.visitor = null;
    if(placeholderVisitor != null)
    {
      placeholderVisitor.removeReservation(this);
    }
    while (reservableItems.size() > 0)
    {
      ReservableItem aReservableItem = reservableItems.get(reservableItems.size() - 1);
      aReservableItem.delete();
      reservableItems.remove(aReservableItem);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "reservationID" + ":" + getReservationID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reservationTimeSlot" + "=" + (getReservationTimeSlot() != null ? !getReservationTimeSlot().equals(this)  ? getReservationTimeSlot().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "visitor = "+(getVisitor()!=null?Integer.toHexString(System.identityHashCode(getVisitor())):"null");
  }
}