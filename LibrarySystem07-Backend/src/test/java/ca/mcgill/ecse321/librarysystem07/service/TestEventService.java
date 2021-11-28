<<<<<<< HEAD
=======

>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
package ca.mcgill.ecse321.librarysystem07.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
<<<<<<< HEAD
=======
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3

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
<<<<<<< HEAD
	
	
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
=======


	@InjectMocks
	private LibrarySystem07Service service;

	private static Integer eventID = 456789;
	private static final int NONEXISTING_EVENT_KEY = 1;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(eventDao.findEventByEventID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(eventID)) {
				Event event = new Event();
				event.setEventID(eventID);
				event.setName(null);
				event.setVisitor(null);

				return event;
			} else {
				return null;
			}
		});
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
	}
	@Test
	public void testCreateEvent() {
		assertEquals(0, service.getAllEvents().size());
<<<<<<< HEAD
		
		String name = "ECSE321";
		Integer eventID = 456789;
		
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor sam = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints, lib);
		
		Event event = null;
		
=======

		String name = "ECSE321";
		Integer eventID = 4567;

		String nameVisitor = "Sam";
		String username = "Sam20031";
		String address = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor sam = new Visitor(nameVisitor, username, address, aLibraryCardID, aDemeritPoints);

		Event event = null;

>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
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
<<<<<<< HEAD
	
	@Test
	public void testCreateEventNull() {
		String name = null;
		Integer eventID = null;
		Visitor sam = null;
		
		Event event = null;
		String error = null;
		
=======

	@Test
	public void testCreateEventNull() {
		String name = null;
		int eventID = 1;
		Visitor sam = null;

		Event event = null;
		String error = null;

>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		try {
			event = service.createEvent(name, eventID, sam);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
<<<<<<< HEAD
		
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
=======

		assertNull(event);
		assertEquals("Name is invalid! " + "Visitor is invalid!", error);
	}
	
	@Test
	public void testUpdateEventName() {
		assertEquals(0, service.getAllEvents().size());

		String name = "Book signing";
		int eventId = 7;
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String address = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor visitor = new Visitor(nameVisitor, username, address, aLibraryCardID, aDemeritPoints);

		Event event = service.createEvent(name, eventId, visitor);

		String newName = "Autobiography signing";
		try {
			service.updateEventName(event, newName);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(newName, event.getName());
	}
	
	@Test
	public void testUpdateEventInvalidName() {
		String error = "";
		String name = "Book signing";
		int eventId = 7;
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String address = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor visitor = new Visitor(nameVisitor, username, address, aLibraryCardID, aDemeritPoints);

		Event event = service.createEvent(name, eventId, visitor);

		String newName = "   ";
		try {
			service.updateEventName(event, newName);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Invalid name!", error);
	}
	@Test
	public void testUpdateEventNameNull() {
		String error = "";
		Event event =null;

		String newName = "Signing";
		try {
			service.updateEventName(event, newName);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Event is null!", error);
	}

	
	@Test
	public void testDeleteEvent() {
		String name = "ECSE321";
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String address = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor sam = new Visitor(nameVisitor, username, address, aLibraryCardID, aDemeritPoints);

		Event event = null; 

		event = service.createEvent(name, eventID, sam);

		assertNotNull(event);

		try{
			service.deleteEvent(event);
		} catch(IllegalArgumentException e) {
			fail();
		}

		verify(eventDao, times(1)).delete(any(Event.class));
	}
	
	@Test
	public void testDeleteEventById() {
		String name = "ECSE321";
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String address = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor sam = new Visitor(nameVisitor, username, address, aLibraryCardID, aDemeritPoints);

		Event event = null; 

		event = service.createEvent(name, eventID, sam);

		assertNotNull(event);

		try{
			service.deleteEvent(event.getEventID());
		} catch(IllegalArgumentException e) {
			fail();
		}

		verify(eventDao, times(1)).delete(any(Event.class));
	}

	@Test
	public void testDeleteNullEvent(){
		String error = "";
		Event event = null;

		try{
			service.deleteEvent(event);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(event);
		assertEquals("Event is null!", error);
	}
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3

	@Test
	public void testCreateEventNegative() {
		Integer eventID = -1;
		String name = "ECSE321";
<<<<<<< HEAD
		
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor sam = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints, lib);
		
		Event event = null;
		String error = null;
		
=======

		String nameVisitor = "Sam";
		String username = "Sam20031";
		String address = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor sam = new Visitor(nameVisitor, username, address, aLibraryCardID, aDemeritPoints);

		Event event = null;
		String error = null;

>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
		try {
			event = service.createEvent(name, eventID, sam);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
<<<<<<< HEAD
		
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
=======

		assertNull(event);
		assertEquals("ID must be an integer above 0.", error);
	}

	@Test
	public void testGetExistingEvent() {
		assertEquals(eventID, service.getEvent(eventID).getEventID());
	}
	@Test
	public void testGetNonExistingEvent() {
		assertNull(service.getEvent(NONEXISTING_EVENT_KEY));
	}

}
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
