
package ca.mcgill.ecse321.librarysystem07.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem07.service.*;
import ca.mcgill.ecse321.librarysystem07.dto.*;
import ca.mcgill.ecse321.librarysystem07.model.*;
import ca.mcgill.ecse321.librarysystem07.model.HeadLibrarianTimeSlot.DayOfTheWeek;


@CrossOrigin(origins = "*")
@RestController
public class LibrarySystem07Controller {

	@Autowired
	private LibrarySystem07Service service;

	/* Visitor controller methods */

	@GetMapping(value = { "/visitors", "/visitors/" })
	public List<VisitorDto> getAllPersons() {
		return service.getAllVisitors().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/visitor/{libraryCardId}", "/visitor/{libraryCardId}/" })
	public VisitorDto createVisitor(@PathVariable("libraryCardId") int libraryCardId,
			@RequestParam String name, 
			@RequestParam String username, @RequestParam String address, @RequestParam int demeritPoints)
					throws IllegalArgumentException {
		Visitor visitor = service.createVisitor(name, username, address, libraryCardId, demeritPoints);
		return convertToDto(visitor);
	}

	/**
	 * 
	 * @param VisitorDto vDto
	 * @return List<ReservationDto> of all reservations for visitor vDto
	 */
	@GetMapping(value = { "/reservations/visitor/{libraryCardId}", "/reservation/visitor/{libraryCardId}/" })
	public List<ReservationDto> getReservationsOfVisitor(@PathVariable("libraryCardId") VisitorDto vDto) {
		Visitor v = convertToDomainObject(vDto);
		return createReservationDtosForVisitor(v);
	}

	/**
	 * 
	 * @param VisitorDto vDto
	 * @return List<EventDto> of all events created by visitor vDto
	 */
	@GetMapping(value = { "/events/visitor/{libraryCardId}", "/events/visitor/{libraryCardId}/" })
	public List<EventDto> getEventsOfVisitor(@PathVariable("libraryCardId") VisitorDto vDto) {
		Visitor v = convertToDomainObject(vDto);
		return createEventDtosForVisitor(v);
	}


	/* Event controller methods */

	@GetMapping(value = { "/events", "/events/" })
	public List<EventDto> getAllEvents() {
		List<EventDto> eventDtos = new ArrayList<>();
		for (Event event : service.getAllEvents()) {
			eventDtos.add(convertToDto(event));
		}
		return eventDtos;
	}

	@PostMapping(value = { "/events/{eventId}", "/events/{eventId}/" })
	public EventDto createEvent(@PathVariable("eventId") int eventId, @RequestParam String name,
			@RequestParam Visitor visitor)
					throws IllegalArgumentException {
		Event event = service.createEvent(name, eventId, visitor);
		return convertToDto(event);
	}


	/* Reservation controller methods */

	@GetMapping(value = { "/reservations", "/reservations/" })
	public List<ReservationDto> getAllReservations() {
		List<ReservationDto> reservationDtos = new ArrayList<>();
		for (Reservation r : service.getAllReservations()) {
			reservationDtos.add(convertToDto(r));
		}
		return reservationDtos;
	}

	@PostMapping(value = { "/reservation/{reservationId}", "/reservation/{reservationId}/" })
	public ReservationDto createReservation(@PathVariable("evreservationIdentId") int reservationId, 
			@RequestParam Date startDate, 
			@RequestParam Date endDate, @RequestParam Visitor visitor, @RequestParam InventoryItem inventoryItem)
					throws IllegalArgumentException {
		Reservation r = service.createReservation(reservationId, startDate, endDate, visitor, inventoryItem);
		return convertToDto(r);
	}


	private List<ReservationDto> createReservationDtosForVisitor(Visitor v) {
		List<Reservation> reservations = service.getReservationsForVisitor(v);
		List<ReservationDto> reservationsDto = new ArrayList<>();
		for (Reservation r : reservations) {
			reservationsDto.add(convertToDto(r));
		}
		return reservationsDto;
	}

	private List<EventDto> createEventDtosForVisitor(Visitor v) {
		List<Event> events = service.getEventsOfVisitor(v);
		List<EventDto> eventsDto = new ArrayList<>();
		for (Event e : events) {
			eventsDto.add(convertToDto(e));
		}
		return eventsDto;
	}


	/*
	 * 
	 * headLibrarian controllers
	 */

	@PostMapping(value = { "/headLibrarian/{libraryCardID}", "/headLibrarian/{libraryCardID}/" })
	public HeadLibrarianDto createHeadLibrarian(@PathVariable("libraryCardID") Integer libraryCardID,
			@RequestParam(name = "name") String name, @RequestParam(name = "username") String username, @RequestParam(name = "address") String address) 
					throws IllegalArgumentException {

		HeadLibrarian headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardID);
		return convertToDto(headLibrarian);
	}

