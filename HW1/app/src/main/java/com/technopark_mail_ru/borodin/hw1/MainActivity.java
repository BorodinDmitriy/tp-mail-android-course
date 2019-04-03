package com.technopark_mail_ru.borodin.hw1;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements GridListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(int number, String numberColor) {
        NumberDetailFragment numberDetailFragment = new NumberDetailFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        numberDetailFragment.setNumber(number);
        numberDetailFragment.setNumberColor(Color.parseColor(numberColor));
        fragmentTransaction.replace(R.id.number_container,numberDetailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
