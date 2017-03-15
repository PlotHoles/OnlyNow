package com.sparecode.vipul.onlynow.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.dialog.CancelDealDialog;
import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.LogoutWrapper;
import com.sparecode.vipul.onlynow.model.UpdateLocationWrapper;
import com.sparecode.vipul.onlynow.model.ZipWrapper;
import com.sparecode.vipul.onlynow.view.CircleImageView;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ClientSettingFragment extends BaseFragment implements UpdateLocationBackend.UpdateLocationResultProvider,LogoutBackend.LogoutBackendResultProvider{

    @Bind(R.id.profile_image)
    CircleImageView profileImage;
    @Bind(R.id.firstname)
    LatoTextView firstname;
    @Bind(R.id.fullname)
    LatoTextView fullname;
    @Bind(R.id.mobile)
    LatoTextView mobile;
    @Bind(R.id.email)
    LatoTextView email;
    @Bind(R.id.text_changepassword)
    LatoTextView textChangepassword;
    @Bind(R.id.relative_changepassword)
    RelativeLayout relativeChangepassword;
    @Bind(R.id.text_changelocation)
    LatoTextView textChangelocation;
    @Bind(R.id.relative_changelocation)
    RelativeLayout relativeChangelocation;
    @Bind(R.id.edit_zipcode)
    LatoEditText editZipcode;
    @Bind(R.id.edit_prefecture)
    LatoEditText editPrefecture;
    @Bind(R.id.edit_cityname)
    LatoEditText editCityname;
    @Bind(R.id.button_change)
    LatoButton buttonChange;
    @Bind(R.id.ziplinear)
    LinearLayout ziplinear;
    @Bind(R.id.text_changecategory)
    LatoTextView textChangecategory;
    @Bind(R.id.relative_changecategory)
    RelativeLayout relativeChangecategory;
    @Bind(R.id.notificationswitch)
    Switch notificationswitch;
    @Bind(R.id.text_pushnotification)
    LatoTextView textPushnotification;
    @Bind(R.id.relative_pushnotification)
    RelativeLayout relativePushnotification;
    @Bind(R.id.text_qa)
    LatoTextView textQa;
    @Bind(R.id.relative_qa)
    RelativeLayout relativeQa;
    @Bind(R.id.text_report)
    LatoTextView textReport;
    @Bind(R.id.relative_report)
    RelativeLayout relativeReport;
    @Bind(R.id.text_about)
    LatoTextView textAbout;
    @Bind(R.id.relative_about)
    RelativeLayout relativeAbout;
    @Bind(R.id.text_blog)
    LatoTextView textBlog;
    @Bind(R.id.relative_blog)
    RelativeLayout relativeBlog;
    @Bind(R.id.text_privacy)
    LatoTextView textPrivacy;
    @Bind(R.id.relative_privacy)
    RelativeLayout relativePrivacy;
    @Bind(R.id.text_terms)
    LatoTextView textTerms;
    @Bind(R.id.relative_terms)
    RelativeLayout relativeTerms;
    @Bind(R.id.text_cancel)
    LatoTextView textCancel;
    @Bind(R.id.relative_cancel)
    RelativeLayout relativeCancel;
    @Bind(R.id.text_logout)
    LatoTextView textLogout;
    @Bind(R.id.relative_logout)
    RelativeLayout relativeLogout;
    private View view;

    public ClientSettingFragment() {
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

        view = inflater.inflate(R.layout.fragment_client_setting, container, false);
        ButterKnife.bind(this, view);

        firstname.setText(getUserData().getFname());
        fullname.setText(getUserData().getFname() + getUserData().getLname());
        mobile.setText(getUserData().getPhone());
        email.setText(getUserData().getEmail());
        String visible = String.valueOf(ziplinear.getVisibility());
        System.out.println("visible"+visible);

        textChangelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String visible = String.valueOf(ziplinear.getVisibility());
                System.out.println("visible"+visible);
                ziplinear.setVisibility(View.VISIBLE);

            }
        });

        editZipcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                String zipurl = "http://zipcloud.ibsnet.co.jp/api/search?zipcode="+editZipcode.getText().toString();
                System.out.println("zipcode"+zipurl);
                new GetRequest<ZipWrapper>().toSimpleRequest(getActivity(), zipurl, ZipWrapper.class, new OnResponse<ZipWrapper>() {
                    @Override
                    public void onSuccess(ZipWrapper zipWrapper) {
                        if (zipWrapper.getStatus() == 400)
                        {
                            Toast.makeText(getActivity(),"Please Select Valid ZipCode",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            editPrefecture.setText(zipWrapper.getResults().get(0).getAddress1());
                            editCityname.setText(zipWrapper.getResults().get(0).getAddress2()+" "+zipWrapper.getResults().get(0).getAddress3());
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

            }
        });

        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"hi",Toast.LENGTH_SHORT).show();

                UpdateLocationBackend updateLocationBackend = new UpdateLocationBackend(getActivity(),getShopId(),editZipcode.getText().toString().trim(),editPrefecture.getText().toString(),editCityname.getText().toString(),ClientSettingFragment.this);
            }
        });
        textChangecategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getActivity(),"hi",Toast.LENGTH_SHORT).show();
                ((BaseActivity)getActivity()).openClientChangeCategoryPage();
            }
        });

        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelDealDialog cancelDealDialog = new CancelDealDialog();
                cancelDealDialog.show(getActivity().getSupportFragmentManager(), "cancel");
                Toast.makeText(getActivity(),"hi",Toast.LENGTH_SHORT).show();
            }
        });
        textLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Logout")
                        .setMessage("Please confirm you are logout")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    LogoutBackend logoutBackend = new LogoutBackend(getActivity(),getUserId(),ClientSettingFragment.this);
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
        });
        return view;
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.setting));
        ((BaseActivity) getActivity()).getTabLayoutclient().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgAdd().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgEdit().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getFab().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarBack().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getTextNext().setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccesssfullLogin(UpdateLocationWrapper updateLocationWrapper) {
        Snackbar.make(view,"Location detals updated successfully",Snackbar.LENGTH_SHORT).show();
        ziplinear.setVisibility(View.GONE);
    }

    @Override
    public void onLoginFailure(String msg) {
        Snackbar.make(view,"Please Provide valid Zipcode",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessfullLogout(LogoutWrapper logoutWrapper) {
        ((BaseActivity)getActivity()).openClientSigninPage();
    }

    @Override
    public void onLogoutFailure(String msg) {

    }
}
