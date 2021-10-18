package ca.mcgill.ecse321.librarysystem07.dao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem07.model.*;
import ca.mcgill.ecse321.librarysystem07.dao.LibrarySystem07Repository;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestTimeSlotPersistence {
	
	@Autowired
	private TimeslotRepository timeSlotRepository;
	
	
	@AfterEach
	public void clearDatabase() {		
		timeSlotRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadTimeSlot() {
		Time startTime = new Time(8, 0, 0);
		Time endTime = new Time(10, 0, 0);
		Date aDate = new Date(2021, 11, 0);
		TimeSlot ts = new TimeSlot(startTime, endTime, aDate, TimeSlot.DayOfTheWeek.Monday);
		
		timeSlotRepository.save(ts);
		int id = ts.getTimeSlotID();

		ts = null;
		ts = timeSlotRepository.findTimeSlotById(id);
		
		assertNotNull(ts);
		assertEquals(id, ts.getTimeSlotID());
	}
	
}
