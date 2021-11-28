package ca.mcgill.ecse321.librarysystem07.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

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
import ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek;

/*
 * assuming database works, when we call specific function return
 * this response for this function call (assume its the database)
 */

@ExtendWith(MockitoExtension.class)
public class TestLibrarianTimeSlotService {
	
	@Mock
	private LibrarianTimeSlotRepository librarianTimeSlotDao;
	
	@Mock
	private LibrarianRepository librarianDao;
	
	@InjectMocks
	private LibrarySystem07Service service;
	
	private static final Integer LIBRARIAN_TIME_SLOT_KEY = 1234;
	
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
	    lenient().when(librarianTimeSlotDao.findLibrarianTimeSlotByLibrarianTimeSlotId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(LIBRARIAN_TIME_SLOT_KEY)) {
	            LibrarianTimeSlot librarianTimeSlot = new LibrarianTimeSlot();
	            librarianTimeSlot.setLibrarianTimeSlotId(LIBRARIAN_TIME_SLOT_KEY);
	            return librarianTimeSlot;
	        } else {
	            return null;
	        }
	    });
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
	    lenient().when(librarianTimeSlotDao.save(any(LibrarianTimeSlot.class))).thenAnswer(returnParameterAsAnswer);
	    lenient().when(librarianDao.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
	    
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
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
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
		DayOfTheWeek dayOfTheWeek = null;
		
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
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
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
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		
		try {
			librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
					Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(librarianTimeSlot);
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
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		try {
			service.deleteLibrarianTimeSlot(librarianTimeSlotId);
			
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		verify(librarianTimeSlotDao, times(1)).delete(any(LibrarianTimeSlot.class));
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
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		librarianTimeSlotId = null;
		
		try {
			service.deleteLibrarianTimeSlot(librarianTimeSlotId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		verify(librarianTimeSlotDao, never()).delete(any(LibrarianTimeSlot.class));
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
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		librarianTimeSlotId = -LIBRARIAN_TIME_SLOT_KEY;
		
		try {
			service.deleteLibrarianTimeSlot(librarianTimeSlotId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		verify(librarianTimeSlotDao, never()).delete(any(LibrarianTimeSlot.class));
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
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY - 1;
		
		try {
			service.deleteLibrarianTimeSlot(librarianTimeSlotId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		verify(librarianTimeSlotDao, never()).delete(any(LibrarianTimeSlot.class));
		assertEquals("No such Librarian Time Slot!", error);
	}
	
	/*
	 * testDeleteAllLibrarianTimeSlots()
	 * 
	 * test deleting all librarian time slots from the system, after this operation no librarian
	 * time slots should be left in the system.
	 * 
	 * must also mock number of elements within the repository as this is necessary to pass
	 * first error test
	 * 
	 * mocking deleteAll output, we check to see if within the service subroutine delete all 
	 * was called, if it was then all the librarian time slots should be deleted
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
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		Integer libraryCardId2 = 90014;
		Librarian librarian2 = null;
		librarian2 = service.createLibrarian(name, username, address, libraryCardId2);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId2 = LIBRARIAN_TIME_SLOT_KEY + 1;
		
		service.createLibrarianTimeSlot(librarianTimeSlotId2, librarian2, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		when(librarianTimeSlotDao.count()).thenReturn((long) 2);
	    
		try {
			service.deleteAllLibrarianTimeSlots();
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		verify(librarianTimeSlotDao).deleteAll();
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
		
		verify(librarianTimeSlotDao, never()).delete(any(LibrarianTimeSlot.class));
		assertEquals("No Librarian Time Slots exist!", error);
	}
	
	/*
	 * testDeleteLibrarianTimeSlotsByLibrarian()
	 * 
	 * test deleting a single librarian's time slots from the system, after this operation no 
	 * time slots for the given librarian should be left in the system. 
	 * 
	 * Using mochito methods to mock findLibrarianTimeSlotByLibrarian, so that when a Librarian
	 * is used to delete time slots, it will be able to find the corresponding time slots
	 * 
	 * in the service class, findLibrarianTimeSlotByLibrarian is used first to find all instances
	 * of time slots with a given librarian, then delete method is called on it
	 * 
	 * in this case, the delete method would be called 2 times, one for each time slot. Verifying
	 * that the delete method within the service call was called 2 times on a LibrarianTimeSlot
	 * object assures the objects have been deleted, without the need to actually create them thanks
	 * to mochito
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
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		Integer librarianTimeSlotId2 = LIBRARIAN_TIME_SLOT_KEY + 1;
		
		LibrarianTimeSlot librarianTimeSlot2 = null;
		librarianTimeSlot2 = service.createLibrarianTimeSlot(librarianTimeSlotId2, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		ArrayList<LibrarianTimeSlot> all = new ArrayList<LibrarianTimeSlot>();
		all.add(librarianTimeSlot);
		all.add(librarianTimeSlot2);
		lenient().when(librarianTimeSlotDao.findLibrarianTimeSlotByLibrarian(any(Librarian.class))).thenReturn(all);
		
		try {
			service.deleteLibrarianSchedule(librarian);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		verify(librarianTimeSlotDao, times(2)).delete(any(LibrarianTimeSlot.class));
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
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		Integer librarianTimeSlotId_2 = 548;
		
		service.createLibrarianTimeSlot(librarianTimeSlotId_2, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		librarian = null;
		
		try {
			service.deleteLibrarianSchedule(librarian);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		verify(librarianTimeSlotDao, never()).delete(any(LibrarianTimeSlot.class));
		assertEquals("No Librarian exist!", error);
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
		
		verify(librarianTimeSlotDao, never()).delete(any(LibrarianTimeSlot.class));
		assertEquals("Librarian has no time slots!", error);
	}
	
	/**
	 * Test update librarian address with valid address
	 */
	@Test
	public void testUpdateLibrarianTimeSlotStartTime() {
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		LocalTime newStartTime = LocalTime.parse("09:00");

		try {
			service.updateLibrarianTimeSlotStartTime(librarianTimeSlot, Time.valueOf(newStartTime));
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(Time.valueOf(newStartTime).toString(), librarianTimeSlot.getStartTime().toString());
	}
	
	/**
	 * Test update librarian time slot without any time slot
	 */
	@Test
	public void testUpdateLibrarianTimeSlotStartTimeNoTimeSlot() {
		
		String error = null;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		
		LocalTime startTimeUpdate = LocalTime.parse("09:30");
		
		try {
			service.updateLibrarianTimeSlotStartTime(librarianTimeSlot, Time.valueOf(startTimeUpdate));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Librarian time slot is null.", error);
	}
	
	/**
	 * Test update librarian time slot without any start time
	 */
	@Test
	public void testUpdateLibrarianTimeSlotStartTimeNoStartTime() {
		
		String error = null;
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		Time updateStartTime = null;
		
		try {
			service.updateLibrarianTimeSlotStartTime(librarianTimeSlot, updateStartTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Nothing to update!", error);
	}
	
	/**
	 * Test update librarian time slot with same start time
	 */
	@Test
	public void testUpdateLibrarianTimeSlotStartTimeSameStartTime() {
		
		String error = null;
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		LocalTime updateStartTime = startTime;
		
		try {
			service.updateLibrarianTimeSlotStartTime(librarianTimeSlot, Time.valueOf(updateStartTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Nothing to update!", error);
	}
	
	/**
	 * Test update librarian time slot with start time after end time
	 */
	@Test
	public void testUpdateLibrarianTimeSlotStartTimeStartTimeAfterEndTime() {
		
		String error = null;
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		LocalTime updateStartTime = LocalTime.parse("18:30");
		
		try {
			service.updateLibrarianTimeSlotStartTime(librarianTimeSlot, Time.valueOf(updateStartTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Start time cannot be after end time.", error);
	}
	
	/**
	 * Test update librarian address with valid address
	 */
	@Test
	public void testUpdateLibrarianTimeSlotEndTime() {
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		LocalTime newEndTime = LocalTime.parse("18:30");

		try {
			service.updateLibrarianTimeSlotEndTime(librarianTimeSlot, Time.valueOf(newEndTime));
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(Time.valueOf(newEndTime).toString(), librarianTimeSlot.getEndTime().toString());
	}
	
	/**
	 * Test update librarian time slot without any time slot
	 */
	@Test
	public void testUpdateLibrarianTimeSlotEndTimeNoTimeSlot() {
		
		String error = null;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		
		LocalTime endTimeUpdate = LocalTime.parse("18:30");
		
		try {
			service.updateLibrarianTimeSlotEndTime(librarianTimeSlot, Time.valueOf(endTimeUpdate));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Librarian time slot is null.", error);
	}
	
	/**
	 * Test update librarian time slot without any time slot
	 */
	@Test
	public void testUpdateLibrarianTimeSlotEndTimeNoEndTime() {
		
		String error = null;
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		Time updateEndTime = null;
		
		try {
			service.updateLibrarianTimeSlotEndTime(librarianTimeSlot, updateEndTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Nothing to update!", error);
	}
	
	/**
	 * Test update librarian time slot with same end time
	 */
	@Test
	public void testUpdateLibrarianTimeSlotEndTimeSameEndTime() {
		
		String error = null;
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		LocalTime updateEndTime = endTime;
		
		try {
			service.updateLibrarianTimeSlotEndTime(librarianTimeSlot, Time.valueOf(updateEndTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Nothing to update!", error);
	}
	
	/**
	 * Test update librarian time slot with end time before start time
	 */
	@Test
	public void testUpdateLibrarianTimeSlotEndTimeEndTimeBeforeStartTime() {
		
		String error = null;
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		LocalTime updateEndTime = LocalTime.parse("08:00");
		
		try {
			service.updateLibrarianTimeSlotEndTime(librarianTimeSlot, Time.valueOf(updateEndTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("End time cannot be before start time.", error);
	}
	
	/**
	 * Test update librarian address with valid address
	 */
	@Test
	public void testUpdateLibrarianTimeSlotDay() {
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		DayOfTheWeek updateDayOfTheWeek = DayOfTheWeek.Thursday;

		try {
			service.updateLibrarianTimeSlotDayOfWeek(librarianTimeSlot, updateDayOfTheWeek);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(updateDayOfTheWeek.toString(), librarianTimeSlot.getDayOfTheWeek().toString());
	}
	
	/**
	 * Test update librarian time slot without any time slot
	 */
	@Test
	public void testUpdateLibrarianTimeSlotDayNoTimeSlot() {
		
		String error = null;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		
		DayOfTheWeek updateDayOfTheWeek = DayOfTheWeek.Thursday;
		
		try {
			service.updateLibrarianTimeSlotDayOfWeek(librarianTimeSlot, updateDayOfTheWeek);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Librarian time slot is null.", error);
	}
	
	/**
	 * Test update librarian time slot without any time slot
	 */
	@Test
	public void testUpdateLibrarianTimeSlotDayNoDay() {
		
		String error = null;
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		DayOfTheWeek updateDayOfTheWeek = null;
		
		try {
			service.updateLibrarianTimeSlotDayOfWeek(librarianTimeSlot, updateDayOfTheWeek);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Nothing to update!", error);
	}
	
	/**
	 * Test update librarian time slot same day of week
	 */
	@Test
	public void testUpdateLibrarianTimeSlotDaySameDay() {
		
		String error = null;
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		DayOfTheWeek updateDayOfTheWeek = DayOfTheWeek.Friday;
		
		try {
			service.updateLibrarianTimeSlotDayOfWeek(librarianTimeSlot, updateDayOfTheWeek);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Nothing to update!", error);
	}
	
	/**
	 * Test update librarian time slot with new valid librarian
	 */
	@Test
	public void testUpdateLibrarianTimeSlotLibrarian() {
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		Integer libraryCardId_update = 124;
		String name_update = "Greg";
		String username_update = "GregTheMan88";
		String address_update = "56 Durocher";
		Librarian librarian_update = null;
		
		librarian_update = service.createLibrarian(name_update, username_update, address_update, libraryCardId_update);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		try {
			service.updateLibrarianTimeSlotLibrarian(librarianTimeSlot, librarian_update);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(librarian_update.getLibraryCardID(), librarianTimeSlot.getLibrarian().getLibraryCardID());
	}
	
	/**
	 * Test update librarian time slot without any time slot
	 */
	@Test
	public void testUpdateLibrarianTimeSlotLibrarianNoTimeSlot() {
		
		String error = null;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		
		Integer libraryCardId_update = 124;
		String name_update = "Greg";
		String username_update = "GregTheMan88";
		String address_update = "56 Durocher";
		Librarian librarian_update = null;
		
		librarian_update = service.createLibrarian(name_update, username_update, address_update, libraryCardId_update);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		try {
			service.updateLibrarianTimeSlotLibrarian(librarianTimeSlot, librarian_update);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Librarian time slot is null.", error);
	}
	
	/**
	 * Test update librarian time slot without any librarian
	 */
	@Test
	public void testUpdateLibrarianTimeSlotLibrarianNoLibrarian() {
		
		String error  = null;
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		Librarian updateLibrarian = null;
		
		try {
			service.updateLibrarianTimeSlotLibrarian(librarianTimeSlot, updateLibrarian);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Nothing to update!", error);
	}
	
	/**
	 * Test update librarian time slot without a librarian existing in the system
	 */
	@Test
	public void testUpdateLibrarianTimeSlotLibrarianLibrarianDNE() {
		
		String error = null;
		
		Integer libraryCardId = 123;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		librarian = service.createLibrarian(name, username, address, libraryCardId);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer librarianTimeSlotId = LIBRARIAN_TIME_SLOT_KEY;
		LocalTime startTime = LocalTime.parse("10:00");
		LocalTime endTime = LocalTime.parse("17:30");
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		LibrarianTimeSlot librarianTimeSlot = null;
		librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		Integer libraryCardId_update = 124;
		String name_update = "Greg";
		String username_update = "GregTheMan88";
		String address_update = "56 Durocher";
		
		Librarian librarian_update = new Librarian();
		
		librarian_update.setLibraryCardID(libraryCardId_update);
		librarian_update.setName(name_update);
		librarian_update.setUsername(username_update);
		librarian_update.setAddress(address_update);
		
		lenient().when(librarianDao.existsById(anyInt())).thenReturn(false);
		
		try {
			service.updateLibrarianTimeSlotLibrarian(librarianTimeSlot, librarian_update);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Librarian does not exist in the system!", error);
	}

}
