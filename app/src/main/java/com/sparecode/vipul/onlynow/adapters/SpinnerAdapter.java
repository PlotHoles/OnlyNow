package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.ClientGetCategoryData;

import java.util.List;

/**
 * Created by vipul on 17/3/17.
 */

public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<ClientGetCategoryData> data;

    public SpinnerAdapter() {

    }

    public SpinnerAdapter(Context context, List<ClientGetCategoryData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //View view = View.inflate(context, R.layout.design_spinner,parent);
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.design_spinner, parent, false);
        TextView spinner_text = (TextView)itemView.findViewById(R.id.spinner_text);
        spinner_text.setText(data.get(position).getName());
        return itemView;
    }
}
