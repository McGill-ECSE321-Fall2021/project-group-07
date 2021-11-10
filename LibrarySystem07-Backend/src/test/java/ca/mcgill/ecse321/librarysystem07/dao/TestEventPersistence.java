package ca.mcgill.ecse321.librarysystem07.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem07.model.Event;
import ca.mcgill.ecse321.librarysystem07.model.Library;
import ca.mcgill.ecse321.librarysystem07.model.Visitor;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestEventPersistence {
	
	@Autowired
	private EventRepository eventRepository;
	
<<<<<<< HEAD
	//@Autowired
	//private VisitorRepository visitorRepository;
=======
	@Autowired
	private VisitorRepository visitorRepository;
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
	
	@AfterEach
	public void clearDatabase() {
		eventRepository.deleteAll();
<<<<<<< HEAD
=======
		visitorRepository.deleteAll();
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
	}
	
	@Test
	public void testPersistAndLoadEvent() {
<<<<<<< HEAD
		String libName = "Westmount Library";
		String city = "Montreal";
		String phoneNumber = "514-678-0453";
		Library lib = new Library(libName, city, phoneNumber);
=======
		
//		String libName = "Westmount Library";
//		String city = "Montreal";
//		String phoneNumber = "514-678-0453";
//		Library lib = new Library(libName, city, phoneNumber);
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
		
		String name = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;

<<<<<<< HEAD
		Visitor sam = new Visitor(name, username, addy, aLibraryCardID, aDemeritPoints, lib);
		
		String eventName = "ECSE 321";
		int eventID = 456789;
		
		Event Event321 = new Event(eventName, eventID, sam);
		eventRepository.save(Event321);
		
		Event321 = null;
		
=======
		Visitor sam = new Visitor(name, username, addy, aLibraryCardID, aDemeritPoints);
		
		visitorRepository.save(sam);
		
		String eventName = "ECSE 321";
		int eventID = 456789;
		Event Event321 = new Event(eventName, eventID, sam);
		
		eventRepository.save(Event321);
		
		Event321 = null;
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
		Event321 = eventRepository.findEventByEventID(eventID);
		
		assertNotNull(Event321);
		assertEquals(eventID, Event321.getEventID());
		

	}
	

}
