package com.zinalabs.subscriptionreminder.interfaces;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.zinalabs.subscriptionreminder.model.Customer;

/**
 * Created by Beraki on 5/24/2017.
 */

public interface MainActivityView {
    void clickOnMenuItem(MenuItem item);
    void changeActivity(Class activityClass, @Nullable Customer customer);
}
