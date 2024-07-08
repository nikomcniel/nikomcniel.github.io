package edu.csueb.android.zoodirectoryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/*
    The MainActivity class of the Zoo Directory App
    Creates and Instantiates variables holding values for each Animal
    Displays RecyclerView with Row for each Animal
    Displays an ActionBar with options to reach InfoActivity or call delete intent
*/

public class MainActivity extends AppCompatActivity {

    // Instantiates a RecyclerView to display clickable rows of Animal names and standard images
    RecyclerView recyclerView;

    // holds the standard images of all the animals
    int images[] = {R.drawable.bear, R.drawable.giraffe, R.drawable.lion, R.drawable.monkey,
            R.drawable.penguin};
    // holds the large image of all the animals
    int largeImages[] = {R.drawable.bearbig, R.drawable.giraffebig, R.drawable.lionbig,
            R.drawable.monkeybig, R.drawable.penguinbig};
    // holds the names of the animals
    String names[];
    // holds description of the animals
    String descriptions[];
    // holds Animal object instances of all animals
    Animal animals[] = new Animal[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiates recyclerView with recyclerView id in activity_main.xml
        recyclerView = findViewById(R.id.recyclerView);

        // instantiates string array of names with names in string.xml file
        names = getResources().getStringArray(R.array.names);
        // instantiates string array of descriptions with descriptions in string.xml file
        descriptions = getResources().getStringArray(R.array.descriptions);
        // instantiates Animal array with all animal fields
        for (int i = 0; i < 5; i++){
            animals[i] = new Animal(images[i], largeImages[i], descriptions[i], names[i]);
        }

        // instantiates and initializes MyAdapter object
        MyAdapter myAdapter = new MyAdapter(this, animals);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        // gets the id of the selected MenuItem
        int id = item.getItemId();
        // Starts InfoActivity if user selects the menu_info MenuItem
        if (id == R.id.menu_info) {
            startActivity(new Intent(MainActivity.this, InfoActivity.class));
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