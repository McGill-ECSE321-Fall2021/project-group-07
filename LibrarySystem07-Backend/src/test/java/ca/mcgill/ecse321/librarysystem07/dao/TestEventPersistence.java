package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem07.model.*;
import ca.mcgill.ecse321.librarysystem07.dao.LibrarySystem07Repository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestEventPersistence {
	
	@Autowired
	private EventRepository eventRepository;

	@AfterEach
	public void clearDatabase() {
		eventRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadEvent() {
		List<TimeSlot> schedule = new ArrayList<TimeSlot>();
		
		//Code to instantiate a library for our visitor
		String libraryName = "Johnson Library";
		String city = "Montreal";
		List<TimeSlot> openingHours = new ArrayList<TimeSlot>();
		List<Librarian> employees = new ArrayList<Librarian>();
		long phoneNumber = 514-514-5141;
		Library library = new Library(libraryName, city, openingHours, employees, phoneNumber);
		
		//Code to instantiate a visitor
		String name = "Max";
		String username = "Max123";
		String address = "123 cat street";
		int libraryCardId = 123456789;
		int demerit = 0;
		Visitor visitor = new Visitor(name, username, address, libraryCardId, library, demerit);
		
		//Creating new event
		Event newEvent = new Event(schedule,visitor);
		
		eventRepository.save(newEvent);
		newEvent = null;
		newEvent = eventRepository.findEventByName(name); //find event by the Visitor's name
		
		assertNotNull(newEvent);
		assertEquals(visitor, newEvent.getVisitor());
		assertEquals(schedule, newEvent.getSchedule());
	}
	

}
