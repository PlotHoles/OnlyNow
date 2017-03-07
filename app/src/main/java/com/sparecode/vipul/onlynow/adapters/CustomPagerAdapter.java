package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.ClientGetCouponImage;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vipul on 6/3/17.
 */

public class CustomPagerAdapter extends PagerAdapter {


    private Context context;
    private List<ClientGetCouponImage> data;
    private LayoutInflater layoutInflater;

    public CustomPagerAdapter(Context context) {
        this.context = context;

    }

    public CustomPagerAdapter(Context context, List<ClientGetCouponImage> data) {
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
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = layoutInflater.inflate(R.layout.design_viewpager, container, false);

        ImageView couponImage = (ImageView)itemView.findViewById(R.id.coupon_image);
        Log.e("------>image",data.get(position).getImageURL());
        if (data.get(position).getImageURL() != null &&
                (!data.get(position).getImageURL().equals("")))
        {
            Picasso.with(context)
                    .load(data.get(position).getImageURL())
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
        container.removeView((FrameLayout)object);
    }
}
