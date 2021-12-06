package ca.mcgill.ecse321.librarysystem07;

import java.util.ArrayList;

public class InventoryItemDto {

    private int inventoryItemID;
    private int duplicates;
    private String name;
    private String author;
    private String status;
    private String type;
    private boolean isReservable;

    public InventoryItemDto(){

    }
    // does not work yet
    public InventoryItemDto(int aInventoryItemID){
        this(aInventoryItemID, 0, "name", "author", "Available", "Book");
    }

    public InventoryItemDto(int aInventoryItemID, int aDuplicates, String aName, String aAuthor, String aStatus, String aTypeOfItem) {

        this.inventoryItemID = aInventoryItemID;
        this.duplicates = aDuplicates;
        this.name = aName;
        this.author = aAuthor;
        this.status = aStatus;
        this.type = aTypeOfItem;

        if (type.equals("Archive") || type.equals("Newspaper") || type.equals("Magazine")) {
            isReservable = false;
        } else {
            isReservable = true;
        }

    }

    public static ArrayList<InventoryItemDto> createInventoryList(int num, ArrayList<String> names, ArrayList<String> authors) {
        ArrayList<InventoryItemDto> items = new ArrayList<InventoryItemDto>();

        for (int i = 0; i < num; i++) {
            items.add(new InventoryItemDto(i, 0, names.get(i), authors.get(i), "Available", "Book"));
        }

        return items;
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

    public String getStatus() {
        return this.status;
    }

    public String getType() {
        return this.type;
    }

    public boolean isReservable() {
        return this.isReservable;
    }
}