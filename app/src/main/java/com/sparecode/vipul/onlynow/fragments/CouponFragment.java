package com.sparecode.vipul.onlynow.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.model.CouponDetailWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.facebook.FacebookSdk.getApplicationContext;

@SuppressLint("ValidFragment")
public class CouponFragment extends Fragment implements DetailFragemenBackend.CouponDetailProvider {

    String couponId;
    @Bind(R.id.imageView9)
    ImageView imageView9;
    @Bind(R.id.imageView11)
    ImageView imageView11;
    @Bind(R.id.ratingBar)
    RatingBar ratingBar;
    @Bind(R.id.coupon_desc)
    LatoTextView couponDesc;
    @Bind(R.id.text_dateto)
    LatoTextView textDateto;
    @Bind(R.id.textCat)
    LatoTextView textCat;
    @Bind(R.id.text_datefrom)
    LatoTextView textDatefrom;
    @Bind(R.id.textArea)
    LatoTextView textArea;
    @Bind(R.id.textView37)
    LatoTextView textView37;
    @Bind(R.id.textCouponDesc)
    LatoTextView textCouponDesc;
    @Bind(R.id.textView39)
    LatoTextView textView39;
    @Bind(R.id.textShopName)
    LatoTextView textShopName;
    @Bind(R.id.textView42)
    LatoTextView textView42;
    @Bind(R.id.textCouponName)
    LatoTextView textCouponName;
    @Bind(R.id.textCouponText)
    LatoTextView textCouponText;
    @Bind(R.id.text_rating)
    LatoTextView textRating;
    @Bind(R.id.othercoupon_linear)
    LinearLayout othercouponLinear;


    public CouponFragment(String couponId) {
        this.couponId = couponId;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);
        getUser();

        ButterKnife.bind(this, view);
        return view;
    }

    DetailFragemenBackend detailFragemenBackend;
    SignupWrapper signupWrapper;

    private void getUser() {
        try {
            signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);

        } catch (JsonSyntaxException e) {
            ((BaseActivity) getActivity()).openSplashPage();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detailFragemenBackend = new DetailFragemenBackend(couponId, this, getActivity(), signupWrapper.getData().getId());
    }

    @Override
    public void onCouponSuccess(CouponDetailWrapper couponDetailWrapper) {
        couponDesc.setText(couponDetailWrapper.getData().getDescription());
        textDateto.setText(couponDetailWrapper.getData().getDate());
        textDatefrom.setText(couponDetailWrapper.getData().getEndDate());
        textArea.setText(couponDetailWrapper.getData().getArea());
        textCat.setText(couponDetailWrapper.getData().getCategory());
        textShopName.setText(couponDetailWrapper.getData().getShopName() + "\n" + couponDetailWrapper.getData().getArea() + "\n" + couponDetailWrapper.getData().getPhone() + "\n" + couponDetailWrapper.getData().getZipCode() + "\n" + couponDetailWrapper.getData().getPrefecture() + "\n" + couponDetailWrapper.getData().getCity() + "\n" + couponDetailWrapper.getData().getStreet() + "\n" + couponDetailWrapper.getData().getBuildName() + "\n" + couponDetailWrapper.getData().getWebsite());
        textCouponDesc.setText(couponDetailWrapper.getData().getDescription());
        textCouponName.setText(couponDetailWrapper.getData().getName());
        textCouponText.setText(couponDetailWrapper.getData().getInstruction());
        String rating = couponDetailWrapper.getData().getRating();
        System.out.println("----->rating" + rating);
        if (rating == "") {
            ratingBar.setRating(0);
        } else {
            ratingBar.setRating(Float.parseFloat(rating));
        }
        textRating.setText(couponDetailWrapper.getData().getRating());
        //textOtherCouponName.setText(couponDetailWrapper.getData().getOtherCoupons().get());
        for (int i=0;i<couponDetailWrapper.getData().getOtherCoupons().size();i++)
        {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(R.layout.design_othercoupon, null);
            othercouponLinear.addView(v, i, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            LatoTextView text_other_coupon_name = (LatoTextView)v.findViewById(R.id.text_other_coupon_name);
            LatoTextView textValid = (LatoTextView)v.findViewById(R.id.textValid);

            text_other_coupon_name.setText(couponDetailWrapper.getData().getOtherCoupons().get(i).getName());
            textValid.setText(couponDetailWrapper.getData().getOtherCoupons().get(i).getEndDate());
        }

    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
