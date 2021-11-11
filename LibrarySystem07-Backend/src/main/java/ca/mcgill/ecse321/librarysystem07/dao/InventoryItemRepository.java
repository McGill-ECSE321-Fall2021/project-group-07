package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.InventoryItem;


public interface InventoryItemRepository extends CrudRepository<InventoryItem, Integer> {
	
<<<<<<< HEAD
	InventoryItem findInventoryItemById(Integer id);
=======
	InventoryItem findInventoryItemByInventoryItemID(int inventoryItemID);
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
	
}
