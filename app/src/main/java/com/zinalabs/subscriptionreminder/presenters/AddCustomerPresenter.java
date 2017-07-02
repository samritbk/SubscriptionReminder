package com.zinalabs.subscriptionreminder.presenters;

import com.zinalabs.subscriptionreminder.interfaces.AddCustomerView;

/**
 * Created by Beraki on 6/29/2017.
 */

public class AddCustomerPresenter {

    AddCustomerView view;

    public AddCustomerPresenter(AddCustomerView view){
        this.view = view;
    }


    public void addCustomerButtonClicked(){
        view.addCustomerButtonClicked();
    }

}
