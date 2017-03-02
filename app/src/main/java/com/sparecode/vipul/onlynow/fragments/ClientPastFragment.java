package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.CouponPastdapter;
import com.sparecode.vipul.onlynow.model.ClientPastData;
import com.sparecode.vipul.onlynow.model.ClientPastWrapper;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerViewScrollListener;
import com.sparecode.vipul.onlynow.view.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClientPastFragment extends BaseFragment implements ClientPastBackend.ClientPastResultProvider{

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    CouponPastdapter couponPastdapter;
    private View view;
    List<ClientPastData> data;
    ClientPastBackend clientPastBackend;
    GridLayoutManager gridLayoutManager;

    public ClientPastFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClientPastFragment newInstance(String text) {
        ClientPastFragment fragment = new ClientPastFragment();
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
        view = inflater.inflate(R.layout.fragment_client_past, container, false);
        ButterKnife.bind(this, view);
        data = new ArrayList<>();

        clientPastBackend = new ClientPastBackend(getActivity(),"3",this);
        clientPastBackend.callPagination(1);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        /*recyclerview.setLayoutManager(gridLayoutManager);
        couponLivedapter = new CouponLivedapter(getActivity(),new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null)
                    ((BaseActivity)getActivity()).openClientCouponDetailsPage();
            }
        });
        *///recyclerview.setAdapter(couponLivedapter);
        setRecycleView();
        setPagination(recyclerview);
        return view;
    }
    private void setPagination(RecyclerView recyclerview) {
        recyclerview.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.e("PAGINATION CALL::", "::" + page);
                clientPastBackend.callPagination(page);
            }

            @Override
            public void MaxPage(int maxpage) {
                Log.e("::MAXPAGE::", "" + maxpage);
            }
        });
    }
    public void setRecycleView() {
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        couponPastdapter = new CouponPastdapter(getActivity(), data, new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null)
                    ((BaseActivity)getActivity()).openClientCouponDetailsPage();
            }
        });
        recyclerview.setAdapter(couponPastdapter);
    }

    public void setAdapter(List<ClientPastData> dataList) {
        this.data.addAll(dataList);
        couponPastdapter.notifyDataSetChanged();
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
    public void onSuccessfullLogin(ClientPastWrapper clientPastWrapper) {
        setAdapter(clientPastWrapper.getData());
    }

    @Override
    public void onLoginFailure(String msg) {
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
    }
}
