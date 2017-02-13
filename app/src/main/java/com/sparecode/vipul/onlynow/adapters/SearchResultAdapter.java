package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.view.OnClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 29/12/16.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.MyViewHolder> {


//    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }

    private Context context;
    private OnClickListener listener;

    public SearchResultAdapter(Context context, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //public TextView text_name,text_place,text_time,text_payments;;
        @Bind(R.id.imageView3)
        ImageView imageView3;
        @Bind(R.id.imageView4)
        ImageView imageView4;
        @Bind(R.id.linearLayout5)
        LinearLayout linearLayout5;
        @Bind(R.id.ratingBar)
        RatingBar ratingBar;
        @Bind(R.id.cardCoupon)
        CardView cardCoupon;
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
                .inflate(R.layout.design_searchresult_recycler, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.cardCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(position);
            }
        });

//        History history = historyList.get(position);
//        holder.text_name.setText(history.getName());
//        holder.text_place.setText(history.getPlace());
//        holder.text_time.setText(history.getTime());
//        holder.text_payments.setText(history.getPayments());
    }

    @Override
    public int getItemCount() {
        return 10;
    }


}
