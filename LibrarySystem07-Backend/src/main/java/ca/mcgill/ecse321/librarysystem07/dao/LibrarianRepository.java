package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem07.model.Librarian;

@Repository
public interface LibrarianRepository extends CrudRepository<Librarian, Integer>{

	Librarian findLibrarianByLibraryCardID(Integer libraryCardID);

}
