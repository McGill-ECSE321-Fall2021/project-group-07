package ca.mcgill.ecse321.librarysystem07.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.librarysystem07.dao.InventoryItemRepository;
import ca.mcgill.ecse321.librarysystem07.model.InventoryItem;

@ExtendWith(MockitoExtension.class)
public class TestInventoryItemService {
    
    @Mock
    private static InventoryItemRepository inventoryItemDao;

   @InjectMocks
    private LibrarySystem07Service inventoryItemService;

    private static int inventoryItemID = 12345; 
    private static int duplicates = 0;
    private static String name = "Moby Dick";
    private static String author = "Herman Melville";
    private static InventoryItem.Status status = InventoryItem.Status.Available;
    private static InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Book; 
    private static boolean isReservable = true; 

    private static final int nonExistinginventoryItemID = -1;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(inventoryItemDao.findInventoryItemByInventoryItemID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(inventoryItemID)) {
                InventoryItem inventoryItem = new InventoryItem();
                
                inventoryItem.setInventoryItemID(inventoryItemID);
                inventoryItem.setDuplicates(duplicates);
                inventoryItem.setName(name);
                inventoryItem.setAuthor(author);
                inventoryItem.setStatus(status);
                inventoryItem.setType(type);
                inventoryItem.setReservable(isReservable);

                return inventoryItem;
            }
            return null;
        });
    }  

    @Test
    public void testCreateInventoryItem(){
        assertEquals(0, inventoryItemService.getAllInventoryItems().size());
        
        int inventoryItemID = 12345; 
        int duplicates = 0;
        String name = "Now you see me";
        String author = "Louis Leterrier";
        InventoryItem.Status status = InventoryItem.Status.Available;
        InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 
        boolean isReservable = true; 

        InventoryItem inventoryItem = null;

        try{
            inventoryItem = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(inventoryItem);
        assertEquals(inventoryItemID, inventoryItem.getInventoryItemID());
        assertEquals(duplicates, inventoryItem.getDuplicates());
        assertEquals(name, inventoryItem.getName());
        assertEquals(author, inventoryItem.getAuthor());
        assertEquals(status, inventoryItem.getStatus());
        assertEquals(type, inventoryItem.getType());
        assertEquals(isReservable, inventoryItem.isReservable());

    }

    @Test
    public void testCreateInventoryItemNull(){
        String error = "";
        
        int inventoryItemID = -1; 
        int duplicates = -1;
        String name = null;
        String author = null;
        InventoryItem.Status status = null;
        InventoryItem.TypeOfItem type = null; 

        InventoryItem inventoryItem = null;

        try{
            inventoryItem = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
        } catch(IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(inventoryItem);
        assertEquals("ID must be an integer above 0. Invalid number of duplicates. Invalid name! Invalid author! Invalid status! Invalid type!", error);

    }

    @Test
    public void testDeleteInventoryItem(){
        InventoryItem inventoryItem = null;
        
        int inventoryItemID = 12345; 
        int duplicates = 0;
        String name = "Now you see me";
        String author = "Louis Leterrier";
        InventoryItem.Status status = InventoryItem.Status.Available;
        InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 

        inventoryItem = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);

        assertNotNull(inventoryItem);

        try{
            inventoryItem = inventoryItemService.deleteInventoryItem(inventoryItem);
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNull(inventoryItem);
    }

    @Test
    public void testDeleteNullInventoryItem(){
        String error = "";

        InventoryItem inventoryItem = null;

        try{
            inventoryItem = inventoryItemService.deleteInventoryItem(inventoryItem);
        } catch(IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(inventoryItem);
        assertEquals("Inventory Item must not be null.", error);
    }

    @Test
    public void testUpdateInventoryItemDuplicate(){
        int inventoryItemID = 12345; 
        int duplicates = 0;
        String name = "Now you see me";
        String author = "Louis Leterrier";
        InventoryItem.Status status = InventoryItem.Status.Available;
        InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 
    
        InventoryItem inventoryItem1 = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
        InventoryItem inventoryItem2 = null;
        int duplicates2 = 1;

        try{
            inventoryItem2 = inventoryItemService.updateIventoryItemDuplicate(inventoryItem1, duplicates2);
        }catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(inventoryItem2);
        assertEquals(duplicates2, inventoryItem2.getDuplicates());
    }

    @Test
    public void testUpdateInventoryItemDuplicateError(){
        String error = "";
        int inventoryItemID = 12345; 
        int duplicates = 0;
        String name = "Now you see me";
        String author = "Louis Leterrier";
        InventoryItem.Status status = InventoryItem.Status.Available;
        InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 
    
        InventoryItem inventoryItem1 = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
        InventoryItem inventoryItem2 = null;
        int duplicates2 = -1;

        try{
            inventoryItem2 = inventoryItemService.updateIventoryItemDuplicate(inventoryItem1, duplicates2);
        }catch(IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(inventoryItem2);
        assertEquals("Invalid number of duplicates.", error);
    }
    
   @Test
   public void testUpdateInventoryItemName(){
       int inventoryItemID = 12345; 
       int duplicates = 0;
       String name = "Now you";
       String author = "Louis Leterrier";
       InventoryItem.Status status = InventoryItem.Status.Available;
       InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 

       InventoryItem inventoryItem1 = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
       InventoryItem inventoryItem2 = null;
       String name2 = "Now you see me";

       try {
           inventoryItem2 = inventoryItemService.updateIventoryItemName(inventoryItem1, name2);
       } catch (IllegalArgumentException e){
           fail();
       }

       assertNotNull(inventoryItem2);
       assertEquals(name2, inventoryItem2.getName());

   }

   @Test
   public void testUpdateInventoryItemNameError(){
       String error = "";
       int inventoryItemID = 12345; 
       int duplicates = 0;
       String name = "Now you See me";
       String author = "Louis Leterrier";
       InventoryItem.Status status = InventoryItem.Status.Available;
       InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 

       InventoryItem inventoryItem1 = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
       InventoryItem inventoryItem2 = null;
       String name2 = null;

       try {
           inventoryItem2 = inventoryItemService.updateIventoryItemName(inventoryItem1, name2);
       } catch (IllegalArgumentException e){
           error = e.getMessage();
       }

       assertNull(inventoryItem2);
       assertEquals("Invalid name!", error);

   }
  
   @Test
   public void testUpdateInventoryItemAuthor(){
       int inventoryItemID = 12345; 
       int duplicates = 0;
       String name = "Now you See me";
       String author = "Louis Leter";
       InventoryItem.Status status = InventoryItem.Status.Available;
       InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 

       InventoryItem inventoryItem1 = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
       InventoryItem inventoryItem2 = null;
       String author2 = "Louis Leterrier";

       try {
           inventoryItem2 = inventoryItemService.updateIventoryItemAuthor(inventoryItem1, author2);
       } catch (IllegalArgumentException e){
           fail();
       }

       assertNotNull(inventoryItem2);
       assertEquals(author2, inventoryItem2.getAuthor());

   }


   @Test
   public void testUpdateInventoryItemAuthorError(){
       String error = "";
       int inventoryItemID = 12345; 
       int duplicates = 0;
       String name = "Now you See me";
       String author = "Louis Leterrier";
       InventoryItem.Status status = InventoryItem.Status.Available;
       InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 

       InventoryItem inventoryItem1 = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
       InventoryItem inventoryItem2 = null;
       String author2 = null;

       try {
           inventoryItem2 = inventoryItemService.updateIventoryItemAuthor(inventoryItem1, author2);
       } catch (IllegalArgumentException e){
           error = e.getMessage();
       }

       assertNull(inventoryItem2);
       assertEquals("Invalid author!", error);

   }

   @Test
   public void testUpdateInventoryItemStatus(){
       int inventoryItemID = 12345; 
       int duplicates = 0;
       String name = "Now you See me";
       String author = "Louis Leterrier";
       InventoryItem.Status status = InventoryItem.Status.Available;
       InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 

       InventoryItem inventoryItem1 = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
       InventoryItem inventoryItem2 = null;
       InventoryItem.Status status2 = InventoryItem.Status.CheckedOut;

       try {
           inventoryItem2 = inventoryItemService.updateIventoryItemStatus(inventoryItem1, status2);
       } catch (IllegalArgumentException e){
           fail();
       }

       assertNotNull(inventoryItem2);
       assertEquals(status2, inventoryItem2.getStatus());

   }

   @Test
   public void testUpdateInventoryItemStatusError(){
       String error = "";
       int inventoryItemID = 12345; 
       int duplicates = 0;
       String name = "Now you See me";
       String author = "Louis Leterrier";
       InventoryItem.Status status = InventoryItem.Status.Available;
       InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 

       InventoryItem inventoryItem1 = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
       InventoryItem inventoryItem2 = null;
       InventoryItem.Status status2 = null;

       try {
           inventoryItem2 = inventoryItemService.updateIventoryItemStatus(inventoryItem1, status2);
       } catch (IllegalArgumentException e){
           error = e.getMessage();
       }

       assertNull(inventoryItem2);
       assertEquals("Invalid status!", error);

   }

   @Test
   public void testUpdateInventoryItemType(){
    int inventoryItemID = 12345; 
    int duplicates = 0;
    String name = "Now you See me";
    String author = "Louis Leterrier";
    InventoryItem.Status status = InventoryItem.Status.Available;
    InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 

    InventoryItem inventoryItem1 = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
    InventoryItem inventoryItem2 = null;
    InventoryItem.TypeOfItem type2 = InventoryItem.TypeOfItem.Movie;

    try {
        inventoryItem2 = inventoryItemService.updateIventoryItemType(inventoryItem1, type2);
    } catch (IllegalArgumentException e){
        fail();
    }

    assertNotNull(inventoryItem2);
    assertEquals(type2, inventoryItem2.getType());
   }

   @Test
   public void testUpdateInventoryItemTypeError(){
    String error = "";
    int inventoryItemID = 12345; 
    int duplicates = 0;
    String name = "Now you See me";
    String author = "Louis Leterrier";
    InventoryItem.Status status = InventoryItem.Status.Available;
    InventoryItem.TypeOfItem type = InventoryItem.TypeOfItem.Movie; 

    InventoryItem inventoryItem1 = inventoryItemService.createInventoryItem(inventoryItemID, duplicates, name, author, status, type);
    InventoryItem inventoryItem2 = null;
    InventoryItem.TypeOfItem type2 = null;

    try {
        inventoryItem2 = inventoryItemService.updateIventoryItemType(inventoryItem1, type2);
    } catch (IllegalArgumentException e){
        error = e.getMessage();
    }

    assertNull(inventoryItem2);
    assertEquals("Invalid type!", error);
   }

   @Test
   public void testGetExistingInventoryItem(){
       assertEquals(inventoryItemID, inventoryItemService.getInventoryItem(inventoryItemID).getInventoryItemID());
   }

   @Test
   public void testGetNonExistingInventoryItem(){
       assertNull(inventoryItemService.getInventoryItem(nonExistinginventoryItemID));
   }

}
