package ca.mcgill.ecse321.librarysystem07.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem07.model.Library;

public interface LibraryRepository extends CrudRepository<Library, String> {
  
	Library findLibraryByName(String Name);
	
	Library findLibraryByCity(String City);
	
	Library findLibraryByPhonenumber(long Phonenumber);
	
}