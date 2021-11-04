/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;
import javax.persistence.*;

// line 2 "model.ump"
// line 113 "model.ump"
@Entity
@Table(name = "User Role")
public abstract class OldUserRole
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, UserRole> userrolesByUsername = new HashMap<String, UserRole>();
  private static Map<Integer, UserRole> userrolesByLibraryCardID = new HashMap<Integer, UserRole>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserRole Attributes
  private String name;
  private String username;
  private String address;
  private int libraryCardID;

  //UserRole Associations
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserRole(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary)
  {
    name = aName;
    address = aAddress;
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (!setLibraryCardID(aLibraryCardID))
    {
      throw new RuntimeException("Cannot create due to duplicate libraryCardID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create userRole due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    String anOldUsername = getUsername();
    if (anOldUsername != null && anOldUsername.equals(aUsername)) {
      return true;
    }
    if (hasWithUsername(aUsername)) {
      return wasSet;
    }
    username = aUsername;
    wasSet = true;
    if (anOldUsername != null) {
      userrolesByUsername.remove(anOldUsername);
    }
    userrolesByUsername.put(aUsername, this);
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setLibraryCardID(int aLibraryCardID)
  {
    boolean wasSet = false;
    Integer anOldLibraryCardID = getLibraryCardID();
    if (anOldLibraryCardID != null && anOldLibraryCardID.equals(aLibraryCardID)) {
      return true;
    }
    if (hasWithLibraryCardID(aLibraryCardID)) {
      return wasSet;
    }
    libraryCardID = aLibraryCardID;
    wasSet = true;
    if (anOldLibraryCardID != null) {
      userrolesByLibraryCardID.remove(anOldLibraryCardID);
    }
    userrolesByLibraryCardID.put(aLibraryCardID, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static UserRole getWithUsername(String aUsername)
  {
    return userrolesByUsername.get(aUsername);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
  }

  public String getAddress()
  {
    return address;
  }

  @Id
  public int getLibraryCardID()
  {
    return libraryCardID;
  }
  /* Code from template attribute_GetUnique */
  public static UserRole getWithLibraryCardID(int aLibraryCardID)
  {
    return userrolesByLibraryCardID.get(aLibraryCardID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithLibraryCardID(int aLibraryCardID)
  {
    return getWithLibraryCardID(aLibraryCardID) != null;
  }
  /* Code from template association_GetOne */
  @ManyToOne
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibrary(Library aLibrary)
  {
    boolean wasSet = false;
    if (aLibrary == null)
    {
      return wasSet;
    }

    Library existingLibrary = library;
    library = aLibrary;
    if (existingLibrary != null && !existingLibrary.equals(aLibrary))
    {
      existingLibrary.removeUserRole(this);
    }
    library.addUserRole(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    userrolesByUsername.remove(getUsername());
    userrolesByLibraryCardID.remove(getLibraryCardID());
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeUserRole(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "username" + ":" + getUsername()+ "," +
            "address" + ":" + getAddress()+ "," +
            "libraryCardID" + ":" + getLibraryCardID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}