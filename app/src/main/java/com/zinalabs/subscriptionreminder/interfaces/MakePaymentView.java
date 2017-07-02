package com.zinalabs.subscriptionreminder.interfaces;

import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Beraki on 6/6/2017.
 */

public interface MakePaymentView {
    void submitPayment(int customer_id, Date upto);
    void selectFromDate(EditText EDview, EditText EDtoView);
    void setEditText(EditText eDview, String text);
    void setTextViewText(TextView textView, String text);
    void payButtonClicked();
}
