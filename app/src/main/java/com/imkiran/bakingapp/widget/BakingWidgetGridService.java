package com.imkiran.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.imkiran.bakingapp.R;

import java.util.List;

import static com.imkiran.bakingapp.widget.BakingWidget.ingredientsList;

/**
 * Created by imkiran on 13/02/18.
 */

public class BakingWidgetGridService extends RemoteViewsService {

    List<String> remoteIngredientsList;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingWidgetGridRemoteViewFactory(this.getApplicationContext(), intent);
    }

    class BakingWidgetGridRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

        Context context;

        public BakingWidgetGridRemoteViewFactory(Context context, Intent intent) {
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            Log.d("ingredientslist : ",new Gson().toJson(ingredientsList));
            remoteIngredientsList = ingredientsList;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            Log.d("ingredinetslistcount ", String.valueOf(remoteIngredientsList.size()));
            return remoteIngredientsList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking_widget_grid_view_item);
            remoteViews.setTextViewText(R.id.baking_widget_grid_view_item, remoteIngredientsList.get(position));
            Log.d("ingredientpos : ",remoteIngredientsList.get(position));
            Intent fillInIntent = new Intent();
            remoteViews.setOnClickFillInIntent(R.id.baking_widget_grid_view_item, fillInIntent);
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }


}
