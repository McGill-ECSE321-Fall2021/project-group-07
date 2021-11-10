package ca.mcgill.ecse321.librarysystem07.dto;

import java.sql.Date;

import ca.mcgill.ecse321.librarysystem07.model.InventoryItem;
import ca.mcgill.ecse321.librarysystem07.model.Visitor;

public class ReservationDto {

	  private Date startDate;
	  private Date endDate;
	  private Visitor visitor;
	  private InventoryItem inventoryItem;
	  private int reservationId;
	  
	  public ReservationDto() {}
	  
	  public ReservationDto(Date startDate, Date endDate, Visitor v, InventoryItem i, int reservationId) {
		  this.startDate = startDate;
		  this.endDate = endDate;
		  this.visitor = v;
		  this.inventoryItem = i;
		  this.reservationId = reservationId;
	  }

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date newEndDate) {
		this.endDate = newEndDate;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	public int getReservationId() {
		return reservationId;
	}
}
