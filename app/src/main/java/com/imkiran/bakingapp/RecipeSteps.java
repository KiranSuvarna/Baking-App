package com.imkiran.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.imkiran.bakingapp.Adapters.RecipeIngredientHeadAdapter;
import com.imkiran.bakingapp.Adapters.RecipeStepsAdapter;
import com.imkiran.bakingapp.models.Recipe;
import com.imkiran.bakingapp.models.Steps;

import java.util.List;

public class RecipeSteps extends AppCompatActivity {

    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    RecyclerView recyclerViewIngredientsHead;
    List<Recipe> dataList;
    RecipeStepsAdapter recipeStepsAdapter;
    RecipeIngredientHeadAdapter recipeIngredientHead;


    private List<Recipe> recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        recyclerView = findViewById(R.id.recycler_view_recipe_details);
        recyclerViewIngredientsHead = findViewById(R.id.recycler_view_ingredient_head);
        relativeLayout = findViewById(R.id.activity_recipe_details);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewIngredientsHead.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        Bundle intent = getIntent().getExtras();
        recipe = intent.getParcelableArrayList(getString(R.string.parcel_recipe));

        List<Steps> stepsList = null;
        for (Recipe recipe : recipe) {
            stepsList = recipe.getSteps();
        }

        recipeStepsAdapter = new RecipeStepsAdapter(RecipeSteps.this, stepsList);
        recipeIngredientHead = new RecipeIngredientHeadAdapter(RecipeSteps.this, null);
        recyclerView.setAdapter(recipeStepsAdapter);
        recyclerViewIngredientsHead.setAdapter(recipeIngredientHead);


    }
}


