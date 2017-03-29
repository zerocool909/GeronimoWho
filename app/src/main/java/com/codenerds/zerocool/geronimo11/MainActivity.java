package com.codenerds.zerocool.geronimo11;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,Button.OnClickListener, DatePickerDialog.OnDateSetListener,Fragment_Laundry.washAndIron{
    TextView mText;
    TextView address_where;
    TextView sel_date;
    TextView sel_time;
    Spinner sp_adrs;
    Spinner sp_timeSlot;
    ImageView calander;
    ImageView timeslot;
    int CUR_MONTH, CUR_YEAR, CUR_DAY_MONTH, CUR_HOUR, CUR_MINUTE;
    int finalMONTH,finalYEAR,finalDAY_MONTH,finalHOUR,finalMINUTE;
    int start_position=0;
    int pro=0;
    int serv=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageButton playPause=(ImageButton)findViewById(R.id.btn_call);
        playPause.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                /*Toast.makeText(getApplicationContext(),
                        "You have selected the GET URL option", Toast.LENGTH_LONG).show();*/
                dialContactPhone("08142181426");
            }
        });
        //getSupportActionBar().setLogo(R.drawable.per50_ln);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);

        salutation("Aditya");
        kindOfService();
        address_where=(TextView) findViewById(R.id.tv_cm_where);
        address_where.setTextColor(Color.WHITE);
        sel_date= (TextView) findViewById(R.id.tv_cm_dateselected);
        sel_time= (TextView) findViewById(R.id.tv_cm_timeselected);
        sel_date.setTextColor(Color.WHITE);
        sel_time.setTextColor(Color.WHITE);
        final TextView val= (TextView) findViewById(R.id.textView2);
        addItemsOnSpinner_location();
        calander= (ImageView) findViewById(R.id.iv_cm_date);
        calander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c= Calendar.getInstance();
                CUR_YEAR=c.get(Calendar.YEAR);
                CUR_MONTH=c.get(Calendar.MONTH);
                CUR_DAY_MONTH=c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this,MainActivity.this,CUR_YEAR,
                        CUR_MONTH,CUR_DAY_MONTH);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
        timeslot= (ImageView) findViewById(R.id.iv_cm_time);
        timeslot.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                
                final CharSequence[] items = {"10:00 - 12:00 AM", "12:00 - 02:00 PM", "04:00 - 06:00 PM", "06:00 - 08:00 PM"};
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Pick A Time Slot ! ");
                builder.setCancelable(true);


                //  builder.setPositiveButton(R.string.dialog_action_dismiss, null);

                // creating a single choice item menu (radio buttons list)
                // -1 indicates that no item should be selected by default
                // pass index argument starting from 0 to preselect an item if required
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                       sel_time= (TextView) findViewById(R.id.tv_cm_timeselected);
                       sel_time.setText(items[item].toString());
                        sel_time.setTextColor(Color.WHITE);
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        final List<String> service=new ArrayList<String>();
        service.add("Washing and Iron");
        service.add("Dry Cleaning");
        service.add("Wash and Dry");
        service.add("Ironing");
        ImageButton _next= (ImageButton) findViewById(R.id.button_next);
        ImageButton _previous= (ImageButton) findViewById(R.id.button_previous);
        val.setText(service.get(0).toString());
        val.setTextColor(Color.WHITE);
         _next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serv == 3)
                    serv=0;
                else serv++;
                val.setText(service.get(serv).toString());
                //setFragment(serv);
                Button button = (Button) findViewById(R.id.button_proceed);
                button.setVisibility(View.INVISIBLE);

            }
        });
        _previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serv == 0)
                    serv=3;
                else --serv;
                val.setText(service.get(serv).toString());
                //setFragment(serv);
                Button button = (Button) findViewById(R.id.button_proceed);
                button.setVisibility(View.INVISIBLE);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /* the fragment case
    private void setFragment(int serv) {

        if(serv==0) {
            Fragment frag = new Fragment_Laundry();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place, frag);
            //commit transaction
            ft.commit();
        }
        if(serv==1) {
            Fragment frag=new Fragment_DryCleaning();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment_place,frag);
            ft.commit();
        }
        if(serv==2) {
            Fragment frag = new Fragment_WashAndFold();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place, frag);
            //commit transaction
            ft.commit();
        }
        if(serv==3) {
            Fragment frag=new Fragment_Ironing();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment_place,frag);
            ft.commit();
        }


    }
*/
    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }


    private void kindOfService() {
    }


    private void addItemsOnSpinner_location() {
        sp_adrs= (Spinner) findViewById(R.id.spinner2);
        //changing the color of the spinner

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            sp_adrs.setPopupBackgroundResource(R.drawable.spinner);
        }
        List<String> list = new ArrayList<String>();
        list.add("Malaysian Township");
        list.add("Kondapur");
        list.add("Fortune Fields,Madhapur");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        sp_adrs.setAdapter(dataAdapter);
        /*List<String> list = new ArrayList<String>();
        list.add("Malaysian Township");
        list.add("Kondapur");
        list.add("list 3");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.address, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        sp_adrs.setAdapter(adapter);*/


           }


    private void salutation(String var1) {

        mText = (TextView) findViewById(R.id.tv_cm_salutation);


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String str = sdf.format(new Date());

        Calendar c = Calendar.getInstance(); // Get current time
        int hr1 = c.get(Calendar.HOUR_OF_DAY);
        //tv1.setText(String.valueOf(hr1));


        if(hr1>=0 && hr1<4)
        {
            mText.setText("Good Night "+var1+" !");
            mText.setTextColor(Color.WHITE);
        }
        else if(hr1>=4 && hr1<12)
        {
            mText.setText("Good Morning " + var1 + " !");
            mText.setTextColor(Color.WHITE);
        }

        else if(hr1>=12 && hr1<17)
        {
            mText.setText("Good Afternoon "+var1+" !");
            mText.setTextColor(Color.WHITE);
        }
        else if(hr1>=17 && hr1<20)
        {
            mText.setText("Good Evening "+var1+" !");
            mText.setTextColor(Color.WHITE);
        }
        else if(hr1>=20&& hr1<24)
        {
            mText.setText("Good Night "+var1+" !");
            mText.setTextColor(Color.WHITE);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*

   // No Use of Overflow menu as such hence removing 08/11
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.inflateMenu(R.menu.call);
        tb.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return onOptionsItemSelected(item);
                    }
                });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /* if (item.getItemId() == R.id.action_call) {
            call_method();
            return true;
        }


        return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_call:
                Toast.makeText(this, "Action Settings selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
*/
        /*@Override
        public boolean onCreateOptionsMenu(Menu menu) {
            Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
            tb.inflateMenu(R.menu.main);
            tb.setOnMenuItemClickListener(
                    new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            return onOptionsItemSelected(item);
                        }
                    });
            return true;
        }

        //
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_call:
                    Toast.makeText(this, "Action Settings selected", Toast.LENGTH_SHORT).show();
                    return true;

                default:
                    break;
            }
            return super.onOptionsItemSelected(item);
        }*/
    private void call_method() {
        Toast.makeText(getApplicationContext(),
                "You have selected the GET URL option", Toast.LENGTH_LONG).show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_order_history) {

        } else if (id == R.id.nav_my_address) {

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_how_it_works) {

        } else if (id == R.id.nav_call_us) {

        } else if (id == R.id.nav_mail_us) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        finalYEAR=year;
        finalMONTH=month+1;
        finalDAY_MONTH=dayOfMonth;
        Calendar c=Calendar.getInstance();
        CUR_HOUR=c.get(Calendar.HOUR_OF_DAY);
        CUR_MINUTE=c.get(Calendar.MINUTE);
        sel_date.setText("Pickup on : "+finalDAY_MONTH+"/"+finalMONTH+"/"+finalYEAR);
        sel_date.setTextColor(Color.WHITE);
    }

    @Override
    public void onFragmentInteraction(String val) {
        if(val.contains("None"))
        {
            Button button=(Button)findViewById(R.id.button_proceed);
            button.setVisibility(View.INVISIBLE);
        }
        else {
            Button button = (Button) findViewById(R.id.button_proceed);
            button.setVisibility(View.VISIBLE);
        }

    }
}
