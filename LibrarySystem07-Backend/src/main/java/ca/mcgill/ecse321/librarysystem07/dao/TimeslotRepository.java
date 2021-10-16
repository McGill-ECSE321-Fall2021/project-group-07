package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.librarysystem07.model.Timeslot;
import ca.mcgill.ecse321.librarysystem07.model.Reservation;
import ca.mcgill.ecse321.librarysystem07.model.Librarian;
import ca.mcgill.ecse321.librarysystem07.model.ReservableItem;


public interface TimeslotRepository extends CrudRepository<Timeslot, String>{
	
	Timeslot findTimeslotByReservation(Reservation reservation);
	
	Iterable<Timeslot> findAllByReservableItem(ReservableItem reservableItem);
	
	Iterable<Timeslot> findAllByLibrarian(Librarian librarian);
	
	Iterable<Timeslot> findAll();

	void delete(Timeslot timeslot);
	
}