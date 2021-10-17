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
import ca.mcgill.ecse321.librarysystem07.model.Timeslot.DayoftheWeek;

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
	public Timeslot createTimeslot(Time startTime, Time endTime, Date startDate, Date endDate, DayoftheWeek dayoftheWeek) {
		Timeslot ts = new Timeslot(startTime, endTime, startDate, endDate, dayoftheWeek);
		entityManager.persist(ts);
		return ts;
	}
	
	@Transactional
	public Timeslot getTimeslot(String id) {
		Timeslot t = entityManager.find(Timeslot.class, id);
		return t;
	}
	
	@Transactional
	public List<Timeslot> findAllTimeslots() {
		Query query = entityManager.createQuery("SELECT e FROM Professor e");
		Collection<Timeslot> collectionOfTimeslots = ((Collection<Timeslot>) query.getResultList());
		List<Timeslot> listOfTimeslots = new ArrayList<Timeslot>(collectionOfTimeslots);
		return listOfTimeslots;
	}
}
