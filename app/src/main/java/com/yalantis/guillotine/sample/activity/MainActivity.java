package com.yalantis.guillotine.sample.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.guillotine.animation.GuillotineAnimation;
import com.yalantis.guillotine.interfaces.GuillotineListener;
import com.yalantis.guillotine.sample.R;

import butterknife.ButterKnife;
import butterknife.BindView;
import fragments.fragment_home;
import fragments.fragment_main;

/**
 * Created by Dmytro Denysenko on 5/4/15.
 */
public class MainActivity extends AppCompatActivity implements  View.OnTouchListener, NavigationView.OnNavigationItemSelectedListener{
    private static final long RIPPLE_DURATION = 250;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.root)
    FrameLayout root;
    @BindView(R.id.content_hamburger)
    View contentHamburger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        ButterKnife.bind(this);


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);

       // GuillotineListener listner;

        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.Home), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
               //.setGuillotineListener(listner)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .setDuration(600)
                .build();

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.activity_frame, new fragment_main()).commit();





       /* new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.events), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                //.setGuillotineListener(listner)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .setDuration(600)
                .build();
                */
        //new GuillotineListener().onGuillotineClosed(this);


        TextView home =  (TextView)guillotineMenu.findViewById(R.id.Home);
        /*home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,testing.class);
                startActivity(i);

            }
        });*/

       //home.setOnTouchListener(this);



    }




    public boolean onTouch(View view, MotionEvent e) {


        Toast.makeText(this, "onTouch", Toast.LENGTH_LONG).show();
        //Intent i=new Intent(MainActivity.this,testing.class);
        //startActivity(i);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.home) {
            fm.beginTransaction().replace(R.id.activity_frame, new fragment_home()).commit();
        }
        return  true;
    }
        /*public void onGuillotineListener()
    {
        Toast.makeText(this,"onTouch",Toast.LENGTH_LONG).show();


    }*/


}
