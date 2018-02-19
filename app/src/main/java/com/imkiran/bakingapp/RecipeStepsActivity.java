package com.imkiran.bakingapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.imkiran.bakingapp.Adapters.RecipeStepsAdapter;
import com.imkiran.bakingapp.models.Recipe;
import com.imkiran.bakingapp.models.Steps;

import java.util.ArrayList;
import java.util.List;


public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepsAdapter.ItemClickListener, RecipeStepsSnapFragment.ItemClickListener {

    ArrayList<Recipe> recipeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        if (savedInstanceState == null) {
            Bundle intent = getIntent().getExtras();
            recipeArrayList = intent.getParcelableArrayList(getString(R.string.parcel_recipe));

            final RecipeStepsFragment recipeStepsFragment = new RecipeStepsFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            recipeStepsFragment.setArguments(intent);

            fragmentManager.beginTransaction()
                    .replace(R.id.recipe_steps_fragment_container, recipeStepsFragment)
                    .addToBackStack(getResources().getString(R.string.fragment_stack))
                    .commit();

            if (findViewById(R.id.activity_recipe_details_fregment_holder).getTag() != null && findViewById(R.id.activity_recipe_details_fregment_holder).getTag().equals("tablet-land")) {
                final RecipeStepsSnapFragment recipeStepsSnapFragment = new RecipeStepsSnapFragment();

                recipeStepsSnapFragment.setArguments(intent);

                fragmentManager.beginTransaction()
                        .replace(R.id.recipe_steps_snap_fragment_container, recipeStepsSnapFragment)
                        .addToBackStack(getResources().getString(R.string.fragment_stack))
                        .commit();
            }
        }
    }

    @Override
    public void onClick(List<Steps> stepsList, int clickedIndex) {
        RecipeStepsSnapFragment recipeStepsSnapFragment = new RecipeStepsSnapFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(getResources().getString(R.string.recipe_step_selected), (ArrayList<Steps>) stepsList);
        bundle.putInt(getResources().getString(R.string.clicked_index), clickedIndex);
        recipeStepsSnapFragment.setArguments(bundle);

            if (findViewById(R.id.activity_recipe_details_fregment_holder).getTag() != null && findViewById(R.id.activity_recipe_details_fregment_holder).getTag().equals("tablet-land")) {
                fragmentManager.beginTransaction()
                        .replace(R.id.recipe_steps_snap_fragment_container, recipeStepsSnapFragment)
                        .addToBackStack(getResources().getString(R.string.fragment_stack))
                        .commit();
            } else {
                fragmentManager.beginTransaction()
                        .replace(R.id.recipe_steps_fragment_container, recipeStepsSnapFragment)
                        .addToBackStack(getResources().getString(R.string.fragment_stack))
                        .commit();
            }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelableArrayList(getResources().getString(R.string.parcel_recipe),recipeArrayList);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}

