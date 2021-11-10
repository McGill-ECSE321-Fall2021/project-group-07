package ca.mcgill.ecse321.librarysystem07.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem07.model.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestHeadLibrarianPersistence {
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
	
	
	@AfterEach
	public void clearDatabase() {
		
		headLibrarianRepository.deleteAll();
		
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
<<<<<<< HEAD
		
		String libName = "Westmount Library";
		String city = "Montreal";
		String phoneNumber = "514-678-0453";
		Library lib = new Library(libName, city, phoneNumber);
		
		String name = "Frances";
		String username = "frank334";
		String address = "240 street rd";
		int libraryCardId = 667890;
		
		HeadLibrarian headLibrarian= new HeadLibrarian(name, username, address, libraryCardId, lib);
=======
		
//		String libName = "Westmount Library";
//		String city = "Montreal";
//		String phoneNumber = "514-678-0453";
//		Library lib = new Library(libName, city, phoneNumber);
		
		String name = "Frances";
		String username = "frank334";
		String address = "240 street rd";
		int libraryCardId = 667890;
		
		HeadLibrarian headLibrarian= new HeadLibrarian(name, username, address, libraryCardId);
		
//		List<UserRole> haha = new ArrayList<UserRole>();
//		haha.add(headLibrarian);
//		lib.setUsers(haha);
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
		
		headLibrarianRepository.save(headLibrarian);
		
		headLibrarian = null;
		
		headLibrarian = headLibrarianRepository.findHeadLibrarianByLibraryCardID(libraryCardId);
		
		assertNotNull(headLibrarian);
		assertEquals(libraryCardId, headLibrarian.getLibraryCardID());
	}
	

}
