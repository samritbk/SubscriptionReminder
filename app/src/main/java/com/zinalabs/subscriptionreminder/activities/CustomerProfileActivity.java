package com.zinalabs.subscriptionreminder.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.zinalabs.subscriptionreminder.R;
import com.zinalabs.subscriptionreminder.interfaces.CustomerProfileView;
import com.zinalabs.subscriptionreminder.model.Customer;
import com.zinalabs.subscriptionreminder.presenters.CustomerProfilePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Permission;

public class CustomerProfileActivity extends AppCompatActivity implements CustomerProfileView, View.OnClickListener {

    Toolbar toolbar;
    TextView customerNameDis;
    TextView locationDis;
    TextView statusDis;
    int id;
    String name;
    String location;
    int status;
    CustomerProfilePresenter presenter;
    Customer customer;
    Button callButton;
    final int MYPERMISSION = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        setToolbar();
        showToolbarButtons();
        setToolbarTitle(toolbar, null);

        initialization();

        presenter = new CustomerProfilePresenter(this);
        Intent activityIntent = this.getIntent();
        Bundle extras = activityIntent.getExtras();
        customer = (Customer) extras.get("customer");

        id = customer.getId();
        name = customer.getName();
        location = customer.getAddress();
        status = customer.getStatus();

        customerNameDis.setText(name);
        locationDis.setText(location);
        getSupportActionBar().setTitle(name);
        if (status != 0) {
            statusDis.setText("Connected");
        } else {
            statusDis.setText("Disconnected");
        }

        callButton.setText("Call "+customer.getName());
        callButton.setOnClickListener(this);
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
        customerNameDis = (TextView) findViewById(R.id.customerNameDis);
        locationDis = (TextView) findViewById(R.id.locationDis);
        statusDis = (TextView) findViewById(R.id.statusDis);
        callButton = (Button) findViewById(R.id.callButton);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setToolbarTitle(Toolbar toolbar, String title) {
        setTitle(title);
    }

    private void showToolbarButtons() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void clickOnMenuItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addPayment:
                presenter.changeActivity(MakePaymentActivity.class, customer);
                break;
        }
    }

    @Override
    public void changeActivty(Class activity, Customer customer) {
        Intent changeActivty = new Intent(this, activity);
        changeActivty.putExtra("customer", customer);
        startActivity(changeActivty);
    }

    @Override
    public void callButtonClicked(Context context, int number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:+256" + number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {


                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MYPERMISSION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case MYPERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Accepted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.callButton:
                presenter.callButtonClicked(this, customer.getTelephone());
                break;
        }
    }
}
