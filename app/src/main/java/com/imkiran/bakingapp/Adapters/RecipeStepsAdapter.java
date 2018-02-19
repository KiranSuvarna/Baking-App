package com.imkiran.bakingapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imkiran.bakingapp.R;
import com.imkiran.bakingapp.models.Steps;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by imkiran on 08/01/18.
 */

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.CustomViewHolder> {
    private Context context;
    private List<Steps> data;


    final private ItemClickListener itemClickListener;


    public interface ItemClickListener {
        void onClick(List<Steps> stepsList, int clickedIndex);
    }

    public RecipeStepsAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setData(Context context, List<Steps> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecipeStepsAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_list_recipe_steps, null);
        RecipeStepsAdapter.CustomViewHolder customViewHolder = new RecipeStepsAdapter.CustomViewHolder(view);
        return customViewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final RecipeStepsAdapter.CustomViewHolder holder, int position) {
        Steps steps = data.get(position);

        final String videoURL = steps.getVideoURL();
        if (!videoURL.isEmpty()) {
            //generate thumbnail
            Glide
                    .with(context)
                    .load(videoURL)
                    .thumbnail(1f)
                    .into(holder.recipeStepImage);
        } else {
            //load default image
            Picasso.with(context)
                    .load(R.drawable.baking)
                    .fit()
                    .centerCrop()
                    .into(holder.recipeStepImage);
        }
        holder.recipeStepTitle.setText(steps.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView recipeStepImage;
        TextView recipeStepTitle;
        CardView cardView;

        CustomViewHolder(View itemView) {
            super(itemView);

            recipeStepImage = itemView.findViewById(R.id.recipe_step_image);
            recipeStepTitle = itemView.findViewById(R.id.recipe_step_title);
            cardView = itemView.findViewById(R.id.card_view_recipe_steps);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            itemClickListener.onClick(data, clickedPosition);

        }

    }
}

