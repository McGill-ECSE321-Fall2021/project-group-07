package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.ReservableItem;
import ca.mcgill.ecse321.librarysystem07.model.Reservation;

public interface ReservableItemRepository extends CrudRepository<ReservableItem, Integer> {

	ReservableItem findReservableItemById(Integer id);

}
