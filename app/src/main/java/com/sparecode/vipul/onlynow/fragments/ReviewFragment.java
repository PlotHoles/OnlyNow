package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.ReviewAdapter;
import com.sparecode.vipul.onlynow.dialog.ReviewDialog;
import com.sparecode.vipul.onlynow.model.ReviewWrapper;
import com.sparecode.vipul.onlynow.model.SaveReviewWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ReviewFragment extends Fragment implements ReviewFragmentBackend.ShopReviewDataProvider ,SaveReviewBackend.ReviewDataProvider{

    String shopId;
    @Bind(R.id.textReviewFive)
    LatoTextView textReviewFive;
    @Bind(R.id.textReviewFour)
    LatoTextView textReviewFour;
    @Bind(R.id.textReviewThree)
    LatoTextView textReviewThree;
    @Bind(R.id.textReviewTwo)
    LatoTextView textReviewTwo;
    @Bind(R.id.textReviewOne)
    LatoTextView textReviewOne;

    public ReviewFragment(String shopId) {
        this.shopId = shopId;
    }

    ReviewFragmentBackend reviewFragmentBackend;
    @Bind(R.id.text_rating)
    TextView textRating;
    @Bind(R.id.textView62)
    TextView textView62;
    @Bind(R.id.fivestar_rating)
    RatingBar fivestarRating;
    @Bind(R.id.fourstar_rating)
    RatingBar fourstarRating;
    @Bind(R.id.threestar_rating)
    RatingBar threestarRating;
    @Bind(R.id.twostar_rating)
    RatingBar twostarRating;
    @Bind(R.id.onestar_rating)
    RatingBar onestarRating;
    @Bind(R.id.text_review)
    TextView textReview;
    @Bind(R.id.review_recyclerview)
    RecyclerView reviewRecyclerview;
    ReviewAdapter reviewAdapter;
    SaveReviewBackend saveReviewBackend;
    ReviewDialog reviewDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    SignupWrapper signupWrapper;

    private void getUser() {
        try {
            signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);

        } catch (JsonSyntaxException e) {
            ((BaseActivity) getActivity()).openSplashPage();
        }
    }

    @OnClick(R.id.text_review)
    void onSubmitReviewClick() {
        reviewDialog = new ReviewDialog(new ReviewDialog.OnReviewClick() {
            @Override
            public void onClick(String review, String star) {
                saveReviewBackend   = new SaveReviewBackend(getActivity(),shopId,signupWrapper.getData().getId(),review,star,ReviewFragment.this);
            }
        });
        reviewDialog.show(getActivity().getSupportFragmentManager(), "review");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this, view);
        getUser();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        reviewRecyclerview.setLayoutManager(gridLayoutManager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        reviewFragmentBackend = new ReviewFragmentBackend(getActivity(), shopId, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccess(ReviewWrapper reviewWrapper) {
        reviewAdapter = new ReviewAdapter(getActivity(), reviewWrapper, new OnClickListener() {
            @Override
            public void onItemClicked(int position) {

            }
        });
        reviewRecyclerview.setAdapter(reviewAdapter);
        textRating.setText(reviewWrapper.getData().getRating());
        textReviewFive.setText(reviewWrapper.getData().getStars5());
        textReviewFour.setText(reviewWrapper.getData().getStars4());
        textReviewThree.setText(reviewWrapper.getData().getStars3());
        textReviewTwo.setText(reviewWrapper.getData().getStars2());
        textReviewOne.setText(reviewWrapper.getData().getStars1());
        fivestarRating.setRating(Float.parseFloat(reviewWrapper.getData().getStars5()));
        fourstarRating.setRating(Float.parseFloat(reviewWrapper.getData().getStars4()));
        threestarRating.setRating(Float.parseFloat(reviewWrapper.getData().getStars3()));
        twostarRating.setRating(Float.parseFloat(reviewWrapper.getData().getStars2()));
        onestarRating.setRating(Float.parseFloat(reviewWrapper.getData().getStars1()));


    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void Successfull(SaveReviewWrapper saveReviewWrapper) {
        reviewDialog.dismiss();
        Toast.makeText(getActivity(),saveReviewWrapper.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Failure(String msg) {

    }
}
