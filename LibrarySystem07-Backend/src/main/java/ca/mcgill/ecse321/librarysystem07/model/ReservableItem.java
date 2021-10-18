/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Table;

// line 66 "model.ump"
// line 148 "model.ump"
@Entity
@Table(name = "Reservable Item")
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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ReservableItem(int aId, Library aLibrary, int aDuplicates, String aName, String aAuthor, Status aStatus, TypeOfReservableItem aReservableItem)
  {
    super(aId, aLibrary);
    duplicates = aDuplicates;
    name = aName;
    author = aAuthor;
    status = aStatus;
    reservableItem = aReservableItem;
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

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "duplicates" + ":" + getDuplicates()+ "," +
            "name" + ":" + getName()+ "," +
            "author" + ":" + getAuthor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservableItem" + "=" + (getReservableItem() != null ? !getReservableItem().equals(this)  ? getReservableItem().toString().replaceAll("  ","    ") : "this" : "null");
  }
}