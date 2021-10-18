package ca.mcgill.ecse321.librarysystem07.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem07.model.*;
import ca.mcgill.ecse321.librarysystem07.model.ReservableItem.Status;
import ca.mcgill.ecse321.librarysystem07.model.ReservableItem.TypeOfReservableItem;
import ca.mcgill.ecse321.librarysystem07.model.TimeSlot.DayOfTheWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibrarySystem07Persistence {
	
	@Autowired
	private VisitorRepository visitorRepository;
	
	@Autowired
	private ReservableItemRepository reservableItemRepository;

	@Autowired
	private NonReservableItemRepository nonReservableItemRepository;

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private TimeslotRepository timeSlotRepository;

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private LibrarianRepository librarianRepository;
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
	
	@AfterEach
	public void clearDatabase() {
		visitorRepository.deleteAll();
		reservableItemRepository.deleteAll();
		nonReservableItemRepository.deleteAll();
		eventRepository.deleteAll();
		timeSlotRepository.deleteAll();
		reservationRepository.deleteAll();
		librarianRepository.deleteAll();
		headLibrarianRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadTimeSlot() {
		String aName = "TestVisitor";
		String aPhoneNumber = "343278902";
		String aAddress = "4500 haha st"; 
		Library aLibrary = new Library(aName, aAddress, aPhoneNumber);

		Librarian librarian = new Librarian("Lisa", "lisa1", "123 steet", 0, aLibrary);
		
		Time startTime = new Time(8, 0, 0);
		Time endTime = new Time(10, 0, 0);
		Date aDate = new Date(2021, 11, 0);
		
		TimeSlot ts = new TimeSlot(startTime, endTime, aDate, DayOfTheWeek.Monday, 0, librarian, null, aLibrary, null, null);

		timeSlotRepository.save(ts);
		int id = ts.getTimeSlotID();

		ts = null;
		ts = timeSlotRepository.findTimeSlotByTimeSlotID(id);
		
		assertNotNull(ts);
		assertEquals(id, ts.getTimeSlotID());
	}
		
	@Test
	public void testPersistAndLoadVisitor() {
		
		String aName = "TestVisitor";
		String aUsername = "TestVisitor123";
		String aAddress = "4500 haha st"; 

		Library aLibrary = new Library(aName, aUsername, aAddress);
		
		Time startTime = new Time(9, 0, 0);
		Time endTime = new Time(20, 0, 0);
		Date day  = new Date(2021, 10, 17);
		TimeSlot.DayOfTheWeek weekday = TimeSlot.DayOfTheWeek.Sunday;
		TimeSlot openingHours = new TimeSlot(startTime, endTime, day, weekday, 0, null, null, aLibrary, null, null);
		
		aLibrary.addTimeSlot(openingHours);
		
		Time startTime1 = new Time(9, 0, 0);
		Time endTime1 = new Time(20, 0, 0);
		Date day1  = new Date(2021, 10, 18);
		TimeSlot.DayOfTheWeek weekday1 = TimeSlot.DayOfTheWeek.Monday;
		TimeSlot openingHours1 = new TimeSlot(startTime1, endTime1, day1, weekday1, 0, null, null, aLibrary, null, null);
		
		aLibrary.addTimeSlot(openingHours1);
		
		Time startTime12 = new Time(9, 0, 0);
		Time endTime12 = new Time(20, 0, 0);
		Date day12  = new Date(2021, 10, 19);
		TimeSlot.DayOfTheWeek weekday12 = TimeSlot.DayOfTheWeek.Tuesday;
		TimeSlot openingHours12 = new TimeSlot(startTime12, endTime12, day12, weekday12, 0, null, null, aLibrary, null, null);

		aLibrary.addTimeSlot(openingHours12);
		
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		
		// First example for object save/load
		Visitor visitor = new Visitor(aName, aUsername, aAddress, aLibraryCardID, aLibrary, aDemeritPoints);
		// First example for attribute save/load
		visitorRepository.save(visitor);

		visitor = null;

		visitor = visitorRepository.findVisitorByLibrarianCardID(aLibraryCardID);
		assertNotNull(visitor);
		assertEquals(aLibraryCardID, visitor.getLibraryCardID());
	}
	
	@Test
	public void testPersistAndLoadLibrarian() {
		String aName = "TestVisitor";
		String aPhoneNumber = "343278902";
		String aAddress = "4500 haha st"; 
		Library aLibrary = new Library(aName, aAddress, aPhoneNumber);

		int libraryCardID = 0;
		String name = "Lisa";
		String username = "lisa1";
		Librarian librarian = new Librarian(name, username, "123 steet", libraryCardID, aLibrary);
		
		librarianRepository.save(librarian);
		
		librarian = null;
		librarian = librarianRepository.findLibrarianByLibraryCardID(libraryCardID);
		
		assertNotNull(librarian);
		assertEquals(libraryCardID, librarian.getLibraryCardID());
		assertEquals(name, librarian.getName());
        assertEquals(username, librarian.getUsername());
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
		String name = "Nancy";
		String username = "nancy";
		String address = "240 test street";
		int libraryCardId = 667;
		Library library = new Library("Lib", "Montreal", "76859340");
		
		HeadLibrarian headLibrarian= new HeadLibrarian(name, username, address, libraryCardId, library);
		headLibrarianRepository.save(headLibrarian);
		
		headLibrarian = null;
		
		headLibrarian = headLibrarianRepository.findHeadLibrarianByName(name);
		
		assertNotNull(headLibrarian);
		assertEquals(libraryCardId, headLibrarian.getLibraryCardID());
	}
	
	@Test
	public void testPersistAndLoadReservableItem() {
		
		int aId = 345456;
		
		String aName = "TestLibrary";
		String aPhoneNumber = "543678439";
		String aAddress = "4500 haha st"; 

		Library aLibrary = new Library(aName, aAddress, aPhoneNumber);
		
		Time startTime = new Time(9, 0, 0);
		Time endTime = new Time(20, 0, 0);
		Date day  = new Date(2021, 10, 17);
		TimeSlot.DayOfTheWeek weekday = TimeSlot.DayOfTheWeek.Sunday;
		TimeSlot openingHours = new TimeSlot(startTime, endTime, day, weekday, 0, null, null, aLibrary, null, null);
		
		aLibrary.addTimeSlot(openingHours);
		
		Time startTime1 = new Time(9, 0, 0);
		Time endTime1 = new Time(20, 0, 0);
		Date day1  = new Date(2021, 10, 18);
		TimeSlot.DayOfTheWeek weekday1 = TimeSlot.DayOfTheWeek.Monday;
		TimeSlot openingHours1 = new TimeSlot(startTime1, endTime1, day1, weekday1, 0, null, null, aLibrary, null, null);
		
		aLibrary.addTimeSlot(openingHours1);
		
		Time startTime12 = new Time(9, 0, 0);
		Time endTime12 = new Time(20, 0, 0);
		Date day12  = new Date(2021, 10, 19);
		TimeSlot.DayOfTheWeek weekday12 = TimeSlot.DayOfTheWeek.Tuesday;
		TimeSlot openingHours12 = new TimeSlot(startTime12, endTime12, day12, weekday12, 0, null, null, aLibrary, null, null);

		aLibrary.addTimeSlot(openingHours12);
		
		int aDuplicates = 1;
		String aNamee = "George of The Jungle"; 
		String aAuthor = "man";
		Status aStatus = ReservableItem.Status.Available;
		TypeOfReservableItem aReservableItem = ReservableItem.TypeOfReservableItem.Book;
			
		// First example for object save/load
		ReservableItem item = new ReservableItem(aId, aLibrary, aDuplicates, aNamee, aAuthor, aStatus, aReservableItem);
		// First example for attribute save/load
		reservableItemRepository.save(item);

		item = null;

		item = reservableItemRepository.findReservableItemById(aId);
		assertNotNull(item);
		assertEquals(aId, item.getId());
	}
	
	@Test
	public void testPersistAndLoadNonReservableItem() {
		
		int aId = 345456;
		
		String aName = "TestVisitor";
		String aUsername = "TestVisitor123";
		String aAddress = "4500 haha st"; 

		Library aLibrary = new Library(aName, aUsername, aAddress);
		
		Time startTime = new Time(9, 0, 0);
		Time endTime = new Time(20, 0, 0);
		Date day  = new Date(2021, 10, 17);
		TimeSlot.DayOfTheWeek weekday = TimeSlot.DayOfTheWeek.Sunday;
		TimeSlot openingHours = new TimeSlot(startTime, endTime, day, weekday, 0, null, null, aLibrary, null, null);
		
		aLibrary.addTimeSlot(openingHours);
		
		Time startTime1 = new Time(9, 0, 0);
		Time endTime1 = new Time(20, 0, 0);
		Date day1  = new Date(2021, 10, 18);
		TimeSlot.DayOfTheWeek weekday1 = TimeSlot.DayOfTheWeek.Monday;
		TimeSlot openingHours1 = new TimeSlot(startTime1, endTime1, day1, weekday1, 0, null, null, aLibrary, null, null);
		
		aLibrary.addTimeSlot(openingHours1);
		
		Time startTime12 = new Time(9, 0, 0);
		Time endTime12 = new Time(20, 0, 0);
		Date day12  = new Date(2021, 10, 19);
		TimeSlot.DayOfTheWeek weekday12 = TimeSlot.DayOfTheWeek.Tuesday;
		TimeSlot openingHours12 = new TimeSlot(startTime12, endTime12, day12, weekday12, 0, null, null, aLibrary, null, null);

		aLibrary.addTimeSlot(openingHours12);
		
		NonReservableItem.TypeOfNonReservableItem aReservableItem = NonReservableItem.TypeOfNonReservableItem.Magazine;
			
		// First example for object save/load
		NonReservableItem item = new NonReservableItem(aId, aLibrary, aReservableItem);
		// First example for attribute save/load
		nonReservableItemRepository.save(item);

		item = null;

		item = nonReservableItemRepository.findNonReservableItemById(aId);
		assertNotNull(item);
		assertEquals(aId, item.getId());
	}
	
	@Test
	public void testPersistAndLoadEvent() {
		
		String aName = "TestVisitor";
		String aUsername = "TestVisitor123";
		String aAddress = "4500 haha st"; 

		Library aLibrary = new Library(aName, aUsername, aAddress);
		
		Time startTime = new Time(9, 0, 0);
		Time endTime = new Time(20, 0, 0);
		Date day  = new Date(2021, 10, 17);
		TimeSlot.DayOfTheWeek weekday = TimeSlot.DayOfTheWeek.Sunday;
		TimeSlot openingHours = new TimeSlot(startTime, endTime, day, weekday, 0, null, null, aLibrary, null, null);
		
		aLibrary.addTimeSlot(openingHours);
		
		Time startTime1 = new Time(9, 0, 0);
		Time endTime1 = new Time(20, 0, 0);
		Date day1  = new Date(2021, 10, 18);
		TimeSlot.DayOfTheWeek weekday1 = TimeSlot.DayOfTheWeek.Monday;
		TimeSlot openingHours1 = new TimeSlot(startTime1, endTime1, day1, weekday1, 0, null, null, aLibrary, null, null);
		
		aLibrary.addTimeSlot(openingHours1);
		
		Time startTime12 = new Time(9, 0, 0);
		Time endTime12 = new Time(20, 0, 0);
		Date day12  = new Date(2021, 10, 19);
		TimeSlot.DayOfTheWeek weekday12 = TimeSlot.DayOfTheWeek.Tuesday;
		TimeSlot openingHours12 = new TimeSlot(startTime12, endTime12, day12, weekday12, 0, null, null, aLibrary, null, null);

		aLibrary.addTimeSlot(openingHours12);
		
		//Code to instantiate a visitor
		String name = "Max";
		String username = "Max123";
		String address = "123 cat street";
		int libraryCardId = 123456789;
		int demerit = 0;
		Visitor visitor = new Visitor(name, username, address, libraryCardId, aLibrary, demerit);
		
		//Creating new event
		String eventName = "Birday";
		int ide = 7654;
		Event newEvent = new Event(eventName, ide, visitor);
		
		eventRepository.save(newEvent);
		newEvent = null;
		newEvent = eventRepository.findEventByEventID(ide); //find event by the Visitor's name
		
		assertNotNull(newEvent);
		assertEquals(visitor, newEvent.getVisitor());
	}
	
	@Test
	public void testPersistAndLoadReservation() {
		
		String aName = "library1";
		String aUsername = "fijdslkm";
		String aAddress = "4500 haha st"; 
		Library aLibrary = new Library(aName, aUsername, aAddress);
		
		/*Persist visitor*/
		Visitor v = new Visitor("bob", "bob1", "street A", 1, aLibrary, 0);
		visitorRepository.save(v);
		
		
		/*Persist reservable item*/
		int aDuplicates = 1;
		String name = "George of The Jungle"; 
		String aAuthor = "man";
		Status aStatus = ReservableItem.Status.Available;
		TypeOfReservableItem aReservableItem = ReservableItem.TypeOfReservableItem.Book;
		ReservableItem r = new ReservableItem(0, aLibrary, aDuplicates, name, aAuthor, aStatus, aReservableItem);
		reservableItemRepository.save(r);
		
		/*Persist and load reservation*/
		int resId = 0;//reservation.getReservationID();
		Reservation reservation = new Reservation(resId, v, r);
		reservationRepository.save(reservation);
		
		reservation = null;
		reservation = reservationRepository.findReservationByReservationID(resId);
		assertNotNull(reservation);
		assertEquals(resId, reservation.getReservationID());	
	
	}


}
