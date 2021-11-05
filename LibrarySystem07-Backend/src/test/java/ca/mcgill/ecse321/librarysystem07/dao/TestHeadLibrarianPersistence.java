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
		String name = "Nancy";
		String username = "nancy";
		String address = "240 test street";
		int libraryCardId=667;
		
		Time startTime3 = new Time(8, 0, 0);
		Time endTime3 = new Time(21, 0, 0);
		Date day3  = new Date(2021, 11, 18);
		TimeSlot.DayOfTheWeek weekday3 = TimeSlot.DayOfTheWeek.Sunday;
		TimeSlot openingHours3 = new TimeSlot(startTime3, endTime3, day3, weekday3);
		
		Time startTime13 = new Time(7, 0, 0);
		Time endTime13 = new Time(22, 0, 0);
		Date day13  = new Date(2021, 11, 19);
		TimeSlot.DayOfTheWeek weekday13 = TimeSlot.DayOfTheWeek.Monday;
		TimeSlot openingHours13 = new TimeSlot(startTime13, endTime13, day13, weekday13);

		Time startTime123 = new Time(9, 0, 0);
		Time endTime123 = new Time(20, 0, 0);
		Date day123  = new Date(2021, 11, 20);
		TimeSlot.DayOfTheWeek weekday123 = TimeSlot.DayOfTheWeek.Tuesday;
		TimeSlot openingHours123 = new TimeSlot(startTime123, endTime123, day123, weekday123);

		List<TimeSlot> openingHourss = new ArrayList<>();
		openingHourss.add(openingHours123);
		openingHourss.add(openingHours13);
		openingHourss.add(openingHours3);
		
		Time startTime = new Time(9, 0, 0);
		Time endTime = new Time(20, 0, 0);
		Date day  = new Date(2021, 10, 17);
		TimeSlot.DayOfTheWeek weekday = TimeSlot.DayOfTheWeek.Sunday;
		TimeSlot sched1 = new TimeSlot(startTime, endTime, day, weekday);
		
		Time startTime1 = new Time(9, 0, 0);
		Time endTime1 = new Time(20, 0, 0);
		Date day1  = new Date(2021, 10, 18);
		TimeSlot.DayOfTheWeek weekday1 = TimeSlot.DayOfTheWeek.Monday;
		TimeSlot sched2 = new TimeSlot(startTime1, endTime1, day1, weekday1);

		Time startTime12 = new Time(9, 0, 0);
		Time endTime12 = new Time(20, 0, 0);
		Date day12  = new Date(2021, 10, 19);
		TimeSlot.DayOfTheWeek weekday12 = TimeSlot.DayOfTheWeek.Tuesday;
		TimeSlot sched3 = new TimeSlot(startTime12, endTime12, day12, weekday12);
		
		List<TimeSlot> schedules = new ArrayList<>();
		schedules.add(sched3);
		schedules.add(sched2);
		schedules.add(sched1);
		
		long phoneNum = 514_888_8888L;
		
		Library library = new Library("Lib", "mtl", openingHourss, phoneNum);
		
		HeadLibrarian headLibrarian= new HeadLibrarian(name, username, address, libraryCardId, library , schedules);
		headLibrarianRepository.save(headLibrarian);
		
		headLibrarian=null;
		
		headLibrarian= headLibrarianRepository.findHeadLibrarianByName(name);
		
		assertNotNull(headLibrarian);
		assertEquals(schedules, headLibrarian.getSchedule());
	}
	

}
