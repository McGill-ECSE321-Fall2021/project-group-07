package ca.mcgill.ecse321.librarysystem07.model;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import ca.mcgill.ecse321.librarysystem07.model.InventoryItem.TypeOfItem;

// line 2 "model.ump"
// line 95 "model.ump"
public class Library
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Library Attributes
  private String name;
  private String city;
  private String phoneNumber;

  //Library Associations
  private List<UserRole> userRoles;
  private List<InventoryItem> inventoryItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Library(String aName, String aCity, String aPhoneNumber)
  {
    name = aName;
    city = aCity;
    phoneNumber = aPhoneNumber;
    userRoles = new ArrayList<UserRole>();
    inventoryItems = new ArrayList<InventoryItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }
=======
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Library")
public class Library {
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa

	private String name;
	private String city;
	private String phoneNumber;
	private List<LibrarianTimeSlot> librarianTimeSlots;
	private List<HeadLibrarianTimeSlot> headLibrarianTimeSlots;
	private List<InventoryItem> inventoryItems;
	private List<UserRole> users;

<<<<<<< HEAD
  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
=======

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
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa

	public void setName(String name) {
		this.name = name;
	}

<<<<<<< HEAD
  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  /* Code from template association_GetMany */
  public UserRole getUserRole(int index)
  {
    UserRole aUserRole = userRoles.get(index);
    return aUserRole;
  }
=======
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa

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

<<<<<<< HEAD
  public int indexOfUserRole(UserRole aUserRole)
  {
    int index = userRoles.indexOf(aUserRole);
    return index;
  }
  /* Code from template association_GetMany */
  public InventoryItem getInventoryItem(int index)
  {
    InventoryItem aInventoryItem = inventoryItems.get(index);
    return aInventoryItem;
  }

  public List<InventoryItem> getInventoryItems()
  {
    List<InventoryItem> newInventoryItems = Collections.unmodifiableList(inventoryItems);
    return newInventoryItems;
  }
=======
	@OneToMany(cascade={CascadeType.ALL})
	public List<HeadLibrarianTimeSlot> getHeadLibrarianTimeSlots() {
		return headLibrarianTimeSlots;
	}
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa

	public void setHeadLibrarianTimeSlots(List<HeadLibrarianTimeSlot> headLibrarianTimeSlots) {
		this.headLibrarianTimeSlots = headLibrarianTimeSlots;
	}

	@OneToMany(cascade={CascadeType.ALL})
	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

<<<<<<< HEAD

  public boolean addUserRole(UserRole aUserRole)
  {
    boolean wasAdded = false;
    if (userRoles.contains(aUserRole)) { return false; }
    Library existingLibrary = aUserRole.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aUserRole.setLibrary(this);
    }
    else
    {
      userRoles.add(aUserRole);
    }
    wasAdded = true;
    return wasAdded;
  }
=======
	public void addInventoryItem(InventoryItem aInventoryItem)
	{
		if (inventoryItems.contains(aInventoryItem)) { return; }
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa

		aInventoryItem.setLibrary(this);
		inventoryItems.add(aInventoryItem);

<<<<<<< HEAD
  public boolean addOrMoveUserRoleAt(UserRole aUserRole, int index)
  {
    boolean wasAdded = false;
    if(userRoles.contains(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserRoleAt(aUserRole, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfInventoryItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public InventoryItem addInventoryItem(int aInventoryItemID, int aDuplicates, String aName, String aAuthor, InventoryItem.Status aStatus, TypeOfItem aType)
  {
    return new InventoryItem(aInventoryItemID, aDuplicates, aName, aAuthor, aStatus, aType, this);
  }
=======
	}
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa

	public void removeInventoryItem(InventoryItem aInventoryItem)
	{
		inventoryItems.remove(aInventoryItem);
	}

	@OneToMany(cascade={CascadeType.ALL})
	public List<UserRole> getUsers() {
		return users;
	}

	public void setUsers(List<UserRole> users) {
		this.users = users;
	}

<<<<<<< HEAD
  public void delete()
  {
    while (userRoles.size() > 0)
    {
      UserRole aUserRole = userRoles.get(userRoles.size() - 1);
      aUserRole.delete();
      userRoles.remove(aUserRole);
    }
    
    while (inventoryItems.size() > 0)
    {
      InventoryItem aInventoryItem = inventoryItems.get(inventoryItems.size() - 1);
      aInventoryItem.delete();
      inventoryItems.remove(aInventoryItem);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "city" + ":" + getCity()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }
}
=======


}
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa
