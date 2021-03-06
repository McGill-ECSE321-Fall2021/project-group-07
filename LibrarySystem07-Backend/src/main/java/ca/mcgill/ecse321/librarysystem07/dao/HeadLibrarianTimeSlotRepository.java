package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarianTimeSlot;

@Repository
public interface HeadLibrarianTimeSlotRepository extends CrudRepository<HeadLibrarianTimeSlot, Integer> {

	//List<HeadLibrarianTimeSlot> findHeadLibrarianTimeSlotByHeadLibrarian(HeadLibrarian headLibrarian);
	HeadLibrarianTimeSlot findHeadLibrarianTimeSlotByHeadLibrarianTimeSlotId(int headLibrarianTimeSlotId);
}
