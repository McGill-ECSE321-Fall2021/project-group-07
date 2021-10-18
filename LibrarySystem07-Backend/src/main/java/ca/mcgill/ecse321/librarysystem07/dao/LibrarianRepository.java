package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.Librarian;

public interface LibrarianRepository extends CrudRepository<Librarian, String>{

	Librarian findLibrarianByLibraryCardID(Integer ID);
	
	Librarian findLibrarianByName(String name);

}