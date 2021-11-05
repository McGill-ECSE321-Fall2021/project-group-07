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
import ca.mcgill.ecse321.librarysystem07.model.TimeSlot.DayOfTheWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestInventoryItemPersistence {
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @AfterEach
    public void clearDataBase() {
        inventoryItemRepository.deleteAll();
    }


    @Test
    @Transactional
    public void testPersistAndLoadInventoryItem() {
        int inventoryItemID = 1;
        int nDuplicates = 3;
        String itemName = "Mobey Dick";
        String itemAuthor = "Herman Melville";
        Status status = "Available";
        TypeOfItem type = "Book";

        String libraryName = "Redpath";
        String libraryCity = "Montreal";
        String libraryPhoneNumber = "5141234567"
        Library library = new Library(libraryName, libraryCity, libraryPhoneNumber);

        InventoryItem inventoryItem = new InventoryItem(inventoryItemID, nDuplicates, itemName, itemAuthor, status, type, library);

        inventoryItemRepository.save(inventoryItem);

        inventoryItem = null;
        inventoryItem = IncentoryItemRepository.findInventoryItemById(inventoryItemID);

        assertNotNull(item);
        assertEquals(inventoryItemID, inventoryItem.getId());
    }
}