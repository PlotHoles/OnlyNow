package com.sparecode.vipul.onlynow.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.ClientCouponAnalyticsAdapter;
import com.sparecode.vipul.onlynow.model.ClientAnalyticsWrapper;
import com.sparecode.vipul.onlynow.model.ClientsAnalyticsCoupon;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.sparecode.vipul.onlynow.R.id.text_datefrom;

public class ClentCouponAnalyticsFragment extends BaseFragment implements ClientAnalyticsBackend.ClientAnalyticsResultProvider {

    @Bind(R.id.viewright)
    View viewright;
    @Bind(R.id.viewcenter)
    View viewcenter;
    @Bind(R.id.viewleft)
    View viewleft;
    @Bind(R.id.text_datepickerlbl)
    LatoTextView textDatepickerlbl;
    @Bind(text_datefrom)
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
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    ClientCouponAnalyticsAdapter clientCouponAnalyticsAdapter;

    private int mYear, mMonth, mDay;
    private View view;
    String fromdate;
    String todate;
    SimpleDateFormat dfDate;
    List<ClientsAnalyticsCoupon> data;

    public ClentCouponAnalyticsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ClentCouponAnalyticsFragment newInstance(String text) {
        ClentCouponAnalyticsFragment fragment = new ClentCouponAnalyticsFragment();
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
        view = inflater.inflate(R.layout.fragment_clent_coupon_analytics, container, false);
        ButterKnife.bind(this, view);
        data = new ArrayList<>();
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        textDatefrom.setText(mYear+ "-" + (mMonth + 1) + "-" + mDay);
        fromdate = mYear + "-" + (mMonth + 1) + "-" + mDay;
        System.out.println("------->fromdate"+fromdate);
        textDateto.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);
        todate = mYear + "-" + (mMonth + 1) + "-" + mDay;
        System.out.println("------->todate"+todate);
        textDatefrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Log.e("fromdate",year + "-" + (monthOfYear + 1) + "-" + dayOfMonth+"");
                                textDatefrom.setText(year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                fromdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                Log.e("fromdate",fromdate);
                                CheckDates(fromdate,todate);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                System.out.println("----->date"+textDatefrom.getText().toString());
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
                                System.out.println("------->todate"+todate);
                                CheckDates(fromdate,todate);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        dfDate = new SimpleDateFormat("yyyy-MM-dd");
        //CheckDates(fromdate,todate);
        setRecycleView();
        return view;
    }
    public  boolean CheckDates(String fromdate,String todate)
    {
        boolean b = false;

        Log.e("--->fromdate",fromdate);
        Log.e("--->todate",todate);
        try {
            if (dfDate.parse(todate).after(dfDate.parse(fromdate)))
            {
                b = true;
                ClientAnalyticsBackend clientAnalyticsBackend = new ClientAnalyticsBackend(getActivity(), "5",fromdate,todate,this);
            }
            else
            {
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
        Log.e("------>boolean",b+"");
        return b;

    }
    public void setRecycleView() {
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        clientCouponAnalyticsAdapter = new ClientCouponAnalyticsAdapter(getActivity(), data, new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null)
                    ((BaseActivity)getActivity()).openClientCouponDetailsPage();
            }
        });
        recyclerview.setAdapter(clientCouponAnalyticsAdapter);
    }

    public void setAdapter(List<ClientsAnalyticsCoupon> dataList) {

            this.data = new ArrayList<>();
            this.data.addAll(dataList);
            clientCouponAnalyticsAdapter.notifyDataSetChanged();
        /*}
        else
        {
            this.data.addAll(dataList);
            clientCouponAnalyticsAdapter.notifyDataSetChanged();
        }*/

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
        setAdapter(clientAnalyticsWrapper.getData().getCoupons());
    }

    @Override
    public void onLoginfailure(String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
