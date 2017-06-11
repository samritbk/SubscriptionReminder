package com.zinalabs.subscriptionreminder.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;

import com.zinalabs.subscriptionreminder.R;
import com.zinalabs.subscriptionreminder.interfaces.CustomerProfileView;
import com.zinalabs.subscriptionreminder.presenters.CustomerProfilePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomerProfileActivity extends AppCompatActivity implements CustomerProfileView {

    Toolbar toolbar;
    TextView customerNameDis;
    TextView locationDis;
    TextView statusDis;
    JSONArray customers;
    JSONObject customer;
    int id;
    String name;
    String location;
    int status;
    CustomerProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        setToolbar();
        showToolbarButtons();
        setToolbarTitle(toolbar, null);

        initialization();

        presenter= new CustomerProfilePresenter(this);
        Intent activityIntent=this.getIntent();
        Bundle extras=activityIntent.getExtras();
        int position=extras.getInt("position");

        try {

            customers= MainActivity.jsonArray;
            customer=customers.getJSONObject(position);
            id= customer.getInt("id");
            name= customer.getString("name");
            location= customer.getString("location");
            status= customer.getInt("status");

            customerNameDis.setText(name);
            locationDis.setText(location);
            getSupportActionBar().setTitle(name);
            if(status != 0) {
                statusDis.setText("Connected");
            }else{
                statusDis.setText("Disconnected");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.customer_profile_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        presenter.clickOnMenuItem(item);

        return super.onOptionsItemSelected(item);
    }

    private void initialization() {
        customerNameDis= (TextView) findViewById(R.id.customerNameDis);
        locationDis= (TextView) findViewById(R.id.locationDis);
        statusDis= (TextView) findViewById(R.id.statusDis);
    }

    private void setToolbar() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    private void setToolbarTitle(Toolbar toolbar, String title){
        setTitle(title);
    }
    private void showToolbarButtons(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void clickOnMenuItem(MenuItem item) {
        switch(item.getItemId()){
            case R.id.addPayment:
                presenter.changeActivity(MakePaymentActivity.class, id);
                break;
        }
    }

    @Override
    public void changeActivty(Class activity, int customer_id) {
        Intent changeActivty= new Intent(this ,activity);
        changeActivty.putExtra("id", customer_id);
        startActivity(changeActivty);
    }
}
