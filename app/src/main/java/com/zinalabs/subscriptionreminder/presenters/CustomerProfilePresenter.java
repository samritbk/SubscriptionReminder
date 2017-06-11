package com.zinalabs.subscriptionreminder.presenters;

import android.view.MenuItem;

import com.zinalabs.subscriptionreminder.interfaces.CustomerProfileView;

/**
 * Created by Beraki on 6/4/2017.
 */

public class CustomerProfilePresenter {

    static CustomerProfileView view;

    public CustomerProfilePresenter(CustomerProfileView view){
        this.view = view;
    }

    public void clickOnMenuItem(MenuItem item) {
        view.clickOnMenuItem(item);
    }

    public void changeActivity(Class activity, int customer_id){
        view.changeActivty(activity, customer_id);
    }
}
