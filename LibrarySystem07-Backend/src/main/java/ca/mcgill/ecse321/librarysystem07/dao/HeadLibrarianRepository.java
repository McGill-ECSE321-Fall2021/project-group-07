package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarian;

public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, String>{
	
	HeadLibrarian findHeadLibrarianByName(String name);
}