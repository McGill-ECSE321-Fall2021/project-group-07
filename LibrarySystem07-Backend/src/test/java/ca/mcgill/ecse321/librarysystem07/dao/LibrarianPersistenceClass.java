package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.models.*;
import ca.mcgill.ecse321.library.models.Librarian;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibrarianPersistence{
    @Autowired
	private LibrarianRepository librarianRepository;
	
	
	@AfterEach
	public void clearDatabase() {
		librarianRepository.deleteAll();
		
	}
    @Test
    @Transactional
    public void testPersistAndLibrarian(){
        Librarian lib = new Librarian(); //initialize al 
        String name = "Sam";
        String username = "sam1";
        String address = "123 Dover Street";
        int libID = 123;


        Time startTime2 = new Time(10, 0, 0);
		Time endTime2 = new Time(12, 0, 0);
		Date aDate2 = new Date(2021, 11, 0);
        Date aDate3 = new Date(2021, 12, 0);
        TimeSlot ts = new TimeSlot(startTime2, endTime2, aDate3, TimeSlot.DayOfTheWeek.Tuesday);
		TimeSlot ts2 = new TimeSlot(startTime2, endTime2, aDate2, TimeSlot.DayOfTheWeek.Monday);
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>(); //to create a list of timeslots
		timeSlots.add(ts);
		timeSlots.add(ts2);
		long phoneNumber = 514-514-5141;
		Library testLibrary = new Library("name", "city", timeSlots, phoneNumber); //create a new library instance to be able to use visitor

        Time startTime = 12;
        Date date = 13;
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Monday;
        int timeSlotID = 321;
        List<TimeSlot> schedule = [TimeSlot(startTime,startTime,date,dayOfTheWeek,timeSlotID)]; //create basic schedule

        lib.setName(name); //write all info to persistance
        lib.UserRole.setUsername(username);
        lib.UserRole.setAddress(address);
        lib.setId(libID);
        lib.setLibrary(testLibrary);
        lib.setSchedule(schedule);

        LibrarianRepository.save(lib);

        lib = null;
        lib = findLibrarianByLibraryCardID(321); //read info from persistance
        assertNotNull(nR); //check to make sure everything is equal
        assertEquals(name, lib.getName());
        assertEquals(username, lib.UserRole.getUsername());
        assertEquals(address, lib.UserRole.getAddress());
        assertEquals(libID, lib.getLibrarianCardID());
        assertEquals(testLibrary, lib.getLibrary());
        assertEquals(schedule, lib.getSchedule());
    }
}