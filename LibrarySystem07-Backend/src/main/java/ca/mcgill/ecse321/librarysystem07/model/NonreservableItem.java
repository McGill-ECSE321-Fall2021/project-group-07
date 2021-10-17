package ca.mcgill.ecse321.librarysystem07.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 81 "model.ump"
// line 141 "model.ump"
public class NonreservableItem extends InventoryItem
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TypeOfNonReservableItem { Magazine, Archive }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //NonreservableItem Attributes
  private typeOfNonReservableItem nonReservableItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NonreservableItem(int aId, Library aLibrary, typeOfNonReservableItem aNonReservableItem)
  {
    super(aId, aLibrary);
    nonReservableItem = aNonReservableItem;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNonReservableItem(typeOfNonReservableItem aNonReservableItem)
  {
    boolean wasSet = false;
    nonReservableItem = aNonReservableItem;
    wasSet = true;
    return wasSet;
  }

  public typeOfNonReservableItem getNonReservableItem()
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