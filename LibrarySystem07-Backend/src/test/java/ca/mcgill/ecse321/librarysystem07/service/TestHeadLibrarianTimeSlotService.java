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
public class TestHeadLibrarianTimeSlotService {
	
	@Mock
	private HeadLibrarianTimeSlotRepository headLibrarianTimeSlotDao;
	
	@Mock
	private HeadLibrarianRepository headLibrarianDao;
	
	@InjectMocks
	private LibrarySystem07Service service;
	
	private static final Integer HEAD_LIBRARIAN_TIME_SLOT_KEY = 1234;
	
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
	    lenient().when(headLibrarianTimeSlotDao.findHeadLibrarianTimeSlotByHeadLibrarianTimeSlotId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(HEAD_LIBRARIAN_TIME_SLOT_KEY)) {
	            HeadLibrarianTimeSlot headLibrarianTimeSlot = new HeadLibrarianTimeSlot();
	            headLibrarianTimeSlot.setHeadLibrarianTimeSlotId(HEAD_LIBRARIAN_TIME_SLOT_KEY);
	            return headLibrarianTimeSlot;
	        } else {
	            return null;
	        }
	    });
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
	    lenient().when(headLibrarianTimeSlotDao.save(any(HeadLibrarianTimeSlot.class))).thenAnswer(returnParameterAsAnswer);
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
		
		lenient().when(headLibrarianDao.existsById(anyInt())).thenReturn(true);
		
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("10:30");
		Integer headLibrarianTimeSlotId = HEAD_LIBRARIAN_TIME_SLOT_KEY;
		
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
		
		lenient().when(headLibrarianDao.existsById(anyInt())).thenReturn(true);

		LocalTime startTime = LocalTime.parse("11:00");
		LocalTime endTime = LocalTime.parse("09:30");
		Integer headLibrarianTimeSlotId = HEAD_LIBRARIAN_TIME_SLOT_KEY;
		
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
		Integer headLibrarianTimeSlotId = HEAD_LIBRARIAN_TIME_SLOT_KEY;
		
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
	
	/*
	 * testDeleteHeadLibrarianTimeSlot()
	 * 
	 * test delete specific head librarian time slot, head librarian time slot should no longer exist 
	 * in the system and number of head librarian time slots in the system should go down by one.
	 */
	
	@Test
	public void testDeleteHeadLibrarianTimeSlot() {
		
		Integer libraryCardId = 9001;
		String name = "Nancy3";
		String username = "Nancy3343";
		String address = "7467 Crecent Hill";
		
		HeadLibrarian headLibrarian = null;
		
		headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		
		lenient().when(headLibrarianDao.existsById(anyInt())).thenReturn(true);

		LocalTime startTime = LocalTime.parse("11:00");
		LocalTime endTime = LocalTime.parse("15:30");
		Integer headLibrarianTimeSlotId = HEAD_LIBRARIAN_TIME_SLOT_KEY;
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		service.createHeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		try {
			service.deleteHeadLibrarianTimeSlot(headLibrarianTimeSlotId);
			
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		verify(headLibrarianTimeSlotDao, times(1)).deleteById(anyInt());
	}
	
	/*
	 * testDeleteHeadLibrarianTimeSlotNullId()
	 * 
	 * if a head librarian time slot id is null when calling delete, then it will no be able to find
	 * an associated head librarian time slot to delete, and therefore no head librarian time slot will be 
	 * deleted and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteHeadLibrarianTimeSlotNullId() {
		
		String error = null;
		
		Integer libraryCardId = 9001;
		String name = "Nancy3";
		String username = "Nancy3343";
		String address = "7467 Crecent Hill";
		
		HeadLibrarian headLibrarian = null;
		
		headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		
		lenient().when(headLibrarianDao.existsById(anyInt())).thenReturn(true);

		LocalTime startTime = LocalTime.parse("11:00");
		LocalTime endTime = LocalTime.parse("15:30");
		Integer headLibrarianTimeSlotId = HEAD_LIBRARIAN_TIME_SLOT_KEY;
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		service.createHeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		headLibrarianTimeSlotId = null;
		
		try {
			service.deleteHeadLibrarianTimeSlot(headLibrarianTimeSlotId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		verify(headLibrarianTimeSlotDao, never()).delete(any(HeadLibrarianTimeSlot.class));
		assertEquals("Head Librarian Time Slot ID is invalid!", error);
	}
	
	/*
	 * testDeleteLibrarianTimeSlotInvalidId()
	 * 
	 * if a head librarian time slot id used to call delete is an invalid number, then it will no be able to find
	 * an associated head librarian time slot to delete, and therefore no head librarian time slot will be deleted
	 * and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteHeadLibrarianTimeSlotInvalidId() {
		
		String error = null;
		
		Integer libraryCardId = 9001;
		String name = "Nancy3";
		String username = "Nancy3343";
		String address = "7467 Crecent Hill";
		
		HeadLibrarian headLibrarian = null;
		
		headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		
		lenient().when(headLibrarianDao.existsById(anyInt())).thenReturn(true);

		LocalTime startTime = LocalTime.parse("11:00");
		LocalTime endTime = LocalTime.parse("15:30");
		Integer headLibrarianTimeSlotId = HEAD_LIBRARIAN_TIME_SLOT_KEY;
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		service.createHeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		headLibrarianTimeSlotId = -HEAD_LIBRARIAN_TIME_SLOT_KEY;
		
		try {
			service.deleteHeadLibrarianTimeSlot(headLibrarianTimeSlotId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		verify(headLibrarianTimeSlotDao, never()).delete(any(HeadLibrarianTimeSlot.class));
		assertEquals("Head Librarian Time Slot ID is invalid!", error);
	}
	
	/*
	 * testDeleteHeadLibrarianTimeSlotDNE()
	 * 
	 * if a head librarian time slot id is valid and not null but does not exist in the system, then it 
	 * will no be able to find an associated head librarian time slot to delete, and therefore no head librarian 
	 * time slot will be deleted and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteHeadLibrarianTimeSlotDNE() {
		
		String error = null;
		
		Integer libraryCardId = 9001;
		String name = "Nancy3";
		String username = "Nancy3343";
		String address = "7467 Crecent Hill";
		
		HeadLibrarian headLibrarian = null;
		
		headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		
		lenient().when(headLibrarianDao.existsById(anyInt())).thenReturn(true);

		LocalTime startTime = LocalTime.parse("11:00");
		LocalTime endTime = LocalTime.parse("15:30");
		Integer headLibrarianTimeSlotId = HEAD_LIBRARIAN_TIME_SLOT_KEY;
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		service.createHeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);
		
		headLibrarianTimeSlotId = HEAD_LIBRARIAN_TIME_SLOT_KEY - 1;
		
		try {
			service.deleteHeadLibrarianTimeSlot(headLibrarianTimeSlotId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		verify(headLibrarianTimeSlotDao, never()).delete(any(HeadLibrarianTimeSlot.class));
		assertEquals("No such Head Librarian Time Slot!", error);
	}
	
	/*
	 * testDeleteAllHeadLibrarianTimeSlots()
	 * 
	 * test deleting all head librarian time slots from the system, after this operation no head 
	 * librarian time slots should be left in the system.
	 * 
	 * must also mock number of elements within the repository as this is necessary to pass
	 * first error test
	 * 
	 * mocking deleteAll output, we check to see if within the service subroutine delete all 
	 * was called, if it was then all the librarian time slots should be deleted
	 */
	
	@Test
	public void testDeleteAllHeadLibrarianTimeSlots() {
		
		Integer libraryCardId = 9001;
		String name = "Nancy3";
		String username = "Nancy3343";
		String address = "7467 Crecent Hill";
		
		HeadLibrarian headLibrarian = null;
		headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		
		lenient().when(headLibrarianDao.existsById(anyInt())).thenReturn(true);

		LocalTime startTime = LocalTime.parse("11:00");
		LocalTime endTime = LocalTime.parse("15:30");
		Integer headLibrarianTimeSlotId = HEAD_LIBRARIAN_TIME_SLOT_KEY;
		
		DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Friday;
		
		service.createHeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);	
		
		Integer libraryCardId2 = 9001;
		
		HeadLibrarian headLibrarian2 = null;
		headLibrarian2 = service.createHeadLibrarian(name, username, address, libraryCardId2);
		
		lenient().when(headLibrarianDao.existsById(anyInt())).thenReturn(true);

		Integer headLibrarianTimeSlotId2 = HEAD_LIBRARIAN_TIME_SLOT_KEY + 1;
		
		service.createHeadLibrarianTimeSlot(headLibrarianTimeSlotId2, headLibrarian2, 
				Time.valueOf(startTime), Time.valueOf(endTime), dayOfTheWeek);	
		
		when(headLibrarianTimeSlotDao.count()).thenReturn((long) 2);
	    
		try {
			service.deleteAllHeadLibrarianTimeSlots();
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		verify(headLibrarianTimeSlotDao).deleteAll();
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
			service.deleteAllHeadLibrarianTimeSlots();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		verify(headLibrarianTimeSlotDao, never()).delete(any(HeadLibrarianTimeSlot.class));
		assertEquals("No Head Librarian Time Slots exist!", error);
	}

}
