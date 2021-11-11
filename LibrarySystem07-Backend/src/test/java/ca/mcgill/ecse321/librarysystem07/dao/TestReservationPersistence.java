package ca.mcgill.ecse321.librarysystem07.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem07.model.InventoryItem;
import ca.mcgill.ecse321.librarysystem07.model.Library;
import ca.mcgill.ecse321.librarysystem07.model.Reservation;
import ca.mcgill.ecse321.librarysystem07.model.Visitor;
import ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status;
import ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestReservationPersistence {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private VisitorRepository visitorRepository;
	
	@Autowired
	private InventoryItemRepository inventoryItemRepository;
	
	@AfterEach
	public void clearDatabase() {
		reservationRepository.deleteAll();
		visitorRepository.deleteAll();
		inventoryItemRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadReservation() {
		
//		String libName = "Westmount Library";
//		String city = "Montreal";
//		String phoneNumber = "514-678-0453";
//		Library lib = new Library(libName, city, phoneNumber);
		
		String name = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 5743; 
		int aDemeritPoints = 1;

		Visitor sam = new Visitor(name, username, addy, aLibraryCardID, aDemeritPoints);
		visitorRepository.save(sam);
		
        int inventoryItemID = 199;
        int nDuplicates = 3;
        String itemName = "Mobey Dick";
        String itemAuthor = "Herman Melville";
        Status status = Status.Available;
        TypeOfItem type = TypeOfItem.Book;

        InventoryItem inventoryItem = new InventoryItem(inventoryItemID, nDuplicates, itemName, itemAuthor, status, type);
        inventoryItemRepository.save(inventoryItem);
		
		int reservationID = 98765;
		Date startDate = Date.valueOf("2021-5-6");
		Date endDate = Date.valueOf("2021-6-6");

		Reservation res = new Reservation(reservationID, startDate, endDate, sam, inventoryItem);
		reservationRepository.save(res);
		
		res = null;
		res = reservationRepository.findReservationByReservationId(reservationID);
		
		assertNotNull(res);
		assertEquals(reservationID, res.getReservationID());
		
//		res = null;
//		res = reservationRepository.findByInventoryItemAndVisitor(inventoryItem, sam);
//		
//		assertNotNull(res);
//		assertEquals(inventoryItem, res.getInventoryItem());
//		assertEquals(sam, res.getVisitor());

	}

}
