package com.zinalabs.subscriptionreminder.presenters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.zinalabs.subscriptionreminder.adapter.CustomerAdapter;
import com.zinalabs.subscriptionreminder.interfaces.MainActivityView;
import com.zinalabs.subscriptionreminder.model.Customer;

import org.json.JSONArray;

import java.util.ArrayList;

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

    public void showProgressBar(ProgressBar progressBar) {
        view.showProgressBar(progressBar);
    }

    public void hideProgressBar(ProgressBar progressBar) {
        view.showProgressBar(progressBar);
    }

    public static void changeActivity(Class activityClass, @Nullable Customer customer) {
        view.changeActivity(activityClass, customer);
    }

    public static void onVolleyResponse(JSONArray response, ArrayList<Customer> customersList, CustomerAdapter customerAdapter){
        view.onVolleyReponse(response, customersList, customerAdapter);
    }

}
