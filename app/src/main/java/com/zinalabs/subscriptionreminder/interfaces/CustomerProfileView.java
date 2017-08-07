package com.zinalabs.subscriptionreminder.interfaces;

import android.content.Context;
import android.view.MenuItem;

import com.zinalabs.subscriptionreminder.model.Customer;

/**
 * Created by Beraki on 6/4/2017.
 */

public interface CustomerProfileView {
    void clickOnMenuItem(MenuItem item);

    void changeActivty(Class activity, Customer customer);

    void callButtonClicked(Context context, int number);
}
