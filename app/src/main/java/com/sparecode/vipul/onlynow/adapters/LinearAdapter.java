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
import com.sparecode.vipul.onlynow.model.SelectCategoryData;
import com.squareup.picasso.Picasso;

import java.util.List;

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
    List<CategoryData> categoryDatas;
    Context mContext;
    GetSelectedCategory getSelectedCategory;
    List<SelectCategoryData> data;

    public LinearAdapter(List<CategoryData> categoryDatas, Context mContext, GetSelectedCategory getSelectedCategory) {
        this.categoryDatas = categoryDatas;
        this.mContext = mContext;
        this.getSelectedCategory = getSelectedCategory;
    }

    public LinearAdapter(List<SelectCategoryData> data, Context mContext, GetSelectedCategory getSelectedCategory, boolean flag) {
        this.data = data;
        this.mContext = mContext;
        this.getSelectedCategory = getSelectedCategory;
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
            ;
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

        if (categoryDatas != null) {
            Picasso.with(mContext).load(categoryDatas.get(position).getImageURL()).placeholder(R.drawable.natural).resize(720, 200).into(holder.imageView2);
            holder.txtCategoryName.setText(categoryDatas.get(position).getName());
            holder.ImageCategorySelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("CLICKED", "CLICK PERFORMED");
                    if (!holder.ImageCategory.isSelected())
                        holder.ImageCategory.setSelected(true);
                    else
                        holder.ImageCategory.setSelected(false);

                    getSelectedCategory.getSelectedCategory(categoryDatas.get(position), holder.ImageCategory.isSelected());
                }
            });
        } else {
            Picasso.with(mContext).load(data.get(position).getImageURL()).placeholder(R.drawable.natural).resize(720, 200).into(holder.imageView2);
            holder.txtCategoryName.setText(data.get(position).getName());

            if (data.get(position).getIsFav() == 1) {
                holder.ImageCategory.setSelected(true);
                Log.e("HOLDER::", "" + holder.ImageCategory.isSelected());
            } else {
                holder.ImageCategory.setSelected(false);
            }
            holder.ImageCategorySelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!holder.ImageCategory.isSelected())
                        holder.ImageCategory.setSelected(true);
                    else
                        holder.ImageCategory.setSelected(false);
                    getSelectedCategory.getSelectedCategory(data.get(position), holder.ImageCategory.isSelected());

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (categoryDatas != null) {
            return categoryDatas.size();
        } else {
            Log.e("::", "size" + data.size());
            return data.size();
        }
    }

    public interface GetSelectedCategory {
        void getSelectedCategory(CategoryData categoryData, boolean flag);

        void getSelectedCategory(SelectCategoryData selectCategoryData, boolean flag);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
