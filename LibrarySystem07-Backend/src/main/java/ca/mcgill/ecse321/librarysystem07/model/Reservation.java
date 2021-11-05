/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
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
  private int reservationID;
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(int aReservationID, Date aStartDate, Date aEndData, Visitor aVisitor, InventoryItem aInventoryItem)
  {
	  this.reservationID = aReservationID;
	  this.startDate = aStartDate;
	  this.endData = aEndData;
	  this.visitor = aVisitor;
	  this.inventoryItem = aInventoryItem;
    
  }

	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndData() {
		return endData;
	}
	
	public void setEndData(Date endData) {
		this.endData = endData;
	}
	
	@ManyToOne(optional=false)
	public Visitor getVisitor() {
		return visitor;
	}
	
	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}
	
	@ManyToOne(optional=false)
	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}
	
	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}
	
	@Id
	public int getReservationID() {
		return reservationID;
	}
	
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
 
}