package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarian;


@Repository
public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, Integer>{
	
	HeadLibrarian findHeadLibrarianByLibraryCardID(Integer libraryCardID);
}