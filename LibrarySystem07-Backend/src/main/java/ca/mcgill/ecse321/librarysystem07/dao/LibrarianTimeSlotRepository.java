
package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Repository;

>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
import ca.mcgill.ecse321.librarysystem07.model.Librarian;
import ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot;

@Repository
public interface LibrarianTimeSlotRepository extends CrudRepository<LibrarianTimeSlot, Integer> {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	List<LibrarianTimeSlot> findLibrarianTimeSlotByHeadLibrarian(Librarian Librarian);
	LibrarianTimeSlot findLibrarianTimeSlotByTimeSlotID(int timeSlotId);
=======
	//List<LibrarianTimeSlot> findLibrarianTimeSlotByHeadLibrarian(Librarian Librarian);
	LibrarianTimeSlot findLibrarianTimeSlotByLibrarianTimeSlotId(int librarianTimeSlotId);
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
=======
	List<LibrarianTimeSlot> findLibrarianTimeSlotByLibrarian(Librarian Librarian);
	LibrarianTimeSlot findLibrarianTimeSlotByTimeSlotID(int timeSlotId);
>>>>>>> 1ba91abc45ee9364119a327d731763c3ae91231a
=======
	List<LibrarianTimeSlot> findLibrarianTimeSlotByLibrarian(Librarian Librarian);
	LibrarianTimeSlot findLibrarianTimeSlotByLibrarianTimeSlotId(int librarianTimeSlotId);
	//List<LibrarianTimeSlot> findLibrarianTimeSlotByDayOfTheWeek(DayOfTheWeek dayOfTheWeek);
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3

}