	@GetMapping(value = { "/headLibrarian", "/headLibrarian/" })
	public HeadLibrarianDto getHeadLibrarian() {

		return convertToDto(service.getAllHeadLibrarians().get(0));
	}

	/*
	 * librarian controllers
	 */

	@PostMapping(value = { "/librarians/{libraryCardID}", "/librarians/{libraryCardID}/" })
	public LibrarianDto createLibrarian(@PathVariable("libraryCardID") Integer libraryCardID,
			@RequestParam(name = "name") String name, @RequestParam(name = "username") String username, @RequestParam(name = "address") String address) 
					throws IllegalArgumentException {

		Librarian librarian = service.createLibrarian(name, username, address, libraryCardID);
		return convertToDto(librarian);
	}

	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<LibrarianDto> getAllLibrarians() {
		List<LibrarianDto> librarianDtos = new ArrayList<>();
		for (Librarian librarian : service.getAllLibrarians()) {
			librarianDtos.add(convertToDto(librarian));
		}
		return librarianDtos;
	}

	@GetMapping(value = { "/librarians/{libraryCardId}", "/librarians/{libraryCardId}" })
	public LibrarianDto getLibrarianByLibraryCardId(@PathVariable("libraryCardId") Integer libraryCardId) 
			throws IllegalArgumentException {
		return convertToDto(service.getLibrarian(libraryCardId));
	}

	/*
	 * headLibrarianTimeSlot controllers
	 */

	@PostMapping(value = { "/headLibrarianTimeSlots", "/headLibrarianTimeSlots/" })
	public HeadLibrarianTimeSlotDto scheduleHeadLibrarian(@RequestParam(name = "dayOfWeek") String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime, 
			@RequestParam(name = "headLibrarian") HeadLibrarianDto hlDto)
					throws IllegalArgumentException {

		DayOfTheWeek weekDay;

		if (dayOfWeek.equalsIgnoreCase("monday")) {
			weekDay = DayOfTheWeek.Monday;
		} else if (dayOfWeek.equalsIgnoreCase("tuesday")) {
			weekDay = DayOfTheWeek.Tuesday;
		} else if (dayOfWeek.equalsIgnoreCase("wednesday")) {
			weekDay = DayOfTheWeek.Wednesday;
		} else if (dayOfWeek.equalsIgnoreCase("thursday")) {
			weekDay = DayOfTheWeek.Thursday;
		} else if (dayOfWeek.equalsIgnoreCase("friday")) {
			weekDay = DayOfTheWeek.Friday;
		} else if (dayOfWeek.equalsIgnoreCase("saturday")) {
			weekDay = DayOfTheWeek.Saturday;
		} else {
			weekDay = DayOfTheWeek.Sunday;
		}

		HeadLibrarian headLibrarian = service.getHeadLibrarian(hlDto.getLibraryCardID());
		HeadLibrarianTimeSlot headLibrarianTimeSlot = service.createHeadLibrarianTimeSlot(headLibrarian, Time.valueOf(startTime), Time.valueOf(endTime), weekDay);
		return convertToDto(headLibrarianTimeSlot, headLibrarian);
	}

	@GetMapping(value = { "/headLibrarianTimeSlots", "/headLibrarianTimeSlots/" })
	public List<HeadLibrarianTimeSlotDto> getAllHeadLibrarianTimeSlots() {
		List<HeadLibrarianTimeSlotDto> headLibrarianTimeSlotDtos = new ArrayList<>();
		for (HeadLibrarianTimeSlot hlts : service.getAllHeadLibrarianTimeSlots()) {
			headLibrarianTimeSlotDtos.add(convertToDto(hlts, hlts.getHeadLibrarian()));
		}
		return headLibrarianTimeSlotDtos;
	}

	/*
	 * librarianTimeSlot controllers
	 */

	@PostMapping(value = { "/librarianTimeSlots", "/librarianTimeSlots/" })
	public LibrarianTimeSlotDto scheduleLibrarian(@RequestParam(name = "dayOfWeek") String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime, 
			@RequestParam(name = "librarian") LibrarianDto lDto)
					throws IllegalArgumentException {

		ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek weekDay;

		if (dayOfWeek.equalsIgnoreCase("monday")) {
			weekDay = ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Monday;
		} else if (dayOfWeek.equalsIgnoreCase("tuesday")) {
			weekDay = ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Tuesday;
		} else if (dayOfWeek.equalsIgnoreCase("wednesday")) {
			weekDay = ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Wednesday;
		} else if (dayOfWeek.equalsIgnoreCase("thursday")) {
			weekDay = ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Thursday;
		} else if (dayOfWeek.equalsIgnoreCase("friday")) {
			weekDay = ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Friday;
		} else if (dayOfWeek.equalsIgnoreCase("saturday")) {
			weekDay = ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Saturday;
		} else {
			weekDay = ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek.Sunday;
		}

		Librarian librarian = service.getLibrarian(lDto.getLibraryCardID());
		LibrarianTimeSlot librarianTimeSlot = service.createLibrarianTimeSlot(librarian, Time.valueOf(startTime), Time.valueOf(endTime), weekDay);
		return convertToDto(librarianTimeSlot, librarian);
	}

	@GetMapping(value = { "/librarianTimeSlots", "/librarianTimeSlots/" })
	public List<LibrarianTimeSlotDto> getAllLibrarianTimeSlots() {
		List<LibrarianTimeSlotDto> librarianTimeSlotDtos = new ArrayList<>();
		for (LibrarianTimeSlot lts : service.getAllLibrarianTimeSlots()) {
			librarianTimeSlotDtos.add(convertToDto(lts, lts.getLibrarian()));
		}
		return librarianTimeSlotDtos;
	}

	@GetMapping(value = { "/librarianTimeSlots/librarians/{libraryCardID}", "/librarianTimeSlots/librarians/{libraryCardID}/" })
	public List<LibrarianTimeSlotDto> getLibrarianSchedule(@PathVariable("libraryCardID") LibrarianDto lDto) {
		Librarian l = convertToDomainObject(lDto);
		return createLibrarianTimeSlotDtosForLibrarian(l);
	}

	/*
	 * MODEL TO DTO HELPER METHODS
	 */

	private HeadLibrarianTimeSlotDto convertToDto(HeadLibrarianTimeSlot hlts, HeadLibrarian hl) {

		if (hl == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}

		HeadLibrarianDto headLibrarianDto = convertToDto(hl);
		HeadLibrarianTimeSlotDto headLibrarianTimeSlotDto = new HeadLibrarianTimeSlotDto(hlts.getStartTime(), hlts.getEndTime(), parseDayOfWeekHL(hlts.getDayOfTheWeek()), headLibrarianDto);
		return headLibrarianTimeSlotDto;
	}

