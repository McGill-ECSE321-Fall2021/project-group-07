/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;
import javax.persistence.*;

// line 60 "model.ump"
// line 129 "model.ump"
@Entity
public class InventoryItem
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, InventoryItem> inventoryitemsById = new HashMap<Integer, InventoryItem>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //InventoryItem Attributes
  private int id;

  //InventoryItem Associations
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public InventoryItem(int aId, Library aLibrary)
  {
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id. See http://manual.umple.org?RE003ViolationofUniqueness.html");
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

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    Integer anOldId = getId();
    if (anOldId != null && anOldId.equals(aId)) {
      return true;
    }
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      inventoryitemsById.remove(anOldId);
    }
    inventoryitemsById.put(aId, this);
    return wasSet;
  }

  @Id
  public int getId()
  {
    return id;
  }
  /* Code from template attribute_GetUnique */
  public static InventoryItem getWithId(int aId)
  {
    return inventoryitemsById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_SetOneToMany */
  @ManyToOne(optional=false)
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
    inventoryitemsById.remove(getId());
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
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}
