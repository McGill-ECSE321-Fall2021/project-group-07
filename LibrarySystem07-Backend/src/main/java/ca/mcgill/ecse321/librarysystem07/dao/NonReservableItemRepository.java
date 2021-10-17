package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.NonReservableItem;

public interface NonReservableItemRepository extends CrudRepository<NonReservableItem, Integer> {
	
	NonReservableItem findNonReservableItemById(Integer id);	
	
}