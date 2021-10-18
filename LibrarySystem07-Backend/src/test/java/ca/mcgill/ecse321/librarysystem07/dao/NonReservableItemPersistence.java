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

    @Test
    @Transactional
    public void testPersistAndNonReservableItem(){
        NonReservableItem nR = new NonReservableItem();
        int nRID = 1;
        Library testLibrary = new Library();
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