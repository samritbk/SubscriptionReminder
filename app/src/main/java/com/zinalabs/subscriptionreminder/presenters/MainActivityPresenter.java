package com.zinalabs.subscriptionreminder.presenters;

import android.view.MenuItem;

import com.zinalabs.subscriptionreminder.interfaces.MainActivityView;

/**
 * Created by Beraki on 5/24/2017.
 */

public class MainActivityPresenter {

    static MainActivityView view;

    public MainActivityPresenter(MainActivityView view){
        this.view = view;
    }

    public void clickOnMenuItem(MenuItem item) {
        view.clickOnMenuItem(item);
    }

    public static void changeActivity(Class activityClass, int position) {
        view.changeActivity(activityClass, position);
    }

}
