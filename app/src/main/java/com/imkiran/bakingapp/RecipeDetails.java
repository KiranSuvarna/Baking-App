package com.imkiran.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.imkiran.bakingapp.models.Ingredients;
import com.imkiran.bakingapp.models.Recipe;

import java.util.ArrayList;

public class RecipeDetails extends AppCompatActivity {

    private ArrayList<Ingredients> ingredientsSteps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Bundle intent = getIntent().getExtras();
        ingredientsSteps = intent.getParcelableArrayList(getString(R.string.parcel_ingredients));
        Log.d("Recipedetails:data",new Gson().toJson(ingredientsSteps.get(0).getIngredient()));
    }
}


