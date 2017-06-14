package com.zinalabs.subscriptionreminder.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.zinalabs.subscriptionreminder.activities.MainActivity;
import com.zinalabs.subscriptionreminder.R;
import com.zinalabs.subscriptionreminder.activities.CustomerProfileActivity;
import com.zinalabs.subscriptionreminder.model.Customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Beraki on 12/17/2016.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolderAdapter> {


    ArrayList<Customer> data=new ArrayList<Customer>();
    LayoutInflater inflater;
    Context context;

    public static String server = "http://eriotc.org/";

    public CustomerAdapter(Context context){
        inflater= LayoutInflater.from(context);

    }

    @Override
    public ViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.recyclerview, parent, false);
        ViewHolderAdapter viewHolder= new ViewHolderAdapter(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderAdapter holder, final int position) {

        final Customer customer= data.get(position);

        Toast.makeText(context, data.toString(), Toast.LENGTH_SHORT).show();

        String name=customer.getName();
        String location=customer.getAddress();
        int status=customer.getStatus();

        holder.customerName.setText(name);
        holder.customerLocation.setText(location);

        if(status != 0) {
            holder.customerStatus.setBackground(new ColorDrawable(context.getResources().getColor(android.R.color.holo_green_dark)));
        }else{
            holder.customerStatus.setBackground(new ColorDrawable(context.getResources().getColor(android.R.color.holo_red_dark)));
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity main=new MainActivity();
                MainActivity.presenter.changeActivity(CustomerProfileActivity.class,customer);
                Toast.makeText(context, position+"", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addList(ArrayList<Customer> customersList){
        this.data = customersList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Created by Beraki on 12/17/2016.
     */

    public class ViewHolderAdapter extends RecyclerView.ViewHolder{

        private RelativeLayout parent;
        private TextView customerName;
        private TextView customerLocation;
        private View customerStatus;


        public ViewHolderAdapter(View itemView) {
            super(itemView);
            context = itemView.getContext();

            parent= (RelativeLayout) itemView.findViewById(R.id.parent);
            customerName=  (TextView) itemView.findViewById(R.id.customerName);
            customerLocation = (TextView) itemView.findViewById(R.id.customerLocation);
            customerStatus= itemView.findViewById(R.id.customerStatus);
        }
    }
}