	private LibrarianTimeSlotDto convertToDto(LibrarianTimeSlot lts, Librarian l) {

		if (lts == null) {
			throw new IllegalArgumentException("There is no such Librarian Time Slot!");
		}

		LibrarianDto librarianDto = convertToDto(l);
		LibrarianTimeSlotDto librarianTimeSlotDto = new LibrarianTimeSlotDto(lts.getStartTime(), lts.getEndTime(), parseDayOfWeekL(lts.getDayOfTheWeek()), librarianDto);
		return librarianTimeSlotDto;

	}

	private HeadLibrarianDto convertToDto(HeadLibrarian hl) {

		if (hl == null) {
			throw new IllegalArgumentException("There is no such Head Librarian!");
		}

		HeadLibrarianDto headLibrarianDto = new HeadLibrarianDto(hl.getLibraryCardID(), hl.getName(), hl.getUsername(), hl.getAddress());
		return headLibrarianDto;
	}

	private LibrarianDto convertToDto(Librarian l) {

		if (l == null) {
			throw new IllegalArgumentException("There is no such Head Librarian!");
		}

		LibrarianDto librarianDto = new LibrarianDto(l.getLibraryCardID(), l.getName(), l.getUsername(), l.getAddress());
		librarianDto.setLibrarianTimeSlots(createLibrarianTimeSlotDtosForLibrarian(l));
		return librarianDto;
	}

	/*
	 * since we do not test the library as a whole, there is no way of accessing schedule
	 * for a specific librarian, store within LibrarianDto a attribute for list of time slots
	 * do not have an attribute within head librarian Dto as there is only one head librarian,
	 * and therefore only one schedule (one set of time slots)
	 * 
	 * @param a librarian
	 * @return DTO time slots for a specific librarian
	 */

	private List<LibrarianTimeSlotDto> createLibrarianTimeSlotDtosForLibrarian(Librarian l) {
		List<LibrarianTimeSlot> librarianTimeSlotsForLibrarian = service.getLibrarianTimeSlotByLibrarian(l);
		List<LibrarianTimeSlotDto> librarianTimeSlots = new ArrayList<>();
		for (LibrarianTimeSlot timeslot : librarianTimeSlotsForLibrarian) {
			librarianTimeSlots.add(convertToDto(timeslot, l));
		}
		return librarianTimeSlots;
	}

	/*
	 * helper method to convert from Dto object to model object
	 * only librarian needs...
	 * 
	 * @param Librarian object from Dto
	 * @return Librarian object from model
	 */

	private Librarian convertToDomainObject(LibrarianDto lDto) {
		List<Librarian> allLibrarians = service.getAllLibrarians();
		for (Librarian librarian : allLibrarians) {
			if (librarian.getLibraryCardID() == lDto.getLibraryCardID()) {
				return librarian;
			}
		}
		return null;
	}

	private Visitor convertToDomainObject(VisitorDto v) {
		List<Visitor> visitors = service.getAllVisitors();
		for (Visitor visitor : visitors) {
			if (visitor.getLibraryCardID() == v.getLibraryCardId()) {
				return visitor;
			}
		}
		return null;
	}

	private EventDto convertToDto(Event e) {
		if (e == null) {
			throw new IllegalArgumentException("Event is not valid.");
		}
		else {
			EventDto event = new EventDto(e.getName(), e.getEventID(), e.getVisitor());
			return event;
		}
	}

	private ReservationDto convertToDto(Reservation r) {
		if (r == null) {
			throw new IllegalArgumentException("Reservation is not valid.");
		}
		else {
			ReservationDto reservation = new ReservationDto(r.getStartDate(), r.getEndDate(), r.getVisitor(), r.getInventoryItem(), r.getReservationID());
			return reservation;
		}
	}



