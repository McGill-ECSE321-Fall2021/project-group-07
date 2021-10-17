package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.*;

public interface ReservationRepository extends CrudRepository<Reservation, String> {
	
	Reservation findReservationByReservableItem(ReservableItem r);
	
	List<Reservation> findReservationsByVisitor(Visitor v);
	
	Reservation findReservationById(Integer ID);
	
}
