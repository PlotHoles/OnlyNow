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
import com.sparecode.vipul.onlynow.model.SearchResultData;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private List<SearchResultData> data;

    public SearchResultAdapter(Context context, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public SearchResultAdapter(Context context, List<SearchResultData> data, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.data = data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.coupon_image)
        ImageView couponImage;
        @Bind(R.id.coupon_desc)
        LatoTextView couponDesc;
        @Bind(R.id.imageView4)
        ImageView imageView4;
        @Bind(R.id.coupon_owner_name)
        LatoTextView couponOwnerName;
        @Bind(R.id.coupon_area)
        LatoTextView couponArea;
        @Bind(R.id.linearLayout5)
        LinearLayout linearLayout5;
        @Bind(R.id.rating)
        LatoTextView rating;
        @Bind(R.id.ratingBar)
        RatingBar ratingBar;
        @Bind(R.id.time)
        LatoTextView time;
        @Bind(R.id.days)
        LatoTextView days;
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

        holder.couponDesc.setText(data.get(position).getDescription());
        holder.couponOwnerName.setText(data.get(position).getShopName());
        holder.couponArea.setText(data.get(position).getArea());
        if (data.get(position).getImageURL() != null &&
                (!data.get(position).getImageURL().equals(""))){

            Picasso.with(context)
                    .load(data.get(position).getImageURL())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .resize(720,200)
                    .into(holder.couponImage);
        }
        else {
            Picasso.with(context).load(R.drawable.placeholder).fit().into(holder.couponImage);
        }
        holder.time.setText(getLeftTimeDuration(data.get(position).getDate(), data.get(position).getEndDate()));
        holder.days.setText(data.get(position).getDate());
        //holder.couponArea.setText(data.get(position).getArea());
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

    private String getLeftTimeDuration(String startDate, String endDate) {


//HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(startDate);
            d2 = format.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


//in milliseconds
        long diff = d2.getTime() - d1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        System.out.print(diffDays + " days, ");
        System.out.print(diffHours + " hours, ");
        System.out.print(diffMinutes + " minutes, ");
        System.out.print(diffSeconds + " seconds.");

        String retValue = "" + diffDays + "days" + diffHours + "hours" + diffMinutes + "mins";

        return retValue.replace("-", "");
    }

}
