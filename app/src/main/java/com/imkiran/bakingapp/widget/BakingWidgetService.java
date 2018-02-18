package com.imkiran.bakingapp.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by imkiran on 12/02/18.
 */

public class BakingWidgetService extends IntentService {

    public static String FROM_ACTIVITY_INGREDIENTS_LIST = "from_activity_ingredients_list";

    public BakingWidgetService() {
        super("BakingWidgetService");
    }

    public static void startUpdateBakingWidget(Context context, ArrayList<String> fromActivityIngredientsList) {
        Intent intent = new Intent(context, BakingWidgetService.class);
        intent.putExtra(FROM_ACTIVITY_INGREDIENTS_LIST, fromActivityIngredientsList);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            ArrayList<String> fromActivityIngredientsList = intent.getExtras().getStringArrayList(FROM_ACTIVITY_INGREDIENTS_LIST);
            handleUpdateBakingWidget(fromActivityIngredientsList);
        }
    }

    private void handleUpdateBakingWidget(ArrayList<String> fromActivityIngredientsList) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.putExtra(FROM_ACTIVITY_INGREDIENTS_LIST, fromActivityIngredientsList);
        sendBroadcast(intent);
    }
}
