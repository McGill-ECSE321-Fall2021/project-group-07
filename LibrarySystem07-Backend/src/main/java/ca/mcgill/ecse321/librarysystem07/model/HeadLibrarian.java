package ca.mcgill.ecse321.librarysystem07.model;


import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Table;

<<<<<<< HEAD

=======
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
@Entity
public class HeadLibrarian extends UserRole
{
<<<<<<< HEAD
<<<<<<< HEAD

  public HeadLibrarian(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary)
  {
    super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
  }



}
=======
=======
		
	public HeadLibrarian() {}
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
  public HeadLibrarian(String aName, String aUsername, String aAddress, int aLibraryCardID)
  {
    super(aName, aUsername, aAddress, aLibraryCardID);
  }
}
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
