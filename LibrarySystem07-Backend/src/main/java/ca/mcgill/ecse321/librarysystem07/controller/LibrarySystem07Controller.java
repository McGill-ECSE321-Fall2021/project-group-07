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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	/*
	 * * * * * * * * * * * * * * headlibrarian controllers * * * * * * * * * * * * * *
	 */

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/headLibrarian/{libraryCardID}?name={String}&username={String}
	 * &address={String}
	 *
	 * Using post methods, we can create a head librarian with a library card id, name,
	 * username and address
	 *
	 * @param Integer libraryCardID, primary identifier for head librarian
	 * @param String name, name of head librarian
	 * @param String username, username for which the head librarian can use to enter the system
	 * @param String address, address of residence (within city or outside?)
	 *
	 * @return head librarian Dto
	 */

	@PostMapping(value = { "/headLibrarian/{libraryCardID}", "/headLibrarian/{libraryCardID}/" })
	public HeadLibrarianDto createHeadLibrarian(@PathVariable("libraryCardID") Integer libraryCardID,
			@RequestParam(name = "name") String name, @RequestParam(name = "username") String username, @RequestParam(name = "address") String address)
		throws IllegalArgumentException {

		HeadLibrarian headLibrarian = service.createHeadLibrarian(name, username, address, libraryCardID);
		return convertToDto(headLibrarian);
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/headLibrarian
	 *
	 * Using get query method, we can get the head librarian existing in the library system.
	 * Since there is only one head librarian, we get the first librarian in the list of all
	 * librarians... no need for getAll method
	 *
	 * @return head librarian Dto
	 */

	@GetMapping(value = { "/headLibrarian", "/headLibrarian/" })
	public HeadLibrarianDto getHeadLibrarian() {

		if (service.getAllHeadLibrarians().isEmpty()) {
			return null;
		}

		return convertToDto(service.getAllHeadLibrarians().get(0));
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/headLibrarian
	 *
	 * Using delete query method, we delete the head librarian from the library system.
	 * Since there is only one, we can just call delete all and therefore no ID is required
	 */

	@DeleteMapping(value = { "/headLibrarian", "/headLibrarian/" })
	public void deleteHeadLibrarian()
		throws IllegalArgumentException {

		service.deleteHeadLibrarian();
	}

	/*

	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/headLibrarian?address={String}
	 *
	 * Using put methods, we can update a head librarian address with a new input address
	 * no need for id since there is only one head librarian in system
	 *
	 * @param String address, address of residence (within city or outside?)
	 */

	@PutMapping(value = { "/headLibrarian/{libraryCardID}", "/headLibrarian/{libraryCardID}/" })
	public void updateHeadLibrarianAddress(@PathVariable("libraryCardID") Integer libraryCardID,
			@RequestParam(name = "address") String address)
		throws IllegalArgumentException {

		if (service.getAllHeadLibrarians().isEmpty()) {
			return;
		}

		service.updateHeadLibrarianAddress(service.getAllHeadLibrarians().get(0), address);
	}

	/*
   * * * * * * * * * * * * * * * librarian controllers * * * * * * * * * * * * * * >>>>>>> master
	 */

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarians/{libraryCardID}?name={String}&username={String}
	 * &address={String}
	 *
	 * Using post methods, we can create a librarian with a library card id, name,
	 * username and address
	 *
	 * @param Integer libraryCardID, primary identifier for librarian
	 * @param String name, name of librarian
	 * @param String username, username for which the librarian can use to enter the system
	 * @param String address, address of residence (within city or outside?)
	 *
	 * @return librarian Dto
	 */

	@PostMapping(value = { "/librarians/{libraryCardID}", "/librarians/{libraryCardID}/" })
	public LibrarianDto createLibrarian(@PathVariable("libraryCardID") Integer libraryCardID,
			@RequestParam(name = "name") String name, @RequestParam(name = "username") String username, @RequestParam(name = "address") String address)
		throws IllegalArgumentException {

		Librarian librarian = service.createLibrarian(name, username, address, libraryCardID);
		return convertToDto(librarian);
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarians
	 *
	 * Using get query method, we can get a all librarians existing in the library system
	 *
	 * @return list of librarian Dtos
	 */

	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<LibrarianDto> getAllLibrarians() {
		List<LibrarianDto> librarianDtos = new ArrayList<>();
		for (Librarian librarian : service.getAllLibrarians()) {
			librarianDtos.add(convertToDto(librarian));
		}
		return librarianDtos;
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarians/{libraryCardId}
	 *
	 * Using get query method, we can get a specific librarian existing in the library system
	 * by their library card id
	 *
	 * @param int libraryCardId, when we enter the library card ID, we query through all the
	 * 		  librarians and return the corresponding librarians
	 *
	 * @return librarian Dto whith library card Id {libraryCardId}
	 */

	@GetMapping(value = { "/librarians/{libraryCardId}", "/librarians/{libraryCardId}" })
	public LibrarianDto getLibrarianByLibraryCardId(@PathVariable("libraryCardId") Integer libraryCardId)
			throws IllegalArgumentException {
			return convertToDto(service.getLibrarian(libraryCardId));
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarian/{libraryCardID}
	 *
	 * Using delete query method, we delete the librarian from the library system.
	 *
	 * @param Integer libraryCardID, card of the librarian
	 */

	@DeleteMapping(value = { "/librarians/{libraryCardID}", "/librarians/{libraryCardID}/" })
	public void deleteLibrarian(@PathVariable("libraryCardID") Integer libraryCardID)
		throws IllegalArgumentException {

		service.deleteLibrarian(libraryCardID);
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarian
	 *
	 * Using delete query method, we delete all the librarians from the library system.
	 */

	@DeleteMapping(value = { "/librarians", "/librarians/" })
	public void deleteLibrarian()
		throws IllegalArgumentException {
		service.deleteAllLibrarians();
	}

	/*

	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarians/{libraryCardID}?address={String}
	 *
	 * Using put methods, we can update a librarian address with a new input address
	 * We can find the librarian by their ID and update from there.
	 *
	 * @param Integer libraryCardID, primary identifier for librarian
	 * @param String address, address of residence (within city or outside?)
	 */

	@PutMapping(value = { "/librarians/{libraryCardID}", "/librarians/{libraryCardID}/" })
	public void updateLibrarianAddress(@PathVariable("libraryCardID") Integer libraryCardID,
			@RequestParam(name = "address") String address)
		throws IllegalArgumentException {
		service.updateLibrarianAddress(service.getLibrarian(libraryCardID), address);
	}

	/*
	 * * * * * * * * * * * * * * * headlibrarianTimeSlot controllers * * * * * * * * * * * * * *
	 */

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/headLibrarianTimeSlots/?dayOfWeek={dayOfTheWeek}&startTime={HH:mm}
	 * &endTime={HH:mm}&headLibrarian={libraryCardId}
	 *
	 * Using post methods, we can create a Head librarian time slot with a day of week, start time,
	 * endtime and head librarian
	 *
	 * @param String dayOfTheWeek, scheduleHeadLibrarian implements functionality to convert
	 * 		  String day of week to the enum type within the Dto class
	 * 		  specifies a day for which the head librarian will work (Schedules work by adding
	 * 		  shifts to a weekly schedule which is repeated week after week)
	 * @param LocalTime startTime, takes time in the format HH:mm, then parsing local time will
	 * 		  will return the Time object we need as input for the create method
	 * @param Local endTime, same as startTime but we specify endtime of work day
	 * @param HeadLibrarianDto headLibrarian, assign the head librarian to the schedule
	 *
	 * within the HeadLibrarianDto class, there is an end point which initializes the ID which
	 * it can be called by within a request param
	 *
	 * @return headLibrarianTimeSlots Dto
	 */

	@PostMapping(value = { "/headLibrarianTimeSlots/{headLibrarianTimeSlotId}", "/headLibrarianTimeSlots/{headLibrarianTimeSlotId}/" })
	public HeadLibrarianTimeSlotDto scheduleHeadLibrarian(@PathVariable("headLibrarianTimeSlotId") Integer headLibrarianTimeSlotId,
	@RequestParam(name = "dayOfWeek") String dayOfWeek,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime)
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

		HeadLibrarian hldto = service.getAllHeadLibrarians().get(0);
		HeadLibrarianTimeSlot headLibrarianTimeSlot = service.createHeadLibrarianTimeSlot(headLibrarianTimeSlotId, hldto, Time.valueOf(startTime), Time.valueOf(endTime), weekDay);
		return convertToDto(headLibrarianTimeSlot, hldto);
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/headLibrarianTimeSlots
	 *
	 * Using get query method, we can get the schedule for head librarian
	 * Since there is only one head librarian, getting all the headLibrarianTimeSlots will
	 * only return schedule for head librarian
	 *
	 * @return list of headLibrarianTimeSlots Dto
	 */

	@GetMapping(value = { "/headLibrarianTimeSlots", "/headLibrarianTimeSlots/" })
	public List<HeadLibrarianTimeSlotDto> getAllHeadLibrarianTimeSlots() {
		List<HeadLibrarianTimeSlotDto> headLibrarianTimeSlotDtos = new ArrayList<>();
		for (HeadLibrarianTimeSlot hlts : service.getAllHeadLibrarianTimeSlots()) {
			headLibrarianTimeSlotDtos.add(convertToDto(hlts, hlts.getHeadLibrarian()));
		}
		return headLibrarianTimeSlotDtos;
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/headLibrarianTimeSlots/{headLibrarianTimeSlotId}
	 *
	 * Using get query method, we can get a specific headLibrarianTimeSlots existing in the library system
	 * by their headLibrarianTimeSlotId
	 *
	 * @param int headLibrarianTimeSlotId, when we enter the headLibrarianTimeSlotId, we query through all the
	 * 		  headLibrarianTimeSlots and return the corresponding headLibrarianTimeSlots
	 *
	 * @return librarian time slot Dto with headLibrarianTimeSlots id {libraryCardId}
	 */

	@GetMapping(value = { "/headLibrarianTimeSlots/{headLibrarianTimeSlotId}", "/headLibrarianTimeSlots/{headLibrarianTimeSlotId}" })
	public HeadLibrarianTimeSlotDto getheadLibrarianTimeSlotsByHeadLibrarianTimeSlotsId(@PathVariable("headLibrarianTimeSlotId") Integer headLibrarianTimeSlotId)
			throws IllegalArgumentException {
			return convertToDto(service.getHeadLibrarianTimeSlot(headLibrarianTimeSlotId), service.getHeadLibrarianTimeSlot(headLibrarianTimeSlotId).getHeadLibrarian());
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/headLibrarianTimeSlots/{headLibrarianTimeSlotId}
	 *
	 * Using delete query method, we delete the head librarian time slot from the library system.
	 *
	 * @param Integer headLibrarianTimeSlotId, id of the head librarian time slot
	 */

	@DeleteMapping(value = { "/headLibrarianTimeSlots/{headLibrarianTimeSlotId}", "/headLibrarianTimeSlots/{headLibrarianTimeSlotId}/" })
	public void deleteHeadLibrarianTimeSlot(@PathVariable("headLibrarianTimeSlotId") Integer headLibrarianTimeSlotId)
		throws IllegalArgumentException {

		service.deleteHeadLibrarianTimeSlot(headLibrarianTimeSlotId);
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/headLibrarianTimeSlots
	 *
	 * Using delete query method, we delete all the head librarian time slots from the library system.
	 */

	@DeleteMapping(value = { "/headLibrarianTimeSlots", "/headLibrarianTimeSlots/" })
	public void deleteAllHeadLibrarianTimeSlots()
		throws IllegalArgumentException {
		service.deleteAllHeadLibrarianTimeSlots();
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/headLibrarianTimeSlots/{headLibrarianTimeSlotId}?updateTime={HH:mm}&type={String}&dayOfWeek={String}
	 * http://localhost:8080/headLibrarianTimeSlots/{headLibrarianTimeSlotId}?updateTime={HH:mm}&type={String}
	 * http://localhost:8080/headLibrarianTimeSlots/{headLibrarianTimeSlotId}?dayOfWeek={String}
	 *
	 * Using put methods, we can update a head librarian time slot start time with a new start time, or end time, or day of week.
	 * We can find the head librarian time slot by it's ID and update from there.
	 *
	 * @param Integer headLibrarianTimeSlotId, primary identifier for librarian
	 * @param LocalTime startOrEnd, time we wish to update
	 * @param String startOrEnd, spesify weather start time or end time will be updated, only choice of input
	 *			     is "start" or "end"
	 * @param Strign dayOfWeek, weekday we wish to update, then transformed into enum type
	 */

	@PutMapping(value = { "/headLibrarianTimeSlots/{headLibrarianTimeSlotId}", "/headLibrarianTimeSlots/{headLibrarianTimeSlotId}/" })
	public void updateHeadLibrarianTimeSlot(@PathVariable("headLibrarianTimeSlotId") Integer headLibrarianTimeSlotId,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime updateTime,
			@RequestParam(required = false, name = "type") String startOrEnd,
			@RequestParam(required = false, name = "dayOfWeek") String dayOfWeek)
		throws IllegalArgumentException {

		if (updateTime != null && startOrEnd != null) {

			if (startOrEnd.equalsIgnoreCase("start")) {
				service.updateHeadLibrarianTimeSlotStartTime(service.getHeadLibrarianTimeSlot(headLibrarianTimeSlotId),Time.valueOf(updateTime));
			}

			if (startOrEnd.equalsIgnoreCase("end")) {
				service.updateHeadLibrarianTimeSlotEndTime(service.getHeadLibrarianTimeSlot(headLibrarianTimeSlotId),Time.valueOf(updateTime));
			}
		}

		if (dayOfWeek != null) {

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

			service.updateHeadLibrarianTimeSlotDayOfWeek(service.getHeadLibrarianTimeSlot(headLibrarianTimeSlotId), weekDay);
		}

	}

	/*
	 * * * * * * * * * * * * * * * librarianTimeSlot controllers * * * * * * * * * * * * * *
	 */

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots/?dayOfWeek={dayOfTheWeek}&startTime={HH:mm}
	 * &endTime={HH:mm}&librarian={libraryCardId}
	 *
	 * Using post methods, we can create a librarian time slot with a day of week, start time,
	 * endtime and librarian
	 *
	 * @param String dayOfTheWeek, scheduleLibrarian implements functionality to convert
	 * 		  String day of week to the enum type within the Dto class
	 * 		  specifies a day for which the librarian will work (Schedules work by adding
	 * 		  shifts to a weekly schedule which is repeated week after week)
	 * @param LocalTime startTime, takes time in the format HH:mm, then parsing local time will
	 * 		  will return the Time object we need as input for the create method
	 * @param Local endTime, same as startTime but we specify endtime of work day
	 * @param LibrarianDto Librarian, assign the librarian to the schedule
	 *
	 * within the LibrarianDto class, there is an end point which initializes the ID which
	 * it can be called by within a request param
	 *
	 * @return librarianTimeSlots Dto
	 */

	@PostMapping(value = { "/librarianTimeSlots/{librarianTimeSlotId}", "/librarianTimeSlots/{librarianTimeSlotId}" })
	public LibrarianTimeSlotDto scheduleLibrarian(@PathVariable("librarianTimeSlotId") Integer librarianTimeSlotId,
	@RequestParam(name = "dayOfWeek") String dayOfWeek,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime,
	@RequestParam(name = "librarianLibraryCardId") Integer librarianLibraryCardId)
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

		Librarian librarian = service.getLibrarian(librarianLibraryCardId);
		LibrarianTimeSlot librarianTimeSlot = service.createLibrarianTimeSlot(librarianTimeSlotId, librarian, Time.valueOf(startTime), Time.valueOf(endTime), weekDay);
		return convertToDto(librarianTimeSlot, librarian);
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots
	 *
	 * Using get query method, we can get the schedule for all librarian in the library system
	 *
	 * @return list of librarianTimeSlots Dto
	 */

	@GetMapping(value = { "/librarianTimeSlots", "/librarianTimeSlots/" })
	public List<LibrarianTimeSlotDto> getAllLibrarianTimeSlots() {
		List<LibrarianTimeSlotDto> librarianTimeSlotDtos = new ArrayList<>();
		for (LibrarianTimeSlot lts : service.getAllLibrarianTimeSlots()) {
			librarianTimeSlotDtos.add(convertToDto(lts, lts.getLibrarian()));
		}
		return librarianTimeSlotDtos;
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots/{librarianTimeSlotId}
	 *
	 * Using get query method, we can get a specific librarianTimeSlots existing in the library system
	 * by their librarianTimeSlotId
	 *
	 * @param int librarianTimeSlotId, when we enter the librarianTimeSlotId, we query through all the
	 * 		  librarianTimeSlots and return the corresponding librarianTimeSlots
	 *
	 * @return librarian time slot Dto with librarianTimeSlots id {librarianTimeSlotId}
	 */

	@GetMapping(value = { "/librarianTimeSlots/{librarianTimeSlotId}", "/librarianTimeSlots/{librarianTimeSlotId}" })
	public LibrarianTimeSlotDto getLibrarianTimeSlotsByHeadLibrarianTimeSlotsId(@PathVariable("librarianTimeSlotId") Integer librarianTimeSlotId)
			throws IllegalArgumentException {
			return convertToDto(service.getLibrarianTimeSlot(librarianTimeSlotId), service.getLibrarianTimeSlot(librarianTimeSlotId).getLibrarian());
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots/librarians/{libraryCardId}
	 *
	 * Using get custom query method, we can get the schedule for a specific librarian by
	 * their library card id
	 *
	 * @param int libraryCardId, when we enter the library card ID, we query through all the
	 * 		  librarians within librarianTimeSlots and return the corresponding librarians
	 *
	 * within the LibrarianDto class, there is an end point which initializes the ID which
	 * it can be called from within a request param
	 *
	 * @return list of librarianTimeSlots Dto
	 */

	@GetMapping(value = { "/librarianTimeSlots/librarians/{libraryCardID}", "/librarianTimeSlots/librarians/{libraryCardID}/" })
	public List<LibrarianTimeSlotDto> getLibrarianSchedule(@PathVariable("libraryCardID") Integer libraryCardID) {
		Librarian l = service.getLibrarian(libraryCardID);
		List<LibrarianTimeSlotDto> libSched = new ArrayList<LibrarianTimeSlotDto>();
		for (LibrarianTimeSlot lts : service.getLibrarianTimeSlotByLibrarian(l)) {
			libSched.add(convertToDto(lts, l));
		}
		return libSched;
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots/{librarianTimeSlotId}
	 *
	 * Using delete query method, we delete the librarian time slot from the library system.
	 *
	 * @param Integer librarianTimeSlotId, id of the librarian time slot
	 */

	@DeleteMapping(value = { "/librarianTimeSlots/{librarianTimeSlotId}", "/librarianTimeSlots/{librarianTimeSlotId}/" })
	public void deleteLibrarianTimeSlot(@PathVariable("librarianTimeSlotId") Integer librarianTimeSlotId)
		throws IllegalArgumentException {

		service.deleteLibrarianTimeSlot(librarianTimeSlotId);
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots
	 *
	 * Using delete query method, we delete all the librarian time slots from the library system.
	 */

	@DeleteMapping(value = { "/librarianTimeSlots", "/librarianTimeSlots/" })
	public void deleteAllLibrarianTimeSlots()
		throws IllegalArgumentException {
		service.deleteAllLibrarianTimeSlots();
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots/librarians/{libraryCardID}
	 *
	 * Using delete query method, we delete all working days for a given the librarians
	 * in the library system. (remove all time slots form library system)
	 */

	@DeleteMapping(value = { "/librarianTimeSlots/librarians/{libraryCardID}", "/librarianTimeSlots/librarians/{libraryCardID}/" })
	public void deleteLibrarianSchedule(@PathVariable("libraryCardID") Integer libraryCardID)
		throws IllegalArgumentException {

		Librarian librarian = service.getLibrarian(libraryCardID);
		service.deleteLibrarianSchedule(librarian);
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots/{librarianTimeSlotId}?updateTime={HH:mm}&type={String}&dayOfWeek={String}&librarianId={Integer}
	 *
	 * Using put methods, we can update a librarian time slot start time, end time, day of week
	 * and librarian. Done with optional parameters, for example can update dayOfWeek and Start time, but
	 * not end time or librarian. Only restriction is only one time based parameter can be updated
	 * at a time (either start time or end time).
	 *
	 * We can find the librarian time slot by it's ID and update from there.
	 *
	 * @param Integer librarianTimeSlotId, primary identifier for librarian
	 * @param LocalTime updateTime, time we wish to update
	 * @param String startOrEnd, specify type of operation for time, ie update start time or end time
	 * 							 only two parameters aloud are "start" or "end"
	 * @param String dayOfWeek, weekday we wish to update, then transformed into enum type
	 * @param Integer librarianId, id of the librarian we want reassign the timeslot to
	 */

	@PutMapping(value = { "/librarianTimeSlots/{librarianTimeSlotId}", "/librarianTimeSlots/{librarianTimeSlotId}/" })
	public void updateLibrarianTimeSlot(@PathVariable("librarianTimeSlotId") Integer librarianTimeSlotId,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime updateTime,
			@RequestParam(required = false, name = "type") String startOrEnd,
			@RequestParam(required = false, name = "dayOfWeek") String dayOfWeek,
			@RequestParam(required = false, name = "librarianId") Integer librarianId)
		throws IllegalArgumentException {

		if (updateTime != null && startOrEnd != null) {

			if (startOrEnd.equalsIgnoreCase("start")) {
				service.updateLibrarianTimeSlotStartTime(service.getLibrarianTimeSlot(librarianTimeSlotId),Time.valueOf(updateTime));
			}

			if (startOrEnd.equalsIgnoreCase("end")) {
				service.updateLibrarianTimeSlotEndTime(service.getLibrarianTimeSlot(librarianTimeSlotId),Time.valueOf(updateTime));
			}
		}

		if (dayOfWeek != null) {

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

			service.updateLibrarianTimeSlotDayOfWeek(service.getLibrarianTimeSlot(librarianTimeSlotId), weekDay);
		}

		if (librarianId != null) {
			service.updateLibrarianTimeSlotLibrarian(service.getLibrarianTimeSlot(librarianTimeSlotId), service.getLibrarian(librarianId));
		}
	}


	/*
	 * * * * * * * * * * * * * * * Visitor Controllers * * * * * * * * * * * * * *
	 */


	/**
	 * URL :  http://localhost:8085/visitors
	 * Queries database for all visitors in location <visitors>
	 * @return iterable list of all visitors
	 */
	@GetMapping(value = { "/visitors/", "/visitors/" })
	public List<VisitorDto> getAllVisitors() {
		return service.getAllVisitors().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}

	/**
	 * URL :  http://localhost:8085/visitors/{libraryCardId}
	 * Queries database location <visitors> for the visitor with id {libraryCardId}
	 *
	 * @param libraryCardId
	 * @param name
	 * @param username
	 * @param address
	 * @param demeritPoints
	 * @return visitor DTO
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/visitors/{libraryCardId}", "/visitor/{libraryCardId}/" })
	public VisitorDto createVisitor(@PathVariable("libraryCardId") Integer libraryCardId,
			@RequestParam String name,
			@RequestParam String username, @RequestParam String address, @RequestParam Integer demeritPoints)
					throws IllegalArgumentException {
		Visitor visitor = service.createVisitor(name, username, address, libraryCardId, demeritPoints);
		return convertToDto(visitor);
	}

	/**
	 *
	 * @param VisitorDto vDto
	 * @return List<ReservationDto> of all reservations for visitor vDto
	 */
	@GetMapping(value = { "/reservations/visitor/{libraryCardId}", "/reservations/visitor/{libraryCardId}/" })
	public List<ReservationDto> getReservationsOfVisitor(@PathVariable("libraryCardId") VisitorDto vDto) {
		Visitor v = convertToDomainObject(vDto);
		return createReservationDtosForVisitor(v);
	}

	/**
	 *
	 * @param VisitorDto vDto
	 * @return iterable list of event DTO of all events created by visitor vDto
	 */
	@GetMapping(value = { "/events/visitor/{libraryCardId}", "/events/visitor/{libraryCardId}/" })
	public List<EventDto> getEventsOfVisitor(@PathVariable("libraryCardId") VisitorDto vDto) {
		Visitor v = convertToDomainObject(vDto);
		return createEventDtosForVisitor(v);
	}

	/**
	 * URL :  http://localhost:8085/visitors/{visitorId}
	 * Update address of visitor with the parameter requested.
	 * @param visitorId
	 * @param address
	 * @param balance
	 * @param demeritPoints
	 */
	@PutMapping(value="/visitors/{visitorId}")
	public void updateVisitorAddress(@PathVariable("visitorId") Integer visitorId,
			@RequestParam(required = false, name="address") String address,
			@RequestParam(required = false, name="balance") Float balance,
			@RequestParam(required = false, name="demeritPoints") Integer points) {
		Visitor v = service.getVisitor(visitorId);

		if (address != null) {
			service.updateVisitorAddress(v, address);
		}

		if (balance != null) {
			service.updateVisitorBalance(v, balance);
		}

		if (points != null) {
			service.updateVisitorDemeritPoints(v, points);
		}
	}

	/**
	 * URL :  http://localhost:8085/visitors/{visitorId}
	 * Delete mapping method to delete a visitor with ID visitorId from system
	 *
	 * @param libraryCardId
	 */
	@DeleteMapping(value="/visitors/{libraryCardId}")
	public void deleteVisitor(@PathVariable("libraryCardId") Integer libraryCardId) {
		service.deleteVisitor(libraryCardId);
	}


	/*
	 * * * * * * * * * * * * * * * * Event controller methods * * * * * * * * * * * * * * * *
	 * /

	/**
	 * URL :  http://localhost:8085/events
	 * Fetches everything in location <events>
	 * @return iterable list of all events
	 */
	@GetMapping(value = { "/events/", "/events/" })
	public List<EventDto> getAllEvents() {
		List<EventDto> eventDtos = new ArrayList<>();
		for (Event event : service.getAllEvents()) {
			eventDtos.add(convertToDto(event));
		}
		return eventDtos;
	}

	/**
	 * URL :  http://localhost:8085/events/{eventId}
	 * Creates new event at location <events>.
	 * @param eventId
	 * @param name
	 * @param visitor
	 * @return new event for visitor
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/events/{eventId}", "/events/{eventId}/" })
	public EventDto createEvent(@PathVariable("eventId") Integer eventId, @RequestParam String name,
			@RequestParam Integer visitorId)
					throws IllegalArgumentException {
		Event event = service.createEvent(name, eventId, service.getVisitor(visitorId));
		return convertToDto(event);
	}

	/**
	 * URL :  http://localhost:8085/events/{eventId}
	 * Delete mapping method to delete event from location <events? found by its ID {eventId}	 
	 * @param eventId
	 */
	@DeleteMapping(value="/events/{eventId}")
	public void deleteEvent(@PathVariable("eventId") Integer eventId) {
		service.deleteEvent(eventId);
	}


	/* * * * * * * * * * * * * * * * Reservation controller methods * * * * * * * * * * * * * * * */

	/**
	 *
	 * @return iterable list of all reservation DTOs
	 */
	@GetMapping(value = { "/reservations/", "/reservations/" })
	public List<ReservationDto> getAllReservations() {
		List<ReservationDto> reservationDtos = new ArrayList<>();
		for (Reservation r : service.getAllReservations()) {
			reservationDtos.add(convertToDto(r));
		}
		return reservationDtos;
	}

	/**
	 *
	 * @param reservationId
	 * @param startDate
	 * @param endDate
	 * @param visitor
	 * @param inventoryItem
	 * @return new reservation
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/reservations/{reservationId}", "/reservations/{reservationId}/" })
	public ReservationDto createReservation(@PathVariable("reservationId") Integer reservationId, 
			@RequestParam Date startDate, 
			@RequestParam Date endDate, @RequestParam Integer visitorId, @RequestParam Integer inventoryItemId)
					throws IllegalArgumentException {
		Reservation r = service.createReservation(reservationId, startDate, endDate, 
				service.getVisitor(visitorId), service.getInventoryItem(inventoryItemId));
		return convertToDto(r);
	}

	/**
	 *
	 * @param visitor v
	 * @return list of reservations created for visitor
	 */
	@PostMapping(value = { "/reservations", "/reservations"})
	private List<ReservationDto> createReservationDtosForVisitor(Visitor v) {
		List<Reservation> reservations = service.getReservationsForVisitor(v);
		List<ReservationDto> reservationsDto = new ArrayList<>();
		for (Reservation r : reservations) {
			reservationsDto.add(convertToDto(r));
		}
		return reservationsDto;
	}

	/**
	 *
	 * @param visitor v
	 * @return list of events created by visitor
	 */
	@PostMapping(value = { "/visitor/reservations", "/visitor/reservations"})
	private List<EventDto> createEventDtosForVisitor(Visitor v) {
		List<Event> events = service.getEventsOfVisitor(v);
		List<EventDto> eventsDto = new ArrayList<>();
		for (Event e : events) {
			eventsDto.add(convertToDto(e));
		}
		return eventsDto;
	}

	/**
	 * Update (for example, extend) the end date of a reservation.
	 * @param reservationId
	 * @param endDate
	 */
	@PutMapping(value="/reservations/{reservationId}")
	public void updateReservation(@PathVariable("reservationId") Integer reservationId, @RequestParam(name="endDate") Date endDate) {
		service.updateReservationEndDate(service.getReservation(reservationId), endDate);
	}

	/**
	 * Delete mapping for deleting a reservation from system
	 * @param reservationId
	 */
	@DeleteMapping(value="/reservations/{reservationId}")
	public void deleteReservation(@PathVariable("reservationId") Integer reservationId) {
		service.deleteReservation(reservationId);
	}


	/*

	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots/{librarianTimeSlotId}?startTime={HH:mm}
	 *
	 * Using put methods, we can update a librarian time slot start time with a new start time
	 * We can find the librarian time slot by it's ID and update from there.
	 *
	 * @param Integer librarianTimeSlotId, primary identifier for librarian
	 * @param LocalTime startTime, time we wish to update
	 */

	@PutMapping(value = { "/librarianTimeSlots/{librarianTimeSlotId}", "/librarianTimeSlots/{librarianTimeSlotId}/" })
	public void updateLibrarianTimeSlotStartTime(@PathVariable("librarianTimeSlotId") Integer librarianTimeSlotId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime)
		throws IllegalArgumentException {
		service.updateLibrarianTimeSlotStartTime(service.getLibrarianTimeSlot(librarianTimeSlotId),Time.valueOf(startTime));
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots/{librarianTimeSlotId}?endTime={HH:mm}
	 *
	 * Using put methods, we can update a librarian time slot end time with a new end time
	 * We can find the librarian time slot by it's ID and update from there.
	 *
	 * @param Integer librarianTimeSlotId, primary identifier for librarian
	 * @param LocalTime endTime, time we wish to update
	 */

	@PutMapping(value = { "/librarianTimeSlots/{librarianTimeSlotId}", "/librarianTimeSlots/{librarianTimeSlotId}/" })
	public void updateLibrarianTimeSlotEndTime(@PathVariable("librarianTimeSlotId") Integer librarianTimeSlotId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime)
		throws IllegalArgumentException {
		service.updateLibrarianTimeSlotEndTime(service.getLibrarianTimeSlot(librarianTimeSlotId),Time.valueOf(endTime));
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots/{librarianTimeSlotId}?dayOfWeek={String}
	 *
	 * Using put methods, we can update a librarian time slot day of the week with a new weekday
	 * We can find the librarian time slot by it's ID and update from there.
	 *
	 * @param Integer librarianTimeSlotId, primary identifier for librarian
	 * @param String dayOfWeek, weekday we wish to update, then transformed into enum type
	 */

	@PutMapping(value = { "/librarianTimeSlots/{librarianTimeSlotId}", "/librarianTimeSlots/{librarianTimeSlotId}/" })
	public void updateLibrarianTimeSlotDay(@PathVariable("librarianTimeSlotId") Integer librarianTimeSlotId,
			@RequestParam(name = "dayOfWeek") String dayOfWeek)
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

		service.updateLibrarianTimeSlotDayOfWeek(service.getLibrarianTimeSlot(librarianTimeSlotId), weekDay);
	}

	/*
	 * Calling RESTful service endpoints
	 *
	 * http://localhost:8080/librarianTimeSlots/{librarianTimeSlotId}?librarian={Integer}
	 *
	 * Using put methods, we can update a librarian time slot assigned librarian with a new librarian
	 * We can find the librarian time slot by it's ID and update from there.
	 *
	 * @param Integer librarianTimeSlotId, primary identifier for librarian
	 * @param LibrarianDto lDto, new librarian for time slot
	 */

	@PutMapping(value = { "/librarianTimeSlots/{librarianTimeSlotId}", "/librarianTimeSlots/{librarianTimeSlotId}/" })
	public void updateLibrarianTimeSlotLibrarian(@PathVariable("librarianTimeSlotId") Integer librarianTimeSlotId,
			@RequestParam(name = "librarian") LibrarianDto lDto)
		throws IllegalArgumentException {
		service.updateLibrarianTimeSlotLibrarian(service.getLibrarianTimeSlot(librarianTimeSlotId), convertToDomainObject(lDto));
	}

	/*
	 * * * * * * * * * * * * * * * Inventory Item controllers * * * * * * * * * * * * * *
	 */

	/**
	 * http://localhost:8085/inventoryitem
	 * Queries database location <inventoryitem>
	 * @return list of all inventory items as DTOs
	 */
	 @GetMapping(value = { "/inventoryitem", "/inventoryitem/"})
	 public List<InventoryItemDto> getAllInventoryItems(){
		 List<InventoryItemDto> inventoryItemDtos = new ArrayList<>();
		 for (InventoryItem inventoryItem : service.getAllInventoryItems()){
			 inventoryItemDtos.add(convertToDto(inventoryItem));
		 }
		 return inventoryItemDtos;
	 }

	 /**
	  * http://localhost:8085/inventoryitem/{inventoryItemID}?duplicates={Integer}&name={String}&author={String}&status={String}&type={String}
	  * Creates a new inventory item with ID inventoryItemID and parameters mapped to requestparams.
	  * @param InventoryItemID
	  * @param duplicates
	  * @param name
	  * @param author
	  * @param status
	  * @param type
	  * @return new inventory item
	  * @throws IllegalArgumentException
	  */
	 @PostMapping(value = {"/inventoryitem/{inventoryItemID}", "/inventoryitem/{inventoryItemID}/"})
	 public InventoryItemDto createInventoryItem(@PathVariable("inventoryItemID") Integer InventoryItemID, @RequestParam Integer duplicates, @RequestParam String name, @RequestParam String author, @RequestParam("status") String status, @RequestParam("type") String type) throws IllegalArgumentException{
		 InventoryItem.TypeOfItem typeOfItem = null;
		 if (type.equalsIgnoreCase("CD")){
			typeOfItem =  InventoryItem.TypeOfItem.CD;
		 } else if (type.equalsIgnoreCase("Movie")){
			typeOfItem =  InventoryItem.TypeOfItem.Movie;
		 } else if (type.equalsIgnoreCase("Book")){
			typeOfItem =  InventoryItem.TypeOfItem.Book;
		 } else if (type.equalsIgnoreCase("Newspaper")){
			typeOfItem =  InventoryItem.TypeOfItem.Newspaper;
		 } else if (type.equalsIgnoreCase("Archive")){
			typeOfItem =  InventoryItem.TypeOfItem.Archive;
		 }

		 InventoryItem.Status goodstatus = null;
		 if (status.equalsIgnoreCase("CheckedOut")){
			goodstatus = InventoryItem.Status.CheckedOut;
		 } else if (status.equalsIgnoreCase("OnReserve")){
			goodstatus = InventoryItem.Status.OnReserve;
		 } else if (status.equalsIgnoreCase("Available")){
			goodstatus = InventoryItem.Status.Available;
		 } else if (status.equalsIgnoreCase("Damaged")){
			goodstatus = InventoryItem.Status.Damaged;
		 }

		 InventoryItem inventoryItem = service.createInventoryItem(InventoryItemID, duplicates, name, author, goodstatus, typeOfItem);
		 return convertToDto(inventoryItem);

	 }

	 /**
	  * http://localhost:8085/inventoryitem/{inventoryItemID}
	  * @param inventoryItemID
	  * @return inventory item with id matching parameter.
	  * @throws IllegalArgumentException
	  */
	 @GetMapping(value = {"/inventoryitem/{inventoryItemID}", "/inventoryitem/{inventoryItemID}/"})
	 public InventoryItemDto getInventoryItemByInventoryItemID(@PathVariable("inventoryItemID") Integer inventoryItemID) throws IllegalArgumentException {
		 return convertToDto(service.getInventoryItem(inventoryItemID));
	 }

	/**
	* http://localhost:8085/inventoryitem/{inventoryItemID}
	* Delete map method deletes the item with ID inventoryItemID from location <inventoryitem>
	*/
	 @DeleteMapping(value = {"/inventoryitem/{inventoryItemID}", "/inventoryitem/{inventoryItemID}/"})
	 public void deleteInventoryItem(@PathVariable("inventoryItemID") Integer inventoryItemID) throws IllegalArgumentException{
		 InventoryItem inventoryItem = service.getInventoryItem(inventoryItemID);
		 service.deleteInventoryItem(inventoryItem);
	 }

	 /**
	  * http://localhost:8085/inventoryitem/{inventoryItemID}?duplicates={Integer}&name={String}&author={String}&status={String}
	  *
	  * @param InventoryItemID
	  * @param duplicates
	  * @param name
	  * @param author
	  * @param status
	  * @param type
	  * @return inventory item with id InventoryItemID with updated attributes
	  * @throws IllegalArgumentException
	  */
	 @PatchMapping(value = {"/inventoryitem/{inventoryItemID}", "/inventoryitem/{inventoryItemID}/"})
	 public InventoryItemDto updateInventoryItem(@PathVariable("inventoryItemID") Integer InventoryItemID, @RequestParam(required = false) Integer duplicates, @RequestParam(required = false) String name, @RequestParam(required = false) String author, @RequestParam(required = false) String status, @RequestParam(required = false) String type) throws IllegalArgumentException{
		 InventoryItem inventoryItem = service.getInventoryItem(InventoryItemID);
		 if (status != null){
			InventoryItem.Status goodstatus = null;
			if (status.equalsIgnoreCase("CheckedOut")){
				goodstatus = InventoryItem.Status.CheckedOut;
			} else if (status.equalsIgnoreCase("OnReserve")){
				goodstatus = InventoryItem.Status.OnReserve;
			} else if (status.equalsIgnoreCase("Available")){
				goodstatus = InventoryItem.Status.Available;
			} else if (status.equalsIgnoreCase("Damaged")){
				goodstatus = InventoryItem.Status.Damaged;
			}
			service.updateIventoryItemStatus(inventoryItem, goodstatus);
		 }

		 if (type != null){
			InventoryItem.TypeOfItem typeOfItem = null;
			if (type.equalsIgnoreCase("CD")){
			   typeOfItem =  InventoryItem.TypeOfItem.CD;
			} else if (type.equalsIgnoreCase("Movie")){
			   typeOfItem =  InventoryItem.TypeOfItem.Movie;
			} else if (type.equalsIgnoreCase("Book")){
			   typeOfItem =  InventoryItem.TypeOfItem.Book;
			} else if (type.equalsIgnoreCase("Newspaper")){
			   typeOfItem =  InventoryItem.TypeOfItem.Newspaper;
			} else if (type.equalsIgnoreCase("Archive")){
			   typeOfItem =  InventoryItem.TypeOfItem.Archive;
			}
			service.updateIventoryItemType(inventoryItem, typeOfItem);
		 }

		 if (duplicates != null){
			 service.updateIventoryItemDuplicate(inventoryItem, duplicates);
		 }
		 if (name != null){
			 service.updateIventoryItemName(inventoryItem, name);
		 }
		 if (author != null){
			 service.updateIventoryItemAuthor(inventoryItem, author);
		 }
		 return convertToDto(inventoryItem);
	 }



	/*
	 * MODEL TO DTO HELPER METHODS
	 */

	private InventoryItemDto convertToDto(InventoryItem inventoryItem){
		if (inventoryItem == null){
			throw new IllegalArgumentException("There is no such Inventory Item!");
		}

		InventoryItemDto inventoryItemDtos = new InventoryItemDto(inventoryItem.getInventoryItemID(), inventoryItem.getDuplicates(), inventoryItem.getName(), inventoryItem.getAuthor(), inventoryItem.getStatus(), inventoryItem.getType());
		return inventoryItemDtos;
	}
	private HeadLibrarianTimeSlotDto convertToDto(HeadLibrarianTimeSlot hlts, HeadLibrarian hl) {

		if (hl == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}

		HeadLibrarianDto headLibrarianDto = convertToDto(hl);
		HeadLibrarianTimeSlotDto headLibrarianTimeSlotDto = new HeadLibrarianTimeSlotDto(hlts.getHeadLibrarianTimeSlotId(), hlts.getStartTime(), hlts.getEndTime(), parseDayOfWeekHL(hlts.getDayOfTheWeek()), headLibrarianDto);
		return headLibrarianTimeSlotDto;
	}

	private LibrarianTimeSlotDto convertToDto(LibrarianTimeSlot lts, Librarian l) {

		if (lts == null) {
			throw new IllegalArgumentException("There is no such Librarian Time Slot!");
		}

		LibrarianDto librarianDto = convertToDto(l);
		LibrarianTimeSlotDto librarianTimeSlotDto = new LibrarianTimeSlotDto(lts.getLibrarianTimeSlotId(), lts.getStartTime(), lts.getEndTime(), parseDayOfWeekL(lts.getDayOfTheWeek()), librarianDto);
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
		return librarianDto;
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
