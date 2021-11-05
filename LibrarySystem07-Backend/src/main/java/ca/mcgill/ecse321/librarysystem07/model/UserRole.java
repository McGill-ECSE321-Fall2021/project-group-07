package ca.mcgill.ecse321.librarysystem07.model;

<<<<<<< HEAD
import java.util.*;

// line 10 "model.ump"
// line 101 "model.ump"
public abstract class UserRole
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
=======
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User Role")
public abstract class UserRole {

	private String name;
	private String username;
	private String address;
	private int libraryCardID;
	private Library library;
	//private int balance?

	public UserRole(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary) {
		this.name = aName;
		this.username = aUsername;
		this.address = aAddress;
		this.libraryCardID = aLibraryCardID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Id
	public int getLibraryCardID() {
		return libraryCardID;
	}

	public void setLibraryCardID(int libraryCardID) {
		this.libraryCardID = libraryCardID;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}
	
	//if (!aAddress.contains("thisCity")) user.setBalance(user.balance + 10));
}
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa
