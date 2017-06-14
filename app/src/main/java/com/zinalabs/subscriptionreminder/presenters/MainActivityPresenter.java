package com.zinalabs.subscriptionreminder.presenters;

import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.zinalabs.subscriptionreminder.interfaces.MainActivityView;
import com.zinalabs.subscriptionreminder.model.Customer;

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

    public static void changeActivity(Class activityClass, @Nullable Customer customer) {
        view.changeActivity(activityClass, customer);
    }

}
