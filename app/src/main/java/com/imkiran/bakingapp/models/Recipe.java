package com.imkiran.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imkiran on 30/12/17.
 */

public class Recipe implements Parcelable {

    private Integer id;
    private Integer servings;
    private String name;
    private String image;
    private List<Ingredients> ingredients = null;
    private List<Steps> steps = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(name);
        if (ingredients == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeTypedList(ingredients);
        }
        if (steps == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(steps);
        }
        if (servings == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(servings);
        }
        dest.writeString(image);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            Recipe recipe = new Recipe();
            recipe.id = in.readByte() == 0x00 ? null : in.readInt();
            recipe.name = in.readString();
            if (in.readByte() == 0x01) {
                recipe.ingredients = new ArrayList<Ingredients>();
                in.readTypedList(recipe.ingredients,Ingredients.CREATOR);
            } else {
                recipe.ingredients = null;
            }
            if (in.readByte() == 0x01) {
                recipe.steps = new ArrayList<>();
                in.readList(recipe.steps,Steps.class.getClassLoader());
            } else {
                recipe.steps = null;
            }
            recipe.servings = in.readByte() == 0x00 ? null : in.readInt();
            recipe.image = in.readString();
            return recipe;
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };}
