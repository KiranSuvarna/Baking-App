package com.imkiran.bakingapp.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by imkiran on 28/12/17.
 */

public class JsonUtils {
    public static ArrayList<String> getRecipeNames(String data){
        ArrayList<String> recipeNames = new ArrayList<String>();
        final String TAG_NAME = "name";
        try {
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++){
                recipeNames.add(jsonArray.getJSONObject(i).getString(TAG_NAME));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeNames;
    }
}