	private VisitorDto convertToDto(Visitor v) {
		if (v == null) {
			throw new IllegalArgumentException("This visitor is invalid.");
		}
		else {
			VisitorDto vDto = new VisitorDto(v.getName(),v.getUsername(), v.getAddress(), v.getLibraryCardID(), v.getDemeritPoints());
			return vDto;
		}
	}


	/*
	 * helper methods for initializing dayOfTheWeek in LibrarianTimeSlotDto and HeadLibrarianTimeSlotDto
	 * when creating DTO, we need to use the enum class within the DTO class and not the model 
	 * 
	 * @param DayOfTheWeek enum type from model class
	 * @return DayOfTheWeek enum type for DTO class
	 */

	private ca.mcgill.ecse321.librarysystem07.dto.LibrarianTimeSlotDto.DayOfTheWeek parseDayOfWeekL(ca.mcgill.ecse321.librarysystem07.model.LibrarianTimeSlot.DayOfTheWeek dayOfTheWeek) {

		ca.mcgill.ecse321.librarysystem07.dto.LibrarianTimeSlotDto.DayOfTheWeek newTime;

		if (dayOfTheWeek.toString().equalsIgnoreCase("monday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.LibrarianTimeSlotDto.DayOfTheWeek.Monday;
		} else if (dayOfTheWeek.toString().equalsIgnoreCase("tuesday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.LibrarianTimeSlotDto.DayOfTheWeek.Tuesday;
		} else if (dayOfTheWeek.toString().equalsIgnoreCase("wednesday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.LibrarianTimeSlotDto.DayOfTheWeek.Wednesday;
		} else if (dayOfTheWeek.toString().equalsIgnoreCase("thursday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.LibrarianTimeSlotDto.DayOfTheWeek.Thursday;
		} else if (dayOfTheWeek.toString().equalsIgnoreCase("thursday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.LibrarianTimeSlotDto.DayOfTheWeek.Friday;
		} else if (dayOfTheWeek.toString().equalsIgnoreCase("thursday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.LibrarianTimeSlotDto.DayOfTheWeek.Saturday;
		} else {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.LibrarianTimeSlotDto.DayOfTheWeek.Sunday;
		}

		return newTime;
	}

	private ca.mcgill.ecse321.librarysystem07.dto.HeadLibrarianTimeSlotDto.DayOfTheWeek parseDayOfWeekHL(ca.mcgill.ecse321.librarysystem07.model.HeadLibrarianTimeSlot.DayOfTheWeek dayOfTheWeek) {

		ca.mcgill.ecse321.librarysystem07.dto.HeadLibrarianTimeSlotDto.DayOfTheWeek newTime = ca.mcgill.ecse321.librarysystem07.dto.HeadLibrarianTimeSlotDto.DayOfTheWeek.Sunday;

		if (dayOfTheWeek.toString().equalsIgnoreCase("monday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.HeadLibrarianTimeSlotDto.DayOfTheWeek.Monday;
		} else if (dayOfTheWeek.toString().equalsIgnoreCase("tuesday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.HeadLibrarianTimeSlotDto.DayOfTheWeek.Tuesday;
		} else if (dayOfTheWeek.toString().equalsIgnoreCase("wednesday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.HeadLibrarianTimeSlotDto.DayOfTheWeek.Wednesday;
		} else if (dayOfTheWeek.toString().equalsIgnoreCase("thursday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.HeadLibrarianTimeSlotDto.DayOfTheWeek.Thursday;
		} else if (dayOfTheWeek.toString().equalsIgnoreCase("thursday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.HeadLibrarianTimeSlotDto.DayOfTheWeek.Friday;
		} else if (dayOfTheWeek.toString().equalsIgnoreCase("thursday")) {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.HeadLibrarianTimeSlotDto.DayOfTheWeek.Saturday;
		} else {
			newTime = ca.mcgill.ecse321.librarysystem07.dto.HeadLibrarianTimeSlotDto.DayOfTheWeek.Sunday;
		}

		return newTime;
	}

}
