/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

import javax.persistence.*;

// line 80 "model.ump"
// line 154 "model.ump"
@Entity
@Table(name = "Non Reservable Item")
public class NonReservableItem extends InventoryItem
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TypeOfNonReservableItem { Magazine, Archive, Newspaper }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //NonReservableItem Attributes
  private TypeOfNonReservableItem nonReservableItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NonReservableItem(int aId, Library aLibrary, TypeOfNonReservableItem aNonReservableItem)
  {
    super(aId, aLibrary);
    nonReservableItem = aNonReservableItem;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNonReservableItem(TypeOfNonReservableItem aNonReservableItem)
  {
    boolean wasSet = false;
    nonReservableItem = aNonReservableItem;
    wasSet = true;
    return wasSet;
  }

  public TypeOfNonReservableItem getNonReservableItem()
  {
    return nonReservableItem;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nonReservableItem" + "=" + (getNonReservableItem() != null ? !getNonReservableItem().equals(this)  ? getNonReservableItem().toString().replaceAll("  ","    ") : "this" : "null");
  }
}