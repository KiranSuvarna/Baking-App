package com.imkiran.bakingapp.utils;

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

public class JsonUtils {
    public static List<Recipe> getRecipeNames(String data){
        final String TAG_ID = "id";
        final String TAG_NAME = "name";
        final String TAG_SERVINGS = "servings";
        final String TAG_image = "image";
        Recipe[] recipes = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
             recipes = new Recipe[jsonArray.length()];
            for(int i=0;i<jsonArray.length();i++){
                recipes[i] = new Recipe();
                JSONObject recipeInfo = jsonArray.getJSONObject(i);
                recipes[i].setId(recipeInfo.getString(TAG_ID));
                recipes[i].setName(recipeInfo.getString(TAG_NAME));
                recipes[i].setServings(recipeInfo.getString(TAG_SERVINGS));
                recipes[i].setImage(recipeInfo.getString(TAG_image));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList(Arrays.asList(recipes));
    }
}
