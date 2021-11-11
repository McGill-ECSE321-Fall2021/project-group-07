package ca.mcgill.ecse321.librarysystem07.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	/*
	 * headlibrarian controllers
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
	 * librarian controllers
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
	
	@DeleteMapping(value = { "/librarian/{libraryCardID}", "/librarian/{libraryCardID}/" })
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
	
	@DeleteMapping(value = { "/librarian/{libraryCardID}", "/librarian/{libraryCardID}/" })
	public void deleteLibrarian() 
		throws IllegalArgumentException {
		service.deleteAllLibrarians();
	}
	
	/*
	 * headlibrarianTimeSlot controllers
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
	@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime, 
	@RequestParam(name = "headLibrarian") HeadLibrarianDto headLibrarian)
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
	 * librarianTimeSlot controllers
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
	public List<LibrarianTimeSlotDto> getLibrarianSchedule(@PathVariable("libraryCardID") LibrarianDto lDto) {
		Librarian l = convertToDomainObject(lDto);
		return createLibrarianTimeSlotDtosForLibrarian(l);
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
	public void deleteLibrarianSchedule(@PathVariable("libraryCardID") LibrarianDto lDto) 
		throws IllegalArgumentException {
		
		Librarian librarian = convertToDomainObject(lDto);
		service.deleteLibrarianSchedule(librarian);
	}
	
	/*
	 * Inventory Item controllers
	 */
	
	 @GetMapping(value = { "/inventoryitem", "/inventoryitem/"})
	 public List<InventoryItemDto> getAllInventoryItems(){
		 List<InventoryItemDto> inventoryItemDtos = new ArrayList<>();
		 for (InventoryItem inventoryItem : service.getAllInventoryItems()){
			 inventoryItemDtos.add(convertToDto(inventoryItem));
		 }
		 return inventoryItemDtos;
	 }

	 @PostMapping(value = {"/inventoryitem/{inventoryItemID}", "/inventoryitem/{inventoryItemID}/"})
	 public InventoryItemDto createInventoryItem(@PathVariable("inventoryItemID") int InventoryItemID, @RequestParam int duplicates, @RequestParam String name, @RequestParam String author, @RequestParam("status") String status, @RequestParam("type") String type) throws IllegalArgumentException{
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

	 @GetMapping(value = {"/inventoryitem/{inventoryItemID}", "/inventoryitem/{inventoryItemID}/"})
	 public InventoryItemDto getInventoryItemByInventoryItemID(@PathVariable("inventoryItemID") int inventoryItemID) throws IllegalArgumentException {
		 return convertToDto(service.getInventoryItem(inventoryItemID));
	 }

	 @DeleteMapping(value = {"/inventoryitem/{inventoryItemID}", "/inventoryitem/{inventoryItemID}/"})
	 public void deleteInventoryItem(@PathVariable("inventoryItemID") int inventoryItemID) throws IllegalArgumentException{
		 InventoryItem inventoryItem = service.getInventoryItem(inventoryItemID);
		 service.deleteInventoryItem(inventoryItem);
	 }

	 @PatchMapping(value = {"/inventoryitem/{inventoryItemID}", "/inventoryitem/{inventoryItemID}/"})
	 public InventoryItemDto editInventoryItem(@PathVariable("inventoryItemID") int InventoryItemID, @RequestParam(required = false) int duplicates, @RequestParam(required = false) String name, @RequestParam(required = false) String author, @RequestParam(required = false) String status, @RequestParam(required = false) String type) throws IllegalArgumentException{
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

		 if (duplicates > 0){
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
