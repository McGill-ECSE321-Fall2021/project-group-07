package ca.mcgill.ecse321.librarysystem07.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem07.model.Librarian;
import ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot;
import ca.mcgill.ecse321.librarysystem07.model.Library;
import ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibrarianTimeSlotPersistence {
	
	@Autowired
	private LibrarianTimeSlotRepository librarianTimeSlotRepository;
	
	@Autowired
	private LibrarianRepository librarianRepository;
		
	@AfterEach
	public void clearDatabase() {
		librarianTimeSlotRepository.deleteAll();
		librarianRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadLibrarianTimeSlot() {
		
//		String libName = "Westmount Library";
//		String city = "Montreal";
//		String phoneNumber = "514-678-0453";
//		Library library = new Library(libName, city, phoneNumber);
		
		String name = "Nancy";
		String username = "nancy";
		String address = "240 test street";
		int libraryCardId = 293;
		
		Librarian nancy = new Librarian(name, username, address, libraryCardId);
		librarianRepository.save(nancy);
		
		int librarianTimeSlotId = 89;
		Time startTime = new Time(8, 0, 0);
		Time endTime = new Time(12, 0, 0);
		DayOfTheWeek day = DayOfTheWeek.Monday;
		
		LibrarianTimeSlot librarianTimeSlot = new LibrarianTimeSlot(librarianTimeSlotId, nancy, startTime, endTime, day);
		
		librarianTimeSlotRepository.save(librarianTimeSlot);
		
		librarianTimeSlot = null;
		
		librarianTimeSlot = librarianTimeSlotRepository.findLibrarianTimeSlotByLibrarianTimeSlotId(librarianTimeSlotId);
		assertNotNull(librarianTimeSlot);
		assertEquals(librarianTimeSlot.getLibrarianTimeSlotId(), librarianTimeSlotId);
		
	}
}
