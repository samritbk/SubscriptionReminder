package com.zinalabs.subscriptionreminder.interfaces;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.zinalabs.subscriptionreminder.adapter.CustomerAdapter;
import com.zinalabs.subscriptionreminder.model.Customer;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Beraki on 5/24/2017.
 */

public interface MainActivityView {
    void clickOnMenuItem(MenuItem item);
    void changeActivity(Class activityClass, @Nullable Customer customer);
    void onVolleyReponse(JSONArray response, ArrayList<Customer> customersList, CustomerAdapter customerAdapter);
}
