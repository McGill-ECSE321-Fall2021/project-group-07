
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
	
	// VISITOR //
	
	@GetMapping(value = { "/visitors", "/visitors/" })
	public List<VisitorDto> getAllPersons() {
		return service.getAllVisitors().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/visitor/{libraryCardId}", "/visitor/{libraryCardId}/" })
	public VisitorDto createVisitor(@PathVariable("libraryCardId") int libraryCardId,
			@RequestParam String name, @RequestParam String username, @RequestParam String address, @RequestParam int demeritPoints)
					throws IllegalArgumentException {
		Visitor visitor = service.createVisitor(name, username, address, libraryCardId, demeritPoints);
		return convertToDto(visitor);
	}
	
	@GetMapping(value = { "/reservations/visitor/{libraryCardId}", "/reservation/visitor/{libraryCardId}/" })
	public List<ReservationDto> getReservationsOfVisitor(@PathVariable("libraryCardId") VisitorDto vDto) {
		Visitor v = convertToDomainObject(vDto);
		return createReservationDtosForVisitor(v);
	}
	
	@GetMapping(value = { "/events/visitor/{libraryCardId}", "/events/visitor/{libraryCardId}/" })
	public List<EventDto> getEventsOfVisitor(@PathVariable("libraryCardId") VisitorDto vDto) {
		Visitor v = convertToDomainObject(vDto);
		return createEventDtosForVisitor(v);
	}

	
	// EVENT //
	
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

	
	// RESERVATION //
	
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
			@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam Visitor visitor, @RequestParam InventoryItem inventoryItem)
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
			ReservationDto reservation = new ReservationDto(r.getStartDate(), r.getEndData(), r.getVisitor(), r.getInventoryItem(), r.getReservationID());
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
}
