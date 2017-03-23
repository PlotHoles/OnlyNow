package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.CouponWrapper;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 29/12/16.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

//    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }

    private Context context;
    private OnClickListener listener;
    private CouponWrapper couponWrapper;

    public CardAdapter(Context context, CouponWrapper couponWrapper, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.couponWrapper = couponWrapper;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txtCouponName)
        LatoTextView txtCouponName;
        @Bind(R.id.txtCouponShopName)
        LatoTextView txtCouponShopName;
        @Bind(R.id.txtCouponAreaName)
        LatoTextView txtCouponAreaName;
        @Bind(R.id.txtCouponTimer)
        LatoTextView txtCouponTimer;
        @Bind(R.id.txtCouponValidTill)
        LatoTextView txtCouponValidTill;
        //public TextView text_name,text_place,text_time,text_payments;
        @Bind(R.id.imageView3)
        ImageView imageView3;
        @Bind(R.id.imageView4)
        ImageView imageView4;
        @Bind(R.id.linearLayout5)
        LinearLayout linearLayout5;
        @Bind(R.id.linearrating)
        LinearLayout linearrating;
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
                .inflate(R.layout.design_card_recyclerview, parent, false);


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
        if (!couponWrapper.getData().get(position).getImageURL().trim().isEmpty()) {
            Picasso.with(context).load(couponWrapper.getData().get(position).getImageURL()).resize(720, 200).into(holder.imageView3);
        }
        holder.txtCouponName.setText(couponWrapper.getData().get(position).getName());
        holder.txtCouponShopName.setText(couponWrapper.getData().get(position).getShopName());
        holder.txtCouponAreaName.setText(couponWrapper.getData().get(position).getArea());
        holder.txtCouponTimer.setText(getLeftTimeDuration(couponWrapper.getData().get(position).getDate(), couponWrapper.getData().get(position).getEndDate()));


        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "h:mm a dd-MM-yyyy ";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(couponWrapper.getData().get(position).getEndDate());
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.txtCouponValidTill.setText(str);
        //getLeftTimeDuration(couponWrapper.getData().get(position).getDate(), couponWrapper.getData().get(position).getEndDate());
        //holder.txtCouponTimer.setText(couponWrapper.getData().get(position).);


//        History history = historyList.get(position);
//        holder.text_name.setText(history.getName());
//        holder.text_place.setText(history.getPlace());
//        holder.text_time.setText(history.getTime());
//        holder.text_payments.setText(history.getPayments());

    }

    @Override
    public int getItemCount() {
        return couponWrapper.getData().size();
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
