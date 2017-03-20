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
import com.sparecode.vipul.onlynow.model.MyListReviewedWrapper;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 29/12/16.
 */

public class CouponReviewedAdapter extends RecyclerView.Adapter<CouponReviewedAdapter.MyViewHolder> {


    Context context;
    private MyListReviewedWrapper myListReviewedWrapper;
    private OnClickListener onClickListener;

    public CouponReviewedAdapter(Context context, MyListReviewedWrapper myListReviewedWrapper, OnClickListener onClickListener) {
        this.context = context;
        this.myListReviewedWrapper = myListReviewedWrapper;
        this.onClickListener = onClickListener;
    }

    //    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //public TextView text_name,text_place,text_time,text_payments;;
        @Bind(R.id.text_date)
        LatoTextView textDate;
        @Bind(R.id.coupon_image)
        ImageView couponImage;
        @Bind(R.id.coupon_desc)
        LatoTextView couponDesc;
        @Bind(R.id.imageView4)
        ImageView imageView4;
        @Bind(R.id.coupon_shopname)
        LatoTextView couponShopname;
        @Bind(R.id.coupon_area)
        LatoTextView couponArea;
        @Bind(R.id.linearLayout5)
        LinearLayout linearLayout5;
        @Bind(R.id.ratingbar)
        RatingBar ratingbar;
        @Bind(R.id.linearrating)
        LinearLayout linearrating;
        @Bind(R.id.txtCouponTimer)
        LatoTextView txtCouponTimer;
        @Bind(R.id.txtCouponValidTill)
        LatoTextView txtCouponValidTill;
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
                .inflate(R.layout.design_list_recyclerviews, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

//        History history = historyList.get(position);
//        holder.text_name.setText(history.getName());
//        holder.text_place.setText(history.getPlace());
//        holder.text_time.setText(history.getTime());
//        holder.text_payments.setText(history.getPayments());
        /*holder.textView12.setText(myListSavedDataList.get(position).getDate());
        holder.txtCouponTimer.setText(getLeftTimeDuration(myListSavedDataList.get(position).getDate(), myListSavedDataList.get(position).getEndDate()));
        holder.txtCouponValidTill.setText(couponDetailWrapper.getData().getDate());*/
        holder.couponDesc.setVisibility(View.GONE);
        holder.textDate.setText(myListReviewedWrapper.getData().get(position).getTitle());
        holder.couponArea.setText(myListReviewedWrapper.getData().get(position).getArea());
        holder.couponShopname.setText(myListReviewedWrapper.getData().get(position).getFname() +" "+myListReviewedWrapper.getData().get(position).getLname());
        holder.txtCouponTimer.setVisibility(View.GONE);
        holder.txtCouponValidTill.setVisibility(View.GONE);
        String rating = myListReviewedWrapper.getData().get(position).getRating();
        System.out.println("----->rating"+rating);
        if (rating == "")
        {
            holder.ratingbar.setRating(0);
        }
        else
        {
            holder.ratingbar.setRating(Float.parseFloat(rating));
        }
        if (!myListReviewedWrapper.getData().get(position).getImageURL().trim().isEmpty()) {
            Picasso.with(context).load(myListReviewedWrapper.getData().get(position).getImageURL()).resize(720, 200).into(holder.couponImage);
        }
    }

    @Override
    public int getItemCount() {
        return myListReviewedWrapper.getData().size();
    }
//       return historyList.size();
//    }

}
