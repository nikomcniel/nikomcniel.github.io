package edu.csueb.android.zoodirectoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
    InfoActivity is called whenever Information option is selected in the ActionBar overflow menu
    Creates and Instantiates a TextView to display the name of the zoo and a Button to call the
        ACTION_DIAL intent to allow user to call the zoo
*/

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Instantiates a TextView instance to represent Zoo Name in activity_info.xml
        TextView zooName = (TextView)findViewById(R.id.zooName);
        // Holds the name of the zoo
        String nameOfZoo = "The Zoo Tycoon Zoo";
        // Instantiates zooName TextView with nameOfZoo String variable
        zooName.setText(nameOfZoo);

        // Instantiates a Button to represent callButton in activity_info.xml
        Button callButton = (Button)findViewById(R.id.callButton);
        // Set text of callButton
        callButton.setText("TEL:888-8888");
        // Sets up onClickListener for button to launch dialer with phone number
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // Stores a string of the phone number
                String phoneNumber = "tel:888-8888";
                // Creates an intent to dial phone number
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
                // Starts intent
                startActivity(callIntent);
            }
        });

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
        // Informs user that they are already in Information Activity
        if (id == R.id.menu_info) {
            Toast.makeText(this, "Already in Information Tab", Toast.LENGTH_SHORT).show();
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