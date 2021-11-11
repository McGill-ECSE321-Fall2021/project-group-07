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


	/* EVENT */
	
	@Transactional
	public List<Event> getAllEvents() {
		return toList(eventRepository.findAll());
	}
	
	@Transactional
	public Event getEvent(int id) {
		if (id <0) {
			throw new IllegalArgumentException("ID is invalid!");
		}
		return eventRepository.findEventByEventID(id);
	}
	
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

		item.setType(type);
		inventoryItemRepository.save(item);
		return item;
	}

	
	/* RESERVATION */
	
	@Transactional
	public List<Reservation> getAllReservations() {
		return toList(reservationRepository.findAll());
	}
	
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
	
	@Transactional
	public Reservation createReservation(int id, Date startDate, Date endDate, 
			Visitor aVisitor, InventoryItem aInventoryItem) {
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
		if (aVisitor == null) {
			error += "Visitor is invalid!";
		}
		if (aInventoryItem == null) {
			error+= "Inventory item is invalid!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		Reservation r = new Reservation(id, startDate, endDate, aVisitor, aInventoryItem);
		reservationRepository.save(r);
		return r;
	}
	
	
	/* VISITOR */
	
	@Transactional
	public List<Visitor> getAllVisitors() {
		return toList(visitorRepository.findAll());
	}
	
	@Transactional
	public Visitor getVisitor(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("ID is invalid!");
		}
		return visitorRepository.findVisitorByLibraryCardID(id);
	}
	
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
	
	/* HEAD LIBRARIAN */

	@Transactional
	public List<HeadLibrarian> getAllHeadLibrarians() {
		return toList(headLibrarianRepository.findAll());
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
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		HeadLibrarian librarian = new HeadLibrarian(name, username, address, id);
		headLibrarianRepository.save(librarian);
		return librarian;
	}
	
	@Transactional
	public void deleteHeadLibrarian() {
		
		String error = "";
		
		if (librarianRepository.count() == 0) {
			error += "Library has no Head Librarian!";
		}
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		headLibrarianRepository.deleteAll();
	}


	/* HEAD LIBRARIAN TIMESLOT */

	@Transactional
	public List<HeadLibrarianTimeSlot> getAllHeadLibrarianTimeSlots() {
		return toList(headLibrarianTimeSlotRepository.findAll());
	}

	@Transactional
	public HeadLibrarianTimeSlot getHeadLibrarianTimeSlot(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException("Time slot id is invalid!");
		}
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
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		HeadLibrarianTimeSlot hlts = new HeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, startTime, endTime, dayOfTheWeek);
		headLibrarianTimeSlotRepository.save(hlts);
		return hlts;
	}
	
	@Transactional
	public void deleteHeadLibrarianTimeSlot(Integer headLibrarianTimeSlotId) {
		
		String error = "";
		
		if (headLibrarianTimeSlotId == null || headLibrarianTimeSlotId < 0) {
			error += "Head Librarian Time Slot ID is invalid! ";
		} else if (headLibrarianTimeSlotRepository.findById(headLibrarianTimeSlotId) == null) {
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
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		headLibrarianTimeSlotRepository.deleteAll();
	}


	/* LIBRARIAN */

	@Transactional
	public List<Librarian> getAllLibrarians() {
		return toList(librarianRepository.findAll());
	}

	@Transactional
	public Librarian getLibrarian(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException("Librarian id is invalid!");
		}
		Librarian l = librarianRepository.findLibrarianByLibraryCardID(id);
		return l;
	}

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
		librarian.setAddress(null);
		librarian.setUsername(null);
		librarian.setName(null);
		librarian.setLibraryCardID(0);
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
		
		for (Librarian librarian : librarianRepository.findAll()) {
			librarianRepository.delete(librarian);
		}
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
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		LibrarianTimeSlot hlts = new LibrarianTimeSlot(librarianTimeSlotId, librarian, startTime, endTime, dayOfTheWeek);
		librarianTimeSlotRepository.save(hlts);
		return hlts;
	}
	
	/*
	 * i added
	 */
	
	@Transactional
	public List<LibrarianTimeSlot> getLibrarianTimeSlotByLibrarian(Librarian librarian) {
		List<LibrarianTimeSlot> librarianSchedule = new ArrayList<>();
		for (LibrarianTimeSlot lts : librarianTimeSlotRepository.findLibrarianTimeSlotByLibrarian(librarian)) {
			librarianSchedule.add(lts);
		}
		return librarianSchedule;
	}
	
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
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	/*
	 * Extra methods for reservation tests
	 */
	
	public Reservation getReservation(Integer reservationId) {
		
		if (reservationId == null || reservationId < 0) {
			throw new IllegalArgumentException("Reservation id is invalid!");
		}
		return reservationRepository.findReservationByReservationId(reservationId);
	}
	
	@Transactional
	public void deleteReservation(Integer id) {
		
		String error = "";
		
		if (id == null || id < 0) {
			error += "Reservation ID is invalid! ";
		}
		
		if (reservationRepository.findReservationByReservationId(id) == null) {
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
	
	
}

}
