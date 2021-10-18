package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.*;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
	
	List<Reservation> findReservationsByVisitor(Visitor v);
	
	Reservation findReservationById(Integer Id);
	
	Reservation findReservationByReservableItem(ReservableItem r);
}
