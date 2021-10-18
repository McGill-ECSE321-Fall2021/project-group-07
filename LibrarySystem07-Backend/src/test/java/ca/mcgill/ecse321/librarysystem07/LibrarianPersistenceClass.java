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
    @Test
    @Transactional
    public void testPersistAndLibrarian(){
        Librarian lib = new Librarian();
        String name = "Sam";
        String username = "sam1";
        String address = "123 Dover Street";
        int libID = 123;
        Library testLibrary = new Library();
        Time startTime = 12;
        Date date = 13;
        DayOfTheWeek dayOfTheWeek = DayOfTheWeek.Monday;
        int timeSlotID = 321
        List<TimeSlot> schedule = [TimeSlot(startTime,startTime,date,dayOfTheWeek,timeSlotID)]

        lib.setName(name);
        lib.UserRole.setUsername(username);
        lib.UserRole.setAddress(address);
        lib.setId(libID);
        lib.setLibrary(testLibrary);
        lib.setSchedule(schedule);

        LibrarianRepository.save(lib);

        lib = null;
        lib = findLibrarianByLibraryCardID(321);
        assertNotNull(nR);
        assertEquals(name, lib.getName());
        assertEquals(username, lib.UserRole.getUsername());
        assertEquals(address, lib.UserRole.getAddress());
        assertEquals(libID, lib.getLibrarianCardID());
        assertEquals(testLibrary, lib.getLibrary());
        assertEquals(schedule, lib.getSchedule());
    }
}