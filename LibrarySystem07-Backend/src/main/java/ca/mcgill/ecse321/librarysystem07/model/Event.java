package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "event")
public class Event {

 
	private String name;
	private int eventID;
	private Visitor visitor;

	public Event(String aName, int aEventID, Visitor aVisitor) {
			name = aName;
			eventID = aEventID;
			visitor = aVisitor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	@ManyToOne(optional=false)
	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}
  
}