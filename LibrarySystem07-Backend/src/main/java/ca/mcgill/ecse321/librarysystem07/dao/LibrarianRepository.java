package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.Librarian;

public interface LibrarianRepository extends CrudRepository<Librarian, Integer>{

	Librarian findLibrarianByLibraryCardID(Integer libraryCardID);
	
	Librarian findLibrarianByName(String name);

}
