package ca.mcgill.ecse321.librarysystem07.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem07.model.Librarian;
import ca.mcgill.ecse321.librarysystem07.model.Library;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibrarianPersistence {
	
	@Autowired
	private LibrarianRepository librarianRepository;
	
	@AfterEach
	public void clearDatabase() {
		librarianRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadLibrarian() {
		
		String libName = "Westmount Library";
		String city = "Montreal";
		String phoneNumber = "514-678-0453";
		Library lib = new Library(libName, city, phoneNumber);
		
		String name = "Nancy";
		String username = "nancy";
		String address = "240 test street";
		int libraryCardId =  667;
		
		Librarian nancy = new Librarian(name, username, address, libraryCardId, lib);
		librarianRepository.save(nancy);
		
		nancy = null;
		
		nancy = librarianRepository.findLibrarianByLibraryCardID(libraryCardId);
		
		assertNotNull(nancy);
		assertEquals(libraryCardId, nancy.getLibraryCardID());

	}

}
