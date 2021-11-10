package ca.mcgill.ecse321.librarysystem07.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem07.model.Library;
import ca.mcgill.ecse321.librarysystem07.model.Visitor;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestVisitorRepository {

	@Autowired
	private VisitorRepository visitorRepository;
	
	@AfterEach
	public void clearDatabase() {
		visitorRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadVisitor() {
		
//		String libName = "Westmount Library";
//		String city = "Montreal";
//		String phoneNumber = "514-678-0453";
//		Library lib = new Library(libName, city, phoneNumber);
		
		String name = "Sam";
		String username = "Sam20031";
		String addy = "4500 av Cumberland";
		Integer aLibraryCardID = 12345; 
		int aDemeritPoints = 0;

		Visitor sam = new Visitor(name, username, addy, aLibraryCardID, aDemeritPoints);
		visitorRepository.save(sam);
		
		sam = null;
		sam = visitorRepository.findVisitorByLibraryCardID(aLibraryCardID);
		
		assertNotNull(sam);
		assertEquals(aLibraryCardID, sam.getLibraryCardID());

	}


}
