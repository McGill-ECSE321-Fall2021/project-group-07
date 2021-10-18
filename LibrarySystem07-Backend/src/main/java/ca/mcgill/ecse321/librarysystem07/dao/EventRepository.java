package ca.mcgill.ecse321.librarysystem07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.librarysystem07.model.Event;
import ca.mcgill.ecse321.librarysystem07.model.Visitor;

public interface EventRepository extends CrudRepository<Event, Integer>{
	
	Event findEventById(Integer id);
	boolean existsByVisitor(Visitor visitor);
	List<Event> findByVisitor(Visitor visitor);

}
