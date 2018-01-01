package com.imkiran.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by imkiran on 30/12/17.
 */

public class Ingredients implements Parcelable{

    private String measure;

    private String ingredient;

    private String quantity;

    private Ingredients(Parcel in) {
        measure = in.readString();
        ingredient = in.readString();
        quantity = in.readString();
    }

    public String getMeasure ()
    {
        return measure;
    }

    public void setMeasure (String measure)
    {
        this.measure = measure;
    }

    public String getIngredient ()
    {
        return ingredient;
    }

    public void setIngredient (String ingredient)
    {
        this.ingredient = ingredient;
    }

    public String getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (String quantity)
    {
        this.quantity = quantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(measure);
        parcel.writeString(ingredient);
        parcel.writeString(quantity);
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        public Ingredients createFromParcel(Parcel source) {
            return new Ingredients(source);
        }

        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}
