package com.zinalabs.subscriptionreminder.presenters;

import android.widget.EditText;
import android.widget.TextView;

import com.zinalabs.subscriptionreminder.interfaces.MainActivityView;
import com.zinalabs.subscriptionreminder.interfaces.MakePaymentView;

import java.util.Date;

/**
 * Created by Beraki on 6/6/2017.
 */

public class MakePaymentPresenter {
    MakePaymentView view;

    public MakePaymentPresenter(MakePaymentView view){
        this.view =  view;
    }

    public void submitPayment(int customer_id, Date upto){
        view.submitPayment(customer_id, upto);
    }
    public void selectFromDate(EditText EDview, EditText toDate){
        view.selectFromDate(EDview, toDate);
    }

    public void setEditText(EditText eDview, String text) {
        view.setEditText(eDview, text);
    }

    public void setTextViewText(TextView textView, String text){
        view.setTextViewText(textView, text);
    }

    public void payButtonClicked() {
        view.payButtonClicked();
    }

}
