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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        presenter = new MainActivityPresenter(this);

        jsonArray=new JSONArray();
        JSONObject customerOne=new JSONObject();
        final JSONObject customerTwo=new JSONObject();

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


        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        customerAdapter = new CustomerAdapter(this);
        recyclerView.setAdapter(customerAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
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
                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
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
