package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.Onlynow;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.LinearsAdapter;
import com.sparecode.vipul.onlynow.model.CategoryData;
import com.sparecode.vipul.onlynow.model.CategoryWrapper;
import com.sparecode.vipul.onlynow.model.ClientSignup1setter;
import com.sparecode.vipul.onlynow.model.Clientsignupsetter;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerViewScrollListener;
import com.sparecode.vipul.onlynow.view.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClientSignupstep2Fragment extends BaseFragment implements Signupstep3Backend.CategoryListProvider{


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    LinearsAdapter linearsAdapter;
    List<CategoryData> categoryDatas;
    private Signupstep3Backend signupstep3Backend;
    GridLayoutManager gridLayoutManager;
    String fname,lname,cname,area,zipcode,prefecture,cityname,streetname,buildname,pnumber,emailaddress,cemailaddress,password,wurl;
    String cat_id;
    String caategory;
    Clientsignupsetter clientsignupsetter;
    ClientSignup1setter clientSignup1setter;

    public ClientSignupstep2Fragment() {
        // Required empty public constructor
    }

    /*public ClientSignupstep2Fragment(String fname) {
        clientsignupsetter = new Clientsignupsetter();
        this.fname = fname;

        clientSignup1setter = new ClientSignup1setter();
        clientSignup1setter.setFirst_name(fname);
    }*/

    // TODO: Rename and change types and number of parameters
    public static ClientSignupstep2Fragment newInstance(String text) {
        ClientSignupstep2Fragment fragment = new ClientSignupstep2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_client_signupstep2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        categoryDatas = new ArrayList<>();
        signupstep3Backend = new Signupstep3Backend(this, getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerview.setLayoutManager(gridLayoutManager);
        linearsAdapter = new LinearsAdapter(categoryDatas, getActivity(), new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                cat_id = categoryDatas.get(position).getId();
                //Toast.makeText(getActivity(),cat_id,Toast.LENGTH_LONG).show();
                Log.e("position",cat_id);
                Onlynow onlynow = (Onlynow)getActivity().getApplicationContext();
                onlynow.setCat_id(categoryDatas.get(position).getId());
                onlynow.getFirst_name();
                String first = onlynow.getFirst_name();
                System.out.println("------>first"+first);
                System.out.println("------>first"+onlynow.getLname());
                System.out.println("------>first"+onlynow.getCname());
                System.out.println("------>first"+onlynow.getArea());
                System.out.println("------>first"+onlynow.getZipcode());
                System.out.println("------>first"+onlynow.getPrefecture());
                System.out.println("------>first"+onlynow.getCityname());
                System.out.println("------>first"+onlynow.getStreetname());
                System.out.println("------>first"+onlynow.getBuildname());
                System.out.println("------>first"+onlynow.getPnumber());
                System.out.println("------>first"+onlynow.getEmailaddress());
                System.out.println("------>first"+onlynow.getCemailaddress());
                System.out.println("------>first"+onlynow.getPassword());
                System.out.println("------>first"+onlynow.getWurl());
                clientsignupsetter = new Clientsignupsetter();
                clientsignupsetter.setCat_id(cat_id);
                clientsignupsetter.setFirst_name(onlynow.getFirst_name());
                clientsignupsetter.setLname(onlynow.getLname());
                clientsignupsetter.setCname(onlynow.getCname());
                clientsignupsetter.setArea(onlynow.getArea());
                clientsignupsetter.setZipcode(onlynow.getZipcode());
                clientsignupsetter.setPrefecture(onlynow.getPrefecture());
                clientsignupsetter.setCityname(onlynow.getCityname());
                clientsignupsetter.setLname(onlynow.getLname());
                clientsignupsetter.setStreetname(onlynow.getStreetname());
                clientsignupsetter.setBuildname(onlynow.getBuildname());
                clientsignupsetter.setPnumber(onlynow.getPnumber());
                clientsignupsetter.setEmailaddress(onlynow.getEmailaddress());
                clientsignupsetter.setCemailaddress(onlynow.getCemailaddress());
                clientsignupsetter.setPassword(onlynow.getPassword());
                clientsignupsetter.setWurl(onlynow.getWurl());
               ClientSignupFragment clientSignupFragment = new ClientSignupFragment(getActivity(),cat_id);
               // clientSignupFragment.call(clientsignupsetter);
            }
        });
        System.out.println("----->signup2"+caategory);
        ((BaseActivity)getActivity()).getTextNext().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("hi","textnext");
            }
        });

        recyclerview.setAdapter(linearsAdapter);
        signupstep3Backend.callPagination(1);
        setPagination(recyclerview);

    }


    private void setPagination(RecyclerView recyclerview) {
        recyclerview.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.e("PAGINATION CALL::", "::" + page);
                signupstep3Backend.callPagination(page);
            }

            @Override
            public void MaxPage(int maxpage) {
                Log.e("::MAXPAGE::", "" + maxpage);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity)getActivity()).getTextNext().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"hi",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onSuccess(CategoryWrapper categoryWrapper) {
        categoryDatas.addAll(categoryWrapper.getData());
        linearsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {

    }
}
