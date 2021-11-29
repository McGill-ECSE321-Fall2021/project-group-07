package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem07.model.Librarian;
import ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot;

@Repository
public interface LibrarianTimeSlotRepository extends CrudRepository<LibrarianTimeSlot, Integer> {

	List<LibrarianTimeSlot> findLibrarianTimeSlotByLibrarian(Librarian Librarian);
	LibrarianTimeSlot findLibrarianTimeSlotByLibrarianTimeSlotId(int librarianTimeSlotId);

}
