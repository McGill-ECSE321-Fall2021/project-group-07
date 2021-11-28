
package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarianTimeSlot;

@Repository
public interface HeadLibrarianTimeSlotRepository extends CrudRepository<HeadLibrarianTimeSlot, Integer> {

<<<<<<< HEAD
<<<<<<< HEAD
	List<HeadLibrarianTimeSlot> findHeadLibrarianTimeSlotByHeadLibrarian(HeadLibrarian headLibrarian);
	HeadLibrarianTimeSlot findHeadLibrarianTimeSlotByTimeSlotID(int timeSlotId);
=======
	//List<HeadLibrarianTimeSlot> findHeadLibrarianTimeSlotByHeadLibrarian(HeadLibrarian headLibrarian);
	HeadLibrarianTimeSlot findHeadLibrarianTimeSlotByHeadLibrarianTimeSlotId(int headLibrarianTimeSlotId);
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
}
=======
	List<HeadLibrarianTimeSlot> findHeadLibrarianTimeSlotByHeadLibrarian(HeadLibrarian headLibrarian);
	HeadLibrarianTimeSlot findHeadLibrarianTimeSlotByTimeSlotID(int timeSlotId);
}
>>>>>>> 1ba91abc45ee9364119a327d731763c3ae91231a
