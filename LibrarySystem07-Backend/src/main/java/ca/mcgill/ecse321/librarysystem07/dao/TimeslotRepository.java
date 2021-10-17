package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.librarysystem07.model.TimeSlot;
import ca.mcgill.ecse321.librarysystem07.model.Reservation;
import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem07.model.Librarian;
import ca.mcgill.ecse321.librarysystem07.model.ReservableItem;


public interface TimeslotRepository extends CrudRepository<TimeSlot, String>{
	
	TimeSlot findTimeSlotById(Integer id);

	TimeSlot findTimeSlotByReservation(Reservation reservation);
	
	TimeSlot findTimeSlotByReservableItem(ReservableItem item);

	TimeSlot findTimeSlotByLibrarian(Librarian librarian);

	TimeSlot findTimeSlotByHeadLibrarian(HeadLibrarian headLibrarian);
	
	Iterable<TimeSlot> findAll();

	void delete(TimeSlot TimeSlot);
	
	void delete(List<TimeSlot> listOfTimeSlot);
	
}