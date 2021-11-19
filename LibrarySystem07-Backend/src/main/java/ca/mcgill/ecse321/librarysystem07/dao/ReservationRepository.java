package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem07.model.*;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

	List<Reservation> findReservationsByVisitor(Visitor visitor);

	List<Reservation> findReservationByInventoryItem(InventoryItem reservableItem);

	Reservation findReservationByReservationID(Integer reservationID);

	boolean existsByInventoryItemAndVisitor(InventoryItem reservableItem, Visitor visitor);

	Reservation findByInventoryItemAndVisitor(InventoryItem reservableItem, Visitor visitor);

}
