package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem07.model.Event;
import ca.mcgill.ecse321.librarysystem07.model.Visitor;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer>{
	
	Event findEventByEventID(Integer eventID);
	boolean existsByVisitor(Visitor visitor);
	List<Event> findByVisitor(Visitor visitor);

}
