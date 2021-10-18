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

import ca.mcgill.ecse321.librarysystem07.model.Library;
import ca.mcgill.ecse321.librarysystem07.model.Reservation;
import ca.mcgill.ecse321.librarysystem07.model.TimeSlot;
import ca.mcgill.ecse321.librarysystem07.model.Visitor;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestReservationPersistence {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private TimeslotRepository timeSlotRepository;
	
	@Autowired
	private VisitorRepository visitorRepository;
	
	@AfterEach
	public void clearDatabase() {
		reservationRepository.deleteAll();
		timeSlotRepository.deleteAll();
		visitorRepository.deleteAll();
	}
	
	
	@Test
	public void testPersistAndLoadReservation() {
		/*Persist and load timeslot*/
		Time startTime = new Time(8, 0, 0);
		Time endTime = new Time(10, 0, 0);
		Date aDate = new Date(2021, 11, 0);
		TimeSlot ts = new TimeSlot(startTime, endTime, aDate, TimeSlot.DayOfTheWeek.Monday);
		
		timeSlotRepository.save(ts);
		int id = ts.getTimeSlotID();

		ts = null;
		ts = timeSlotRepository.findTimeSlotById(id);
		
		assertNotNull(ts);
		assertEquals(id, ts.getTimeSlotID());
		
		/*Persist visitor*/
		Time startTime2 = new Time(10, 0, 0);
		Time endTime2 = new Time(12, 0, 0);
		Date aDate2 = new Date(2021, 11, 0);
		TimeSlot ts2 = new TimeSlot(startTime2, endTime2, aDate2, TimeSlot.DayOfTheWeek.Monday);
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		timeSlots.add(ts);
		timeSlots.add(ts2);
		Library library = new Library("name", "city", timeSlots, null, 5145);
		
		
		Visitor v = new Visitor("bob", "bob1", "street A", 1, library, 0);
		visitorRepository.save(v);
		
		
		/*Persist and load reservation*/
		Reservation reservation = new Reservation(0, ts, v);
		reservationRepository.save(reservation);
		int resId = 0;//reservation.getReservationID();
		
		reservation = null;
		reservation = reservationRepository.findReservationById(id);
		assertNotNull(reservation);
		assertEquals(id, reservation.getReservationID());

		
	}
}
