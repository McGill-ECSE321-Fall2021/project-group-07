package ca.mcgill.ecse321.librarysystem07.model;


import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Table;

<<<<<<< HEAD

=======
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
@Entity
@Table(name = "head librarian")
public class HeadLibrarian extends UserRole
{
<<<<<<< HEAD

  public HeadLibrarian(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary)
  {
    super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
  }



}
=======
  public HeadLibrarian(String aName, String aUsername, String aAddress, int aLibraryCardID)
  {
    super(aName, aUsername, aAddress, aLibraryCardID);
  }
}
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
