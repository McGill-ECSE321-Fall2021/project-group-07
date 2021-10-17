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
import ca.mcgill.ecse321.librarysystem07.model.TimeSlot.DayOfTheWeek;

@Repository
public class LibrarySystem07Repository {

	@Autowired
	EntityManager entityManager;

	/*Visitor*/
	@Transactional
	public Visitor createVisitor(String name) {
		Visitor v = new Visitor(name, name, name, 0, null, 0);
		v.setName(name);
		entityManager.persist(v);
		return v;
	}

	@Transactional
	public Visitor getVisitor(String name) {
		Visitor v = entityManager.find(Visitor.class, name);
		return v;
	}

	
	/*TimeSlot - RM*/
	@Transactional
	public TimeSlot createTimeSlot(Time startTime, Time endTime, Date date, DayOfTheWeek dayoftheWeek) {
		TimeSlot ts;

		List<TimeSlot> allSlots = findAllTimeSlots();
		Integer id = 0;
		//if no timeslots, make ID 0
		if (allSlots.isEmpty()) ts = new TimeSlot(startTime, endTime, date, dayoftheWeek, id);
		else {
			//find highest ID and add 1 to it to get next ID
			for (TimeSlot t : allSlots) {

				if (t.getTimeSlotID() > id) id = t.getTimeSlotID();
			}
			ts = new TimeSlot(startTime, endTime, date, dayoftheWeek, (id+1));
		}
		entityManager.persist(ts);
		return ts;
	}

	@Transactional
	public TimeSlot findTimeSlotById(int id) {
		TimeSlot t = entityManager.find(TimeSlot.class, id);
		return t;
	}

	@Transactional
	public List<TimeSlot> findAllTimeSlots() {
		Query query = entityManager.createQuery("SELECT e FROM Professor e");
		Collection<TimeSlot> collectionOfTimeSlots = ((Collection<TimeSlot>) query.getResultList());
		List<TimeSlot> listOfTimeSlots = new ArrayList<TimeSlot>(collectionOfTimeSlots);
		return listOfTimeSlots;
	}
	
	/*Reservation - RM*/
	@Transactional
	public Reservation createReservation(int id, TimeSlot ts, Visitor v) {
		Reservation r = new Reservation(id, ts, v);
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
	public Librarian createLibrarian(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary, List<TimeSlot> aSchedule) {
		Librarian l = new Librarian(aName, aUsername, aAddress, aLibraryCardID, aLibrary, aSchedule);
		entityManager.persist(l);
		return l;
	}
	
	public Librarian findLibrarian(String name) {
		return entityManager.find(Librarian.class, name);
	}
	
	public Librarian findLibrarian(Integer id) {
		return entityManager.find(Librarian.class, id);
	}
	
	/*Non-Reservable Item*/

}
