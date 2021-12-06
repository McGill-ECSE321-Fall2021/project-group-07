package ca.mcgill.ecse321.librarysystem07;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InventoryItemBrowseActivity extends AppCompatActivity {
    ArrayList<InventoryItemDto> inventoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);

        // Lookup the recyclerview in activity layout
        RecyclerView inventory_items = (RecyclerView) findViewById(R.id.inventory_items);

        // Initialize contacts
        inventoryItems = new ArrayList<InventoryItemDto>();
        inventoryItems.add(new InventoryItemDto(0, 1, "name1","author", "available", "book"));
        inventoryItems.add(new InventoryItemDto(1, 1, "name2","author", "available", "book"));
        inventoryItems.add(new InventoryItemDto(2, 1, "name3","author", "available", "book"));

        // Create adapter passing in the sample user data
        InventoryAdapter adapter = new InventoryAdapter(inventoryItems);
        // Attach the adapter to the recyclerview to populate items
        inventory_items.setAdapter(adapter);
        // Set layout manager to position the items
        inventory_items.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }
}
