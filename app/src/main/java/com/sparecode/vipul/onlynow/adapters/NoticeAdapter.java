package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.NoticeData;
import com.sparecode.vipul.onlynow.view.CircleImageView;
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

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

    //    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }
    List<NoticeData> data;
    Context mContext;


    public NoticeAdapter(List<NoticeData> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //public TextView text_name,text_place,text_time,text_payments;;
        @Bind(R.id.linearLayout6)
        LinearLayout linearLayout6;
        @Bind(R.id.imageView5)
        ImageView imageView5;
        @Bind(R.id.textView11)
        LatoTextView textView11;
        @Bind(R.id.textView10)
        LatoTextView textView10;
        @Bind(R.id.textView15)
        LatoTextView textView15;
        @Bind(R.id.notificationImg)
        CircleImageView notificationImg;


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

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_notice_recyclerview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.textView11.setText(data.get(position).getName());
        holder.textView10.setText(data.get(position).getText());
        if (!data.get(position).getImageURL().trim().isEmpty()) {
            Picasso.with(mContext).load(data.get(position).getImageURL()).resize(250, 250).into(holder.notificationImg);
        }
        try {
            long now = System.currentTimeMillis();
            String datetime1 = "" + data.get(position).getDate();//2017-03-08 17:13:48
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date convertedDate = dateFormat.parse(datetime1);

            CharSequence relavetime1 = DateUtils.getRelativeTimeSpanString(
                    convertedDate.getTime(),
                    now,
                    DateUtils.SECOND_IN_MILLIS);

            //xt_time.append(relavetime1 + "\n\n");
            holder.textView15.setText(relavetime1);

            //System.out.println(relavetime1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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


//       return historyList.size();
//    }

}
