package com.sparecode.vipul.onlynow.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;

/**
 * Created by vipul on 29/12/16.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

//    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //public TextView text_name,text_place,text_time,text_payments;;

        public MyViewHolder(View view) {
            super(view);
//            text_name = (TextView) view.findViewById(R.id.text_name);
//            text_place = (TextView) view.findViewById(R.id.text_place);
//            text_time = (TextView) view.findViewById(R.id.text_time);
//            text_payments = (TextView) view.findViewById(R.id.text_payments);
        }
    }
    
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.design_notice_recyclerview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

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
//       return historyList.size();
//    }

}
