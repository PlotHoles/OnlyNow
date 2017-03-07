package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.ClientReview;
import com.sparecode.vipul.onlynow.view.OnClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 29/12/16.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {


//    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }

    private Context context;
    private OnClickListener listener;
    private List<ClientReview> data;

    public ReviewAdapter(Context context, List<ClientReview> data, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.data = data;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.username)
        TextView username;
        @Bind(R.id.ratingBar)
        RatingBar ratingBar;
        @Bind(R.id.text_time)
        TextView textTime;
        @Bind(R.id.review)
        TextView review;

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
                .inflate(R.layout.design_review_recyclerview, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.username.setText(data.get(position).getUsername());
        holder.ratingBar.setRating(Float.parseFloat(data.get(position).getStar()));
        holder.textTime.setText(data.get(position).getDate());
        holder.review.setText(data.get(position).getReview());

        /*String date = data.get(position).getDate();

        Log.e("----->datereview",date);
        String replace = date.replace("`"," ");
        holder.textTime.setText(replace);
        Log.e("replacestring",replace);*/
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
