package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.ClientReviewAdapter;
import com.sparecode.vipul.onlynow.model.ClientReview;
import com.sparecode.vipul.onlynow.model.ClientReviewWrapper;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerViewScrollListener;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClientReviewFragment extends BaseFragment implements ClientReviewBackend.ClientReviewResultProvider {

    @Bind(R.id.text_rating)
    LatoTextView textRating;

    @Bind(R.id.fivestar_rating)
    RatingBar fivestarRating;
    LatoTextView review;
    @Bind(R.id.fourstar_rating)
    RatingBar fourstarRating;
    @Bind(R.id.threestar_rating)
    RatingBar threestarRating;
    @Bind(R.id.twostar_rating)
    RatingBar twostarRating;
    @Bind(R.id.onestar_rating)
    RatingBar onestarRating;
    @Bind(R.id.review_recyclerview)
    RecyclerView reviewRecyclerview;
    @Bind(R.id.text_average)
    LatoTextView textAverage;
    @Bind(R.id.star5)
    LatoTextView star5;
    @Bind(R.id.star4)
    LatoTextView star4;
    @Bind(R.id.star3)
    LatoTextView star3;
    @Bind(R.id.star2)
    LatoTextView star2;
    @Bind(R.id.star1)
    LatoTextView star1;
    @Bind(R.id.nodata)
    LatoTextView nodata;
    @Bind(R.id.linearreview)
    LinearLayout linearreview;
    private View view;
    List<ClientReview> data;
    ClientReviewBackend clientReviewBackend;
    GridLayoutManager gridLayoutManager;
    ClientReviewAdapter clientReviewAdapter;

    public ClientReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_client_review, container, false);
        ButterKnife.bind(this, view);
        data = new ArrayList<>();

        /*reviewAdapter = new ReviewAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        reviewRecyclerview.setLayoutManager(gridLayoutManager);
        reviewRecyclerview.setAdapter(reviewAdapter);*/


        clientReviewBackend = new ClientReviewBackend(getUserId(), getActivity(), this);
        clientReviewBackend.callPagination(1);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        Log.e("shop", getShopId());
        setRecycleView();
        setPagination(reviewRecyclerview);
        return view;
    }

    private void setPagination(RecyclerView recyclerview) {
        recyclerview.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.e("PAGINATION CALL::", "::" + page);
                clientReviewBackend.callPagination(page);
            }

            @Override
            public void MaxPage(int maxpage) {
                Log.e("::MAXPAGE::", "" + maxpage);
            }
        });
    }

    public void setRecycleView() {
        reviewRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        clientReviewAdapter = new ClientReviewAdapter(getActivity(), data, new OnClickListener() {
            @Override
            public void onItemClicked(int position) {

            }
        });
        reviewRecyclerview.setAdapter(clientReviewAdapter);
    }

    public void setAdapter(List<ClientReview> dataList) {
        this.data.addAll(dataList);
        clientReviewAdapter.notifyDataSetChanged();
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.Review));
        ((BaseActivity) getActivity()).getTabLayoutclient().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgAdd().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgEdit().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getFab().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getTextNext().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).openClientCouponPage();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccessfullLogin(ClientReviewWrapper clientReviewWrapper) {
        if (getActivity()!=null) {
            if (linearreview!=null){
            linearreview.setVisibility(View.VISIBLE);
            nodata.setVisibility(View.GONE);
            textRating.setText(clientReviewWrapper.getData().getRating());
            star1.setText(clientReviewWrapper.getData().getStars1());
            star2.setText(clientReviewWrapper.getData().getStars2());
            star3.setText(clientReviewWrapper.getData().getStars3());
            star4.setText(clientReviewWrapper.getData().getStars4());
            star5.setText(clientReviewWrapper.getData().getStars5());

            fivestarRating.setRating(Float.parseFloat(clientReviewWrapper.getData().getStars5()));
            fourstarRating.setRating(Float.parseFloat(clientReviewWrapper.getData().getStars4()));
            threestarRating.setRating(Float.parseFloat(clientReviewWrapper.getData().getStars3()));
            twostarRating.setRating(Float.parseFloat(clientReviewWrapper.getData().getStars2()));
            onestarRating.setRating(Float.parseFloat(clientReviewWrapper.getData().getStars1()));

            setAdapter(clientReviewWrapper.getData().getReviews());
        }}
        }

    @Override
    public void onLoginfailure(String msg) {
        if (getActivity()!=null){
            if (linearreview!=null){
                if (nodata != null) {
                    Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
                    nodata.setVisibility(View.VISIBLE);
                    linearreview.setVisibility(View.GONE);
                }}}

    }
}
