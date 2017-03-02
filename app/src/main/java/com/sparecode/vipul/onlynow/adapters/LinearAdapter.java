package com.sparecode.vipul.onlynow.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sparecode.vipul.onlynow.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 29/12/16.
 */

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.MyViewHolder> {


//    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }
    int focusPos;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //public TextView text_name,text_place,text_time,text_payments;;
        @Bind(R.id.imageView2)
        ImageView imageView2;
        @Bind(R.id.ImageCategory)
        ImageView ImageCategory;
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
                .inflate(R.layout.design_linear_recyclerview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        History history = historyList.get(position);
//        holder.text_name.setText(history.getName());
//        holder.text_place.setText(history.getPlace());
//        holder.text_time.setText(history.getTime());
//        holder.text_payments.setText(history.getPayments());
        holder.ImageCategorySelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Log.e("CLICKED", "CLICK PERFORMED");*/
                if (!holder.ImageCategory.isSelected())
                holder.ImageCategory.setSelected(true);
                else
                    holder.ImageCategory.setSelected(false);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 10;
    }
//       return historyList.size();
//    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
