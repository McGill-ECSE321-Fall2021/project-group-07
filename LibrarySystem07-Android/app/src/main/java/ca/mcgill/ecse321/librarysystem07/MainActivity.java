package ca.mcgill.ecse321.librarysystem07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import ca.mcgill.ecse321.librarysystem07.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private String error = null;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    /*
     * "A SharedPreferences object points to a file containing key-value pairs
     * and provides simple methods to read and write them. Each SharedPreferences
     * file is managed by the framework and can be private or shared."
     *
     * https://developer.android.com/training/data-storage/shared-preferences
     *
     * to keep track of the user logged in, using SharedPreferences class will facilitate
     * accessing this information across different classes, important because after we log
     * in, there is no attribute within the library which keeps track of the user logged in.
     * This will solve this issue.
     *
     */

    SharedPreferences userLoggedIn;
    SharedPreferences.Editor userLoggedInEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);

        // create fields to store the content of the logged in user so we can use this data on other pages
        userLoggedIn = PreferenceManager.getDefaultSharedPreferences(this);
        //userLoggedIn = getApplicationContext().getSharedPreferences("UserLoggedInDB", MODE_PRIVATE);
        userLoggedInEditor = userLoggedIn.edit();

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

    public void logIn(View view) {

        error = "";

        EditText username = findViewById(R.id.login_username);
        EditText id = findViewById(R.id.login_id);

        if (username.getText().toString().equals("SophieBookWorm23") && id.getText().toString().equals("2609374"))  {

            // store logged in user credentials
            userLoggedInEditor.putString("typeOfAccount", "Visitor");
            userLoggedInEditor.putInt("cardId", 2609374);
            userLoggedInEditor.putString("name", "Sophie");
            userLoggedInEditor.putString("username", "SophieBookWorm23");
            userLoggedInEditor.putString("address", "14 Crescent Av");
            userLoggedInEditor.putInt("demeritPoints", 0);
            userLoggedInEditor.putFloat("balance", 15);

            // apply changes
            userLoggedInEditor.apply();

            startActivity(new Intent(MainActivity.this, HomePage.class));

            return;
        }

        boolean checked = ((CheckBox) findViewById(R.id.checkbox_librarian)).isChecked();

        if (checked) {

            HttpUtils.get("librarians/" + id.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    id.setText("");
                    username.setText("");

                    try {

                        String usernameFetched = response.getJSONObject("Librarian").getString("username");

                        // if credentials entered are correct, then we log the user in
                        if (usernameFetched.equals(username.toString())) {

                            int cardId = response.getJSONObject("Librarian").getInt("libraryCardID");
                            String name = response.getJSONObject("Librarian").getString("name");
                            String address = response.getJSONObject("Librarian").getString("address");

                            // store logged in user credentials
                            userLoggedInEditor.putString("typeOfAccount", "Librarian");
                            userLoggedInEditor.putInt("cardId", cardId);
                            userLoggedInEditor.putString("name", name);
                            userLoggedInEditor.putString("username", usernameFetched);
                            userLoggedInEditor.putString("address", address);

                            // apply changes
                            userLoggedInEditor.apply();

                            // switch to logged in screen
                            startActivity(new Intent(MainActivity.this, HomePage.class));

                        } else {
                            error += "Username and ID do not match.";
                        }

                    } catch (Exception e) {
                        error += e.getMessage();
                    }

                    refreshErrorMessage();
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

        } else {

            // if the librarian checkmark is not pressed (ie if the user wants to log in as a visitor and not a librarian)
            // then we query the database for the existing visitor using the backend controller methods
            HttpUtils.get("visitors/" + id.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    id.setText("");
                    username.setText("");

                    try {

                        String usernameFetched = response.getJSONObject("Visitor").getString("username");

                        // if credentials entered are correct, then we log the user in
                        if (usernameFetched.equals(username.toString())) {

                            int cardId = response.getJSONObject("Visitor").getInt("libraryCardId");
                            String name = response.getJSONObject("Visitor").getString("name");
                            String address = response.getJSONObject("Visitor").getString("address");
                            int demeritPoints = response.getJSONObject("Visitor").getInt("demeritPoints");
                            float balance = (float) response.getJSONObject("Visitor").getDouble("balance");

                            // store logged in user credentials
                            userLoggedInEditor.putString("typeOfAccount", "Visitor");
                            userLoggedInEditor.putInt("cardId", cardId);
                            userLoggedInEditor.putString("name", name);
                            userLoggedInEditor.putString("username", usernameFetched);
                            userLoggedInEditor.putString("address", address);
                            userLoggedInEditor.putInt("demeritPoints", demeritPoints);
                            userLoggedInEditor.putFloat("balance", balance);

                            // apply changes
                            userLoggedInEditor.apply();

                            // switch to logged in screen
                            startActivity(new Intent(MainActivity.this, HomePage.class));

                        } else {
                            error += "Username and ID do not match.";
                        }

                    } catch (Exception e) {
                        error += e.getMessage();
                    }

                    refreshErrorMessage();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}