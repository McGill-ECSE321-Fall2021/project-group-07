/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;

import javax.persistence.*;

// line 66 "model.ump"
// line 139 "model.ump"
@Entity
<<<<<<< HEAD
=======
@Table(name = "Inventory Item")
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
public class InventoryItem
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Status { CheckedOut, OnReserve, Available, Damaged }
  public enum TypeOfItem { CD, Movie, Book, Magazine, Newspaper, Archive }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  //private static Map<Integer, InventoryItem> inventoryitemsByInventoryItemID = new HashMap<Integer, InventoryItem>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //InventoryItem Attributes
  private int inventoryItemID; 
  private int duplicates;
  private String name;
  private String author;
  private Status status;
  private TypeOfItem type; 
  private boolean isReservable; 

//  //InventoryItem Associations
//  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  public InventoryItem(int aInventoryItemID, int aDuplicates, String aName, String aAuthor, Status aStatus, TypeOfItem aType, Library aLibrary) {
=======
=======
  public InventoryItem(){
	  
  }

>>>>>>> 1ba91abc45ee9364119a327d731763c3ae91231a
=======
  public InventoryItem() {
	  
  }
  
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
  public InventoryItem(int aInventoryItemID, int aDuplicates, String aName, String aAuthor, Status aStatus, TypeOfItem aType) {
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
	
	this.inventoryItemID = aInventoryItemID;
    this.duplicates = aDuplicates;
    this.name = aName;
    this.author = aAuthor;
    this.status = aStatus;
    this.type = aType;
<<<<<<< HEAD
    this.library = aLibrary;
=======
    //this.library = aLibrary;
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
    
    if (type.equals(TypeOfItem.Archive) || type.equals(TypeOfItem.Newspaper) || type.equals(TypeOfItem.Magazine)) {
    	this.isReservable = false;
    } else {
    	this.isReservable = true;
    }
    
  }

<<<<<<< HEAD
	public static Map<Integer, InventoryItem> getInventoryitemsByInventoryItemID() {
		return inventoryitemsByInventoryItemID;
	}
	
	public static void setInventoryitemsByInventoryItemID(Map<Integer, InventoryItem> inventoryitemsByInventoryItemID) {
		InventoryItem.inventoryitemsByInventoryItemID = inventoryitemsByInventoryItemID;
	}
=======
//	public static Map<Integer, InventoryItem> getInventoryitemsByInventoryItemID() {
//		return inventoryitemsByInventoryItemID;
//	}
//	
//	public static void setInventoryitemsByInventoryItemID(Map<Integer, InventoryItem> inventoryitemsByInventoryItemID) {
//		InventoryItem.inventoryitemsByInventoryItemID = inventoryitemsByInventoryItemID;
//	}
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
	
	@Id
	public int getInventoryItemID() {
		return inventoryItemID;
	}
	
	public void setInventoryItemID(int inventoryItemID) {
		this.inventoryItemID = inventoryItemID;
	}
	
	public int getDuplicates() {
		return duplicates;
	}
	
	public void setDuplicates(int duplicates) {
		this.duplicates = duplicates;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public TypeOfItem getType() {
		return type;
	}
	
	public void setType(TypeOfItem type) {
		this.type = type;
	}

	public boolean isReservable() {
		return isReservable;
	}

	public void setReservable() {
		
	    if (type.equals(TypeOfItem.Archive) || type.equals(TypeOfItem.Newspaper) || type.equals(TypeOfItem.Magazine)) {
	    	this.isReservable = false;
	    } else {
	    	this.isReservable = true;
	    }
	}
	
	public void setReservable(boolean isReservable) {
			    
	    this.isReservable = isReservable;
	}
<<<<<<< HEAD
	
<<<<<<< HEAD
	public Library getLibrary() {
		return library;
	}
	
	public void setLibrary(Library library) {
		this.library = library;
	}
=======
=======
//		
>>>>>>> 43c22ed296891f706c6d8f0a2870ff860b32dea3
//	public Library getLibrary() {
//		return library;
//	}
//	
//	public void setLibrary(Library library) {
//		this.library = library;
//	}
>>>>>>> c5b769f778555a1335d3105b5a61456b4aa9abda
	

}