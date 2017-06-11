package com.zinalabs.subscriptionreminder.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.zinalabs.subscriptionreminder.R;

public class AddCustomerActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        setToolbar("Add Customer");
        showToolbarButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_customer_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void setToolbar(@Nullable String title) {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
    }

    private void showToolbarButtons(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
