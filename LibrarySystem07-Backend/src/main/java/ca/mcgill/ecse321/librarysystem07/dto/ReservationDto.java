package ca.mcgill.ecse321.librarysystem07.dto;

import ca.mcgill.ecse321.librarysystem07.model.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Collections;

/**
 * Data transfer object for reservation model class
 * @author ameliebarsoum
 *
 */

public class ReservationDto {
	
	private Date startDate;
	private Date endDate;
	private Visitor visitor;
	private InventoryItem inventoryItem;
	private int reservationID;
	
	public ReservationDto() {
		
	}
	
	@SuppressWarnings("unchecked")
	public ReservationDto(int reservationID) {
		this(Date.valueOf("1971-01-01"), Date.valueOf("1971-01-02"), null, null, reservationID);
	}
	
	public ReservationDto(Date startDate, Date endData, Visitor visitor, InventoryItem inventoryItem, int reservationID) {
		this.startDate=startDate;
		this.endDate=endDate;
		this.visitor=visitor;
		this.inventoryItem=inventoryItem;
		this.reservationID=reservationID;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	public int getReservationID() {
		return reservationID;
	}
}
