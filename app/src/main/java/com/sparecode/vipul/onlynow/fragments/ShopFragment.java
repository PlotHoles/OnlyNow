package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShopFragment extends Fragment {


    @Bind(R.id.coupon_image)
    ImageView couponImage;
    @Bind(R.id.coupon_next_image)
    ImageView couponNextImage;
    @Bind(R.id.coupon_rating)
    LatoTextView couponRating;
    @Bind(R.id.ratingBar)
    RatingBar ratingBar;
    @Bind(R.id.text_coupon_desc)
    LatoTextView textCouponDesc;
    @Bind(R.id.imageButton_coupon_like)
    ImageButton imageButtonCouponLike;
    @Bind(R.id.text_coupon_category)
    LatoTextView textCouponCategory;
    @Bind(R.id.text_coupon_area)
    LatoTextView textCouponArea;
    @Bind(R.id.text_phone)
    LatoTextView textPhone;
    @Bind(R.id.text_coupon_web)
    LatoTextView textCouponWeb;
    @Bind(R.id.text_shopname)
    LatoTextView textShopname;
    @Bind(R.id.imageView21)
    ImageView imageView21;
    @Bind(R.id.text_adress)
    LatoTextView textAdress;

    public ShopFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ShopFragment newInstance(String text) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putString("arg", text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.imageButton_coupon_like)
    void onFavSelected() {
        if (!imageButtonCouponLike.isSelected()) {
            imageButtonCouponLike.setSelected(true);
        } else {
            imageButtonCouponLike.setSelected(false);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
