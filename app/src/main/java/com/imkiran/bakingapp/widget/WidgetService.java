package com.imkiran.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.imkiran.bakingapp.R;

import java.util.List;

import static com.imkiran.bakingapp.widget.BakingWidget.ingredientsArrayList;

/**
 * Created by imkiran on 02/02/18.
 */

public class WidgetService extends RemoteViewsService {
    List<String> remoteViewIngredientsList;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsServiceFactory(this.getApplicationContext(),intent);
    }

    class RemoteViewsServiceFactory implements RemoteViewsService.RemoteViewsFactory {

        Context context = null;

        public RemoteViewsServiceFactory(Context context, Intent intent) {
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            remoteViewIngredientsList = ingredientsArrayList;

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return remoteViewIngredientsList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget_item);
            views.setTextViewText(R.id.widget_grid_view_item, remoteViewIngredientsList.get(position));
            Intent intent = new Intent();
            views.setOnClickFillInIntent(R.id.widget_grid_view_item, intent);
            return views;
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

