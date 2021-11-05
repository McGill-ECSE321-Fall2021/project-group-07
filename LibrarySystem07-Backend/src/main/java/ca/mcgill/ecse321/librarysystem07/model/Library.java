package ca.mcgill.ecse321.librarysystem07.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Library")
public class Library {

	private String name;
	private String city;
	private String phoneNumber;
	private List<LibrarianTimeSlot> librarianTimeSlots;
	private List<HeadLibrarianTimeSlot> headLibrarianTimeSlots;
	private List<InventoryItem> inventoryItems;
	private List<UserRole> users;


	public Library(String aName, String aCity, String aPhoneNumber)
	{
		setName(aName);
		setCity(aCity);
		setPhoneNumber(aPhoneNumber);
	}

	@Id
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@OneToMany(cascade={CascadeType.ALL})
	public List<LibrarianTimeSlot> getLibrarianTimeSlots() {
		return librarianTimeSlots;
	}

	public void setLibrarianTimeSlots(List<LibrarianTimeSlot> librarianTimeSlots) {
		this.librarianTimeSlots = librarianTimeSlots;
	}
	
	public void addLibrarianTimeSlot(LibrarianTimeSlot ts) {
		if (!librarianTimeSlots.contains(ts)) librarianTimeSlots.add(ts);
	}


	@OneToMany(cascade={CascadeType.ALL})
	public List<HeadLibrarianTimeSlot> getHeadLibrarianTimeSlots() {
		return headLibrarianTimeSlots;
	}

	public void setHeadLibrarianTimeSlots(List<HeadLibrarianTimeSlot> headLibrarianTimeSlots) {
		this.headLibrarianTimeSlots = headLibrarianTimeSlots;
	}
	
	public void addHeadLibrarianTimeSlot(HeadLibrarianTimeSlot ts) {
		if (!headLibrarianTimeSlots.contains(ts)) headLibrarianTimeSlots.add(ts);
	}

	@OneToMany(cascade={CascadeType.ALL})
	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public void addInventoryItem(InventoryItem aInventoryItem)
	{
		if (inventoryItems.contains(aInventoryItem)) { return; }
		aInventoryItem.setLibrary(this);
		inventoryItems.add(aInventoryItem);
	}

	public void removeInventoryItem(InventoryItem aInventoryItem)
	{
		if (!inventoryItems.contains(aInventoryItem)) { return; }
		inventoryItems.remove(aInventoryItem);
	}

	@OneToMany(cascade={CascadeType.ALL})
	public List<UserRole> getUsers() {
		return users;
	}

	public void setUsers(List<UserRole> users) {
		this.users = users;
	}
	
	public void addUser(UserRole user) {
		if (users.contains(user)) { return; }
		users.add(user);
	}
	public void removeUser(UserRole user) {
		if (users.contains(user)) {
			users.remove(user);
		}
	}

}
