package ca.mcgill.ecse321.librarysystem07.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem07.dao.*;
import ca.mcgill.ecse321.librarysystem07.model.*;
import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarianTimeSlot.DayOfTheWeek;
import ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status;
import ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem;


@Service
public class LibrarySystemService {

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
	
	/* INVENTORY ITEM */
	
	@Transactional
	public List<InventoryItem> getAllInventoryItems() {
		return toList(inventoryItemRepository.findAll());
	}
	
	@Transactional
	public InventoryItem getInventoryItem(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("ID is invalid!");
		}
		return inventoryItemRepository.findInventoryItemByInventoryItemID(id);
	}
	
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
	public HeadLibrarian getHeadLibrarian(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException("ID is invalid!");
		}
		HeadLibrarian l = headLibrarianRepository.findHeadLibrarianByLibraryCardID(id);
		return l;
	}

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
	public HeadLibrarianTimeSlot createHeadLibrarianTimeSlot(int headLibrarianTimeSlotId, 
			HeadLibrarian headLibrarian, Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek) {
		String error = "";

		if (headLibrarian == null) {
			error += "Head librarian is invalid!";
		}
		if (headLibrarianTimeSlotId < 0) {
			error += "Head librarian ID is invalid!   ";
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
		HeadLibrarianTimeSlot hlts = new HeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, startTime, endTime, dayOfTheWeek);
		headLibrarianTimeSlotRepository.save(hlts);
		return hlts;
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
	public LibrarianTimeSlot createLibrarianTimeSlot(int librarianTimeSlotId, 
			Librarian librarian, Time startTime, Time endTime, LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek) {
		String error = "";

		if (librarian == null) {
			error += "Head librarian is invalid!";
		}
		if (librarianTimeSlotId < 0) {
			error += "Head librarian ID is invalid!   ";
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
		LibrarianTimeSlot hlts = new LibrarianTimeSlot(librarianTimeSlotId, librarian, startTime, endTime, dayOfTheWeek);
		librarianTimeSlotRepository.save(hlts);
		return hlts;
	}
	
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}