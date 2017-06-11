package com.zinalabs.subscriptionreminder.interfaces;

import android.view.MenuItem;

/**
 * Created by Beraki on 6/4/2017.
 */

public interface CustomerProfileView {
    void clickOnMenuItem(MenuItem item);

    void changeActivty(Class activity, int customer_id);
}
