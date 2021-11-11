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
public class TestHeadLibrarianService {
	
	@Mock
	private HeadLibrarianRepository headLibrarianDao;
	
	@InjectMocks
	private LibrarySystem07Service service;
	
	private static final Integer HEAD_LIBRARIAN_KEY = 1234;
	
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
	    lenient().when(headLibrarianDao.findHeadLibrarianByLibraryCardID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(HEAD_LIBRARIAN_KEY)) {
	            HeadLibrarian headLibrarian = new HeadLibrarian();
	            headLibrarian.setLibraryCardID(HEAD_LIBRARIAN_KEY);
	            return headLibrarian;
	        } else {
	            return null;
	        }
	    });
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
	    lenient().when(headLibrarianDao.save(any(HeadLibrarian.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * HEAD LIBRARIAN TESTS  * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/*
	 * testCreateHeadLibrarian()
	 * 
	 * Test creating head librarian, if successful, then service should successfully add
	 * a head librarian to the system. 
	 * Check to see if the object we created exists, and if it's attributes where properly set
	 */

	@Test
	public void testCreateHeadLibrarian() {
		
		assertEquals(0, service.getAllHeadLibrarians().size());

		Integer libraryCardId = HEAD_LIBRARIAN_KEY;
		String name = "Nancy";
		String username = "Nancy334";
		String address = "746 Mont Royal";
		HeadLibrarian headLibrarian = null;
		
		try {
			headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(headLibrarian);
		assertEquals(libraryCardId, headLibrarian.getLibraryCardID());
		assertEquals(name, headLibrarian.getName());
		assertEquals(address, headLibrarian.getAddress());
		assertEquals(username, headLibrarian.getUsername());
	}
	
	/*
	 * testCreateHeadLibrarianNull()
	 * 
	 * if any of the fields of head librarian are null upon initialization,
	 * creation of a head librarian will be terminated and corresponding error message will
	 * be thrown
	 */
	
	@Test
	public void testCreateHeadLibrarianNull() {
		
		Integer libraryCardId = null;
		String name = null;
		String username = null;
		String address = null;
		HeadLibrarian headLibrarian = null;
		String error = null;
		
		try {
			headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(headLibrarian);
		// check error
		assertEquals("Head Librarian ID is invalid! Head Librarian name is invalid! "
				+ "Username is invalid! Address is invalid!", error);
	}
	
	/*
	 * testCreateHeadLibrarianNegative()
	 * 
	 * if the unique ID of head librarian is negative, we cannot have this and thus no object
	 * will be created and an error will be thrown
	 */
	
	@Test
	public void testCreateHeadLibrarianNegative() {
		
		Integer libraryCardId = -HEAD_LIBRARIAN_KEY;
		String name = "Nancy";
		String username = "Nancy334";
		String address = "746 Mont Royal";
		HeadLibrarian headLibrarian = null;
		String error = null;
		
		try {
			headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(headLibrarian);
		// check error
		assertEquals("Head Librarian ID is invalid!", error);
	}
	
	/*
	 * testCreateHeadLibrarianAlreadyExist()
	 * 
	 * if a head librarian is already exists in the system, there should
	 * only be on existing at a time, (delete previous one first) and thus no object
	 * will be created and an error will be thrown
	 */
	
	@Test
	public void testCreateHeadLibrarianAlreadyExist() {
		
		String error = null;
		
		Integer libraryCardId = HEAD_LIBRARIAN_KEY;
		String name = "Nancy";
		String username = "Nancy334";
		String address = "746 Mont Royal";
		
		service.createHeadLibrarian(name, username, address, libraryCardId);
		
		lenient().when(headLibrarianDao.existsById(anyInt())).thenReturn(true);
		
		Integer libraryCardId2 = HEAD_LIBRARIAN_KEY + 1;
		
		when(headLibrarianDao.count()).thenReturn((long) 1);
		
		HeadLibrarian headLibrarian = null;
		
		try {
			headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardId2);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(headLibrarian);
		assertEquals("A Head Librarian already exists!", error);
	}
	

	/*
	 * testDeleteHeadLibrarian()
	 * 
	 * test delete specific librarian, librarian should no longer exist in the system
	 * and number of librarians in the system should go down by one.
	 */
	
	@Test
	public void testDeleteHeadLibrarian() {

		Integer libraryCardId = HEAD_LIBRARIAN_KEY;
		String name = "Nancy5";
		String username = "Nancy3345";
		String address = "745 Mont Royal Crecent";
		
		service.createHeadLibrarian(name, username, address, libraryCardId);
		
		when(headLibrarianDao.count()).thenReturn((long) 1);
		
		try {
			service.deleteHeadLibrarian();
		} catch (IllegalArgumentException e) {
			fail();
		}

		verify(headLibrarianDao, times(1)).deleteAll();
	}
	
	/*
	 * testDeleteHeadLibrarian()
	 * 
	 * test delete specific head librarian while no librarians, therefore deleteAll should not
	 * be called and error message notifying user of no head librarian will be thrown
	 */
	
	@Test
	public void testDeleteHeadLibrarianNoHeadLibrarian() {
		
		String error = null;
			
		try {
			service.deleteHeadLibrarian();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		verify(headLibrarianDao, never()).deleteAll();
		assertEquals("Library has no Head Librarian!", error);
	}

}
