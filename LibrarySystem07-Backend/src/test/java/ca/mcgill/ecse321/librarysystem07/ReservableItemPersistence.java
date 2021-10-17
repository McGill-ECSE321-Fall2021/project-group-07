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

    @Test
    @Transactional
    public void testPersistAndReservableItem(){
        NonReservableItem rI = new ReservableItem();
        int rIID = 1;
        Library testLibrary = new Library();
        int duplicateNum = 3;
        String name = "Hello, World!"
        String author = "Liamo Pennimpede"
        Status status = Status.OrIeserve;
        TypeOfReservableItem type = TypeOfReservableItem.CD;

        rI.InventoryItem.setId(rIID);
        rI.setLibrary(testLibrary);
        rI.setDuplicates(duplicateNum);
        rI.setName(name);
        rI.setAuthor(author);
        rI.setStatus(status);
        rI.setType(type);

        NonReservableItemRepository.save(rI);

        rI = null;
        rI = ReservableItem.findReservableItemById(Integer id);
        assertNotNull(rI);
        assertEquals(aID, rI.getID());
        assertEquals(testLibrary, rI.getLibrary());
        assertEquals(duplicateNum, rI.getDuplicates());
        assertEquals(name, rI.getName());
        assertEquals(author, rI.getAuthor());
        assertEquals(status, rI.getStatus());
        assertEquals(type, rI.getType());
    }
