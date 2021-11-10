package ca.mcgill.ecse321.librarysystem07.model;


import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class HeadLibrarian extends UserRole
{
		
	public HeadLibrarian() {}
  public HeadLibrarian(String aName, String aUsername, String aAddress, int aLibraryCardID)
  {
    super(aName, aUsername, aAddress, aLibraryCardID);
  }
}