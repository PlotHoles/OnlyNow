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
import com.sparecode.vipul.onlynow.adapters.CouponDraftadapter;
import com.sparecode.vipul.onlynow.model.ClientDraftData;
import com.sparecode.vipul.onlynow.model.ClientDraftWrapper;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerViewScrollListener;
import com.sparecode.vipul.onlynow.view.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClientDraftFragment extends BaseFragment implements ClientDraftBackend.ClientDraftResultProvider  {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    CouponDraftadapter couponDraftadapter;
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

        clientDraftBackend = new ClientDraftBackend(getActivity(),"5",this);
        clientDraftBackend.callPagination(1);

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
                if (getActivity() != null)
                    ((BaseActivity)getActivity()).openClientCouponDetailsPage();
            }
        });
        recyclerview.setAdapter(couponDraftadapter);
    }

    public void setAdapter(List<ClientDraftData> dataList) {
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
        setAdapter(clientDraftWrapper.getData());
    }

    @Override
    public void onLoginFailure(String msg) {
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
    }
}
