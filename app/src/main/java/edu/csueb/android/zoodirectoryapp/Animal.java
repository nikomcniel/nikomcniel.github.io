package edu.csueb.android.zoodirectoryapp;
public class Animal {
    // Fields of the animal class
    private int image;
    private int largeImage;
    private String description;
    private String name;

    // Default constructor
    public Animal(){
        image = -1;
        largeImage = -1;
        description = "";
        name = "";
    }

    // Complex constructor
    public Animal(int image, int largeImage, String description, String name) {
        this.image = image;
        this.largeImage = largeImage;
        this.description = description;
        this.name = name;
    }

    // Getter methods
    int getImage() {return image;}
    int getLargeImage() {return largeImage;}
    String getDescription() {return description;}
    String getName() {return name;}

    // Setter methods
    void setImage(int newImage) {this.image = newImage;}
    void setLargeImage(int newLargeImage) {this.largeImage = newLargeImage;}
    void setDescription(String newDescription) {this.description = newDescription;}
    void setName(String newName) {this.name = newName;}
}
