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
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
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
    CustomerAdapter customerAdapter;
    ArrayList<Customer> customersList=new ArrayList<>();

    String url="http://192.168.0.100/projectabdi/index.php?mode=getCustomers";

    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        presenter = new MainActivityPresenter(this);

        initialization();
        
        jsonArrayRequest= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            presenter.onVolleyResponse(response, customersList, customerAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    public void initialization(){
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        customerAdapter = new CustomerAdapter(this);
        recyclerView.setAdapter(customerAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        requestQueue = Volley.newRequestQueue(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                changeActivity(AddCustomerActivity.class, null);
                break;
        }
    }

    @Override
    public void showProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar(ProgressBar progressBar) {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void changeActivity(Class activityClass, Customer customer) {
        Intent paymentActivity= new Intent(context, activityClass);

        if(customer != null) {
            paymentActivity.putExtra("customer", customer);
        }

        context.startActivity(paymentActivity);
    }

    @Override
    public void onVolleyReponse(JSONArray response, ArrayList<Customer> customersList, CustomerAdapter customerAdapter) {
        if(response.length() != 0) {
            for (int i=0; i < response.length(); i++) {
                try {

                    JSONObject customer= response.getJSONObject(i);

                    int customer_id=customer.getInt("customer_id");
                    String name= customer.getString("name");
                    String address= customer.getString("address");
                    int telephone= customer.getInt("telephone");
                    int status=customer.getInt("status");
                    Customer customerObj=new Customer(customer_id, name, address, telephone, status);
                    customersList.add(customerObj);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        customerAdapter.addList(customersList);
    }
}
