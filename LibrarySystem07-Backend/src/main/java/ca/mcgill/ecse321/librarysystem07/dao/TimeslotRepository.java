package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.librarysystem07.model.TimeSlot;
import ca.mcgill.ecse321.librarysystem07.model.Reservation;
import ca.mcgill.ecse321.librarysystem07.model.Librarian;
import ca.mcgill.ecse321.librarysystem07.model.ReservableItem;


public interface TimeslotRepository extends CrudRepository<TimeSlot, String>{
	
	TimeSlot findTimeSlotByReservation(Reservation reservation);
	
	Iterable<TimeSlot> findAllByReservableItem(ReservableItem reservableItem);
	
	Iterable<TimeSlot> findAllByLibrarian(Librarian librarian);
	
	Iterable<TimeSlot> findAll();

	void delete(TimeSlot TimeSlot);
	
	void delete(List<TimeSlot> listOfTimeSlot);
	
}