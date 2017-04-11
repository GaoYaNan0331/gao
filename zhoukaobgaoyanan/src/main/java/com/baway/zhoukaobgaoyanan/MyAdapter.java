package com.baway.zhoukaobgaoyanan;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * data:2017/4/10
 * author:高亚男(Administrator)
 * function:
 */
public class MyAdapter extends FragmentPagerAdapter{
    private List<Fragment> list;
    private String[] str;

    public MyAdapter(FragmentManager fm, List<Fragment> list, String[] str) {
        super(fm);
        this.list = list;
        this.str = str;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
