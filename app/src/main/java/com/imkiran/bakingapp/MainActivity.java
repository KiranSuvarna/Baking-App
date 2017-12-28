package com.imkiran.bakingapp;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.imkiran.bakingapp.util.Helpers;
import com.imkiran.bakingapp.util.JsonUtils;
import com.imkiran.bakingapp.util.NetworkUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<String>> {

    private static final int BAKING_APP_ASYNK_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public Loader<ArrayList<String>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<ArrayList<String>>(this) {

            ArrayList<String> theBakingAppResponse;
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
            public ArrayList<String> loadInBackground() {
                String urlString = args.getString(getString(R.string.bakingapp_data_url_key));
                if(urlString == null | TextUtils.isEmpty(urlString)){
                    return null;
                }

                try {
                    URL url = new URL(urlString);
                    String rawData = NetworkUtils.getBakingAppData(url);
                    return JsonUtils.getRecipeNames(rawData);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                return null;
            }

            @Override
            public void deliverResult(ArrayList<String> data) {
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<String>> loader, ArrayList<String> data) {
        if (null == data) {
            Toast.makeText(this, getString(R.string.no_data), Toast.LENGTH_LONG).show();
        } else {
            Log.d("MainActivity:data",data.toString());
            Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<String>> loader) {

    }
}
