package com.zinalabs.subscriptionreminder.activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zinalabs.subscriptionreminder.R;
import com.zinalabs.subscriptionreminder.interfaces.MakePaymentView;
import com.zinalabs.subscriptionreminder.model.Customer;
import com.zinalabs.subscriptionreminder.presenters.MakePaymentPresenter;

import java.util.Calendar;
import java.util.Date;

public class MakePaymentActivity extends AppCompatActivity implements MakePaymentView {


    Toolbar toolbar;
    EditText fromDate;
    EditText toDate;
    Button payButton;
    TextView customerName;
    DatePickerDialog datePickerDialog;
    MakePaymentPresenter presenter;
    Bundle intentExtras;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);
        initialization();


        intentExtras=getIntent().getExtras();
        customer=(Customer) intentExtras.get("customer");

        presenter.setTextViewText(customerName, customer.getName());
        Toast.makeText(this, customer.toString(), Toast.LENGTH_SHORT).show();

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.selectFromDate(fromDate, toDate);
            }
        });
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.selectFromDate(toDate, toDate);
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
        customerName = (TextView) findViewById(R.id.customerName);
        fromDate = (EditText) findViewById(R.id.fromDate);
        toDate = (EditText) findViewById(R.id.toDate);
        payButton = (Button) findViewById(R.id.payButton);

        setSupportActionBar(toolbar);
        showUpButton();
    }

    @Override
    public void submitPayment(int customer_id, Date upto) {

    }

    @Override
    public void selectFromDate(final EditText EDview, final EditText EDtoView) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;

                int monthTo=month+1;
                presenter.setEditText(EDview, year+"-"+month+"-"+day);
                presenter.setEditText(EDtoView, year+"-"+monthTo+"-"+day);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void setEditText(EditText eDview, String text) {
        eDview.setText(text);
    }

    @Override
    public void setTextViewText(TextView textView, String text) {
        textView.setText(text);
    }

    @Override
    public void payButtonClicked() {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
