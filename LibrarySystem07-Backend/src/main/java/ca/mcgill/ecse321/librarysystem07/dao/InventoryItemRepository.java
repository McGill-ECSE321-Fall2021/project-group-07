package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem07.model.InventoryItem;
import ca.mcgill.ecse321.librarysystem07.model.Reservation;

@Repository
public interface InventoryItemRepository extends CrudRepository<InventoryItem, Integer> {
	
	InventoryItem findInventoryItemByInventoryItemID(int inventoryItemID);
	
}