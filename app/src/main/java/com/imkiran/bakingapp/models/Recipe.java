package com.imkiran.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by imkiran on 30/12/17.
 */

public class Recipe implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("servings")
    @Expose
    private String servings;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("ingredients")
    @Expose
    private Ingredients[] ingredients;
    @SerializedName("steps")
    @Expose
    private Steps[] steps;

    public String getId() {
        return id;
    }

    public String getServings() {
        return servings;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Ingredients[] getIngredients() {
        return ingredients;
    }

    public Steps[] getSteps() {
        return steps;
    }

    public static Creator<Recipe> getCREATOR() {
        return CREATOR;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIngredients(Ingredients[] ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(Steps[] steps) {
        this.steps = steps;
    }

    public final static Parcelable.Creator<Recipe> CREATOR = new Creator<Recipe>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Recipe createFromParcel(Parcel in) {
            Recipe instance = new Recipe();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.servings = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.image = ((String) in.readValue((String.class.getClassLoader())));
            instance.steps = ((Steps[]) in.readValue((String.class.getClassLoader())));
            instance.ingredients = ((Ingredients[]) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public Recipe[] newArray(int size) {
            return (new Recipe[size]);
        }

    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(id);
        parcel.writeValue(servings);
        parcel.writeValue(name);
        parcel.writeValue(image);
        parcel.writeArray(steps);
        parcel.writeArray(ingredients);
     }
}
