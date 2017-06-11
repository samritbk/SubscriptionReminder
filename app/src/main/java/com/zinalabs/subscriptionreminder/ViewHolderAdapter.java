package com.zinalabs.subscriptionreminder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderAdapter extends RecyclerView.ViewHolder{

    private TextView articleTitle;
    private ImageView image;

    public ViewHolderAdapter(View itemView) {
        super(itemView);

        articleTitle = (TextView) itemView.findViewById(R.id.title);
        //image = (ImageView) itemView.findViewById(R.id.img);
    }
}