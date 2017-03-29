package com.codenerds.zerocool.geronimo11;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

/**
 * Created by adraj on 1/1/2017.
 */

public class Fragment_Laundry extends Fragment {
    private washAndIron mListener;
    public interface washAndIron {
        // TODO: Update argument type and name
        void onFragmentInteraction(String str);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (washAndIron) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_two, container, false);
        final CheckBox regular=(CheckBox)view.findViewById(R.id.checkBox_regular);
        final CheckBox subscription=(CheckBox)view.findViewById(R.id.checkBox_subscription);
        final CheckBox perItemBased=(CheckBox)view.findViewById(R.id.checkBox_eachItem);
        final CheckBox kgBased=(CheckBox)view.findViewById(R.id.checkBox_kgwise);
        final CheckBox selectClothes=(CheckBox)view.findViewById(R.id.checkBox_selectclothes);
        final CheckBox noSelection=(CheckBox)view.findViewById(R.id.checkBox_noselection);
        perItemBased.setVisibility(View.INVISIBLE);
        kgBased.setVisibility(View.INVISIBLE);
        selectClothes.setVisibility(View.INVISIBLE);
        noSelection.setVisibility(View.INVISIBLE);
        regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction("None");
                perItemBased.setVisibility(View.VISIBLE);
                perItemBased.setChecked(false);
                kgBased.setVisibility(View.VISIBLE);
                kgBased.setChecked(false);
                selectClothes.setChecked(false);
                noSelection.setChecked(false);
                if(subscription.isChecked())
                    subscription.setChecked(false);

            }
        });
        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction("subscription");
                if(regular.isChecked()) {
                    regular.setChecked(false);
                    if(regular.isChecked()) {
                        perItemBased.setChecked(false);}
                    perItemBased.setVisibility(View.INVISIBLE);
                    kgBased.setVisibility(View.INVISIBLE);
                    selectClothes.setVisibility(View.INVISIBLE);
                    noSelection.setVisibility(View.INVISIBLE);
                    kgBased.setChecked(false);
                    selectClothes.setChecked(false);
                    noSelection.setChecked(false);

                }

            }
        });
        perItemBased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectClothes.setVisibility(View.VISIBLE);
                selectClothes.setChecked(false);
                noSelection.setVisibility(View.VISIBLE);
                noSelection.setChecked(false);
                if(kgBased.isChecked())
                    kgBased.setChecked(false);
                mListener.onFragmentInteraction("None");
            }
        });
        kgBased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction("Regular_Kg");
                if(perItemBased.isChecked()) {
                    perItemBased.setChecked(false);
                }

                selectClothes.setVisibility(View.INVISIBLE);
                noSelection.setVisibility(View.INVISIBLE);
            }
        });
        selectClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noSelection.setChecked(false);
                if(!noSelection.isChecked() && !selectClothes.isChecked())
                {
                    mListener.onFragmentInteraction("None");
                }
                else
                    mListener.onFragmentInteraction("Regular_Item_SelectClothes");

            }
        });
        noSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectClothes.setChecked(false);
                if(!noSelection.isChecked() && !selectClothes.isChecked())
                {
                    mListener.onFragmentInteraction("None");
                }
                else
                    mListener.onFragmentInteraction("Regular_Item_NoSelectClothes");
            }
        });

       /* if(regular.isChecked())
        {
            perItemBased.setVisibility(View.VISIBLE);
            kgBased.setVisibility(View.VISIBLE);
        }
       if(subscription.isChecked())
       {
           regular.setChecked(false);
           perItemBased.setVisibility(View.INVISIBLE);
           kgBased.setVisibility(View.INVISIBLE);
           selectClothes.setVisibility(View.INVISIBLE);
           noSelection.setVisibility(View.INVISIBLE);
       }
        if(perItemBased.isChecked())
        {
            selectClothes.setVisibility(View.VISIBLE);
            noSelection.setVisibility(View.VISIBLE);
        }
        /*
        switch(view.getId()) {
            case R.id.checkbox_meat:
                if (checked)
                // Put some meat on the sandwich
                else
                // Remove the meat
                break;
            case R.id.checkbox_cheese:
                if (checked)
                // Cheese me
                else
                // I'm lactose intolerant
                break;
            // TODO: Veggie sandwich
        }
        regular.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ( regular.isChecked() ||subscription.isChecked()) {

                } else {
                    subscription.setChecked(true);
                }

            }
        });
        subscription.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (regular.isChecked() || subscription.isChecked()) {

                } else {
                    regular.setChecked(true);
                        perItemBased.setVisibility(View.VISIBLE);
                        kgBased.setVisibility(View.VISIBLE);
                        selectClothes.setVisibility(View.VISIBLE);
                        noSelection.setVisibility(View.VISIBLE);

                }

            }
        });
        */
        return(view);
    }




}
