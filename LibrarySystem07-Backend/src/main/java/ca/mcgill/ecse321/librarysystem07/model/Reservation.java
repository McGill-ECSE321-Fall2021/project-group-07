/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;

// line 87 "model.ump"
// line 155 "model.ump"
public class Reservation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private int reservationID;

  //Reservation Associations
  private Timeslot timeslot;
  private Visitor visitor;
  private List<ReservableItem> reservableItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(int aReservationID, Timeslot aTimeslot, Visitor aVisitor)
  {
    reservationID = aReservationID;
    boolean didAddTimeslot = setTimeslot(aTimeslot);
    if (!didAddTimeslot)
    {
      throw new RuntimeException("Unable to create reservation due to timeslot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
    reservationID = aReservationID;
    wasSet = true;
    return wasSet;
  }

  public int getReservationID()
  {
    return reservationID;
  }
  /* Code from template association_GetOne */
  public Timeslot getTimeslot()
  {
    return timeslot;
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
  public boolean setTimeslot(Timeslot aTimeslot)
  {
    boolean wasSet = false;
    if (aTimeslot == null)
    {
      return wasSet;
    }
    wasSet = true;
    return wasSet;
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
  public ReservableItem addReservableItem(String aId, Library aLibrary, int aDuplicates, String aName, String aAuthor, ReservableItem.Status aStatus, ReservableItem.TypeOfReservableItem aReservableItem)
  {
    return new ReservableItem(aId, aLibrary, aDuplicates, aName, aAuthor, aStatus, aReservableItem, this);
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
    Timeslot placeholderTimeslot = timeslot;
    this.timeslot = null;
    Visitor placeholderVisitor = visitor;
    this.visitor = null;
    if(placeholderVisitor != null)
    {
      placeholderVisitor.removeReservation(this);
    }
    for(int i=reservableItems.size(); i > 0; i--)
    {
      ReservableItem aReservableItem = reservableItems.get(i - 1);
      aReservableItem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "reservationID" + ":" + getReservationID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "timeslot = "+(getTimeslot()!=null?Integer.toHexString(System.identityHashCode(getTimeslot())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "visitor = "+(getVisitor()!=null?Integer.toHexString(System.identityHashCode(getVisitor())):"null");
  }
}