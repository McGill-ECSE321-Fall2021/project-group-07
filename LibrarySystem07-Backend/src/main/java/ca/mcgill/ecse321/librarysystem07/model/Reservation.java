/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Date;

// line 83 "model.ump"
// line 146 "model.ump"
public class Reservation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private Date startDate;
  private Date endData;

  //Reservation Associations
  private Visitor visitor;
  private InventoryItem inventoryItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(Date aStartDate, Date aEndData, Visitor aVisitor, InventoryItem aInventoryItem)
  {
    startDate = aStartDate;
    endData = aEndData;
    boolean didAddVisitor = setVisitor(aVisitor);
    if (!didAddVisitor)
    {
      throw new RuntimeException("Unable to create reservation due to visitor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setInventoryItem(aInventoryItem))
    {
      throw new RuntimeException("Unable to create Reservation due to aInventoryItem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndData(Date aEndData)
  {
    boolean wasSet = false;
    endData = aEndData;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndData()
  {
    return endData;
  }
  /* Code from template association_GetOne */
  public Visitor getVisitor()
  {
    return visitor;
  }
  /* Code from template association_GetOne */
  public InventoryItem getInventoryItem()
  {
    return inventoryItem;
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
  /* Code from template association_SetUnidirectionalOne */
  public boolean setInventoryItem(InventoryItem aNewInventoryItem)
  {
    boolean wasSet = false;
    if (aNewInventoryItem != null)
    {
      inventoryItem = aNewInventoryItem;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    Visitor placeholderVisitor = visitor;
    this.visitor = null;
    if(placeholderVisitor != null)
    {
      placeholderVisitor.removeReservation(this);
    }
    inventoryItem = null;
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endData" + "=" + (getEndData() != null ? !getEndData().equals(this)  ? getEndData().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "visitor = "+(getVisitor()!=null?Integer.toHexString(System.identityHashCode(getVisitor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "inventoryItem = "+(getInventoryItem()!=null?Integer.toHexString(System.identityHashCode(getInventoryItem())):"null");
  }
}