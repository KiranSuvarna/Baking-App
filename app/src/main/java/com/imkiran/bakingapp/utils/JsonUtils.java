package com.imkiran.bakingapp.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imkiran.bakingapp.R;
import com.imkiran.bakingapp.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by imkiran on 28/12/17.
 */

public class JsonUtils  extends AppCompatActivity{

   public static List<Recipe> getRecipeNames(Context context,List<Recipe> dataObject){
       Gson gson = new Gson();
       String data = gson.toJson(dataObject);
       final String TAG_ID = "id";
       final String TAG_NAME = "name";
       final String TAG_SERVINGS = "servings";
        Recipe[] recipes = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            recipes = new Recipe[jsonArray.length()];
            for(int i=0;i<jsonArray.length();i++){
                recipes[i] = new Recipe();
                JSONObject recipeInfo = jsonArray.getJSONObject(i);
                recipes[i].setId(recipeInfo.getInt(TAG_ID));
                recipes[i].setName(recipeInfo.getString(TAG_NAME));
                recipes[i].setServings(recipeInfo.getInt(TAG_SERVINGS));
                //String ingredients = recipeInfo.getString("ingredients");
                recipes[i].setIngredients(new ArrayList(Arrays.asList(recipeInfo.get("ingredients"))));
                //String steps = recipeInfo.getString("steps");
                recipes[i].setSteps(new ArrayList(Arrays.asList(recipeInfo.get("steps"))));
                switch (recipes[i].getName()){
                    case "Nutella Pie":
                        recipes[i].setImage(context.getString(R.string.nutella_pie_image_url));
                        break;
                    case  "Brownies":
                        recipes[i].setImage(context.getString(R.string.brownies_image_url));
                        break;
                    case "Yellow Cake":
                        recipes[i].setImage(context.getString(R.string.yellowcake_image_url_));
                        break;
                    case "Cheesecake":
                        recipes[i].setImage(context.getString(R.string.cheesecake_image_url));
                        break;
                    default: break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
       return new ArrayList(Arrays.asList(recipes));
    }
}
