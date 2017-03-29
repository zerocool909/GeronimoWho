package com.codenerds.zerocool.geronimo11;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by adraj on 1/1/2017.
 */


public class Fragment_DryCleaning extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }
  /*  @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment childFragment = new ChildFragment();
        FragmentTransaction transaction = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            transaction = getChildFragmentManager().beginTransaction();
        }*
        transaction.replace(R.id.child_fragment_container, childFragment).commit();
    }*/
}