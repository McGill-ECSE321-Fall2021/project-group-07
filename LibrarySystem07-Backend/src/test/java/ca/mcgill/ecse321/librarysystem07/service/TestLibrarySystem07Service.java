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

/*
 * assuming database works, when we call specific function return
 * this response for this function call (assume its the database)
 */

@ExtendWith(MockitoExtension.class)
public class TestLibrarySystem07Service {
	
	/*
	 * created in response to making function call
	 * mocking response from server
	 * pretend this response we get
	 * even tho we use the datatype, it actually doesn't do
	 * anything besides mock up for testing
	 */
	
	@Mock
	private LibrarianRepository librarianDao;
	@Mock
	private HeadLibrarianRepository headLibraianDto;
	
	@Mock
	private HeadLibrarianTimeSlotRepository headLibraianTimeSlotDto;
	@Mock
	private LibrarianTimeSlotRepository libraianTimeSlotDto;
	@Mock
	private ReservationRepository reservationDao;
	
	@InjectMocks
	private LibrarySystem07Service service;
	
	private static final Integer LIBRARIAN_KEY = 1234;
	private static final Integer HEAD_LIBRARIAN_KEY = 1235;
	private static final Integer NONEXISTING_KEY = 0;
	private static final Integer RESERVATION_KEY = 3445;
	
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
	    lenient().when(librarianDao.findLibrarianByLibraryCardID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
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
	    lenient().when(librarianDao.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * LIBRARIAN TESTS * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/*
	 * testCreateLibrarian()
	 * 
	 * Test creating librarian, if successful, then service should successfully add
	 * a librarian to the system. 
	 * Check to see if the object we created exists, and if it's attributes where properly set
	 */
	
	@Test
	public void testCreateLibrarian() {
		
		assertEquals(0, service.getAllLibrarians().size());

		Integer libraryCardId = 1239;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		try {
			librarian = service.createLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(librarian);
		assertEquals(libraryCardId, librarian.getLibraryCardID());
		assertEquals(name, librarian.getName());
		assertEquals(username, librarian.getUsername());
		assertEquals(address, librarian.getAddress());
	}
	
	/*
	 * testCreateLibrarianNull()
	 * 
	 * if any of the fields of librarian are null upon initialization,
	 * creation of a librarian will be terminated and corresponding error message will
	 * be thrown
	 */
	
	@Test
	public void testCreateLibrarianNull() {
		
		Integer libraryCardId = null;
		String name = null;
		String username = null;
		String address = null;
		String error = null;
		Librarian librarian = null;
		
		try {
			librarian = service.createLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(librarian);
		// check error
		assertEquals("Librarian ID is invalid! Librarian name is invalid! "
				+ "Username is invalid! Address is invalid!", error);
	}
	
	/*
	 * testCreateLibrarianNegative()
	 * 
	 * if the unique ID of librarian is negative, we cannot have this and thus no object
	 * will be created and an error will be thrown.
	 */
	
	@Test
	public void testCreateLibrarianNegative() {
		
		Integer libraryCardId = -1;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		String error = null;
		Librarian librarian = null;
		
		try {
			librarian = service.createLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(librarian);
		// check error
		assertEquals("Librarian ID is invalid!", error);
	}
	
	@Test
	public void testGetExistingLibrarian() {
		assertEquals(LIBRARIAN_KEY, service.getLibrarian(LIBRARIAN_KEY).getLibraryCardID());
	}

	@Test
	public void testGetNonExistingLibrarian() {
		assertNull(service.getLibrarian(NONEXISTING_KEY));
	}
	
	/*
	 * testDeleteLibrarian()
	 * 
	 * test delete specific librarian, librarian should no longer exist in the system
	 * and number of librarians in the system should go down by one.
	 */
	
	@Test
	public void testDeleteLibrarian() {

		Integer libraryCardId = 1239;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		int numOfLibrariansBefore = service.getAllLibrarians().size();
		
		try {
			service.deleteLibrarian(libraryCardId);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		int numOfLibrariansAfter = service.getAllLibrarians().size();
		
		assertNull(librarian);
		assertEquals(numOfLibrariansBefore - 1, numOfLibrariansAfter);
	}
	
	/*
	 * testDeleteLibrarianNullId()
	 * 
	 * if a librarian id is null when calling delete, then it will no be able to find
	 * an associated librarian to delete, and therefore no librarian will be deleted
	 * and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteLibrarianNullId() {

		Integer libraryCardId = 1239;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		String error = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		int numOfLibrariansBefore = service.getAllLibrarians().size();
		
		try {
			service.deleteLibrarian(null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		int numOfLibrariansAfter = service.getAllLibrarians().size();
		
		assertNotNull(librarian);
		assertEquals(numOfLibrariansBefore, numOfLibrariansAfter);
		assertEquals("Librarian ID is invalid!", error);
	}
	
	/*
	 * testDeleteLibrarianNullId()
	 * 
	 * if a librarian id used to call delete is an invalid number, then it will no be able to find
	 * an associated librarian to delete, and therefore no librarian will be deleted
	 * and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteLibrarianInvalidId() {

		Integer libraryCardId = 1239;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		String error = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		int numOfLibrariansBefore = service.getAllLibrarians().size();
		
		try {
			service.deleteLibrarian(-1239);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		int numOfLibrariansAfter = service.getAllLibrarians().size();
		
		assertNotNull(librarian);
		assertEquals(numOfLibrariansBefore, numOfLibrariansAfter);
		assertEquals("Librarian ID is invalid!", error);
	}
	
	/*
	 * testDeleteLibrarianDNE()
	 * 
	 * if a librarian id is valid and not null but does not exist in the system, then it 
	 * will no be able to find an associated librarian to delete, and therefore no librarian 
	 * will be deleted and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteLibrarianDNE() {
		
		Integer libraryCardId = 1239;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		String error = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		int numOfLibrariansBefore = service.getAllLibrarians().size();
		
		try {
			service.deleteLibrarian(333);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		int numOfLibrariansAfter = service.getAllLibrarians().size();
		
		assertNotNull(librarian);
		assertEquals(numOfLibrariansBefore, numOfLibrariansAfter);
		assertEquals("No such Librarian!", error);
	}
	
	/*
	 * testDeleteAllLibrarian()
	 * 
	 * test deleting all librarians from the system, after this operation no librarians
	 * should be left in the system.
	 */
	
	@Test
	public void testDeleteAllLibrarian() {

		Integer libraryCardId = 1239;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		try {
			service.deleteAllLibrarians();
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		int numOfLibrariansAfter = service.getAllLibrarians().size();
		
		assertNull(librarian);
		assertEquals(0, numOfLibrariansAfter);
		assertNull(service.getAllLibrarians());
	}
	
	/*
	 * testDeleteAllLibrarianNoLibrarians()
	 * 
	 * if there are already no librarians in the system, then nothing changes 
	 * but good to notify the user that there where already no librarians to delete.
	 */
	
	@Test
	public void testDeleteAllLibrarianNoLibrarians() {
		
		String error = null;
		
		try {
			service.deleteAllLibrarians();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Library has no Librarians!", error);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * HEAD LIBRARIAN TESTS  * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/*
	 * testCreateHeadLibrarian()
	 * 
	 * Test creating head librarian, if successful, then service should successfully add
	 * a head librarian to the system. 
	 * Check to see if the object we created exists, and if it's attributes where properly set
	 */

	@Test
	public void testCreateHeadLibrarian() {
		
		assertEquals(0, service.getAllHeadLibrarians().size());

		Integer libraryCardId = 1239;
		String name = "Nancy";
		String username = "Nancy334";
		String address = "746 Mont Royal";
		HeadLibrarian headLibrarian = null;
		
		try {
			headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(headLibrarian);
		assertEquals(libraryCardId, headLibrarian.getLibraryCardID());
		assertEquals(name, headLibrarian.getName());
		assertEquals(address, headLibrarian.getAddress());
		assertEquals(username, headLibrarian.getUsername());
	}
	
	/*
	 * testCreateHeadLibrarianNull()
	 * 
	 * if any of the fields of head librarian are null upon initialization,
	 * creation of a head librarian will be terminated and corresponding error message will
	 * be thrown
	 */
	
	@Test
	public void testCreateHeadLibrarianNull() {
		
		Integer libraryCardId = null;
		String name = null;
		String username = null;
		String address = null;
		HeadLibrarian headLibrarian = null;
		String error = null;
		
		try {
			headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(headLibrarian);
		// check error
		assertEquals("Head Librarian ID is invalid! Head Librarian name is invalid! "
				+ "Username is invalid! Address is invalid!", error);
	}
	
	/*
	 * testCreateHeadLibrarianNegative()
	 * 
	 * if the unique ID of head librarian is negative, we cannot have this and thus no object
	 * will be created and an error will be thrown
	 */
	
	@Test
	public void testCreateHeadLibrarianNegative() {
		
		Integer libraryCardId = -1;
		String name = "Nancy";
		String username = "Nancy334";
		String address = "746 Mont Royal";
		HeadLibrarian headLibrarian = null;
		String error = null;
		
		try {
			headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(headLibrarian);
		// check error
		assertEquals("Head Librarian ID is invalid!", error);
	}
	
	/*
	 * testCreateHeadLibrarianAlreadyExists()
	 * 
	 * Test if there already exists a head librarian, then we cannot make a new one
	 * until old one is removed (can only every have 1 head librarian in the system at a time)
	 * 
	 * creation of head librarian will be terminated and corresponding error will be thrown
	 * 
	 * To ensure the user does not override the existing head librarian, test is made
	 */
	
	@Test
	public void testCreateHeadLibrarianAlreadyExists() {
		
		assertEquals(0, service.getAllHeadLibrarians().size());
		
		Integer libraryCardId = 9;
		String name = "Nancy2";
		String username = "Nancy3342";
		String address = "746 Mont Royal Crecent";
		
		HeadLibrarian headLibrarian = null;
		
		headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		
		Integer libraryCardId_2 = 45678;
		String name_2 = "Geene";
		String username_2 = "Geene8989";
		String address_2 = "78 Cumberland";
		
		HeadLibrarian headLibrarian_2 = null;
		String error = null;
		
		try {
			headLibrarian_2 = service.createHeadLibrarian(name_2, username_2, address_2, libraryCardId_2);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(headLibrarian_2);
		// check error
		assertEquals("A Head Librarian already exists!", error);
	}
	
	/*
	 * testDeleteHeadLibrarian()
	 * 
	 * test delete specific librarian, librarian should no longer exist in the system
	 * and number of librarians in the system should go down by one.
	 */
	
	@Test
	public void testDeleteHeadLibrarian() {

		Integer libraryCardId = 900333;
		String name = "Nancy5";
		String username = "Nancy3345";
		String address = "745 Mont Royal Crecent";
		
		HeadLibrarian headLibrarian = null;
		
		headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		
		try {
			service.deleteHeadLibrarian();
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		int numOfHeadLibrariansAfter = service.getAllHeadLibrarians().size();
		
		assertNull(headLibrarian);
		assertEquals(0, numOfHeadLibrariansAfter);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * LIBRARIAN TIME SLOT TESTS * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/*
	 * testCreateLibrarianTimeSlot()
	 * 
	 * Test creating librarian time slot, if successful, then service should successfully add
	 * a librarian time slot to the system. 
	 * Check to see if the object we created exists, if it's attributes where properly set, 
	 * and also if the librarian associated with the time slot's attributes where properly set.
	 */
	
	@Test
	public void testCreateLibrarianTimeSlot() {
		
		Integer libraryCardId = 9000;
		String name = "Sophie";
		String username = "SophieBookworm3";
		String address = "679 King Edward";
		
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = 890;
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("10:30");
		
		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek = 
				ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		
		try {
			librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
					Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(librarianTimeSlot);
		
		// test to see if head librarian within head librarian time slot attributes are 
		// set properly
		assertEquals(libraryCardId, librarianTimeSlot.getLibrarian().getLibraryCardID());
		assertEquals(name, librarianTimeSlot.getLibrarian().getName());
		assertEquals(username, librarianTimeSlot.getLibrarian().getUsername());
		assertEquals(address, librarianTimeSlot.getLibrarian().getAddress());
		
		// check to see if time slot attributes are made peoperly
		assertEquals(dayOfTheWeek, librarianTimeSlot.getDayOfTheWeek());
		assertEquals(Time.valueOf(startTime).toString(), librarianTimeSlot.getStartTime().toString());
		assertEquals(Time.valueOf(endTime).toString(), librarianTimeSlot.getEndTime().toString());

	}
	
	/*
	 * testCreateLibrarianTimeSlotNull()
	 * 
	 * if any of the fields of librarian time slot are null upon initialization,
	 * creation of a librarian time slot will be terminated and corresponding error message will
	 * be thrown
	 */
	
	@Test
	public void testCreateLibrarianTimeSlotNull() {
		
		String error = null;
		
		Librarian librarian = null;
		assertEquals(0, service.getAllLibrarians().size());
		
		Integer librarianTimeSlotId = null;
		Time startTime = null;
		Time endTime = null;
		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek = null;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		
		try {
			librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
					startTime, endTime, dayOfTheWeek);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(librarianTimeSlot);
		assertEquals("Time slot id is invalid! Librarian is invalid! Timeslot start time is invalid! Timeslot end time is invalid! Invalid day of week!", error);
	}
	
	/*
	 * testCreateLibrarianTimeSlotEndTimeBeforeStartTime()
	 * 
	 * if a time slot is being created where it's start time is later than it's end time,
	 * then this is not physically possible, therefore creation of librarian time slot will
	 * be terminated and the corresponding error message will be thrown.
	 */
	
	@Test
	public void testCreateLibrarianTimeSlotEndTimeBeforeStartTime() {
		
		String error = null;
		
		Integer libraryCardId = 9001;
		String name = "Sophie1";
		String username = "SophieBookworm33";
		String address = "6795 King Edward";
		
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);

		Integer librarianTimeSlotId = 890;
		LocalTime startTime = LocalTime.parse("11:00");
		LocalTime endTime = LocalTime.parse("09:30");
		
		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek = 
				ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		
		try {
			librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
					Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(librarianTimeSlot);
		assertEquals("Timeslot end time cannot be before event start time!", error);
	}
	
	/*
	 * testCreateLibrarianTimeSlotAndLibrarianDoesNotExist()
	 * 
	 * if a time slot is being created without any librarian associated with it, 
	 * the creation of librarian time slot will be terminated and the corresponding 
	 * error message will be thrown.
	 */
	
	@Test
	public void testCreateLibrarianTimeSlotAndLibrarianDoesNotExist() {

		String error = null;
		
		Integer libraryCardId = 9001;
		String name = "Sophie1";
		String username = "SophieBookworm33";
		String address = "6795 King Edward";
		
		Librarian librarian = new Librarian();
		librarian.setLibraryCardID(libraryCardId);
		librarian.setName(name);
		librarian.setUsername(username);
		librarian.setAddress(address);

		assertEquals(0, service.getAllLibrarians().size());
		
		Integer librarianTimeSlotId = 543;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek = 
				ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		
		try {
			librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
					Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(librarianTimeSlot);
		// check error
		assertEquals("Librarian does not exist in the system!", error);
	}
	
	/*
	 * testDeleteLibrarianTimeSlot()
	 * 
	 * test delete specific librarian time slot, librarian time slot should no longer exist 
	 * in the system and number of librarian time slots in the system should go down by one.
	 */
	
	@Test
	public void testDeleteLibrarianTimeSlot() {
		
		Integer libraryCardId = 90011;
		String name = "Sophie11";
		String username = "SophieBookworm333";
		String address = "67953 King Edward";
		
		Librarian librarian = null;
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = 543;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek = 
				ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		int numOfLibrarianTSBefore = service.getAllLibrarianTimeSlots().size();
		
		try {
			service.deleteLibrarianTimeSlot(librarianTimeSlotId);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		int numOfLibrarianTSAfter = service.getAllLibrarianTimeSlots().size();
		
		assertNull(librarianTimeSlot);
		assertEquals(numOfLibrarianTSBefore - 1, numOfLibrarianTSAfter);
	}
	
	/*
	 * testDeleteLibrarianTimeSlotNullId()
	 * 
	 * if a librarian time slot id is null when calling delete, then it will no be able to find
	 * an associated librarian time slot to delete, and therefore no librarian time slot will be 
	 * deleted and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteLibrarianTimeSlotNullId() {
		
		String error = null;

		Integer libraryCardId = 90011;
		String name = "Sophie11";
		String username = "SophieBookworm333";
		String address = "67953 King Edward";
		
		Librarian librarian = null;
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = 543;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek = 
				ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		int numOfLibrarianTSBefore = service.getAllLibrarianTimeSlots().size();
		
		librarianTimeSlotId = null;
		
		try {
			service.deleteLibrarianTimeSlot(librarianTimeSlotId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		int numOfLibrariansTSAfter = service.getAllLibrarians().size();
		
		assertNotNull(librarianTimeSlot);
		assertEquals(numOfLibrarianTSBefore, numOfLibrariansTSAfter);
		assertEquals("Librarian Time Slot ID is invalid!", error);
	}
	
	/*
	 * testDeleteLibrarianTimeSlotInvalidId()
	 * 
	 * if a librarian time slot id used to call delete is an invalid number, then it will no be able to find
	 * an associated librarian time slot to delete, and therefore no librarian time slot will be deleted
	 * and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteLibrarianTimeSlotInvalidId() {
		
		String error = null;

		Integer libraryCardId = 90012;
		String name = "Sophie11";
		String username = "SophieBookworm333";
		String address = "67953 King Edward";
		
		Librarian librarian = null;
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = 544;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek = 
				ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		int numOfLibrarianTSBefore = service.getAllLibrarianTimeSlots().size();
		
		try {
			service.deleteLibrarianTimeSlot(-544);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		int numOfLibrarianTSAfter = service.getAllLibrarianTimeSlots().size();
		
		assertNotNull(librarianTimeSlot);
		assertEquals(numOfLibrarianTSBefore, numOfLibrarianTSAfter);
		assertEquals("Librarian Time Slot ID is invalid!", error);
	}
	
	/*
	 * testDeleteLibrarianTimeSlotDNE()
	 * 
	 * if a librarian time slot id is valid and not null but does not exist in the system, then it 
	 * will no be able to find an associated librarian time slot to delete, and therefore no librarian 
	 * time slot will be deleted and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteLibrarianTimeSlotDNE() {
		
		String error = null;
		
		Integer libraryCardId = 90013;
		String name = "Sophie11";
		String username = "SophieBookworm333";
		String address = "67953 King Edward";
		
		Librarian librarian = null;
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = 545;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek = 
				ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		int numOfLibrarianTSBefore = service.getAllLibrarianTimeSlots().size();
		
		try {
			service.deleteLibrarianTimeSlot(5555);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		int numOfLibrarianTSAfter = service.getAllLibrarianTimeSlots().size();
		
		assertNotNull(librarianTimeSlot);
		assertEquals(numOfLibrarianTSBefore, numOfLibrarianTSAfter);
		assertEquals("No such Librarian Time Slot!", error);
	}
	
	/*
	 * testDeleteAllLibrarianTimeSlots()
	 * 
	 * test deleting all librarian time slots from the system, after this operation no librarian
	 * time slots should be left in the system.
	 */
	
	@Test
	public void testDeleteAllLibrarianTimeSlots() {
		
		Integer libraryCardId = 90014;
		String name = "Sophie11";
		String username = "SophieBookworm333";
		String address = "67953 King Edward";
		
		Librarian librarian = null;
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = 546;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek = 
				ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		try {
			service.deleteAllLibrarianTimeSlots();
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		int numOfLibrarianTSAfter = service.getAllLibrarianTimeSlots().size();
		
		assertNull(librarianTimeSlot);
		assertEquals(0, numOfLibrarianTSAfter);
	}
	
	/*
	 * testDeleteAllLibrarianTimeSlotsNoLibrarians()
	 * 
	 * if there are already no librarian time slots in the system, then nothing changes 
	 * but good to notify the user that there where already no librarian time slots to delete.
	 */
	
	@Test
	public void testDeleteAllLibrarianTimeSlotsNoLibrarians() {
		
		String error = null;
		
		try {
			service.deleteAllLibrarianTimeSlots();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		int numOfLibrarianTSAfter = service.getAllLibrarianTimeSlots().size();
		
		assertEquals(0, numOfLibrarianTSAfter);
		assertEquals("No Librarian Time Slots exist!", error);
	}
	
	/*
	 * testDeleteLibrarianTimeSlotsByLibrarian()
	 * 
	 * test deleting a single librarian's time slots from the system, after this operation no 
	 * time slots for the given librarian should be left in the system.
	 */
	
	@Test
	public void testDeleteLibrarianTimeSlotsByLibrarian() {
		
		Integer libraryCardId = 90015;
		String name = "Sophie11";
		String username = "SophieBookworm333";
		String address = "67953 King Edward";
		
		Librarian librarian = null;
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = 547;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek = 
				ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		Integer librarianTimeSlotId_2 = 548;
		
		LibrarianTimeSlot librarianTimeSlot2 = null;
		librarianTimeSlot2 = service.createLibrarianTimeSlot(librarianTimeSlotId_2, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		try {
			service.deleteLibrarianSchedule(librarian);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNull(librarianTimeSlot);
		assertNull(librarianTimeSlot2);
	}
	
	/*
	 * testDeleteLibrarianTimeSlotsByLibrarianNull()
	 * 
	 * test deleting a single librarian's time slots from the system, if librarian input
	 * is null, then no librarian time slot will be deleted and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteLibrarianTimeSlotsByLibrarianNull() {
		
		String error = null;
		
		Integer libraryCardId = 90016;
		String name = "Sophie11";
		String username = "SophieBookworm333";
		String address = "67953 King Edward";
		
		Librarian librarian = null;
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = 547;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek = 
				ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		Integer librarianTimeSlotId_2 = 548;
		
		LibrarianTimeSlot librarianTimeSlot2 = null;
		librarianTimeSlot2 = service.createLibrarianTimeSlot(librarianTimeSlotId_2, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		librarian = null;
		
		try {
			service.deleteLibrarianSchedule(librarian);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("No Librarian exist!", error);
		assertNotNull(librarianTimeSlot);
		assertNotNull(librarianTimeSlot2);
	}
	
	/*
	 * testDeleteLibrarianTimeSlotsByLibrarianNoTS()
	 * 
	 * test deleting a single librarian's time slots from the system, if input librarian
	 * has no time slots, then no librarian time slot will be deleted and corresponding 
	 * error message will be thrown.
	 */
	
	@Test
	public void testDeleteLibrarianTimeSlotsByLibrarianNoTS() {
		
		String error = null;
		
		Integer libraryCardId = 90017;
		String name = "Sophie11";
		String username = "SophieBookworm333";
		String address = "67953 King Edward";
		
		Librarian librarian = null;
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		try {
			service.deleteLibrarianSchedule(librarian);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Librarian has no time slots!", error);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * HEAD LIBRARIAN TIME SLOT TESTS  * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/*
	 * testCreateLibrarianTimeSlot()
	 * 
	 * Test creating librarian time slot, if successful, then service should successfully add
	 * a librarian time slot to the system. 
	 * Check to see if the object we created exists, if it's attributes where properly set, 
	 * and also if the librarian associated with the time slot's attributes where properly set.
	 */

	@Test
	public void testCreateHeadLibrarianTimeSlot() {
		
		Integer libraryCardId = 9000;
		String name = "Nancy2";
		String username = "Nancy3342";
		String address = "746 Mont Royal Crecent";
		
		HeadLibrarian headLibrarian = null;
		
		headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		
		lenient().when(headLibraianDto.existsById(anyInt())).thenReturn(true);
		
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("10:30");
		Integer headLibrarianTimeSlotId = 987;
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		HeadLibrarianTimeSlot headLibrarianTimeSlot = null;
		
		try {
			headLibrarianTimeSlot = service.createHeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, 
					Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(headLibrarianTimeSlot);
		
		// test to see if head librarian within head librarian time slot attributes are 
		// set properly
		assertEquals(libraryCardId, headLibrarianTimeSlot.getHeadLibrarian().getLibraryCardID());
		assertEquals(name, headLibrarianTimeSlot.getHeadLibrarian().getName());
		assertEquals(username, headLibrarianTimeSlot.getHeadLibrarian().getUsername());
		assertEquals(address, headLibrarianTimeSlot.getHeadLibrarian().getAddress());
		
		// check to see if time slot attributes are made peoperly
		assertEquals(headLibrarianTimeSlotId, headLibrarianTimeSlot.getHeadLibrarianTimeSlotId());
		assertEquals(dayOfTheWeek, headLibrarianTimeSlot.getDayOfTheWeek());
		assertEquals(Time.valueOf(startTime).toString(), headLibrarianTimeSlot.getStartTime().toString());
		assertEquals(Time.valueOf(endTime).toString(), headLibrarianTimeSlot.getEndTime().toString());

	}
	
	/*
	 * testCreateHeadLibrarianTimeSlotNull()
	 * 
	 * if any of the fields of head librarian time slot are null upon initialization,
	 * creation of a head librarian time slot will be terminated and corresponding error message 
	 * will be thrown
	 */
	
	@Test
	public void testCreateHeadLibrarianTimeSlotNull() {
		
		String error = null;
		
		HeadLibrarian headLibrarian = null;
		assertEquals(0, service.getAllHeadLibrarians().size());
		
		Time startTime = null;
		Time endTime = null;
		DayOfTheWeek dayOfTheWeek = null;
		Integer headLibrarianTimeSlotId = null;
		
		HeadLibrarianTimeSlot headLibrarianTimeSlot = null;
		
		try {
			headLibrarianTimeSlot = service.createHeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, 
					startTime, endTime, dayOfTheWeek);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(headLibrarianTimeSlot);
		assertEquals("Time slot id is invalid! Head librarian is invalid! Timeslot start time is invalid! Timeslot end time is invalid! Invalid day of week!", error);
	}
	
	/*
	 * testCreateHeadLibrarianTimeSlotEndTimeBeforeStartTime()
	 * 
	 * if a time slot is being created where it's start time is later than it's end time,
	 * then this is not physically possible, therefore creation of head librarian time slot will
	 * be terminated and the corresponding error message will be thrown.
	 */
	
	@Test
	public void testCreateHeadLibrarianTimeSlotEndTimeBeforeStartTime() {
		
		String error = null;
		
		Integer libraryCardId = 9001;
		String name = "Nancy3";
		String username = "Nancy3343";
		String address = "7467 Crecent Hill";
		
		HeadLibrarian headLibrarian = null;
		
		headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		
		lenient().when(headLibraianDto.existsById(anyInt())).thenReturn(true);

		LocalTime startTime = LocalTime.parse("11:00");
		LocalTime endTime = LocalTime.parse("09:30");
		Integer headLibrarianTimeSlotId = 789;
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		HeadLibrarianTimeSlot headLibrarianTimeSlot = null;
		
		try {
			headLibrarianTimeSlot = service.createHeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, 
					Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(headLibrarianTimeSlot);
		assertEquals("Timeslot end time cannot be before event start time!", error);
	}
	
	/*
	 * testCreateHeadLibrarianTimeSlotAndHeadLibrarianDoesNotExist()
	 * 
	 * if a time slot is being created without any head librarian associated with it, 
	 * the creation of head librarian time slot will be terminated and the corresponding 
	 * error message will be thrown.
	 */
	
	@Test
	public void testCreateHeadLibrarianTimeSlotAndHeadLibrarianDoesNotExist() {

		String error = null;
		
		Integer libraryCardId = 9001;
		String name = "Nancy3";
		String username = "Nancy3343";
		String address = "7467 Crecent Hill";
		
		HeadLibrarian headLibrarian = new HeadLibrarian();
		headLibrarian.setLibraryCardID(libraryCardId);
		headLibrarian.setName(name);
		headLibrarian.setUsername(username);
		headLibrarian.setAddress(address);

		assertEquals(0, service.getAllHeadLibrarians().size());
		
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		Integer headLibrarianTimeSlotId = 789;
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		HeadLibrarianTimeSlot headLibrarianTimeSlot = null;
		
		try {
			headLibrarianTimeSlot = service.createHeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, 
					Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(headLibrarianTimeSlot);
		// check error
		assertEquals("Head Librarian does not exist in the system!", error);
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
		Integer reservationId = 4;
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
		
		Integer reservationId = null;
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
		Integer reservationId = -1;
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
		
		Integer reservationId = 4;
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
		
		Integer reservationId = 4;
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
		
		Integer reservationId = 4;
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
			service.deleteReservation(-4);
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
		
		Integer reservationId = 4;
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
		
		Integer reservationId = 4;
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
		
		Integer reservationId = 9001;
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


}
