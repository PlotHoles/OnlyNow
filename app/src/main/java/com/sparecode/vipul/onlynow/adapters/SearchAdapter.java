package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.SearchLocationData;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 29/12/16.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {



//    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }


    private Context context;
    private OnClickListener listener;
    private List<SearchLocationData> data;

    public SearchAdapter(Context context, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public SearchAdapter(Context context, List<SearchLocationData> data, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.data = data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.location_image)
        ImageView locationImage;
        @Bind(R.id.location_name)
        LatoTextView locationName;
        @Bind(R.id.searchlinear)
        LinearLayout searchlinear;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


//            text_name = (TextView) view.findViewById(R.id.text_name);
//            text_place = (TextView) view.findViewById(R.id.text_place);
//            text_time = (TextView) view.findViewById(R.id.text_time);
//            text_payments = (TextView) view.findViewById(R.id.text_payments);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.design_search_recyclerview, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.searchlinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(position);
            }
        });

        holder.locationName.setText(data.get(position).getArea());
//        History history = historyList.get(position);
//        holder.text_name.setText(history.getName());
//        holder.text_place.setText(history.getPlace());
//        holder.text_time.setText(history.getTime());
//        holder.text_payments.setText(history.getPayments());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
