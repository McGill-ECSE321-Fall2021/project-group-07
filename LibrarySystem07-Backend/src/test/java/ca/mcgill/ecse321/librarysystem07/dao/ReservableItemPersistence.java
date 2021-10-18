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
import ca.mcgill.ecse321.library.models.ReservableItem;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestReservableItem{
    @Autowired
	private ReservableItemRepository reservableItemRepository;
	
	
	@AfterEach
	public void clearDatabase() {
		reservableItemRepository.deleteAll();
		
	}
    @Test
    @Transactional
    public void testPersistAndReservableItem(){
        NonReservableItem rI = new ReservableItem();
        int rIID = 1;
        
        Time startTime2 = new Time(10, 0, 0); //create a new library instance to be able to use visitor
		Time endTime2 = new Time(12, 0, 0);
		Date aDate2 = new Date(2021, 11, 0);
		TimeSlot ts2 = new TimeSlot(startTime2, endTime2, aDate2, TimeSlot.DayOfTheWeek.Monday);
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>(); //to create a list of timeslots
		timeSlots.add(ts);
		timeSlots.add(ts2);
		long phoneNumber = 514-514-5141;
		Library testLibrary = new Library("name", "city", timeSlots, phoneNumber); 

        int duplicateNum = 3;
        String name = "Hello, World!" //name of book
        String author = "Liamo Pennimpede" //author name
        Status status = Status.OnReserve; //Status of book
        TypeOfReservableItem type = TypeOfReservableItem.CD; //CD

        rI.InventoryItem.setId(rIID); //write
        rI.setLibrary(testLibrary);
        rI.setDuplicates(duplicateNum);
        rI.setName(name);
        rI.setAuthor(author);
        rI.setStatus(status);
        rI.setType(type);

        NonReservableItemRepository.save(rI);

        rI = null;
        rI = ReservableItem.findReservableItemById(Integer id); //read
        assertNotNull(rI);
        assertEquals(aID, rI.getID());
        assertEquals(testLibrary, rI.getLibrary());
        assertEquals(duplicateNum, rI.getDuplicates());
        assertEquals(name, rI.getName());
        assertEquals(author, rI.getAuthor());
        assertEquals(status, rI.getStatus());
        assertEquals(type, rI.getType());
    }
}
