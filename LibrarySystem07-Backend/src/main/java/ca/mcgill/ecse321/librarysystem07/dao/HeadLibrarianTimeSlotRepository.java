package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarianTimeSlot;

public interface HeadLibrarianTimeSlotRepository extends CrudRepository<HeadLibrarianTimeSlot, Integer> {

<<<<<<< HEAD
	List<HeadLibrarianTimeSlot> findHeadLibrarianTimeSlotByHeadLibrarian(HeadLibrarian headLibrarian);
	HeadLibrarianTimeSlot findHeadLibrarianTimeSlotByTimeSlotID(int timeSlotId);
=======
	//List<HeadLibrarianTimeSlot> findHeadLibrarianTimeSlotByHeadLibrarian(HeadLibrarian headLibrarian);
	HeadLibrarianTimeSlot findHeadLibrarianTimeSlotByHeadLibrarianTimeSlotId(int headLibrarianTimeSlotId);
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
}
