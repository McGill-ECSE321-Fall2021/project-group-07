package ca.mcgill.ecse321.librarysystem07.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Library")
public class Library {

	private String name;
	private String city;
	private List<LibrarianTimeSlot> librarianSchedules;
	private List<HeadLibrarianTimeSlot> headLibrarianSchedules;
	private List<UserRole> users;
	private List<InventoryItem> inventoryItems;

	public Library(String aName, String aCity)
	{
		setName(aName);
		setCity(aCity);
		setUsers(new ArrayList<UserRole>());
		setLibrarianSchedules(new ArrayList<LibrarianTimeSlot>());
		setHeadLibrarianSchedules(new ArrayList<HeadLibrarianTimeSlot>());
		setInventoryItems(new ArrayList<InventoryItem>());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LibrarianTimeSlot> getLibrarianSchedules() {
		return librarianSchedules;
	}

	public void setLibrarianSchedules(List<LibrarianTimeSlot> librarianSchedules) {
		this.librarianSchedules = librarianSchedules;
	}

	public List<HeadLibrarianTimeSlot> getHeadLibrarianSchedules() {
		return headLibrarianSchedules;
	}

	public void setHeadLibrarianSchedules(List<HeadLibrarianTimeSlot> headLibrarianSchedules) {
		this.headLibrarianSchedules = headLibrarianSchedules;
	}

	public List<UserRole> getUsers() {
		return users;
	}

	public void setUsers(List<UserRole> users) {
		this.users = users;
	}

	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
