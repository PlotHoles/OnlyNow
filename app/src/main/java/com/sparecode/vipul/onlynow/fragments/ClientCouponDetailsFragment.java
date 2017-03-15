package com.sparecode.vipul.onlynow.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.CustomPagerAdapter;
import com.sparecode.vipul.onlynow.model.CLientUpdateCouponWrapper;
import com.sparecode.vipul.onlynow.model.ClientGetCouponImage;
import com.sparecode.vipul.onlynow.model.ClientGetCouponWrapper;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ClientCouponDetailsFragment extends BaseFragment implements ClientCouponDetailBackend.ClientCouponDetailResultProvider, ClientUpdateCouponBackend.ClientUpdateCouponResultProvider {


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
    String coupon_id;
    @Bind(R.id.coupon_pager)
    ViewPager couponPager;
    @Bind(R.id.previous_image)
    ImageView previousImage;
    private View view;
    List<ClientGetCouponImage> data;
    CustomPagerAdapter customPagerAdapter;
    Integer datasize;
    Date date;
    private int mYear, mMonth, mDay;

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

        view = inflater.inflate(R.layout.fragment_client_coupon_details, container, false);
        ButterKnife.bind(this, view);
        data = new ArrayList<>();
        coupon_id = getArguments().getString("key");
        System.out.println("----->coupon" + coupon_id);

        ClientCouponDetailBackend clientCouponDetailBackend = new ClientCouponDetailBackend(coupon_id, getActivity(), this);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        textCoupondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Log.e("fromdate", year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "");
                                textCoupondate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        coupan.setEnabled(false);
        shopname.setEnabled(false);
        coupandetail.setEnabled(false);

        ((BaseActivity) getActivity()).getImgEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coupan.setEnabled(true);
                coupan.requestFocus();
                coupandetail.setEnabled(true);
            }
        });

        customPagerAdapter = new CustomPagerAdapter(getActivity(), data);

        Log.e("sizez", data.size() + "");
        couponPager.setAdapter(customPagerAdapter);
        couponPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("position", position + "");
                // currentposition = position;
                /*if (position == 0)
                {
                    previousImage.setVisibility(View.GONE);
                }
                else
                {
                    previousImage.setVisibility(View.VISIBLE);
                }*/
                gone(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couponPager.setCurrentItem(getItem(+1), true);
            }
        });

        previousImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couponPager.setCurrentItem(getpreviousItem(+1), true);
            }
        });

        return view;
    }

    public void setAdapter(List<ClientGetCouponImage> dataList) {
        this.data.addAll(dataList);
        customPagerAdapter.notifyDataSetChanged();
        datasize = data.size();
        if (datasize == 0) {
            nextImage.setVisibility(View.GONE);
            previousImage.setVisibility(View.GONE);
        } else if (datasize == 1) {
            nextImage.setVisibility(View.GONE);
            previousImage.setVisibility(View.GONE);
        }
        Log.e("sizeeeeeee", datasize + "");

    }

    private int getItem(int i) {
        return couponPager.getCurrentItem() + i;
    }

    private int getpreviousItem(int i) {
        return couponPager.getCurrentItem() - i;
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

    private void gone(int currentposition) {
        if (currentposition == 0) {
            previousImage.setVisibility(View.GONE);
        } else if (datasize == (currentposition + 1)) {
            nextImage.setVisibility(View.GONE);
        } else if (datasize == 0) {
            nextImage.setVisibility(View.GONE);
            previousImage.setVisibility(View.GONE);
        } else {
            previousImage.setVisibility(View.VISIBLE);
            nextImage.setVisibility(View.VISIBLE);
        }
        Log.e("sizeeeeeeegone", datasize + "");
        Log.e("currrent ", currentposition + "");
    }

    @Override
    public void onSuccessfullLogin(ClientGetCouponWrapper clientGetCouponWrapper) {
        setAdapter(clientGetCouponWrapper.getData().getImages());
        textCouponname.setText(clientGetCouponWrapper.getData().getCoupon().getName());
        textCoupontype.setText(clientGetCouponWrapper.getData().getCoupon().getCatName());
        textCouponplace.setText(clientGetCouponWrapper.getData().getCoupon().getArea());
        coupan.setText(clientGetCouponWrapper.getData().getCoupon().getInstruction());
        shopname.setText(clientGetCouponWrapper.getData().getCoupon().getShopName());
        coupandetail.setText(clientGetCouponWrapper.getData().getCoupon().getDescription());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm");
        try {

            date = sdf.parse(clientGetCouponWrapper.getData().getCoupon().getEndDate());
            Log.e("date",""+sdf.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        textCoupondate.setText(sdf.format(date));
    }

    @Override
    public void onSuuccessfullLogin(CLientUpdateCouponWrapper cLientUpdateCouponWrapper) {
        Snackbar.make(view, cLientUpdateCouponWrapper.getMessage(), Snackbar.LENGTH_SHORT).show();
        coupan.setEnabled(false);
        shopname.setEnabled(false);
        coupandetail.setEnabled(false);
    }

    @Override
    public void onLoginFailure(String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.save)
    public void onClick() {

        ClientUpdateCouponBackend clientUpdateCouponBackend = new ClientUpdateCouponBackend(getActivity(),coupon_id,coupan.getText().toString().trim(),coupandetail.getText().toString().trim(),textCoupondate.getText().toString(),this);
    }
}
