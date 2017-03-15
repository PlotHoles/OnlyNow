package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.CategoryData;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 29/12/16.
 */

public class LinearsAdapter extends RecyclerView.Adapter<LinearsAdapter.MyViewHolder> {


    //    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }
    List<CategoryData> categoryDatas;
    Context mContext;
    OnClickListener onClickListener;
    int focusPos;
    private int selectedPosition = -1;
    private int previousSelectedPos = 0;

    public LinearsAdapter(List<CategoryData> categoryDatas, Context mContext, OnClickListener onClickListener) {
        this.categoryDatas = categoryDatas;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //public TextView text_name,text_place,text_time,text_payments;;
        @Bind(R.id.imageView2)
        ImageView imageView2;
        @Bind(R.id.ImageCategory)
        ImageView ImageCategory;
        @Bind(R.id.ImageCategorySelected)
        LinearLayout ImageCategorySelected;
        @Bind(R.id.txtCategoryName)
        TextView txtCategoryName;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("--->Adapter", getAdapterPosition() + "");
                    Log.e("--->layout", getLayoutPosition() + "");
                    Log.e("---->pos", getPosition() + "");
                    Log.e("---->old", getOldPosition() + "");
                }
            });
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
        Picasso.with(mContext).load(categoryDatas.get(position).getImageURL()).placeholder(R.drawable.placeholder).resize(720, 200).into(holder.imageView2);
        holder.txtCategoryName.setText(categoryDatas.get(position).getName());


        //holder.ImageCategorySelected.setOnClickListener(onStateChangedListener(holder.ImageCategorySelected, position));

        holder.ImageCategorySelected.setTag(position);

        if (position==selectedPosition)
        {
            holder.ImageCategory.setSelected(true);
        }
        else
        {
            holder.ImageCategory.setSelected(false);
        }

        holder.ImageCategorySelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.e("CLICKED", "CLICK PERFORMED");

                //selectedPosition = position;

               // holder.ImageCategorySelected.setSelected(true);
/*                if (position != previousSelectedPos) {
                    previousSelectedPos = position;
                    holder.ImageCategorySelected = (LinearLayout) holder.ImageCategorySelected.findViewWithTag(position);
                    holder.ImageCategorySelected.setSelected(true);
                } else {
                    holder.ImageCategorySelected.setSelected(false);
                }*/
                Log.e("hihello", position + "");
                onClickListener.onItemClicked(position);

                if (!holder.ImageCategory.isSelected()){
                    holder.ImageCategory.setSelected(true);
                    selectedPosition = position;
                }
                else
                {
                    selectedPosition = -1;
                }
                notifyDataSetChanged();
               /* for (int i = selectedPosition; i <= categoryDatas.size(); i++) {
                    if (i == position) {
                        holder.ImageCategory.setSelected(true);
                    } else {
                        holder.ImageCategory.setSelected(false);
                    }
                }*/
                /*if (position == selectedPosition) {
                    holder.ImageCategory.setSelected(true);

                } else {
                    holder.ImageCategory.setSelected(false);
                }*/
                /*if (!holder.ImageCategory.isSelected())
                {
                    selectedPosition = position;
                    holder.ImageCategory.setSelected(true);
                }
                else {
                    holder.ImageCategory.setSelected(false);
                }*/
                /*if (!holder.ImageCategory.isSelected())
                {
                    holder.ImageCategory.setSelected(true);
                    selectedPosition = position;
                   /// Log.e("---->","hi");
                }

                else
                    holder.ImageCategory.setSelected(false);*/

            }
        });
        /*if (position == selectedPosition) {
            holder.ImageCategory.setSelected(true);

        } else {
            holder.ImageCategory.setSelected(false);
        }*/

    }
    /*private View.OnClickListener onStateChangedListener(final LinearLayout imageView, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.isSelected()) {
                    selectedPosition = position;
                } else {
                    selectedPosition = -1;
                }
                notifyDataSetChanged();
            }
        };

    }*/

    @Override
    public int getItemCount() {
        return categoryDatas.size();
    }
//       return historyList.size();
//    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
