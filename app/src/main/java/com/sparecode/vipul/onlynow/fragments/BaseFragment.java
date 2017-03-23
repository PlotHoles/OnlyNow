package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.sparecode.vipul.onlynow.activity.MainActivity;
import com.sparecode.vipul.onlynow.interfaces.SortInterface;
import com.sparecode.vipul.onlynow.model.LoginData;

/**
 * Created by vipul on 30/1/17.
 */

public abstract class BaseFragment extends Fragment implements SortInterface{
    public abstract void setToolbarForFragment();

    @Override
    public void onsuccessfull1(String value) {
        setSortInterface(this);
    }

    SortInterface sortInterface;

    public SortInterface getSortInterface() {
        return sortInterface;
    }

    public void setSortInterface(SortInterface sortInterface) {
        this.sortInterface = sortInterface;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbarForFragment();
    }

    public void addFragment(BaseFragment fragment, boolean isReplace) {
        if (getActivity() != null)
            ((MainActivity)getActivity()).addFragment(fragment,true);
    }

    public void menusort(String value)
    {
        ((MainActivity)getActivity()).menu(value);
    }
    public String getUserId() {
        LoginData data;
        if (getUserData() != null)
        {
            data = getUserData();
            return data.getId();
        }
        return "0";
    }

    public String getShopId() {
        LoginData data;
        if (getUserData() != null)
        {
            data = getUserData();
            return data.getShopId();
        }
        return "0";
    }

    public LoginData getUserData() {
        if (getActivity() != null)
            return ((MainActivity)getActivity()).getUserData();
        return null;
    }
}
