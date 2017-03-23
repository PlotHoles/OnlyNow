package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.adapters.CouponLivedapter;
import com.sparecode.vipul.onlynow.model.ClientLiveData;
import com.sparecode.vipul.onlynow.model.ClientLiveWrapper;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerViewScrollListener;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClientLiveFragment extends BaseFragment implements CliebtLiveBackend.ClientLiveResultProvider {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    CouponLivedapter couponLivedapter;
    @Bind(R.id.nodata)
    LatoTextView nodata;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    private View view;
    List<ClientLiveData> data;
    GridLayoutManager gridLayoutManager;
    CliebtLiveBackend cliebtLiveBackend;

    public ClientLiveFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClientLiveFragment newInstance(String text) {
        ClientLiveFragment fragment = new ClientLiveFragment();
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
        view = inflater.inflate(R.layout.fragment_client_live, container, false);
        ButterKnife.bind(this, view);
        data = new ArrayList<>();

        cliebtLiveBackend = new CliebtLiveBackend(getActivity(), getUserId(), this);
        cliebtLiveBackend.callPagination(1);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        /*recyclerview.setLayoutManager(gridLayoutManager);
        couponLivedapter = new CouponLivedapter(getActivity(),new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null)
                    ((BaseActivity)getActivity()).openClientCouponDetailsPage();
            }
        });
        recyclerview.setAdapter(couponLivedapter);*/
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cliebtLiveBackend = new CliebtLiveBackend(getActivity(), getUserId(), ClientLiveFragment.this);
                cliebtLiveBackend.callPagination(1);
            }
        });
        setRecycleView();
        setPagination(recyclerview);
        return view;
    }

    private void setPagination(RecyclerView recyclerview) {
        recyclerview.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.e("PAGINATION CALL::", "::" + page);
                cliebtLiveBackend.callPagination(page);
            }

            @Override
            public void MaxPage(int maxpage) {
                Log.e("::MAXPAGE::", "" + maxpage);
            }
        });
    }

    public void setRecycleView() {
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        couponLivedapter = new CouponLivedapter(getActivity(), data, new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null) {
                    ClientCouponDetailsFragment clientCouponDetailsFragment = new ClientCouponDetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("key", data.get(position).getId());
                    clientCouponDetailsFragment.setArguments(bundle);
                    addFragment(clientCouponDetailsFragment, true);
                    // ((BaseActivity)getActivity()).openClientCouponDetailsPage();
                }

            }
        });
        recyclerview.setAdapter(couponLivedapter);
    }

    public void setAdapter(List<ClientLiveData> dataList) {
        /*HashSet<ClientLiveData> hashSet = new HashSet<ClientLiveData>();
        hashSet.addAll(dataList);
        data.clear();
        data.addAll(hashSet);*/
        data.clear();
        this.data.addAll(dataList);
        couponLivedapter.notifyDataSetChanged();
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
    public void onSuccessfullLogin(ClientLiveWrapper clientLiveWrapper) {
        swiperefresh.setRefreshing(false);
        recyclerview.setVisibility(View.VISIBLE);
        nodata.setVisibility(View.GONE);
        setAdapter(clientLiveWrapper.getData());
        couponLivedapter.notifyDataSetChanged();
    }

    @Override
    public void onLoginFailure(String msg) {
        recyclerview.setVisibility(View.GONE);
        nodata.setVisibility(View.VISIBLE);
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
