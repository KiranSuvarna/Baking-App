package com.imkiran.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imkiran.bakingapp.R;
import com.imkiran.bakingapp.models.Ingredients;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by imkiran on 07/01/18.
 */

public class RecipeIngredientHeadAdapter extends RecyclerView.Adapter<RecipeIngredientHeadAdapter.CustomViewHolder> {

    private Context context;
    private String data;

    public RecipeIngredientHeadAdapter(Context context, String data){
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
        }
    }
}
