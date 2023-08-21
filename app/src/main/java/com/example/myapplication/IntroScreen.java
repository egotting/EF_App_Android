package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IntroScreen extends AppCompatActivity {

    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    Button voltabtn,proximobtn, pularbtn;

    TextView[] dots;
    ViewPageAdapter viewPageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        voltabtn = findViewById(R.id.voltaButton);
        proximobtn = findViewById(R.id.proximoButton);
        pularbtn = findViewById(R.id.pularButton);

        voltabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem( 0) > 0){
                    mSlideViewPager.setCurrentItem(getItem(-1),true);
                }
            }
        });

        proximobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem(0) < 1){
                    mSlideViewPager.setCurrentItem(getItem(1), true);
                }else{
                    Intent i = new Intent(IntroScreen.this,mainscreen.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        pularbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(IntroScreen.this,mainscreen.class);
                startActivity(i);
                finish();

            }
        });

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);


        viewPageAdapter = new ViewPageAdapter(this);

        mSlideViewPager.setAdapter(viewPageAdapter);

        setUpIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    public void setUpIndicator(int position){
        dots = new TextView[2];
        mDotLayout.removeAllViews();

        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));

            mDotLayout.addView(dots[i]);

        }
        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);

            if(position > 0){
                voltabtn.setVisibility(View.VISIBLE);
            }else{
                voltabtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getItem(int i){
        return mSlideViewPager.getCurrentItem() + i;
    }
}








