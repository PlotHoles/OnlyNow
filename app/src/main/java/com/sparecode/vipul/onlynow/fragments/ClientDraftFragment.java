package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.adapters.CouponDraftadapter;
import com.sparecode.vipul.onlynow.model.ClientDraftData;
import com.sparecode.vipul.onlynow.model.ClientDraftWrapper;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerViewScrollListener;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClientDraftFragment extends BaseFragment implements ClientDraftBackend.ClientDraftResultProvider {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    CouponDraftadapter couponDraftadapter;
    @Bind(R.id.nodata)
    LatoTextView nodata;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    private View view;
    List<ClientDraftData> data;
    GridLayoutManager gridLayoutManager;
    ClientDraftBackend clientDraftBackend;

    public ClientDraftFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClientDraftFragment newInstance(String text) {
        ClientDraftFragment fragment = new ClientDraftFragment();
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
        view = inflater.inflate(R.layout.fragment_client_draft, container, false);
        ButterKnife.bind(this, view);
        data = new ArrayList<>();

        clientDraftBackend = new ClientDraftBackend(getActivity(), getUserId(), this);
        clientDraftBackend.callPagination(1);

        gridLayoutManager = new GridLayoutManager(getActivity(), 1);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clientDraftBackend = new ClientDraftBackend(getActivity(), getUserId(), ClientDraftFragment.this);
                clientDraftBackend.callPagination(1);
            }
        });
        /*recyclerview.setLayoutManager(gridLayoutManager);
        couponLivedapter = new CouponLivedapter(getActivity(),new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null)
                    ((BaseActivity)getActivity()).openClientCouponDetailsPage();
            }
        });
        recyclerview.setAdapter(couponLivedapter);*/
        setRecycleView();
        setPagination(recyclerview);
        return view;
    }

    private void setPagination(RecyclerView recyclerview) {
        recyclerview.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.e("PAGINATION CALL::", "::" + page);
                clientDraftBackend.callPagination(page);
            }

            @Override
            public void MaxPage(int maxpage) {
                Log.e("::MAXPAGE::", "" + maxpage);
            }
        });
    }

    public void setRecycleView() {
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        couponDraftadapter = new CouponDraftadapter(getActivity(), data, new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null) {
                    ClientCouponDetailsFragment clientCouponDetailsFragment = new ClientCouponDetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("key", data.get(position).getId());
                    clientCouponDetailsFragment.setArguments(bundle);
                    addFragment(clientCouponDetailsFragment, true);
                }
                // ((BaseActivity)getActivity()).openClientCouponDetailsPage();
            }
        });
        recyclerview.setAdapter(couponDraftadapter);
    }

    public void setAdapter(List<ClientDraftData> dataList) {
        /*HashSet<ClientDraftData> hashSet = new HashSet<ClientDraftData>();
        hashSet.addAll(dataList);
        data.clear();
        data.addAll(hashSet);*/
        data.clear();
        this.data.addAll(dataList);
        couponDraftadapter.notifyDataSetChanged();
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
    public void onSuccessfullLogin(ClientDraftWrapper clientDraftWrapper) {
        if (getActivity()!=null){
            if (swiperefresh!=null){
                if (recyclerview!=null) {
                    if (nodata != null) {
                        recyclerview.setVisibility(View.VISIBLE);
                        nodata.setVisibility(View.GONE);
                        swiperefresh.setRefreshing(false);
                        setAdapter(clientDraftWrapper.getData());
                    }}}}
    }

    @Override
    public void onLoginFailure(String msg) {
        if (getActivity()!=null){
            if (recyclerview!=null) {
                if (nodata != null) {
                    //Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
                    recyclerview.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }}}
    }
}
