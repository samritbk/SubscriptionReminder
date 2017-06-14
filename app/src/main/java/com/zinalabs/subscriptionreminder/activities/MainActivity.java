package com.zinalabs.subscriptionreminder.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zinalabs.subscriptionreminder.R;
import com.zinalabs.subscriptionreminder.adapter.CustomerAdapter;
import com.zinalabs.subscriptionreminder.interfaces.MainActivityView;
import com.zinalabs.subscriptionreminder.model.Customer;
import com.zinalabs.subscriptionreminder.presenters.MainActivityPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    Context context;
    public static JSONArray jsonArray;
    public static MainActivityPresenter presenter;
    ArrayList<Customer> customersList=new ArrayList<Customer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        presenter = new MainActivityPresenter(this);

        jsonArray=new JSONArray();
        JSONObject customerOne=new JSONObject();
        JSONObject customerTwo=new JSONObject();

        try {
            customerOne.put("id",1);
            customerOne.put("name","Yusuf");
            customerOne.put("location","lower");
            customerOne.put("status",1);

            customerOne.put("id",2);
            customerTwo.put("name","Abdifatah");
            customerTwo.put("location","upper");
            customerTwo.put("status",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonArray.put(customerOne);
        jsonArray.put(customerTwo);


        Customer customer= new Customer(1,"Yusuf","lower",1);
        Customer customers= new Customer(2,"Abdifatah","upper",0);

        customersList.add(customer);
        customersList.add(customers);


        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        CustomerAdapter customerAdapter = new CustomerAdapter(this);
        recyclerView.setAdapter(customerAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        customerAdapter.addList(customersList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.clickOnMenuItem(item);

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void clickOnMenuItem(MenuItem item) {
        switch(item.getItemId()){
            case R.id.addCustomer:
                presenter.changeActivity(AddCustomerActivity.class, null);
                break;
        }
    }

    @Override
    public void changeActivity(Class activityClass, Customer customer) {
        Intent paymentActivity= new Intent(context, activityClass);

        if(customer != null) {
            paymentActivity.putExtra("customer", customer);
        }

        context.startActivity(paymentActivity);
    }
}
