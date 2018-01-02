package com.imkiran.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imkiran.bakingapp.Adapters.RecyclerAdapter;
import com.imkiran.bakingapp.models.Recipe;
import com.imkiran.bakingapp.retrofit.IRecipe;
import com.imkiran.bakingapp.retrofit.RetrofitBuilder;
import com.imkiran.bakingapp.utils.Helpers;
import com.imkiran.bakingapp.utils.JsonUtils;
import com.imkiran.bakingapp.utils.NetworkUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Recipe>>{

    private static final int BAKING_APP_ASYNK_ID = 100;

    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    List<Recipe> dataList;
    RecyclerAdapter recyclerAdapter;

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
                String bakingDataUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
                URL url = new URL(bakingDataUrl);

                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.bakingapp_data_url_key), url.toString());

                LoaderManager loaderManager = getSupportLoaderManager();
                Loader<String> bakingAppLoader = loaderManager.getLoader(BAKING_APP_ASYNK_ID);
                if (bakingAppLoader == null) {
                    loaderManager.initLoader(BAKING_APP_ASYNK_ID, bundle, this);
                } else {
                    loaderManager.restartLoader(BAKING_APP_ASYNK_ID, bundle, this);
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
                if(args == null){
                    return;
                }
                if(theBakingAppResponse!=null){
                    deliverResult(theBakingAppResponse);
                }   else{
                    forceLoad();
                }
            }

            @Override
            public List<Recipe> loadInBackground() {
                String urlString = args.getString(getString(R.string.bakingapp_data_url_key));
                if(urlString == null | TextUtils.isEmpty(urlString)){
                    return null;
                }

                try {
                    IRecipe iRecipe = RetrofitBuilder.Retrieve();
                    Call<ArrayList<Recipe>> recipe = iRecipe.getRecipe();
                    List<Recipe> recipes = recipe.execute().body();
                    Log.d("retrofit:data",new Gson().toJson(recipes));
                    return JsonUtils.getRecipeNames(MainActivity.this,recipes);
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
            recyclerAdapter = new RecyclerAdapter(MainActivity.this, data);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Recipe>> loader) {

    }
}
