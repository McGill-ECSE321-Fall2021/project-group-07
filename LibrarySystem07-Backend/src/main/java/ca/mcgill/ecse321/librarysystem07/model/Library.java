/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;

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

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

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

  public String getCity()
  {
    return city;
  }

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

  public List<UserRole> getUserRoles()
  {
    List<UserRole> newUserRoles = Collections.unmodifiableList(userRoles);
    return newUserRoles;
  }

  public int numberOfUserRoles()
  {
    int number = userRoles.size();
    return number;
  }

  public boolean hasUserRoles()
  {
    boolean has = userRoles.size() > 0;
    return has;
  }

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

  public int numberOfInventoryItems()
  {
    int number = inventoryItems.size();
    return number;
  }

  public boolean hasInventoryItems()
  {
    boolean has = inventoryItems.size() > 0;
    return has;
  }

  public int indexOfInventoryItem(InventoryItem aInventoryItem)
  {
    int index = inventoryItems.indexOf(aInventoryItem);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserRoles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


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

  public boolean removeUserRole(UserRole aUserRole)
  {
    boolean wasRemoved = false;
    //Unable to remove aUserRole, as it must always have a library
    if (!this.equals(aUserRole.getLibrary()))
    {
      userRoles.remove(aUserRole);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserRoleAt(UserRole aUserRole, int index)
  {  
    boolean wasAdded = false;
    if(addUserRole(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    }
    return wasAdded;
  }

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

  public boolean addInventoryItem(InventoryItem aInventoryItem)
  {
    boolean wasAdded = false;
    if (inventoryItems.contains(aInventoryItem)) { return false; }
    Library existingLibrary = aInventoryItem.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aInventoryItem.setLibrary(this);
    }
    else
    {
      inventoryItems.add(aInventoryItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeInventoryItem(InventoryItem aInventoryItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aInventoryItem, as it must always have a library
    if (!this.equals(aInventoryItem.getLibrary()))
    {
      inventoryItems.remove(aInventoryItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addInventoryItemAt(InventoryItem aInventoryItem, int index)
  {  
    boolean wasAdded = false;
    if(addInventoryItem(aInventoryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInventoryItems()) { index = numberOfInventoryItems() - 1; }
      inventoryItems.remove(aInventoryItem);
      inventoryItems.add(index, aInventoryItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveInventoryItemAt(InventoryItem aInventoryItem, int index)
  {
    boolean wasAdded = false;
    if(inventoryItems.contains(aInventoryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInventoryItems()) { index = numberOfInventoryItems() - 1; }
      inventoryItems.remove(aInventoryItem);
      inventoryItems.add(index, aInventoryItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addInventoryItemAt(aInventoryItem, index);
    }
    return wasAdded;
  }

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