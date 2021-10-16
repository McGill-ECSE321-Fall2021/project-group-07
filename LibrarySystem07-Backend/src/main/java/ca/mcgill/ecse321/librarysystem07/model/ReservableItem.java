/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;


// line 67 "model.ump"
// line 110 "model.ump"
public class ReservableItem extends InventoryItem
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Status { CheckedOut, OnReserve, Available, Damaged }
  public enum TypeOfReservableItem { CD, Movie, Book }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ReservableItem Attributes
  private int duplicates;
  private String name;
  private String author;
  private Status status;
  private TypeOfReservableItem reservableItem;

  //ReservableItem Associations
  private Reservation reservation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ReservableItem(String aId, Library aLibrary, int aDuplicates, String aName, String aAuthor, Status aStatus, TypeOfReservableItem aReservableItem, Reservation aReservation)
  {
    super(aId, aLibrary);
    duplicates = aDuplicates;
    name = aName;
    author = aAuthor;
    status = aStatus;
    reservableItem = aReservableItem;
    boolean didAddReservation = setReservation(aReservation);
    if (!didAddReservation)
    {
      throw new RuntimeException("Unable to create reservableItem due to reservation. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDuplicates(int aDuplicates)
  {
    boolean wasSet = false;
    duplicates = aDuplicates;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAuthor(String aAuthor)
  {
    boolean wasSet = false;
    author = aAuthor;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(Status aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setReservableItem(TypeOfReservableItem aReservableItem)
  {
    boolean wasSet = false;
    reservableItem = aReservableItem;
    wasSet = true;
    return wasSet;
  }

  public int getDuplicates()
  {
    return duplicates;
  }

  public String getName()
  {
    return name;
  }

  public String getAuthor()
  {
    return author;
  }

  public Status getStatus()
  {
    return status;
  }

  public TypeOfReservableItem getReservableItem()
  {
    return reservableItem;
  }
  /* Code from template association_GetOne */
  public Reservation getReservation()
  {
    return reservation;
  }
  /* Code from template association_SetOneToMany */
  public boolean setReservation(Reservation aReservation)
  {
    boolean wasSet = false;
    if (aReservation == null)
    {
      return wasSet;
    }

    Reservation existingReservation = reservation;
    reservation = aReservation;
    if (existingReservation != null && !existingReservation.equals(aReservation))
    {
      existingReservation.removeReservableItem(this);
    }
    reservation.addReservableItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Reservation placeholderReservation = reservation;
    this.reservation = null;
    if(placeholderReservation != null)
    {
      placeholderReservation.removeReservableItem(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "duplicates" + ":" + getDuplicates()+ "," +
            "name" + ":" + getName()+ "," +
            "author" + ":" + getAuthor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservableItem" + "=" + (getReservableItem() != null ? !getReservableItem().equals(this)  ? getReservableItem().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservation = "+(getReservation()!=null?Integer.toHexString(System.identityHashCode(getReservation())):"null");
  }
}