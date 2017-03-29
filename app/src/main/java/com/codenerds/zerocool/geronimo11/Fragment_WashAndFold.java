package com.codenerds.zerocool.geronimo11;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by adraj on 1/1/2017.
 */

public class  Fragment_WashAndFold extends Fragment

    {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.laundry_service, container, false);
    }
}
