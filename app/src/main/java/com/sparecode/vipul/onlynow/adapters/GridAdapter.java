package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.LocationListData;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 29/12/16.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> {

    List<LocationListData> data;
    Context mContext;
    GetSelectedLocation getSelectedLocation;

    public GridAdapter(List<LocationListData> data, Context mContext, GetSelectedLocation getSelectedLocation) {
        this.data = data;
        this.mContext = mContext;
        this.getSelectedLocation = getSelectedLocation;
    }

//    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //public TextView text_name,text_place,text_time,text_payments;;
        @Bind(R.id.imageactiveArea)
        ImageView FrameactiveArea;
        @Bind(R.id.linearActiveArea)
        FrameLayout linearActiveArea;
        @Bind(R.id.linearactive)
        LinearLayout linearactive;
        @Bind(R.id.imageView)
        ImageView imageView;
        @Bind(R.id.textView2)
        TextView textView2;
        @Bind(R.id.ImageCategorySelected)
        LinearLayout ImageCategorySelected;

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
                .inflate(R.layout.design_grid_recyclerview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        History history = historyList.get(position);
//        holder.text_name.setText(history.getName());
//        holder.text_place.setText(history.getPlace());
//        holder.text_time.setText(history.getTime());
//        holder.text_payments.setText(history.getPayments());
        Picasso.with(mContext).load(data.get(position).getImageURL()).resize(720, 720).placeholder(R.drawable.natural).into(holder.imageView);
        holder.textView2.setText(data.get(position).getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Log.e("CLICKED", "CLICK PERFORMED");*/
                if (!holder.FrameactiveArea.isSelected())
                    holder.FrameactiveArea.setSelected(true);
                else
                    holder.FrameactiveArea.setSelected(false);

                getSelectedLocation.getSelectedLocation(data.get(position), holder.FrameactiveArea.isSelected());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

//       return historyList.size();
//    }

    public interface GetSelectedLocation {
        void getSelectedLocation(LocationListData locationListData, boolean flag);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
