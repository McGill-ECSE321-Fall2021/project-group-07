package ca.mcgill.ecse321.librarysystem07;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import cz.msebera.android.httpclient.Header;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.mcgill.ecse321.librarysystem07.databinding.ActivityMainBinding;
import cz.msebera.android.httpclient.HttpEntity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String error = null;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.content_main); //not sure about this id..
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

    public void login(View v) {
        error = "";

        final EditText username = (EditText) findViewById(R.id.login_username);
        final EditText id = (EditText) findViewById(R.id.login_id);

        boolean checked = ((CheckBox) findViewById(R.id.checkbox_librarian)).isChecked();
        if (checked) {
            HttpUtils.get("librarians/" + id.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    id.setText("");
                    username.setText("");
                    try {
                        if (response.getJSONObject("Librarian").getString("username").equals(username)) {
                            startActivity(new Intent(MainActivity.this, BrowseActivityLibrarian.class));
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

        HttpUtils.get("visitors/" + id.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                id.setText("");
                username.setText("");
                try {
                    if (response.getJSONObject("Visitor").getString("username").equals(username)) {
                        startActivity(new Intent(MainActivity.this, BrowseActivity.class));
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.content_main); //here too
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}