package com.imkiran.bakingapp;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.imkiran.bakingapp.Adapters.RecipeAdapter;
import com.imkiran.bakingapp.models.Recipe;
import com.imkiran.bakingapp.retrofit.IRecipe;
import com.imkiran.bakingapp.retrofit.RetrofitBuilder;
import com.imkiran.bakingapp.utils.Helpers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Recipe>>{

    private static final int BAKING_APP_ASYNK_ID = 100;

    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    List<Recipe> dataList;
    RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout =  findViewById(R.id.activity_main);
        recyclerView =  findViewById(R.id.recycler_view);
        boolean targetSize = getResources().getBoolean(R.bool.tab_layout);
        if(targetSize) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        else{
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        }
        dataList = Collections.<Recipe>emptyList();
        getBakingData();
    }

    public void getBakingData() {
        try {
            if (new Helpers(this).isNetworkAvailable()) {



                LoaderManager loaderManager = getSupportLoaderManager();
                Loader<String> bakingAppLoader = loaderManager.getLoader(BAKING_APP_ASYNK_ID);
                if (bakingAppLoader == null) {
                    loaderManager.initLoader(BAKING_APP_ASYNK_ID, null, this);
                } else {
                    loaderManager.restartLoader(BAKING_APP_ASYNK_ID, null, this);
                }
            } else {
                Toast.makeText(this, getString(R.string.error_need_internet), Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Loader<List<Recipe>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<List<Recipe>>(this) {

            List<Recipe> theBakingAppResponse;
            @Override
            protected void onStartLoading() {

                if(theBakingAppResponse!=null){
                    deliverResult(theBakingAppResponse);
                }   else{
                    forceLoad();
                }
            }

            @Override
            public List<Recipe> loadInBackground() {
                try {
                    IRecipe iRecipe = RetrofitBuilder.Retrieve();
                    Call<ArrayList<Recipe>> recipe = iRecipe.getRecipe();
                    List<Recipe> recipes = recipe.execute().body();
                    return recipes;
                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }

            @Override
            public void deliverResult(List<Recipe> data) {
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<Recipe>> loader, List<Recipe> data) {
        if (null == data) {
            Toast.makeText(this, getString(R.string.no_data), Toast.LENGTH_LONG).show();
        } else {
            recipeAdapter = new RecipeAdapter(MainActivity.this, data);
            recyclerView.setAdapter(recipeAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Recipe>> loader) {

    }
}
