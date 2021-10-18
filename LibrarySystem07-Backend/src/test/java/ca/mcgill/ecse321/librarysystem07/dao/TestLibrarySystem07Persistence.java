package ca.mcgill.ecse321.librarysystem07.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem07.model.*;
import ca.mcgill.ecse321.librarysystem07.model.ReservableItem.Status;
import ca.mcgill.ecse321.librarysystem07.model.ReservableItem.TypeOfReservableItem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibrarySystem07Persistence {
	
	@Autowired
	private VisitorRepository visitorRepository;
	
	@Autowired
	private ReservableItemRepository reservableItemRepository;

	@Autowired
	private NonReservableItemRepository nonReservableItemRepository;


	
	@AfterEach
	public void clearDatabase() {
		// Fisrt, we clear registrations to avoid exceptions due to inconsistencies
		visitorRepository.deleteAll();
		reservableItemRepository.deleteAll();
		nonReservableItemRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadVisitor() {
		
		Time startTime = new Time(9, 0, 0);
		Time endTime = new Time(20, 0, 0);
		Date day  = new Date(2021, 10, 17);
		TimeSlot.DayOfTheWeek weekday = TimeSlot.DayOfTheWeek.Sunday;
		TimeSlot openingHours = new TimeSlot(startTime, endTime, day, weekday);
		
		Time startTime1 = new Time(9, 0, 0);
		Time endTime1 = new Time(20, 0, 0);
		Date day1  = new Date(2021, 10, 18);
		TimeSlot.DayOfTheWeek weekday1 = TimeSlot.DayOfTheWeek.Monday;
		TimeSlot openingHours1 = new TimeSlot(startTime1, endTime1, day1, weekday1);

		Time startTime12 = new Time(9, 0, 0);
		Time endTime12 = new Time(20, 0, 0);
		Date day12  = new Date(2021, 10, 19);
		TimeSlot.DayOfTheWeek weekday12 = TimeSlot.DayOfTheWeek.Tuesday;
		TimeSlot openingHours12 = new TimeSlot(startTime12, endTime12, day12, weekday12);

		List<TimeSlot> openingHourss = new ArrayList<>();
		openingHourss.add(openingHours12);
		openingHourss.add(openingHours1);
		openingHourss.add(openingHours);
		
		int aLibraryCardIDD = 7654;
		
		
		String aName = "TestVisitor";
		String aUsername = "TestVisitor123";
		String aAddress = "4500 haha st"; 
		Library aLibrary = new Library("Lib", "mtl", openingHourss, aLibraryCardIDD);
		int aLibraryCardID = 12345; 
		int aDemeritPoints = 0;
		
		
		// First example for object save/load
		Visitor visitor = new Visitor(aName, aUsername, aAddress, aLibraryCardID, aLibrary, aDemeritPoints);
		// First example for attribute save/load
		visitorRepository.save(visitor);

		visitor = null;

		visitor = visitorRepository.findVisitorByLibrarianCardID(aLibraryCardID);
		assertNotNull(visitor);
		assertEquals(aLibraryCardID, visitor.getLibraryCardID());
	}
	
	@Test
	public void testPersistAndLoadReservableItem() {
		
		int aId = 345456;
		
		Time startTime = new Time(9, 0, 0);
		Time endTime = new Time(20, 0, 0);
		Date day  = new Date(2021, 10, 17);
		TimeSlot.DayOfTheWeek weekday = TimeSlot.DayOfTheWeek.Sunday;
		TimeSlot openingHours = new TimeSlot(startTime, endTime, day, weekday);
		
		Time startTime1 = new Time(9, 0, 0);
		Time endTime1 = new Time(20, 0, 0);
		Date day1  = new Date(2021, 10, 18);
		TimeSlot.DayOfTheWeek weekday1 = TimeSlot.DayOfTheWeek.Monday;
		TimeSlot openingHours1 = new TimeSlot(startTime1, endTime1, day1, weekday1);

		Time startTime12 = new Time(9, 0, 0);
		Time endTime12 = new Time(20, 0, 0);
		Date day12  = new Date(2021, 10, 19);
		TimeSlot.DayOfTheWeek weekday12 = TimeSlot.DayOfTheWeek.Tuesday;
		TimeSlot openingHours12 = new TimeSlot(startTime12, endTime12, day12, weekday12);

		List<TimeSlot> openingHourss = new ArrayList<>();
		openingHourss.add(openingHours12);
		openingHourss.add(openingHours1);
		openingHourss.add(openingHours);
				
		Library aLibrary = new Library("Lib", "mtl", openingHourss, aId);
		
		int aDuplicates = 1;
		String aName = "George of The Jungle"; 
		String aAuthor = "man";
		Status aStatus = ReservableItem.Status.Available;
		TypeOfReservableItem aReservableItem = ReservableItem.TypeOfReservableItem.Book;
			
		// First example for object save/load
		ReservableItem item = new ReservableItem(aId, aLibrary, aDuplicates, aName, aAuthor, aStatus, aReservableItem);
		// First example for attribute save/load
		reservableItemRepository.save(item);

		item = null;

		item = reservableItemRepository.findReservableItemById(aId);
		assertNotNull(item);
		assertEquals(aId, item.getId());
	}
	
	@Test
	public void testPersistAndLoadNonReservableItem() {
		
		int aId = 345456;
		
		Time startTime = new Time(9, 0, 0);
		Time endTime = new Time(20, 0, 0);
		Date day  = new Date(2021, 10, 17);
		TimeSlot.DayOfTheWeek weekday = TimeSlot.DayOfTheWeek.Sunday;
		TimeSlot openingHours = new TimeSlot(startTime, endTime, day, weekday);
		
		Time startTime1 = new Time(9, 0, 0);
		Time endTime1 = new Time(20, 0, 0);
		Date day1  = new Date(2021, 10, 18);
		TimeSlot.DayOfTheWeek weekday1 = TimeSlot.DayOfTheWeek.Monday;
		TimeSlot openingHours1 = new TimeSlot(startTime1, endTime1, day1, weekday1);

		Time startTime12 = new Time(9, 0, 0);
		Time endTime12 = new Time(20, 0, 0);
		Date day12  = new Date(2021, 10, 19);
		TimeSlot.DayOfTheWeek weekday12 = TimeSlot.DayOfTheWeek.Tuesday;
		TimeSlot openingHours12 = new TimeSlot(startTime12, endTime12, day12, weekday12);

		List<TimeSlot> openingHourss = new ArrayList<>();
		openingHourss.add(openingHours12);
		openingHourss.add(openingHours1);
		openingHourss.add(openingHours);
				
		Library aLibrary = new Library("Lib", "mtl", openingHourss, aId);
		
		NonReservableItem.TypeOfNonReservableItem aReservableItem = NonReservableItem.TypeOfNonReservableItem.Magazine;
			
		// First example for object save/load
		NonReservableItem item = new NonReservableItem(aId, aLibrary, aReservableItem);
		// First example for attribute save/load
		nonReservableItemRepository.save(item);

		item = null;

		item = nonReservableItemRepository.findNonReservableItemById(aId);
		assertNotNull(item);
		assertEquals(aId, item.getId());
	}


}
