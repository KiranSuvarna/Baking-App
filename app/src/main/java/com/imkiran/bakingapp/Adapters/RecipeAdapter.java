package com.imkiran.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imkiran.bakingapp.R;
import com.imkiran.bakingapp.RecipeDetails;
import com.imkiran.bakingapp.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by imkiran on 29/12/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.CustomViewHolder>{

    private Context context;
    private List<Recipe> dataList;

    public RecipeAdapter(Context context, List<Recipe> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_list_recipe,null);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Recipe recipe = dataList.get(position);
        String recipeImage = "";
        switch (recipe.getName()){
            case "Nutella Pie":
                recipeImage = context.getString(R.string.nutella_pie_image_url);
                break;
            case  "Brownies":
                recipeImage  = context.getString(R.string.brownies_image_url);
                break;
            case "Yellow Cake":
                recipeImage = context.getString(R.string.yellowcake_image_url_);
                break;
            case "Cheesecake":
                recipeImage = context.getString(R.string.cheesecake_image_url);
                break;
            default: break;
        }

        Picasso.with(context)
                .load(recipeImage)
                .fit()
                .centerCrop()
                .into(holder.recipe_image);

        holder.nameTextView.setText(recipe.getName());

    }

    @Override
    public int getItemCount() {
        return dataList!=null ? dataList.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView recipe_image;
        TextView nameTextView;
        CardView cardView;


        CustomViewHolder(View itemView) {
            super(itemView);

            nameTextView =  itemView.findViewById(R.id.recipe_name);
            recipe_image =  itemView.findViewById(R.id.recipe_image);
            cardView =  itemView.findViewById(R.id.card_view);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Bundle bundle = new Bundle();
            ArrayList<Recipe> recipeArrayList = new ArrayList<>();
            recipeArrayList.add(dataList.get(clickedPosition));
            Log.d("clicked_position :",new Gson().toJson(recipeArrayList));
            bundle.putParcelableArrayList(context.getResources().getString(R.string.parcel_recipe),recipeArrayList);
            Intent intent = new Intent(context, RecipeDetails.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
            Toast.makeText(context, dataList.get(clickedPosition).getName(), Toast.LENGTH_LONG).show();
        }
    }
}
