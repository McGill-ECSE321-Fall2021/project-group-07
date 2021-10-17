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

	@Transactional
	public TimeSlot createTimeSlot(Time startTime, Time endTime, Date date, DayOfTheWeek dayoftheWeek) {
		TimeSlot ts;

		List<TimeSlot> allSlots = findAllTimeSlots();
		Integer id = 0;
		if (allSlots.isEmpty()) ts = new TimeSlot(startTime, endTime, date, dayoftheWeek, id);
		else {
			for (TimeSlot t : allSlots) {

				if (t.getTimeSlotID() > id) id = t.getTimeSlotID();
			}
			ts = new TimeSlot(startTime, endTime, date, dayoftheWeek, (id+1));
		}
		entityManager.persist(ts);
		return ts;
	}

	@Transactional
	public TimeSlot getTimeSlot(String id) {
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
}
