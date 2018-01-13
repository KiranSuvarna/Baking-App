package com.imkiran.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imkiran.bakingapp.R;
import com.imkiran.bakingapp.RecipeIngredients;
import com.imkiran.bakingapp.models.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imkiran on 07/01/18.
 */

public class RecipeIngredientHeadAdapter extends RecyclerView.Adapter<RecipeIngredientHeadAdapter.CustomViewHolder> {

    private Context context;
    private List<Recipe> data;

    public RecipeIngredientHeadAdapter(Context context, List<Recipe> data){
        this.data = data;
        this.context = context;
    }

    @Override
    public RecipeIngredientHeadAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recipe_ingredient_head,null);
        RecipeIngredientHeadAdapter.CustomViewHolder customViewHolder = new RecipeIngredientHeadAdapter.CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeIngredientHeadAdapter.CustomViewHolder holder, int position) {
                holder.recipeIngredientHead.setText(R.string.reipce_ingerdients_head_title);
           }

    @Override
    public int getItemCount() {
        return 1;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView recipeIngredientHead;
        CardView cardView;


        CustomViewHolder(View itemView) {
            super(itemView);

            recipeIngredientHead = itemView.findViewById(R.id.recipe_ingredient);
            cardView =  itemView.findViewById(R.id.card_view_recipe_ingredient_head);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Bundle bundle = new Bundle();
            ArrayList<Recipe> recipeArrayList = new ArrayList<>();
            recipeArrayList.add(data.get(clickedPosition));
            bundle.putParcelableArrayList(context.getResources().getString(R.string.parcel_ingredients),recipeArrayList);
            Intent intent = new Intent(context, RecipeIngredients.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}
