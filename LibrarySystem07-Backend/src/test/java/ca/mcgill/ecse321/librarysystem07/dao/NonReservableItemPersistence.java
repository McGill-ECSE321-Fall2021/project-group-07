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
import ca.mcgill.ecse321.library.models.NonReservableItem;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestNonReservableItem{
    @Autowired
	private NonReservableItemRepository nonReservableItemRepository;
	
	
	@AfterEach
	public void clearDatabase() {
		nonReservableItemRepository.deleteAll();
		
	}
    @Test
    @Transactional
    public void testPersistAndNonReservableItem(){
        NonReservableItem nR = new NonReservableItem();
        int nRID = 1;

        Time startTime2 = new Time(10, 0, 0);//to create a list of timeslots
		Time endTime2 = new Time(12, 0, 0);
		Date aDate2 = new Date(2021, 11, 0);
		TimeSlot ts2 = new TimeSlot(startTime2, endTime2, aDate2, TimeSlot.DayOfTheWeek.Monday);
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>(); //list of TimeSlots
		timeSlots.add(ts);
		timeSlots.add(ts2);
		long phoneNumber = 514-514-5141;
		Library testLibrary = new Library("name", "city", timeSlots, phoneNumber); //create a new library instance to be able to use visitor

        TypeOfNonReservableItem type = TypeOfNonReservableItem.Magazine;

        nR.InventoryItem.setId(nRID);
        nR.setLibrary(testLibrary);
        nR.setType(type);

        NonReservableItemRepository.save(nR);

        nR = null;
        nR = NonReservableItem.findNonReservableItemById(Integer id);
        assertNotNull(nR);
        assertEquals(nRID, nR.getID());
        assertEquals(testLibrary, nR.getLibrary());
        assertEquals(type, nR.getType());
    }
}