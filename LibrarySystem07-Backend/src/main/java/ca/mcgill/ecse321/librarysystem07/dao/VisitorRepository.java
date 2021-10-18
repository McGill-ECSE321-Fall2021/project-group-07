package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.Event;
import ca.mcgill.ecse321.librarysystem07.model.Reservation;
import ca.mcgill.ecse321.librarysystem07.model.Visitor;

public interface VisitorRepository extends CrudRepository<Visitor, Integer> {
	
	Visitor findVisitorByLibrarianCardID(Integer visitorLibrarianCardID);

	boolean existsByReservationAndEvent(Reservation reservation, Event eventName);

	Visitor findByPersonAndEvent(Reservation reservation, Event eventName);

}
