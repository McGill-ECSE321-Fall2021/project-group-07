package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.librarysystem07.model.TimeSlot;
import ca.mcgill.ecse321.librarysystem07.model.Reservation;
import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem07.model.Librarian;
import ca.mcgill.ecse321.librarysystem07.model.Library;
import ca.mcgill.ecse321.librarysystem07.model.Event;


public interface TimeslotRepository extends CrudRepository<TimeSlot, Integer>{
	
	TimeSlot findTimeSlotByTimeSlotID(Integer timeSlotID);

	TimeSlot findTimeSlotByReservation(Reservation reservation);
	
	TimeSlot findTimeSlotByEvent(Event event);

	TimeSlot findTimeSlotByLibrarian(Librarian librarian);

	TimeSlot findTimeSlotByHeadLibrarian(HeadLibrarian headLibrarian);
	
	TimeSlot findTimeSlotByLibrary(Library library);
	
//	Iterable<TimeSlot> findAll();

//	void delete(TimeSlot TimeSlot);
//	
//	void delete(List<TimeSlot> listOfTimeSlot);
	
}