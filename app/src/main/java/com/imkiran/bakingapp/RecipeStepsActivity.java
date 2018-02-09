package com.imkiran.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.imkiran.bakingapp.Adapters.RecipeStepsAdapter;
import com.imkiran.bakingapp.models.Steps;

import java.util.ArrayList;
import java.util.List;


public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepsAdapter.ItemClickListener, RecipeStepsSnapFragment.ItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        if (savedInstanceState == null) {
            Bundle intent = getIntent().getExtras();
            intent.getParcelableArrayList(getString(R.string.parcel_recipe));

            final RecipeStepsFragment recipeStepsFragment = new RecipeStepsFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            recipeStepsFragment.setArguments(intent);

            fragmentManager.beginTransaction()
                    .replace(R.id.recipe_steps_fragment_container, recipeStepsFragment)
                    .addToBackStack("test")
                    .commit();


            if (findViewById(R.id.activity_recipe_details_fregment_holder).getTag() != null && findViewById(R.id.activity_recipe_details_fregment_holder).getTag().equals("tablet-land")) {
                final RecipeStepsSnapFragment recipeStepsSnapFragment = new RecipeStepsSnapFragment();

                recipeStepsSnapFragment.setArguments(intent);

                fragmentManager.beginTransaction()
                        .replace(R.id.recipe_steps_snap_fragment_container, recipeStepsSnapFragment)
                        .addToBackStack("test_2")
                        .commit();
            }
        }
    }

    @Override
    public void onClick(List<Steps> stepsList, int clickedIndex) {
        RecipeStepsSnapFragment recipeStepsSnapFragment = new RecipeStepsSnapFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("recipe_step_selected", (ArrayList<Steps>) stepsList);
        bundle.putInt("clicked_index", clickedIndex);
        recipeStepsSnapFragment.setArguments(bundle);

        //if (!stepsList.get(clickedIndex).getVideoURL().isEmpty()) {
            if (findViewById(R.id.activity_recipe_details_fregment_holder).getTag() != null && findViewById(R.id.activity_recipe_details_fregment_holder).getTag().equals("tablet-land")) {
                fragmentManager.beginTransaction()
                        .replace(R.id.recipe_steps_snap_fragment_container, recipeStepsSnapFragment)
                        .addToBackStack("test_2")
                        .commit();
            } else {
                fragmentManager.beginTransaction()
                        .replace(R.id.recipe_steps_fragment_container, recipeStepsSnapFragment)
                        .addToBackStack("test_2")
                        .commit();
            }
       // }
        /*else {
            Bundle bundleUrl = new Bundle();
            bundleUrl.putString(getResources().getString(R.string.recipe_video_step_instruction), stepsList.get(clickedIndex).getDescription());
            Intent intent = new Intent(this, RecipeStepInstruction.class);
            intent.putExtras(bundleUrl);
            startActivity(intent);
        } */
    }
}
