package com.baway.zhoukaobgaoyanan;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mPager;
    private List<Fragment> fragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addFragment();
        setTabAdapter();

        new NewBean();

    }

    private void setTabAdapter() {
        MyAdapter adapter=new MyAdapter(MainActivity.this.getSupportFragmentManager(),fragments,new String[]{"头条","社会","时尚","财经","娱乐","国际","国内","科技","军事","体育"});
        mPager.setAdapter(adapter);
        mTab.setupWithViewPager(mPager);

    }

    private void addFragment() {
        fragments.add(UserFragment.getFragment("tt"));
        fragments.add(UserFragment.getFragment("shehui"));
        fragments.add(UserFragment.getFragment("ss"));
        fragments.add(UserFragment.getFragment("cj"));
        fragments.add(UserFragment.getFragment("yl"));
        fragments.add(UserFragment.getFragment("gj"));
        fragments.add(UserFragment.getFragment("gn"));
        fragments.add(UserFragment.getFragment("kj"));
        fragments.add(UserFragment.getFragment("js"));
        fragments.add(UserFragment.getFragment("ty"));

    }

    private void initView() {
        mTab = (TabLayout) findViewById(R.id.tab);
        mPager = (ViewPager) findViewById(R.id.pager);
    }
}
