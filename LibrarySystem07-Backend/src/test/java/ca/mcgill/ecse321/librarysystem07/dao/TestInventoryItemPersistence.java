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
<<<<<<< HEAD
import ca.mcgill.ecse321.librarysystem07.model.ReservableItem.Status;
import ca.mcgill.ecse321.librarysystem07.model.ReservableItem.TypeOfReservableItem;
import ca.mcgill.ecse321.librarysystem07.model.TimeSlot.DayOfTheWeek;
=======
import ca.mcgill.ecse321.librarysystem07.model.InventoryItem.Status;
import ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem;
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestInventoryItemPersistence {
<<<<<<< HEAD
=======
	
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @AfterEach
    public void clearDataBase() {
        inventoryItemRepository.deleteAll();
    }


    @Test
<<<<<<< HEAD
    @Transactional
    public void testPersistAndLoadInventoryItem() {
=======
    public void testPersistAndLoadInventoryItem() {
    	
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
        int inventoryItemID = 1;
        int nDuplicates = 3;
        String itemName = "Mobey Dick";
        String itemAuthor = "Herman Melville";
<<<<<<< HEAD
        Status status = "Available";
        TypeOfItem type = "Book";

        String libraryName = "Redpath";
        String libraryCity = "Montreal";
        String libraryPhoneNumber = "5141234567"
        Library library = new Library(libraryName, libraryCity, libraryPhoneNumber);

        InventoryItem inventoryItem = new InventoryItem(inventoryItemID, nDuplicates, itemName, itemAuthor, status, type, library);
=======
        Status status = Status.Available;
        TypeOfItem type = TypeOfItem.Book;

//        String libraryName = "Redpath";
//        String libraryCity = "Montreal";
//        String libraryPhoneNumber = "5141234567";
//        Library library = new Library(libraryName, libraryCity, libraryPhoneNumber);

        InventoryItem inventoryItem = new InventoryItem(inventoryItemID, nDuplicates, itemName, itemAuthor, status, type);
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda

        inventoryItemRepository.save(inventoryItem);

        inventoryItem = null;
<<<<<<< HEAD
        inventoryItem = IncentoryItemRepository.findInventoryItemById(inventoryItemID);

        assertNotNull(item);
        assertEquals(inventoryItemID, inventoryItem.getId());
=======
        inventoryItem = inventoryItemRepository.findInventoryItemByInventoryItemID(inventoryItemID);

        assertNotNull(inventoryItem);
        assertEquals(inventoryItemID, inventoryItem.getInventoryItemID());
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
    }
}