package com.imkiran.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RecipeStepInstruction extends AppCompatActivity {

    CardView cardViewRecipeInstruction;
    TextView textViewRecipeInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_instruction);
        cardViewRecipeInstruction = findViewById(R.id.card_view_recipe_step_instruction);
        textViewRecipeInstruction = findViewById(R.id.recipe_step_instruction);

        Bundle intent = getIntent().getExtras();
        textViewRecipeInstruction.setText(intent.getString(getResources().getString(R.string.recipe_video_step_instruction)));


    }
}
