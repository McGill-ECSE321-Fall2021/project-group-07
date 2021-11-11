package ca.mcgill.ecse321.librarysystem07.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem07.dao.*;
import ca.mcgill.ecse321.librarysystem07.model.*;

/*
 * assuming database works, when we call specific function return
 * this response for this function call (assume its the database)
 */

@ExtendWith(MockitoExtension.class)
public class TestLibrarianService {
	
	/*
	 * created in response to making function call
	 * mocking response from server
	 * pretend this response we get
	 * even tho we use the datatype, it actually doesn't do
	 * anything besides mock up for testing
	 */
	
	@Mock
	private LibrarianRepository librarianDao;
	
	@InjectMocks
	private LibrarySystem07Service service;
	
	private static final Integer LIBRARIAN_KEY = 1234;
	private static final Integer NONEXISTING_KEY = 0;
	
	/*
	 * any string => getArgument(0)
	 * triggers invocation call, prevents any other data type to be passed
	 * if it is, create person
	 * if person exists in database, we get that person back
	 * what this does (mocking database return)
	 * if it is equal to person key, then it will create it and return it 
	 * 
	 */
	
	@BeforeEach
	public void setMockOutput() {
	    lenient().when(librarianDao.findLibrarianByLibraryCardID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(LIBRARIAN_KEY)) {
	            Librarian librarian = new Librarian();
	            librarian.setLibraryCardID(LIBRARIAN_KEY);
	            return librarian;
	        } else {
	            return null;
	        }
	    });
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
	    lenient().when(librarianDao.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * LIBRARIAN TESTS * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/*
	 * testCreateLibrarian()
	 * 
	 * Test creating librarian, if successful, then service should successfully add
	 * a librarian to the system. 
	 * Check to see if the object we created exists, and if it's attributes where properly set
	 */
	
	@Test
	public void testCreateLibrarian() {
		
		assertEquals(0, service.getAllLibrarians().size());

		Integer libraryCardId = 1239;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		Librarian librarian = null;
		
		try {
			librarian = service.createLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(librarian);
		assertEquals(libraryCardId, librarian.getLibraryCardID());
		assertEquals(name, librarian.getName());
		assertEquals(username, librarian.getUsername());
		assertEquals(address, librarian.getAddress());
	}
	
	/*
	 * testCreateLibrarianNull()
	 * 
	 * if any of the fields of librarian are null upon initialization,
	 * creation of a librarian will be terminated and corresponding error message will
	 * be thrown
	 */
	
	@Test
	public void testCreateLibrarianNull() {
		
		Integer libraryCardId = null;
		String name = null;
		String username = null;
		String address = null;
		String error = null;
		Librarian librarian = null;
		
		try {
			librarian = service.createLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(librarian);
		// check error
		assertEquals("Librarian ID is invalid! Librarian name is invalid! "
				+ "Username is invalid! Address is invalid!", error);
	}
	
	/*
	 * testCreateLibrarianNegative()
	 * 
	 * if the unique ID of librarian is negative, we cannot have this and thus no object
	 * will be created and an error will be thrown.
	 */
	
	@Test
	public void testCreateLibrarianNegative() {
		
		Integer libraryCardId = -1;
		String name = "Gina";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		String error = null;
		Librarian librarian = null;
		
		try {
			librarian = service.createLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(librarian);
		assertEquals("Librarian ID is invalid!", error);
	}
	
	@Test
	public void testGetExistingLibrarian() {
		assertEquals(LIBRARIAN_KEY, service.getLibrarian(LIBRARIAN_KEY).getLibraryCardID());
	}

	@Test
	public void testGetNonExistingLibrarian() {
		assertNull(service.getLibrarian(NONEXISTING_KEY));
	}
	
	/*
	 * testDeleteLibrarian()
	 * 
	 * test delete specific librarian, librarian should no longer exist in the system
	 * and number of librarians in the system should go down by one.
	 * 
	 * test to see if librarian has been deleted, then delete method would be called once
	 * within the service class.
	 */
	
	@Test
	public void testDeleteLibrarian() {

		Integer libraryCardId = LIBRARIAN_KEY;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		
		service.createLibrarian(name, username, address, libraryCardId);
		
		try {
			service.deleteLibrarian(libraryCardId);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		verify(librarianDao, times(1)).delete(any(Librarian.class));
	}
	
	/*
	 * testDeleteLibrarianNullId()
	 * 
	 * if a librarian id is null when calling delete, then it will no be able to find
	 * an associated librarian to delete, and therefore no librarian will be deleted
	 * and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteLibrarianNullId() {
		
		String error = null;

		Integer libraryCardId = 1239;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		
		service.createLibrarian(name, username, address, libraryCardId);
		
		libraryCardId = null;
		
		try {
			service.deleteLibrarian(libraryCardId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		verify(librarianDao, never()).delete(any(Librarian.class));
		assertEquals("Librarian ID is invalid!", error);
	}
	
	/*
	 * testDeleteLibrarianNullId()
	 * 
	 * if a librarian id used to call delete is an invalid number, then it will no be able to find
	 * an associated librarian to delete, and therefore no librarian will be deleted
	 * and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteLibrarianInvalidId() {
		
		String error = null;

		Integer libraryCardId = 1239;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		
		service.createLibrarian(name, username, address, libraryCardId);
		
		libraryCardId = -1239;
		
		try {
			service.deleteLibrarian(libraryCardId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		verify(librarianDao, never()).delete(any(Librarian.class));
		assertEquals("Librarian ID is invalid!", error);
	}
	
	/*
	 * testDeleteLibrarianDNE()
	 * 
	 * if a librarian id is valid and not null but does not exist in the system, then it 
	 * will no be able to find an associated librarian to delete, and therefore no librarian 
	 * will be deleted and corresponding error message will be thrown
	 */
	
	@Test
	public void testDeleteLibrarianDNE() {
		
		String error = null;
		
		Integer libraryCardId = 1239;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		
		service.createLibrarian(name, username, address, libraryCardId);
		
		libraryCardId = LIBRARIAN_KEY - 1;
		
		try {
			service.deleteLibrarian(libraryCardId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		verify(librarianDao, never()).delete(any(Librarian.class));
		assertEquals("No such Librarian!", error);
	}
	
	/*
	 * testDeleteAllLibrarian()
	 * 
	 * test deleting all librarians from the system, after this operation no librarians
	 * should be left in the system.
	 * 
	 * using verify function, we can check to see if deleteAll was called within the service class,
	 * if it was, this means that all the librarians have been deleted from the system.
	 * 
	 * must mock number of librarians within the system as well, if not first check for
	 * existing librarians will fail
	 */
	
	@Test
	public void testDeleteAllLibrarian() {

		Integer libraryCardId = 1239;
		String name = "Oscar";
		String username = "OscarTheGrouch33";
		String address = "4568 av pins";
		
		service.createLibrarian(name, username, address, libraryCardId);
		
		when(librarianDao.count()).thenReturn((long) 1);
		
		try {
			service.deleteAllLibrarians();
		} catch (IllegalArgumentException e) {
			fail();
		}

		verify(librarianDao).deleteAll();
	}
	
	/*
	 * testDeleteAllLibrarianNoLibrarians()
	 * 
	 * if there are already no librarians in the system, then nothing changes 
	 * but good to notify the user that there where already no librarians to delete.
	 */
	
	@Test
	public void testDeleteAllLibrarianNoLibrarians() {
		
		String error = null;
		
		try {
			service.deleteAllLibrarians();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		verify(librarianDao, never()).delete(any(Librarian.class));
		assertEquals("Library has no Librarians!", error);
	}
}
