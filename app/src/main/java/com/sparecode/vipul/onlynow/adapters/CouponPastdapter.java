package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.ClientPastData;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 29/12/16.
 */

public class CouponPastdapter extends RecyclerView.Adapter<CouponPastdapter.MyViewHolder> {


//    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }

    private Context context;
    private OnClickListener listener;
    private List<ClientPastData> data;

    public CouponPastdapter(Context context, List<ClientPastData> data, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.data = data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //public TextView text_name,text_place,text_time,text_payments;;
        @Bind(R.id.coupon_image)
        ImageView couponImage;
        @Bind(R.id.coupon_name)
        LatoTextView couponName;
        @Bind(R.id.imageView4)
        ImageView imageView4;
        @Bind(R.id.name)
        LatoTextView name;
        @Bind(R.id.shop_name)
        LatoTextView shopName;
        @Bind(R.id.linearLayout5)
        LinearLayout linearLayout5;
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
                .inflate(R.layout.design_couponlive_recyclerview, parent, false);


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

        Log.e(":::::IMAGE URLS:::::", ":" + data.get(position).getImageURL());
        if (!data.get(position).getImageURL().trim().isEmpty()) {
            Picasso.with(context).load(data.get(position).getImageURL())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .resize(720,200)
                    .into(holder.couponImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.e("IMAGE LOAD SUCC", ":::");
                        }

                        @Override
                        public void onError() {
                            Log.e("IMAGE LOAD ERROR", ":::");
                        }
                    });
        } else {
            Picasso.with(context).load(R.drawable.natural).fit().into(holder.couponImage);

        }


        holder.couponName.setText(data.get(position).getDescription());
        holder.name.setText(data.get(position).getName());
        holder.shopName.setText(data.get(position).getShopName());
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

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
