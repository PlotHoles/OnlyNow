package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.fragments.MapData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vipul on 20/3/17.
 */

public class CustomMapPagerAdapter extends PagerAdapter {


    private Context context;
    private List<MapData> data;
    private LayoutInflater layoutInflater;

    public CustomMapPagerAdapter(Context context, List<MapData> data) {
        this.context = context;
        this.data = data;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = layoutInflater.inflate(R.layout.design_map_viewpager, container, false);

        ImageView couponImage = (ImageView)itemView.findViewById(R.id.coupon_image);
        TextView coupon_name = (TextView)itemView.findViewById(R.id.text_coupon_name);
        TextView shop_name = (TextView)itemView.findViewById(R.id.text_shop_name);
        TextView coupon_area = (TextView)itemView.findViewById(R.id.text_coupon_area);

        Log.e("------>image",data.get(position).getIconURL());
        coupon_name.setText(data.get(position).getName());
        shop_name.setText(data.get(position).getShopName());
        coupon_area.setText(data.get(position).getArea());

        if (data.get(position).getImageURL() != null &&
                (!data.get(position).getImageURL().equals("")))
        {
            Picasso.with(context)
                    .load(data.get(position).getIconURL())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .resize(720, 200)
                    .into(couponImage);
        }

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
