package com.zinalabs.subscriptionreminder.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.zinalabs.subscriptionreminder.R;
import com.zinalabs.subscriptionreminder.interfaces.AddCustomerView;
import com.zinalabs.subscriptionreminder.presenters.AddCustomerPresenter;
import com.zinalabs.subscriptionreminder.presenters.MakePaymentPresenter;

import java.util.ArrayList;

public class AddCustomerActivity extends AppCompatActivity implements AddCustomerView, View.OnClickListener {

    Toolbar toolbar;
    EditText customerName;
    Spinner locationSpinner;
    EditText addressBoxET;
    EditText tel;
    EditText comment;
    AddCustomerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        setToolbar("Add Customer");

        initialization();
    }

    private void initialization() {
        presenter= new AddCustomerPresenter(this);
        customerName= (EditText) findViewById(R.id.customerNameET);
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        addressBoxET= (EditText) findViewById(R.id.addressBoxET);
        tel= (EditText) findViewById(R.id.telET);
        comment= (EditText) findViewById(R.id.commentET);
        showToolbarButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_customer_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.done:
                presenter.addCustomerButtonClicked();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setToolbar(@Nullable String title) {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
    }

    private void showToolbarButtons(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void addCustomerButtonClicked() {

        ArrayList<String> list=new ArrayList<>();

        String name= customerName.getText().toString();
        String address= locationSpinner.getSelectedItem().toString();
        String boxNo= addressBoxET.getText().toString();
        String telephone= tel.getText().toString();

        if(name.length() < 3){
            list.add("- Name is too short\n");
            if(boxNo.length() == 0){
                list.add("- Add Box No\n");
                if(telephone.length() != 10){
                    list.add("- Telephone is invalid\n");
                }
            }
        }

        if(!list.isEmpty()){
            AlertDialog.Builder alert= new AlertDialog.Builder(this);
            alert.setMessage(name+address+boxNo+telephone);
            alert.show();
        }

    }

    @Override
    public void onClick(View view) {
        int viewId= view.getId();

        switch(viewId){
            case R.id.addCustomerButton:
                // CODE HERE

                break;
        }
    }
}
