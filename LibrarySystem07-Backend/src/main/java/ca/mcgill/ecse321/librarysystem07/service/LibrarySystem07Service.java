package ca.mcgill.ecse321.librarysystem07.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem07.dao.*;
import ca.mcgill.ecse321.librarysystem07.model.*;
import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarianTimeSlot.DayOfTheWeek;
import ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status;
import ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem;

<<<<<<< HEAD

=======
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
@Service
public class LibrarySystem07Service {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	HeadLibrarianRepository headLibrarianRepository;
	@Autowired
	HeadLibrarianTimeSlotRepository headLibrarianTimeSlotRepository;
	@Autowired
	LibrarianRepository librarianRepository;
	@Autowired
	LibrarianTimeSlotRepository librarianTimeSlotRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	VisitorRepository visitorRepository;
	@Autowired
	InventoryItemRepository inventoryItemRepository;


	// EVENT //
<<<<<<< HEAD

=======
	
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	/**
	 * @return Iterable list of events.
	 */
	@Transactional
	public List<Event> getAllEvents() {
		return toList(eventRepository.findAll());
	}

	/**
	 * @param id
	 * @return Event with ID id.
	 */
	@Transactional
	public Event getEvent(int id) {
		if (id <0) {
			throw new IllegalArgumentException("ID is invalid!");
		}
		return eventRepository.findEventByEventID(id);
	}

	/**
	 * 
	 * @param Event name
	 * @param eventID
	 * @param Visitor booking event
	 * @return New event
	 */
	@Transactional
	public Event createEvent(String name, int eventID, Visitor visitor) {
		String error = "";
<<<<<<< HEAD
		if (name.trim().length() == 0 || name == null) {
=======
		if (name == null || name.trim().length() == 0) {
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
			error += "Name is invalid! ";
		}
		if (eventID < 0) {
			error += "ID must be an integer above 0. ";
		}
		if (visitor == null) {
			error += "Visitor is invalid! ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Event e = new Event(name, eventID, visitor);
		eventRepository.save(e);
		return e;
	}

	/**
	 * 
	 * @param visitor
	 * @return all events created by visitor
	 */
	@Transactional
	public List<Event> getEventsOfVisitor(Visitor visitor) {
<<<<<<< HEAD
=======
		String error = "";
		if (visitor == null) {
			error += "Visitor is null.";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		List<Event> events = new ArrayList<Event>();
		for (Event e : getAllEvents()) {
			if (e.getVisitor().equals(visitor)) {
				events.add(e);
			}
		}
		return events;
	}
<<<<<<< HEAD
=======
	
	/**
	 * Gives event a new name.
	 * @param e
	 * @param name
	 */
	@Transactional
	public void updateEventName(Event e, String name) {
		String error = "";
		if (name.trim().length() == 0 || name ==null) {
			error += "Invalid name!";
		}
		if (e == null) {
			error += "Event is null!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		e.setName(name);
	}
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3

	/**
	 * Deletes event from the event repository and sets its attributes to null.
	 * @param eventId
	 */
	@Transactional
	public void deleteEvent(int eventId) {
		Event e = eventRepository.findEventByEventID(eventId);
		eventRepository.delete(e);
		e.setName(null);
		e.setVisitor(null);
		e.setEventID(-1);
	}

	/**
	 * 
	 * @param Event e
	 */
	@Transactional
	public void deleteEvent(Event e) {
<<<<<<< HEAD
=======
		if (e == null) {
			throw new IllegalArgumentException("Event is null!");
		}
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		eventRepository.delete(e);
		e.setName(null);
		e.setVisitor(null);
		e.setEventID(-1);
<<<<<<< HEAD
	}


=======
		e = null;
	}
	
	
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	// INVENTORY ITEM //

	/**
	 * 
	 * @return Iterable list of inventory items.
	 */
	@Transactional
	public List<InventoryItem> getAllInventoryItems() {
		return toList(inventoryItemRepository.findAll()); //ask about to list
	}

	/**
	 * 
	 * @param id
	 * @return Inventory item with ID = id.
	 */
	@Transactional
	public InventoryItem getInventoryItem(int id) {
		return inventoryItemRepository.findInventoryItemByInventoryItemID(id);
	}

	/**
	 * 
	 * @param id
	 * @param duplicates
	 * @param name
	 * @param author
	 * @param status
	 * @param type
	 * @return new inventory item.
	 */
	@Transactional
	public InventoryItem createInventoryItem(int id, int duplicates, String name, 
			String author, Status status, TypeOfItem type) {
		String error = "";
		if (id < 0) {
			error += "ID must be an integer above 0. ";
		}
		if (duplicates < 0) {
			error += "Invalid number of duplicates. ";
		}
		if (name == null || name.trim().length() == 0) {
			error += "Invalid name! ";
		}
		if (author == null || author.trim().length() == 0) {
			error += "Invalid author! ";
		}
		if (status == null) {
			error += "Invalid status! ";
		}
		if (type == null) {
			error += "Invalid type! ";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		InventoryItem item = new InventoryItem(id, duplicates, name, author, status, type);
		//talk abt this
		item.setInventoryItemID(id);
		item.setDuplicates(duplicates);
		item.setName(name);
		item.setAuthor(author);
		item.setStatus(status);
		item.setType(type);
		inventoryItemRepository.save(item);
		return item;
	}
	
	/**
	 * Delete inventory item from the repository and set its attributes to null.
	 * @param id
	 */
	@Transactional
	public InventoryItem deleteInventoryItem(InventoryItem item){
		if (item == null){
				throw new IllegalArgumentException("Inventory Item must not be null.");
			}
			inventoryItemRepository.delete(item);
			item = null;
			return item;
		
	}

	@Transactional
	public InventoryItem updateIventoryItemDuplicate(InventoryItem item, int duplicates){
		if (duplicates < 0){
			throw new IllegalArgumentException("Invalid number of duplicates.");
		}

		item.setDuplicates(duplicates);
		inventoryItemRepository.save(item);
		return item;
	}

	@Transactional
	public InventoryItem updateIventoryItemName(InventoryItem item, String name){
		if (name == null ||  name.trim().length() == 0){
			throw new IllegalArgumentException("Invalid name!");
		}

		item.setName(name);
		inventoryItemRepository.save(item);
		return item;
	}

	@Transactional
	public InventoryItem updateIventoryItemAuthor(InventoryItem item, String author){
		if (author == null ||  author.trim().length() == 0){
			throw new IllegalArgumentException("Invalid author!");
		}

		item.setAuthor(author);
		inventoryItemRepository.save(item);
		return item;
	}

	

	@Transactional
	public InventoryItem updateIventoryItemStatus(InventoryItem item, Status status){
		if (status == null){
			throw new IllegalArgumentException("Invalid status!");
		}

		item.setStatus(status);
		inventoryItemRepository.save(item);
		return item;
	}

	@Transactional
	public InventoryItem updateIventoryItemType(InventoryItem item, TypeOfItem type){
		if (type == null){
			throw new IllegalArgumentException("Invalid type!");
		}
<<<<<<< HEAD
=======
		if (item == null){
			throw new IllegalArgumentException("Invalid item!");
		}
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3

		item.setType(type);
		inventoryItemRepository.save(item);
		return item;
	}

<<<<<<< HEAD


	// RESERVATION //

=======
	
	// RESERVATION //
	
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	/**
	 * 
	 * @return Iterable list of reservations.
	 */
	@Transactional
	public List<Reservation> getAllReservations() {
		return toList(reservationRepository.findAll());
	}

	/**
	 * 
	 * @param item
	 * @param visitor
	 * @return reservation for Visitor visitor with InventoryItem item
	 */
	@Transactional
	public Reservation getReservation(InventoryItem item, Visitor visitor) {
		if (item == null) {
			throw new IllegalArgumentException("Invalid item!");
		}
		if (visitor == null) {
			throw new IllegalArgumentException("Invalid!");
		}
		return reservationRepository.findByInventoryItemAndVisitor(item, visitor);
	}

	/**
	 * 
	 * @param visitor
	 * @return all reservations for visitor
	 */
	@Transactional
	public List<Reservation> getReservationsForVisitor(Visitor visitor) {
		List<Reservation> reservations = new ArrayList<Reservation>();
		for (Reservation r : getAllReservations()) {
			if (r.getVisitor().equals(visitor)) {
				reservations.add(r);
			}
		}
		return reservations;
	}

	/**
	 * 
	 * @param id
	 * @param startDate
	 * @param endDate
	 * @param visitor
	 * @param inventoryItem
	 * @return new reservation for Visitor
	 */
	@Transactional
	public Reservation createReservation(int id, Date startDate, Date endDate, 
			Visitor visitor, InventoryItem inventoryItem) {

		String error = "";
		if (id < 0) {
			error += "ID is invalid!";
		}
		if (startDate == null) {
			error += "Start time is invalid!";
		}
		if (endDate == null) {
			error += "End time is invalid!";
		}
		if (endDate != null && startDate != null && endDate.before(startDate)) {
			error = error + "End date cannot be before start date!";
		}
		if (visitor == null) {
			error += "Visitor is invalid!";
		}
		if (inventoryItem == null) {

			error+= "Inventory item is invalid!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Reservation r = new Reservation(id, startDate, endDate, visitor, inventoryItem);
		reservationRepository.save(r);
		return r;
	}

	/**
	 * Delete reservation from repository and set the object's attributes to null.
	 * @param reservationId
	 */
	@Transactional
	public void deleteReservation(int reservationId) {
		Reservation r = reservationRepository.findReservationByReservationID(reservationId);
		reservationRepository.delete(r);
		r.setEndDate(null);
		r.setStartDate(null);
		r.setInventoryItem(null);
		r.setReservationID(-1);
		r.setVisitor(null);
		r = null;
	}

	@Transactional
	public void deleteReservation(Reservation r) {
		reservationRepository.delete(r);
		r.setEndDate(null);
		r.setStartDate(null);
		r.setInventoryItem(null);
		r.setReservationID(-1);
		r.setVisitor(null);
		r = null;
	}

	@Transactional
	public void deleteReservation(Visitor v, InventoryItem i) {
		Reservation r = reservationRepository.findByInventoryItemAndVisitor(i, v);
		reservationRepository.delete(r);
		r.setEndDate(null);
		r.setStartDate(null);
		r.setInventoryItem(null);
		r.setReservationID(-1);
		r.setVisitor(null);
		r = null;
	}

	@Transactional
	public void deleteAllReservations(Visitor v) {
		List<Reservation> reservation = reservationRepository.findReservationsByVisitor(v);
		for (Reservation r : reservation) {
			reservationRepository.delete(r);
			r.setEndDate(null);
			r.setStartDate(null);
			r.setInventoryItem(null);
			r.setReservationID(-1);
			r.setVisitor(null);
			r = null;
		}
	}
	
	@Transactional
	public void deleteAllReservations(InventoryItem i) {
		List<Reservation> reservation = reservationRepository.findReservationByInventoryItem(i);
		for (Reservation r : reservation) {
			reservationRepository.delete(r);
			r.setEndDate(null);
			r.setStartDate(null);
			r.setInventoryItem(null);
			r.setReservationID(-1);
			r.setVisitor(null);
			r = null;
		}
	}
	
<<<<<<< HEAD

	// VISITOR //

=======
	
	// VISITOR //
	
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	/**
	 * 
	 * @return Iterable list of visitors
	 */
	@Transactional
	public List<Visitor> getAllVisitors() {
		return toList(visitorRepository.findAll());
	}

	/**
	 * 
	 * @param id
	 * @return Visitor with Library Card Id id.
	 */
	@Transactional
	public Visitor getVisitor(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("ID is invalid!");
		}
		return visitorRepository.findVisitorByLibraryCardID(id);
	}

	/**
	 * 
	 * @param name
	 * @param username
	 * @param address
	 * @param libraryCardID
	 * @param demeritPoints
	 * @return new visitor
	 */
	public Visitor createVisitor(String name, String username, String address, int libraryCardID, int demeritPoints) {
		String error = "";

		if (name == null || name.trim().length() == 0) {
			error += "Name is invalid!";
		}
		if (username == null || username.trim().length() == 0) {
			error += "Username is invalid!";
		}
		if (address == null || address.trim().length() == 0) {
			error += "Address is invalid";
		}
		if (libraryCardID < 0) {
			error += "Library card ID invalid";
		}
		if (demeritPoints < 0) {
			error += "Demerit points must be 0 or greater!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Visitor v = new Visitor(name, username, address, libraryCardID, demeritPoints);
		visitorRepository.save(v);
		return v;

	}

	/**
<<<<<<< HEAD
=======
	 * Updates visitor address in case of move.
	 * @param v
	 * @param address
	 */
	@Transactional
	public void updateVisitorAddress(Visitor v, String address) {
		String error = "";
		if (v == null) {
			error += "Visitor is null.";
		}
		if (address.trim().length() == 0 || address == null) {
			error += "Address is invalid.";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		v.setAddress(address);
	}
	
	/**
	 * Update visitor's demerit points.
	 * @param v
	 * @param points
	 */
	@Transactional
	public void updateVisitorDemeritPoints(Visitor v, int points) {
		String error = "";
		if (v == null) {
			error += "Visitor is null.";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		v.setDemeritPoints(points);
	}
	
	/**
	 * Update visitor balance.
	 * @param v
	 * @param balance
	 */
	@Transactional
	public void updateVisitorBalance(Visitor v, float balance) {
		String error = "";
		if (v == null) {
			error += "Visitor is null.";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		v.setBalance(balance);
	}
	
	@Transactional
	public void addToVisitorBalance(Visitor v, float amount) {
		String error = "";
		if (v == null) {
			error += "Visitor is null.";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		v.setBalance(v.getBalance() + amount);
	}
	
	
	/**
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	 * Delete visitor from repository and set visitor to null.
	 * @param id
	 */
	@Transactional
	public void deleteVisitor(int id) {
<<<<<<< HEAD
=======
		String error = "";
		if (id < 0) {
			error += "Visitor library card Id must be a positive number!";
		}
		else if (visitorRepository.findVisitorByLibraryCardID(id) == null) {
			error += ("Cannot delete visitor that is not in system!");
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		Visitor v = visitorRepository.findVisitorByLibraryCardID(id);
		visitorRepository.delete(v);
		v.setAddress(null);
		v.setBalance(-1);
		v.setDemeritPoints(-1);
		v.setLibraryCardID(-1);
		v.setName(null);
		v.setUsername(null);
		v = null;
	}
	
<<<<<<< HEAD
	//TODO
	//Add checks to ensure visitor exists in repo, same for all delete methods
	
	@Transactional
	public void deleteVisitor(Visitor v) {
=======
	/**
	 * Deletes a visitor if they exist in the repository.
	 * @param v
	 */
	@Transactional
	public void deleteVisitor(Visitor v) {
		if (v == null) {
			throw new IllegalArgumentException("Cannot delete null visitor!");
		}
		if (visitorRepository.findVisitorByLibraryCardID(v.getLibraryCardID()) == null) {
			throw new IllegalArgumentException("Cannot delete visitor that is not in system!");
		}
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		visitorRepository.delete(v);
		v.setAddress(null);
		v.setBalance(-1);
		v.setDemeritPoints(-1);
		v.setLibraryCardID(-1);
		v.setName(null);
		v.setUsername(null);
		v = null;
	}
<<<<<<< HEAD

	
	// HEAD LIBRARIAN //

	/**
	 * 
	 * @param id
	 * @return head librarian with libraryCardId id.
	 */
	@Transactional
	public HeadLibrarian getHeadLibrarian(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException("ID is invalid!");
		}
		HeadLibrarian l = headLibrarianRepository.findHeadLibrarianByLibraryCardID(id);
		return l;
	}

	/**
	 * @return list of all head librarians.
=======
	
	
	// HEAD LIBRARIAN //


	/**
	 * @return list of all head librarians. Should be list of 1 head librarian.
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	 */
	@Transactional
	public List<HeadLibrarian> getAllHeadLibrarians() {
		return toList(headLibrarianRepository.findAll());
	}

	/**
<<<<<<< HEAD
	 * 
	 * @param name
	 * @param username
	 * @param address
	 * @param id
	 * @return new head librarian/
	 */
	@Transactional
	public HeadLibrarian createHeadLibrarian(String name, String username, String address, Integer id) {
		String error = "";
		if (id == null || id < 0) {
			error += "Librarian ID is invalid!   ";
		}
		if (name == null || name.trim().length() == 0) {
			error += "Librarian name is invalid!   ";
		}
		if (username == null || username.trim().length() == 0) {
			error += "Username is invalid!   ";
		}
		if (address == null || address.trim().length() == 0) {
			error += "Address is invalid!   ";
		}
=======
	 * Update address in case of move.
	 * @param headLibrarian
	 * @param address
	 */
	@Transactional
	public void updateHeadLibrarianAddress(HeadLibrarian headLibrarian, String address) {
		
		String error = "";
		
		if (headLibrarian == null) {
		 	error += "Head Librarian is null! ";
		}
		
		if (address == null || address.trim().length() == 0) {
			error += "Address is invalid! ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		headLibrarian.setAddress(address);
	}
	
	@Transactional
	public HeadLibrarian createHeadLibrarian(String name, String username, String address, Integer id) {
		
		String error = "";
		
		if (id == null || id < 0) {
			error += "Head Librarian ID is invalid! ";
		}
		if (name == null || name.trim().length() == 0) {
			error += "Head Librarian name is invalid! ";
		}
		if (username == null || username.trim().length() == 0) {
			error += "Username is invalid! ";
		}
		if (address == null || address.trim().length() == 0) {
			error += "Address is invalid! ";
		}
		
		if (headLibrarianRepository.count() >= 1) {
			error += "A Head Librarian already exists! ";
		}
		
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		HeadLibrarian librarian = new HeadLibrarian(name, username, address, id);
		headLibrarianRepository.save(librarian);
		return librarian;
	}
<<<<<<< HEAD

	/**
	 * Delete head librarian from repository and set the head librarian to null.
	 * @param id
	 */
	@Transactional
	public void deleteHeadLibrarian(int id) {
		HeadLibrarian hl = headLibrarianRepository.findHeadLibrarianByLibraryCardID(id);
		headLibrarianRepository.delete(hl);
		hl.setAddress(null);
		hl.setLibraryCardID(-1);
		hl.setName(null);
		hl.setUsername(null);
		hl = null;
	}
	
	@Transactional
	public void deleteHeadLibrarian(HeadLibrarian hl) {
		headLibrarianRepository.delete(hl);
		hl.setAddress(null);
		hl.setLibraryCardID(-1);
		hl.setName(null);
		hl.setUsername(null);
		hl = null;
	}
	
	
	// HEAD LIBRARIAN TIMESLOT //

	/**
	 * @return all head librarian schedule time slots
	 */
=======
	
	@Transactional
	public void deleteHeadLibrarian() {
		
		String error = "";
		
		if (headLibrarianRepository.count() == 0) {
			error += "Library has no Head Librarian!";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		headLibrarianRepository.deleteAll();
	}


	// HEAD LIBRARIAN TIMESLOT //

>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	@Transactional
	public List<HeadLibrarianTimeSlot> getAllHeadLibrarianTimeSlots() {
		return toList(headLibrarianTimeSlotRepository.findAll());
	}

<<<<<<< HEAD

	/**
	 * 
	 * @param id
	 * @return head librarian schedule slot with Id id.
	 */
=======
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	@Transactional
	public HeadLibrarianTimeSlot getHeadLibrarianTimeSlot(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException("Time slot id is invalid!");
		}
<<<<<<< HEAD
		return headLibrarianTimeSlotRepository.findHeadLibrarianTimeSlotByTimeSlotID(id);
	}

	/**
	 * 
	 * @param headLibrarian
	 * @param startTime
	 * @param endTime
	 * @param dayOfTheWeek
	 * @return new head librarian schedule time slot
	 */
	@Transactional
	public HeadLibrarianTimeSlot createHeadLibrarianTimeSlot(HeadLibrarian headLibrarian, Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek) {
		String error = "";

		if (headLibrarian == null) {
			error += "Head librarian is invalid!";
		}
		if (startTime == null) {
			error += "Timeslot start time is invalid!";
		}
		if (endTime == null) {
			error += "Timeslot end time is invalid!";
		}
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			error = error + "Timeslot end time cannot be before event start time!";
		}
		if (dayOfTheWeek == null) {
			error += "Invalid day of week!";
		}

=======
		return headLibrarianTimeSlotRepository.findHeadLibrarianTimeSlotByHeadLibrarianTimeSlotId(id);
	}

	@Transactional
	public HeadLibrarianTimeSlot createHeadLibrarianTimeSlot(Integer headLibrarianTimeSlotId, HeadLibrarian headLibrarian, Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek) {
		
		String error = "";

		if (headLibrarianTimeSlotId == null || headLibrarianTimeSlotId < 0) {
			error += "Time slot id is invalid! ";
		}
		
		if (headLibrarian == null) {
			error += "Head librarian is invalid! ";
		} else if (!headLibrarianRepository.existsById(headLibrarian.getLibraryCardID())) {
			error += "Head Librarian does not exist in the system! ";
		}
		
		if (startTime == null) {
			error += "Timeslot start time is invalid! ";
		}
		if (endTime == null) {
			error += "Timeslot end time is invalid! ";
		}
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			error = error + "Timeslot end time cannot be before event start time! ";
		}
		if (dayOfTheWeek == null) {
			error += "Invalid day of week! ";
		}
		
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
<<<<<<< HEAD

		Random rand = new Random();
		int headLibrarianTimeSlotId = rand.nextInt();

=======
		
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		HeadLibrarianTimeSlot hlts = new HeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, startTime, endTime, dayOfTheWeek);
		headLibrarianTimeSlotRepository.save(hlts);
		return hlts;
	}
<<<<<<< HEAD

	/**
	 * Given a time slot to delete, delete any headLibrarianTimeSlots that overlap entirely with the time slot to delete,
	 * and shorten any headLibrarianTimeSlots that partially overlap with the time slot to delete so no headLibrarianTimeSlots
	 * have any overlap with the given time slot.
	 * 
	 * @param startTime
	 * @param endTime
	 * @param day
	 */
	@Transactional
	public void deleteHeadLibrarianTimeSlot(Time startTime, Time endTime, HeadLibrarianTimeSlot.DayOfTheWeek day, HeadLibrarian headLibrarian) {
		for (HeadLibrarianTimeSlot timeSlot : headLibrarianTimeSlotRepository.findHeadLibrarianTimeSlotByHeadLibrarian(headLibrarian)) {
			if (timeSlot.getDayOfTheWeek().equals(day)) {
				
				//if times have total overlap with a timeSlot, delete timeSlot
				if ((timeSlot.getStartTime().equals(startTime) || timeSlot.getStartTime().after(startTime)) 
						&& timeSlot.getEndTime().equals(endTime) || timeSlot.getEndTime().before(endTime)) {
					deleteHeadLibrarianTimeSlot(timeSlot);
				}
				
				//if the time period to delete starts before the timeSlot, and ends in the middle of a timeSlot, 
				//set the timeSlot startTime to the endTime of the time period
				else if ((timeSlot.getStartTime().after(startTime) || timeSlot.getStartTime().equals(startTime)) && 
						timeSlot.getEndTime().after(endTime)) {
					timeSlot.setStartTime(endTime);
				}
				
				//if the time period to delete starts in the middle of a timeSlot, and ends after the timeSlot,
				//set the timeSlot endTime to the startTime of the time period
				else if (timeSlot.getStartTime().before(startTime) && 
						(timeSlot.getEndTime().before(endTime) || timeSlot.getEndTime().equals(endTime))) {
					timeSlot.setEndTime(startTime);
				}
			}
		}
	}
	
	@Transactional
	public void deleteHeadLibrarianTimeSlot(HeadLibrarianTimeSlot timeSlot) {
		headLibrarianTimeSlotRepository.delete(timeSlot);
		timeSlot.setDayOfTheWeek(null);
		timeSlot.setEndTime(null);
		timeSlot.setHeadLibrarian(null);
		timeSlot.setHeadLibrarianTimeSlotId(-1);
		timeSlot.setStartTime(null);
		timeSlot = null;
	}

	// LIBRARIAN //

	/**
	 * @return iterable list of all librarians
	 */

	@Transactional
	public List<Librarian> getAllLibrarians() {
		return toList(librarianRepository.findAll());
	}

	/**
	 * 
	 * @param id
	 * @return librarian with LibraryCardId id
	 */
	@Transactional
	public Librarian getLibrarian(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException("Librarian id is invalid!");
		}
		Librarian l = librarianRepository.findLibrarianByLibraryCardID(id);
		return l;
	}

	/**
	 * 
	 * @param name
	 * @param username
	 * @param address
	 * @param id
	 * @return new librarian
	 */
	@Transactional
	public Librarian createLibrarian(String name, String username, String address, Integer id) {
		String error = "";
		if (id == null || id < 0) {
			error += "Librarian ID is invalid!   ";
		}
		if (name.equals(null) || name.trim().length() == 0) {
			error += "Librarian name is invalid!   ";
		}
		if (username.equals(null) || username.trim().length() == 0) {
			error += "Username is invalid!   ";
		}
		if (address.equals(null) || address.trim().length() == 0) {
			error += "Address is invalid!   ";
		}
=======
	
	@Transactional
	public void deleteHeadLibrarianTimeSlot(Integer headLibrarianTimeSlotId) {
		
		String error = "";
		
		if (headLibrarianTimeSlotId == null || headLibrarianTimeSlotId < 0) {
			error += "Head Librarian Time Slot ID is invalid! ";
		} else if (headLibrarianTimeSlotRepository.findHeadLibrarianTimeSlotByHeadLibrarianTimeSlotId(headLibrarianTimeSlotId) == null) {
			error += "No such Head Librarian Time Slot! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		headLibrarianTimeSlotRepository.deleteById(headLibrarianTimeSlotId);
	}
	
	@Transactional
	public void deleteAllHeadLibrarianTimeSlots() {
		
		String error = "";
		
		if (headLibrarianTimeSlotRepository.count() == 0) {
			error += "No Head Librarian Time Slots exist!";
		}
		
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
<<<<<<< HEAD

		Librarian librarian = new Librarian(name, username, address, id);
		librarianRepository.save(librarian);
		return librarian;
	}
	
	/**
	 * Delete head librarian from repository and set the head librarian to null.
	 * @param id
	 */
	@Transactional
	public void deleteLibrarian(int id) {
		Librarian hl = librarianRepository.findLibrarianByLibraryCardID(id);
		librarianRepository.delete(hl);
		hl.setAddress(null);
		hl.setLibraryCardID(-1);
		hl.setName(null);
		hl.setUsername(null);
		hl = null;
	}
	
	@Transactional
	public void deleteLibrarian(Librarian hl) {
		librarianRepository.delete(hl);
		hl.setAddress(null);
		hl.setLibraryCardID(-1);
		hl.setName(null);
		hl.setUsername(null);
		hl = null;
	}
	
	

	// LIBRARIAN TIMESLOT //

	/**
	 * 
	 * @return iterable list of librarian schedule time slots
	 */
	@Transactional
	public List<LibrarianTimeSlot> getAllLibrarianTimeSlots() {
		return toList(librarianTimeSlotRepository.findAll());
=======
		
		headLibrarianTimeSlotRepository.deleteAll();
	}
	
		/**
	 * Update end time of librarian time slot
	 * @param lts
	 * @param endTime
	 */
	
	@Transactional
	public void updateHeadLibrarianTimeSlotEndTime(HeadLibrarianTimeSlot hlts, Time endTime) {
		
		String error = "";
		
		if (hlts == null) {
			error += "Head Librarian time slot is null.";
			throw new IllegalArgumentException(error);
		}
				
		if (endTime == null || hlts.getEndTime().equals(endTime)) {
			error += "Nothing to update!";
			throw new IllegalArgumentException(error);
		}
		
		if (hlts.getStartTime().after(endTime)) {
			error+="End time cannot be before start time.";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		hlts.setEndTime(endTime);
	}
	
	/**
	 * Update start time of librarian time slot
	 * @param lts
	 * @param endTime
	 */
	
	@Transactional
	public void updateHeadLibrarianTimeSlotStartTime(HeadLibrarianTimeSlot hlts, Time startTime) {
		
		String error = "";
		
		if (hlts == null) {
			error += "Head Librarian time slot is null.";
			throw new IllegalArgumentException(error);
		}
		
		if (startTime == null || hlts.getStartTime().equals(startTime)) {
			error += "Nothing to update!";
			throw new IllegalArgumentException(error);
		}
		
		if (hlts.getEndTime().before(startTime)) {
			error+="Start time cannot be after end time. ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		hlts.setStartTime(startTime);
	}
	
	/**
	 * Update dayofweek of librarian time slot
	 * @param lts
	 * @param day
	 */
	
	@Transactional
	public void updateHeadLibrarianTimeSlotDayOfWeek(HeadLibrarianTimeSlot hlts, ca.mcgill.ecse321.librarysystem07.model.HeadLibrarianTimeSlot.DayOfTheWeek day) {
		String error = "";
		
		if (hlts == null) {
			error += "Head Librarian time slot is null.";
			throw new IllegalArgumentException(error);
		}
		
		if (day == null || hlts.getDayOfTheWeek().equals(day)) {
			error += "Nothing to update! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		hlts.setDayOfTheWeek(day);
	}


	// LIBRARIAN //
	
	/**
	 * @return iterable list of all librarians
	 */
	@Transactional
	public List<Librarian> getAllLibrarians() {
		return toList(librarianRepository.findAll());
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	}

	/**
	 * 
	 * @param id
<<<<<<< HEAD
	 * @return librarian schedule time slot with TimeSlotId id
	 */
	@Transactional
	public LibrarianTimeSlot getLibrarianTimeSlot(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException("Time slot id is invalid!");
		}
		return librarianTimeSlotRepository.findLibrarianTimeSlotByTimeSlotID(id);
	}

	/**
	 * 
	 * @param librarian
	 * @param startTime
	 * @param endTime
	 * @param dayOfTheWeek
	 * @return new librarian time slot
	 */
	@Transactional
	public LibrarianTimeSlot createLibrarianTimeSlot(Librarian librarian, Time startTime, Time endTime, LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek) {
		String error = "";

		if (librarian == null) {
			error += "Head librarian is invalid!";
		}
		if (startTime == null) {
			error += "Timeslot start time is invalid!";
		}
		if (endTime == null) {
			error += "Timeslot end time is invalid!";
		}
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			error = error + "Timeslot end time cannot be before event start time!";
		}
		if (dayOfTheWeek == null) {
			error += "Invalid day of week!";
		}

=======
	 * @return librarian with LibraryCardId id
	 */
	@Transactional
	public Librarian getLibrarian(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException("Librarian id is invalid!");
		}
		Librarian l = librarianRepository.findLibrarianByLibraryCardID(id);
		return l;
	}
	
	/**
	 * Update address in case of move.
	 * @param librarian
	 * @param address
	 */
	@Transactional
	public void updateLibrarianAddress(Librarian librarian, String address) {
		String error = "";
		if (librarian == null) {
		 	error += "Librarian is null!";
		}
		if (address == null || address.trim().length() == 0) {
			error += "Address is invalid!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		librarian.setAddress(address);
	}
	

	/**
	 * 
	 * @param name
	 * @param username
	 * @param address
	 * @param id
	 * @return new librarian
	 */
	@Transactional
	public Librarian createLibrarian(String name, String username, String address, Integer id) {
		
		String error = "";
		
		if (id == null || id < 0) {
			error += "Librarian ID is invalid! ";
		}
		if (name == null || name.trim().length() == 0) {
			error += "Librarian name is invalid! ";
		}
		if (username == null || username.trim().length() == 0) {
			error += "Username is invalid! ";
		}
		if (address == null || address.trim().length() == 0) {
			error += "Address is invalid! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Librarian librarian = new Librarian(name, username, address, id);
		librarianRepository.save(librarian);
		return librarian;
	}
	
	/**
	 * Delete librarian from repository and set the librarian to null.
	 * @param id
	 */
	@Transactional
	public void deleteLibrarian(Integer id) {
		
		String error = "";
		
		if (id == null || id < 0) {
			error += "Librarian ID is invalid! ";
		} else if (librarianRepository.findLibrarianByLibraryCardID(id) == null) {
			error += "No such Librarian! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		Librarian librarian = librarianRepository.findLibrarianByLibraryCardID(id);
		librarianRepository.delete(librarian);
	}
	
	@Transactional
	public void deleteAllLibrarians() {
		
		String error = "";
		
		if (librarianRepository.count() == 0) {
			error += "Library has no Librarians!";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		librarianRepository.deleteAll();
	}

	/* LIBRARIAN TIMESLOT */

	@Transactional
	public List<LibrarianTimeSlot> getAllLibrarianTimeSlots() {
		return toList(librarianTimeSlotRepository.findAll());
	}

	@Transactional
	public LibrarianTimeSlot getLibrarianTimeSlot(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException("Time slot id is invalid!");
		}
		return librarianTimeSlotRepository.findLibrarianTimeSlotByLibrarianTimeSlotId(id);
	}

	@Transactional
	public LibrarianTimeSlot createLibrarianTimeSlot(Integer librarianTimeSlotId, Librarian librarian, Time startTime, Time endTime, LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek) {
		
		String error = "";
		
		if (librarianTimeSlotId == null || librarianTimeSlotId < 0) { 
			error += "Time slot id is invalid! ";
		}

		if (librarian == null) {
			error += "Librarian is invalid! ";
		} else if (!librarianRepository.existsById(librarian.getLibraryCardID())) {
			error += "Librarian does not exist in the system! ";
		}
		
		if (startTime == null) {
			error += "Timeslot start time is invalid! ";
		}
		if (endTime == null) {
			error += "Timeslot end time is invalid! ";
		}
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			error = error + "Timeslot end time cannot be before event start time! ";
		}
		if (dayOfTheWeek == null) {
			error += "Invalid day of week! ";
		}
		
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

<<<<<<< HEAD
		Random rand = new Random();
		int librarianTimeSlotId = rand.nextInt();

=======
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		LibrarianTimeSlot hlts = new LibrarianTimeSlot(librarianTimeSlotId, librarian, startTime, endTime, dayOfTheWeek);
		librarianTimeSlotRepository.save(hlts);
		return hlts;
	}
<<<<<<< HEAD


	/**
	 *
	 * @param librarian
	 * @return list of all librarian's schedule slots
	 */
=======
	

>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	@Transactional
	public List<LibrarianTimeSlot> getLibrarianTimeSlotByLibrarian(Librarian librarian) {
		List<LibrarianTimeSlot> librarianSchedule = new ArrayList<>();
		for (LibrarianTimeSlot lts : librarianTimeSlotRepository.findLibrarianTimeSlotByLibrarian(librarian)) {
			librarianSchedule.add(lts);
		}
		return librarianSchedule;
	}
	
<<<<<<< HEAD
	/**
	 * Given a time slot to delete, delete any LibrarianTimeSlots that overlap entirely with the time slot to delete,
	 * and shorten any LibrarianTimeSlots that partially overlap with the time slot to delete so no LibrarianTimeSlots
	 * have any overlap with the given time slot.
	 * 
	 * @param startTime
	 * @param endTime
	 * @param day
	 */
	@Transactional
	public void deleteLibrarianTimeSlot(Time startTime, Time endTime, LibrarianTimeSlot.DayOfTheWeek day, Librarian librarian) {
		for (LibrarianTimeSlot timeSlot : librarianTimeSlotRepository.findLibrarianTimeSlotByLibrarian(librarian)) {
			if (timeSlot.getDayOfTheWeek().equals(day)) {
				
				//if times have total overlap with a timeSlot, delete timeSlot
				if ((timeSlot.getStartTime().equals(startTime) || timeSlot.getStartTime().after(startTime)) 
						&& timeSlot.getEndTime().equals(endTime) || timeSlot.getEndTime().before(endTime)) {
					deleteLibrarianTimeSlot(timeSlot);
				}
				
				//if the time period to delete starts before the timeSlot, and ends in the middle of a timeSlot, 
				//set the timeSlot startTime to the endTime of the time period
				else if ((timeSlot.getStartTime().after(startTime) || timeSlot.getStartTime().equals(startTime)) && 
						timeSlot.getEndTime().after(endTime)) {
					timeSlot.setStartTime(endTime);
				}
				
				//if the time period to delete starts in the middle of a timeSlot, and ends after the timeSlot,
				//set the timeSlot endTime to the startTime of the time period
				else if (timeSlot.getStartTime().before(startTime) && 
						(timeSlot.getEndTime().before(endTime) || timeSlot.getEndTime().equals(endTime))) {
					timeSlot.setEndTime(startTime);
				}
			}
		}
	}
	
	@Transactional
	public void deleteLibrarianTimeSlot(LibrarianTimeSlot timeSlot) {
		librarianTimeSlotRepository.delete(timeSlot);
		timeSlot.setDayOfTheWeek(null);
		timeSlot.setEndTime(null);
		timeSlot.setLibrarian(null);
		timeSlot.setLibrarianTimeSlotId(-1);
		timeSlot.setStartTime(null);
		timeSlot = null;
	}

	
	
	//HELPER//

	/**
	 * 
	 * @param <T>
	 * @param iterable
	 * @return iterable list
	 */
=======
	@Transactional
	public void deleteLibrarianTimeSlot(Integer librarianTimeSlotId) {
		
		String error = "";
		
		if (librarianTimeSlotId == null || librarianTimeSlotId < 0) {
			error += "Librarian Time Slot ID is invalid! ";
		} else if (librarianTimeSlotRepository.findLibrarianTimeSlotByLibrarianTimeSlotId(librarianTimeSlotId) == null) {
			error += "No such Librarian Time Slot! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		LibrarianTimeSlot librarianTimeSlot = librarianTimeSlotRepository.findLibrarianTimeSlotByLibrarianTimeSlotId(librarianTimeSlotId);
		librarianTimeSlotRepository.delete(librarianTimeSlot);
	}
	
	@Transactional
	public void deleteAllLibrarianTimeSlots() {
		
		String error = "";
		
		if (librarianTimeSlotRepository.count() == 0) {
			error += "No Librarian Time Slots exist! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		librarianTimeSlotRepository.deleteAll();
	}
	
	@Transactional
	public void deleteLibrarianSchedule(Librarian librarian) {
		
		String error = "";
				
		if (librarian == null) {
			error += "No Librarian exist! ";
		} else if (librarianTimeSlotRepository.findLibrarianTimeSlotByLibrarian(librarian).size() == 0) {
			error += "Librarian has no time slots! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		for (LibrarianTimeSlot lts : librarianTimeSlotRepository.findLibrarianTimeSlotByLibrarian(librarian)) {
			librarianTimeSlotRepository.delete(lts);
		}
	}
	
	/**
	 * Update end time of librarian time slot
	 * @param lts
	 * @param endTime
	 */
	
	@Transactional
	public void updateLibrarianTimeSlotEndTime(LibrarianTimeSlot lts, Time endTime) {
		
		String error = "";
		
		if (lts == null) {
			error += "Librarian time slot is null.";
			throw new IllegalArgumentException(error);
		}
				
		if (endTime == null || lts.getEndTime().equals(endTime)) {
			error += "Nothing to update!";
			throw new IllegalArgumentException(error);
		}
		
		if (lts.getStartTime().after(endTime)) {
			error+="End time cannot be before start time.";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		lts.setEndTime(endTime);
	}
	
	/**
	 * Update start time of librarian time slot
	 * @param lts
	 * @param endTime
	 */
	
	@Transactional
	public void updateLibrarianTimeSlotStartTime(LibrarianTimeSlot lts, Time startTime) {
		
		String error = "";
		
		if (lts == null) {
			error += "Librarian time slot is null.";
			throw new IllegalArgumentException(error);
		}
		
		if (startTime == null || lts.getStartTime().equals(startTime)) {
			error += "Nothing to update!";
			throw new IllegalArgumentException(error);
		}
		
		if (lts.getEndTime().before(startTime)) {
			error+="Start time cannot be after end time. ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		lts.setStartTime(startTime);
	}
	
	/**
	 * Update dayofweek of librarian time slot
	 * @param lts
	 * @param day
	 */
	
	@Transactional
	public void updateLibrarianTimeSlotDayOfWeek(LibrarianTimeSlot lts, ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek day) {
		String error = "";
		
		if (lts == null) {
			error += "Librarian time slot is null.";
			throw new IllegalArgumentException(error);
		}
		
		if (day == null || lts.getDayOfTheWeek().equals(day)) {
			error += "Nothing to update! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		lts.setDayOfTheWeek(day);
	}
	
	/**
	 * Update librarian of librarian time slot
	 * @param lts
	 * @param librarian
	 */
	@Transactional
	public void updateLibrarianTimeSlotLibrarian(LibrarianTimeSlot lts, Librarian librarian) {
		
		String error = "";
		
		if (lts == null) {
			error += "Librarian time slot is null.";
			throw new IllegalArgumentException(error);
		}
		
		if (librarian == null) {
			error += "Nothing to update! ";
		} else if (!librarianRepository.existsById(librarian.getLibraryCardID())) {
			error += "Librarian does not exist in the system! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		lts.setLibrarian(librarian);
		
	}
	
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
<<<<<<< HEAD
}
=======
	
	/*
	 * Extra methods for reservation tests
	 */
	
	public Reservation getReservation(Integer reservationId) {
		
		if (reservationId == null || reservationId < 0) {
			throw new IllegalArgumentException("Reservation id is invalid!");
		}
		return reservationRepository.findReservationByReservationID(reservationId);
	}
	
	@Transactional
	public void deleteReservationById(Integer id) {
		
		String error = "";
		
		if (id == null || id < 0) {
			error += "Reservation ID is invalid! ";
		}
		
		if (reservationRepository.findReservationByReservationID(id) == null) {
			error += "No such Reservation! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		reservationRepository.deleteById(id);
	}

	@Transactional
	public void deleteAllReservations() {
		String error = "";
		
		if (reservationRepository.count() == 0) {
			error += "Library has no Reservations!";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		reservationRepository.deleteAll();

		
	}
	
	/**
	 * RESERVATION TIMESLOT UPDATES
	 */
	
	/**
	 * Update start date of reservation
	 * @param r
	 * @param startDate
	 */
	
	@Transactional
	public void updateReservationStartDate(Reservation r, Date startDate) {
		String error = "";
		if (r == null) {
			error += "Reservation is null.";
		}
		if (r.getEndDate().before(startDate)) {
			error+="Start date cannot be after end date.";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		r.setStartDate(startDate);
	}
	
	/**
	 * Update end date of reservation
	 * @param r
	 * @param endDate
	 */
	
	@Transactional
	public void updateReservationEndDate(Reservation r, Date endDate) {
		String error = "";
		if (r == null) {
			error += "Reservation is null.";
		}
		if (r.getStartDate().after(endDate)) {
			error+="Start date cannot be after end date.";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		r.setEndDate(endDate);
	}

	

	/**
	 * Update librarian of librarian time slot
	 * @param lts
	 * @param librarian
	 */
	@Transactional
	public void updateHeadLibrarianTimeSlotLibrarian(HeadLibrarianTimeSlot hlts, HeadLibrarian headLibrarian) {
		String error = "";
		if (hlts == null) {
			error += "Head Librarian time slot is null.";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		HeadLibrarian hl= hlts.getHeadLibrarian();
		hl.setAddress(null);
		hl.setLibraryCardID(0);
		hl.setName(null);
		hl.setUsername(null);
		hlts.setHeadLibrarian(headLibrarian);
	}
	
}

>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
