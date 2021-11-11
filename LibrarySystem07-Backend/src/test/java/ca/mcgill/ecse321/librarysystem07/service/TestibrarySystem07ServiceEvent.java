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
public class TestibrarySystem07ServiceEvent {

	@Mock
	private EventRepository eventDto;
	@Mock
	private LibrarianRepository libraianDto;
	
	@InjectMocks
	private LibrarySystem07Service service;
	
	private static final Integer LIBRARIAN_KEY = 1234;
	private static final Integer NONEXISTING_KEY = 0;
	
	@BeforeEach
	public void setMockOutput() {
	    lenient().when(libraianDto.findLibrarianByLibraryCardID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(LIBRARIAN_KEY)) {
	            Librarian librarian = new Librarian();
	            librarian.setLibraryCardID(LIBRARIAN_KEY);
	            return librarian;
	        } else {
	            return null;
	        }
	    });
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
	    lenient().when(eventDto.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
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
		String error = null;
		
		Event event = null;
		
		try {
			event = service.createEvent(name, eventID, sam);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(event);
		assertEquals("Event name is invalid!" + "Event ID is invalid!" + "Visitor is invalid!", error);
		
	}
	
	
}
