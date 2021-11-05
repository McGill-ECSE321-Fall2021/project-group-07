package ca.mcgill.ecse321.librarysystem07.model;

<<<<<<< HEAD
import java.util.*;
import java.sql.Date;

// line 19 "model.ump"
// line 106 "model.ump"
public class Visitor extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Visitor Attributes
  private int demeritPoints;

  //Visitor Associations
  private List<Reservation> reservations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Visitor(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary, int aDemeritPoints)
  {
    super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
    demeritPoints = aDemeritPoints;
    reservations = new ArrayList<Reservation>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDemeritPoints(int aDemeritPoints)
  {
    boolean wasSet = false;
    demeritPoints = aDemeritPoints;
    wasSet = true;
    return wasSet;
  }

  public int getDemeritPoints()
  {
    return demeritPoints;
  }
  /* Code from template association_GetMany */
  public Reservation getReservation(int index)
  {
    Reservation aReservation = reservations.get(index);
    return aReservation;
  }

  public List<Reservation> getReservations()
  {
    List<Reservation> newReservations = Collections.unmodifiableList(reservations);
    return newReservations;
  }

  public int numberOfReservations()
  {
    int number = reservations.size();
    return number;
  }

  public boolean hasReservations()
  {
    boolean has = reservations.size() > 0;
    return has;
  }

  public int indexOfReservation(Reservation aReservation)
  {
    int index = reservations.indexOf(aReservation);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReservations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reservation addReservation(Date aStartDate, Date aEndData, InventoryItem aInventoryItem)
  {
    return new Reservation(aStartDate, aEndData, this, aInventoryItem);
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    Visitor existingVisitor = aReservation.getVisitor();
    boolean isNewVisitor = existingVisitor != null && !this.equals(existingVisitor);
    if (isNewVisitor)
    {
      aReservation.setVisitor(this);
    }
    else
    {
      reservations.add(aReservation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReservation(Reservation aReservation)
  {
    boolean wasRemoved = false;
    //Unable to remove aReservation, as it must always have a visitor
    if (!this.equals(aReservation.getVisitor()))
    {
      reservations.remove(aReservation);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReservationAt(Reservation aReservation, int index)
  {  
    boolean wasAdded = false;
    if(addReservation(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservationAt(Reservation aReservation, int index)
  {
    boolean wasAdded = false;
    if(reservations.contains(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservationAt(aReservation, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=reservations.size(); i > 0; i--)
    {
      Reservation aReservation = reservations.get(i - 1);
      aReservation.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "demeritPoints" + ":" + getDemeritPoints()+ "]";
  }
}
=======
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Visitor")
public class Visitor extends UserRole {
	
	private float balance;
	private int demeritPoints;
	private Library library;
	
	
	public Visitor(String aName, String aUsername, String aAddress, int aLibraryCardID, int aDemeritPoints, Library aLibrary) {

		super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
		this.balance = 0;
		this.setDemeritPoints(aDemeritPoints);
//		if (!aAddress.contains(library.getCity())) {
//			setBalance(this.balance + 10);
//		}
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getDemeritPoints() {
		return demeritPoints;
	}

	public void setDemeritPoints(int demeritPoints) {
		this.demeritPoints = demeritPoints;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}
	
}
>>>>>>> bd4c3279ae24cc91d34b04f3ab03de3ecccd2afa
