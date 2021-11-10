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
		if (name.trim().length() == 0 || name == null) {
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
		List<Event> events = new ArrayList<Event>();
		for (Event e : getAllEvents()) {
			if (e.getVisitor().equals(visitor)) {
				events.add(e);
			}
		}
		return events;
	}

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
		eventRepository.delete(e);
		e.setName(null);
		e.setVisitor(null);
		e.setEventID(-1);
	}


	// INVENTORY ITEM //

	/**
	 * 
	 * @return Iterable list of inventory items.
	 */
	@Transactional
	public List<InventoryItem> getAllInventoryItems() {
		return toList(inventoryItemRepository.findAll());
	}

	/**
	 * 
	 * @param id
	 * @return Inventory item with ID = id.
	 */
	@Transactional
	public InventoryItem getInventoryItem(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("ID is invalid!");
		}
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
			error += "Invalid number of duplicates.";
		}
		if (name.trim().length() == 0 || name == null) {
			error += "Invalid name!";
		}
		if (author.trim().length() == 0 || author == null) {
			error += "Invalid author!";
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
		inventoryItemRepository.save(item);
		return item;
	}
	
	/**
	 * Delete inventory item from the repository and set its attributes to null.
	 * @param id
	 */
	@Transactional
	public void deleteInventoryItem(int id) {
		InventoryItem i = inventoryItemRepository.findInventoryItemByInventoryItemID(id);
		inventoryItemRepository.delete(i);
		i.setAuthor(null);
		i.setDuplicates(-1);
		i.setInventoryItemID(-1);
		i.setName(null);
		i.setType(null);
		i.setStatus(null);
		i = null;
		
	}



	// RESERVATION //

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
	

	// VISITOR //

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
	 * Delete visitor from repository and set visitor to null.
	 * @param id
	 */
	@Transactional
	public void deleteVisitor(int id) {
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
	
	//TODO
	//Add checks to ensure visitor exists in repo, same for all delete methods
	
	@Transactional
	public void deleteVisitor(Visitor v) {
		visitorRepository.delete(v);
		v.setAddress(null);
		v.setBalance(-1);
		v.setDemeritPoints(-1);
		v.setLibraryCardID(-1);
		v.setName(null);
		v.setUsername(null);
		v = null;
	}

	
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
	 */
	@Transactional
	public List<HeadLibrarian> getAllHeadLibrarians() {
		return toList(headLibrarianRepository.findAll());
	}

	/**
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
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		HeadLibrarian librarian = new HeadLibrarian(name, username, address, id);
		headLibrarianRepository.save(librarian);
		return librarian;
	}

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
	@Transactional
	public List<HeadLibrarianTimeSlot> getAllHeadLibrarianTimeSlots() {
		return toList(headLibrarianTimeSlotRepository.findAll());
	}


	/**
	 * 
	 * @param id
	 * @return head librarian schedule slot with Id id.
	 */
	@Transactional
	public HeadLibrarianTimeSlot getHeadLibrarianTimeSlot(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException("Time slot id is invalid!");
		}
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

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Random rand = new Random();
		int headLibrarianTimeSlotId = rand.nextInt();

		HeadLibrarianTimeSlot hlts = new HeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, startTime, endTime, dayOfTheWeek);
		headLibrarianTimeSlotRepository.save(hlts);
		return hlts;
	}

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
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

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
	}

	/**
	 * 
	 * @param id
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

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Random rand = new Random();
		int librarianTimeSlotId = rand.nextInt();

		LibrarianTimeSlot hlts = new LibrarianTimeSlot(librarianTimeSlotId, librarian, startTime, endTime, dayOfTheWeek);
		librarianTimeSlotRepository.save(hlts);
		return hlts;
	}


	/**
	 *
	 * @param librarian
	 * @return list of all librarian's schedule slots
	 */
	@Transactional
	public List<LibrarianTimeSlot> getLibrarianTimeSlotByLibrarian(Librarian librarian) {
		List<LibrarianTimeSlot> librarianSchedule = new ArrayList<>();
		for (LibrarianTimeSlot lts : librarianTimeSlotRepository.findLibrarianTimeSlotByLibrarian(librarian)) {
			librarianSchedule.add(lts);
		}
		return librarianSchedule;
	}
	
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
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
