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
		return headLibrarianTimeSlotRepository.findHeadLibrarianTimeSlotByHeadLibrarianTimeSlotId(id);
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
		return librarianTimeSlotRepository.findLibrarianTimeSlotByLibrarianTimeSlotId(id);
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
