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

import com.google.gson.Gson;
import com.imkiran.bakingapp.R;
import com.imkiran.bakingapp.RecipeDetails;
import com.imkiran.bakingapp.models.Recipe;

import java.util.List;

/**
 * Created by imkiran on 03/01/18.
 */

public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.CustomViewHolder>{

    private Context context;
    private List<Recipe> data;

    public RecipeDetailsAdapter(Context context, List<Recipe> data){
        this.data = data;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_list_recipe_details,null);
        RecipeDetailsAdapter.CustomViewHolder customViewHolder = new RecipeDetailsAdapter.CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
            Recipe recipe = data.get(position);
            Log.d("recipe_details:ing",new Gson().toJson(recipe.getIngredients().get(0).getIngredient()));
            holder.recipeIngredient.setText(recipe.getIngredients().get(0).getIngredient());
    }

    @Override
    public int getItemCount() {
        return 0;//data!=null ? data.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView recipeIngredient;
        CardView cardView;


        CustomViewHolder(View itemView) {
            super(itemView);

            recipeIngredient =  itemView.findViewById(R.id.recipe_ingredient);
            cardView =  itemView.findViewById(R.id.card_view_recipe_details);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
