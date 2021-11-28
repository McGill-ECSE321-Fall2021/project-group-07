/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
<<<<<<< HEAD

@Entity
=======
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
public class Reservation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private Date startDate;
  private Date endDate;

  //Reservation Associations
  private Visitor visitor;
  private InventoryItem inventoryItem;
  private int reservationID;
  //------------------------
  // CONSTRUCTOR
  //------------------------
  public Reservation() {
	  
  }
  
  public Reservation(int aReservationID, Date aStartDate, Date aEndData, Visitor aVisitor, InventoryItem aInventoryItem)
  {
	  this.reservationID = aReservationID;
	  this.startDate = aStartDate;
	  this.endDate = aEndData;
	  this.visitor = aVisitor;
	  this.inventoryItem = aInventoryItem;
    
  }

	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
<<<<<<< HEAD
	public void setEndDate(Date endData) {
		this.endDate = endData;
=======
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
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