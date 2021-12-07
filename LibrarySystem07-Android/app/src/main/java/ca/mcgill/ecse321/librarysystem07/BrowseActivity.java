package ca.mcgill.ecse321.librarysystem07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;

import ca.mcgill.ecse321.librarysystem07.databinding.ActivityMainBinding;

public class BrowseActivity extends AppCompatActivity {

    private String error = null;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    ArrayList<InventoryItemDto> inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);

        // Lookup the recyclerview in activity layout
        RecyclerView rv_inventory_items = (RecyclerView) findViewById(R.id.rv_inventory_items);

        // Initialize items
        ArrayList<String> names = new ArrayList<String>();
        names.add("Hi");
        names.add("fiohdsnk");
        ArrayList<String> authors = new ArrayList<String>();
        authors.add("nifoanksd");
        authors.add("im an author");

        inventory = InventoryItemDto.createInventoryList(2, names, authors);
        // Create adapter passing in the sample user data
        InventoryAdapter adapter = new InventoryAdapter(inventory);
        // Attach the adapter to the recyclerview to populate items
        rv_inventory_items.setAdapter(adapter);
        // Set layout manager to position the items
        rv_inventory_items.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        // initialize error message text view
        refreshErrorMessage();
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }
}