package com.imkiran.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.imkiran.bakingapp.Adapters.RecipeIngredientsAdapter;
import com.imkiran.bakingapp.models.Ingredients;
import com.imkiran.bakingapp.models.Recipe;

import java.util.List;

/**
 * Created by imkiran on 10/01/18.
 */

public class RecipeIngredients extends AppCompatActivity {
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    RecipeIngredientsAdapter recipeIngredientsAdapter;


    private List<Recipe> recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredients);
        recyclerView = findViewById(R.id.recycler_view_recipe_ingredients);
        relativeLayout = findViewById(R.id.activity_recipe_ingredients);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        Bundle intent = getIntent().getExtras();
        recipe = intent.getParcelableArrayList(getString(R.string.parcel_ingredients));

        List<Ingredients> ingredientsList = null;
        for (Recipe recipe : recipe) {
            ingredientsList = recipe.getIngredients();
        }

        recipeIngredientsAdapter = new RecipeIngredientsAdapter(RecipeIngredients.this, ingredientsList);
        recyclerView.setAdapter(recipeIngredientsAdapter);
    }
}
