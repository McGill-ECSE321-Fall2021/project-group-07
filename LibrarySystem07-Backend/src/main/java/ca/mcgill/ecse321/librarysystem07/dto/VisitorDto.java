package ca.mcgill.ecse321.librarysystem07.dto;

import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse321.librarysystem07.model.*;

public class VisitorDto {
	
	// ATTRIBUTES
	private float balance;
	private int demeritPoints;
	private String address;
	private	String name;
	private String username;
	private int libraryCardId;
	private List<Event> events;
	private List<Reservation> reservations;
	
	//CONSTRUCTORS
	public VisitorDto() {}
	
	@SuppressWarnings("unchecked")
	public VisitorDto(String aName, String aUsername, String aAddress, int aLibraryCardID) {
		this(aName, aUsername, aAddress, aLibraryCardID, 0, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
	}
	
	@SuppressWarnings("unchecked")
	public VisitorDto(String aName, String aUsername, String aAddress, int aLibraryCardID, int aDemeritPoints) {
		this(aName, aUsername, aAddress, aLibraryCardID, aDemeritPoints, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
	}
	
	public VisitorDto(String aName, String aUsername, String aAddress, 
			int aLibraryCardID, int aDemeritPoints, List<Event> events, List<Reservation> reservations) {
		name = aName;
		username = aUsername;
		address = aAddress;
		libraryCardId = aLibraryCardID;
		setDemeritPoints(aDemeritPoints);
		setBalance(0);
		this.setEvents(events);
		this.setReservations(reservations);
	}
	

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getDemeritPoints() {
		return demeritPoints;
	}

	public void setDemeritPoints(int demeritPoints) {
		this.demeritPoints = demeritPoints;
	}

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public int getLibraryCardId() {
		return libraryCardId;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}	

}
