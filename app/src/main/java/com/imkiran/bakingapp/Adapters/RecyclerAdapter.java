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
import android.widget.Toast;
import com.imkiran.bakingapp.R;
import com.imkiran.bakingapp.models.Recipe;

import java.util.List;


/**
 * Created by imkiran on 29/12/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>{

    private Context context;
    private List<Recipe> dataList;

    public RecyclerAdapter(Context context,List<Recipe> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_list,null);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Recipe recipe = dataList.get(position);

        /*Picasso.with(context)
                .load(recipe.getImage())
                .into(holder.fullImage); */

        holder.nameTextView.setText(recipe.getName());

        /*Picasso.with(context)
                .load(datum.getNameImage())
                .transform(new CircleTransform())
                .into(holder.profileImage); */

        //holder.profileName.setText(recipe.getServings());

    }

    @Override
    public int getItemCount() {
        return dataList!=null ? dataList.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        //ImageView fullImage;
        //ImageView profileImage;
        TextView nameTextView;
        //TextView profileName;
        //TextView timeStamp;
        CardView cardView;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        CustomViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.recipe_name);
            //profileName = (TextView) itemView.findViewById(R.id.profile_name);
            /*timeStamp = (TextView) itemView.findViewById(R.id.time_stamp);
            fullImage = (ImageView) itemView.findViewById(R.id.full_image);
            profileImage = (ImageView) itemView.findViewById(R.id.profile_image); */
            cardView = (CardView) itemView.findViewById(R.id.card_view);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Toast.makeText(context, dataList.get(clickedPosition).getName(), Toast.LENGTH_LONG).show();
        }
    }
}
