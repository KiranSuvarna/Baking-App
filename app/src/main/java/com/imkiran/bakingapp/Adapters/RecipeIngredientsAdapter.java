package com.imkiran.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imkiran.bakingapp.R;
import com.imkiran.bakingapp.models.Ingredients;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by imkiran on 03/01/18.
 */

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<RecipeIngredientsAdapter.CustomViewHolder>{

    private Context context;
    private List<Ingredients> data;

    public RecipeIngredientsAdapter(Context context, List<Ingredients> data){
        this.data = data;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_list_recipe_details,null);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
            Ingredients ingredients = data.get(position);
            holder.recipeIngredient.setText(StringUtils.capitalize(ingredients.getIngredient()));
            holder.recipeIngredientQuantity.setText(String.valueOf(ingredients.getQuantity()));
            Log.d("measure: ",ingredients.getMeasure());
            holder.recipeIngredientMeasure.setText(ingredients.getMeasure());
    }

    @Override
    public int getItemCount() {
        return data!=null ? data.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView recipeIngredient;
        TextView recipeIngredientQuantity;
        TextView recipeIngredientMeasure;
        CardView cardView;


        CustomViewHolder(View itemView) {
            super(itemView);

            recipeIngredient =  itemView.findViewById(R.id.recipe_ingredient);
            recipeIngredientQuantity = itemView.findViewById(R.id.recipe_ingredient_quantity);
            recipeIngredientMeasure = itemView.findViewById(R.id.recipe_ingredient_measure);
            cardView =  itemView.findViewById(R.id.card_view_recipe_details);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
