package com.imkiran.bakingapp.retrofit;


import com.imkiran.bakingapp.models.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface IRecipe {
    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipe();
}