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
		
		if (headLibrarianTimeSlotId < 0) {
			error += "Librarian ID is invalid!   ";
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
	
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}
