/*
* Adapter for RecyclerView
* Note that this view is not displaying/functioning correctly, however the functionality for the dynamic display is included here.
*/
package ca.mcgill.ecse321.librarysystem07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView authorTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.inventory_item_name);
            authorTextView = (TextView) itemView.findViewById(R.id.inventory_item_author);
        }
    }
    private List<InventoryItemDto> inventoryItems;

    // Pass in the contact array into the constructor
    public InventoryAdapter(List<InventoryItemDto> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
    @NonNull
    @Override
    public InventoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View v = inflater.inflate(R.layout.item_in_inventory, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        InventoryItemDto item = inventoryItems.get(position);

        // Set item views based on your views and data model
        TextView textViewName = holder.nameTextView;
        textViewName.setText(item.getName());
        TextView textViewAuthor = holder.authorTextView;
        textViewAuthor.setText(item.getAuthor());
    }

    @Override
    public int getItemCount() {
        return inventoryItems.size();
    }
}
