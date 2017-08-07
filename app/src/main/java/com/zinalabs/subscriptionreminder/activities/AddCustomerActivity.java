package com.zinalabs.subscriptionreminder.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zinalabs.subscriptionreminder.R;
import com.zinalabs.subscriptionreminder.interfaces.AddCustomerView;
import com.zinalabs.subscriptionreminder.presenters.AddCustomerPresenter;

import java.util.ArrayList;

public class AddCustomerActivity extends AppCompatActivity implements AddCustomerView, View.OnClickListener {

    Toolbar toolbar;
    EditText customerName;
    Spinner locationSpinner;
    EditText addressBoxET;
    EditText tel;
    EditText comment;
    AddCustomerPresenter presenter;
    String mainUrl= "http://192.168.0.100/projectabdi/";

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
            case R.id.actionAddCustomer:
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

        ArrayList<String> list=new ArrayList<String>();
        final ProgressDialog progressDialog;

        String name= customerName.getText().toString();
        String address= locationSpinner.getSelectedItem().toString();
        String boxNo= addressBoxET.getText().toString();
        String telephone= tel.getText().toString();

        if(name.length() < 3){
            list.add("- Name is too short\n");
        }
        if(boxNo.length() < 1){
            list.add("- Add Box No\n");
        }
        if(telephone.length() != 10){
            list.add("- Telephone is invalid\n");
        }

        if(list.size() != 0){

            AlertDialog.Builder alert= new AlertDialog.Builder(this);
            StringBuilder message = new StringBuilder();
            for(int i=0; i < list.size(); i++){
                message.append(list.get(i));
                alert.setMessage(message);
            }
            alert.setPositiveButton("Okay", null);
            alert.show();

        }else{
            progressDialog= new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Adding customer...");
            //progressDialog.setCancelable(false);
            progressDialog.show();

            String url= mainUrl+"index.php?mode=addCustomer&name="+name+"&address="+address+"-"+boxNo+"&tel="+telephone;
            Toast.makeText(this, url, Toast.LENGTH_LONG).show();
            StringRequest stringRequest= new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.hide();
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddCustomerActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
            });

            RequestQueue requestQueue=Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    }

    @Override
    public void onClick(View view) {
        int viewId= view.getId();

        switch(viewId){
            case R.id.addCustomerButton:
                // CODE HERE
                presenter.addCustomerButtonClicked();
                break;
        }
    }
}
