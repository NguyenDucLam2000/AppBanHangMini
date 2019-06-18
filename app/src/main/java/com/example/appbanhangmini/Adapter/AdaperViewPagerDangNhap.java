package com.example.appbanhangmini.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

import java.util.List;

public class AdaperViewPagerDangNhap extends FragmentStatePagerAdapter
{
    List<String> listTitle;
    List<Fragment> listFragment;
    public AdaperViewPagerDangNhap(List<Fragment> listFragment, FragmentManager fm)
    {
        super(fm);
        this.listFragment = listFragment;
    }

    public AdaperViewPagerDangNhap(List<Fragment> listFragment, List<String> listTitle, FragmentManager fm)
    {
        super(fm);
        this.listFragment = listFragment;
        this.listTitle = listTitle;
    }

    @Override
    public Fragment getItem(int i)
    {
        return listFragment.get(i);
    }

    @Override
    public int getCount()
    {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return listFragment.get(position).toString();
    }
}
