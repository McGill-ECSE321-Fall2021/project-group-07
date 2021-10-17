package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.librarysystem07.model.Event;

public interface EventRepository extends CrudRepository<Event, String>{
	
	Event findEventByName(String name);

}
