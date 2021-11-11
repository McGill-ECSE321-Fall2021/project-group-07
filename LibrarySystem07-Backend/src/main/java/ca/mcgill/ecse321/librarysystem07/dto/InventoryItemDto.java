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
    // does not work yet
    public InventoryItemDto(int aInventoryItemID){
        this(aInventoryItemID, 0, "name", "author", Status.Available, TypeOfItem.Book);
    }

    public InventoryItemDto(int aInventoryItemID, int aDuplicates, String aName, String aAuthor, Status aStatus, TypeOfItem aType) {
	
        this.inventoryItemID = aInventoryItemID;
        this.duplicates = aDuplicates;
        this.name = aName;
        this.author = aAuthor;
        this.status = aStatus;
        this.type = aType;
        
        if (type.equals(TypeOfItem.Archive) || type.equals(TypeOfItem.Newspaper) || type.equals(TypeOfItem.Magazine)) {
            isReservable = false;
        } else {
            isReservable = true;
        }
        
      }


    public int getInventoryItemID() {
          return this.inventoryItemID;
      }
      
      
      public int getDuplicates() {
          return this.duplicates;
      }
      
      
      public String getName() {
          return this.name;
      }
      
      
      public String getAuthor() {
          return this.author;
      }
      
      public Status getStatus() {
          return this.status;
      }
      
      public TypeOfItem getType() {
          return this.type;
      }
      
      public boolean isReservable() {
          return this.isReservable;
      }

}
