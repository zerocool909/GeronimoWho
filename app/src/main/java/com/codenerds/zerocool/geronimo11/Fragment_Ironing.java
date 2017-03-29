package com.codenerds.zerocool.geronimo11;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by adraj on 1/1/2017.
 */

public class Fragment_Ironing  extends Fragment

{
    boolean sel_dc_but =true;
    boolean no_sel_dc_but =true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.dry_cleaning_service, container, false);
        final Button sel_dc=(Button)view.findViewById(R.id.but_dcs_selectDryClean);
        final Button no_sel_dc=(Button)view.findViewById(R.id.but_dcs_noSelection);
        sel_dc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                {
                    if (sel_dc_but) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            sel_dc.setBackgroundColor(Color.BLUE);
                            no_sel_dc.setBackgroundColor(Color.GREEN);
                        }
                        sel_dc_but = false;
                    } else {
                        sel_dc.setBackgroundColor(Color.RED);
                        sel_dc_but=true;
                    }
                }
        }
        });
        no_sel_dc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                { if (no_sel_dc_but) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        no_sel_dc.setBackgroundColor(Color.BLUE);
                        sel_dc.setBackgroundColor(Color.RED);
                    }
                    no_sel_dc_but = false;
                }

                    else {
                        no_sel_dc.setBackgroundColor(Color.RED);
                    no_sel_dc_but=true;

                    }
                }


            }
        });

        return view;

    }
}