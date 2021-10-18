package ca.mcgill.ecse321.librarysystem07.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem07.model.*;
import ca.mcgill.ecse321.librarysystem07.model.ReservableItem.Status;
import ca.mcgill.ecse321.librarysystem07.model.ReservableItem.TypeOfReservableItem;
import ca.mcgill.ecse321.librarysystem07.model.TimeSlot.DayOfTheWeek;

@Repository
public class LibrarySystem07Repository {

	@Autowired
	EntityManager entityManager;

	/*Visitor - SH*/
	@Transactional
	public Visitor createVisitor(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary, int aDemeritPoints) {
		Visitor v = new Visitor(aName, aUsername, aAddress, aLibraryCardID, aLibrary, aDemeritPoints);
		entityManager.persist(v);
		return v;
	}

	@Transactional
	public Visitor getVisitor(int aLibraryCardID) {
		Visitor v = entityManager.find(Visitor.class, aLibraryCardID);
		return v;
	}

	
	/*TimeSlot - RM*/
	@Transactional
	public TimeSlot createTimeSlot(Time startTime, Time endTime, Date date, DayOfTheWeek dayoftheWeek, int id) {
		TimeSlot ts = new TimeSlot(startTime, endTime, date, dayoftheWeek, id, null, null, null, null, null);
		entityManager.persist(ts);
		return ts;
	}

	@Transactional
	public TimeSlot getTimeSlotById(int id) {
		TimeSlot t = entityManager.find(TimeSlot.class, id);
		return t;
	}

	@Transactional
	public List<TimeSlot> findAllTimeSlots() {
		Query query = entityManager.createQuery("SELECT e FROM TimeSlot e");
		Collection<TimeSlot> collectionOfTimeSlots = ((Collection<TimeSlot>) query.getResultList());
		List<TimeSlot> listOfTimeSlots = new ArrayList<TimeSlot>(collectionOfTimeSlots);
		return listOfTimeSlots;
	}
	
	/*Reservation - RM*/
	@Transactional
	public Reservation createReservation(int id, Visitor v, ReservableItem type) {
		Reservation r = new Reservation(id, v, type);
		entityManager.persist(r);
		return r;
	}
	
	@Transactional
	public Reservation findReservationByVisitor(Visitor v) {
		Reservation r = entityManager.find(Reservation.class, v);
		return r;
	}
	
	@Transactional
	public Reservation findReservationByReservableItem(ReservableItem i) {
		Reservation r = entityManager.find(Reservation.class, i);
		return r;
	}
	
	@Transactional
	public Reservation findReservationById(Integer ID) {
		Reservation r = entityManager.find(Reservation.class, ID);
		return r;	
	}
	
	/*Librarian - RM*/
	@Transactional
	public Librarian createLibrarian(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary) {
		Librarian l = new Librarian(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
		entityManager.persist(l);
		return l;
	}
	
	@Transactional
	public Librarian findLibrarian(String name) {
		return entityManager.find(Librarian.class, name);
	}
	
	@Transactional
	public Librarian findLibrarian(Integer id) {
		return entityManager.find(Librarian.class, id);
	}
	
	/*NonReservable Item - RM*/
	@Transactional
	public NonReservableItem createNonReservableItem(int id, Library library, NonReservableItem.TypeOfNonReservableItem type) {
		NonReservableItem nr = new NonReservableItem(id, library, type);
		entityManager.persist(nr);
		return nr;
	}
	
	@Transactional
	public NonReservableItem findNonReservableItem(int id) {
		return entityManager.find(NonReservableItem.class, id);
	}
	
	/*Reservable Item - RM*/
	@Transactional
	public ReservableItem createReservableItem(int id, Library library, int aDuplicates, String aName, String aAuthor, Status aStatus, ReservableItem.TypeOfReservableItem type) {
		ReservableItem r = new ReservableItem(id, library, aDuplicates, aName, aAuthor, aStatus, type);
		entityManager.persist(r);
		return r;
	}
	
	@Transactional
	public ReservableItem findReservableItem(int id) {
		return entityManager.find(ReservableItem.class, id);
	}
}
