/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem07.model;

import java.util.*;

import javax.persistence.*;

// line 66 "model.ump"
// line 139 "model.ump"
@Entity
@Table(name = "Inventory Item")
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

  public InventoryItem(){
	  
  }

  public InventoryItem(int aInventoryItemID, int aDuplicates, String aName, String aAuthor, Status aStatus, TypeOfItem aType) {
	
	this.inventoryItemID = aInventoryItemID;
    this.duplicates = aDuplicates;
    this.name = aName;
    this.author = aAuthor;
    this.status = aStatus;
    this.type = aType;
    //this.library = aLibrary;
    
    if (type.equals(TypeOfItem.Archive) || type.equals(TypeOfItem.Newspaper) || type.equals(TypeOfItem.Magazine)) {
    	isReservable = false;
    } else {
    	isReservable = true;
    }
    
  }

//	public static Map<Integer, InventoryItem> getInventoryitemsByInventoryItemID() {
//		return inventoryitemsByInventoryItemID;
//	}
//	
//	public static void setInventoryitemsByInventoryItemID(Map<Integer, InventoryItem> inventoryitemsByInventoryItemID) {
//		InventoryItem.inventoryitemsByInventoryItemID = inventoryitemsByInventoryItemID;
//	}
	
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
	
	public void setReservable(boolean isReservable) {
		this.isReservable = isReservable;
	}
	
//	public Library getLibrary() {
//		return library;
//	}
//	
//	public void setLibrary(Library library) {
//		this.library = library;
//	}
	

}