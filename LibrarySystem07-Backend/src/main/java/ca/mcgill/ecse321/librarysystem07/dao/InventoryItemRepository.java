package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.InventoryItem;


public interface InventoryItemRepository extends CrudRepository<InventoryItem, Integer> {
	
	InventoryItem findInventoryItemByInventoryItemID(int inventoryItemID);
	
}
