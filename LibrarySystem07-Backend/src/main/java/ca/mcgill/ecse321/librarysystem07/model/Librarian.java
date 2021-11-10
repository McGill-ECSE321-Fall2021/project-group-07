package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "librarian")
public class Librarian extends UserRole
{

<<<<<<< HEAD
  public Librarian(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary)
=======
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Librarian(String aName, String aUsername, String aAddress, int aLibraryCardID)
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
  {
    super(aName, aUsername, aAddress, aLibraryCardID);
  }

<<<<<<< HEAD
  public void delete()
  {
    super.delete();
  }

}
=======
  //------------------------
  // INTERFACE
  //------------------------

}
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
