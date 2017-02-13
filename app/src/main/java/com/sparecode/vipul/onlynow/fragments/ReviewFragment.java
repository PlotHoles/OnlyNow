package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.adapters.CardAdapter;
import com.sparecode.vipul.onlynow.adapters.ReviewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ReviewFragment extends Fragment {

    @Bind(R.id.text_rating)
    TextView textRating;
    @Bind(R.id.textView62)
    TextView textView62;
    @Bind(R.id.fivestar_rating)
    RatingBar fivestarRating;
    @Bind(R.id.textView63)
    TextView textView63;
    @Bind(R.id.fourstar_rating)
    RatingBar fourstarRating;
    @Bind(R.id.textView64)
    TextView textView64;
    @Bind(R.id.threestar_rating)
    RatingBar threestarRating;
    @Bind(R.id.textView65)
    TextView textView65;
    @Bind(R.id.twostar_rating)
    RatingBar twostarRating;
    @Bind(R.id.textView66)
    TextView textView66;
    @Bind(R.id.onestar_rating)
    RatingBar onestarRating;
    @Bind(R.id.textView67)
    TextView textView67;
    @Bind(R.id.text_review)
    TextView textReview;
    @Bind(R.id.review_recyclerview)
    RecyclerView reviewRecyclerview;
    ReviewAdapter reviewAdapter;

    public ReviewFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ReviewFragment newInstance(String text) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putString("arg", text);
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
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this, view);

        reviewAdapter = new ReviewAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        reviewRecyclerview.setLayoutManager(gridLayoutManager);
        reviewRecyclerview.setAdapter(reviewAdapter);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
