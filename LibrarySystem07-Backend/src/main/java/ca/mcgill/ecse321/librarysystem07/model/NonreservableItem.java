package ca.mcgill.ecse321.librarysystem07.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 81 "model.ump"
// line 141 "model.ump"
public class NonReservableItem extends InventoryItem
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TypeOfNonReservableItem { Magazine, Archive }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //NonreservableItem Attributes
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