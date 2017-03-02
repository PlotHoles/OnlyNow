package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClientCouponDetailsFragment extends BaseFragment {


    @Bind(R.id.coupon_image)
    ImageView couponImage;
    @Bind(R.id.next_image)
    ImageView nextImage;
    @Bind(R.id.text_couponname)
    LatoTextView textCouponname;
    @Bind(R.id.text_coupontime)
    LatoTextView textCoupontime;
    @Bind(R.id.text_coupontype)
    LatoTextView textCoupontype;
    @Bind(R.id.text_coupondate)
    LatoTextView textCoupondate;
    @Bind(R.id.text_couponplace)
    LatoTextView textCouponplace;
    @Bind(R.id.coupan)
    LatoEditText coupan;
    @Bind(R.id.shopname)
    LatoEditText shopname;
    @Bind(R.id.coupandetail)
    LatoEditText coupandetail;
    @Bind(R.id.save)
    LatoButton save;
    @Bind(R.id.linear)
    LinearLayout linear;

    public ClientCouponDetailsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClientCouponDetailsFragment newInstance(String text) {
        ClientCouponDetailsFragment fragment = new ClientCouponDetailsFragment();
        Bundle args = new Bundle();
        args.putString("msg", text);
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
        View view = inflater.inflate(R.layout.fragment_client_coupon_details, container, false);
        ButterKnife.bind(this, view);

        coupan.setEnabled(false);
        shopname.setEnabled(false);
        coupandetail.setEnabled(false);

        ((BaseActivity) getActivity()).getImgEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coupan.setEnabled(true);
                coupan.requestFocus();
                shopname.setEnabled(true);
                coupandetail.setEnabled(true);
            }
        });
        return view;
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.coupon_details));
        ((BaseActivity) getActivity()).getImgEdit().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgAdd().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getFab().setVisibility(View.GONE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
