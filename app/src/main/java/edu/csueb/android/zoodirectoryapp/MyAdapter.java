package edu.csueb.android.zoodirectoryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

/*
    The Adapter for the RecyclerView
    MyAdapter contains context to refer to specific Activity calling it
      and an array of Animal objects to be accessed
    Also contains the ViewHolder class to define the layout of each row
*/

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Animal animals[];       // holds all Animal objects
    Context context;        // holds the context of the current Activity
    int currentPosition;    // holds the current position of row in onBindViewer

    // MyAdapter Constructor
    public MyAdapter(Context context, Animal animals[]){
        this.animals = animals;
        this.context = context;
    }

    // Creates view holder with row_layout.xml layout file
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Sets row text to name of animal
        holder.textView.setText(animals[position].getName());
        // Sets row image to image of animal
        holder.imageView.setImageResource(animals[position].getImage());

        // Creates a intent that calls DetailsActivity when row is clicked
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPosition = position;
                // Checks if animal clicked on was Penguin
                if (animals[currentPosition].getName().equals("Penguin")){
                    // Displays AlertDialog box with warning
                    alert("Warning! The penguin is very dangerous and may endanger your life. Are "
                            + "you sure you want to still see it?");
                }
                else{
                    // Calls viewDetails method no matter what
                    viewDetails(currentPosition);
                }
            }
        });
    }

    // Returns amount of items (or rows)
    @Override
    public int getItemCount() {
        return animals.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;              // Holds the text in a row
        ImageView imageView;            // Holds the image in a row
        ConstraintLayout rootLayout;    // Holds the layout of the main activity

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            rootLayout = itemView.findViewById(R.id.rootLayout);
        }
    }

    // Creates and Defines behavior or AlertDialog called in OnBindViewHolder
    private void alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        // calls viewDetails method if user clicks Positive Button
                        viewDetails(currentPosition);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                })
                .create();
        dlg.show();
    }

    // Instantiates and calls intent to view details for each animal
    private void viewDetails(int position){
        // Initializes new Intent to call DetailsActivity
        Intent viewAnimal = new Intent(context, DetailsActivity.class);
        // Puts animal name, description, and image in viewAnimal intent Bundle
        viewAnimal.putExtra("name", animals[position].getName());
        viewAnimal.putExtra("description", animals[position].getDescription());
        viewAnimal.putExtra("image", animals[position].getLargeImage());
        // calls DetailsActivity using viewAnimal intent from MainActivity
        context.startActivity(viewAnimal);
    }
}
