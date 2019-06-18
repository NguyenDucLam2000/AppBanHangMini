package com.example.appbanhangmini.View;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.appbanhangmini.Adapter.AdaperViewPagerDangNhap;
import com.example.appbanhangmini.R;
import com.example.appbanhangmini.View.Fragment.FragmentDangKy;
import com.example.appbanhangmini.View.Fragment.FragmentDangNhap;
import com.facebook.FacebookSdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DangNhapActivity extends AppCompatActivity
{
    Toolbar toolbar;
    ViewPager vpDangNhap;
    TabLayout tabDangNhap;
    AdaperViewPagerDangNhap adapter;
    List<Fragment> listFragment;
    private void addControls()
    {
       toolbar = findViewById(R.id.toolbar);
       vpDangNhap = findViewById(R.id.vpDangNhap);
       tabDangNhap = findViewById(R.id.tabDangNhap);
       listFragment = new ArrayList<>();
       listFragment.add(new FragmentDangNhap());
       listFragment.add(new FragmentDangKy());
       adapter = new AdaperViewPagerDangNhap(listFragment, getSupportFragmentManager());
       vpDangNhap.setAdapter(adapter);
       tabDangNhap.setupWithViewPager(vpDangNhap);
    }
    private void addToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Đăng nhập/Đăng ký");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addControls();
        addToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
