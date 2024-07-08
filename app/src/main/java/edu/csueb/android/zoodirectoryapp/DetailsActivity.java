package edu.csueb.android.zoodirectoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
    DetailsActivity is called whenever an Animal item is selected from the RecyclerView
    Retrieves data pertinent to specific animal and instantiates activity_layout.xml layout
        with those related fields
    Also displays same ActionBar present in MainActivity
*/

public class DetailsActivity extends AppCompatActivity {

    ImageView largeImageView;           // Will be set to largerImage passed in from MyAdapter
    TextView nameTxt, descriptionTxt;   // Will set text to text passed in from MyAdapter

    // Variables to retrieve data from calling intent
    String name, description;
    int largeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Instantiates ImageView and TextView objects
        largeImageView = findViewById(R.id.largeImage);
        nameTxt = findViewById(R.id.nameText);
        descriptionTxt = findViewById(R.id.descText);

        // Methods to retrieve and instantiate layout fields of related Animal
        getData();
        setData();
    }

    // Retrieves Animal data from calling intent in MyAdapter
    void getData() {
        // Checks if calling intent Bundle has data
        if(getIntent().hasExtra("image") && getIntent().hasExtra("name")){
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
            largeImage = getIntent().getIntExtra("image", 1);
        }
        else{   // Error message displayed if Bundle does not contain appropriate data
            Toast.makeText(this, "No Animal data available.", Toast.LENGTH_LONG).show();
        }
    }

    // Set ImageView and TextView data to corresponding values passed from calling Intent
    void setData(){
        nameTxt.setText(name);
        descriptionTxt.setText(description);
        largeImageView.setImageResource(largeImage);
    }

    // Creates the ActionBar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    // Defines behavior for selecting options in ActionBar overflow menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // gets the id of the select MenuItem
        int id = item.getItemId();
        // Starts InfoActivity if user selects the menu_info MenuItem
        if (id == R.id.menu_info) {
            startActivity(new Intent(DetailsActivity.this, InfoActivity.class));
            return true;
        }
        // Calls uninstall method in UninstallUtil if user selects the menu_uninstall MenuItem
        if (id == R.id.menu_uninstall){
            UninstallUtil.uninstall(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}