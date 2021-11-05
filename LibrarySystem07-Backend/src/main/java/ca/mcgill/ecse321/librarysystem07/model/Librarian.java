/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Table;

// line 25 "model.ump"
// line 111 "model.ump"
@Entity
public class Librarian extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Librarian(String aName, String aUsername, String aAddress, int aLibraryCardID, Library aLibrary)
  {
    super(aName, aUsername, aAddress, aLibraryCardID, aLibrary);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}