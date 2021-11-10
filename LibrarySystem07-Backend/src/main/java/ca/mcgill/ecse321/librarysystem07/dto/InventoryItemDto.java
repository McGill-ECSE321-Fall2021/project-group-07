package ca.mcgill.ecse321.librarysystem07.dto;

public class InventoryItemDto {

    private int inventoryItemID; 
    private int duplicates;
    private String name;
    private String author;
    private Status status;
    private TypeOfItem type; 
    private boolean isReservable; 

    public enum Status { CheckedOut, OnReserve, Available, Damaged }
    public enum TypeOfItem { CD, Movie, Book, Magazine, Newspaper, Archive }

    public InventoryItemDto(){

    }

    public InventoryItemDto(int aInventoryItemID, int aDuplicates, String aName, String aAuthor, Status aStatus, TypeOfItem aType) {
	
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

      public int getInventoryItemID() {
          return inventoryItemID;
      }
      
      
      public int getDuplicates() {
          return duplicates;
      }
      
      
      public String getName() {
          return name;
      }
      
      
      public String getAuthor() {
          return author;
      }
      
      public Status getStatus() {
          return status;
      }
      
      public TypeOfItem getType() {
          return type;
      }
      
      public boolean isReservable() {
          return isReservable;
      }

}
