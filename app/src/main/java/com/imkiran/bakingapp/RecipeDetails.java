package com.imkiran.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.imkiran.bakingapp.Adapters.RecipeDetailsAdapter;
import com.imkiran.bakingapp.models.Ingredients;
import com.imkiran.bakingapp.models.Recipe;
import com.imkiran.bakingapp.models.Steps;

import java.util.List;

public class RecipeDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    List<Recipe> dataList;
    RecipeDetailsAdapter recipeDetailsAdapter;

    private List<Recipe> recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        recyclerView = findViewById(R.id.recycler_view_recipe_details);
        relativeLayout = findViewById(R.id.activity_recipe_details);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        Bundle intent = getIntent().getExtras();
        recipe = intent.getParcelableArrayList(getString(R.string.parcel_recipe));

        List<Ingredients> ingredientsList = null;
        for(Recipe recipe : recipe){
            ingredientsList = recipe.getIngredients();

        }


        recipeDetailsAdapter = new RecipeDetailsAdapter(RecipeDetails.this, ingredientsList);
        recyclerView.setAdapter(recipeDetailsAdapter);

    }
}


