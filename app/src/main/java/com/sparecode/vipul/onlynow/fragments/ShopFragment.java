package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.model.AddFavShopWrapper;
import com.sparecode.vipul.onlynow.model.RemoveFavShopWrapper;
import com.sparecode.vipul.onlynow.model.ShopDetailWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShopFragment extends Fragment implements ShopFragmentBackend.ShopDataProvider,ShopFavFragmentBackend.ShopFavDataProvider,ShopRemoveFavFragmentBackend.ShopRemoveFavDataProvider{
    Context mContext;
    String shopId, userId,id;
    @Bind(R.id.ratingBar)
    RatingBar ratingBar;
    @Bind(R.id.imageButton2)
    ImageButton imageButton2;
    ShopFragmentBackend shopFragmentBackend;
    @Bind(R.id.imageView9)
    ImageView imageView9;
    @Bind(R.id.imageView11)
    ImageView imageView11;
    @Bind(R.id.textCouponDesc)
    LatoTextView textCouponDesc;
    @Bind(R.id.textCategory)
    LatoTextView textCategory;
    @Bind(R.id.textArea)
    LatoTextView textArea;
    @Bind(R.id.textView53)
    LatoTextView textView53;
    @Bind(R.id.textNumber)
    LatoTextView textNumber;
    @Bind(R.id.textView55)
    LatoTextView textView55;
    @Bind(R.id.textWeb)
    LatoTextView textWeb;
    @Bind(R.id.textView57)
    LatoTextView textView57;
    @Bind(R.id.textShopDetail)
    LatoTextView textShopDetail;
    @Bind(R.id.textView59)
    LatoTextView textView59;
    @Bind(R.id.imageView21)
    ImageView imageView21;
    @Bind(R.id.textAddress)
    LatoTextView textAddress;
    @Bind(R.id.text_rating)
    LatoTextView textRating;
    ShopFavFragmentBackend shopFavFragmentBackend;
    ShopRemoveFavFragmentBackend shopRemoveFavFragmentBackend;
    View view;
    public ShopFragment(String shopId, String userId,String id) {
        this.shopId = shopId;
        this.userId= userId;
        this.id=id;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_shop, container, false);
        ButterKnife.bind(this, view);
        getUser();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shopFragmentBackend = new ShopFragmentBackend(getActivity(),shopId, userId, this);
    }

    SignupWrapper signupWrapper;

    private void getUser() {
        try {
            signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);

        } catch (JsonSyntaxException e) {
            ((BaseActivity) getActivity()).openSplashPage();
        }
    }

    @OnClick(R.id.imageButton2)
    void onFavSelected() {
        if (!imageButton2.isSelected()) {
            imageButton2.setSelected(true);
            shopFavFragmentBackend=new ShopFavFragmentBackend(getActivity(),shopId,userId,this);
            Toast.makeText(getActivity(), "Added to Favorites", Toast.LENGTH_SHORT).show();
        } else {
            imageButton2.setSelected(false);
            shopRemoveFavFragmentBackend=new ShopRemoveFavFragmentBackend(id,getActivity(),this);
            Toast.makeText(getActivity(), "Remove to Favorites", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccess(ShopDetailWrapper shopDetailWrapper) {
        textCategory.setText(shopDetailWrapper.getData().getCatName());
        textArea.setText(shopDetailWrapper.getData().getArea());
        textNumber.setText(shopDetailWrapper.getData().getPhone());
        textWeb.setText(shopDetailWrapper.getData().getWeb());
        textShopDetail.setText(shopDetailWrapper.getData().getTitle()+ " \n "+ shopDetailWrapper.getData().getArea()+"\n"+shopDetailWrapper.getData().getZipCode()+"\n"+shopDetailWrapper.getData().getPrefecture()+"\n"+shopDetailWrapper.getData().getCity()+"\n"+shopDetailWrapper.getData().getStreet()+"\n"+shopDetailWrapper.getData().getBuildName()+"\n"+shopDetailWrapper.getData().getPhone()+"\n"+shopDetailWrapper.getData().getWeb()+"\n"+shopDetailWrapper.getData().getDetails());
        textAddress.setText(shopDetailWrapper.getData().getBuildName());
        textRating.setVisibility(View.GONE);
        ratingBar.setVisibility(View.GONE);
        textCouponDesc.setText(shopDetailWrapper.getData().getTitle());
        if (!shopDetailWrapper.getData().getImageURL().trim().isEmpty()) {
            Picasso.with(mContext).load(shopDetailWrapper.getData().getImageURL()).resize(720, 200).into(imageView9);
        }
    }

    @Override
    public void onSuccess(AddFavShopWrapper addFavShopWrapper) {
        id = String.valueOf(addFavShopWrapper.getData().getId());
    }
    @Override
    public void onSuccess(RemoveFavShopWrapper removeFavShopWrapper) {
    }
    @Override
    public void onFailure(String msg) {
        Snackbar.make(view,"Can't Delete Shop Due to Error", Snackbar.LENGTH_SHORT).show();

    }

}
