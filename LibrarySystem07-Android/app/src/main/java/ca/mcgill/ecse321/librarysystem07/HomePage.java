package ca.mcgill.ecse321.librarysystem07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class HomePage extends AppCompatActivity {

    private String error = null;

    // access information from the user logged in
    SharedPreferences userLoggedIn;
    String typeOfAccount;
    int cardId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Instanciate the shared preferances on create, this way it understands thread "this"
        userLoggedIn = PreferenceManager.getDefaultSharedPreferences(this);
        typeOfAccount = userLoggedIn.getString("typeOfAccount", "null");
        cardId = userLoggedIn.getInt("cardId", 0);

        // fetching user logged in information
        String name = userLoggedIn.getString("name", "null");
        String username = userLoggedIn.getString("username", "null");
        String address = userLoggedIn.getString("address", "null");

        // fetching the view tags
        TextView viewName = findViewById(R.id.Name);
        TextView viewUsername = findViewById(R.id.Username);
        TextView viewAddress = findViewById(R.id.Address);
        TextView viewId = findViewById(R.id.CardId);

        // setting the view to display user information
        viewName.setText("Name:  " + name);
        viewUsername.setText("Username:  " + username);
        viewAddress.setText("Address:  " + address);
        viewId.setText("ID Card Number:  " + cardId);

        if (typeOfAccount.equals("Visitor")) {

            // extra fields for visitor

            float balance = userLoggedIn.getFloat("balance", 0);
            int demeritPoints = userLoggedIn.getInt("demeritPoints", 0);

            TextView viewBalance = findViewById(R.id.Balance);
            TextView viewDemerit = findViewById(R.id.DemeritPoints);

            viewBalance.setText("Balance:  " + balance + "$");
            viewDemerit.setText("Demerit Points:  " + demeritPoints);
        }

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

    public void updateAddress(View v) {

        error = "";

        // create a request parameter type to feed our controller method from the backend
        // want to update the address field in the user / librarian account information
        // we want to set the parameter with the right id....
        // need to call http://localhost:8085/visitors/{visitorId}?address=newaddress_update

        RequestParams rp = new RequestParams();
        final TextView tv = (TextView) findViewById(R.id.newaddress_update);
        rp.add("address", tv.getText().toString());

        if (typeOfAccount.equals("Visitor")) {

            HttpUtils.put("visitors/" + cardId, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    // update view with new address
                    TextView viewAddress = findViewById(R.id.Address);
                    viewAddress.setText("Address:  " + tv.getText().toString());

                    // refresh error message (hide any previous error messages)
                    // reset default text
                    refreshErrorMessage();
                    tv.setText("");
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        error += errorResponse.get("message").toString();
                    } catch (JSONException e) {
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
                }
            });

        } else if (typeOfAccount.equals("Librarian")) {

            HttpUtils.put("librarians/" + cardId, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    // update view with new address
                    TextView viewAddress = findViewById(R.id.Address);
                    viewAddress.setText("Address:  " + tv.getText().toString());

                    // refresh error message (hide any previous error messages)
                    // reset default text
                    refreshErrorMessage();
                    tv.setText("");
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        error += errorResponse.get("message").toString();
                    } catch (JSONException e) {
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
                }
            });
        }

    }
}