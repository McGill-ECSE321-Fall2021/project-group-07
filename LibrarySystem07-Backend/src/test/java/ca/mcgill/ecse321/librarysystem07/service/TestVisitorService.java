package ca.mcgill.ecse321.librarysystem07;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import ca.mcgill.ecse321.librarysystem07.service.LibrarySystem07Service;

/*
 * MOCK DATABASE TO TEST FUNCTIONALITY
 */

@ExtendWith(MockitoExtension.class)
public class TestVisitorService {


	@Mock
	private VisitorRepository visitorDao;
	@Mock
	private EventRepository eventDao;
	@Mock
	private ReservationRepository reservationDao;
	
	
	@InjectMocks
	private LibrarySystem07Service service;

	private static final int VISITOR_KEY = 321;
	private static final int NONEXISTING_VISITOR_KEY = 123;

	/**
	 * Mock response from database returning a visitor
	 */
	@BeforeEach
	public void setMockOutput() {
		lenient().when(visitorDao.findVisitorByLibraryCardID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(VISITOR_KEY)) { //arg 0 is findPersonByName(anyString()), answer is the following
				Visitor v = new Visitor(null, null, null, VISITOR_KEY); //return new visitor AS IF from DB
				return v;
			} else { //return null if person doesn't exist
				return null;
			}
		});
		// Whenever anything is saved, return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(visitorDao.save(any(Visitor.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(reservationDao.save(any(Reservation.class))).thenAnswer(returnParameterAsAnswer);
	}


	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * VISITOR TESTS * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	//Create a valid visitor
	//No error should be raised
	@Test
	public void testCreateVisitor() {
		assertEquals(0, service.getAllVisitors().size());
		Integer libraryCardId = 100;
		String name = "Bob";
		String username = "Bobob";
		String address = "1 Montreal";
		
		Visitor visitor = null;
		try {
			visitor = service.createVisitor(name, username, address, libraryCardId, 0);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(visitor);
		assertEquals(name, visitor.getName());
	}
	
	//Create a null visitor
	//Error should be raised saying that the visitor is not valid; no visitor shall be created
	@Test
	public void testCreateVisitorNull() {
		String error = null;
		assertEquals(0, service.getAllVisitors().size());
		Integer libraryCardId = 100;
		String name = null;
		String username = "Bobob";
		String address = "1 Montreal";
		
		Visitor visitor = null;
		try {
			visitor = service.createVisitor(name, username, address, libraryCardId, 0);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(visitor);
		assertNotNull(error);
		assertEquals("Name is invalid!", error);
	}
	
	//Create a visitor with no name
	//Raise an error saying visitor name is invalid
	@Test
	public void testCreateVisitorEmptyName() {
		String name = "";
		String username = "Bobob";
		String address = "1 Montreal";
		Integer libraryCardId = 100;
		String error = null;
		Visitor visitor = null;
		try {
			visitor = service.createVisitor(name, username, address, libraryCardId, 0);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(visitor);
		// check error
		assertEquals("Name is invalid!", error);
	}

	//Create a visitor that lives outside the library city of montreal
	//No error; instead, balance should be 10, the fee for being a library member if not living in the library's city
	@Test
	public void testCreateVisitorOutsideCity() {
		String name = "Bob";
		String username = "Bobob";
		String address = "1 Chicago";
		Integer libraryCardId = 100;
		Visitor visitor = null;
		try {
			visitor = service.createVisitor(name, username, address, libraryCardId, 0);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(visitor);
		assertEquals(10, visitor.getBalance());
	}
	
	//Create a visitor that lives in the same city as the library
	//Balance should be 0
	@Test
	public void testCreateVisitorInsideCity() {
		String name = "Bob";
		String username = "Bobob";
		String address = "1 Montreal";
		Integer libraryCardId = 100;
		Visitor visitor = null;
		try {
			visitor = service.createVisitor(name, username, address, libraryCardId, 0);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(visitor);
		assertEquals(0, visitor.getBalance());
	}
	
	@Test
	public void testUpdateVisitorBalance() {
		String error = null;
		Integer libraryCardId = 215;
		String name = "Bob";
		String username = "Bobob";
		String address = "1 Montreal";
		float newBalance = 15;

		Visitor visitor = service.createVisitor(name, username, address, libraryCardId, 0);
		service.updateVisitorBalance(visitor, newBalance);
		
		assertEquals(15, visitor.getBalance());
	}
	
	//Update visitor address to a valid address
	@Test
	public void testUdateVisitorAddress() {
		String error = null;
		Integer libraryCardId = 15;
		String name = "Bob";
		String username = "Bobob";
		String address = "1 Montreal";
		
		Visitor visitor = service.createVisitor(name, username, address, libraryCardId, 0);
		assertNotNull(visitor);
		
		String newAddress = "3 Montreal";
		service.updateVisitorAddress(visitor, newAddress);
		assertEquals(newAddress, visitor.getAddress());
	}
	
	@Test
	public void testDeleteVisitor() {
		String name = "Bob";
		String username = "Bobob";
		String address = "1 Montreal";
		Visitor v = service.getVisitor(VISITOR_KEY);
		try {
			service.deleteVisitor(v);
		} catch(IllegalArgumentException e) {
			fail();
		}
		//verify(visitorDao, times(1)).deleteById(anyInt());		
		verify(visitorDao, times(1)).delete(any(Visitor.class));
	}
	
	@Test
	public void testGetExistingVisitor() {
		assertEquals(VISITOR_KEY, service.getVisitor(VISITOR_KEY).getLibraryCardID());
	}

	@Test
	public void testGetNonExistingPerson() {
		Visitor v = null;
		try {
			v = service.getVisitor(NONEXISTING_VISITOR_KEY);
		} catch (NullPointerException e) {
			
		}
		assertNull(v);
	}

}
