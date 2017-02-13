package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by vipul on 30/1/17.
 */

public abstract class BaseFragment extends Fragment {
    public abstract void setToolbarForFragment();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbarForFragment();
    }


}
