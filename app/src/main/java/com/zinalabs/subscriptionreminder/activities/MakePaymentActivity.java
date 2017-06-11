package com.zinalabs.subscriptionreminder.activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.zinalabs.subscriptionreminder.R;
import com.zinalabs.subscriptionreminder.interfaces.MakePaymentView;
import com.zinalabs.subscriptionreminder.presenters.MakePaymentPresenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MakePaymentActivity extends AppCompatActivity implements MakePaymentView {


    Toolbar toolbar;
    EditText fromDate;
    EditText toDate;
    Button payButton;
    DatePickerDialog datePickerDialog;
    MakePaymentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);
        initialization();

        setSupportActionBar(toolbar);
        showUpButton();
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.selectFromDate(fromDate);
            }
        });
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.selectFromDate(toDate);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.payButtonClicked();
            }
        });
    }

    private void showUpButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initialization() {
        presenter=new MakePaymentPresenter(this);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        fromDate = (EditText) findViewById(R.id.fromDate);
        toDate = (EditText) findViewById(R.id.toDate);
        payButton = (Button) findViewById(R.id.payButton);
    }

    @Override
    public void submitPayment(int customer_id, Date upto) {

    }

    @Override
    public void selectFromDate(final EditText EDview) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                presenter.setEditText(EDview, year+"-"+month+"-"+day);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void setEditText(EditText eDview, String text) {
        eDview.setText(text);
    }

    @Override
    public void payButtonClicked() {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
