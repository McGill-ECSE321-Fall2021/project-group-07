package ca.mcgill.ecse321.librarysystem07.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem07.model.*;
import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarianTimeSlot.DayOfTheWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestHeadLibrarianTimeSlotPersistence {

	@Autowired
	private HeadLibrarianTimeSlotRepository headLibrarianTimeSlotRepository;
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
	
	@AfterEach
	public void clearDatabase() {
		
		headLibrarianTimeSlotRepository.deleteAll();
		headLibrarianRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarianTimeSlot() {
		
		int headLibrarianTimeSlotId = 123;
		
//		String libraryName = "Redpath";
//        String libraryCity = "Montreal";
//        String libraryPhoneNumber = "5141234567";
//        Library library = new Library(libraryName, libraryCity, libraryPhoneNumber);
		
		String name = "Frances";
		String username = "frank334";
		String address = "240 street rd";
		int libraryCardId = 667890;
		HeadLibrarian headLibrarian = new HeadLibrarian(name, username, address, libraryCardId);
		
//		List<UserRole> haha = new ArrayList<UserRole>();
//		haha.add(headLibrarian);
//		library.setUsers(haha);
		
		headLibrarianRepository.save(headLibrarian);
		
		Time aStartTime = new Time(8, 0, 0);
		Time aEndTime = new Time(21, 0, 0);
		DayOfTheWeek aDayOfTheWeek= HeadLibrarianTimeSlot.DayOfTheWeek.Friday;
				
		HeadLibrarianTimeSlot headLibrarianTimeSlot = new HeadLibrarianTimeSlot(headLibrarianTimeSlotId, headLibrarian, aStartTime, aEndTime, aDayOfTheWeek);
		
//		List<HeadLibrarianTimeSlot> slotsss = new ArrayList<HeadLibrarianTimeSlot>();
//		slotsss.add(headLibrarianTimeSlot);
//		library.setHeadLibrarianTimeSlots(slotsss);
		
		headLibrarianTimeSlotRepository.save(headLibrarianTimeSlot);
		
		headLibrarianTimeSlot = null;
		headLibrarianTimeSlot = headLibrarianTimeSlotRepository.findHeadLibrarianTimeSlotByHeadLibrarianTimeSlotId(headLibrarianTimeSlotId);
	
        assertNotNull(headLibrarianTimeSlot);
        assertEquals(headLibrarianTimeSlotId, headLibrarianTimeSlot.getHeadLibrarianTimeSlotId());

	}
}
