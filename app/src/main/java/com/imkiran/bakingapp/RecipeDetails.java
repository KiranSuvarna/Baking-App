package com.imkiran.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.imkiran.bakingapp.Adapters.RecipeAdapter;
import com.imkiran.bakingapp.Adapters.RecipeDetailsAdapter;
import com.imkiran.bakingapp.models.Ingredients;
import com.imkiran.bakingapp.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    List<Recipe> dataList;
    RecipeDetailsAdapter recipeDetailsAdapter;

    private ArrayList<Recipe> recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        recyclerView = findViewById(R.id.recycler_view_recipe_details);
        relativeLayout = findViewById(R.id.activity_recipe_details);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        Bundle intent = getIntent().getExtras();
        recipe = intent.getParcelableArrayList(getString(R.string.parcel_recipe));

        Log.d("Recipedetails:recipe",new Gson().toJson(recipe));
        for(Recipe recipe : recipe){
            List<Ingredients> in = recipe.getIngredients();
            Log.d("Recipedetails:listin",new Gson().toJson(in));

        }


        recipeDetailsAdapter = new RecipeDetailsAdapter(RecipeDetails.this, recipe);
        recyclerView.setAdapter(recipeDetailsAdapter);

    }
}


