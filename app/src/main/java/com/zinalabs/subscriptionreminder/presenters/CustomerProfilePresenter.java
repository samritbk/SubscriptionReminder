package com.zinalabs.subscriptionreminder.presenters;

import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.zinalabs.subscriptionreminder.interfaces.CustomerProfileView;
import com.zinalabs.subscriptionreminder.model.Customer;

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

    public void changeActivity(Class activity, @Nullable Customer customer){
        view.changeActivty(activity, customer);
    }
}
