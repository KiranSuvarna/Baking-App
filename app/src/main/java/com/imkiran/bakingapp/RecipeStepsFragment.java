package com.imkiran.bakingapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.imkiran.bakingapp.Adapters.RecipeIngredientHeadAdapter;
import com.imkiran.bakingapp.Adapters.RecipeStepsAdapter;
import com.imkiran.bakingapp.models.Ingredients;
import com.imkiran.bakingapp.models.Recipe;
import com.imkiran.bakingapp.models.Steps;
import com.imkiran.bakingapp.widget.UpdateBakingService;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepsFragment extends Fragment {

    public RecipeStepsFragment() {

    }

    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    RecyclerView recyclerViewIngredientsHead;
    RecipeStepsAdapter recipeStepsAdapter;
    RecipeIngredientHeadAdapter recipeIngredientHead;


    private List<Recipe> recipe;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View rootView = layoutInflater.inflate(R.layout.fragment_recipe_steps, viewGroup, false);

        recyclerView = rootView.findViewById(R.id.recycler_view_recipe_steps);
        recyclerViewIngredientsHead = rootView.findViewById(R.id.recycler_view_ingredient_head);
        relativeLayout = rootView.findViewById(R.id.fragment_recipe_steps_relativelayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewIngredientsHead.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));

        recipe = new ArrayList<>();
        if (savedInstanceState != null) {
            recipe = savedInstanceState.getParcelableArrayList(getString(R.string.parcel_recipe));
        } else {
            recipe = getArguments().getParcelableArrayList(rootView.getContext().getResources().getString(R.string.parcel_recipe));
        }

        List<Ingredients> ingredientsList = recipe.get(0).getIngredients();

        ArrayList<String> recipeIngredientsForWidgets = new ArrayList<>();

        for(Ingredients ingredient : ingredientsList){
            recipeIngredientsForWidgets.add(ingredient.getIngredient()+"\n"+
                    "Quantity: "+ingredient.getQuantity().toString()+"\n"+
                    "Measure: "+ingredient.getMeasure()+"\n");
        }

        List<Steps> stepsList = null;
        for (Recipe recipe : recipe) {
            stepsList = recipe.getSteps();
        }
        recipeStepsAdapter = new RecipeStepsAdapter((RecipeStepsActivity) getActivity());
        recipeIngredientHead = new RecipeIngredientHeadAdapter(rootView.getContext(), recipe);
        recyclerView.setAdapter(recipeStepsAdapter);
        recipeStepsAdapter.setData(rootView.getContext(), stepsList);
        recyclerViewIngredientsHead.setAdapter(recipeIngredientHead);

        UpdateBakingService.startBakingService(getContext(), recipeIngredientsForWidgets);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.parcel_recipe), (ArrayList<Recipe>) recipe);
    }
}


