package com.sparecode.vipul.onlynow.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.ClientAnalyticsWrapper;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ClientAnalyticsClientkFragment extends BaseFragment implements ClientAnalyticsClientBackend.ClientAnalyticsResultProvider {

    @Bind(R.id.viewright)
    View viewright;
    @Bind(R.id.viewcenter)
    View viewcenter;
    @Bind(R.id.viewleft)
    View viewleft;
    @Bind(R.id.text_datepickerlbl)
    LatoTextView textDatepickerlbl;
    @Bind(R.id.text_datefrom)
    LatoTextView textDatefrom;
    @Bind(R.id.view_bottom_date)
    View viewBottomDate;
    @Bind(R.id.linearLayout16)
    LinearLayout linearLayout16;
    @Bind(R.id.text_timepickerlbl)
    LatoTextView textTimepickerlbl;
    @Bind(R.id.text_dateto)
    LatoTextView textDateto;
    @Bind(R.id.linearLayout5)
    LinearLayout linearLayout5;
    @Bind(R.id.coupon_image)
    ImageView couponImage;
    @Bind(R.id.text_coupon)
    LatoTextView textCoupon;
    @Bind(R.id.text_client_show)
    LatoTextView textClientShow;
    @Bind(R.id.text_client_like)
    LatoTextView textClientLike;
    @Bind(R.id.linearclient)
    LinearLayout linearclient;
    @Bind(R.id.nodata)
    LatoTextView nodata;

    private int mYear, mMonth, mDay;
    private View view;
    String fromdate;
    String todate;
    SimpleDateFormat dfDate;
    ClientAnalyticsClientBackend clientAnalyticsClientBackend;

    public ClientAnalyticsClientkFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ClientAnalyticsClientkFragment newInstance(String text) {
        ClientAnalyticsClientkFragment fragment = new ClientAnalyticsClientkFragment();
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
        view = inflater.inflate(R.layout.fragment_client_analytics_clientk, container, false);
        ButterKnife.bind(this, view);


        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        textDatefrom.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);
        fromdate = mYear + "-" + (mMonth + 1) + "-" + mDay;
        System.out.println("------->fromdate" + fromdate);
        textDateto.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);
        todate = mYear + "-" + (mMonth + 1) + "-" + mDay;
        System.out.println("------->todate" + todate);
        textDatefrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Log.e("fromdate", year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "");
                                textDatefrom.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                fromdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                Log.e("fromdate", fromdate);
                                CheckDates(todate, fromdate);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                System.out.println("----->date" + textDatefrom.getText().toString());
            }
        });

        textDateto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                textDateto.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                todate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                System.out.println("------->todate" + todate);
                                CheckDates(todate, fromdate);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        dfDate = new SimpleDateFormat("yyyy-MM-dd");
        //CheckDates(fromdate,todate);

        return view;
    }

    public boolean CheckDates(String fromdate, String todate) {
        boolean b = false;
        //getUserId()
        Log.e("--->fromdate", fromdate);
        Log.e("--->todate", todate);
        try {
            if (dfDate.parse(fromdate).after(dfDate.parse(todate))) {
                b = true;

                clientAnalyticsClientBackend = new ClientAnalyticsClientBackend(getActivity(), getUserId(), todate, fromdate, this);
            } else {
                b = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("DATE")
                        .setMessage("Please Select Proper Date")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("------>boolean", b + "");
        return b;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setToolbarForFragment() {

    }

    @Override
    public void onSuccessfullLogin(ClientAnalyticsWrapper clientAnalyticsWrapper) {
        linearclient.setVisibility(View.VISIBLE);
        nodata.setVisibility(View.GONE);
        textCoupon.setText(clientAnalyticsWrapper.getData().getClient().getDetails());
        textClientShow.setText(clientAnalyticsWrapper.getData().getClient().getTotalViews());
        textClientLike.setText(clientAnalyticsWrapper.getData().getClient().getTotalLikes());

        if (clientAnalyticsWrapper.getData().getClient().getImageURL() != null &&
                (!clientAnalyticsWrapper.getData().getClient().getImageURL().equals("")))
            Picasso.with(getActivity())
                    .load(clientAnalyticsWrapper.getData().getClient().getImageURL())
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_placeholder)
                    .resize(720, 200)
                    .into(couponImage);
    }

    @Override
    public void onLoginfailure(String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
        nodata.setVisibility(View.VISIBLE);
        linearclient.setVisibility(View.GONE);
    }
}
