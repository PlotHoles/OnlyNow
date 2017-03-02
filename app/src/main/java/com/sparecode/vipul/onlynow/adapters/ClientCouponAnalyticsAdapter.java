package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.ClientsAnalyticsCoupon;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 29/12/16.
 */

public class ClientCouponAnalyticsAdapter extends RecyclerView.Adapter<ClientCouponAnalyticsAdapter.MyViewHolder> {



//    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }

    private Context context;
    private OnClickListener listener;
    private List<ClientsAnalyticsCoupon> data;

    public ClientCouponAnalyticsAdapter(Context context, List<ClientsAnalyticsCoupon> data, OnClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.data = data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //public TextView text_name,text_place,text_time,text_payments;;
        @Bind(R.id.coupon_image)
        ImageView couponImage;
        @Bind(R.id.text_coupon_name)
        LatoTextView textCouponName;
        @Bind(R.id.coupon_publishdate)
        LatoTextView couponPublishdate;
        @Bind(R.id.coupon_view)
        LatoTextView couponView;
        @Bind(R.id.coupon_save)
        LatoTextView couponSave;

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
                .inflate(R.layout.design_client_analytics_coupon, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (data.get(position).getImageURL() != null &&
                (!data.get(position).getImageURL().equals("")))
            Picasso.with(context)
                    .load(data.get(position).getImageURL())
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_placeholder)
                    .resize(720,200)
                    .into(holder.couponImage);
        holder.textCouponName.setText(data.get(position).getName());
        holder.couponPublishdate.setText(data.get(position).getDate());
        holder.couponView.setText(data.get(position).getTotalViews());
        holder.couponSave.setText(data.get(position).getTotalSaved());
//        History history = historyList.get(position);
//        holder.text_name.setText(history.getName());
//        holder.text_place.setText(history.getPlace());
//        holder.text_time.setText(history.getTime());
//        holder.text_payments.setText(history.getPayments());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
