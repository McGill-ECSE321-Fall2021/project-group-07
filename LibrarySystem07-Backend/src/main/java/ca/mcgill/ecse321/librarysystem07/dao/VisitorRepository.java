package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem07.model.Event;
import ca.mcgill.ecse321.librarysystem07.model.Reservation;
import ca.mcgill.ecse321.librarysystem07.model.Visitor;


@Repository
public interface VisitorRepository extends CrudRepository<Visitor, Integer> {
	
	Visitor findVisitorByLibraryCardID(Integer libraryCardID);

}
