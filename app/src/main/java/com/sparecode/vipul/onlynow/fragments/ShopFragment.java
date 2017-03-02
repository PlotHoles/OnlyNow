package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShopFragment extends Fragment {


    @Bind(R.id.ratingBar)
    RatingBar ratingBar;
    @Bind(R.id.textView35)
    TextView textView35;
    @Bind(R.id.imageButton2)
    ImageButton imageButton2;
    @Bind(R.id.textView51)
    TextView textView51;
    @Bind(R.id.textView52)
    TextView textView52;
    @Bind(R.id.textView53)
    TextView textView53;
    @Bind(R.id.textView54)
    TextView textView54;
    @Bind(R.id.textView55)
    TextView textView55;
    @Bind(R.id.textView56)
    TextView textView56;
    @Bind(R.id.textView57)
    TextView textView57;
    @Bind(R.id.textView58)
    TextView textView58;
    @Bind(R.id.textView59)
    TextView textView59;
    @Bind(R.id.imageView21)
    ImageView imageView21;
    @Bind(R.id.textView61)
    TextView textView61;

    public ShopFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ShopFragment newInstance(String text) {
        ShopFragment fragment = new ShopFragment();
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
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
@OnClick(R.id.imageButton2)
void onFavSelected() {
    if (!imageButton2.isSelected())
    {
        imageButton2.setSelected(true);
    }
    else
    {
        imageButton2.setSelected(false);
    }
}


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
