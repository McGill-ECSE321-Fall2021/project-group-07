package ca.mcgill.ecse321.librarysystem07.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem07.dao.*;
import ca.mcgill.ecse321.librarysystem07.model.*;
import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarianTimeSlot.DayOfTheWeek;

@ExtendWith(MockitoExtension.class)
public class TestEventService {

	@Mock
	private EventRepository eventDao;
	
	
	@InjectMocks
	private LibrarySystem07Service service;
	
	private static String name = "ECSE321";
	private static Integer eventID = 456789;
	
	private static String nameVisitor = "Sam";
	private static String username = "Sam20031";
	private static String addy = "4500 av Cumberland";
	private static Integer aLibraryCardID = 12345; 
	private static int aDemeritPoints = 0;
	private static Visitor sam = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints, lib);
	
	private static final int nonExistingEventID = -1;
	@BeforeEach
	public void setMockOutput() {
	    lenient().when(eventDao.findEventByEventID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(eventID)) {
	            Event event = new Event();
	            
	            event.setEventID(eventID);
	            event.setName(name);
	            event.setVisitor(sam);
	            
	            return event;
	        } else {
	            return null;
	        }
	    });
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
	    lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
	}
	@Test
	public void testCreateEvent() {
		assertEquals(0, service.getAllEvents().size());
		
		String name = "ECSE321";
		Integer eventID = 456789;
		
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor sam = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints, lib);
		
		Event event = null;
		
		try {
			event = service.createEvent(name, eventID, sam);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertNotNull(event);
		assertEquals(name, event.getName());
		assertEquals(eventID, event.getEventID());
		assertEquals(sam, event.getVisitor());
	}
	
	@Test
	public void testCreateEventNull() {
		String name = null;
		Integer eventID = null;
		Visitor sam = null;
		
		Event event = null;
		String error = null;
		
		try {
			event = service.createEvent(name, eventID, sam);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(event);
		assertEquals("Event name is invalid!" + "Event ID is invalid!" + "Visitor is invalid!", error);
		
	}
		@Test
	    public void testDeleteEvent(){
			String name = "ECSE321";
			Integer eventID = 456789;
			
			String nameVisitor = "Sam";
			String username = "Sam20031";
			String addy = "4500 av Cumberland";
			Integer aLibraryCardID = 12345; 
			int aDemeritPoints = 0;
			Visitor sam = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints, lib);
			
			Event event = null; 

	        event = service.createEvent(name, eventID, sam);

	        assertNotNull(event);

	        try{
	            event = service.deleteEvent(event);
	        } catch(IllegalArgumentException e) {
	            fail();
	        }

	        assertNull(event);
	    }
		
		@Test
	    public void testDeleteNullEvent(){
	        String error = "";

	        Event event = null;

	        try{
	            event = service.deleteEvent(event);
	        } catch(IllegalArgumentException e) {
	            error = e.getMessage();
	        }

	        assertNull(event);
	        assertEquals("Event must not be null.", error);
	    }

	@Test
	public void testCreateEventNegative() {
		Integer eventID = -1;
		String name = "ECSE321";
		
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor sam = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints, lib);
		
		Event event = null;
		String error = null;
		
		try {
			event = service.createEvent(name, eventID, sam);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(event);
		assertEquals("Event ID is invalid!", error);
	}
	
	@Test
	public void testGetExistingEvent() {
		assertEquals(eventID, service.getEvent(eventID).getEventID())
	}
	@Test
	public void testGetNonExistingEvent() {
		assertNull(service.getEvent(nonExistingEventID));
	}
	
}
