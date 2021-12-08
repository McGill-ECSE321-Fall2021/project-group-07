// Generated by view binder compiler. Do not edit!
package ca.mcgill.ecse321.librarysystem07.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import ca.mcgill.ecse321.librarysystem07.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAccountBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView Address;

  @NonNull
  public final TextView Balance;

  @NonNull
  public final TextView CardId;

  @NonNull
  public final TextView DemeritPoints;

  @NonNull
  public final TextView Name;

  @NonNull
  public final TextView Username;

  @NonNull
  public final TextView error;

  @NonNull
  public final ImageView logo;

  @NonNull
  public final EditText newaddressUpdate;

  private FragmentAccountBinding(@NonNull RelativeLayout rootView, @NonNull TextView Address,
      @NonNull TextView Balance, @NonNull TextView CardId, @NonNull TextView DemeritPoints,
      @NonNull TextView Name, @NonNull TextView Username, @NonNull TextView error,
      @NonNull ImageView logo, @NonNull EditText newaddressUpdate) {
    this.rootView = rootView;
    this.Address = Address;
    this.Balance = Balance;
    this.CardId = CardId;
    this.DemeritPoints = DemeritPoints;
    this.Name = Name;
    this.Username = Username;
    this.error = error;
    this.logo = logo;
    this.newaddressUpdate = newaddressUpdate;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_account, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Address;
      TextView Address = ViewBindings.findChildViewById(rootView, id);
      if (Address == null) {
        break missingId;
      }

      id = R.id.Balance;
      TextView Balance = ViewBindings.findChildViewById(rootView, id);
      if (Balance == null) {
        break missingId;
      }

      id = R.id.CardId;
      TextView CardId = ViewBindings.findChildViewById(rootView, id);
      if (CardId == null) {
        break missingId;
      }

      id = R.id.DemeritPoints;
      TextView DemeritPoints = ViewBindings.findChildViewById(rootView, id);
      if (DemeritPoints == null) {
        break missingId;
      }

      id = R.id.Name;
      TextView Name = ViewBindings.findChildViewById(rootView, id);
      if (Name == null) {
        break missingId;
      }

      id = R.id.Username;
      TextView Username = ViewBindings.findChildViewById(rootView, id);
      if (Username == null) {
        break missingId;
      }

      id = R.id.error;
      TextView error = ViewBindings.findChildViewById(rootView, id);
      if (error == null) {
        break missingId;
      }

      id = R.id.logo;
      ImageView logo = ViewBindings.findChildViewById(rootView, id);
      if (logo == null) {
        break missingId;
      }

      id = R.id.newaddress_update;
      EditText newaddressUpdate = ViewBindings.findChildViewById(rootView, id);
      if (newaddressUpdate == null) {
        break missingId;
      }

      return new FragmentAccountBinding((RelativeLayout) rootView, Address, Balance, CardId,
          DemeritPoints, Name, Username, error, logo, newaddressUpdate);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
