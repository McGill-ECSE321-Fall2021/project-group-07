package ca.mcgill.ecse321.librarysystem07.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem07.dao.*;
import ca.mcgill.ecse321.librarysystem07.model.*;

/*
 * assuming database works, when we call specific function return
 * this response for this function call (assume its the database)
 */

@ExtendWith(MockitoExtension.class)
public class TestReservationService {
	
	/*
	 * created in response to making function call
	 * mocking response from server
	 * pretend this response we get
	 * even tho we use the datatype, it actually doesn't do
	 * anything besides mock up for testing
	 */
	
	@Mock
	private ReservationRepository reservationDao;

	@InjectMocks
	private LibrarySystem07Service service;

	private static final Integer RESERVATION_KEY = 3445;
	private static final Integer NONEXISTING_KEY = 0;
	
	/*
	 * any string => getArgument(0)
	 * triggers invocation call, prevents any other data type to be passed
	 * if it is, create person
	 * if person exists in database, we get that person back
	 * what this does (mocking database return)
	 * if it is equal to person key, then it will create it and return it 
	 * 
	 */

	@BeforeEach
	public void setMockOutput() {
	    lenient().when(reservationDao.findReservationByReservationId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(RESERVATION_KEY)) {
	            Reservation reservation = new Reservation();
	            reservation.setReservationID(RESERVATION_KEY);
	            return reservation;
	        } else {
	            return null;
	        }
	    });
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
	    lenient().when(reservationDao.save(any(Reservation.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * RESERVATION TESTS * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/*
	 * testCreateReservation()
	 * 
	 * Test creating reservation, if successful, then service should successfully add
	 * a reservation to the system. 
	 * Check to see if the object we created exists, and if it's attributes where properly set
	 */
	
	@Test
	public void testCreateReservation() {
		
		assertEquals(0, service.getAllReservations().size());
		Integer reservationId = RESERVATION_KEY;
		Date startDate = Date.valueOf("1971-01-01");
		Date endDate = Date.valueOf("1971-01-02");
		
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor visitor = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints);
		
		int aInventoryItemID=2;
		int aDuplicates=6;
		String aName="book";
		String aAuthor="amelie";
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status aStatus=ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status.CheckedOut;
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem aType= ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem.Book;
		InventoryItem inventoryItem = new InventoryItem(aInventoryItemID, aDuplicates, aName, aAuthor, aStatus, aType);
		Reservation reservation = null;
		
		try {
			reservation = service.createReservation(reservationId, startDate, endDate, visitor, inventoryItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(reservation);
		assertEquals(reservationId, reservation.getReservationID());
		assertEquals(startDate, reservation.getStartDate());
		assertEquals(endDate, reservation.getEndData());
		assertEquals(inventoryItem, reservation.getInventoryItem());
		
	}
	
	/*
	 * testCreateReservationNull()
	 * 
	 * if any of the fields of reservation are null upon initialization,
	 * creation of a reservation will be terminated and corresponding error message will
	 * be thrown
	 */
	
	@Test
	public void testCreateReservationNull() {
		
		Integer reservationId = NONEXISTING_KEY;
		Date startDate = null;
		Date endDate = null;
		String error=null;
		Visitor visitor = null;
		int aInventoryItemID=2;
		int aDuplicates=6;
		String aName="book";
		String aAuthor="amelie";
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status aStatus=ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status.CheckedOut;
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem aType= ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem.Book;
		InventoryItem inventoryItem = new InventoryItem(aInventoryItemID, aDuplicates, aName, aAuthor, aStatus, aType);
		
		Reservation reservation = null;
		
		try {
			reservation = service.createReservation(reservationId, startDate, endDate, visitor, inventoryItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(reservation);
		assertEquals("Reservation ID is invalid! Reservation start date is invalid! Reservation end date is invalid! "
				+ "Visitor is invalid! Inventory item is invalid! ", error);
		
		
	}
	
	/*
	 * testCreateReservationNegative()
	 * 
	 * if the unique ID of reservation is negative, we cannot have this and thus no object
	 * will be created and an error will be thrown.
	 */
	
	@Test
	public void testCreateLibrarianNegative() {
		Integer reservationId = -RESERVATION_KEY;
		Date startDate = Date.valueOf("1971-01-01");
		Date endDate = Date.valueOf("1971-01-02");
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor visitor = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints);
		
		int aInventoryItemID=2;
		int aDuplicates=6;
		String aName="book";
		String aAuthor="amelie";
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status aStatus=ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status.CheckedOut;
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem aType= ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem.Book;
		InventoryItem inventoryItem = new InventoryItem(aInventoryItemID, aDuplicates, aName, aAuthor, aStatus, aType);
		
		String error = null;
		Reservation reservation = null;
		
		try {
			reservation = service.createReservation(reservationId, startDate, endDate, visitor, inventoryItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(reservation);
		assertEquals("Reservation ID is invalid!", error);
	}

	@Test
	public void testGetExistingReservation() {
		assertEquals(RESERVATION_KEY, service.getReservation(RESERVATION_KEY).getReservationID());
	}
	@Test
	public void testGetNonExistingReservation() {
		assertNull(service.getLibrarian(NONEXISTING_KEY));
	}
	
	/*
	 * testDeleteReservation()
	 * 
	 * test delete specific reservation, reservation should no longer exist in the system
	 * and number of reservations in the system should go down by one.
	 */
	
	@Test
	public void testDeleteReservation() {
		
		Integer reservationId = RESERVATION_KEY;
		Date startDate = Date.valueOf("1971-01-01");
		Date endDate = Date.valueOf("1971-01-02");
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor visitor = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints);
		
		int aInventoryItemID=2;
		int aDuplicates=6;
		String aName="book";
		String aAuthor="amelie";
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status aStatus=ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status.CheckedOut;
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem aType= ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem.Book;
		InventoryItem inventoryItem = new InventoryItem(aInventoryItemID, aDuplicates, aName, aAuthor, aStatus, aType);
		
		Reservation reservation = null;
		
		reservation = service.createReservation(reservationId, startDate, endDate, visitor, inventoryItem);
		int numOfReservationsBefore = service.getAllReservations().size();
		
		try {
			service.deleteReservation(reservationId);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		int numOfReservationsAfter = service.getAllReservations().size();
		
		assertNull(reservation);
		assertEquals(numOfReservationsBefore - 1, numOfReservationsAfter);
	}
	
	/*
	 * testDeleteReservationNullId()
	 * 
	 * if a reservation id is null when calling delete, then it will no be able to find
	 * an associated reservation to delete, and therefore no reservation will be deleted
	 * and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteReservationNullId() {
		
		Integer reservationId = RESERVATION_KEY; 
		Date startDate = Date.valueOf("1971-01-01");
		Date endDate = Date.valueOf("1971-01-02");
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor visitor = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints);
		
		int aInventoryItemID=2;
		int aDuplicates=6;
		String aName="book";
		String aAuthor="amelie";
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status aStatus=ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status.CheckedOut;
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem aType= ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem.Book;
		InventoryItem inventoryItem = new InventoryItem(aInventoryItemID, aDuplicates, aName, aAuthor, aStatus, aType);
		
		Reservation reservation = null;
		String error = null;
		
		reservation = service.createReservation(reservationId, startDate, endDate, visitor, inventoryItem);
		int numOfReservationsBefore = service.getAllReservations().size();
		
		try {
			service.deleteReservation(null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		int numOfReservationsAfter = service.getAllReservations().size();
		assertNotNull(reservation);
		assertEquals(numOfReservationsBefore, numOfReservationsAfter);
		assertEquals("Reservation ID is invalid!", error);
	}
	
	/*
	 * testDeleteReservationNullId()
	 * 
	 * if a reservation id used to call delete is an invalid number, then it will no be able to find
	 * an associated reservation to delete, and therefore no reservation will be deleted
	 * and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteReservationInvalidId() {
		
		Integer reservationId = RESERVATION_KEY;
		Date startDate = Date.valueOf("1971-01-01");
		Date endDate = Date.valueOf("1971-01-02");
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor visitor = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints);
		
		int aInventoryItemID=2;
		int aDuplicates=6;
		String aName="book";
		String aAuthor="amelie";
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status aStatus=ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status.CheckedOut;
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem aType= ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem.Book;
		InventoryItem inventoryItem = new InventoryItem(aInventoryItemID, aDuplicates, aName, aAuthor, aStatus, aType);
		
		Reservation reservation = null;
		String error = null;
		
		reservation = service.createReservation(reservationId, startDate, endDate, visitor, inventoryItem);
		int numOfReservationsBefore = service.getAllReservations().size();
		
		try {
			service.deleteReservation(-RESERVATION_KEY);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		int numOfReservationsAfter = service.getAllReservations().size();
		assertNotNull(reservation);
		assertEquals(numOfReservationsBefore, numOfReservationsAfter);
		assertEquals("Reservation ID is invalid!", error);
		
	}
	
	/*
	 * testDeleteReservationDNE()
	 * 
	 * if a reservation id is valid and not null but does not exist in the system, then it 
	 * will no be able to find an associated reservation to delete, and therefore no reservation 
	 * will be deleted and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteReservationDNE() {
		
		Integer reservationId = RESERVATION_KEY;
		Date startDate = Date.valueOf("1971-01-01");
		Date endDate = Date.valueOf("1971-01-02");
		
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor visitor = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints);
		
		int aInventoryItemID=2;
		int aDuplicates=6;
		String aName="book";
		String aAuthor="amelie";
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status aStatus=ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status.CheckedOut;
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem aType= ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem.Book;
		InventoryItem inventoryItem = new InventoryItem(aInventoryItemID, aDuplicates, aName, aAuthor, aStatus, aType);
		
		Reservation reservation = null;
		String error = null;
		
		reservation = service.createReservation(reservationId, startDate, endDate, visitor, inventoryItem);
		int numOfReservationsBefore = service.getAllReservations().size();
		
		try {
			service.deleteReservation(123);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		int numOfReservationsAfter = service.getAllReservations().size();
		
		assertNotNull(reservation);
		assertEquals(numOfReservationsBefore, numOfReservationsAfter);
		assertEquals("No such reservation!", error);
		
	}
	
	/*
	 * testDeleteAllReservation()
	 * 
	 * test deleting all reservations from the system, after this operation no reservations
	 * should be left in the system.
	 */
	
	@Test
	public void testDeleteAllReservation() {
		
		Integer reservationId = RESERVATION_KEY;
		Date startDate = Date.valueOf("1971-01-01");
		Date endDate = Date.valueOf("1971-01-02");
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor visitor = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints);
		
		int aInventoryItemID=2;
		int aDuplicates=6;
		String aName="book";
		String aAuthor="amelie";
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status aStatus=ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status.CheckedOut;
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem aType= ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem.Book;
		InventoryItem inventoryItem = new InventoryItem(aInventoryItemID, aDuplicates, aName, aAuthor, aStatus, aType);
		
		Reservation reservation = null;
		
		reservation = service.createReservation(reservationId, startDate, endDate, visitor, inventoryItem);
		
		try {
			service.deleteAllReservations();
		} catch (IllegalArgumentException e) {
			fail();
		}
		int numOfReservationsAfter = service.getAllReservations().size();
		assertNotNull(reservation);
		assertEquals(0, numOfReservationsAfter);
		assertNull(service.getAllReservations());
		
	}
	
	/*
	 * testDeleteAllReservationNoReservations()
	 * 
	 * if there are already no reservations in the system, then nothing changes 
	 * but good to notify the user that there where already no reservations to delete.
	 */
	
	
	@Test
	public void testDeleteAllReservationNoReservations() {
		
		String error = null;
		
		try {
			service.deleteAllReservations();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(service.getAllReservations());
		assertEquals("Library has no Reservations!", error);
		
	}
	
	/*
	 * testCreateReservationEndDateBeforeStartDate()
	 * 
	 * if a reservation is being created where it's start date is later than it's end date,
	 * then this is not physically possible, therefore creation of reservation will
	 * be terminated and the corresponding error message will be thrown.
	 */
	
	@Test
	public void testCreateReservationEndDateBeforeStartDate() {
		String error = null;
		
		Integer reservationId = RESERVATION_KEY;
		String nameVisitor = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		Visitor visitor = new Visitor(nameVisitor, username, addy, aLibraryCardID, aDemeritPoints);
		
		int aInventoryItemID=2;
		int aDuplicates=6;
		String aName="book";
		String aAuthor="amelie";
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status aStatus=ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status.CheckedOut;
		ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem aType= ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem.Book;
		InventoryItem inventoryItem = new InventoryItem(aInventoryItemID, aDuplicates, aName, aAuthor, aStatus, aType);
		
		
		Date startDate = Date.valueOf("1971-01-02");
		Date endDate = Date.valueOf("1971-01-01");
		
		Reservation reservation = null;
		
		lenient().when(reservationDao.existsById(anyInt())).thenReturn(true);
		
		try {
			reservation = service.createReservation(reservationId, startDate, endDate, visitor, inventoryItem);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNotNull(reservation);
		assertEquals("Reservation end date cannot be before reservation start date!", error);
	}
	

	
}
