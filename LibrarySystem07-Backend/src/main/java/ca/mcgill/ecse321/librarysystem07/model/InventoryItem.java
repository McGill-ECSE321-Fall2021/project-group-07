/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;

// line 66 "model.ump"
// line 139 "model.ump"
public class InventoryItem
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Status { CheckedOut, OnReserve, Available, Damaged }
  public enum TypeOfReservableItem { CD, Movie, Book }
  public enum TypeOfItem { Reservable, NonReservableItem }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, InventoryItem> inventoryitemsByInventoryItemID = new HashMap<Integer, InventoryItem>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //InventoryItem Attributes
  private int inventoryItemID;
  private int duplicates;
  private String name;
  private String author;
  private Status status;
  private TypeOfItem type;

  //InventoryItem Associations
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public InventoryItem(int aInventoryItemID, int aDuplicates, String aName, String aAuthor, Status aStatus, TypeOfItem aType, Library aLibrary)
  {
    duplicates = aDuplicates;
    name = aName;
    author = aAuthor;
    status = aStatus;
    type = aType;
    if (!setInventoryItemID(aInventoryItemID))
    {
      throw new RuntimeException("Cannot create due to duplicate inventoryItemID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create inventoryItem due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setInventoryItemID(int aInventoryItemID)
  {
    boolean wasSet = false;
    Integer anOldInventoryItemID = getInventoryItemID();
    if (anOldInventoryItemID != null && anOldInventoryItemID.equals(aInventoryItemID)) {
      return true;
    }
    if (hasWithInventoryItemID(aInventoryItemID)) {
      return wasSet;
    }
    inventoryItemID = aInventoryItemID;
    wasSet = true;
    if (anOldInventoryItemID != null) {
      inventoryitemsByInventoryItemID.remove(anOldInventoryItemID);
    }
    inventoryitemsByInventoryItemID.put(aInventoryItemID, this);
    return wasSet;
  }

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

  public boolean setType(TypeOfItem aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public int getInventoryItemID()
  {
    return inventoryItemID;
  }
  /* Code from template attribute_GetUnique */
  public static InventoryItem getWithInventoryItemID(int aInventoryItemID)
  {
    return inventoryitemsByInventoryItemID.get(aInventoryItemID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithInventoryItemID(int aInventoryItemID)
  {
    return getWithInventoryItemID(aInventoryItemID) != null;
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

  public TypeOfItem getType()
  {
    return type;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibrary(Library aLibrary)
  {
    boolean wasSet = false;
    if (aLibrary == null)
    {
      return wasSet;
    }

    Library existingLibrary = library;
    library = aLibrary;
    if (existingLibrary != null && !existingLibrary.equals(aLibrary))
    {
      existingLibrary.removeInventoryItem(this);
    }
    library.addInventoryItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    inventoryitemsByInventoryItemID.remove(getInventoryItemID());
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeInventoryItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "inventoryItemID" + ":" + getInventoryItemID()+ "," +
            "duplicates" + ":" + getDuplicates()+ "," +
            "name" + ":" + getName()+ "," +
            "author" + ":" + getAuthor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}